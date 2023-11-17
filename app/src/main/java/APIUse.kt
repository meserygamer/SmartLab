import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.io.Serializable

data class APINewsItem(
    val description: String?,
    val id: Int,
    val image: String?,
    val name: String?,
    val price: Int
)

data class CatalogItem(
    val bio: String?,
    val category: String?,
    val description: String?,
    val id: Int,
    val name: String?,
    val preparation: String?,
    val price: Int,
    val timeResul: String?
) : Serializable

interface APIIterface
{
    @POST("api/SendCode")
    fun SendCodeOnEmail(@Header("User-email") Users_email : String)
    : Call<String>

    @POST("api/SignIn")
    fun SendEnterCodeOnServer(@Header("User-email") Users_email : String
                              , @Header("User-code") User_code : String)
    : Call<String>

    @GET("api/News")
    fun GetAllNewsForUser()
    : Call<MutableList<APINewsItem>>

    @GET("api/Catalog")
    fun GetAllCatalogItems()
    : Call<MutableList<CatalogItem>>
}

object RetrofitClient {

    private var retrofit: Retrofit? = null

    fun GetClient(BaseURL: String) : Retrofit
    {
        if(retrofit == null)
        {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            retrofit = Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        }
        return retrofit!!
    }
}

object Common{

    private val Base_URL = "https://iis.ngknn.ru/NGKNN/МамшеваЮС/MedicMadlab/"
    public var User_mail : String = ""
    public var Bearer : String = ""
    val retrofitService : APIIterface
        get() = RetrofitClient.GetClient(Base_URL).create(APIIterface::class.java)

}