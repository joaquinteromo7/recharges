CREATE TABLE ACCOUNTS(NUMBER_ACCOUNT int(10), TYPE int(10), STATE boolean, BALANCE float );
CREATE TABLE SERVICES(ID_CLIENTE int(10), TYPE int(10), ID_PROVEEDOR int(10), CREDIT_AMOUNT float );
CREATE TABLE ACCOUNTS(ID int(10), TYPE int(10), STATE boolean, DESCRIPCION varchar(50) );



INSERT INTO ACCOUNTS(NUMBER_ACCOUNT , TYPE,  STATE,  BALANCE) VALUES (1, 5, true, 1);

