--SCHEMA PARA MICROSERVICIO CUSTOMER
CREATE SCHEMA IF NOT EXISTS CUSTOMER;

--TABLAS PARA  SCHEMA CUSTOMER
CREATE TABLE customer.person (
    person_id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    gender VARCHAR(1),
    birth_date DATE,
    identity_number VARCHAR(50),
    address VARCHAR(255),
    phone VARCHAR(50),
    status VARCHAR(1),
    created_by_user BIGINT,
    created_date TIMESTAMP,
    last_modified_by_user BIGINT,
    last_modified_date TIMESTAMP,
    created_from_ip VARCHAR(255),
    updated_from_ip VARCHAR(255),
    PRIMARY KEY (person_id)
);

CREATE TABLE customer.client (
    client_id BIGINT AUTO_INCREMENT NOT NULL,
    password VARCHAR(255) NOT NULL,
    person_id BIGINT NOT NULL,
    status VARCHAR(1),
    created_by_user BIGINT,
    created_date TIMESTAMP,
    last_modified_by_user BIGINT,
    last_modified_date TIMESTAMP,
    created_from_ip VARCHAR(255),
    updated_from_ip VARCHAR(255),
    PRIMARY KEY (client_id),
    FOREIGN KEY (person_id) REFERENCES customer.person (person_id)
);

-- INSERT PARA TABLA PERSON
INSERT INTO customer.person (
    person_id,
    name,
    surname,
    gender,
    birth_date,
    identity_number,
    address,
    phone,
    status,
    created_by_user,
    created_date,
    last_modified_by_user,
    last_modified_date,
    created_from_ip,
    updated_from_ip
) VALUES (
     1,
    'Jose',
    'Lema',
    'M',
    '1996-10-08',
    '1787859852',
    'Otavalo sn y principal',
    '098254785',
    '1',
    1001,
    CURRENT_TIMESTAMP,
    1001,
    CURRENT_TIMESTAMP,
    '192.168.0.1',
    '192.168.0.1'
);

INSERT INTO customer.person (
    person_id,
    name,
    surname,
    gender,
    birth_date,
    identity_number,
    address,
    phone,
    status,
    created_by_user,
    created_date,
    last_modified_by_user,
    last_modified_date,
    created_from_ip,
    updated_from_ip
) VALUES (
     2,
    'Marianela',
    'Montalvo',
    'M',
    '1996-10-08',
    '1741415412',
    'Amazonas y NNUU',
    '097548965',
    '1',
    1001,
    CURRENT_TIMESTAMP,
    1001,
    CURRENT_TIMESTAMP,
    '192.168.0.1',
    '192.168.0.1'
);

INSERT INTO customer.person (
    person_id,
    name,
    surname,
    gender,
    birth_date,
    identity_number,
    address,
    phone,
    status,
    created_by_user,
    created_date,
    last_modified_by_user,
    last_modified_date,
    created_from_ip,
    updated_from_ip
) VALUES (
     3,
    'Juan',
    'Osorio',
    'M',
    '1996-10-08',
    '1747748754',
    '13 junio y Equinoccial',
    '098874587',
    '1',
    1001,
    CURRENT_TIMESTAMP,
    1001,
    CURRENT_TIMESTAMP,
    '192.168.0.1',
    '192.168.0.1'
);

-- INSERT PARA TABLA CLIENT
INSERT INTO customer.client (
    password,
    person_id,
    status,
    created_by_user,
    created_date,
    last_modified_by_user,
    last_modified_date,
    created_from_ip,
    updated_from_ip
)
VALUES (
    '1234',
    (SELECT p.person_id
     FROM customer.person p
     WHERE p.identity_number = '1787859852'),
    '1',
    1001,
    CURRENT_TIMESTAMP,
    1001,
    CURRENT_TIMESTAMP,
    '192.168.0.1',
    '192.168.0.1'
);

INSERT INTO customer.client (
    password,
    person_id,
    status,
    created_by_user,
    created_date,
    last_modified_by_user,
    last_modified_date,
    created_from_ip,
    updated_from_ip
)
VALUES (
    '5678',
    (SELECT p.person_id
     FROM customer.person p
     WHERE p.identity_number = '1741415412'),
    '1',
    1001,
    CURRENT_TIMESTAMP,
    1001,
    CURRENT_TIMESTAMP,
    '192.168.0.1',
    '192.168.0.1'
);

INSERT INTO customer.client (
    password,
    person_id,
    status,
    created_by_user,
    created_date,
    last_modified_by_user,
    last_modified_date,
    created_from_ip,
    updated_from_ip
)
VALUES (
    '1245',
    (SELECT p.person_id
     FROM customer.person p
     WHERE p.identity_number = '1747748754'),
    '1',
    1001,
    CURRENT_TIMESTAMP,
    1001,
    CURRENT_TIMESTAMP,
    '192.168.0.1',
    '192.168.0.1'
);
