package com.murilo.corridakart.service

import com.murilo.corridakart.exception.CorridaCanceladaException
import difTempo
import com.murilo.corridakart.model.MelhorVolta
import com.murilo.corridakart.model.ParciaisPiloto
import com.murilo.corridakart.model.ResultadoFinal
import com.murilo.corridakart.model.ResultadoGeralPiloto
import java.time.LocalTime

class ResultadoCorrida {

    fun getResultadoFinal(parciaisPilotoList: List<ParciaisPiloto>): ResultadoFinal {

        val listaResultadoPilotos = ResultadoPiloto().obtemResultadoPilotos(parciaisPilotoList)

        val pilotosClassificadosMap = listaResultadoPilotos.filterValues { value -> value.numeroTotalVoltas == 4}
        val pilotosDesclassificados = listaResultadoPilotos.filterValues { value -> value.numeroTotalVoltas != 4}

        if (!pilotosClassificadosMap.isEmpty()){

            val listaColocacaoPilotos = obtemResultadoColocacao(pilotosClassificadosMap)
            val melhorVolta = obtemMelhorVolta(listaColocacaoPilotos)

            return ResultadoFinal(listaColocacaoPilotos, pilotosDesclassificados.values.toList(), melhorVolta)

        } else {
            throw CorridaCanceladaException(" Nenhum piloto Classificado - CORRIDA CANCELADA !!! ")
        }

    }

    private fun obtemResultadoColocacao(listaResultadoPilotos: Map<String, ResultadoGeralPiloto>) : List<ResultadoGeralPiloto> {

        var tempoVencedor = LocalTime.of(0,0,0,0)

        var colocao = 0

        val listaRetorno = listaResultadoPilotos.values.sortedBy { value -> value.tempoTotalCorrida }

        listaRetorno.forEachIndexed { index, resultado ->
            resultado.posicaoChegada = ++colocao

            if (index == 0) {
                tempoVencedor = resultado.tempoTotalCorrida
            } else {
                calculaTempoAposVencedor(resultado, tempoVencedor)
            }
        }

        return listaRetorno
    }

    private fun obtemMelhorVolta(listaResultadoPilotos: List<ResultadoGeralPiloto>) : MelhorVolta? {
        var resultadoPiloto = listaResultadoPilotos.sortedBy { resultado -> resultado.tempoMelhorVolta }.get(0)
        return MelhorVolta(resultadoPiloto.piloto, resultadoPiloto.tempoMelhorVolta)
    }

    private fun calculaTempoAposVencedor(resultado: ResultadoGeralPiloto, tempoVencedor: LocalTime) {
        resultado.tempoAposVencedor = tempoVencedor.difTempo(resultado.tempoTotalCorrida)
    }

}