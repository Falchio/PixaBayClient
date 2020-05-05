package ru.falchio.pixabayclient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import ru.falchio.pixabayclient.data.DataAdapter;
import ru.falchio.pixabayclient.data.PixaImage;
import ru.falchio.pixabayclient.presenters.PresenterFragmentMain;


public class FragmentMain extends Fragment {
    private PresenterFragmentMain presenterFragMain;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        presenterFragMain = new PresenterFragmentMain();

        //в начале создаём view затем получаем ссылку на RecyclerView
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        List<PixaImage> pixaImages = presenterFragMain.getModel().getPixaImages();

        //ссылку на RecyclerView получаем из view, созданной ранее
        RecyclerView recyclerView = Objects.requireNonNull(view).findViewById(R.id.list);

        //грид менеджер для размещения RecyclerView в 2 столбца
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // создаем адаптер
        DataAdapter adapter = new DataAdapter(getContext(), pixaImages);

        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

        return view;
    }
}
