package org.project.bankingapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.project.bankingapp.model.TransactionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Validated
public interface TransactionController {

    @Operation(summary = "Get user's current balance", description = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "User's balance retrieved successfully") })
    @RequestMapping(value = "/money",
        method = RequestMethod.GET)
    ResponseEntity<?> getBalance();


    @Operation(summary = "Transfer money to another user", description = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Money transferred successfully") })
    @RequestMapping(value = "/money",
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<?> transferMoney(
            @Parameter(in = ParameterIn.DEFAULT,
                    description = "",
                    required=true,
                    schema=@Schema())@RequestBody TransactionRequest body
);

}

