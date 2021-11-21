package com.gildedrose;

import org.junit.jupiter.api.Test;

import static io.cucumber.core.cli.Main.run;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseTest {

    @Test
    void run_cucumber() {
        assertEquals(Byte.valueOf("0"), run("--plugin", "pretty", "--glue", "com.gildedrose", "src/test/resources"));
    }
}
