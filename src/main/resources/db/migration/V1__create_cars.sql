drop table if exists products;

create table products(
  id SERIAL PRIMARY KEY,
  vin VARCHAR(255) NOT NULL,
  year VARCHAR(255) NOT NULL,
  make VARCHAR(255) NOT NULL,
  price decimal NOT NULL,
  model VARCHAR(255)
);