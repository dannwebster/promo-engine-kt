DROP TABLE promos IF EXISTS;

CREATE TABLE promos(
    promo_id SERIAL,
    campaign_id VARCHAR(255) not null,
    promo_guid  VARCHAR(255) not null
)