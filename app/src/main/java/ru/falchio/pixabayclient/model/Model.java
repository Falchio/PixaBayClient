package ru.falchio.pixabayclient.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.falchio.pixabayclient.App;
import ru.falchio.pixabayclient.json.PixaAnswer;
import ru.falchio.pixabayclient.json.PixaImageUrl;
import ru.falchio.pixabayclient.json.PixaRequest;

public class Model extends ViewModel {
    private MutableLiveData<List<PixaImageUrl>> listMutableLiveData;
    private List<PixaImageUrl> pixaImages;
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
        wordsForSearch.replaceAll(" ", "+");

        Thread t1 = new Thread(() -> {
            if (imageType.equals("all")){
                pixaImages = App.getInstance().getPixaUrlsDao().getListPixaImageUrs(wordsForSearch);
            } else{
                pixaImages = App.getInstance().getPixaUrlsDao().getListPixaImageUrs(wordsForSearch, imageType);
            }

                Log.d(TAG, "in new Thread: " + pixaImages.size() + " " + Thread.currentThread().getName());
            if (pixaImages.size()<5){
                Log.d(TAG, "getPixaImages: pixaImages from database < 5 items, then load link from pixabay");
                getLinkImageFromPixabay(wordsForSearch, imageType);
            } else{
                Log.d(TAG, "getPixaImages: pixaImages from database > 5 items, then get link from database");
                listMutableLiveData.postValue(pixaImages);
            }
        });
        t1.start();
    }

    private void getLinkImageFromPixabay(String wordsForSearch, String imageType) {
        request.loadImage(API, wordsForSearch, imageType, AMOUNT_RESULTS).enqueue(new Callback<PixaAnswer>() {
            @Override
            public void onResponse(Call<PixaAnswer> call, Response<PixaAnswer> response) {
                pixaAnswer = response.body();

                assert pixaAnswer != null;
                pixaImages = Arrays.asList(pixaAnswer.getPixaImageUrls());
                for (PixaImageUrl pixa:pixaImages) {
                    pixa.setWordsForSearch(wordsForSearch);
                }
                listMutableLiveData.setValue(pixaImages);

                Observable<List<PixaImageUrl>> observable
                        = Observable.just(Objects.requireNonNull(listMutableLiveData.getValue()));
                observable.subscribeOn(Schedulers.io())
                        .subscribe(pixaImageUrlList ->
                                App.getInstance().getPixaUrlsDao().insertPixaImageUrlsList(pixaImageUrlList));

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
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<>();
        }
        return listMutableLiveData;
    }
}
