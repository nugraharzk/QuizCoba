package id.quizcoba;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("buat_account")
    Call<Model> postAccount(@Query("id") String id, @Query("passwd") String passwd);

}
