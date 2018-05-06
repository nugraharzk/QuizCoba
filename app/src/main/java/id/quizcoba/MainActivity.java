package id.quizcoba;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText id, passwd;
    private Button btnOk;
    private String strId, strPass;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        id = findViewById(R.id.id);
        passwd = findViewById(R.id.passwd);
        btnOk = findViewById(R.id.go);

        final ApiInterface apiClient = ApiClient.getClient().create(ApiInterface.class);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strId = id.getText().toString();
                strPass = passwd.getText().toString();
                Call<Model> call = apiClient.postAccount(strId,strPass);
                call.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Log.d(TAG, "onResponse: " + response.body());
                        Toast.makeText(context, "Isinya : " + response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t);
                        Toast.makeText(context, "Isinya : " + t, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
