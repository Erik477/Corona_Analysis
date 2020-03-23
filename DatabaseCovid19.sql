

DROP database IF EXISTS covid19;
CREATE DATABASE covid19;
use covid19;

DROP TABLE if exists country;

create TABLE country
(
	Region varchar(64),
    Province varchar(64),
    Datee date,
    Last_Update date,
    Confirmed int,
    Deaths int,
    Recovered int
);