package org.project.bankingapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.project.bankingapp.jwt.JwtTokenProvider;
import org.project.bankingapp.model.TransactionRequest;
import org.project.bankingapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class TransactionControllerImpl implements TransactionController{
    private final JwtTokenProvider jwtTokenProvider;

    private final TransactionService transactionService;

    public ResponseEntity<?> getBalance() {
        String token = getTokenFromRequest();

        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        String username = jwtTokenProvider.getUsernameFromToken(token);

        Integer balance = transactionService.getBalance(username);
        balance = balance == null? 0: balance;
        return ResponseEntity.ok(balance);
    }

    public ResponseEntity<?> transferMoney(TransactionRequest moneyTransferRequest) {
        String token = getTokenFromRequest();

        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        String currentUser = jwtTokenProvider.getUsernameFromToken(token);
        boolean answer = transactionService.transferMoney(currentUser, moneyTransferRequest.to(), moneyTransferRequest.amount());
        if (!answer){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Money not transferred");
        }
        return ResponseEntity.ok("Money transferred successfully");
    }

    private String getTokenFromRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

