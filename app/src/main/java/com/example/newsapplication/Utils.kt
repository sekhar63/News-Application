package com.example.newsapplication

import com.example.newsapplication.Constants.Companion.INPUT_DATE_FORMAT
import com.example.newsapplication.Constants.Companion.OUTPUT_DATE_FORMAT
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        fun convertTime(publishedTime: String): String {
            val originalFormat: DateFormat =
                SimpleDateFormat(INPUT_DATE_FORMAT, Locale.ENGLISH)
            val targetFormat: DateFormat = SimpleDateFormat(OUTPUT_DATE_FORMAT)
            val date: Date = originalFormat.parse(publishedTime)
            return targetFormat.format(date)
        }
    }

}