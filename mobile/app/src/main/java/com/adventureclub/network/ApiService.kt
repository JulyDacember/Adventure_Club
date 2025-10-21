interface AdventureClubApi {
    @GET("api/routes")
    suspend fun getRoutes(): Response<List<Route>>

    @POST("api/routes")
    suspend fun createRoute(@Body route: Route): Response<Route>

    @DELETE("api/routes/{id}")
    suspend fun deleteRoute(@Path("id") id: Int): Response<Unit>

    @GET("api/instructors")
    suspend fun getInstructors(): Response<List<Instructor>>

    @POST("api/instructors")
    suspend fun createInstructor(@Body instructor: Instructor): Response<Instructor>

    @DELETE("api/instructors/{id}")
    suspend fun deleteInstructor(@Path("id") id: Int): Response<Unit>
}