Feature: Response of a web-page

  Scenario: sending HTTP request to https://www.iste.uni-stuttgart.de/rss/
    Given the next beginning of Summer Term
    Then "run" the experiment for 24 "hours"
    And "ensure" a "positive answer" should return
    And "ensure" a "response time" is "less" than 1000 "miliseconds"
