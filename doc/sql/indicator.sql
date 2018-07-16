
CREATE TABLE t_ind_expression
(
	ID                   VARCHAR(32) NOT NULL,
	expr_code            VARCHAR(32) NOT NULL
);

ALTER TABLE t_ind_expression
ADD PRIMARY KEY (ID);

CREATE TABLE t_ind_expression_code
(
	ID                   VARCHAR(32) NOT NULL,
	code                 INTEGER NULL,
	remark               VARCHAR(20) NULL
);

ALTER TABLE t_ind_expression_code
ADD PRIMARY KEY (ID);

CREATE TABLE t_ind_indicator_classification
(
	ID                   VARCHAR(32) NOT NULL,
	clazz                VARCHAR(50) NULL,
	remark               VARCHAR(30) NULL,
	data_time            TIMESTAMP(6) NULL
);

ALTER TABLE t_ind_indicator_classification
ADD PRIMARY KEY (ID);

CREATE TABLE t_ind_indicator_library
(
	ID                   VARCHAR(32) NOT NULL,
	fun_name             VARCHAR(30) NULL,
	fun_expr             VARCHAR(30) NULL,
	data_time            TIMESTAMP(6) NULL,
	clazz_id             VARCHAR(32) NOT NULL
);

ALTER TABLE t_ind_indicator_library
ADD PRIMARY KEY (ID);

CREATE TABLE t_ind_indicator_where
(
	ID                   VARCHAR(32) NOT NULL,
	value                VARCHAR(20) NULL,
	expr_id              VARCHAR(32) NOT NULL,
	priority             INTEGER NULL,
	where_code           INTEGER NOT NULL,
	library_id           VARCHAR(32) NOT NULL
);

ALTER TABLE t_ind_indicator_where
ADD PRIMARY KEY (ID);

CREATE TABLE t_ind_where_code
(
	code                 INTEGER NOT NULL,
	remark               VARCHAR(20) NULL
);

ALTER TABLE t_ind_where_code
ADD PRIMARY KEY (code);

ALTER TABLE t_ind_expression
ADD FOREIGN KEY R_26 (expr_code) REFERENCES t_ind_expression_code (ID);

ALTER TABLE t_ind_indicator_library
ADD FOREIGN KEY R_27 (clazz_id) REFERENCES t_ind_indicator_classification (ID);

ALTER TABLE t_ind_indicator_where
ADD FOREIGN KEY R_22 (where_code) REFERENCES t_ind_where_code (code);

ALTER TABLE t_ind_indicator_where
ADD FOREIGN KEY R_25 (expr_id) REFERENCES t_ind_expression (ID);

ALTER TABLE t_ind_indicator_where
ADD FOREIGN KEY R_29 (library_id) REFERENCES t_ind_indicator_library (ID);
