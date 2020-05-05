package ru.falchio.pixabayclient.model;

import java.util.ArrayList;
import java.util.List;

import ru.falchio.pixabayclient.R;
import ru.falchio.pixabayclient.data.PixaImage;

public class Model {
    private List<PixaImage> pixaImages;

    public Model() {

        List<PixaImage> pixaImages = new ArrayList<>();
        pixaImages.add(new PixaImage(R.drawable.ic_icon_svg));
        pixaImages.add(new PixaImage(R.drawable.ic_camera_alt_black_24dp));
        pixaImages.add(new PixaImage(R.drawable.ic_clear_black_24dp));
        pixaImages.add(new PixaImage(R.drawable.ic_cloud_upload_black_24dp));
        pixaImages.add(new PixaImage(R.drawable.ic_local_airport_black_24dp));

        this.pixaImages = pixaImages;
    }

    public List<PixaImage> getPixaImages() {
        return pixaImages;
    }
}
