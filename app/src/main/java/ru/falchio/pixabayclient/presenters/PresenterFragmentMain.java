package ru.falchio.pixabayclient.presenters;

import ru.falchio.pixabayclient.model.Model;

public class PresenterFragmentMain {
    private final Model model;

    public PresenterFragmentMain() {
        this.model = new Model();
    }

    public Model getModel() {
        return model;
    }
}
