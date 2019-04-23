package app;

import com.fasterxml.jackson.annotation.JsonProperty;
import coverart.CoverArtConsumer;
import coverart.CoverArtContent;
import coverart.CoverArtImage;

public class Album {
    private String title;
    private String mbid;
    private CoverArtContent content;

    public Album() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @JsonProperty(value = "id")
    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getMbid() {
        return mbid;
    }

    public void addCoverArt() {
        content = CoverArtConsumer.createFromMbid(mbid);
    }


    public CoverArtImage[] getImages() {
        if (content == null) {
            return null;
        }
        return content.getImages();
    }
}
