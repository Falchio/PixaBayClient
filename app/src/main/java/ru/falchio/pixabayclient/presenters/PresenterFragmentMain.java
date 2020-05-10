package ru.falchio.pixabayclient.presenters;

import java.util.List;

import ru.falchio.pixabayclient.json.PixaImageUrl;
import ru.falchio.pixabayclient.model.Model;

public class PresenterFragmentMain {
    private final Model model;

    public PresenterFragmentMain() {
        this.model = new Model();
    }

    public List<PixaImageUrl> getPixaImageUrl(String wordsForSearch, String imageType){
        return model.getPixaImages(wordsForSearch,imageType);

    }

}
