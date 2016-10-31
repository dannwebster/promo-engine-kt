DROP TABLE rules IF EXISTS;

CREATE TABLE rules(
  rule_id SERIAL,
  campaign_id VARCHAR(255) not null,
  rule_type        VARCHAR(255) not null,
  movie_id    VARCHAR(255),
  theater_id  VARCHAR(255),
  is_vip      boolean     ,
  discount    FLOAT
);