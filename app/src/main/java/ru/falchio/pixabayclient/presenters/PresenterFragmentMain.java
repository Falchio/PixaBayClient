package ru.falchio.pixabayclient.presenters;


import androidx.lifecycle.MutableLiveData;

import java.util.List;


import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.falchio.pixabayclient.App;
import ru.falchio.pixabayclient.MainFragmentInterface;
import ru.falchio.pixabayclient.json.PixaImageUrl;
import ru.falchio.pixabayclient.model.Model;

@InjectViewState
public class PresenterFragmentMain extends MvpPresenter<MainFragmentInterface> {
    private final String TAG = this.getClass().getSimpleName();
    private final Model model;

    public PresenterFragmentMain() {
        this.model = App.getInstance().getModel();
    }

    public void getPixaImageUrlRX(String searchWord, String imageType){
        model.getPixaImages(searchWord, imageType);
    }


    public MutableLiveData<List<PixaImageUrl>> getListMutableLiveData() {
//        getViewState().loadRecyclerViewRx();
        return model.getListMutableLiveData();
    }
}
