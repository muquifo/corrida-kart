import com.murilo.corridakart.model.ResultadoFinal
import com.murilo.corridakart.service.DadosCorrida
import com.murilo.corridakart.service.ResultadoCorrida
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter


fun main() {

    println("######################################### CORRIDA DE KART #########################################")
    println(" ")

    try {

        val listaParciaisPiloto = DadosCorrida().obtemParciais()

        val resultadoFinal = ResultadoCorrida().getResultadoFinal(listaParciaisPiloto)

        exibirResultado(resultadoFinal)

    } catch (e: Exception) {
        println(e.message)
    }

    println(" ")
    println("################################################## FIM ###################################################")
}

private fun exibirResultado (resultadoFinal: ResultadoFinal) {
    val df = DecimalFormat("#,###.000")
    val formatter = DateTimeFormatter.ofPattern("mm:ss.SSSSSSSSS")
    val formatoTabelaFinal = "| %-15d | %-13s | %-14s | %-21d | %-20s |%n"
    val formatoTabelaMelhorVolta = "| %-13s | %-14s | %-18s |%n"
    val formatoTabelaDetalhe = "| %-13s | %-14s | %-18s | %-24s | %-19s |%n"

    println("Resultado Final")
    println("+-------------------------------------------------------------------------------------------------+")
    println("| Posição Chegada | Codigo Piloto | Nome Piloto    | Qtde Voltas Completas | Tempo total de Prova |")
    println("+-----------------+---------------+----------------+-----------------------+----------------------+")
    resultadoFinal.listaResultadoFinalPilotos.forEach { r ->
        System.out.format(formatoTabelaFinal, r.posicaoChegada, r.piloto.codigo, r.piloto.nome,
            r.numeroTotalVoltas, r.tempoTotalCorrida.format(formatter) )
    }
    println("+-------------------------------------------------------------------------------------------------+")
    println(" ")
    println("Melhor Volta")
    println("+-----------------------------------------------------+")
    println("| Codigo Piloto | Nome Piloto    | Tempo Melhor Volta |")
    println("+---------------+----------------+--------------------+")
    System.out.format(formatoTabelaMelhorVolta, resultadoFinal.melhorVoltaCorrida!!.piloto.codigo,
        resultadoFinal.melhorVoltaCorrida.piloto.nome, resultadoFinal.melhorVoltaCorrida.tempoMelhorVolta.format(formatter))
    println("+---------------+----------------+--------------------+")
    println(" ")
    println("Detalhe Corrida")
    println("+------------------------------------------------------------------------------------------------------+")
    println("| Codigo Piloto | Nome Piloto    | Tempo Melhor Volta | Velocidade Média Corrida | Tempo após Vencedor |")
    println("+---------------+----------------+--------------------+--------------------------+---------------------+")
    resultadoFinal.listaResultadoFinalPilotos.forEach { r ->
        System.out.format(formatoTabelaDetalhe, r.piloto.codigo, r.piloto.nome, r.tempoMelhorVolta.format(formatter), df.format(r.velocidadeMediaCorrida), r.tempoAposVencedor.format(formatter))
    }

    if (!resultadoFinal.listaPilotosDesclassificados.isEmpty()) {
        val formatoTabelaDesclassificados = "| %-13s | %-14s | %-21s | %-18s | %-24s |%n"
        println("+------------------------------------------------------------------------------------------------------+")
        println(" ")
        println("Desclassificados")
        println("+--------------------------------------------------------------------------------------------------------+")
        println("| Codigo Piloto | Nome Piloto    | Qtde Voltas Completas | Tempo Melhor Volta | Velocidade Média Corrida |")
        println("+---------------+----------------+-----------------------+--------------------+--------------------------+")
        resultadoFinal.listaPilotosDesclassificados.forEach { r ->
            System.out.format(formatoTabelaDesclassificados, r.piloto.codigo, r.piloto.nome, r.numeroTotalVoltas, r.tempoMelhorVolta.format(formatter), df.format(r.velocidadeMediaCorrida))
        }
        println("+--------------------------------------------------------------------------------------------------------+")
        println(" ")
    }

}