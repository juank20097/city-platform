/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     3/8/2016 10:50:32                            */
/*==============================================================*/


drop table GEN_AREAS;

drop table GEN_CATALOGO_CAB;

drop table GEN_CATALOGO_ITEMS_DET;

drop table GEN_ESTUDIANTE_INSTITUCION;

drop table GEN_EXTERNOS;

drop table GEN_FUNCIONARIOS_INSTITUCION;

drop table GEN_INSTITUCIONES;

drop table GEN_PARAMETROS;

drop table GEN_PERSONA;

drop table GEN_PERSONA_DETALLE;

drop table GEN_REGISTRO_EXCEL;

drop table GEN_SALUD;

drop table GEN_SECTORES;

drop table GEN_SITIOS;

drop table GEN_SIT_FOTO;

drop table GEN_TIPO_SITIOS;

drop table GEN_WS_MAIL;

drop table INT_SITIOS_CCAA_DEPARTAMENTOS;

drop table SEG_REGISTRO_EMERGENCIAS;

/*==============================================================*/
/* Table: GEN_AREAS                                             */
/*==============================================================*/
create table GEN_AREAS (
   ARE_ID               INT4                 not null,
   SEC_ID               INT4                 null,
   ARE_NOMBRE           VARCHAR(100)         null,
   ARE_DESCRIPCION      VARCHAR(254)         null,
   ARE_LATITUD          VARCHAR(30)          null,
   ARE_LONGITUD         VARCHAR(30)          null,
   ARE_ESTADO           CHAR                 null,
   ARE_PADRE            VARCHAR(100)         null,
   ARE_CODIGO           VARCHAR(50)          null,
   constraint PK_GEN_AREAS primary key (ARE_ID)
);

/*==============================================================*/
/* Table: GEN_CATALOGO_CAB                                      */
/*==============================================================*/
create table GEN_CATALOGO_CAB (
   CAT_CODIGO           VARCHAR(100)         not null,
   CAT_NOMBRE           VARCHAR(100)         null,
   CAT_DESCRIPCION      VARCHAR(254)         null,
   constraint PK_GEN_CATALOGO_CAB primary key (CAT_CODIGO)
);

/*==============================================================*/
/* Table: GEN_CATALOGO_ITEMS_DET                                */
/*==============================================================*/
create table GEN_CATALOGO_ITEMS_DET (
   ITE_CODIGO           VARCHAR(100)         not null,
   CAT_CODIGO           VARCHAR(100)         null,
   ITE_NOMBRE           VARCHAR(100)         null,
   ITE_PADRE            VARCHAR(100)         null,
   ITE_CLASIFICACION    VARCHAR(100)         null,
   constraint PK_GEN_CATALOGO_ITEMS_DET primary key (ITE_CODIGO)
);

/*==============================================================*/
/* Table: GEN_ESTUDIANTE_INSTITUCION                            */
/*==============================================================*/
create table GEN_ESTUDIANTE_INSTITUCION (
   PER_DNI              VARCHAR(100)         not null,
   INS_CODIGO           VARCHAR(100)         not null,
   EST_CORREO           VARCHAR(100)         null,
   EST_NIVEL            VARCHAR(20)          null,
   EST_CARRERA          VARCHAR(200)         null,
   EST_MODALIDAD        VARCHAR(20)          null,
   EST_AREA_ESTUDIO     VARCHAR(100)         null,
   EST_ESTADO           CHAR                 null,
   constraint PK_GEN_ESTUDIANTE_INSTITUCION primary key (PER_DNI, INS_CODIGO)
);

/*==============================================================*/
/* Table: GEN_EXTERNOS                                          */
/*==============================================================*/
create table GEN_EXTERNOS (
   PER_DNI              VARCHAR(100)         not null,
   EXT_TIPO             VARCHAR(30)          null,
   EXT_REFERENCIA       VARCHAR(254)         null,
   constraint PK_GEN_EXTERNOS primary key (PER_DNI)
);

/*==============================================================*/
/* Table: GEN_FUNCIONARIOS_INSTITUCION                          */
/*==============================================================*/
create table GEN_FUNCIONARIOS_INSTITUCION (
   PER_DNI              VARCHAR(100)         not null,
   INS_CODIGO           VARCHAR(100)         not null,
   FUN_CARGO            VARCHAR(100)         null,
   FUN_DIRECCION        VARCHAR(200)         null,
   FUN_GERENCIA         VARCHAR(200)         null,
   FUN_JEFE_INMEDIATO   VARCHAR(20)          null,
   FUN_TIPO             VARCHAR(100)         null,
   FUN_TIPO_EVALUACION  VARCHAR(100)         null,
   FUN_FECHA_INGRESO    DATE                 null,
   FUN_ESTADO           CHAR                 null,
   constraint PK_GEN_FUNCIONARIOS_INSTITUCIO primary key (PER_DNI, INS_CODIGO)
);

/*==============================================================*/
/* Table: GEN_INSTITUCIONES                                     */
/*==============================================================*/
create table GEN_INSTITUCIONES (
   INS_CODIGO           VARCHAR(100)         not null,
   INS_NOMBRE           VARCHAR(100)         null,
   INS_DESCRIPCION      VARCHAR(254)         null,
   INS_ESTADO           CHAR                 null,
   INS_RUC              VARCHAR(20)          null,
   INS_RAZON_SOCIAL     VARCHAR(100)         null,
   INS_CATEGORIA        VARCHAR(50)          null,
   constraint PK_GEN_INSTITUCIONES primary key (INS_CODIGO)
);

/*==============================================================*/
/* Table: GEN_PARAMETROS                                        */
/*==============================================================*/
create table GEN_PARAMETROS (
   PAR_ID               VARCHAR(20)          not null,
   PAR_NOMBRE           VARCHAR(100)         null,
   PAR_DESCRIPCION      VARCHAR(254)         null,
   PAR_VALOR            VARCHAR(254)         null,
   PAR_PADRE            VARCHAR(20)          null,
   PAR_ESTADO           CHAR                 null,
   constraint PK_GEN_PARAMETROS primary key (PAR_ID)
);

/*==============================================================*/
/* Table: GEN_PERSONA                                           */
/*==============================================================*/
create table GEN_PERSONA (
   PER_DNI              VARCHAR(100)         not null,
   PER_TIPO_DNI         VARCHAR(20)          null,
   PER_NOMBRES          VARCHAR(100)         null,
   PER_APELLIDOS        VARCHAR(100)         null,
   PER_FECHA_NACIMIENTO DATE                 null,
   PER_GENERO           VARCHAR(20)          null,
   PER_TELEFONO         VARCHAR(20)          null,
   PER_CELULAR          VARCHAR(20)          null,
   PER_CORREO           VARCHAR(100)         null,
   PER_ESTADO_CIVIL     VARCHAR(20)          null,
   PER_ESTADO           CHAR                 null,
   constraint PK_GEN_PERSONA primary key (PER_DNI)
);

/*==============================================================*/
/* Table: GEN_PERSONA_DETALLE                                   */
/*==============================================================*/
create table GEN_PERSONA_DETALLE (
   PER_DNI              VARCHAR(100)         not null,
   PDE_FOTO             VARCHAR(254)         null,
   PDE_HUELLA           BOX                  null,
   PDE_PAIS_NACIMIENTO  VARCHAR(50)          null,
   PDE_PROVINCIA_NACIMIENTO VARCHAR(50)          null,
   PDE_CIUDAD_NACIMIENTO VARCHAR(50)          null,
   PDE_LUGAR_NACIMIENTO VARCHAR(100)         null,
   PDE_PAIS_RESIDENCIA  VARCHAR(50)          null,
   PDE_PROVINCIA_RESIDENCIA VARCHAR(50)          null,
   PDE_CIUDAD_RESIDENCIA VARCHAR(50)          null,
   PDE_DIRECCION        VARCHAR(254)         null,
   PDE_CONDICION_CIUDADANA VARCHAR(20)          null,
   PDE_CONYUGE          VARCHAR(100)         null,
   PDE_NOMBRE_PADRE     VARCHAR(50)          null,
   PDE_NACIONALIDAD_PADRE VARCHAR(50)          null,
   PDE_NOMBRE_MADRE     VARCHAR(50)          null,
   PDE_NACIONALIDAD_MADRE VARCHAR(50)          null,
   PDE_FECHA_MATRIMONIO DATE                 null,
   PDE_INSCRIPCION_DEFUNCION VARCHAR(100)         null,
   PDE_FECHA_DEFUNCION  DATE                 null,
   PDE_OBSERVACION      VARCHAR(254)         null,
   PDE_EMERG_CONTACTO_NOMBRES VARCHAR(100)         null,
   PDE_EMERG_CONTACTO_ID VARCHAR(100)         null,
   PDE_EMERG_CONTACTO_TELEFONO VARCHAR(20)          null,
   PDE_NUM_HIJOS        INT4                 null,
   constraint PK_GEN_PERSONA_DETALLE primary key (PER_DNI)
);

/*==============================================================*/
/* Table: GEN_REGISTRO_EXCEL                                    */
/*==============================================================*/
create table GEN_REGISTRO_EXCEL (
   EXC_ID               INT4                 null,
   EXC_USUARIO          VARCHAR(20)          null,
   EXC_NOMBRE_ARCHIVO   TEXT                 null,
   EXC_NUEVOS           INT4                 null,
   EXC_ACTUALIZADOS     INT4                 null,
   EXC_INACTIVOS        INT4                 null,
   EXC_ERRORES          INT4                 null,
   EXC_IP               VARCHAR(30)          null,
   EXC_FECHA            TIMESTAMP            null
);

/*==============================================================*/
/* Table: GEN_SALUD                                             */
/*==============================================================*/
create table GEN_SALUD (
   PER_DNI              VARCHAR(100)         not null,
   SLD_ASEGURADO        VARCHAR(100)         null,
   SLD_GRUPO_SANGUINEO  VARCHAR(100)         null,
   SLD_CARNET_CONADIES  VARCHAR(20)          null,
   SLD_DISCPACIDAD_TIPO VARCHAR(254)         null,
   SLD_DISCAPACIDAD_GRADO VARCHAR(100)         null,
   SLD_ALERGIAS         VARCHAR(254)         null,
   SLD_PESO             NUMERIC              null,
   SLD_ALTURA           NUMERIC              null,
   SLD_PRESION          VARCHAR(20)          null,
   SLD_NIVEL_AZUCAR     VARCHAR(20)          null,
   SLD_REALIZA_EJERCICIO BOOL                 null,
   SLD_PERIODICIDAD_EJERCICIO VARCHAR(254)         null,
   SLD_VEGETARIANO      BOOL                 null,
   SLD_CONSUME_TABACO   BOOL                 null,
   SLD_CONSUME_ALCOHOL  BOOL                 null,
   SLD_MEDICAMENTOS_HABITUALES VARCHAR(254)         null,
   SLD_FRECUENCIA_CONSUMO_MEDICAME VARCHAR(254)         null,
   SLD_ALERGIAS_CRONICAS1 VARCHAR(254)         null,
   SLD_ALERGIAS_CRONICAS2 VARCHAR(254)         null,
   SLD_NOMBRE_LUGAR_CENTRO_MEDICO VARCHAR(254)         null,
   SLD_PADRE_VIVO       BOOL                 null,
   SLD_PADRE_ENFERMEDADES_ACTUALES VARCHAR(254)         null,
   SLD_PADRE_CAUSA_MUERTE VARCHAR(254)         null,
   SLD_PADRE_EDAD       INT4                 null,
   SLD_MADRE_VIVA       BOOL                 null,
   SLD_MADRE_ENFERMEDADES_ACTUALES VARCHAR(254)         null,
   SLD_MADRE_CAUSA_MUERTE VARCHAR(254)         null,
   SLD_MADRE_EDAD       INT4                 null,
   SLD_PERIODICIDAD_ALCOHOL VARCHAR(254)         null,
   SLD_PERIODICIDAD_TABACO VARCHAR(254)         null,
   SLD_EMBRIAGAR        BOOL                 null,
   SLD_PERIODICIDAD_EMBRIAGAR VARCHAR(254)         null,
   SLD_OBSERVACIONES    VARCHAR(254)         null,
   constraint PK_GEN_SALUD primary key (PER_DNI)
);

/*==============================================================*/
/* Table: GEN_SECTORES                                          */
/*==============================================================*/
create table GEN_SECTORES (
   SEC_ID               INT4                 not null,
   SEC_NOMBRE           VARCHAR(100)         null,
   SEC_DIRECCION_REFERENCIAL VARCHAR(200)         null,
   SEC_DESCRIPCION      VARCHAR(254)         null,
   SEC_ESTADO           CHAR                 null,
   constraint PK_GEN_SECTORES primary key (SEC_ID)
);

/*==============================================================*/
/* Table: GEN_SITIOS                                            */
/*==============================================================*/
create table GEN_SITIOS (
   SIT_ID               VARCHAR(100)         not null,
   INS_CODIGO           VARCHAR(100)         null,
   TSI_ID               INT4                 null,
   ARE_ID               INT4                 null,
   SIT_NOMBRE           VARCHAR(100)         null,
   SIT_PISO             INT4                 null,
   SIT_INTEGRACION_ACCESOS BOOL                 null,
   SIT_COSTO_ARRIENDO   NUMERIC              null,
   SIT_CAPACIDAD        INT4                 null,
   SIT_DESCRIPCION      VARCHAR(254)         null,
   SIT_NUMERO           VARCHAR(10)          null,
   SIT_CALLE_PRINCIPAL  VARCHAR(100)         null,
   SIT_CALLE_SECUNDARIA VARCHAR(100)         null,
   SIT_ESTADO           CHAR                 null,
   constraint PK_GEN_SITIOS primary key (SIT_ID)
);

/*==============================================================*/
/* Table: GEN_SIT_FOTO                                          */
/*==============================================================*/
create table GEN_SIT_FOTO (
   SIF_ID               INT4                 not null,
   SIT_ID               VARCHAR(100)         null,
   SIF_NOMBRE           VARCHAR(100)         null,
   SIF_RUTA_IMG         VARCHAR(254)         null,
   SIF_IMAGEN           BOX                  null,
   constraint PK_GEN_SIT_FOTO primary key (SIF_ID)
);

/*==============================================================*/
/* Table: GEN_TIPO_SITIOS                                       */
/*==============================================================*/
create table GEN_TIPO_SITIOS (
   TSI_ID               INT4                 not null,
   TSI_NOMBRE           VARCHAR(100)         null,
   TSI_DESCRIPCION      VARCHAR(254)         null,
   TSI_ESTADO           CHAR                 null,
   constraint PK_GEN_TIPO_SITIOS primary key (TSI_ID)
);

/*==============================================================*/
/* Table: GEN_WS_MAIL                                           */
/*==============================================================*/
create table GEN_WS_MAIL (
   MAI_ID               VARCHAR(20)          not null,
   MAI_SERVER           VARCHAR(100)         null,
   MAI_USUARIO          VARCHAR(100)         null,
   MAI_PASSWORD         VARCHAR(200)         null,
   MAI_PWD_RESP         VARCHAR(200)         null,
   MAI_ESTADO           CHAR                 null,
   MAI_DESCRIPCION      VARCHAR(254)         null,
   constraint PK_GEN_WS_MAIL primary key (MAI_ID)
);

/*==============================================================*/
/* Table: INT_SITIOS_CCAA_DEPARTAMENTOS                         */
/*==============================================================*/
create table INT_SITIOS_CCAA_DEPARTAMENTOS (
   SIT_ID               VARCHAR(100)         not null,
   CCA_ID_DEPARTAMENTO  INT4                 not null,
   SIT_NOMBRE           VARCHAR(100)         null,
   CCA_DEPARTAMENTO     VARCHAR(100)         null,
   CCA_ESTADO           CHAR                 null,
   CCA_EXITO            VARCHAR(20)          null,
   CCA_OBSERVACION      VARCHAR(254)         null,
   constraint PK_INT_SITIOS_CCAA_DEPARTAMENT primary key (SIT_ID, CCA_ID_DEPARTAMENTO)
);

/*==============================================================*/
/* Table: SEG_REGISTRO_EMERGENCIAS                              */
/*==============================================================*/
create table SEG_REGISTRO_EMERGENCIAS (
   SEG_ID               INT4                 not null,
   PER_DNI              VARCHAR(100)         not null,
   INS_CODIGO           VARCHAR(100)         not null,
   SEG_EMERGENCIA       VARCHAR(254)         null,
   SEG_FECHA            TIMESTAMP            null,
   SEG_ACCION           VARCHAR(254)         null,
   SEG_TIPO_EMERGENCIA  VARCHAR(100)         null,
   SEG_SUB_TIPO         VARCHAR(100)         null,
   SEG_SUB_HIJO         VARCHAR(100)         null,
   SEG_LATITUD          FLOAT8               null,
   SEG_LONGITUD         FLOAT8               null,
   constraint PK_SEG_REGISTRO_EMERGENCIAS primary key (SEG_ID)
);

alter table GEN_AREAS
   add constraint FK_GEN_AREA_REFERENCE_GEN_SECT foreign key (SEC_ID)
      references GEN_SECTORES (SEC_ID)
      on delete restrict on update restrict;

alter table GEN_CATALOGO_ITEMS_DET
   add constraint FK_GEN_CATA_REFERENCE_GEN_CATA foreign key (CAT_CODIGO)
      references GEN_CATALOGO_CAB (CAT_CODIGO)
      on delete restrict on update restrict;

alter table GEN_ESTUDIANTE_INSTITUCION
   add constraint FK_GEN_ESTU_REFERENCE_GEN_PERS foreign key (PER_DNI)
      references GEN_PERSONA (PER_DNI)
      on delete restrict on update restrict;

alter table GEN_ESTUDIANTE_INSTITUCION
   add constraint FK_GEN_ESTU_REFERENCE_GEN_INST foreign key (INS_CODIGO)
      references GEN_INSTITUCIONES (INS_CODIGO)
      on delete restrict on update restrict;

alter table GEN_EXTERNOS
   add constraint FK_GEN_EXTE_REFERENCE_GEN_PERS foreign key (PER_DNI)
      references GEN_PERSONA (PER_DNI)
      on delete restrict on update restrict;

alter table GEN_FUNCIONARIOS_INSTITUCION
   add constraint FK_GEN_FUNC_REFERENCE_GEN_PERS foreign key (PER_DNI)
      references GEN_PERSONA (PER_DNI)
      on delete restrict on update restrict;

alter table GEN_FUNCIONARIOS_INSTITUCION
   add constraint FK_GEN_FUNC_REFERENCE_GEN_INST foreign key (INS_CODIGO)
      references GEN_INSTITUCIONES (INS_CODIGO)
      on delete restrict on update restrict;

alter table GEN_PERSONA_DETALLE
   add constraint FK_GEN_PERS_REFERENCE_GEN_PERS foreign key (PER_DNI)
      references GEN_PERSONA (PER_DNI)
      on delete restrict on update restrict;

alter table GEN_SALUD
   add constraint FK_GEN_SALU_REFERENCE_GEN_PERS foreign key (PER_DNI)
      references GEN_PERSONA (PER_DNI)
      on delete restrict on update restrict;

alter table GEN_SITIOS
   add constraint FK_GEN_SITI_REFERENCE_GEN_TIPO foreign key (TSI_ID)
      references GEN_TIPO_SITIOS (TSI_ID)
      on delete restrict on update restrict;

alter table GEN_SITIOS
   add constraint FK_GEN_SITI_REFERENCE_GEN_AREA foreign key (ARE_ID)
      references GEN_AREAS (ARE_ID)
      on delete restrict on update restrict;

alter table GEN_SITIOS
   add constraint FK_GEN_SITI_REFERENCE_GEN_INST foreign key (INS_CODIGO)
      references GEN_INSTITUCIONES (INS_CODIGO)
      on delete restrict on update restrict;

alter table GEN_SIT_FOTO
   add constraint FK_GEN_SIT__REFERENCE_GEN_SITI foreign key (SIT_ID)
      references GEN_SITIOS (SIT_ID)
      on delete restrict on update restrict;

alter table INT_SITIOS_CCAA_DEPARTAMENTOS
   add constraint FK_INT_SITI_REFERENCE_GEN_SITI foreign key (SIT_ID)
      references GEN_SITIOS (SIT_ID)
      on delete restrict on update restrict;

alter table SEG_REGISTRO_EMERGENCIAS
   add constraint FK_SEG_REGI_REFERENCE_GEN_FUNC foreign key (PER_DNI, INS_CODIGO)
      references GEN_FUNCIONARIOS_INSTITUCION (PER_DNI, INS_CODIGO)
      on delete restrict on update restrict;

