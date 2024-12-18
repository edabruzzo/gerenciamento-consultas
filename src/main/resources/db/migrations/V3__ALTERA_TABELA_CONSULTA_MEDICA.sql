alter table if exists tb_consulta_medica
       add column in_situacao varchar(255) not null check (in_situacao in ('AGENDADA','REALIZADA','CANCELADA'))
alter table if exists tb_consulta_medica
       add column id_medico bigint;
    alter table if exists tb_consulta_medica
       add column id_paciente bigint;
    alter table if exists tb_pessoa
       add column data_nascimento date;
    alter table if exists tb_pessoa
       drop constraint if exists UKraf7m9d4gaybcwnoljccgsdv5;
    alter table if exists tb_pessoa
       add constraint UKraf7m9d4gaybcwnoljccgsdv5 unique (cpf, nome);
    create sequence if not exists tb_consulta_medica_seq start with 1 increment by 1;
    alter table if exists tb_consulta_medica
       add constraint fk01_tb_consulta_medica
       foreign key (id_medico)
       references tb_medico;
    alter table if exists tb_consulta_medica
       add constraint fk02_tb_consulta_medica
       foreign key (id_paciente)
       references tb_paciente;