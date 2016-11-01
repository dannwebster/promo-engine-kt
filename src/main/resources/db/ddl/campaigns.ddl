DROP TABLE campaigns IF EXISTS;

CREATE TABLE campaigns(
    campaign_id VARCHAR(255) not null,
    name VARCHAR(255) not null
);