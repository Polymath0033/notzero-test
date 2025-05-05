package com.polymath.notzerowalletservice.controllers;

import com.polymath.notzerowalletservice.dto.requests.CreateWalletRequest;
import com.polymath.notzerowalletservice.dto.requests.TransactionRequest;
import com.polymath.notzerowalletservice.dto.responses.WalletResponse;
import com.polymath.notzerowalletservice.model.Transaction;
import com.polymath.notzerowalletservice.service.WalletService;
import com.polymath.notzerowalletservice.utils.ResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
@Tag(name = "Wallet API",description = "API endpoints for wallet management")
public class WalletController {
    private final WalletService walletService;
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }
    @PostMapping
    @Operation(summary = "Create a wallet",description = "Creates a wallet with first name and last name,balance set to zero")
    public ResponseEntity<Object> createWallet(@Valid @RequestBody CreateWalletRequest wallet) {
        WalletResponse walletResponse = walletService.createWallet(wallet);
        return ResponseHandler.handleResponse(walletResponse, HttpStatus.CREATED,"Created wallet");
    }

    @GetMapping("/{walletAccountNumber}")
    @Operation(summary = "Get wallet details",description = "Retrieve details for specific wallet account")
    public ResponseEntity<Object> getWalletDetails(@PathVariable String walletAccountNumber) {
        WalletResponse walletResponse = walletService.getWalletDetails(walletAccountNumber);
        return ResponseHandler.handleResponse(walletResponse, HttpStatus.OK,"Wallet details");
    }

    @PostMapping("/{walletAccountNumber}/transactions")
    @Operation(summary = "Process a transaction",description = "Process a CREDIT or DEBIT transaction for a wallet")
    public ResponseEntity<?> processTransaction(@PathVariable String walletAccountNumber,@Valid @RequestBody TransactionRequest transactionRequest ) {
        Transaction transaction = walletService.processTransaction(walletAccountNumber, transactionRequest);
        return ResponseHandler.handleResponse(transaction, HttpStatus.OK,"Processed transaction");
    }

    @GetMapping("/{walletAccountNumber}/transactions")
    @Operation(summary = "Get wallet transactions",description = "Retrieve all transactions for a specific wallet")
    public ResponseEntity<Object> getWalletTransactions(@PathVariable String walletAccountNumber) {
        List<Transaction> transactions = walletService.getAllTransactions(walletAccountNumber);
        return ResponseHandler.handleResponse(transactions, HttpStatus.OK,"Retrieved all transactions");
    }

    @DeleteMapping("/{walletAccountNumber}")
    @Operation(summary = "Disable wallet",description = "Disable a wallet account")
    public ResponseEntity<Object> disableWallet(@PathVariable String walletAccountNumber) {
        walletService.disableWallet(walletAccountNumber);
        return ResponseHandler.handleResponse(null, HttpStatus.NO_CONTENT,"Disabled wallet");
    }


}
