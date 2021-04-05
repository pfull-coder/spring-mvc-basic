
-- ½ÃÄö½º »ý¼º
CREATE SEQUENCE seq_score;

CREATE TABLE tbl_score (
    stu_num NUMBER(10),
    name VARCHAR2(50) NOT NULL,
    kor NUMBER(3) NOT NULL,
    eng NUMBER(3) NOT NULL,
    math NUMBER(3) NOT NULL,
    total NUMBER(3),
    average NUMBER(5, 2)
);

ALTER TABLE tbl_score ADD CONSTRAINT pk_score PRIMARY KEY (stu_num);

SELECT * FROM tbl_score;

INSERT INTO tbl_score VALUES(seq_score.nextval, '±èÃ¶¼ö', 90, 90, 90, 270, 90.00);
INSERT INTO tbl_score VALUES(seq_score.nextval, '¹Ú¿µÈñ', 80, 80, 80, 240, 80.00);
INSERT INTO tbl_score VALUES(seq_score.nextval, '°í±æµ¿', 70, 70, 70, 210, 70.00);
COMMIT;

