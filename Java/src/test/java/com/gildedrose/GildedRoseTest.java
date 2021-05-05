package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    private GildedRose app;

    @Test
    public void quality_reduce_one_per_day_when_not_expired() {
        app = gildedRoseWithItem(normalItem(50, 1));

        app.updateQuality();

        assertItemQualityEquals(49);
    }

    @Test
    public void quality_reduce_one_per_day_when_just_expired() {
        app = gildedRoseWithItem(normalItem(50, 0));

        app.updateQuality();

        assertItemQualityEquals(48);
    }

    @Test
    public void quality_reduce_one_per_day_when_already_expired() {
        app = gildedRoseWithItem(normalItem(50, -1));

        app.updateQuality();

        assertItemQualityEquals(48);
    }

    private void assertItemQualityEquals(int expected) {
        assertEquals(expected, app.items[0].quality);
    }

    private GildedRose gildedRoseWithItem(Item item) {
        return new GildedRose(new Item[]{item});
    }

    private Item normalItem(int quality, int sellIn) {
        return new Item("normal", sellIn, quality);
    }

}
