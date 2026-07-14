// ─────────────────────────────────────────────
// data/remote/RetrofitClient.kt
// ─────────────────────────────────────────────
package com.example.albumchampions.data.remote

import com.google.firebase.appdistribution.gradle.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // ── Configuração ──────────────────────────────────────────────────────────

    private const val BASE_URL = "https://cnpvzcsdedlksbbuqyti.supabase.co/rest/v1/"

    // Chave pública (anon key):
    // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNucHZ6Y3NkZWRsa3NiYnVxeXRpIiwicm9sZSI6ImFub24iLCJpYXQiOjE3ODM5NjQzNDEsImV4cCI6MjA5OTU0MDM0MX0.2UPq6JSg-0Uds0DTta4_cQZQjA5Hrq1ol2AqEx8NHcs
    private const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNucHZ6Y3NkZWRsa3NiYnVxeXRpIiwicm9sZSI6ImFub24iLCJpYXQiOjE3ODM5NjQzNDEsImV4cCI6MjA5OTU0MDM0MX0.2UPq6JSg-0Uds0DTta4_cQZQjA5Hrq1ol2AqEx8NHcs"

    // ── Interceptor de headers ────────────────────────────────────────────────

    // O Supabase exige esses dois headers em toda requisição.
    // O Interceptor os adiciona automaticamente, sem precisar
    // colocar em cada função do ApiService.
    private val supabaseInterceptor = Interceptor { chain ->
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("apikey", SUPABASE_KEY)
            .addHeader("Authorization", "Bearer $SUPABASE_KEY")
            // Diz ao Supabase para retornar o JSON como lista,
            // mesmo quando o resultado for um único objeto.
            .addHeader("Accept", "application/json")
            .build()
        chain.proceed(request)
    }

    // ── OkHttp (cliente HTTP) ─────────────────────────────────────────────────

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(supabaseInterceptor)
        .build()

    // ── Retrofit ──────────────────────────────────────────────────────────────

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // ── Instância pública do ApiService ──────────────────────────────────────

    // Os Repositories chamam RetrofitClient.apiService.getTeams(), etc.
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}