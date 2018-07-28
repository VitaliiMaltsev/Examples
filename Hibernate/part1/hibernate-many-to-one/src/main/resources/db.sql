create table region.jc_region(
region_id serial,
region_name varchar,
primary key (region_id)
);

create table region.jc_city(
city_id serial,
city_name varchar,
	region_id int not null,
primary key (city_id),
	foreign key (region_id)
    references jc_region(region_id)
);