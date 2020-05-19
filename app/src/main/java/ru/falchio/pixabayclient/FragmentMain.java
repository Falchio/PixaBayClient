package ru.falchio.pixabayclient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import ru.falchio.pixabayclient.adapter.DataAdapter;
import ru.falchio.pixabayclient.json.PixaImageUrl;
import ru.falchio.pixabayclient.presenters.PresenterFragmentMain;


public class FragmentMain extends MvpAppCompatFragment implements MainFragmentInterface {
    private final String TAG = this.getClass().getSimpleName();

    @InjectPresenter
    PresenterFragmentMain presenterFragMain;

    private RecyclerView recyclerView;
    private Button load;
    private Spinner spinner;
    private String imageType;
    private EditText editText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        presenterFragMain = new PresenterFragmentMain();  под Moxy пока выключу
        //в начале создаём view затем получаем ссылку на RecyclerView
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //ссылку на RecyclerView получаем из view, созданной ранее
        recyclerView = Objects.requireNonNull(view).findViewById(R.id.list);
        load = Objects.requireNonNull(view).findViewById(R.id.load_pixa_image);
        load.setOnClickListener(v -> loadUrlImage());
        editText =Objects.requireNonNull(view).findViewById(R.id.search_text);
        spinner = Objects.requireNonNull(view).findViewById(R.id.image_types);
        initSpinner(spinner);
      return view;
    }

    private void loadUrlImage(){
        String searchWord = editText.getText().toString();
        if (searchWord.isEmpty()||searchWord.equals(" ")){
            Toast.makeText(getContext(), getString(R.string.please_enter_word),Toast.LENGTH_SHORT).show();
            return;
        }
        presenterFragMain.getPixaImageUrlRX(searchWord, imageType);

    }



    public void loadRecyclerViewRx(List<PixaImageUrl> pixaImageUrlList){
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        // создаем адаптер
        DataAdapter adapter = new DataAdapter(getContext(), pixaImageUrlList);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }



    private void initSpinner(Spinner spinner) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        Objects.requireNonNull(getContext()),
                        android.R.layout.simple_spinner_dropdown_item,
                        Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.image_types)
                );

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
