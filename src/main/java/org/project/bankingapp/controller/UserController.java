package org.project.bankingapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.project.bankingapp.model.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Validated
public interface UserController {

    @Operation(summary = "Authenticate user", description = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "User authenticated successfully") })
    @RequestMapping(value = "/signin",
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<?> signIn(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema())@RequestBody UserRequest body
);
    @Operation(summary = "Register a new user", description = "", tags={  })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully") })
    @RequestMapping(value = "/signup",
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<?> signUp(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema())@RequestBody UserRequest body
    );
}

