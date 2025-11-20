package com.automation.steps;

import com.automation.pojo.BookingDatesPojo;
import com.automation.pojo.BookingDetailsPojo;
import com.automation.pojo.ObjectDataPojo;
import com.automation.pojo.ObjectPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;


import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class RequestSteps {

    @Given("user want to call base {string}")
    public void user_want_to_call_base(String url) {
        System.out.println("Base URI: " + url);
        RestAssuredUtils.setURI(url);
    }

    @Given("user wants to call endpoint {string}")
    public void user_wants_to_call_endpoint(String endPoint) {
        RestAssuredUtils.setEndPoint(endPoint);
    }

    @Given("set header {string} to {string}")
    public void set_header_to(String key, String value) {
        RestAssuredUtils.setHeaderValue(key, value);
    }

    @Given("set request body for {string} API from the file {string} using pojo")
    public void set_request_body_for_api_from_the_file_using_pojo(String urlValue, String fileName) throws FileNotFoundException, JsonProcessingException {
        String jsonBody = RestAssuredUtils.getDataFromJsonFile(fileName);
        ObjectMapper om = new ObjectMapper();
        Faker faker = new Faker();
        if(urlValue.equals("restful-booker")){
            BookingDetailsPojo bdPojo = om.readValue(jsonBody, BookingDetailsPojo.class);

            BookingDatesPojo datesPojo = new BookingDatesPojo();

            bdPojo.setFirstname(faker.name().firstName());
            bdPojo.setLastname(faker.name().lastName());
            bdPojo.setTotalprice(faker.number().numberBetween(100, 9999));
            bdPojo.setDepositpaid(faker.bool().bool());
            datesPojo.setCheckin(faker.date().past(10, TimeUnit.DAYS).toString());
            datesPojo.setCheckout(faker.date().future(10, TimeUnit.DAYS).toString());
            bdPojo.setBookingdates(datesPojo);
            bdPojo.setAdditionalneeds(faker.food().dish());

            ConfigReader.setObject("create_booking_pojo",bdPojo);

            RestAssuredUtils.setBodyUsingPojo(bdPojo);
        }
        else if(urlValue.equals("restful-api")){
            ObjectPojo op = om.readValue(jsonBody,ObjectPojo.class);

            ObjectDataPojo odp = new ObjectDataPojo();

            op.setName(faker.commerce().productName());

            odp.setYear(faker.number().numberBetween(2010,2026));
            odp.setPrice(Double.parseDouble(faker.commerce().price()));
            odp.setCpu_model(faker.options().option("Intel Core i5", "Intel Core i7", "Intel Core i10", "AMD Ryzen 7", "M2 Pro", "M1 Pro"));
            odp.setHard_disk_size(faker.options().option("256 GB", "512 GB", "1 TB", "2 TB"));

            op.setData(odp);

            ConfigReader.setObject("add_object_pojo",op);
            RestAssuredUtils.setBodyUsingPojo(op);
        }
    }
    @When("user performs {string} call")
    public void user_performs_call(String method) {
        if (method.equalsIgnoreCase("POST")){
            RestAssuredUtils.post();
        }else if(method.equalsIgnoreCase("GET")){
            RestAssuredUtils.get();
        }
    }
}
