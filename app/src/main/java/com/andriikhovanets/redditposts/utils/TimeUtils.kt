package com.andriikhovanets.redditposts.utils

import org.joda.time.DateTime
import java.util.concurrent.TimeUnit


object TimeUtils {

    private const val INC_DURATION = 1L

    private const val MS_IN_SEC = 1000L

    fun getFormattedDate(timestamp: Long) =
        DateTime(timestamp * MS_IN_SEC).getHoursDiffWithNow().toString()

    private fun DateTime.getHoursDiffWithNow() =
        DateTime.now().minus(millis).millis / TimeUnit.HOURS.toMillis(INC_DURATION)

}