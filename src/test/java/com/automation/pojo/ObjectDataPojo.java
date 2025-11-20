package com.automation.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)

public class ObjectDataPojo {
    private int year;
    private double price;
    @JsonProperty("CPU Model")
    private String cpu_model;
    @JsonProperty("Hard disk size")
    private String hard_disk_size;
}
