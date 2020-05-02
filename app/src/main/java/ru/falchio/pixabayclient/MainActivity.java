package ru.falchio.pixabayclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import ru.falchio.pixabayclient.presenters.PresenterMain;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private static final FragmentMain FRAGMENT_MAIN = new FragmentMain();
    private static final PresenterMain PRESENTER_MAIN = new PresenterMain();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentMainAdd();
    }

    private void fragmentMainAdd(){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_main, FRAGMENT_MAIN);
            fragmentTransaction.commit();
    }

}
