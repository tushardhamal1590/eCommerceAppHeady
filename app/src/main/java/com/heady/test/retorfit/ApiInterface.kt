package kotlincodes.com.retrofitwithkotlin.retrofit

import com.heady.test.model.FinalResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("json")
    fun getFinalResponse(): Call<FinalResponse>

}