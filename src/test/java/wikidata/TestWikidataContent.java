package wikidata;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestWikidataContent {

    @Test
    public void testGetEnwikiTitleVeronicaMaggio() {
        String id = "Q260597";
        WikidataContent content = WikidataContentFactory.createFromWikidataId(id);
        assertEquals("Veronica Maggio", content.getEnwikiTitle(id));
    }

    @Test
    public void testGetEnwikiTitleNirvana() {
        String id = "Q11649";
        WikidataContent content = WikidataContentFactory.createFromWikidataId(id);
        assertEquals("Nirvana (band)", content.getEnwikiTitle(id));
    }

    @Test
    public void testGetEntitiesNull() {
        WikidataContent content = new WikidataContent();
        assertNull(content.getEntities());
    }

    @Test
    public void testGetEntities() {
        WikidataContent content = new WikidataContent();
        Map<String, Object> entities = new HashMap<>();
        entities.put("Key", null);
        content.setEntities(entities);
        assertEquals(entities, content.getEntities());
    }
}
