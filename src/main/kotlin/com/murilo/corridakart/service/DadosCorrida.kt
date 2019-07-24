package com.murilo.corridakart.service

import com.murilo.corridakart.model.ParciaisPiloto
import com.murilo.corridakart.model.Piloto
import toHora
import toListaValores
import toTempoMinSecMil
import toVelocidadeDouble

class DadosCorrida {

    fun obtemParciais(): List<ParciaisPiloto> {

        val bufferedReader = javaClass.getResourceAsStream("/log_corrida.txt").bufferedReader()

        val listaParciais = ArrayList<ParciaisPiloto>()

        bufferedReader.useLines { linha ->
            linha.forEachIndexed { index, it ->
                if (index != 0) {
                    listaParciais.add(getParciaisPorLinha(it))
                }
            }
        }

        return listaParciais.sortedBy { parcial -> parcial.hora }
    }

    private fun getParciaisPorLinha(linha: String): ParciaisPiloto {

        val valor = linha.toListaValores()

        val parciaisPiloto = ParciaisPiloto(
            valor[0].toHora(),
            Piloto(valor[1], valor[3]),
            valor[4].toInt(),
            valor[5].toTempoMinSecMil(),
            valor[6].toVelocidadeDouble()
        )

        return parciaisPiloto
    }

}