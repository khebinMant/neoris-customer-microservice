-- Crear esquema para el microservicio CUSTOMER
CREATE SCHEMA IF NOT EXISTS CUSTOMER;

-- Crear tabla PERSON
CREATE TABLE customer.person (
    person_id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    gender VARCHAR(1),
    birth_date DATE,
    identity_number BIGINT UNIQUE,
    address VARCHAR(255),
    phone VARCHAR(50),
    status VARCHAR(1) DEFAULT '1',
    created_by_user BIGINT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_by_user BIGINT,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_from_ip VARCHAR(255),
    updated_from_ip VARCHAR(255),
    PRIMARY KEY (person_id)
);

-- Crear tabla CLIENT
CREATE TABLE customer.client (
    client_id BIGINT AUTO_INCREMENT NOT NULL,
    password VARCHAR(255) NOT NULL,
    person_id BIGINT NOT NULL,
    status VARCHAR(1) DEFAULT '1',
    created_by_user BIGINT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_by_user BIGINT,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_from_ip VARCHAR(255),
    updated_from_ip VARCHAR(255),
    PRIMARY KEY (client_id),
    FOREIGN KEY (person_id) REFERENCES customer.person (person_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Insertar datos en la tabla PERSON
INSERT INTO customer.person (
    name, surname, gender, birth_date, identity_number, address, phone,
    status, created_by_user, created_date, last_modified_by_user, last_modified_date,
    created_from_ip, updated_from_ip
) VALUES (
    'Jose', 'Lema', 'M', '1996-10-08', 1234567890, 'Otavalo sn y principal', '098254785',
    '1', 1001, CURRENT_TIMESTAMP, 1001, CURRENT_TIMESTAMP,
    '192.168.0.1', '192.168.0.1'
);

-- Insertar datos en la tabla CLIENT
INSERT INTO customer.client (
    password, person_id, status, created_by_user, created_date, last_modified_by_user,
    last_modified_date, created_from_ip, updated_from_ip
) VALUES (
    '1234',
    (SELECT p.person_id FROM customer.person p WHERE p.identity_number = 1234567890),
    '1', 1001, CURRENT_TIMESTAMP, 1001, CURRENT_TIMESTAMP,
    '192.168.0.1', '192.168.0.1'
);