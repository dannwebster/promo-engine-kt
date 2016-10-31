DROP TABLE campaigns IF EXISTS;

CREATE TABLE campaigns(
    campaign_id SERIAL,
    name VARCHAR(255) not null
);