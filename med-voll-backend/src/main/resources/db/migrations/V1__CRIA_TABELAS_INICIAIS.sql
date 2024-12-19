drop table if exists tb_usuario_lista_papeis_sistema cascade;
drop table if exists tb_clinica cascade;
drop table if exists tb_consulta_medica cascade;
drop table if exists tb_convenio cascade;
drop table if exists tb_medico cascade;
drop table if exists tb_paciente cascade;
drop table if exists tb_papel_sistema cascade;
drop table if exists tb_pessoa cascade;
drop table if exists tb_usuario cascade;
drop sequence if exists tb_papel_sistema_seq;

create sequence tb_papel_sistema_seq start with 1 increment by 1;

create table tb_clinica (
        id_clinica bigint generated by default as identity,
        primary key (id_clinica)
    );

create table tb_consulta_medica (
        dt_consulta timestamp(6) not null,
        id_consulta_medica bigint generated by default as identity,
        id_convenio_acionado bigint,
        id_medico bigint,
        id_paciente bigint,
        in_situacao varchar(255) not null check (in_situacao in ('AGENDADA','REALIZADA','CANCELADA')),
        primary key (id_consulta_medica)
    );

create table tb_convenio (
        data_expiracao_contrato_clinica timestamp(6),
        id_convenio bigint generated by default as identity,
        numero_contrato_clinica bigint,
        cnpj varchar(255),
        nome_convenio varchar(255),
        primary key (id_convenio)
    );

create table tb_medico (
        id_medico bigint not null,
        crm varchar(255),
        in_especialidade varchar(255) check (in_especialidade in ('ORTOPEDIA','CARDIOLOGIA','NEUROLOGIA','DERMATOLOGIA','PEDIATRIA')),
        in_situacao varchar(255) check (in_situacao in ('ATIVO','DESLIGADO_CLINICA','SUSPENSO_CONSELHO_CLASSE')),
        primary key (id_medico)
    );

create table tb_paciente (
        id_paciente bigint not null,
        profissao varchar(255),
        primary key (id_paciente)
    );

create table tb_papel_sistema (
        id bigint not null,
        role varchar(255) check (role in ('ADMIN','MEDICO','PACIENTE','FUNCIONARIO')),
        primary key (id)
    );

create table tb_pessoa (
        data_nascimento date,
        id_pessoa bigint generated by default as identity,
        bairro varchar(255),
        cep varchar(255),
        cidade varchar(255),
        complemento varchar(255),
        cpf varchar(255) not null unique,
        email varchar(255),
        logradouro varchar(255),
        nome varchar(255),
        numero varchar(255),
        uf varchar(255),
        primary key (id_pessoa),
        unique (cpf, nome)
    );

create table tb_usuario (
        id_usuario bigint generated by default as identity,
        login varchar(255),
        senha varchar(255),
        primary key (id_usuario)
    );

create table tb_usuario_lista_papeis_sistema (
        lista_papeis_sistema_id bigint not null,
        usuario_id_usuario bigint not null,
        primary key (lista_papeis_sistema_id, usuario_id_usuario)
    );

alter table if exists tb_usuario_lista_papeis_sistema
       add constraint fk01_tb_usuario_lista_papeis_sistema
       foreign key (lista_papeis_sistema_id)
       references tb_papel_sistema;

alter table if exists tb_usuario_lista_papeis_sistema
       add constraint fk02_tb_usuario_lista_papeis_sistema
       foreign key (usuario_id_usuario)
       references tb_usuario;

alter table if exists tb_consulta_medica
       add constraint fk01_tb_consulta_medica
       foreign key (id_convenio_acionado)
       references tb_convenio;

alter table if exists tb_consulta_medica
       add constraint fk02_tb_consulta_medica
       foreign key (id_medico)
       references tb_medico;

alter table if exists tb_consulta_medica
       add constraint fk03_tb_consulta_medica
       foreign key (id_paciente)
       references tb_paciente;

alter table if exists tb_medico
       add constraint fk01_tb_medico
       foreign key (id_medico)
       references tb_pessoa;

alter table if exists tb_paciente
       add constraint fk01_tb_paciente
       foreign key (id_paciente)
       references tb_pessoa;


alter table if exists tb_papel_sistema
       add constraint fk01_tb_papel_sistema
       foreign key (usuario_id)
       references tb_usuario;
