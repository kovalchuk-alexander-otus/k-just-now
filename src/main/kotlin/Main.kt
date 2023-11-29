import kotlin.random.Random

fun main() {

    // блок объявления констант
    val minute = 60
    val hour = 60 * 60
    val day = 24 * 60 * 60
    val cMinute = 0
    val cHour = 1
    val timeWords = arrayOf(arrayOf("минуту", "минуты", "минут"), arrayOf("час", "часа", "часов"))

    /**
     * склонение числительных
     *
     * 1 минуту назад,
     * 2 минуты назад,
     * 5 минут назад,
     * 11 минут назад,
     * 21 минуту назад,
     * 25 минут назад.
     * То же самое с часами: час, часа, часов.
     */
    fun declensionOfNumerals(time: Long, type: Int): String =
        when {
            ((time - 1) % 10).toInt() == 0 && ((time - 11) % 100).toInt() != 0 -> timeWords[type][0]
            time % 10 in 2..4 && time % 100 !in 12..14 -> timeWords[type][1]
            else -> timeWords[type][2]
        }

    /**
     * функция, возвращаем шаблонную фразу вида:
     *
     *     был(а) только что
     *     был(а) в сети 3 часа назад
     */
    fun agoToText(timeInSeconds: Long): String =
        "был(а) " + when {
            timeInSeconds < minute -> "только что"
            timeInSeconds in minute..<hour -> "${timeInSeconds / minute} ${
                declensionOfNumerals(
                    timeInSeconds / minute,
                    cMinute
                )
            } назад"

            timeInSeconds in hour..<day -> "${timeInSeconds / hour} ${
                declensionOfNumerals(
                    timeInSeconds / hour,
                    cHour
                )
            } назад"

            timeInSeconds in day..<2 * day -> "вчера"
            timeInSeconds in 2 * day..<3 * day -> "позавчера"
            else -> "давно"
        }

    // демонстрация работы на рандомайзах
    for (t in intArrayOf(minute, hour, day, 4 * day)) {
        for (i in 1..10) {
            val time: Long = Random.nextInt(0, t).toLong()
            println("${time} -> ${agoToText(time)}")
        }
    }
}

