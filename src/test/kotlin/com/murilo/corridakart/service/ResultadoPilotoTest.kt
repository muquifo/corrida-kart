package com.murilo.corridakart.service

import com.murilo.corridakart.model.ParciaisPiloto
import com.murilo.corridakart.model.Piloto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalTime

class ResultadoPilotoTest {

    @Test
    fun `Dado as parciais dos pilotos quando obter o resultado deve retornar a melhor volta de cada piloto`() {

        val hora = LocalTime.of(20,30,0,0)
        val tempoVolta = LocalTime.of(0,0,0,0)

        val parcialPiloto = hashMapOf(
            0 to ParciaisPiloto(hora.plusMinutes(1), Piloto("1","Piloto 1"),3,
                tempoVolta.plusMinutes(3), 43.243),
            1 to ParciaisPiloto(hora.plusMinutes(2), Piloto("2","Piloto 2"),3,
                tempoVolta.plusMinutes(2), 41.243),
            2 to ParciaisPiloto(hora.plusMinutes(3), Piloto("3","Piloto 3"),3,
                tempoVolta.plusMinutes(5), 32.000),
            3 to ParciaisPiloto(hora.plusMinutes(5), Piloto("1","Piloto 1"),4,
                tempoVolta.plusMinutes(1), 32.000),
            4 to ParciaisPiloto(hora.plusMinutes(6), Piloto("2","Piloto 2"),4,
                tempoVolta.plusMinutes(3), 32.000),
            5 to ParciaisPiloto(hora.plusMinutes(7), Piloto("3","Piloto 3"),4,
                tempoVolta.plusMinutes(3), 32.000)
        )

        val resultadoPilotos = ResultadoPiloto().obtemResultadoPilotos(parcialPiloto.values.toList())

        assertEquals(parcialPiloto.getValue(3).tempoVolta, resultadoPilotos.getValue("1").tempoMelhorVolta)
        assertEquals(parcialPiloto.getValue(1).tempoVolta, resultadoPilotos.getValue("2").tempoMelhorVolta)
        assertEquals(parcialPiloto.getValue(5).tempoVolta, resultadoPilotos.getValue("3").tempoMelhorVolta)

    }

}