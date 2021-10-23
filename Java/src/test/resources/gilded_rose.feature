Feature: Gilded Rose

  Scenario: Sell in days
    Given gilded rose item with name "normal" and sell in 1
    When update quality
    Then item sell in should be 0
