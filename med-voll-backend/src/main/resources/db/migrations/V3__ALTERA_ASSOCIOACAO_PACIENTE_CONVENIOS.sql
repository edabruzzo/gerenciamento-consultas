alter table if exists tb_paciente_convenios
       add constraint fk01_tb_paciente_convenios
       foreign key (convenios_id_convenio)
       references tb_convenio;

alter table if exists tb_paciente_convenios
       add constraint fk02_tb_paciente_convenios
       foreign key (paciente_id)
       references tb_paciente;