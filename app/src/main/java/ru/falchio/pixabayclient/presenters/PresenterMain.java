package ru.falchio.pixabayclient.presenters;

import androidx.fragment.app.Fragment;

import ru.falchio.pixabayclient.FragmentMain;

public class PresenterMain {
    private Fragment currentFragment;

    public PresenterMain() {
        this.currentFragment = new FragmentMain();
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }
}
