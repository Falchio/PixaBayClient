package ru.falchio.pixabayclient.presenters;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;


import ru.falchio.pixabayclient.json.PixaImageUrl;
import ru.falchio.pixabayclient.model.Model;

public class PresenterFragmentMain {
    private final String TAG = this.getClass().getSimpleName();
    private final Model model;

    public PresenterFragmentMain() {
        this.model = new Model();
    }

//    public List<PixaImageUrl> getPixaImageUrl(String wordsForSearch, String imageType) {
//        return model.getPixaImages(wordsForSearch, imageType);
//
//    }

//    public Observable<List<PixaImageUrl>> getPixaImageUrlRX(String searchWord, String imageType) {
//        model.getPixaImages(searchWord, imageType);
//        Observable<List<PixaImageUrl>> observable = Observable.create((ObservableOnSubscribe<List<PixaImageUrl>>) emitter -> {
//            try {
//                emitter.onNext(model.getPixaImages());
//                emitter.onComplete();
//            } catch (Exception e) {
//                Log.e(TAG, "getPixaImageUrlRX: " + e.toString());
//            }
//        }).subscribeOn(Schedulers.io());
//        return observable;
//    }

    public void getPixaImageUrlRX(String searchWord, String imageType){
        model.getPixaImages(searchWord, imageType);
    }



    public MutableLiveData<List<PixaImageUrl>> getListMutableLiveData() {
        return model.getListMutableLiveData();
    }
}
