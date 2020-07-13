package com.pod32g.Demo.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
public class Account {
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "The Account id", required = false)
    public int Id;
    @ApiModelProperty(value = "The Account name", required = true)
    public String AccountName;
    @ApiModelProperty(value = "The Account curerncy", required = true)
    public String AccountCurrency;
}