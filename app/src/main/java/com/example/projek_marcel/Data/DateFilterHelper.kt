package com.example.projek_marcel.Data

import java.util.Calendar

object DateFilterHelper {
    fun getStartTimeByFilter(filterType: String): Long {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        when (filterType) {
            "1_MINGGU" -> calendar.add(Calendar.DAY_OF_YEAR, -7)
            "1_BULAN"  -> calendar.add(Calendar.MONTH, -1)
            "3_BULAN"  -> calendar.add(Calendar.MONTH, -3)
            "6_BULAN"  -> calendar.add(Calendar.MONTH, -6)
            "1_TAHUN"  -> calendar.add(Calendar.YEAR, -1)
        }
        return calendar.timeInMillis
    }
}