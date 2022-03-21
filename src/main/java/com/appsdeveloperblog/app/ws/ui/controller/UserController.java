package com.appsdeveloperblog.app.ws.ui.controller;

import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class UserController {

    @GetMapping
    public String getUsers() {
        return "get users was called";
    }

    @GetMapping(path = "/{userId}")
    public String getUserDetail(@PathVariable String userId) {
        return "get user was called with user Id = " + userId;
    }

    @GetMapping(path = "/parameters")
    public String getUserWithRequestParameters(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit
    ) {
        return "get user was called with page = " + page + " and limit = " + limit;
    }

    @GetMapping(path = "/parameters/default-values")
    public String getUserWithRequestParametersDefaultValues(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "50") Integer limit
    ) {
        return "get user was called with page = " + page + " and limit = " + limit;
    }

    @GetMapping(path = "/parameters/required-values")
    public String getUserWithRequestParametersRequiredValues(
            @RequestParam(value = "page", required = true) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit
    ) {
        return "get user was called with page = " + page + " and limit = " + limit;
    }

    @GetMapping(path = "/{userId}/java-obj-return-xml", produces = MediaType.APPLICATION_XML_VALUE)
    public UserRest getUserDetailsViaXML(@PathVariable String userId) {
        UserRest userRest = new UserRest();
        userRest.setUserId(userId);
        userRest.setFirstName("Roberto");
        userRest.setLastName("Gianotto");
        userRest.setEmail("gianottoroberto@gmail.com");

        return userRest;
    }

    @GetMapping(path = "/{userId}/java-obj-return-json", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRest getUserDetailsViaJSON(@PathVariable String userId) {
        UserRest userRest = new UserRest();
        userRest.setUserId(userId);
        userRest.setFirstName("Roberto");
        userRest.setLastName("Gianotto");
        userRest.setEmail("gianottoroberto@gmail.com");

        return userRest;
    }

    @PostMapping
    public String createUser() {
        return "create user was called";
    }

    @PutMapping
    public String updatetUser() {
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete user was called";
    }
}
