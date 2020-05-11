package ru.falchio.pixabayclient.json;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(indices = {@Index(value = "wordsForSearch")}, tableName = "PixaUrls")
public class PixaImageUrl {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("previewURL")
    private String previewUrl;

    @SerializedName("webformatURL")
    private String webFormatUrl;

    @SerializedName("type")
    private String imageType;

    private String wordsForSearch;

    public PixaImageUrl(String previewUrl, String webFormatUrl) {
        this.previewUrl = previewUrl;
        this.webFormatUrl = webFormatUrl;
    }

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

    public String getWordsForSearch() {
        return wordsForSearch;
    }

    public void setWordsForSearch(String wordsForSearch) {
        this.wordsForSearch = wordsForSearch;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
