package id.quizcoba;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("cek_saldo")
    Call<ModelResponse> getSaldo(@Query("id") String id, @Query("passwd") String passwd);

    @POST("buat_account")
    Call<Model> postAccount(@Query("id") String id, @Query("passwd") String passwd);

    @POST("transfer_coin")
    Call<Model> transferCoin(@Query("id") String id, @Query("passwd") String passwd, @Query("id_tujuan") String id_tujuan, @Query("jumlah") String jumlah);

}
