package com.murilo.corridakart.model

import java.time.LocalTime

data class ResultadoGeralPiloto (
    val piloto: Piloto,
    var posicaoChegada: Int = 0,
    var numeroTotalVoltas: Int = 0,
    var somaTotalVelocidade: Double = 0.000,
    var tempoTotalCorrida: LocalTime = LocalTime.of(0, 0, 0, 0),
    var tempoMelhorVolta: LocalTime = LocalTime.of(23, 0, 0, 0),
    var velocidadeMediaCorrida: Double = 0.000,
    var tempoAposVencedor: LocalTime = LocalTime.of(0, 0, 0, 0)
)