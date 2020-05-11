package ru.falchio.pixabayclient.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.falchio.pixabayclient.App;
import ru.falchio.pixabayclient.database.PixaUrlsDao;
import ru.falchio.pixabayclient.json.PixaAnswer;
import ru.falchio.pixabayclient.json.PixaImageUrl;
import ru.falchio.pixabayclient.json.PixaRequest;

public class Model extends ViewModel {
    private MutableLiveData<List<PixaImageUrl>> listMutableLiveData;
//    private List<PixaImageUrl> pixaImages;
    private final String TAG = this.getClass().getSimpleName();
    private final String AMOUNT_RESULTS = "200";
    private PixaRequest request;
    private PixaAnswer pixaAnswer;
    @SuppressWarnings("SpellCheckingInspection")
    private final String API = "16246042-74f45b97e0abdd7225fc1506d";
    private Retrofit retrofit;


    public Model() {
        initRetrofit();
    }

    public void getPixaImages(String wordsForSearch, String imageType) {
        wordsForSearch.replaceAll(" ","+");
        request.loadImage(API, wordsForSearch, imageType, AMOUNT_RESULTS).enqueue(new Callback<PixaAnswer>() {
            @Override
            public void onResponse(Call<PixaAnswer> call, Response<PixaAnswer> response) {
                pixaAnswer = response.body();
                assert pixaAnswer != null;
                listMutableLiveData.setValue(Arrays.asList(pixaAnswer.getPixaImageUrls()));

                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        PixaUrlsDao dao = App.getInstance().getPixaUrlsDao();
                        int i = 0;
                        for (PixaImageUrl pixa:pixaAnswer.getPixaImageUrls()) {
                            pixa.setWordsForSearch(wordsForSearch);
                            dao.insertPixaImageUrl(pixa);
                            Log.d(TAG, "onResponse: " + pixa.getWordsForSearch() + " " + i);
                            i++;
                        }
                    }
                };
                thread.start();
            }

            @Override
            public void onFailure(Call<PixaAnswer> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    private void initRetrofit() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = retrofit.create(PixaRequest.class);
    }

    public MutableLiveData<List<PixaImageUrl>> getListMutableLiveData() {
        if (listMutableLiveData==null) {
            listMutableLiveData = new MutableLiveData<>();
        }
        return listMutableLiveData;
    }
}
