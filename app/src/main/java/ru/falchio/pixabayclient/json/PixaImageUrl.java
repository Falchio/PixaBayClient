package ru.falchio.pixabayclient.json;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class PixaImageUrl {

    @SerializedName("previewURL")
    private String previewUrl;

    @SerializedName("webformatURL")
    private String webFormatUrl;

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getWebFormatUrl() {
        return webFormatUrl;
    }

    public void setWebFormatUrl(String webFormatUrl) {
        this.webFormatUrl = webFormatUrl;
    }

   
    @NonNull
    @Override
    public String toString() {
        return "\n preview url: " + this.previewUrl + "\n web format view url: " + this.webFormatUrl;
    }
}
