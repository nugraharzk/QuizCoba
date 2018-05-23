package id.quizcoba;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText idGet, passGet, id, passwd, idDonor, passDonor, idRecipient, jumlah;
    private Button btnGet, btnOk, btnTrans;
    private TextView saldo, akun, trans;
    private String strGetId, strGetPass, strId, strPass, strIdDonor, strPassDonor, strIdRecipient, strJumlah;
    private Context context;
    private ModelResponse modelResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        idGet = findViewById(R.id.getid);
        passGet = findViewById(R.id.getpass);
        btnGet = findViewById(R.id.btnget);
        saldo = findViewById(R.id.result);

        id = findViewById(R.id.id);
        passwd = findViewById(R.id.passwd);
        btnOk = findViewById(R.id.go);
        akun = findViewById(R.id.responakun);

        idDonor = findViewById(R.id.iddonor);
        passDonor = findViewById(R.id.passdonor);
        idRecipient = findViewById(R.id.idrecipient);
        jumlah = findViewById(R.id.jumlah);
        btnTrans = findViewById(R.id.btnTrans);
        trans = findViewById(R.id.respontrans);

        final ApiInterface apiClient = ApiClient.getClient().create(ApiInterface.class);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strGetId = idGet.getText().toString();
                strGetPass = passGet.getText().toString();
                Call<ModelResponse> call = apiClient.getSaldo(strGetId, strGetPass);
                call.enqueue(new Callback<ModelResponse>() {
                    @Override
                    public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                        modelResponse = response.body();
                        saldo.setText("Saldo Anda adalah : " + modelResponse.getSaldo());
                    }

                    @Override
                    public void onFailure(Call<ModelResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t);
                        Toast.makeText(context, "Isinya : " + t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strId = id.getText().toString();
                strPass = passwd.getText().toString();
                Call<ModelResponse> call = apiClient.postAccount(strId,strPass);
                call.enqueue(new Callback<ModelResponse>() {
                    @Override
                    public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                        modelResponse = response.body();
                        akun.setText(modelResponse.getRespon());
                        Log.d(TAG, "onResponse: " + response.body());
                        Toast.makeText(context, "Isinya : " + response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ModelResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t);
                        Toast.makeText(context, "Isinya : " + t, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        btnTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strIdDonor = idDonor.getText().toString();
                strPassDonor = passDonor.getText().toString();
                strIdRecipient = idRecipient.getText().toString();
                strJumlah = jumlah.getText().toString();

                Call<ModelResponse> call = apiClient.transferCoin(strIdDonor, strPassDonor, strIdRecipient, strJumlah);
                call.enqueue(new Callback<ModelResponse>() {
                    @Override
                    public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                        modelResponse = response.body();
                        trans.setText(modelResponse.getRespon());
                        Log.d(TAG, "onResponse: " + response.body());
                        Toast.makeText(context, "Isinya : " + response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ModelResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t);
                        Toast.makeText(context, "Isinya : " + t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
