package ru.falchio.pixabayclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.falchio.pixabayclient.data.DataAdapter;
import ru.falchio.pixabayclient.data.PixaImage;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private List<PixaImage> pixaImages = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));
        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));
        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));
        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));
        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));
        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));
        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));
        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));
        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));
        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));
        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));
        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));
        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));
        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));
        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));
        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));

        RecyclerView recyclerView = findViewById(R.id.list);
        //грид менеджер для размещения RecyclerView в 2 столбца
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        // создаем адаптер
        DataAdapter adapter = new DataAdapter(this, pixaImages);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

    }

}
