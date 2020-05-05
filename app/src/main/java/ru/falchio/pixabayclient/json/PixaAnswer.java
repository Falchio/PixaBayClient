package ru.falchio.pixabayclient.json;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class PixaAnswer {
    @SerializedName("hits")
    private PixaImageUrl[] pixaImageUrls;

    public PixaImageUrl[] getPixaImageUrls() {
        return pixaImageUrls;
    }

    public void setPixaImageUrls(PixaImageUrl[] pixaImageUrls) {
        this.pixaImageUrls = pixaImageUrls;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();

        for (PixaImageUrl pixaImageUrl :this.pixaImageUrls) {
            toString.append(pixaImageUrl.toString());
        }

        return String.valueOf(toString);
    }
}
