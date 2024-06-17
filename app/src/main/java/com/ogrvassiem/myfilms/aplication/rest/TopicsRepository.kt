package com.ogrvassiem.myfilms.aplication.rest

import android.content.Context
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.nio.charset.Charset
import java.util.Locale
import javax.inject.Inject

class TopicsRepository @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun getTopics(): Result<TopicsResponse> {
        val filename = getFilenameForLocale()
        return try {
            val jsonString = loadJsonFromAssets(filename)
            val topicsResponse = Gson().fromJson(jsonString, TopicsResponse::class.java)
            Result.success(topicsResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun getFilenameForLocale(): String {
        val locale = Locale.getDefault()
        return when (locale) {
            Locale("ru", "RU") -> "ru.json"
            Locale("en", "US") -> "en.json"
            else -> "en.json"
        }
    }

    private fun loadJsonFromAssets(filename: String): String {
        return try {
            val inputStream = context.assets.open(filename)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            throw ex
        }
    }
}