Feature: Restful API Functionality

  @Object
  Scenario: Add Object through API Call and Fetch that Object Details from API
    Given user want to call base "restful-api"
    Given user wants to call endpoint "/objects"
    And set header "Content-type" to "application/json"
    And set request body for "restful-api" API from the file "createObject.json" using pojo
    When user performs "POST" call
    Then verify status code is 200
    Then store created id into "request.id" from "restful-api" response
    
    When user wants to call endpoint "/objects/@id"
    When user performs "GET" call
    Then verify status code is 200
    Then verify that object details sent in request matches the GET Call response
