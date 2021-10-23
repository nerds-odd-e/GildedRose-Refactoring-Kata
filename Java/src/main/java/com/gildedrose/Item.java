package com.gildedrose;

public class Item {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    private int sellIn;
    private int quality;

    public Item() {
    }


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

                if (willExpireInDays(10)) {
                    increaseQuality();
                }

                if (willExpireInDays(5)) {
                    increaseQuality();
                }

                if (isExpiredAfterReduction()) {
                    makeNoQuality();
                }
                break;
            case "Sulfuras, Hand of Ragnaros":
                break;
            default:
                decreaseQuality();

                if (isExpiredAfterReduction()) {
                    decreaseQuality();
                }
                break;
        }
    }

    private void decreaseQuality() {
        if (quality > 0) {
            quality = quality - 1;
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

    private void makeNoQuality() {
        quality = 0;
    }

    private boolean willExpireInDays(int days) {
        return sellIn <= days;
    }
}
