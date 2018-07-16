
CREATE TABLE T_TP_PARTNER
(
	ID                   VARCHAR(32) NOT NULL,
	partner_name         VARCHAR(20) NULL,
	partner_begin_time   DATE NULL,
	partner_end_time     DATE NULL,
	data_time            DATE NULL,
	remark               VARCHAR(20) NULL
);

ALTER TABLE T_TP_PARTNER
ADD PRIMARY KEY (ID);

CREATE TABLE T_TP_SECRET_KEY
(
	secret_key           VARCHAR(20) NULL,
	value                VARCHAR(20) NULL,
	ID                   VARCHAR(32) NOT NULL,
	remark               VARCHAR(20) NULL,
	partner_id           VARCHAR(32) NULL
);

ALTER TABLE T_TP_SECRET_KEY
ADD PRIMARY KEY (ID);

ALTER TABLE T_TP_SECRET_KEY
ADD FOREIGN KEY R_19 (partner_id) REFERENCES T_TP_PARTNER (ID);
