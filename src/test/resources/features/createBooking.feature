Feature: Validate Restful Booker API Functionality

  Scenario: Verify user can creates a new booking through API

    #Given user want to call base "restful-booker"
    Given user wants to call endpoint "/booking"
    And set header "Content-type" to "application/json"
    And set request body for "restful-booker" API from the file "createBooking.json" using pojo
    When user performs "POST" call
    Then verify status code is 200
    And store created id into "request.bookingId" from response

    When user wants to call endpoint "/booking/@id"
    When user performs "GET" call
    Then verify status code is 200
#    And verify response is same as request passed in post call
#
#    And verify response is matching with the "createBookingSchema.json"