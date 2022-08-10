create table member (
    id bigint not null auto_increment,
    created_date datetime,
    modified_date datetime,
    email varchar(255) not null,
    name varchar(255) not null,
    password varchar(255) not null,
    role varchar(255) not null,
    primary key (id)
                    )

create table price_log (
    price_id bigint not null auto_increment,
    created_date datetime,
    barcode_id bigint,
    name varchar(255),
    updated_price bigint,
    primary key (price_id)
                       )

create table products (
    id bigint not null auto_increment,
    created_date datetime,
    modified_date datetime,
    barcode_id bigint not null,
    brand varchar(255) not null,
    price bigint not null,
    product_name varchar(500) not null,
    store varchar(255) not null,
    unit varchar(255) not null,
    volume_height varchar(255),
    volume_long varchar(255),
    volume_short varchar(255),
    weight varchar(255) not null,
    primary key (id)
                      )




create table stock_log (
    stock_id bigint not null auto_increment,
    created_date datetime, barcode_id bigint,
    in_stock integer,
    name varchar(255),
    stock_add integer,
    stock_sub integer, primary key (stock_id)
                       )