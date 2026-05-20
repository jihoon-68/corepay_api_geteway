CREATE TABLE auth_users (
                            id BIGINT PRIMARY KEY,
                            email VARCHAR(100) NOT NULL UNIQUE,
                            password VARCHAR(255) NOT NULL,
                            role VARCHAR(20) NOT NULL
);

CREATE UNIQUE INDEX idx_auth_users_email ON auth_users(email);