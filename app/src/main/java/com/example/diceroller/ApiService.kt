
import com.example.diceroller.dataClasses.Tran
import com.example.diceroller.dataClasses.Transaction
import com.example.diceroller.dataClasses.Withdraw
import com.example.diceroller.dataClasses.Customer
import com.example.diceroller.dataClasses.Deposit
import com.example.diceroller.dataClasses.EntityResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("tran/getTran")
    fun getUsers(@Query("id") id: Long): Call<EntityResponse<Tran>>

    @GET("Customer/get")
    fun getCustomer(@Query("id") id: Long): Call<EntityResponse<Customer>>

    @POST("account/withdrawReq")
    fun withdrawReq(@Query("id") id: Long,@Body data: Withdraw): Call<Void>

    @GET("Transaction/get")
    fun getTran(@Query("id") id: Long): Call<EntityResponse<Transaction>>

    @POST("account/deposit")
    fun depositReq(@Query("id") id: Long,@Body data: Deposit): Call<Void>

}
