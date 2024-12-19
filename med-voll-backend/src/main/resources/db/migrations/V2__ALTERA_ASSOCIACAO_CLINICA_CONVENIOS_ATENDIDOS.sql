create table tb_clinica_convenios_atendidos (
        clinica_id_clinica bigint not null,
        convenios_atendidos_id_convenio bigint not null,
        primary key (clinica_id_clinica, convenios_atendidos_id_convenio)
    );

alter table if exists tb_clinica_convenios_atendidos
       add constraint fk01_tb_clinica_convenios_atendidos
       foreign key (convenios_atendidos_id_convenio)
       references tb_convenio;

alter table if exists tb_clinica_convenios_atendidos
       add constraint fk02_tb_clinica_convenios_atendidos
       foreign key (clinica_id_clinica)
       references tb_clinica;