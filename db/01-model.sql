BEGIN TRANSACTION;

--
-- Tabla para las credenciales usadas en el servicio
--
DROP TABLE IF EXISTS credentials CASCADE;
CREATE TABLE credentials (
	pk bigserial not null,
	token varchar(255) not null,
	app varchar(255) not null,
	password varchar(255) not null,
	active boolean default '0',
	UNIQUE (token),
    primary key(pk)
);
CREATE UNIQUE INDEX ON credentials(UPPER(TRIM(both FROM app)));

--
-- TABLA de ventas.
--
DROP TABLE IF EXISTS sismo CASCADE;
CREATE TABLE sismo (
	pk bigserial not null,
	fecha varchar(255) not null,
	fechautc varchar(255) not null,
	latitud float8 not null ,
	longitud float8 not null ,
	profundidad int not null ,
	magnitud float8 not null,
	agencia varchar(255) not null,
	referencia_geografica varchar(255) not null,
	primary key (pk)
);

-- Auto-generated SQL script #202107111934
INSERT INTO public.credentials (pk,"token",app,"password",active)
	VALUES (1,'8c071b2c-e2a0-11eb-ba80-0242ac130004','utem','1234',true);

COMMIT;





