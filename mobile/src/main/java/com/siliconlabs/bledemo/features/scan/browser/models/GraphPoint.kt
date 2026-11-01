package com.siliconlabs.bledemo.features.scan.browser.models

import kotlin.math.round

data class GraphPoint(
        val rssi: Int,
        val timestamp: Long
) {
    fun convertTimestampToSeconds(currentTime: Long) = roundDecimalPlaces((timestamp - currentTime) / 1000000000f)

    private fun roundDecimalPlaces(timestamp: Float) = round(timestamp * 1000.0f) / 1000.0f
}