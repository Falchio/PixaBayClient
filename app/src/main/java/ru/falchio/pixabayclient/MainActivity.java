package ru.falchio.pixabayclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


import ru.falchio.pixabayclient.presenters.PresenterMain;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private static FragmentMain FRAGMENT_MAIN = new FragmentMain();
    private static final PresenterMain PRESENTER_MAIN = new PresenterMain();
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentMainAdd(FRAGMENT_MAIN);
    }

    private void fragmentMainAdd(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_container, fragment);
        fragmentTransaction.commit();
    }

}
