Feature: Gilded Rose

  Scenario Outline: Sell in days - <testName>
    Given gilded rose item with name "<itemName>" and sell in <currentSellIn>
    When update quality
    Then item sell in should be <expectedSellIn>
    Examples:
      | testName                             | itemName | currentSellIn | expectedSellIn |
      | reduce one per day when not expired  | normal   | 1             | 0              |
      | reduce one per day when just expired | normal   | 0             | -1             |

  Scenario Outline: Quality - <testName>
    Given gilded rose item with name "<itemName>" and quality <quality> and sell in <sellIn>
    When update quality
    Then item quality should be <expectedQuality>
    Examples:
      | testName                             | itemName | quality | sellIn | expectedQuality |
      | reduce one per day when not expired  | normal   | 50      | 1      | 49              |
      | reduce one per day when just expired | normal   | 50      | 0      | 48              |

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
      | testName                             | itemName | currentSellIn | expectedSellIn |
      | reduce one per day when not expired  | normal   | 1             | 0              |
      | reduce one per day when just expired | normal   | 0             | -1             |

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
      | testName                             | itemName | quality | sellIn | expectedQuality |
      | reduce one per day when not expired  | normal   | 50      | 1      | 49              |
      | reduce one per day when just expired | normal   | 50      | 0      | 48              |

