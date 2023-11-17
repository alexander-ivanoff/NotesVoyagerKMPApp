package screens.utils

import kotlinx.datetime.LocalDateTime

interface DateTimeFormatter : (LocalDateTime) -> String

class TimeFormatterImpl() : DateTimeFormatter {

    override fun invoke(dateTime: LocalDateTime): String {
        return dateTime.toString().substringAfter('T')
    }

}
