package aaa.bbb.registration.retrofit


import okhttp3.RequestBody
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.http.*

interface PatientApi {
    @POST("/api/v1/register/patient")
    suspend fun createPatient(@Body requestBody: RequestBody): Response<ResponseBody>

    @POST("/api/v1/register/parent")
    suspend fun createParent(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("/api/v1/users/current")
    suspend fun getUser(@Path("credentials") credUser: String): Response<ResponseBody>
}