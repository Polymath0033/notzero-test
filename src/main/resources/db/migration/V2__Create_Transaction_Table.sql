CREATE TABLE transactions (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              wallet_account_number VARCHAR(20) NOT NULL,
                              type VARCHAR(10) NOT NULL,
                              amount DECIMAL(19, 4) NOT NULL,
                              description VARCHAR(255),
                              timestamp TIMESTAMP NOT NULL,
                              FOREIGN KEY (wallet_account_number) REFERENCES wallets(account_number)
)