package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void quality_reduce_two_per_day_when_expired() {
        app = gildedRoseWithItem(normalItem(3, 0));

        app.updateQuality();

        assertItemQualityEquals(1);
    }

    @Test
    public void quality_can_not_reduce_beyond_0_when_expired() {
        app = gildedRoseWithItem(normalItem(1, 0));

        app.updateQuality();

        assertItemQualityEquals(0);
    }

    @Test
    public void quality_should_keep_as_0_even_when_not_expired() {
        app = gildedRoseWithItem(normalItem(0, 1));

        app.updateQuality();

        assertItemQualityEquals(0);
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
