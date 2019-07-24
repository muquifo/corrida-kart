package com.murilo.corridakart.service

import com.murilo.corridakart.exception.CorridaCanceladaException
import com.murilo.corridakart.model.ParciaisPiloto
import com.murilo.corridakart.model.Piloto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.time.LocalTime

class ResultadoCorridaTest {

    @Test
    fun `Dado as parciais dos pilotos quando obter o resultado final deve retornar a colocaco dos pilotos`() {

        val hora = LocalTime.of(23,30,33,0)
        val tempoVolta = LocalTime.of(0,1,2,0)

        val parcialPiloto = hashMapOf(
            0 to ParciaisPiloto(hora, Piloto("1","Piloto 1"),4,
                tempoVolta, 43.243),
            1 to ParciaisPiloto(hora.plusMinutes(1), Piloto("2","Piloto 2"),4,
                tempoVolta.plusMinutes(2), 41.243),
            2 to ParciaisPiloto(hora.plusMinutes(2), Piloto("3","Piloto 3"),3,
                tempoVolta.plusMinutes(4), 32.000)
        )

        val resultadoFinal = ResultadoCorrida().getResultadoFinal(parcialPiloto.values.toList())

        assertEquals(parcialPiloto.getValue(0).piloto, resultadoFinal.listaResultadoFinalPilotos.get(0).piloto)

    }

    @Test
    fun `Dado as parciais dos pilotos quando obter o resultado final deve retornar os pilotos desclassificados`() {

        val hora = LocalTime.of(23,30,33,0)
        val tempoVolta = LocalTime.of(0,1,2,0)

        val parcialPiloto = hashMapOf(
            0 to ParciaisPiloto(hora, Piloto("1","Piloto 1"),4,
                tempoVolta, 43.243),
            1 to ParciaisPiloto(hora.plusMinutes(1), Piloto("2","Piloto 2"),4,
                tempoVolta.plusMinutes(2), 41.243),
            2 to ParciaisPiloto(hora.plusMinutes(2), Piloto("3","Piloto 3"),3,
                tempoVolta.plusMinutes(4), 32.000)
        )

        val resultadoFinal = ResultadoCorrida().getResultadoFinal(parcialPiloto.values.toList())

        assertEquals(parcialPiloto.getValue(2).piloto, resultadoFinal.listaPilotosDesclassificados.get(0).piloto)

    }

    @Test
    fun `Dado as parciais dos pilotos quando obter o resultado final deve retornar a melhor volta`() {

        val hora = LocalTime.of(23,30,33,0)
        val tempoVolta = LocalTime.of(0,0,0,0)

        val parcialPiloto = hashMapOf(
            0 to ParciaisPiloto(hora, Piloto("1","Piloto 1"),4,
                tempoVolta.plusMinutes(2), 43.243),
            1 to ParciaisPiloto(hora.plusMinutes(1), Piloto("2","Piloto 2"),4,
                tempoVolta.plusMinutes(1), 41.243),
            2 to ParciaisPiloto(hora.plusMinutes(2), Piloto("3","Piloto 3"),4,
                tempoVolta.plusMinutes(4), 32.000)
        )

        val resultadoFinal = ResultadoCorrida().getResultadoFinal(parcialPiloto.values.toList())

        assertEquals(parcialPiloto.getValue(1).piloto, resultadoFinal.melhorVoltaCorrida?.piloto)

    }

    @Test
    fun `Dado as parciais quando obter todos os pilotos desclassificados deve retornar uma exception` (){

        val hora = LocalTime.of(23,30,33,0)
        val tempoVolta = LocalTime.of(0,1,2,0)

        val parcialPiloto = hashMapOf(
            0 to ParciaisPiloto(hora, Piloto("1","Piloto 1"),4,
                tempoVolta, 43.243),
            1 to ParciaisPiloto(hora.plusMinutes(1), Piloto("2","Piloto 2"),2,
                tempoVolta.plusMinutes(2), 41.243),
            2 to ParciaisPiloto(hora.plusMinutes(2), Piloto("3","Piloto 3"),3,
                tempoVolta.plusMinutes(4), 32.000)
        )

        assertThrows(CorridaCanceladaException::class.java) {
            ResultadoCorrida().getResultadoFinal(parcialPiloto.values.toList())
        }

    }

}