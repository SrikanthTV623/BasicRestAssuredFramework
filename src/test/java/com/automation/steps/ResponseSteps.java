package com.automation.steps;

import com.automation.pojo.ObjectPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
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
    public void verifyThatObjectDetailsSentInRequestMatchesTheGETCallResponse() throws JsonProcessingException {
        String nameInResponse = RestAssuredUtils.getResponseFieldValue("name");
        String yearInResponse = RestAssuredUtils.getResponseFieldValue("data.year");
        String priceInResponse = RestAssuredUtils.getResponseFieldValue("data.price");
        String cpuModelInResponse = RestAssuredUtils.getResponseFieldValue("data['CPU model']");
        String hardDiskDriveInResponse = RestAssuredUtils.getResponseFieldValue("data['Hard disk size']");

        ObjectPojo requestPojo = (ObjectPojo) ConfigReader.getObject("add_object_pojo");

        //1st method
        // Convert POJO → JSON
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(requestPojo);

        // Now use JsonPath
        JsonPath js = new JsonPath(jsonBody);

        Assert.assertEquals(js.getString("name"),nameInResponse);
        Assert.assertEquals(js.getString("data.year"),yearInResponse);
        Assert.assertEquals(js.getString("data.price"),priceInResponse);
        Assert.assertEquals(js.getString("data['CPU model']"),cpuModelInResponse);
        Assert.assertEquals(js.getString("data['Hard disk size']"),hardDiskDriveInResponse);


        //2nd method
        //here extracting that object through config reader, assigning that to pojo class
        //using field values to validate

//        Assert.assertEquals(nameInResponse, requestPojo.getName());
//        Assert.assertEquals(yearInResponse, String.valueOf(requestPojo.getData().getYear()));
//        Assert.assertEquals(priceInResponse, String.valueOf(requestPojo.getData().getPrice()));
//        Assert.assertEquals(cpuModelInResponse, requestPojo.getData().getCpu_model());
//        Assert.assertEquals(hardDiskDriveInResponse, requestPojo.getData().getHard_disk_size());
    }
}
