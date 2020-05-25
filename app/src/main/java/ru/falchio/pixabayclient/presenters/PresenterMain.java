package ru.falchio.pixabayclient.presenters;


import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.falchio.pixabayclient.App;
import ru.falchio.pixabayclient.ui.views.MainView;
import ru.falchio.pixabayclient.model.Model;

@InjectViewState
public class PresenterMain extends MvpPresenter<MainView> {
    private final String TAG = this.getClass().getSimpleName();
    private final Model model;

    public PresenterMain() {
        Log.e(TAG, "PresenterMain: --> Create");
        this.model = App.getInstance().getModel();
    }


    public void getPixaImageUrlRX(String wordsForSearch, String imageType){
        wordsForSearch =wordsForSearch.replaceAll(" ", "+");
        Log.e(TAG, "getPixaImageUrlRX: LOAD ---");
        Disposable sendToRecyclerView = model.getImageLink(wordsForSearch, imageType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pixaAnswer -> getViewState().loadRecyclerViewRx(pixaAnswer),
                throwable -> Log.e(TAG, "getPixaImageUrlRX: "));
    }
}
