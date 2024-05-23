
import com.example.diceroller.Customer
import com.example.diceroller.Deposit
import com.example.diceroller.Entity
import com.example.diceroller.EntityResponse
import com.example.diceroller.Transaction
import com.example.diceroller.Withdraw
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("tran/getTran")
    fun getUsers(@Query("id") id: Long): Call<EntityResponse<Entity>>

    @GET("Customer/get")
    fun getCustomer(@Query("id") id: Long): Call<EntityResponse<Customer>>

    @POST("account/withdrawReq")
    fun withdrawReq(@Query("id") id: Long,@Body data: Withdraw): Call<Void>

    @GET("Transaction/get")
    fun getTran(@Query("id") id: Long): Call<EntityResponse<Transaction>>

    @POST("account/deposit")
    fun depositReq(@Query("id") id: Long,@Body data: Deposit): Call<Void>

}
