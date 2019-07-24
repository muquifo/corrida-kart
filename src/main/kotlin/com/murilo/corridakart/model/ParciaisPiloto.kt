package com.murilo.corridakart.model

import java.time.LocalTime

data class ParciaisPiloto (
    val hora: LocalTime,
    val piloto: Piloto,
    val numeroVolta: Int,
    val tempoVolta: LocalTime,
    val velocidadeMediaVolta: Double
)