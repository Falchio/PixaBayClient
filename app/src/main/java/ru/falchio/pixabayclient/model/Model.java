package ru.falchio.pixabayclient.model;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.List;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import ru.falchio.pixabayclient.App;
import ru.falchio.pixabayclient.database.PixaUrlsDao;
import ru.falchio.pixabayclient.json.PixaAnswer;
import ru.falchio.pixabayclient.json.PixaImageUrl;
import ru.falchio.pixabayclient.json.PixaRequest;


public class Model  extends ViewModel {
    private PixaUrlsDao pixaUrlsDao;
    private final String TAG = this.getClass().getSimpleName();

    private PixaRequest request;
    @SuppressWarnings("SpellCheckingInspection")
    private final String API_KEY = "16246042-74f45b97e0abdd7225fc1506d";
    private final String AMOUNT_RESULTS = "200";
    private Retrofit retrofit;
    private String wordsForSearch;
    private String imageType;


    public Model() {
        Log.e(TAG, "Model: -->Create ");
        initRetrofit();
    }



    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = retrofit.create(PixaRequest.class);
    }


    public Single<List<PixaImageUrl>> getImageLink(String wordsForSearch, String imageType){
        Single<List<PixaImageUrl>> pixaList;
        this.wordsForSearch= wordsForSearch;
        this.imageType = imageType;


        Log.e(TAG, "getImageLink: LOAD ===");


        pixaUrlsDao = App.getInstance().getPixaUrlsDao();
        if (imageType.equals("all")){
            pixaList = pixaUrlsDao.getListPixaImageUrs(wordsForSearch);
        } else {
            pixaList = pixaUrlsDao.getListPixaImageUrs(wordsForSearch, imageType);
        }

        return pixaList.subscribeOn(Schedulers.io()).flatMap(new GetFromPixabayIfNeed());
    }


    private class GetFromPixabayIfNeed implements Function<List<PixaImageUrl>, SingleSource<List<PixaImageUrl>>>{
        @Override
        public SingleSource<List<PixaImageUrl>> apply(List<PixaImageUrl> pixaImageUrls) throws Exception {
            if (pixaImageUrls.size()<20){
                Log.d(TAG, "pixa size<20, get link from ----> pixabay.com");
                Single<List<PixaImageUrl>> observable = request.loadImage(API_KEY,wordsForSearch,imageType,AMOUNT_RESULTS)
                        .map(PixaAnswer::getPixaImageUrlsList)
                        .subscribeOn(Schedulers.io());
                Disposable dis = observable.observeOn(Schedulers.io()).subscribe(pixaImageUrlList -> {
                    for (PixaImageUrl pixa: pixaImageUrlList) {
                        pixa.setWordsForSearch(wordsForSearch);
                        pixaUrlsDao.insertPixaImageUrlsList(pixaImageUrlList);
                    }
                });

                return observable;
            } else {
                Log.d(TAG, "pixa size>20, get link from ---> database");
                return Single.just(pixaImageUrls);
            }
        }
    }

}
