package ru.falchio.pixabayclient.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import ru.falchio.pixabayclient.R;


public class FragmentPixaImage extends Fragment {
    private String image;

    public FragmentPixaImage(String image) {
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

        Picasso.get().load(this.image).into(imageView);

        return view;
    }
}
