fun main() {

    // блок объявления констант
    val minute = 60
    val hour = 60 * 60
    val day = 24 * 60 * 60

    /**
     * функция, возвращаем шаблонную фразу вида:
     *
     *     был(а) только что
     *     был(а) в сети 3 часа назад
     *
     *
     */
    fun agoToText(timeInSeconds: Long) =
        "был(а) " + when {
            timeInSeconds < minute -> "только что"
            timeInSeconds in minute..<hour -> "${timeInSeconds % minute} минут назад"
            timeInSeconds in hour..<day -> "${timeInSeconds % hour} часов назад"
            timeInSeconds in day..<2 * day -> "вчера"
            timeInSeconds in 2 * day..<3 * day -> "позавчера"
            else -> "давно"
        }
}

