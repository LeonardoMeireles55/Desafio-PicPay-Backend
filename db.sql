CREATE TABLE IF NOT EXISTS wallets (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cpf VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    type INT NOT NULL,
    BALANCE DECIMAL(10, 2)
);

CREATE UNIQUE INDEX IF NOT EXISTS cpf_idx ON wallets (cpf);

CREATE UNIQUE INDEX IF NOT EXISTS email_idx ON wallets (email);

CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    payer INT NOT NULL,
    payee INT NOT NULL,
    value DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (payer) REFERENCES wallets (id),
    FOREIGN KEY (payee) REFERENCES wallets (id)
);

DELETE FROM
    transactions;

DELETE FROM
    wallets;

INSERT INTO
    WALLETS (
        id,
        name,
        cpf,
        email,
        "password",
        "type",
        balance
    )
VALUES
    (
        1,
        'Joao - User',
        12345678900,
        'joao@test.com',
        '123456',
        1,
        1000.00
    );

INSERT INTO
    WALLETS (
        id,
        name,
        cpf,
        email,
        "password",
        "type",
        balance
    )
VALUES
    (
        2,
        'Maria - Lojista',
        12345678901,
        'maria@test.com',
        '123456',
        2,
        1000.00
    );