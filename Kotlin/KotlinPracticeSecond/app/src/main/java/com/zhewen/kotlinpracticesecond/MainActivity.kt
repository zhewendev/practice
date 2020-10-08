package com.zhewen.kotlinpracticesecond

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

interface Api {
    @GET("/repos/Jetbrains/kotlin")
    fun getRepository(): Call<Repository>
}
fun main() {
    val request = Retrofit.Builder().baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    val response = request.getRepository().execute()

    val repository = response.body()

    if(repository == null){
        println("Request Error! ErrorCode = ${response.code()},ErrorMessage = ${response.message()}")
    } else {
        File("Kotlin.html").writeText("""
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <title>${repository.owner.login} - ${repository.name}</title>
            </head>
            <body>
                <h1><a href='https://github.com/JetBrains/kotlin'>JetBrains - kotlin</a></h1>
                <p>Stars: ${repository.stargazers_count}</p>
                <p>Forks: ${repository.forks_count}</p>
            </body>
            </html>
        """.trimIndent())
    }
}