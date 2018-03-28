--
-- SEQ_HMNDEP_ARTIFACT_HIST  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_ARTIFACT_HIST
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_ARTIFACTS  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_ARTIFACTS
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  CYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_DAO_DEPENDENCIES  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_DAO_DEPENDENCIES
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_EXECUTIONS  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_EXECUTIONS
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_FE_DEPENDENCIES  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_FE_DEPENDENCIES
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  CYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_FE_DEPENDENCY_HIST  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_FE_DEPENDENCY_HIST
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  CYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_HBM_TABLES  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_HBM_TABLES
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_NQ_DEPENDENCIES  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_NQ_DEPENDENCIES
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  CYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_NQ_DEPENDENCY_HIST  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_NQ_DEPENDENCY_HIST
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_PARAMETERS  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_PARAMETERS
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_PLSQL_DEPENDENCIES  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_PLSQL_DEPENDENCIES
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  CYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_PLSQL_DEPNDNCY_HIST  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_PLSQL_DEPNDNCY_HIST
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_SERVICE_CLASSES  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_SERVICE_CLASSES
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  CYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_SERVICE_CLASS_HIST  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_SERVICE_CLASS_HIST
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_SERVICE_DEPENDENCY  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_SERVICE_DEPENDENCY
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  CYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_SERVICE_DEPEND_HIST  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_SERVICE_DEPEND_HIST
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_SERVICE_INTERFACES  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_SERVICE_INTERFACES
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  CYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_SERVICE_INTERF_HIST  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_SERVICE_INTERF_HIST
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_SERVICE_METHODS  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_SERVICE_METHODS
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  CYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_SERVICE_SUPERCLASS  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_SERVICE_SUPERCLASS
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  CYCLE
  CACHE 20
  NOORDER
/


--
-- SEQ_HMNDEP_SERVICE_SUPERCL_HST  (Sequence) 
--
CREATE SEQUENCE SEQ_HMNDEP_SERVICE_SUPERCL_HST
  START WITH 1
  MAXVALUE 9999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER
/


--
-- HMNDEP_ARTIFACT_HIST  (Table) 
--
CREATE TABLE HMNDEP_ARTIFACT_HIST
(
  ID             NUMBER(16)                     NOT NULL,
  EXECUTION_ID   NUMBER(16)                     NOT NULL,
  FILE_NAME		 VARCHAR2(500 BYTE)             NOT NULL,
  ARTIFACT_TYPE  VARCHAR2(2 BYTE),
  GROUP_ID  	 VARCHAR2(500),
  ARTIFACT_ID    VARCHAR2(500),
  ARTIFACT_VERSION  VARCHAR2(100),
  ARTIFACT_DESC  VARCHAR2(1000)
)
/


--
-- HMNDEP_ARTIFACTS  (Table) 
--
CREATE TABLE HMNDEP_ARTIFACTS
(
  ID             NUMBER(16)                     NOT NULL,
  EXECUTION_ID   NUMBER(16)                     NOT NULL,
  FILE_NAME		 VARCHAR2(500 BYTE)             NOT NULL,
  ARTIFACT_TYPE  VARCHAR2(2 BYTE),
  GROUP_ID  	 VARCHAR2(500),
  ARTIFACT_ID    VARCHAR2(500),
  ARTIFACT_VERSION  VARCHAR2(100),
  ARTIFACT_DESC  VARCHAR2(1000)
)
/


--
-- HMNDEP_DAO_DEPENDENCIES  (Table) 
--
CREATE TABLE HMNDEP_DAO_DEPENDENCIES
(
  ID                NUMBER(16)                  NOT NULL,
  EXECUTION_ID      NUMBER(16)                  NOT NULL,
  SERVICE_CLASS     VARCHAR2(500 BYTE)          NOT NULL,
  SERVICE_METHOD    VARCHAR2(500 BYTE)          NOT NULL,
  DAO_CLASS         VARCHAR2(500 BYTE)          NOT NULL,
  DAO_METHOD        VARCHAR2(500 BYTE)          NOT NULL,
  DEPENDENCY_LEVEL  NUMBER(2)                   NOT NULL,
  DEPENDENCY_PATH   VARCHAR2(3000 BYTE)         NOT NULL,
  CRUD_FLAG         VARCHAR2(1 BYTE)            NOT NULL
)
/


--
-- HMNDEP_EXECUTIONS  (Table) 
--
CREATE TABLE HMNDEP_EXECUTIONS
(
  ID              NUMBER(16)                    NOT NULL,
  EXECUTION_DATE  DATE                          NOT NULL,
  HOST_COMPUTER   VARCHAR2(100 BYTE),
  FINISH_DATE     DATE
)
/


--
-- HMNDEP_FE_DEPENDENCIES  (Table) 
--
CREATE TABLE HMNDEP_FE_DEPENDENCIES
(
  ID                NUMBER(16)                  NOT NULL,
  ARTIFACT_ID       NUMBER(16)                  NOT NULL,
  SOURCE_CLASS      VARCHAR2(500 BYTE)          NOT NULL,
  SOURCE_METHOD     VARCHAR2(500 BYTE)          NOT NULL,
  SOURCE_SIGNATURE  VARCHAR2(3000 BYTE)         NOT NULL,
  TARGET_SERVICE    VARCHAR2(500 BYTE)          NOT NULL,
  TARGET_METHOD     VARCHAR2(500 BYTE)          NOT NULL,
  TARGET_SIGNATURE  VARCHAR2(3000 BYTE)         NOT NULL,
  EXECUTION_ID      NUMBER(16)                  NOT NULL
)
/


--
-- HMNDEP_FE_DEPENDENCY_HIST  (Table) 
--
CREATE TABLE HMNDEP_FE_DEPENDENCY_HIST
(
  ID                NUMBER(16)                  NOT NULL,
  EXECUTION_ID      NUMBER(16)                  NOT NULL,
  ARTIFACT_ID       NUMBER(16)                  NOT NULL,
  SOURCE_CLASS      VARCHAR2(500 BYTE)          NOT NULL,
  SOURCE_METHOD     VARCHAR2(500 BYTE)          NOT NULL,
  SOURCE_SIGNATURE  VARCHAR2(3000 BYTE)         NOT NULL,
  TARGET_SERVICE    VARCHAR2(500 BYTE)          NOT NULL,
  TARGET_METHOD     VARCHAR2(500 BYTE)          NOT NULL,
  TARGET_SIGNATURE  VARCHAR2(3000 BYTE)         NOT NULL
)
/


--
-- HMNDEP_HBM_TABLES  (Table) 
--
CREATE TABLE HMNDEP_HBM_TABLES
(
  ID            NUMBER(16)                      NOT NULL,
  EXECUTION_ID  NUMBER(16)                      NOT NULL,
  ARTIFACT_ID   NUMBER(16)                      NOT NULL,
  DAO_CLASS     VARCHAR2(500 BYTE)              NOT NULL,
  MODEL_CLASS   VARCHAR2(500 BYTE)              NOT NULL,
  TABLE_NAME    VARCHAR2(500 BYTE)              NOT NULL,
  DATASOURCE    VARCHAR2(100 BYTE)              NOT NULL,
  HBM_FILE      VARCHAR2(500 BYTE)              NOT NULL
)
/


--
-- HMNDEP_NQ_DEPENDENCIES  (Table) 
--
CREATE TABLE HMNDEP_NQ_DEPENDENCIES
(
  ID                NUMBER(16)                  NOT NULL,
  SERVICE_CLASS     VARCHAR2(500 BYTE)          NOT NULL,
  SERVICE_METHOD    VARCHAR2(500 BYTE)          NOT NULL,
  QUERY_NAME        VARCHAR2(500 BYTE),
  DEPENDENCY_LEVEL  NUMBER(2)                   NOT NULL,
  DEPENDENCY_PATH   VARCHAR2(3000 BYTE),
  EXECUTION_ID      NUMBER(16)                  NOT NULL
)
/


--
-- HMNDEP_NQ_DEPENDENCY_HIST  (Table) 
--
CREATE TABLE HMNDEP_NQ_DEPENDENCY_HIST
(
  ID                NUMBER(16)                  NOT NULL,
  EXECUTION_ID      NUMBER(16)                  NOT NULL,
  SERVICE_CLASS     VARCHAR2(500 BYTE)          NOT NULL,
  SERVICE_METHOD    VARCHAR2(500 BYTE)          NOT NULL,
  QUERY_NAME        VARCHAR2(500 BYTE),
  DEPENDENCY_LEVEL  NUMBER(2)                   NOT NULL,
  DEPENDENCY_PATH   VARCHAR2(3000 BYTE)
)
/


--
-- HMNDEP_PARAMETERS  (Table) 
--
CREATE TABLE HMNDEP_PARAMETERS
(
  ID           NUMBER(16)                       NOT NULL,
  PARAM_NAME   VARCHAR2(500 BYTE)               NOT NULL,
  PARAM_VALUE  VARCHAR2(1000 BYTE)              NOT NULL
)
/


--
-- HMNDEP_PLSQL_DEPENDENCIES  (Table) 
--
CREATE TABLE HMNDEP_PLSQL_DEPENDENCIES
(
  ID                NUMBER(16)                  NOT NULL,
  SERVICE_CLASS     VARCHAR2(500 BYTE)          NOT NULL,
  SERVICE_METHOD    VARCHAR2(500 BYTE)          NOT NULL,
  PACKAGE_NAME      VARCHAR2(500 BYTE),
  PROC_NAME         VARCHAR2(500 BYTE),
  DEPENDENCY_LEVEL  NUMBER(2)                   NOT NULL,
  DEPENDENCY_PATH   VARCHAR2(3000 BYTE),
  EXECUTION_ID      NUMBER(16)                  NOT NULL
)
/


--
-- HMNDEP_PLSQL_DEPENDENCY_HIST  (Table) 
--
CREATE TABLE HMNDEP_PLSQL_DEPENDENCY_HIST
(
  ID                NUMBER(16)                  NOT NULL,
  EXECUTION_ID      NUMBER(16)                  NOT NULL,
  SERVICE_CLASS     VARCHAR2(500 BYTE)          NOT NULL,
  SERVICE_METHOD    VARCHAR2(500 BYTE)          NOT NULL,
  PACKAGE_NAME      VARCHAR2(500 BYTE),
  PROC_NAME         VARCHAR2(500 BYTE),
  DEPENDENCY_LEVEL  NUMBER(2)                   NOT NULL,
  DEPENDENCY_PATH   VARCHAR2(3000 BYTE)
)
/


--
-- HMNDEP_SERVICE_CLASSES  (Table) 
--
CREATE TABLE HMNDEP_SERVICE_CLASSES
(
  ID            NUMBER(16)                      NOT NULL,
  CLASS_NAME    VARCHAR2(500 BYTE)              NOT NULL,
  ARTIFACT_ID   NUMBER(16)                      NOT NULL,
  EXECUTION_ID  NUMBER(16)                      NOT NULL
)
/


--
-- HMNDEP_SERVICE_CLASSES_HIST  (Table) 
--
CREATE TABLE HMNDEP_SERVICE_CLASSES_HIST
(
  ID            NUMBER(16)                      NOT NULL,
  EXECUTION_ID  NUMBER(16)                      NOT NULL,
  CLASS_NAME    VARCHAR2(500 BYTE)              NOT NULL,
  ARTIFACT_ID   NUMBER(16)                      NOT NULL
)
/


--
-- HMNDEP_SERVICE_DEPENDENCIES  (Table) 
--
CREATE TABLE HMNDEP_SERVICE_DEPENDENCIES
(
  ID                NUMBER(16)                  NOT NULL,
  EXECUTION_ID      NUMBER(16),
  ARTIFACT_ID       NUMBER(16)                  NOT NULL,
  CALLER_CLASS      VARCHAR2(500 BYTE)          NOT NULL,
  CALLER_METHOD     VARCHAR2(500 BYTE)          NOT NULL,
  CALLER_SIGNATURE  VARCHAR2(3000 BYTE),
  TARGET_SERVICE    VARCHAR2(500 BYTE)          NOT NULL,
  TARGET_METHOD     VARCHAR2(500 BYTE)          NOT NULL,
  TARGET_SIGNATURE  VARCHAR2(3000 BYTE)         NOT NULL,
  DEPENDENCY_LEVEL  NUMBER(2)                   NOT NULL,
  DEPENDENCY_PATH   VARCHAR2(3000 BYTE)
)
/


--
-- HMNDEP_SERVICE_DEPENDENCY_HIST  (Table) 
--
CREATE TABLE HMNDEP_SERVICE_DEPENDENCY_HIST
(
  ID                NUMBER(16)                  NOT NULL,
  EXECUTION_ID      NUMBER(16),
  ARTIFACT_ID       NUMBER(16)                  NOT NULL,
  CALLER_CLASS      VARCHAR2(500 BYTE)          NOT NULL,
  CALLER_METHOD     VARCHAR2(500 BYTE)          NOT NULL,
  CALLER_SIGNATURE  VARCHAR2(3000 BYTE),
  TARGET_SERVICE    VARCHAR2(500 BYTE)          NOT NULL,
  TARGET_METHOD     VARCHAR2(500 BYTE)          NOT NULL,
  TARGET_SIGNATURE  VARCHAR2(3000 BYTE)         NOT NULL,
  DEPENDENCY_LEVEL  NUMBER(2)                   NOT NULL,
  DEPENDENCY_PATH   VARCHAR2(3000 BYTE)
)
/


--
-- HMNDEP_SERVICE_INTERFACE_HIST  (Table) 
--
CREATE TABLE HMNDEP_SERVICE_INTERFACE_HIST
(
  ID            NUMBER(16)                      NOT NULL,
  EXECUTION_ID  NUMBER(16)                      NOT NULL,
  CLASS_NAME    VARCHAR2(500 BYTE)              NOT NULL,
  IMPLEMENTS    VARCHAR2(500 BYTE)              NOT NULL
)
/


--
-- HMNDEP_SERVICE_INTERFACES  (Table) 
--
CREATE TABLE HMNDEP_SERVICE_INTERFACES
(
  ID            NUMBER(16)                      NOT NULL,
  CLASS_NAME    VARCHAR2(500 BYTE)              NOT NULL,
  IMPLEMENTS    VARCHAR2(500 BYTE)              NOT NULL,
  EXECUTION_ID  NUMBER(16)                      NOT NULL
)
/


--
-- HMNDEP_SERVICE_METHODS  (Table) 
--
CREATE TABLE HMNDEP_SERVICE_METHODS
(
  ID                           NUMBER(16)       NOT NULL,
  SERVICE_CLASS_ID             NUMBER(16)       NOT NULL,
  INTERFACE_NAME               VARCHAR2(500)   NOT NULL,
  METHOD_NAME                  VARCHAR2(500)    NOT NULL,
  METHOD_SIGNATURE             VARCHAR2(3000)    NOT NULL,
  COMPILED_SIGNATURE           VARCHAR2(3000),
  THROWS_HMNSERVICE_EXCEPTION  VARCHAR2(1)
)
/

--
-- HMNDEP_SERVICE_SUPERCLASSES  (Table) 
--
CREATE TABLE HMNDEP_SERVICE_SUPERCLASSES
(
  ID            NUMBER(16)                      NOT NULL,
  CLASS_NAME    VARCHAR2(500 BYTE)              NOT NULL,
  SUPER_CLASS   VARCHAR2(500 BYTE)              NOT NULL,
  EXECUTION_ID  NUMBER(16)                      NOT NULL
)
/


--
-- HMNDEP_SERVICE_SUPERCLASS_HIST  (Table) 
--
CREATE TABLE HMNDEP_SERVICE_SUPERCLASS_HIST
(
  ID            NUMBER(16)                      NOT NULL,
  EXECUTION_ID  NUMBER(16)                      NOT NULL,
  CLASS_NAME    VARCHAR2(500 BYTE)              NOT NULL,
  SUPER_CLASS   VARCHAR2(500 BYTE)              NOT NULL
)
/


--
-- IDX01_HMNDEP_FE_DPND_HIST  (Index) 
--
CREATE INDEX IDX01_HMNDEP_FE_DPND_HIST ON HMNDEP_FE_DEPENDENCY_HIST
(EXECUTION_ID)
/


--
-- IDX1_HMNDEP_NQ_DEPENDENCY_HIST  (Index) 
--
CREATE INDEX IDX1_HMNDEP_NQ_DEPENDENCY_HIST ON HMNDEP_NQ_DEPENDENCY_HIST
(EXECUTION_ID)
/


--
-- IDX1_HMNDEP_PLSQL_DEPENDEN_HST  (Index) 
--
CREATE INDEX IDX1_HMNDEP_PLSQL_DEPENDEN_HST ON HMNDEP_PLSQL_DEPENDENCY_HIST
(EXECUTION_ID)
/


--
-- IDX1_HMNDEP_SVC_DEPENDENCY_HST  (Index) 
--
CREATE INDEX IDX1_HMNDEP_SVC_DEPENDENCY_HST ON HMNDEP_SERVICE_DEPENDENCY_HIST
(EXECUTION_ID)
/


--
-- PK_HMNDEP_ARTIFACT_HIST  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_ARTIFACT_HIST ON HMNDEP_ARTIFACT_HIST
(ID)
/


--
-- PK_HMNDEP_ARTIFACTS  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_ARTIFACTS ON HMNDEP_ARTIFACTS
(ID)
/


--
-- PK_HMNDEP_DAO_DEPENDENCIES  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_DAO_DEPENDENCIES ON HMNDEP_DAO_DEPENDENCIES
(ID)
/


--
-- PK_HMNDEP_EXECUTIONS  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_EXECUTIONS ON HMNDEP_EXECUTIONS
(ID)
/


--
-- PK_HMNDEP_FE_DEPENDENCIES  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_FE_DEPENDENCIES ON HMNDEP_FE_DEPENDENCIES
(ID)
/


--
-- PK_HMNDEP_FE_DEPENDENCY_HIST  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_FE_DEPENDENCY_HIST ON HMNDEP_FE_DEPENDENCY_HIST
(ID)
/


--
-- PK_HMNDEP_HBM_TABLES  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_HBM_TABLES ON HMNDEP_HBM_TABLES
(ID)
/


--
-- PK_HMNDEP_NQ_DEPENDENCIES  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_NQ_DEPENDENCIES ON HMNDEP_NQ_DEPENDENCIES
(ID)
/


--
-- PK_HMNDEP_NQ_DEPENDENCY_HIST  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_NQ_DEPENDENCY_HIST ON HMNDEP_NQ_DEPENDENCY_HIST
(ID)
/


--
-- PK_HMNDEP_PARAMETERS  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_PARAMETERS ON HMNDEP_PARAMETERS
(ID)
/


--
-- PK_HMNDEP_PLSQL_DEPENDENCIES  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_PLSQL_DEPENDENCIES ON HMNDEP_PLSQL_DEPENDENCIES
(ID)
/


--
-- PK_HMNDEP_PLSQL_DPNDNCY_HIST  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_PLSQL_DPNDNCY_HIST ON HMNDEP_PLSQL_DEPENDENCY_HIST
(ID)
/


--
-- PK_HMNDEP_SERVICE_CLASSES  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_SERVICE_CLASSES ON HMNDEP_SERVICE_CLASSES
(ID)
/


--
-- PK_HMNDEP_SERVICE_CLASSES_HIST  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_SERVICE_CLASSES_HIST ON HMNDEP_SERVICE_CLASSES_HIST
(ID)
/


--
-- PK_HMNDEP_SERVICE_DEPENDENCIES  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_SERVICE_DEPENDENCIES ON HMNDEP_SERVICE_DEPENDENCIES
(ID)
/


--
-- PK_HMNDEP_SERVICE_DEPEND_HIST  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_SERVICE_DEPEND_HIST ON HMNDEP_SERVICE_DEPENDENCY_HIST
(ID)
/


--
-- PK_HMNDEP_SERVICE_INTERFACES  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_SERVICE_INTERFACES ON HMNDEP_SERVICE_INTERFACES
(ID)
/


--
-- PK_HMNDEP_SERVICE_INTERF_HIST  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_SERVICE_INTERF_HIST ON HMNDEP_SERVICE_INTERFACE_HIST
(ID)
/


--
-- PK_HMNDEP_SERVICE_METHODS  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_SERVICE_METHODS ON HMNDEP_SERVICE_METHODS
(ID)
/


--
-- PK_HMNDEP_SERVICE_SUPERCLASSES  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_SERVICE_SUPERCLASSES ON HMNDEP_SERVICE_SUPERCLASSES
(ID)
/


--
-- PK_HMNDEP_SERVICE_SUPERCLS_HST  (Index) 
--
CREATE UNIQUE INDEX PK_HMNDEP_SERVICE_SUPERCLS_HST ON HMNDEP_SERVICE_SUPERCLASS_HIST
(ID)
/


--
-- UC_HMNDEP_PARAMETERS_01  (Index) 
--
CREATE UNIQUE INDEX UC_HMNDEP_PARAMETERS_01 ON HMNDEP_PARAMETERS
(PARAM_NAME)
/


--
-- UC01_HMNDEP_SERVICE_INTERFACES  (Index) 
--
CREATE UNIQUE INDEX UC01_HMNDEP_SERVICE_INTERFACES ON HMNDEP_SERVICE_INTERFACES
(CLASS_NAME, IMPLEMENTS)
/


--
-- UC01_HMNDEP_SERVICE_SUPERCLASS  (Index) 
--
CREATE UNIQUE INDEX UC01_HMNDEP_SERVICE_SUPERCLASS ON HMNDEP_SERVICE_SUPERCLASSES
(CLASS_NAME, SUPER_CLASS)
/


--
-- UIX_HMNDEP_ARTIFACTS_01  (Index) 
--
CREATE UNIQUE INDEX UIX_HMNDEP_ARTIFACTS_01 ON HMNDEP_ARTIFACTS
(ARTIFACT_NAME)
/


--
-- TRG_HMNDEP_ARTIFACTS_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_ARTIFACTS_BI
 before insert ON HMNDEP_ARTIFACTS FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_ARTIFACTS.NEXTVAL;
END TRG_HMNDEP_ARTIFACTS_BI;
/


--
-- TRG_HMNDEP_ARTIFACT_HIST_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_ARTIFACT_HIST_BI
BEFORE INSERT
   ON HMNDEP_ARTIFACT_HIST
FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_ARTIFACT_HIST.NEXTVAL;
END TRG_HMNDEP_ARTIFACT_HIST_BI;
/


--
-- TRG_HMNDEP_DAO_DEPENDENCIES_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_DAO_DEPENDENCIES_BI
BEFORE INSERT
   ON HMNDEP_DAO_DEPENDENCIES
FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_DAO_DEPENDENCIES.NEXTVAL;
END TRG_HMNDEP_DAO_DEPENDENCIES_BI;
/


--
-- TRG_HMNDEP_EXECUTIONS_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_EXECUTIONS_BI
 before insert ON HMNDEP_EXECUTIONS FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_EXECUTIONS.NEXTVAL;
END TRG_HMNDEP_EXECUTIONS_BI;
/


--
-- TRG_HMNDEP_FE_DEPENDENCIES_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_FE_DEPENDENCIES_BI
 before insert ON HMNDEP_FE_DEPENDENCIES FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_FE_DEPENDENCIES.NEXTVAL;
END TRG_HMNDEP_FE_DEPENDENCIES_BI;
/


--
-- TRG_HMNDEP_FE_DEP_HIST_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_FE_DEP_HIST_BI
 before insert ON HMNDEP_FE_DEPENDENCY_HIST FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_FE_DEPENDENCY_HIST.NEXTVAL;
END TRG_HMNDEP_FE_DEP_HIST_BI;
/


--
-- TRG_HMNDEP_HBM_TABLES_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_HBM_TABLES_BI
BEFORE INSERT
   ON HMNDEP_HBM_TABLES
FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_HBM_TABLES.NEXTVAL;
END TRG_HMNDEP_HBM_TABLES_BI;
/


--
-- TRG_HMNDEP_NQ_DEPENDENCIES_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_NQ_DEPENDENCIES_BI
 before insert ON HMNDEP_NQ_DEPENDENCIES FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_NQ_DEPENDENCIES.NEXTVAL;
END TRG_HMNDEP_NQ_DEPENDENCIES_BI;
/


--
-- TRG_HMNDEP_NQ_DEPEND_HIST_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_NQ_DEPEND_HIST_BI
BEFORE INSERT
   ON HMNDEP_NQ_DEPENDENCY_HIST
FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_NQ_DEPENDENCY_HIST.NEXTVAL;
END TRG_HMNDEP_NQ_DEPEND_HIST_BI;
/


--
-- TRG_HMNDEP_PARAMETERS_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_PARAMETERS_BI
 BEFORE INSERT ON HMNDEP_PARAMETERS FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_PARAMETERS.NEXTVAL;
END TRG_HMNDEP_PARAMETERS_BI;
/


--
-- TRG_HMNDEP_PLSQL_DEPENDENCY_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_PLSQL_DEPENDENCY_BI
 before insert ON HMNDEP_PLSQL_DEPENDENCIES FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_PLSQL_DEPENDENCIES.NEXTVAL;
END TRG_HMNDEP_PLSQL_DEPENDENCY_BI;
/


--
-- TRG_HMNDEP_PLSQL_DEPND_HIST_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_PLSQL_DEPND_HIST_BI
BEFORE INSERT
   ON HMNDEP_PLSQL_DEPENDENCY_HIST
FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_PLSQL_DEPNDNCY_HIST.NEXTVAL;
END TRG_HMNDEP_PLSQL_DEPND_HIST_BI;
/


--
-- TRG_HMNDEP_SERVICE_CLASSES_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_SERVICE_CLASSES_BI
 before insert ON HMNDEP_SERVICE_CLASSES FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_SERVICE_CLASSES.NEXTVAL;
END TRG_HMNDEP_SERVICE_CLASSES_BI;
/


--
-- TRG_HMNDEP_SERVICE_CLS_HIST_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_SERVICE_CLS_HIST_BI
BEFORE INSERT
   ON  HMNDEP_SERVICE_CLASSES_HIST
FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_SERVICE_CLASS_HIST.NEXTVAL;
END TRG_HMNDEP_SERVICE_CLS_HIST_BI;
/


--
-- TRG_HMNDEP_SERVICE_DEPENDCY_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_SERVICE_DEPENDCY_BI
 before insert ON HMNDEP_SERVICE_DEPENDENCIES FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_SERVICE_DEPENDENCY.NEXTVAL;
END TRG_HMNDEP_SERVICE_DEPENDCY_BI;
/


--
-- TRG_HMNDEP_SERVICE_DEP_HIST_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_SERVICE_DEP_HIST_BI
 before insert ON HMNDEP_SERVICE_DEPENDENCY_HIST FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_SERVICE_DEPEND_HIST.NEXTVAL;
END TRG_HMNDEP_SERVICE_DEP_HIST_BI;
/


--
-- TRG_HMNDEP_SERVICE_INTERF_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_SERVICE_INTERF_BI
 before insert ON HMNDEP_SERVICE_INTERFACES FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_SERVICE_INTERFACES.NEXTVAL;
END TRG_HMNDEP_SERVICE_INTERF_BI;
/


--
-- TRG_HMNDEP_SERVICE_INT_HIST_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_SERVICE_INT_HIST_BI
BEFORE INSERT
   ON HMNDEP_SERVICE_INTERFACE_HIST
FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_SERVICE_INTERF_HIST.NEXTVAL;
END TRG_HMNDEP_SERVICE_INT_HIST_BI;
/


--
-- TRG_HMNDEP_SERVICE_METHODS_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_SERVICE_METHODS_BI
 before insert ON HMNDEP_SERVICE_METHODS FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_SERVICE_METHODS.NEXTVAL;
END TRG_HMNDEP_SERVICE_METHODS_BI;
/


--
-- TRG_HMNDEP_SERVICE_SUPERCLS_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_SERVICE_SUPERCLS_BI
 before insert ON HMNDEP_SERVICE_SUPERCLASSES FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_SERVICE_SUPERCLASS.NEXTVAL;
END TRG_HMNDEP_SERVICE_SUPERCLS_BI;
/


--
-- TRG_HMNDEP_SERVIS_SPRCL_HST_BI  (Trigger) 
--
CREATE OR REPLACE TRIGGER TRG_HMNDEP_SERVIS_SPRCL_HST_BI
BEFORE INSERT
   ON HMNDEP_SERVICE_SUPERCLASS_HIST
FOR EACH ROW
WHEN (
NEW.ID IS NULL OR NEW.ID = 0
      )
BEGIN
   :NEW.ID := SEQ_HMNDEP_SERVICE_SUPERCL_HST.NEXTVAL;
END TRG_HMNDEP_SERVIS_SPRCL_HST_BI;
/


-- 
-- Non Foreign Key Constraints for Table HMNDEP_ARTIFACT_HIST 
-- 
ALTER TABLE HMNDEP_ARTIFACT_HIST ADD (
  CONSTRAINT PK_HMNDEP_ARTIFACT_HIST
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_ARTIFACT_HIST
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_ARTIFACTS 
-- 
ALTER TABLE HMNDEP_ARTIFACTS ADD (
  CONSTRAINT PK_HMNDEP_ARTIFACTS
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_ARTIFACTS
  ENABLE VALIDATE,
  CONSTRAINT UIX_HMNDEP_ARTIFACTS_01
  UNIQUE (ARTIFACT_NAME)
  USING INDEX UIX_HMNDEP_ARTIFACTS_01
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_DAO_DEPENDENCIES 
-- 
ALTER TABLE HMNDEP_DAO_DEPENDENCIES ADD (
  CONSTRAINT PK_HMNDEP_DAO_DEPENDENCIES
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_DAO_DEPENDENCIES
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_EXECUTIONS 
-- 
ALTER TABLE HMNDEP_EXECUTIONS ADD (
  CONSTRAINT PK_HMNDEP_EXECUTIONS
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_EXECUTIONS
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_FE_DEPENDENCIES 
-- 
ALTER TABLE HMNDEP_FE_DEPENDENCIES ADD (
  CONSTRAINT PK_HMNDEP_FE_DEPENDENCIES
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_FE_DEPENDENCIES
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_FE_DEPENDENCY_HIST 
-- 
ALTER TABLE HMNDEP_FE_DEPENDENCY_HIST ADD (
  CONSTRAINT PK_HMNDEP_FE_DEPENDENCY_HIST
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_FE_DEPENDENCY_HIST
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_HBM_TABLES 
-- 
ALTER TABLE HMNDEP_HBM_TABLES ADD (
  CONSTRAINT PK_HMNDEP_HBM_TABLES
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_HBM_TABLES
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_NQ_DEPENDENCIES 
-- 
ALTER TABLE HMNDEP_NQ_DEPENDENCIES ADD (
  CONSTRAINT PK_HMNDEP_NQ_DEPENDENCIES
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_NQ_DEPENDENCIES
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_NQ_DEPENDENCY_HIST 
-- 
ALTER TABLE HMNDEP_NQ_DEPENDENCY_HIST ADD (
  CONSTRAINT PK_HMNDEP_NQ_DEPENDENCY_HIST
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_NQ_DEPENDENCY_HIST
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_PARAMETERS 
-- 
ALTER TABLE HMNDEP_PARAMETERS ADD (
  CONSTRAINT PK_HMNDEP_PARAMETERS
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_PARAMETERS
  ENABLE VALIDATE,
  CONSTRAINT UC_HMNDEP_PARAMETERS_01
  UNIQUE (PARAM_NAME)
  USING INDEX UC_HMNDEP_PARAMETERS_01
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_PLSQL_DEPENDENCIES 
-- 
ALTER TABLE HMNDEP_PLSQL_DEPENDENCIES ADD (
  CONSTRAINT PK_HMNDEP_PLSQL_DEPENDENCIES
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_PLSQL_DEPENDENCIES
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_PLSQL_DEPENDENCY_HIST 
-- 
ALTER TABLE HMNDEP_PLSQL_DEPENDENCY_HIST ADD (
  CONSTRAINT PK_HMNDEP_PLSQL_DPNDNCY_HIST
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_PLSQL_DPNDNCY_HIST
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_SERVICE_CLASSES 
-- 
ALTER TABLE HMNDEP_SERVICE_CLASSES ADD (
  CONSTRAINT PK_HMNDEP_SERVICE_CLASSES
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_SERVICE_CLASSES
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_SERVICE_CLASSES_HIST 
-- 
ALTER TABLE HMNDEP_SERVICE_CLASSES_HIST ADD (
  CONSTRAINT PK_HMNDEP_SERVICE_CLASSES_HIST
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_SERVICE_CLASSES_HIST
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_SERVICE_DEPENDENCIES 
-- 
ALTER TABLE HMNDEP_SERVICE_DEPENDENCIES ADD (
  CONSTRAINT PK_HMNDEP_SERVICE_DEPENDENCIES
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_SERVICE_DEPENDENCIES
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_SERVICE_DEPENDENCY_HIST 
-- 
ALTER TABLE HMNDEP_SERVICE_DEPENDENCY_HIST ADD (
  CONSTRAINT PK_HMNDEP_SERVICE_DEPEND_HIST
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_SERVICE_DEPEND_HIST
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_SERVICE_INTERFACE_HIST 
-- 
ALTER TABLE HMNDEP_SERVICE_INTERFACE_HIST ADD (
  CONSTRAINT PK_HMNDEP_SERVICE_INTERF_HIST
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_SERVICE_INTERF_HIST
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_SERVICE_INTERFACES 
-- 
ALTER TABLE HMNDEP_SERVICE_INTERFACES ADD (
  CONSTRAINT PK_HMNDEP_SERVICE_INTERFACES
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_SERVICE_INTERFACES
  ENABLE VALIDATE,
  CONSTRAINT UC01_HMNDEP_SERVICE_INTERFACES
  UNIQUE (CLASS_NAME, IMPLEMENTS)
  USING INDEX UC01_HMNDEP_SERVICE_INTERFACES
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_SERVICE_METHODS 
-- 
ALTER TABLE HMNDEP_SERVICE_METHODS ADD (
  CONSTRAINT PK_HMNDEP_SERVICE_METHODS
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_SERVICE_METHODS
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_SERVICE_SUPERCLASSES 
-- 
ALTER TABLE HMNDEP_SERVICE_SUPERCLASSES ADD (
  CONSTRAINT PK_HMNDEP_SERVICE_SUPERCLASSES
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_SERVICE_SUPERCLASSES
  ENABLE VALIDATE,
  CONSTRAINT UC01_HMNDEP_SERVICE_SUPERCLASS
  UNIQUE (CLASS_NAME, SUPER_CLASS)
  USING INDEX UC01_HMNDEP_SERVICE_SUPERCLASS
  ENABLE VALIDATE)
/

-- 
-- Non Foreign Key Constraints for Table HMNDEP_SERVICE_SUPERCLASS_HIST 
-- 
ALTER TABLE HMNDEP_SERVICE_SUPERCLASS_HIST ADD (
  CONSTRAINT PK_HMNDEP_SERVICE_SUPERCLS_HST
  PRIMARY KEY
  (ID)
  USING INDEX PK_HMNDEP_SERVICE_SUPERCLS_HST
  ENABLE VALIDATE)
/

ALTER TABLE HMNDEP_ARTIFACT_HIST RENAME COLUMN ARTIFACT_NAME TO FILE_NAME
/

ALTER TABLE HMNDEP_ARTIFACT_HIST ADD (GROUP_ID  VARCHAR2(500))
/

ALTER TABLE HMNDEP_ARTIFACT_HIST ADD (ARTIFACT_ID  VARCHAR2(500) )
/

ALTER TABLE HMNDEP_ARTIFACT_HIST ADD (ARTIFACT_VERSION  VARCHAR2(100))
/

ALTER TABLE HMNDEP_ARTIFACT_HIST ADD (ARTIFACT_DESC  VARCHAR2(1000))
/

-- 4 Mart 2014
ALTER TABLE HMNDEP_FE_DEPENDENCIES add(DEPENDENCY_LEVEL  NUMBER(2))
/

-- 4 Mart 2014
ALTER TABLE HMNDEP_FE_DEPENDENCIES add(DEPENDENCY_PATH   VARCHAR2(3000))
/

CREATE INDEX IDX_HMNDEP_SERVICE_DEPS_01 ON hmndep_service_dependencies
(target_service, target_method)
/

CREATE INDEX IDX_HMNDEP_FE_DEPENDENCIES_01 ON hmndep_fe_dependencies
(target_service, target_method)
/


CREATE INDEX IDX_HMNDEP_SERVICE_CLASSES_01 ON hmndep_service_classes (class_name)
/

CREATE INDEX IDX_HMNDEP_SERVICE_METHODS_01 ON hmndep_service_methods (SERVICE_ID)
/