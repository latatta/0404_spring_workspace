--  ȸ���� ������ '�뿩'�ϴ� ����ó���� ���õ� ���̺�.
CREATE TABLE tbl_member(
	mem_idx number(5) PRIMARY KEY,	--ȸ����ȣ
	name varchar2(20) NOT NULL,
	email varchar2(20) NOT NULL,
	tel varchar2(20),
	password varchar2(10)
);
-- ȸ����ȣ ������
CREATE SEQUENCE memidx_seq START WITH 10001;

CREATE TABLE tbl_book(
	bcode char(5) PRIMARY KEY,
	title varchar2(30) NOT NULL,
	writer varchar2(20),
	publisher varchar2(20),
	pdate date
);

CREATE TABLE tbl_bookrent(			-- '�뿩' ���� ����
	--�⺻Ű
	rent_no number(5) PRIMARY KEY,	-- �Ϸù�ȣ
	mem_idx number(5) NOT NULL,
	bcode char(5) NOT NULL,
	rent_date DATE NOT NULL,		--���� ��¥
	exp_date DATE NOT NULL,			--�ݳ����� ��¥
	return_date DATE,				--���� �ݳ� ��¥
	delay_days number(3),			-- ��ü�ϼ�
	-- ��������
	CONSTRAINT fk1 FOREIGN KEY (mem_idx) 
			REFERENCES tbl_member(mem_idx),
	CONSTRAINT fk2 FOREIGN KEY (bcode)
			REFERENCES tbl_book(bcode)
);

-- �뿩 �Ϸù�ȣ�� ���� ������
CREATE SEQUENCE bookrent_seq;


-- ȸ�������� �߰�
INSERT INTO tbl_member (mem_idx,name,email,tel,password)
	VALUES (memidx_seq.nextval, '���ϴ�','honey@naver.com','010-9889-0567','1122');
insert into tbl_member(mem_idx,name,email,tel,password)
values(memidx_seq.nextval,'�̼���','jong@daum.net','010-2354-6773','2345');
insert into tbl_member(mem_idx,name,email,tel,password)
values(memidx_seq.nextval,'�����','lucky@korea.com','010-5467-8792','9876');
insert into tbl_member(mem_idx,name,email,tel,password)
values(memidx_seq.nextval,'���浿','nadong@kkk.net','010-3456-8765','3456');
insert into tbl_member(mem_idx,name,email,tel,password)
values(memidx_seq.nextval,'������','haha@korea.net','010-3987-9087','1234');

SELECT * FROM TBL_MEMBER tm ;

INSERT INTO TBL_BOOK (bcode,title,WRITER,PUBLISHER,PDATE)
values ('A1101','�ڽ���','Į���̰�','���̾𽺺Ͻ�',to_date('06/12/01','YY/MM/DD'));
-- ���ڿ����� ��¥Ÿ�����δ� �ڵ�ĳ���õ˴ϴ�. �Լ��� ����� ������ to_date() �Լ�����մϴ�.
-- ���� : ��¥Ÿ���� ���ڿ��� ��ȯ��Ű�� ����? to_char() �Լ�
INSERT INTO TBL_BOOK (bcode,title,WRITER,PUBLISHER,PDATE)
values ('B1101','��Ŀ������','����Ŀ','��Ŀ����',to_date('18/07/10','YY/MM/DD'));
INSERT INTO TBL_BOOK (bcode,title,WRITER,PUBLISHER,PDATE)
values ('C1101','Ǫ������ �ʹϴ�','����','â��',to_date('15/06/20','YY/MM/DD'));
INSERT INTO TBL_BOOK (bcode,title,WRITER,PUBLISHER,PDATE)
values ('A1102','�佺Ʈ','�˺���Ʈ �','������',to_date('11/03/01','YY/MM/DD'));

SELECT * FROM TBL_BOOK tb ;

-- ���ν��� ���� :  �ݳ���¥ = �뿩��¥ + 14
INSERT INTO TBL_BOOKRENT (rent_no,MEM_IDX,BCODE,RENT_DATE,EXP_DATE)
values(bookrent_seq.nextval,10001,'A1101','2021-10-11','2021-10-25');
INSERT INTO TBL_BOOKRENT (rent_no,MEM_IDX,BCODE,RENT_DATE,EXP_DATE,RETURN_DATE)
values(bookrent_seq.nextval,10001,'B1101','2021-09-01','2021-09-15','2021-09-14');
INSERT INTO TBL_BOOKRENT (rent_no,MEM_IDX,BCODE,RENT_DATE,EXP_DATE,RETURN_DATE)
values(bookrent_seq.nextval,10002,'C1101','2021-09-12','2021-09-26','2021-09-29');
INSERT INTO TBL_BOOKRENT (rent_no,MEM_IDX,BCODE,RENT_DATE,EXP_DATE)
values(bookrent_seq.nextval,10003,'A1102','2021-10-04','2021-10-18');
INSERT INTO TBL_BOOKRENT (rent_no,MEM_IDX,BCODE,RENT_DATE,EXP_DATE,RETURN_DATE)
values(bookrent_seq.nextval,10003,'B1101','2021-09-03','2021-09-17','2021-09-17');
INSERT INTO TBL_BOOKRENT (rent_no,MEM_IDX,BCODE,RENT_DATE,EXP_DATE)
values(bookrent_seq.nextval,10004,'C1101','2021-10-02','2021-10-16');

SELECT * FROM TBL_BOOKRENT tb ;

-- '�뿩' ����ó�� (���� : ȸ���� �Ϸ翡 1���� å�� �뿩�� �� �ִ�.)
--1) �뿩�Ѵ�. : insert �� rent_date ���ϰ� exp_date �� rent_date + 14
--			   �Է¸Ű������� ȸ��IDX, bcode, �뿩��¥
--2) �ݳ��Ѵ�. : �Է¸Ű������� ȸ��IDX, bcode, �ݳ���¥ 
--             �ش�Ǵ� rent_no �� ���ؼ� �� ���� �������� update return date, delay_days 

-- 2)���� ���ؼ� ���ν��� �����
SELECT rent_no ,MEM_IDX  FROM TBL_BOOKRENT tb 
	WHERE BCODE ='B1101' AND MEM_IDX = 10001
		AND RETURN_DATE IS NULL;	-- �뿩���� å �߿����Դϴ�.

UPDATE TBL_BOOKRENT SET RETURN_DATE = '2021-10-24' WHERE RENT_NO = 1;
UPDATE TBL_BOOKRENT SET DELAY_DAYS = RETURN_DATE - EXP_DATE WHERE RENT_NO =1;
--- �ٸ� �� ������ �׽�Ʈ(return_date �� �ִ� ��)
UPDATE TBL_BOOKRENT SET DELAY_DAYS = RETURN_DATE - EXP_DATE WHERE RENT_NO =5;
SELECT * FROM TBL_BOOKRENT tb ;
--------------------------------------------------------------��������� �׽�Ʈ
----****�߿�******-----
CREATE OR REPLACE PROCEDURE return_book(
	amem_idx IN tbl_member.MEM_IDX%TYPE,   -- ���ǿ����� ȸ����ȣ �ȹ޽��ϴ�. -> ���� �غ�����.
	abcode IN tbl_book.BCODE%TYPE,
	areturn_date IN tbl_bookrent.RETURN_DATE%TYPE,
	-- ��� �Ű� ������ ���� �Ǵ� ���� ���ڿ� ����.
	success OUT varchar2
)
IS 
	vrent_no NUMBER;
	v_idx NUMBER;
	cnt NUMBER;
BEGIN 
	SELECT count(*) INTO cnt
		FROM TBL_BOOKRENT tb 
		WHERE BCODE =abcode AND MEM_IDX = amem_idx AND RETURN_DATE IS NULL;
	SELECT rent_no ,MEM_IDX  INTO vrent_no , v_idx
		FROM TBL_BOOKRENT tb 
		WHERE BCODE =abcode AND MEM_IDX = amem_idx AND RETURN_DATE IS NULL;
	IF(v_idx=amem_idx AND cnt=1) THEN 
		UPDATE TBL_BOOKRENT SET RETURN_DATE = areturn_date WHERE RENT_NO = vrent_no;
		UPDATE TBL_BOOKRENT SET DELAY_DAYS = RETURN_DATE - EXP_DATE WHERE RENT_NO =vrent_no;
	END IF;
	COMMIT;	
	DBMS_OUTPUT.put_line('success ����');
	success :='success';
	EXCEPTION WHEN OTHERS THEN
		dbms_output.put_line('fail ����');
		success := 'fail';
		ROLLBACK;
END;	
SELECT * FROM TBL_BOOKRENT tb ;


-- �߿� : ���������Ҷ� ���ν��� �����ϴ� �Ű����� �Ǵ� ���������� ũ�� �����ϰ� varchar2, number ��� �����ϰ��
--		 �Ϲ� ���ν��������� ũ�⸦ �� ���ּ���. varchar2(20) , number(3) �� ���� �����մϴ�.
DECLARE
	vresult varchar2(20);
BEGIN
	return_book (10003,'A1102','2021-10-18',vresult);
	DBMS_OUTPUT.put_line('��� : ' || vresult);
END;


-- �Ű����� 2���� bcode , �ݳ� ��¥�θ� �����غ�����.
CREATE OR REPLACE PROCEDURE return_book2(
	-- amem_idx IN tbl_member.MEM_IDX%TYPE,   -- ���ǿ����� ȸ����ȣ �ȹ޽��ϴ�. -> ���� �غ�����.
	abcode IN tbl_book.BCODE%TYPE,
	areturn_date IN tbl_bookrent.RETURN_DATE%TYPE 
)
IS 
	vrent_no NUMBER;
	cnt NUMBER;
BEGIN 
	SELECT count(*) INTO cnt
		FROM TBL_BOOKRENT tb 
		WHERE BCODE =abcode AND RETURN_DATE IS NULL;
	SELECT rent_no  INTO vrent_no
		FROM TBL_BOOKRENT tb 
		WHERE BCODE =abcode AND RETURN_DATE IS NULL;
	IF(cnt=1) THEN 
		UPDATE TBL_BOOKRENT SET RETURN_DATE = areturn_date WHERE RENT_NO = vrent_no;
		UPDATE TBL_BOOKRENT SET DELAY_DAYS = RETURN_DATE - EXP_DATE WHERE RENT_NO =vrent_no;
	END IF;
	COMMIT;	
	DBMS_OUTPUT.put_line('success ����');
	EXCEPTION WHEN OTHERS THEN
		dbms_output.put_line('fail ����');
		ROLLBACK;
END;	

BEGIN
	return_book2 ('C1101','2021-10-18');
END;
SELECT * FROM TBL_BOOKRENT tb ;
--- �ٽ� �׽�Ʈ�Ҷ� : delete �� ���̺� ������ ����� ó�� insert 6�� �ٽý����ؼ� �ϼ���.
DELETE FROM TBL_BOOKRENT tb ;

