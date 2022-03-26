package com.appsdeveloperblog.app.ws.ui.controller;

import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<UserRest> getUserDetailsViaJSON(@PathVariable String userId) {
        UserRest userRest = new UserRest();
        userRest.setUserId(userId);
        userRest.setFirstName("Roberto");
        userRest.setLastName("Gianotto");
        userRest.setEmail("gianottoroberto@gmail.com");

        return new ResponseEntity<>(userRest, HttpStatus.OK);
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    },
            produces = {
            MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetailsRequestModel) {

        UserRest userRest = new UserRest();
        userRest.setFirstName(userDetailsRequestModel.getFirstName());
        userRest.setLastName(userDetailsRequestModel.getLastName());
        userRest.setEmail(userDetailsRequestModel.getEmail());

        return new ResponseEntity<>(userRest, HttpStatus.OK);
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
