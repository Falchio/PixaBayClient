package ru.falchio.pixabayclient.presenters;


import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

import java.util.List;


import ru.falchio.pixabayclient.json.PixaImageUrl;
import ru.falchio.pixabayclient.model.Model;

public class PresenterFragmentMain {
    private final String TAG = this.getClass().getSimpleName();
    private final Model model;

    public PresenterFragmentMain() {
        this.model = new Model();
    }

    public List<PixaImageUrl> getPixaImageUrl(String wordsForSearch, String imageType) {
        return model.getPixaImages(wordsForSearch, imageType);

    }

    public Observable<List<PixaImageUrl>> getPixaImageUrlRX(String searchWord, String imageType) {
        Observable<List<PixaImageUrl>> observable = Observable.create((ObservableOnSubscribe<List<PixaImageUrl>>) emitter -> {
            try {
                emitter.onNext(model.getPixaImages(searchWord, imageType));
                emitter.onComplete();
            } catch (Exception e) {
                Log.e(TAG, "getPixaImageUrlRX: " + e.toString());
            }
        }).subscribeOn(Schedulers.io());
        return observable;
    }
}
