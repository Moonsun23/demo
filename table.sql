create table sul(
    sno           int                auto_increment,
    sname       varchar(18)    unique,
    kind          varchar(8)      unique,
    base         varchar(30)    not null,
    ptg           varchar(5)      not null,
    intro         text               not null,
    brname      varchar(10)    unique,
    tag           varchar(15)    not null,
    primary key (sno)
);

create table online(
   ono            int                auto_increment,
   store         varchar(18)      not null,
   tel             varchar(14)      not null,
   waddr         varchar(100)      not null,
   primary key (ono)
);

create table offline(
    fno            int                auto_increment,
    store         varchar(18)      not null,
    tel             varchar(14)      not null,
    opt            varchar(100)      not null,
    primary key (fno)
);

create table brewery(
    bno            int                auto_increment,
    brname       varchar(18)      not null,
    addr           varchar(14)      not null,
    kind            varchar(8)      not null,
    tel              varchar(14)      not null,
    waddr         varchar(100)      not null,
    sname         varchar(18)    not null,
    pname        varchar(20)      unique,
    primary key (bno)
);

--  술 이름 외래키
alter table brewery
    add constraint fkbsname
        foreign key (sname) references sul (sname);
--  양조장 이름 외래키
alter table brewery
    add constraint fkbbrname
        foreign key (brname) references sul (brname);
--  주종 이름 외래키
alter table anju
    add constraint fkakind
        foreign key (kind) references sul (kind);
--  프로그램명 외래키
alter table program
    add constraint fkpname
        foreign key (pname) references brewery (pname);

create table program(
    pname            varchar(20),
    place             varchar(18)      not null,
    contents       text                  not null,
    time             varchar(20)      not null,
    cost             varchar(14)      not null,
    primary key (pname)
);

create table anju(
     ano            int,
     aname       varchar(18)      not null,
     kind            varchar(8)      not null,
     primary key (ano)
);

create table cocktail(
     cno            int,
     cname       varchar(18)      not null,
     mix            varchar(20)      not null,
     comment     text               not null,
     recipe          varchar(50)      not null,
     primary key (cno)
);