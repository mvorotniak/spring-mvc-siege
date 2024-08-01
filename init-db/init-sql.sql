create table persons (
    id bigserial primary key,
    -- name varchar(255) unique -- correct definition
    name varchar(255) -- not correct definition for testing
)