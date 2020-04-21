insert into CITY (ID, NAME, GOOGLE_PLACE_CITY_ID, STATE) values (1000, 'São Leopoldo', 'place-id-sao-leopoldo', 'RS');
insert into ADDRESS (ID, CITY_ID) values (1000, 1000);
insert into USER_VOLUNTEER (ID, NAME, EMAIL, PASSWORD, CREATED_AT, ADDRESS_ID) values (1000, 'Volunteer', 'voluntario@ajuda.ai', '$2a$10$qCjgCT1abANB9e2hqV8rsuOffqCaQy37rCTG3GpnZ1NKggEGPZIjC', '2020-04-10', 1000);
insert into USER_GROUP (ID, NAME, EMAIL, PASSWORD, CREATED_AT, ADDRESS_ID) values (1001, 'Group', 'grupo@ajuda.ai', '$2a$10$qCjgCT1abANB9e2hqV8rsuOffqCaQy37rCTG3GpnZ1NKggEGPZIjC', '2020-04-10', 1000);
insert into USER_ORGANIZATION (ID, NAME, EMAIL, PASSWORD, CREATED_AT, ADDRESS_ID) values (1002, 'Organization', 'ong@ajuda.ai', '$2a$10$qCjgCT1abANB9e2hqV8rsuOffqCaQy37rCTG3GpnZ1NKggEGPZIjC', '2020-04-10', 1000);
