package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured Mana Cake";

    public void updateQuality() {
        for (Item item : items) {

            if (is(item, SULFURAS)) {
                continue;
            }

            if (itemAgesInReverse(item)) {
                adjustQuality(item, 1, getMultiplier(item));
            } else {
                adjustQuality(item, -1, getMultiplier(item));
            }

            if (itemHasPassedSellBy(item)) {
                if (is(item, AGED_BRIE)) {
                    adjustQuality(item, 1, getMultiplier(item));
                } else if (is(item, BACKSTAGE_PASSES)) {
                    adjustQuality(item, -item.quality, getMultiplier(item));
                } else {
                    adjustQuality(item, -1, getMultiplier(item));
                }
            }

            item.sellIn = item.sellIn - 1;
        }
    }

    private static int getMultiplier(final Item item) {
        if (item.name.equals(BACKSTAGE_PASSES)) {
            if (item.sellIn <= 0) {
                return 1;
            } else if (item.sellIn <= 5) {
                return 3;
            } else if (item.sellIn <= 10) {
                return 2;
            }
        } else if (item.name.equals(CONJURED)) {
            return 2;
        }

        return 1;
    }

    private static boolean is(final Item item, String name) {
        return item.name.equals(name);
    }

    private static void adjustQuality(final Item item, final int adjustment, final int multiplier) {
        final int quality = item.quality + (adjustment * multiplier);

        if (quality > 50) {
            item.quality = 50;
        } else {
            item.quality = Math.max(quality, 0);
        }
    }

    private static boolean itemHasPassedSellBy(final Item item) {
        return item.sellIn <= 0;
    }

    private static boolean itemAgesInReverse(final Item item) {
        return item.name.equals(AGED_BRIE)
            || item.name.equals(BACKSTAGE_PASSES);
    }
}
