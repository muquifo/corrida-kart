package com.murilo.corridakart.service

import com.murilo.corridakart.exception.SemDadosException
import com.murilo.corridakart.model.ParciaisPiloto
import com.murilo.corridakart.model.ResultadoGeralPiloto

class ResultadoPiloto {

    fun obtemResultadoPilotos(parciaisPiloto: List<ParciaisPiloto>) : HashMap<String, ResultadoGeralPiloto> {

        val resultadoPilotos = HashMap<String, ResultadoGeralPiloto>()

        if (parciaisPiloto.isEmpty()){
            throw SemDadosException(" Sem Dados dos Pilotos para Processar o Resultado ")
        }

        for (parcial in parciaisPiloto) {

            var codigoPiloto = parcial.piloto.codigo

            if (!resultadoPilotos.contains(codigoPiloto)){
                val resultadoPiloto = ResultadoGeralPiloto(piloto = parcial.piloto)
                resultadoPilotos.put(codigoPiloto, resultadoPiloto)
            }

            calculaParciaisPiloto(resultadoPilotos.get(codigoPiloto), parcial)
        }

        return resultadoPilotos
    }


    private fun calculaParciaisPiloto(resultadoPiloto: ResultadoGeralPiloto?, parciaisPiloto: ParciaisPiloto) {

        if (resultadoPiloto != null) {

            if (parciaisPiloto.tempoVolta.isBefore(resultadoPiloto.tempoMelhorVolta)){
                resultadoPiloto.tempoMelhorVolta = parciaisPiloto.tempoVolta
            }

            val hora = parciaisPiloto.tempoVolta.hour.toLong()
            val minuto = parciaisPiloto.tempoVolta.minute.toLong()
            val segundo = parciaisPiloto.tempoVolta.second.toLong()
            val nano = parciaisPiloto.tempoVolta.nano.toLong()

            resultadoPiloto.tempoTotalCorrida = resultadoPiloto.tempoTotalCorrida.plusHours(hora).plusMinutes(minuto).plusSeconds(segundo).plusNanos(nano)

            resultadoPiloto.somaTotalVelocidade += parciaisPiloto.velocidadeMediaVolta
            resultadoPiloto.numeroTotalVoltas = parciaisPiloto.numeroVolta

            resultadoPiloto.velocidadeMediaCorrida = resultadoPiloto.somaTotalVelocidade / resultadoPiloto.numeroTotalVoltas

        }

    }

}