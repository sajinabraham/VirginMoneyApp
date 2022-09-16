package com.sajin.virginmoneyapp.domain.model

import com.google.gson.annotations.SerializedName
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class RoomsDataModel(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("isOccupied")
    var isOccupied: Boolean,
    @SerializedName("maxOccupancy")
    val maxOccupancy: Int
) {
    fun getDateTimeObj(): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")
        return try {
            format.parse(createdAt)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }

    }
}