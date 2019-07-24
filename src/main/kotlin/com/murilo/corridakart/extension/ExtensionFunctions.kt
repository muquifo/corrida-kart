import java.time.LocalTime

fun String.toListaValores() : List<String> {
    val pattern = "\\s+"
    val replacement = ";"
    val rgx = Regex(pattern, RegexOption.MULTILINE)

    val result = rgx.replace(this, replacement)
    return result.split(";")
}

fun String.toHora() : LocalTime {
    return LocalTime.of(this.substring(0,2).toInt(),this.substring(3,5).toInt(),
        this.substring(6,8).toInt(),this.substring(9,12).toInt())
}

fun String.toTempoMinSecMil() : LocalTime {
    return LocalTime.of(0, this.substring(0,1).toInt(), this.substring(2,4).toInt(), this.substring(5,8).toInt())
}

fun String.toVelocidadeDouble() : Double {
    return this.replace(",",".").toDouble()
}


fun LocalTime.difTempo(tempoDif: LocalTime) : LocalTime {

    val hora = this.hour.toLong()
    val minuto = this.minute.toLong()
    val segundo = this.second.toLong()
    val nano = this.nano.toLong()

    return tempoDif.minusHours(hora).minusMinutes(minuto).minusSeconds(segundo).minusNanos(nano)
}