CREATE TABLE wallets(
                        account_number VARCHAR(20) PRIMARY KEY,
                        first_name VARCHAR(100) NOT NULL,
                        last_name VARCHAR(100) NOT NULL,
                        balance DECIMAL(19, 4) NOT NULL DEFAULT 0,
                        created_at TIMESTAMP NOT NULL,
                        updated_at TIMESTAMP,
                        active BOOLEAN NOT NULL DEFAULT TRUE
)