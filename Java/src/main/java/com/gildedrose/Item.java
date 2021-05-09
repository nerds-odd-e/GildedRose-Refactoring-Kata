package com.gildedrose;

public class Item {

    private final String name;

    private int sellIn;
    private int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public int getSellIn() {
        return sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void updateQuality() {
        switch (name) {
            case "Aged Brie":
                increaseQuality();

                sellIn = sellIn - 1;

                if (sellIn < 0) {
                    increaseQuality();
                }
                break;
            case "Backstage passes to a TAFKAL80ETC concert":
                if (quality < 50) {
                    quality = quality + 1;

                    if (sellIn < 11) {
                        increaseQuality();
                    }

                    if (sellIn < 6) {
                        increaseQuality();
                    }
                }

                sellIn = sellIn - 1;

                if (sellIn < 0) {
                    quality = 0;
                }
                break;
            case "Sulfuras, Hand of Ragnaros":
                break;
            default:
                if (quality > 0) {
                    quality = quality - 1;
                }

                sellIn = sellIn - 1;

                if (sellIn < 0) {
                    if (quality > 0) {
                        quality = quality - 1;
                    }
                }
                break;
        }
    }

    private void increaseQuality() {
        if (quality < 50) {
            quality = quality + 1;
        }
    }
}
