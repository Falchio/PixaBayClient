package ru.falchio.pixabayclient;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.StateStrategyType;
import ru.falchio.pixabayclient.json.PixaImageUrl;

public interface MainFragmentInterface extends MvpView {
    @StateStrategyType(value = AddToEndStrategy.class)
    void loadRecyclerViewRx(List<PixaImageUrl> pixaImageUrlList);
}
