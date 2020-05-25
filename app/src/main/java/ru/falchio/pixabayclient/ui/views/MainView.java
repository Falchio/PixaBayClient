package ru.falchio.pixabayclient.ui.views;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.StateStrategyType;
import ru.falchio.pixabayclient.json.PixaImageUrl;

public interface MainView extends MvpView {
    @StateStrategyType(value = AddToEndStrategy.class)
    void loadRecyclerViewRx(List<PixaImageUrl> pixaImageUrlList);
}
