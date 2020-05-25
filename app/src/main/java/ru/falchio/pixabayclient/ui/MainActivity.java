package ru.falchio.pixabayclient.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import ru.falchio.pixabayclient.R;
import ru.falchio.pixabayclient.adapter.DataAdapter;
import ru.falchio.pixabayclient.json.PixaImageUrl;
import ru.falchio.pixabayclient.presenters.PresenterMain;
import ru.falchio.pixabayclient.ui.views.MainView;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    private final String TAG = this.getClass().getSimpleName();
    private RecyclerView recyclerView;
    private EditText editText;
    private ImageButton load;
    private Spinner spinner;
    private String imageType;


    @InjectPresenter
    PresenterMain presenterFragMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {setSupportActionBar(toolbar);}

        recyclerView = findViewById(R.id.list);
        load = findViewById(R.id.load_pixa_image);
        load.setOnClickListener(v -> loadUrlImage());
        editText =findViewById(R.id.search_text);
        spinner = findViewById(R.id.image_types);
        initSpinner(spinner);
    }


    private void loadUrlImage(){
        String searchWord = editText.getText().toString();
        if (searchWord.isEmpty()||searchWord.equals(" ")){
            Toast.makeText(this, getString(R.string.please_enter_word),Toast.LENGTH_SHORT).show();
            return;
        }
        Log.e(TAG, "loadUrlImage:  LOAD ---");
        presenterFragMain.getPixaImageUrlRX(searchWord, imageType);
    }



    public void loadRecyclerViewRx(List<PixaImageUrl> pixaImageUrlList){
        Log.e(TAG, "loadRecyclerViewRx: LOAD ---");
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        // создаем адаптер
        DataAdapter adapter = new DataAdapter(this, pixaImageUrlList);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }



    private void initSpinner(Spinner spinner) {
        ArrayAdapter adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        getResources().getStringArray(R.array.image_types));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt(getString(R.string.image_type));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageType = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                imageType=(String) parent.getItemAtPosition(0);
            }
        });
    }
}
