package ru.falchio.pixabayclient.data;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.falchio.pixabayclient.R;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<PixaImage> pixaImages;

        // не забываем создавать свой ViewHolder!
    public class ViewHolder extends RecyclerView.ViewHolder{
        final ImageView imageView;

        ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.image);
        }
    }

    public DataAdapter(Context context, List<PixaImage> pixaImages) {
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
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        PixaImage pixaImage = pixaImages.get(position);
        holder.imageView.setImageResource(pixaImage.getImage());
    }

    @Override
    public int getItemCount() {
        return pixaImages.size();
    }


}
