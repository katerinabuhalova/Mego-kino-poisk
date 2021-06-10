package com.example.megokinopoisk.data

import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class DataSource(private val listener: FilmLoaderListener) {
    private val filmId : String = "550"
    private val key = "41fed2aa069a1cd0703d15824da33141"

    private  val collection : MutableList<FilmDetailsDTO>? = mutableListOf()

    interface FilmLoaderListener {
        fun onLoaded(filmDetailsDTO: FilmDetailsDTO)
        fun onFailed(throwable: Throwable)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadData() {

        try {
            val uri = URL("https://api.themoviedb.org/3/movie/${filmId}?api_key=${key}")
            val handler = Handler()
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 10000
                    val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val filmDetailsDTO: FilmDetailsDTO = Gson().fromJson(getLines(bufferedReader), FilmDetailsDTO::class.java)
                    handler.post {
                        listener.onLoaded(filmDetailsDTO)
                    }
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                    listener.onFailed(e)
                    //Обработка ошибки
                } finally {
                    urlConnection.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
            listener.onFailed(e)
            //Обработка ошибки
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}