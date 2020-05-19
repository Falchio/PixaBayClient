package ru.falchio.pixabayclient.presenters;


import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.falchio.pixabayclient.App;
import ru.falchio.pixabayclient.MainFragmentInterface;
import ru.falchio.pixabayclient.model.Model;

@InjectViewState
public class PresenterFragmentMain extends MvpPresenter<MainFragmentInterface> {
    private final String TAG = this.getClass().getSimpleName();
    private final Model model;

    public PresenterFragmentMain() {
        this.model = App.getInstance().getModel();
    }

    public void getPixaImageUrlRX(String wordsForSearch, String imageType){
        wordsForSearch =wordsForSearch.replaceAll(" ", "+");

        Disposable sendToRecyclerView = model.getImageLink2(wordsForSearch, imageType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pixaAnswer -> getViewState().loadRecyclerViewRx(pixaAnswer),
                throwable -> Log.e(TAG, "getPixaImageUrlRX: "));
    }



}
