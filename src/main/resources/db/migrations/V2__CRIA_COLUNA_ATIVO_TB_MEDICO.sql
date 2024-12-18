alter table if exists tb_medico
add column in_situacao varchar(255) check (in_situacao in ('ATIVO','DESLIGADO','SUSPENSO'));