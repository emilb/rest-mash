package coverart;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TestCoverArtContent {
    @Test
    public void testGetImagesDallas() {
        String id = "2c67cccd-3412-474d-8864-d1e66d7ae1d9";
        CoverArtContent content = CoverArtContentFactory.createFromMbid(id);
        CoverArtImage[] images = content.getImages();
        CoverArtImage image = images[0];
        String expected = "http://coverartarchive.org/release/88ad4815-52ac-4627-8f9e-c9ebe66e184d/5095677398.jpg";
        assertEquals(expected, image.getUrl());
    }

    @Test
    public void testGetImagesNonExistent() {
        String id = "60c826fb-8853-3796-8ff2-16c35f362ec9";
        assertNull(CoverArtContentFactory.createFromMbid(id));
    }
}
