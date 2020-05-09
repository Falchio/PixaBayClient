package ru.falchio.pixabayclient;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import ru.falchio.pixabayclient.data.DataAdapter;
import ru.falchio.pixabayclient.json.PixaImageUrl;
import ru.falchio.pixabayclient.presenters.PresenterFragmentMain;



public class FragmentMain extends Fragment {
    private PresenterFragmentMain presenterFragMain;
    private RecyclerView recyclerView;
    private Button load;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        presenterFragMain = new PresenterFragmentMain();

        //в начале создаём view затем получаем ссылку на RecyclerView
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //ссылку на RecyclerView получаем из view, созданной ранее
        recyclerView = Objects.requireNonNull(view).findViewById(R.id.list);
        load = Objects.requireNonNull(view).findViewById(R.id.load_pixa_image);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRecyclerView();
            }
        });

        return view;
    }

    private void loadRecyclerView(){
        List<PixaImageUrl> pixaImages = presenterFragMain.getModel().getPixaImages();

        if (pixaImages!=null){
            //грид менеджер для размещения RecyclerView в 2 столбца
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

            // создаем адаптер
            DataAdapter adapter = new DataAdapter(getContext(), pixaImages);

            // устанавливаем для списка адаптер
            recyclerView.setAdapter(adapter);
        }

    }

    public PresenterFragmentMain getPresenterFragMain() {
        return presenterFragMain;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
