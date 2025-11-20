package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredUtils {
    static RequestSpecification reqSpec = RestAssured.given();
    static Response response;
    static String endPoint;

    public static void setURI(String url){
        if(ConfigReader.getConfigValue("restful-booker.url").contains(url)){
            reqSpec = reqSpec.baseUri(ConfigReader.getConfigValue("restful-booker.url"));
        }else{
            reqSpec = reqSpec.baseUri(ConfigReader.getConfigValue("restful-api-url"));
        }
    }

    public static void setEndPoint(String endPoint){
        if(endPoint.contains("@") && endPoint.contains("booking")){
            String bookingId = ConfigReader.getConfigValue("request.bookingId");
            endPoint = endPoint.replace("@id",bookingId);
        } else if (endPoint.contains("@") && endPoint.contains("objects")) {
            String objectId = ConfigReader.getConfigValue("request.id");
            endPoint=endPoint.replace("@id",objectId);
        }
        RestAssuredUtils.endPoint = endPoint;
    }

    public static void setHeaderValue(String key,String value){
        reqSpec.header(key,value);
    }

    public static void setPathParam(String key, String value){
        reqSpec.pathParam(key,value);
    }

    public static void setQueryParam(String key, String value){
        reqSpec.queryParam(key,value);
    }

    public static void setBody(String filePath){
        try {
            reqSpec.body(getDataFromJsonFile(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setBodyUsingPojo(Object pojo){
        reqSpec.body(pojo);
    }

    public static void get(){
        reqSpec.log().all();
        response = reqSpec.get(endPoint);
        response.then().log().all();
    }

    public static void post(){
        reqSpec.log().all();
        response = reqSpec.post(endPoint);
        response.then().log().all();
    }

    public static void delete() {
        reqSpec.log().all();
        response = reqSpec.delete(endPoint);
        response.then().log().all();
    }

    public static void put() {
        reqSpec.log().all();
        response = reqSpec.put(endPoint);
        response.then().log().all();
    }

    public static int getStatusCode(){
        return response.getStatusCode();
    }

    public static String getResponseFieldValue(String jsonPath) {
        return response.jsonPath().getString(jsonPath);
    }

    public static String getDataFromJsonFile(String fileName) throws FileNotFoundException {
        String jsonFolderPath = "src/test/resources/json/";
        Scanner sc = new Scanner(new FileInputStream(jsonFolderPath + fileName));
        return sc.useDelimiter("\\Z").next();
    }
}
