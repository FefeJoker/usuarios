create table cliente (id int4 not null, cuit varchar(255), fecha_baja timestamp, habilitado_online boolean, mail varchar(255), max_cuenta_corriente float8, razon_social varchar(255), user_id int4, primary key (id))
create table cliente_obras (cliente_id int4 not null, obras_id int4 not null)
create table empleado (id int4 not null, mail varchar(255), user_id int4, primary key (id))
create table obra (id int4 not null, descripcion varchar(255), direccion varchar(255), latitud float4, longitud float4, superficie int4, cliente_id int4, tipo_id int4, primary key (id))
create table tipo_obra (id int4 not null, descripcion varchar(255), primary key (id))
create table tipo_usuario (id int4 not null, tipo varchar(255), primary key (id))
create table usuario (id int4 not null, password varchar(255), user varchar(255), tipo_usuario_id int4, primary key (id))
alter table if exists cliente_obras add constraint UK_f8ot3errhmx86hghg7dd43dri unique (obras_id)
alter table if exists cliente add constraint FK51q58w7p2gonvu6xsuyp3t2bo foreign key (user_id) references usuario
alter table if exists cliente_obras add constraint FKtrufkwlx0plkmvig4dmxqfv25 foreign key (obras_id) references obra
alter table if exists cliente_obras add constraint FK6yic10eflk00v3njk6891lh1m foreign key (cliente_id) references cliente
alter table if exists empleado add constraint FKa8t6or0dh99qx9ekdbyljsipa foreign key (user_id) references usuario
alter table if exists obra add constraint FKoe59djlrpdgbtaqqp69jf84i5 foreign key (cliente_id) references cliente
alter table if exists obra add constraint FK921cbuhqdgus5ljso6216f7nk foreign key (tipo_id) references tipo_obra
alter table if exists usuario add constraint FKe581tp719p3d7o5u2w9sre10b foreign key (tipo_usuario_id) references tipo_usuario
