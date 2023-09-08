package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.GildedRose.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    public static final String GENERICITEM = "genericitem";

    @Test
    void qualityIsNeverNegative(){
        Item[] items = new Item[] { new Item(GENERICITEM, 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    void qualityDegradesByOneBeforeSellBy(){
        Item[] items = new Item[] { new Item(GENERICITEM, 15, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].quality);
        assertEquals(14, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
        assertEquals(13, app.items[0].sellIn);
    }

    @Test
    void qualityDegradesByTwoAfterSellBy(){
        Item[] items = new Item[] { new Item(GENERICITEM, 1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(7, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void qualityIsNeverMoreThan50(){
        Item[] items = new Item[] { new Item(AGED_BRIE, 0, 49), new Item(BACKSTAGE_PASSES, 5, 49)  };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);

        assertEquals(50, app.items[1].quality);
        assertEquals(4, app.items[1].sellIn);

        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);

        assertEquals(50, app.items[1].quality);
        assertEquals(3, app.items[1].sellIn);
    }


    @Test
    void agedBrieIncreasesInQuality(){
        Item[] items = new Item[] { new Item(AGED_BRIE, 2, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(12, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    // I feel like this is implied but it isn't clear. It passes though
    @Test
    void agedBrieIncreasesByDoubleInQualityAfterSellBy(){
        Item[] items = new Item[] { new Item(AGED_BRIE, 1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(13, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(15, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    void sulfurasNeverHasToBeSoldOrDecreasesInQuality() {
        Item[] items = new Item[] { new Item(SULFURAS, 1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(10, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    void backStagePassesIncreasesInQuality(){
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, 15, 11) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(12, app.items[0].quality);
        assertEquals(14, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(13, app.items[0].quality);
        assertEquals(13, app.items[0].sellIn);
    }

    @Test
    void backStagePassesIncreasesInQualityByTwoWithinTenDays(){
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, 11, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
        assertEquals(10, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(6, app.items[0].quality);
        assertEquals(8, app.items[0].sellIn);
    }

    @Test
    void backStagePassesIncreasesInQualityByThreeWithinTenDays(){
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, 6, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
        assertEquals(5, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(6, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(9, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);
    }

    @Test
    void backStagePassesQualityDropsToZeroAfterTheConcert(){
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    void conjuredQualityDegradesByTwoBeforeSellBy(){
        Item[] items = new Item[] { new Item(CONJURED, 15, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
        assertEquals(14, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(6, app.items[0].quality);
        assertEquals(13, app.items[0].sellIn);
    }

    @Test
    void conjuredQualityDegradesByFourAfterSellBy(){
        Item[] items = new Item[] { new Item(CONJURED, 1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-3, app.items[0].sellIn);
    }






}
