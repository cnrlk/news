package com.caneru.news.ui.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateFormatter {

    fun format(dateString: String, pattern: String = Constants.DEFAULT_DATE_PATTERN): String {
        val dateFormatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
        val formattedDate = LocalDateTime.parse(dateString, dateFormatter)
        return DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mma").format(formattedDate) ?: ""
    }

}