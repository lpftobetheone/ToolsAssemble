package com.lpf.tools.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lpf.tools.R;
import com.lpf.tools.utils.SnackbarUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit数据请求Demo
 */
public class RetrofitActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://m.lenovo.com.cn";
    private static final String API_KEY = "";

    @InjectView(R.id.id_phone_number)
    EditText idPhoneNumber;
    @InjectView(R.id.id_phone_query)
    Button idPhoneQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.inject(this);

        initListeners();
    }

    private void initListeners() {
        idPhoneQuery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                queryData();
            }
        });
    }

    private void queryData() {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
        PhoneService phoneService = retrofit.create(PhoneService.class);
        Call<PhoneResult> phoneResultCall = phoneService.getResult("3");
        phoneResultCall.enqueue(new Callback<PhoneResult>(){
            @Override
            public void onResponse(Call<PhoneResult> call, Response<PhoneResult> response) {
                if(response.isSuccessful()){
                    PhoneResult phoneResult = response.body();
                    if(phoneResult!=null){
                        String totalPage = phoneResult.getData().get(1).getCelltype();
                        Toast.makeText(RetrofitActivity.this, ""+totalPage, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<PhoneResult> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
