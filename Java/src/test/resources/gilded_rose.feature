Feature: Gilded Rose

  Scenario Outline: Sell in days - <testName>
    Given gilded rose item with name "<itemName>" and sell in <currentSellIn>
    When update quality
    Then item sell in should be <expectedSellIn>
    Examples:
      | testName                                | itemName                   | currentSellIn | expectedSellIn |
      | reduce one per day when not expired     | normal                     | 1             | 0              |
      | reduce one per day when just expired    | normal                     | 0             | -1             |
      | reduce one per day when already expired | normal                     | -1            | -2             |
      | never reduce when not expired           | Sulfuras, Hand of Ragnaros | 1             | 1              |
      | never reduce when just expired          | Sulfuras, Hand of Ragnaros | 0             | 0              |
      | never reduce when already expired       | Sulfuras, Hand of Ragnaros | -1            | -1             |

  Scenario Outline: Quality - <testName>
    Given gilded rose item with name "<itemName>" and quality <quality> and sell in <sellIn>
    When update quality
    Then item quality should be <expectedQuality>
    Examples:
      | testName                                            | itemName                                  | quality | sellIn | expectedQuality |
      | reduce one per day when not expired                 | normal                                    | 50      | 1      | 49              |
      | reduce two per day when just expired                | normal                                    | 50      | 0      | 48              |
      | reduce two per day when already expired             | normal                                    | 50      | -1     | 48              |
      | reduce two per day when expired                     | normal                                    | 3       | 0      | 1               |
      | can not reduce beyond 0 when expired                | normal                                    | 1       | 0      | 0               |
      | should keep as 0 even when not expired              | normal                                    | 0       | 1      | 0               |
      | increase one per day when not expired               | Aged Brie                                 | 0       | 1      | 1               |
      | increase two per day when just expired              | Aged Brie                                 | 0       | 0      | 2               |
      | increase two per day when already expired           | Aged Brie                                 | 0       | -1     | 2               |
      | increase two per day when already expired           | Aged Brie                                 | 49      | -1     | 50              |
      | increase two per day when already expired           | Aged Brie                                 | 48      | -1     | 50              |
      | can't exceed 50 when not expired                    | Aged Brie                                 | 50      | 1      | 50              |
      | can't exceed 50 when expired                        | Aged Brie                                 | 49      | 1      | 50              |
      | is always 80 and never changes when not expired     | Sulfuras, Hand of Ragnaros                | 80      | 1      | 80              |
      | is always 80 and never changes when just expired    | Sulfuras, Hand of Ragnaros                | 80      | 0      | 80              |
      | is always 80 and never changes when already expired | Sulfuras, Hand of Ragnaros                | 80      | -1     | 80              |
      | increase one per day when sell in days > 10         | Backstage passes to a TAFKAL80ETC concert | 0       | 11     | 1               |
      | increase one per day when sell in days > 10         | Backstage passes to a TAFKAL80ETC concert | 50      | 11     | 50              |
      | increase two per day when sell in days = 10         | Backstage passes to a TAFKAL80ETC concert | 0       | 10     | 2               |
      | increase two per day when sell in days = 10         | Backstage passes to a TAFKAL80ETC concert | 48      | 10     | 50              |
      | increase two per day when sell in days < 10         | Backstage passes to a TAFKAL80ETC concert | 0       | 9      | 2               |
      | increase two per day when sell in days > 5          | Backstage passes to a TAFKAL80ETC concert | 0       | 6      | 2               |
      | increase three per day when sell in days = 5        | Backstage passes to a TAFKAL80ETC concert | 0       | 5      | 3               |
      | increase three per day when sell in days = 5        | Backstage passes to a TAFKAL80ETC concert | 47      | 5      | 50              |
      | increase three per day when sell in days < 5        | Backstage passes to a TAFKAL80ETC concert | 0       | 4      | 3               |
      | drop to 0 when expired regardless quality value     | Backstage passes to a TAFKAL80ETC concert | 50      | 0      | 0               |
      | drop to 0 when expired regardless quality value     | Backstage passes to a TAFKAL80ETC concert | 50      | 1      | 50              |
      | can't exceed 50 when increasing two per day         | Backstage passes to a TAFKAL80ETC concert | 49      | 9      | 50              |
      | can't exceed 50 when increasing three per day       | Backstage passes to a TAFKAL80ETC concert | 48      | 4      | 50              |


  Scenario Outline: Sell in days by jfactory - <testName>
    Given jfactory gilded rose item
      | name       | sellIn          |
      | <itemName> | <currentSellIn> |
    When update quality
    Then gilded rose items should be:
    """
      sellIn: [<expectedSellIn>]
    """
    Examples:
      | testName                                | itemName                   | currentSellIn | expectedSellIn |
      | reduce one per day when not expired     | normal                     | 1             | 0              |
      | reduce one per day when just expired    | normal                     | 0             | -1             |
      | reduce one per day when already expired | normal                     | -1            | -2             |
      | never reduce when not expired           | Sulfuras, Hand of Ragnaros | 1             | 1              |
      | never reduce when just expired          | Sulfuras, Hand of Ragnaros | 0             | 0              |
      | never reduce when already expired       | Sulfuras, Hand of Ragnaros | -1            | -1             |

  Scenario Outline: Quality - <testName>
    Given jfactory gilded rose item
      | name       | sellIn   | quality   |
      | <itemName> | <sellIn> | <quality> |
    When update quality
    Then gilded rose items should be:
    """
      quality: [<expectedQuality>]
    """
    Examples:
      | testName                                            | itemName                                  | quality | sellIn | expectedQuality |
      | reduce one per day when not expired                 | normal                                    | 50      | 1      | 49              |
      | reduce one per day when just expired                | normal                                    | 50      | 0      | 48              |
      | reduce two per day when already expired             | normal                                    | 50      | -1     | 48              |
      | reduce two per day when expired                     | normal                                    | 3       | 0      | 1               |
      | can not reduce beyond 0 when expired                | normal                                    | 1       | 0      | 0               |
      | should keep as 0 even when not expired              | normal                                    | 0       | 1      | 0               |
      | increase one per day when not expired               | Aged Brie                                 | 0       | 1      | 1               |
      | increase two per day when just expired              | Aged Brie                                 | 0       | 0      | 2               |
      | increase two per day when already expired           | Aged Brie                                 | 0       | -1     | 2               |
      | increase two per day when already expired           | Aged Brie                                 | 49      | -1     | 50              |
      | increase two per day when already expired           | Aged Brie                                 | 48      | -1     | 50              |
      | can't exceed 50 when not expired                    | Aged Brie                                 | 50      | 1      | 50              |
      | can't exceed 50 when expired                        | Aged Brie                                 | 49      | 1      | 50              |
      | is always 80 and never changes when not expired     | Sulfuras, Hand of Ragnaros                | 80      | 1      | 80              |
      | is always 80 and never changes when just expired    | Sulfuras, Hand of Ragnaros                | 80      | 0      | 80              |
      | is always 80 and never changes when already expired | Sulfuras, Hand of Ragnaros                | 80      | -1     | 80              |
      | increase one per day when sell in days > 10         | Backstage passes to a TAFKAL80ETC concert | 0       | 11     | 1               |
      | increase one per day when sell in days > 10         | Backstage passes to a TAFKAL80ETC concert | 50      | 11     | 50              |
      | increase two per day when sell in days = 10         | Backstage passes to a TAFKAL80ETC concert | 0       | 10     | 2               |
      | increase two per day when sell in days = 10         | Backstage passes to a TAFKAL80ETC concert | 48      | 10     | 50              |
      | increase two per day when sell in days < 10         | Backstage passes to a TAFKAL80ETC concert | 0       | 9      | 2               |
      | increase two per day when sell in days > 5          | Backstage passes to a TAFKAL80ETC concert | 0       | 6      | 2               |
      | increase three per day when sell in days = 5        | Backstage passes to a TAFKAL80ETC concert | 0       | 5      | 3               |
      | increase three per day when sell in days = 5        | Backstage passes to a TAFKAL80ETC concert | 47      | 5      | 50              |
      | increase three per day when sell in days < 5        | Backstage passes to a TAFKAL80ETC concert | 0       | 4      | 3               |
      | drop to 0 when expired regardless quality value     | Backstage passes to a TAFKAL80ETC concert | 50      | 0      | 0               |
      | drop to 0 when expired regardless quality value     | Backstage passes to a TAFKAL80ETC concert | 50      | 1      | 50              |
      | can't exceed 50 when increasing two per day         | Backstage passes to a TAFKAL80ETC concert | 49      | 9      | 50              |
      | can't exceed 50 when increasing three per day       | Backstage passes to a TAFKAL80ETC concert | 48      | 4      | 50              |

