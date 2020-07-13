package com.pod32g.Demo.Models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
public class User {
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "The User's id", required = false)
    public int Id;
    @ApiModelProperty(value = "The User's name", required = true)
    public String Name;

    @ApiModelProperty(value = "The User's accounts list", required = false)
    @OneToMany
    public List<Account> Accounts;

}