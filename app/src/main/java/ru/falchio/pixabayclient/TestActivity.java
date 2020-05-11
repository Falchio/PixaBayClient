package ru.falchio.pixabayclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.falchio.pixabayclient.json.PixaAnswer;
import ru.falchio.pixabayclient.json.PixaRequest;

public class TestActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private PixaRequest request;
    private final String API =  "16246042-74f45b97e0abdd7225fc1506d";
    private final String SEARCH_KEY_WORDS = "yellow+flowers";
    private final String IMAGE_TYPE = "photo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initRetrofit();

        Button button = findViewById(R.id.test_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request.loadImage(API,SEARCH_KEY_WORDS, IMAGE_TYPE,"200").enqueue(new Callback<PixaAnswer>() {
                    @Override
                    public void onResponse(Call<PixaAnswer> call, Response<PixaAnswer> response) {
                        PixaAnswer pixaAnswer = response.body();
                        Log.d(TAG, "onResponse: "+  pixaAnswer.toString());
                    }

                    @Override
                    public void onFailure(Call<PixaAnswer> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.toString());
                    }
                });
            }
        });
    }

    private void initRetrofit(){
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request =retrofit.create(PixaRequest.class);
    }

}
