package app;

import musicbrainz.MusicBrainzContentFactory;
import musicbrainz.MusicBrainzContent;
import wikipedia.WikipediaContentFactory;
import wikipedia.WikipediaContent;

import java.net.URISyntaxException;
import java.util.NoSuchElementException;

public class Artist {
    private final String mbid;
    private final MusicBrainzContent musicBrainzContent;
    private final WikipediaContent wikipediaContent;

    public Artist(String mbid) {
        this.mbid = mbid;
        musicBrainzContent = createMusicBrainzContent(mbid);
        musicBrainzContent.addCoverArtToAlbums();
        wikipediaContent = createWikipediaContent();
    }

    public String getMbid() {
        return mbid;
    }

    public String getName() {
        return musicBrainzContent.getName();
    }

    public Album[] getAlbums() {
        return musicBrainzContent.getAlbums();
    }

    public String getDescription() {
        if (wikipediaContent == null) {
            return "";
        }
        return wikipediaContent.getExtract();
    }

    private MusicBrainzContent createMusicBrainzContent(String mbid) {
        return MusicBrainzContentFactory.createFromMbid(mbid);
    }

    private WikipediaContent createWikipediaContent() {
        try {
            return createWikipediaContentSafe();
        } catch (NoSuchElementException | URISyntaxException e) {
            System.err.println(String.format("WikipediaContent not created due to Exception: %s", e));
            return null;
        }
    }

    private WikipediaContent createWikipediaContentSafe() throws URISyntaxException {
        try {
            return createWikipediaContentFromTitle();
        } catch (NoSuchElementException e) {
            return createWikipediaContentFromId();
        }
    }

    private WikipediaContent createWikipediaContentFromTitle() throws URISyntaxException {
        String title = musicBrainzContent.getWikipediaTitle();
        return WikipediaContentFactory.createFromWikipediaTitle(title);
    }

    private WikipediaContent createWikipediaContentFromId() throws URISyntaxException {
        String wikidataId = musicBrainzContent.getWikidataId();
        return WikipediaContentFactory.createFromWikidataId(wikidataId);
    }
}
