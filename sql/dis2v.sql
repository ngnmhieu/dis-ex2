CREATE TABLE estateAgent (
    ID int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1, NO CACHE),
    name varchar(50) not null,
    address varchar(50),
    login varchar(50),
    password varchar(64),
    primary key(ID)
);

CREATE TABLE person (
    ID int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1, NO CACHE),
    firstname varchar(50),
    name varchar(50) not null,
    address varchar(100),
    primary key(ID)
);

CREATE TABLE estate (
    ID int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1, NO CACHE),
    city varchar(50),
    postalcode int,
    street varchar(50),
    streetnumber int,
    squarearea int,
    agentID int not null,
    primary key (ID),
    foreign key (agentID) references estateAgent(ID)
);

CREATE TABLE apartment (
    ID int NOT NULL,
    floor int,
    rent double,
    rooms int,
    balcony SMALLINT,
    builtinkitchen SMALLINT,
    primary key(ID),
    foreign key (ID) references estate(ID)
);

CREATE TABLE house (
    ID int NOT NULL,
    floors int,
    price double,
    garden SMALLINT,
    primary key (ID),
    foreign key (ID) references estate(ID)
);

CREATE TABLE contract (
    contractNo int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1, NO CACHE),
    Date date,
    place varchar(50),
    primary key (contractNo)
);

CREATE TABLE tenancyContract (
    contractNo int not null,
    startDate date,
    duration int,
    apartmentID int not null,
    additionalCosts int,
    renterID int,
    primary key(contractNo),
    foreign key (contractNo) references contract(contractNo),
    foreign key (renterID) references person(ID),
    foreign key (apartmentID) references apartment(ID)
);

CREATE TABLE purchaseContract (
    contractNo int not null,
    noOfInstallments int,
    intrestRate double,
    houseID int not null,
    ownerID int not null,
    primary key(contractNo),
    foreign key (contractNo) references contract(contractNo),
    foreign key (houseID) references house(ID),
    foreign key (ownerID) references person(ID)
);
