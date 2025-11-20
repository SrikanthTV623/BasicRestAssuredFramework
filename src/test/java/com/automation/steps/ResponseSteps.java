package com.automation.steps;

import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class ResponseSteps {

    @Then("verify status code is {int}")
    public void verify_status_code_is(int statusCode) {
        Assert.assertEquals(RestAssuredUtils.getStatusCode(),statusCode);
    }

    @And("store created id into {string} from response")
    public void storeCreatedIdInto(String key) {
        ConfigReader.setConfigValue(key,
                RestAssuredUtils.getResponseFieldValue("bookingid"));
    }

    @And("store created {string} into {string} from response")
    public void storeIntoFromResponse(String jsonPath, String key) {
        String value = RestAssuredUtils.getResponseFieldValue(jsonPath);
        ConfigReader.setConfigValue(key, value);
    }

    @Then("store created id into {string} from {string} response")
    public void store_created_id_into_from_response(String key, String apiName) {
        if(ConfigReader.getConfigValue("restful-api-url").contains(apiName)){
            ConfigReader.setConfigValue(key,RestAssuredUtils.
                    getResponseFieldValue("id"));
        }else if(ConfigReader.getConfigValue("restful-booker.url").contains(apiName)){
            ConfigReader.setConfigValue(key,
                    RestAssuredUtils.getResponseFieldValue("bookingid"));
        }
    }

    @Then("verify response is same as request passed in post call")
    public void verify_response_is_same_as_request_passed_in_post_call() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("verify response is matching with the {string}")
    public void verify_response_is_matching_with_the(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("verify that object details sent in request matches the GET Call response")
    public void verifyThatObjectDetailsSentInRequestMatchesTheGETCallResponse() {
    }
}
