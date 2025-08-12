CREATE TABLE topicos(
    id bigint auto_increment primary key,
    titulo varchar(255) not null,
    mensagem varchar(500) not null,
    data_criacao timestamp default current_timestamp,
    status varchar(50) default 'NAO_RESPONDIDO',
    autor varchar(255) not null,
    curso varchar(255) not null,
    unique(titulo, mensagem)
);