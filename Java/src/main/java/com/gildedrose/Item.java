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

                if (isExpiredAfterReduction()) {
                    increaseQuality();
                }
                break;
            case "Backstage passes to a TAFKAL80ETC concert":
                increaseQuality();

                if (sellIn < 11) {
                    increaseQuality();
                }

                if (sellIn < 6) {
                    increaseQuality();
                }

                if (isExpiredAfterReduction()) {
                    quality = 0;
                }
                break;
            case "Sulfuras, Hand of Ragnaros":
                break;
            default:
                if (quality > 0) {
                    quality = quality - 1;
                }

                if (isExpiredAfterReduction()) {
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

    private boolean isExpiredAfterReduction() {
        return --sellIn < 0;
    }
}
