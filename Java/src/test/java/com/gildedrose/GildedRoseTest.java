package com.gildedrose;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class GildedRoseTest {

    private GildedRose app;

    public static Stream<Arguments> dataForNormalTests() {
        return Stream.of(
                arguments("quality_reduce_one_per_day_when_not_expired", 50, 1, 49),
                arguments("quality_reduce_one_per_day_when_just_expired", 50, 0, 48),
                arguments("quality_reduce_one_per_day_when_already_expired", 50, -1, 48),
                arguments("quality_reduce_two_per_day_when_expired", 3, 0, 1),
                arguments("quality_can_not_reduce_beyond_0_when_expired", 1, 0, 0),
                arguments("quality_should_keep_as_0_even_when_not_expired", 0, 1, 0)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("dataForNormalTests")
    public void normal_item(String testName, int currentQuality, int sellInDays, int expectedUpdateQuality) {
        app = gildedRoseWithItem(normalItem(currentQuality, sellInDays));

        app.updateQuality();

        assertItemQualityEquals(expectedUpdateQuality);
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
