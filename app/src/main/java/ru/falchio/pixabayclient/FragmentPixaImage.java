package ru.falchio.pixabayclient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;


public class FragmentPixaImage extends Fragment {
    private int image;

    public FragmentPixaImage(int image) {
        this.image = image;
    }

    public FragmentPixaImage() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //в начале создаём view затем получаем ссылку на RecyclerView
        View view = inflater.inflate(R.layout.fragment_pixa_image, container, false);
        ImageView imageView = Objects.requireNonNull(view).findViewById(R.id.frag_pixa_image_view);
        imageView.setImageResource(this.image);
        return view;
    }
}
