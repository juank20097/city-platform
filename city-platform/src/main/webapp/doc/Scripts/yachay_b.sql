

/*==============================================================*/
/* Table: GEN_BARRIOS                                           */
/*==============================================================*/
create table GEN_BARRIOS (
   BAR_ID               VARCHAR(100)         not null,
   DIS_ID               VARCHAR(100)         null,
   BAR_NOMBRE           VARCHAR(150)         null,
   BAR_DESCRIPCION      VARCHAR(254)         null,
   BAR_HECTAREAS        NUMERIC              null,
   BAR_METROS_CUADRADOS NUMERIC              null,
   BAR_ESTADO           CHAR                 null,
   BAR_LINK_MAPA        VARCHAR(500)         null,
   BAR_LINK_PDF         VARCHAR(500)         null,
   constraint PK_GEN_BARRIOS primary key (BAR_ID)
);




/*==============================================================*/
/* Table: GEN_COMUNIDADES                                       */
/*==============================================================*/
create table GEN_COMUNIDADES (
   COM_ID               VARCHAR(100)         not null,
   COM_NOMBRE           VARCHAR(150)         null,
   COM_HECTAREAS        NUMERIC              null,
   COM_UBICACION        BOOL                 null,
   COM_METROS_CUADRADOS NUMERIC              null,
   COM_ESTADO           CHAR                 null,
   COM_LINK_MAPA        VARCHAR(500)         null,
   COM_LINK_PDF         VARCHAR(500)         null,
   constraint PK_GEN_COMUNIDADES primary key (COM_ID)
);

/*==============================================================*/
/* Table: GEN_DISTRITOS                                         */
/*==============================================================*/
create table GEN_DISTRITOS (
   DIS_ID               VARCHAR(100)         not null,
   ZON_ID               VARCHAR(100)         null,
   DIS_NOMBRE           VARCHAR(150)         null,
   DIS_DESCRIPCION      VARCHAR(254)         null,
   DIS_HECTAREAS        NUMERIC              null,
   DIS_METROS_CUADRADOS NUMERIC              null,
   DIS_ESTADO           CHAR                 null,
   DIS_LINK_MAPA        VARCHAR(500)         null,
   DIS_LINK_PDF         VARCHAR(500)         null,
   constraint PK_GEN_DISTRITOS primary key (DIS_ID)
);






/*==============================================================*/
/* Table: GEN_ZONAS                                             */
/*==============================================================*/
create table GEN_ZONAS (
   ZON_ID               VARCHAR(100)         not null,
   ZON_NOMBRE           VARCHAR(100)         null,
   ZON_DESCRIPCION      VARCHAR(254)         null,
   ZON_HECTAREAS        NUMERIC              null,
   ZON_METROS_CUADRADOS NUMERIC              null,
   ZON_ESTADO           CHAR                 null,
   ZON_LINK_MAPA        VARCHAR(500)         null,
   ZON_LINK_PDF         VARCHAR(500)         null,
   constraint PK_GEN_ZONAS primary key (ZON_ID)
);

/*==============================================================*/
/* Table: GEN_ZONAS_COMUNIDADES                                 */
/*==============================================================*/
create table GEN_ZONAS_COMUNIDADES (
   ZC_ID                VARCHAR(100)         not null,
   COM_ID               VARCHAR(100)         null,
   ZON_ID               VARCHAR(100)         null,
   constraint PK_GEN_ZONAS_COMUNIDADES primary key (ZC_ID)
);



alter table GEN_BARRIOS
   add constraint FK_GEN_BARR_REFERENCE_GEN_DIST foreign key (DIS_ID)
      references GEN_DISTRITOS (DIS_ID)
      on delete restrict on update restrict;



alter table GEN_DISTRITOS
   add constraint FK_GEN_DIST_REFERENCE_GEN_ZONA foreign key (ZON_ID)
      references GEN_ZONAS (ZON_ID)
      on delete restrict on update restrict;



alter table GEN_ZONAS_COMUNIDADES
   add constraint FK_GEN_ZONA_REFERENCE_GEN_COMU foreign key (COM_ID)
      references GEN_COMUNIDADES (COM_ID)
      on delete restrict on update restrict;

alter table GEN_ZONAS_COMUNIDADES
   add constraint FK_GEN_ZONA_REFERENCE_GEN_ZONA foreign key (ZON_ID)
      references GEN_ZONAS (ZON_ID)
      on delete restrict on update restrict;



