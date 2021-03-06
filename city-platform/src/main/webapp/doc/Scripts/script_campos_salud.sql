ALTER TABLE seg_registro_emergencias ADD COLUMN seg_usuario_aplicacion character varying(100);
ALTER TABLE seg_registro_emergencias ADD COLUMN seg_fecha_registro time with time zone;




ALTER TABLE gen_salud DROP COLUMN sld_medicamentos;

ALTER TABLE gen_salud ADD COLUMN sld_medicamentos_cronicos1 varchar(254);
ALTER TABLE gen_salud ADD COLUMN sld_medicamentos_cronicos2 varchar(254);

ALTER TABLE gen_salud DROP COLUMN sld_consume_tabaco;
ALTER TABLE gen_salud DROP COLUMN sld_consume_alcohol;

ALTER TABLE gen_salud ADD COLUMN sld_consume_tabaco bool;
ALTER TABLE gen_salud ADD COLUMN sld_consume_alcohol bool;

ALTER TABLE gen_salud ADD COLUMN SLD_ALERGIAS_CRONICAS1 VARCHAR(254)  ;  
ALTER TABLE gen_salud ADD COLUMN SLD_ALERGIAS_CRONICAS2 VARCHAR(254);      
ALTER TABLE gen_salud ADD COLUMN SLD_NOMBRE_LUGAR_CENTRO_MEDICO VARCHAR(254); 
ALTER TABLE gen_salud ADD COLUMN SLD_PADRE_fallecido       BOOL;            
ALTER TABLE gen_salud ADD COLUMN SLD_PADRE_ENFERMEDADES_ACTUALES VARCHAR(254);      
ALTER TABLE gen_salud ADD COLUMN   SLD_PADRE_CAUSA_MUERTE VARCHAR(254) ;       
ALTER TABLE gen_salud ADD COLUMN   SLD_PADRE_EDAD       INT4;                 
ALTER TABLE gen_salud ADD COLUMN   SLD_MADRE_FALLECIDO       BOOL;                 
ALTER TABLE gen_salud ADD COLUMN   SLD_MADRE_ENFERMEDADES_ACTUALES VARCHAR(254);        
ALTER TABLE gen_salud ADD COLUMN   SLD_MADRE_CAUSA_MUERTE VARCHAR(254);         
ALTER TABLE gen_salud ADD COLUMN   SLD_MADRE_EDAD       INT4;                 
ALTER TABLE gen_salud ADD COLUMN   SLD_PERIODICIDAD_ALCOHOL VARCHAR(254);         
ALTER TABLE gen_salud ADD COLUMN   SLD_PERIODICIDAD_TABACO VARCHAR(254);        
ALTER TABLE gen_salud ADD COLUMN   SLD_EMBRIAGAR        BOOL;                
ALTER TABLE gen_salud ADD COLUMN   SLD_PERIODICIDAD_EMBRIAGAR VARCHAR(254);         
ALTER TABLE gen_salud ADD COLUMN   SLD_OBSERVACIONES    VARCHAR(254);        