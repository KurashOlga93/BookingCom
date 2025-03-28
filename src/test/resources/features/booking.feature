Feature: Search on Booking.com

  @smoke
  Scenario: Search by city
    Given User is looking for hotels in 'United States' city
    When User does search
    Then Hotel 'North Beach Resort & Villas' should be on the search results page
    Then Hotel 'North Beach Resort & Villas' should has rate is '8,7'

  @smoke
  Scenario: Search car by city
    Given User is looking for car on Booking
    And User looking for car from 'London Gatwick Airport'
    And User enter destination is 'London Zoo'
    And The user enters the date of the trip
    When User click search
    Then Car 'standard' should be on the results page

  @smoke
  Scenario: DataTable
    Given User provide information:
      | name | email | phone |
      | olga | ola@mail.ru    | 2233433  |
      | kirill | lil@mail.ru  | 3344455  |