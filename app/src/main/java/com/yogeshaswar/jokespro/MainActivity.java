package com.yogeshaswar.jokespro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
TextView setUp, punchLine;
Button btnLoad;
private static String BASE_URL = "https://official-joke-api.appspot.com";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //base url -> https://official-joke-api.appspot.com/   end point -> random_joke

        initiateUI();

        //btn click
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                punchLine.setText("");
                setUp.setText("");
                callAPI();
                btnLoad.setText("Load Next");
            }
        });

    }

    private void callAPI() {
        //Retrofit Builder/Client/Instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //retrofit instance
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Call<JokeModel> call = apiInterface.getJoke();

        call.enqueue(new Callback<JokeModel>() {
            @Override
            public void onResponse(Call<JokeModel> call, Response<JokeModel> response) {
                if(response.code() != 200) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                setUp.setText(response.body().getSetup());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        punchLine.setText(response.body().getPunchline());
                    }
                }, 2000);
            }

            @Override
            public void onFailure(Call<JokeModel> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage() );
            }
        });
    }

    private void initiateUI() {
        setUp = (TextView) findViewById(R.id.txt_joke);
        punchLine = findViewById(R.id.txt_punchline);
        btnLoad = findViewById(R.id.btn_load_joke);
    }
}