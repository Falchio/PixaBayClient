package ru.falchio.pixabayclient.data;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.falchio.pixabayclient.FragmentPixaImage;
import ru.falchio.pixabayclient.R;
import ru.falchio.pixabayclient.json.PixaImageUrl;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private final String TAG = this.getClass().getSimpleName();
    private LayoutInflater inflater;
    private List<PixaImageUrl> pixaImages;

    // не забываем создавать свой ViewHolder!
    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;

        ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
        }
    }

    public DataAdapter(Context context, List<PixaImageUrl> pixaImages) {
        this.inflater = LayoutInflater.from(context);
        this.pixaImages = pixaImages;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, final int position) {
        final PixaImageUrl pixaImageUrl = pixaImages.get(position);

        Picasso.get().load(pixaImageUrl.getPreviewUrl()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick:  <-view clicked-> " + position);

                FragmentPixaImage fragmentPixaImage = new FragmentPixaImage(pixaImageUrl.getWebFormatUrl());
                FragmentManager fragmentManager = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                int frame = ((AppCompatActivity) v.getContext()).findViewById(R.id.main_fragment_container).getId();
                transaction.replace(
                        frame,
                        fragmentPixaImage);
                transaction.addToBackStack("");

                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pixaImages.size();
    }


}
