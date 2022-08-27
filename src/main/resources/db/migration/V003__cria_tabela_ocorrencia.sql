create table Ocorrencia (
id bigint not null auto_increment,
entrega_id bigint not null,
descricao text not null,
data_registro datetime,
primary key (id),
foreign key (entrega_id) references entrega (id)
)