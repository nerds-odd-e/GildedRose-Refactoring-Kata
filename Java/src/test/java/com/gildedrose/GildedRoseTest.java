package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    @Test
    public void quality_reduce_one_per_day_when_not_expired() {
        Item[] items = new Item[]{new Item("normal", 1, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(49, app.items[0].quality);
    }

    @Test
    public void quality_reduce_one_per_day_when_just_expired() {
        Item[] items = new Item[]{new Item("normal", 0, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(48, app.items[0].quality);
    }

    @Test
    public void quality_reduce_one_per_day_when_already_expired() {
        Item[] items = new Item[]{new Item("normal", -1, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(48, app.items[0].quality);
    }

}
