package com.murilo.corridakart.model

data class ResultadoFinal (val listaResultadoFinalPilotos: List<ResultadoGeralPiloto>,
                           val listaPilotosDesclassificados: List<ResultadoGeralPiloto>,
                           val melhorVoltaCorrida: MelhorVolta?)