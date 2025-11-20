package com.automation.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.javafaker.DateAndTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)   //it ignores the property of id
@EqualsAndHashCode //here we override the equals method of the parent object class

public class BookingDetailsPojo {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesPojo bookingdates;
    private String additionalneeds;
}
