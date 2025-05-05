package com.polymath.notzerowalletservice.service;

import com.polymath.notzerowalletservice.dto.requests.CreateWalletRequest;
import com.polymath.notzerowalletservice.dto.requests.TransactionRequest;
import com.polymath.notzerowalletservice.dto.responses.WalletResponse;
import com.polymath.notzerowalletservice.exceptions.CustomBadRequest;
import com.polymath.notzerowalletservice.exceptions.NotFound;
import com.polymath.notzerowalletservice.model.Transaction;
import com.polymath.notzerowalletservice.model.TransactionType;
import com.polymath.notzerowalletservice.model.Wallet;
import com.polymath.notzerowalletservice.repositories.TransactionRepository;
import com.polymath.notzerowalletservice.repositories.WalletRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public WalletService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }
    @Transactional
    public WalletResponse createWallet(CreateWalletRequest request){
        if(request.firstName()==null|| request.firstName().isEmpty()){
            throw new CustomBadRequest("First name cannot be empty");
        }
        if(request.lastName()==null|| request.lastName().isEmpty()){
            throw new CustomBadRequest("Last name cannot be empty");
        }
        String accountNumber = generateAccountNumber();
        Wallet wallet = new Wallet(accountNumber,request.firstName(),request.lastName());
        walletRepository.save(wallet);
        return mapToWalletResponse(wallet);
    }

    @Transactional(readOnly = true)
    public WalletResponse getWalletDetails(String accountNumber){
        Wallet wallet = findActiveWallet(accountNumber);
        return mapToWalletResponse(wallet);
    }

    @Transactional
    public Transaction processTransaction(String accountNumber,TransactionRequest request){
       Wallet wallet = findActiveWallet(accountNumber);
        BigDecimal currentBalance = wallet.getBalance();
        BigDecimal amount = request.amount();
        if(request.transactionType()== TransactionType.CREDIT){
            wallet.setBalance(currentBalance.add(amount));
        }else {
            if(currentBalance.compareTo(amount)<0){
                throw new CustomBadRequest("Insufficient funds");
            }
            wallet.setBalance(currentBalance.subtract(amount));
        }
        wallet.setUpdatedAt(LocalDateTime.now());
        walletRepository.save(wallet);
        Transaction transaction = new Transaction(
                accountNumber,
                request.transactionType(),
                request.amount(),
                request.description()
        );
        return transactionRepository.save(transaction);
    }

    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactions(String accountNumber){
       findActiveWallet(accountNumber);
        return transactionRepository.findByWalletAccountNumberOrderByTimestampDesc(accountNumber);
    }
    @Transactional
    public void disableWallet(String accountNumber){
        Wallet wallet = findActiveWallet(accountNumber);
        wallet.setActive(false);
        wallet.setUpdatedAt(LocalDateTime.now());
        walletRepository.save(wallet);
    }
    private Wallet findActiveWallet(String accountNumber){
        return walletRepository.findByAccountNumberAndActiveTrue(accountNumber).orElseThrow(()->new NotFound("Wallet not found or disabled "+accountNumber));
    }

    private String generateAccountNumber() {
        Random random = new Random();
        String accountNumber;
        do{
            accountNumber = String.format("%010d",random.nextInt(1000000000));
        }while (walletRepository.findByAccountNumberAndActiveTrue(accountNumber).isPresent());
        return accountNumber;
    }
    private WalletResponse mapToWalletResponse(Wallet wallet){
        return new WalletResponse(wallet.getAccountNumber(),wallet.getFirstName(),wallet.getLastName(),wallet.getBalance(),wallet.getCreatedAt());
    }
}
