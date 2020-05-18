package ru.falchio.pixabayclient.json;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class PixaAnswer {
    @SerializedName("hits")
    private PixaImageUrl[] pixaImageUrls;

    public PixaImageUrl[] getPixaImageUrls() {
        return pixaImageUrls;
    }

    @SuppressWarnings("unused")
    public void setPixaImageUrls(PixaImageUrl[] pixaImageUrls) {
        this.pixaImageUrls = pixaImageUrls;
    }

    public List<PixaImageUrl> getPixaImageUrlsList(){
        return Arrays.asList(pixaImageUrls);
    }

}
