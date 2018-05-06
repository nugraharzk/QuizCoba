package id.quizcoba;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // Base URL for Retrofit
    public static final String BASE_URL = "http://mobprog.yuliadi.pro:5000/";
    private static Retrofit retrofit = null;

    // getClient Method
    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
