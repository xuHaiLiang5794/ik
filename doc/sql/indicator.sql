
CREATE TABLE t_ind_expression
(
	ID                   CHAR(18) NOT NULL,
	expr_code            CHAR(18) NOT NULL
);

ALTER TABLE t_ind_expression
ADD PRIMARY KEY (ID);

CREATE TABLE t_ind_expression_code
(
	ID                   CHAR(18) NOT NULL,
	code                 INTEGER NULL,
	remark               VARCHAR(20) NULL
);

ALTER TABLE t_ind_expression_code
ADD PRIMARY KEY (ID);

CREATE TABLE t_ind_indicator_classification
(
	ID                   CHAR(18) NOT NULL,
	clazz                VARCHAR(20) NULL,
	remark               VARCHAR(20) NULL,
	data_time            DATE NULL
);

ALTER TABLE t_ind_indicator_classification
ADD PRIMARY KEY (ID);

CREATE TABLE t_ind_indicator_library
(
	ID                   CHAR(18) NOT NULL,
	fun_name             VARCHAR(20) NULL,
	fun_expr             VARCHAR(20) NULL,
	data_time            DATE NULL,
	clazz_id             CHAR(18) NOT NULL,
	where_id             CHAR(18) NOT NULL
);

ALTER TABLE t_ind_indicator_library
ADD PRIMARY KEY (ID);

CREATE TABLE t_ind_indicator_where
(
	ID                   CHAR(18) NOT NULL,
	value                VARCHAR(20) NULL,
	expr_id              VARCHAR(20) NOT NULL,
	priority             INTEGER NULL,
	where_code           INTEGER NOT NULL
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

ALTER TABLE t_ind_indicator_library
ADD FOREIGN KEY R_28 (where_id) REFERENCES t_ind_indicator_where (ID);

ALTER TABLE t_ind_indicator_where
ADD FOREIGN KEY R_22 (where_code) REFERENCES t_ind_where_code (code);

ALTER TABLE t_ind_indicator_where
ADD FOREIGN KEY R_25 (expr_id) REFERENCES t_ind_expression (ID);
