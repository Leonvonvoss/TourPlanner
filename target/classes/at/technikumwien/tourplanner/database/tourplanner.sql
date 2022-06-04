CREATE SCHEMA tours
    AUTHORIZATION postgres;

CREATE TABLE tours.tour
(
    tourid bigint NOT NULL,
    description character varying COLLATE pg_catalog."default",
    distance numeric NOT NULL,
    "from" character varying COLLATE pg_catalog."default" NOT NULL,
    picture character varying COLLATE pg_catalog."default",
    name character varying COLLATE pg_catalog."default" NOT NULL,
    "time" interval,
    "to" character varying COLLATE pg_catalog."default" NOT NULL,
    type character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tour_pkey PRIMARY KEY (tourid)
)

TABLESPACE pg_default;

ALTER TABLE tours.tour
    OWNER to postgres;

DROP table if exists tours.tourlog;

CREATE TABLE tours.tourlog
(
    logid bigint NOT NULL,
    tourid_fk bigint,
    date date,
    comment character varying COLLATE pg_catalog."default",
    difficulty bigint,
    totaltime interval,
    rating bigint,
    CONSTRAINT tourlog_pkey PRIMARY KEY (logid)
)

TABLESPACE pg_default;

ALTER TABLE IF Exists tours.tourlog
	ADD CONSTRAINT tourid_fk FOREIGN KEY (tourid_fk)
	REFERENCES tours.tour (tourid) MATCH SIMPLE
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
	NOT VALID;
	
ALTER TABLE IF Exists tours.tourlog
    OWNER to postgres;
	
CREATE INDEX IF NOT EXISTS tourid_fk
	ON tours.tour(tourid);