package aaa.bbb.registration.retrofit


import okhttp3.RequestBody
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface PatientApi {

    @POST("/api/v1/register/user")
    suspend fun createUser(@Body requestBody: RequestBody): Response<ResponseBody>

    @POST("/api/v1/codes/generate/parent")
    suspend fun createParent(): Response<ResponseBody>

    @POST("/api/v1/users/login")
    suspend fun postUser(@Body requestBody: RequestBody): Response<ResponseBody>

    companion object Factory {
        fun create(): PatientApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://83.222.11.163/")
                .build()

            return retrofit.create(PatientApi::class.java);
        }
    }
}