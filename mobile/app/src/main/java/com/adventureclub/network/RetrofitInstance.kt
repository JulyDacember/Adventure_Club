object RetrofitInstance {
    private const val BASE_URL = "http://your-server-ip:5000/"

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: AdventureClubApi by lazy {
        retrofit.create(AdventureClubApi::class.java)
    }
}