# First Model Query
create table category
(
    id   binary(16) not null,
    name varchar(255),
    primary key (id)
) engine = InnoDB ;
create table price
(
    id       binary(16) not null,
    currency varchar(255),
    price    float(53)  not null,
    primary key (id)
) engine = InnoDB ;
create table product
(
    id          binary(16) not null,
    description varchar(255),
    image       varchar(255),
    title       varchar(255),
    category    binary(16),
    price       binary(16),
    primary key (id)
) engine = InnoDB ;
alter table product
    add constraint FKqx9wikktsev17ctu0kcpkrafc foreign key (category) references category (id) ;
alter table product
    add constraint FKiygh4hph1bssut0qdh097nobk foreign key (price) references price (id);