drop table if exists t_user_account;
drop table if exists t_user_profile;

/*==============================================================*/
/* Table: t_user_profile                                        */
/*==============================================================*/
create table t_user_profile
(
   pro_no               int not null,
   description          varchar(255),
   primary key (pro_no)
);

INSERT INTO t_user_profile ( pro_no, description ) VALUES ( 1, 'Administrator'); 
INSERT INTO t_user_profile ( pro_no, description ) VALUES ( 2, 'cafe owner'); 
INSERT INTO t_user_profile ( pro_no, description ) VALUES ( 3, 'manager'); 
INSERT INTO t_user_profile ( pro_no, description ) VALUES ( 4, 'staff'); 


/*==============================================================*/
/* Table: t_user_account                                        */
/*==============================================================*/
CREATE TABLE t_user_account (
   accountNo            VARCHAR(255),
   t_u_pro_no           INT,
   password             VARCHAR(255) NOT NULL,
   name                 VARCHAR(255) NOT NULL,
   status               BOOLEAN DEFAULT TRUE,
   PRIMARY KEY (accountNo)
);

alter table t_user_account add constraint FK_Reference_1 foreign key (t_u_pro_no)
      references t_user_profile (pro_no) on delete restrict on update restrict;

INSERT INTO t_user_account ( accountNo, password,name,t_u_pro_no) VALUES ( 'admin', '123456','jack',1); 
INSERT INTO t_user_account ( accountNo, password,name,t_u_pro_no) VALUES ( 'owner1', '123456','smith',2); 
INSERT INTO t_user_account ( accountNo, password,name,t_u_pro_no) VALUES ( 'manage1', '123456','tony',3); 
INSERT INTO t_user_account ( accountNo, password,name,t_u_pro_no) VALUES ( 'staff1', '123456','tom',4); 





