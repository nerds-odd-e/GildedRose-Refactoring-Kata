package com.gildedrose;

class GildedRose {
    public Item[] getItems() {
        return items;
    }

    private final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public Item getItems(int index) {
        return items[index];
    }

    public void updateQuality() {
        for (Item item : items) {
            item.updateQuality();
        }
    }

}