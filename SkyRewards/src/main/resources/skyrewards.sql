create database skyrewards;
create table skyrewards.channels (channel_code VARCHAR(10)   NOT NULL   PRIMARY KEY , 
description VARCHAR(38));

create table skyrewards.customer_portfolio (id INT   NOT NULL AUTO_INCREMENT  PRIMARY KEY , 
account_no VARCHAR(38), channel_code VARCHAR(10),  FOREIGN KEY(channel_code) REFERENCES channels(channel_code));


create table skyrewards.rewards(id INT   NOT NULL AUTO_INCREMENT  PRIMARY KEY , 
channel_code VARCHAR(10), reward VARCHAR(50),  FOREIGN KEY(channel_code) REFERENCES channels(channel_code));

insert into skyrewards.channels VALUES("SPORTS", "Sports Channel");
insert into skyrewards.channels VALUES("KIDS", "Kids Channel");
insert into skyrewards.channels VALUES("MUSIC", "Music Channel");
insert into skyrewards.channels VALUES("NEWS", "News Channel");
insert into skyrewards.channels VALUES("MOVIES", "Movies Channel");


insert into skyrewards.customer_portfolio VALUES(1,100, "SPORTS");
insert into skyrewards.customer_portfolio VALUES(2,100, "KIDS");
insert into skyrewards.customer_portfolio VALUES(3,100, "MOVIES");

insert into skyrewards.customer_portfolio VALUES(4,200, "MUSIC");
insert into skyrewards.customer_portfolio VALUES(5,200, "NEWS");

insert into skyrewards.customer_portfolio VALUES(6,300, "SPORTS");
insert into skyrewards.customer_portfolio VALUES(7,300, "KIDS");
insert into skyrewards.customer_portfolio VALUES(8,300, "MOVIES");

insert into skyrewards.rewards VALUES(1, "SPORTS","CHAMPIONS_LEAGUE_FINAL_TICKET");
insert into skyrewards.rewards VALUES(2, "MUSIC","KARAOKE_PRO_MICROPHONE");
insert into skyrewards.rewards VALUES(3, "MOVIES","PIRATES_OF_THE_CARRIBEAN_COLLECTION");

commit;