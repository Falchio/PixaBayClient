package ru.falchio.pixabayclient.json;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

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

}
