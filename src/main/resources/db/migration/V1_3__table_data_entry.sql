DELETE FROM Visit;
DELETE FROM Vote;
DELETE FROM Users;
DELETE FROM Voter;

-- Inserting Data into Users
INSERT INTO Users(user_id, first_name, last_name, mobile_number, password,otp, otp_generation_time, user_role, part_id, constituency_id, district_id) VALUES
    (1,'Hilde','Ferrini',1111111111,'$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','1970-01-01 00:00:01','BLO','1','1','1'),
    (2,'Christoper','Pendrich',1111111112,'$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','1970-01-01 00:00:01','BLO','2','1','1'),
    (3,'Dasha','Paudin',1111111113,'$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','1970-01-01 00:00:01','BLO','3','1','1'),
    (4,'Barbaraanne','Karleman',1111111114,'$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','1970-01-01 00:00:01','BLO','47','2','1'),
    (5,'Renee','Menzies',1111111115,'$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','1970-01-01 00:00:01','BLO','48','2','1'),
    (6,'Beulah','Samsin',1111111116,'$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','1970-01-01 00:00:01','BLO','828','21','2'),
    (7,'Dirk','Barabisch',1111111117,'$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','1970-01-01 00:00:01','BLO','829','21','2'),
    (8,'Elberta','Harral',1111111118,'$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','1970-01-01 00:00:01','BLO','830','21','2'),
    (9,'Lawton','Warboy',1111111119,'$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','1970-01-01 00:00:01','BLO','871','22','2'),
    (10,'Forster','Cleghorn',1111111120,'$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','1970-01-01 00:00:01','BLO','872','22','2');


INSERT INTO Users(user_id, first_name, last_name, user_name,password,otp, user_role, constituency_id, district_id) VALUES
    (201,'Kellby','Farfoot','RO1','$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','RO','1','1'),
    (202,'Dave','Van der Baaren','RO2','$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','RO','2','1'),
    (203,'Ray','Tipping','RO3','$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','RO','3','1'),
    (204,'Cally','Beswetherick','RO4','$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','RO','4','1'),
    (205,'Johann','Hadny','RO5','$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','RO','5','1'),
    (206,'Rosalinda','Hamner','RO6','$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','RO','21','2'),
    (207,'Hally','Mingame','RO7','$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','RO','22','2'),
    (208,'Urbain','Randals','RO8','$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','RO','23','2'),
    (209,'Amby','Cracie','RO9','$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','RO','24','2'),
    (210,'Andy','Baylay','RO10','$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','RO','25','2');

INSERT INTO Users(user_id, first_name, last_name, user_name,password,otp, user_role, district_id) VALUES
    (211,'Adamsss','Elviee','DEO1','$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','DEO','1'),
    (212,'Emanuel','Josh','DEO2','$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','DEO','2');

INSERT INTO Users(user_id, first_name, last_name, user_name,password,otp, user_role) VALUES
    (213,'Cooper','Duke','CEO','$2a$10$s6SWT2GHCs675afbyBUdu.5AgibRl4EgPTDWKKXmZck4DhhDig29W', '000000','CEO');


-- Inserting data into Voter table

INSERT INTO Voter(list_sl_no, sl_no_in_part, epic_no, first_name, last_name, relative_first_name, relative_last_name, rln_type, gender, age, dob, c_house_no, district_id, district_name, constituency_id, constituency_name, part_id, part_name, section_no, pwd_yn, age_above_80_yn, category,is_eligible) VALUES
    (1,13,'RCA0370171','Amrut','Ganjigatti','Narayan','Ganjigatti','F','M',46,'1900-01-01',3,1,'North Goa',1, 'Mandrem',1,'Government Primary School\, Tiracol',1,'Y','N', 'AVPD',true),
    (2,14,'RCA0200832','Ankush','Ganjigatti','Narayan','Ganjigatti','F','M',45,'1974-03-29',3,1,'North Goa',1, 'Mandrem',1,'Government Primary School\, Tiracol',1,'Y','N', 'AVPD',true),
    (3,15,'JFF4923926','Ankita','Ganjigatti','Amruth','Ganjigatti','H','F',38,'1900-01-01',3,1,'North Goa',1, 'Mandrem',1,'Government Primary School\, Tiracol',1,'Y','N', 'AVPD',true),
    (4,16,'LJN1474048','Prashanti','D''Souza','Uddesh','D''Souza','H','F',34,'1986-02-27',4,1,'North Goa',1, 'Mandrem',1,'Government Primary School\, Tiracol',1,'N','Y', 'AVSC',true),
    (5,30,'RCA0370239','Radha','Bhaskar','Krishna','Bhaskar','H','F',53,'1900-01-01',6,1,'North Goa',1, 'Mandrem',1,'Government Primary School\, Tiracol',1,'N','Y', 'AVSC',true),
    (6,31,'RCA0263038','Gauresh','Bhaskar','Krishna','Bhaskar','F','M',27,'1993-03-25',6,1,'North Goa',1, 'Mandrem',1,'Government Primary School\, Tiracol',1,'N','Y', 'AVSC',true),
    (7,32,'RCA0377408','Chaitanya','Dessai','Krishna','Dessai','F','M',19,'2001-11-27',6,1,'North Goa',1, 'Mandrem',1,'Government Primary School\, Tiracol',1,'N','N', 'AVCO',true),
    (8,33,'MGX5455639','Savita','Dessai','Apa','Dessai','H','F',37,'1983-11-21',7,1,'North Goa',1, 'Mandrem',1,'Government Primary School\, Tiracol',1,'N','N', 'AVCO',true),
    (9,46,'RCA0201681','Ratan','Aguiar','Redio','Aguiar','F','F',58,'1900-01-01',9,1,'North Goa',1, 'Mandrem',1,'Government Primary School\, Tiracol',1,'N','N', 'AVCO',true),
    (10,47,'JFF2134658','Surekha','Aguiar','Ramdas','Aguiar','H','F',51,'1900-01-01',9,1,'North Goa',1, 'Mandrem',1,'Government Primary School\, Tiracol',1,'N','N', 'AVGE',false),
    (11,49,'RCA0200469','Padmavati','Almedia','Nagu','Almedia','H','F',71,'1900-01-01',10,1,'North Goa',1, 'Mandrem',2,'Government Primary School Talwada Querim',1,'Y','N','AVPD',false),
    (12,63,'RCA0370304','Satywati','Serrao','Sagun','Serrao','H','F',71,'1900-01-01',12,1,'North Goa',1, 'Mandrem',2,'Government Primary School Talwada Querim',1,'Y','N','AVPD',false),
    (13,64,'JFF1205186','Sunil','D''Souza','Sagun','D''Souza','F','M',42,'1900-01-01',12,1,'North Goa',1, 'Mandrem',2,'Government Primary School Talwada Querim',1,'N','N', 'AVGE',false),
    (14,65,'JFF1205202','Sandesh','D''Souza','Sagun','D''Souza','F','M',39,'1900-01-01',12,1,'North Goa',1, 'Mandrem',2,'Government Primary School Talwada Querim',1,'N','N', 'AVGE',false),
    (15,66,'RCA0281998','Laxmi','D''Souza','Sunil','D''Souza','H','F',32,'1988-07-21',12,1,'North Goa',1, 'Mandrem',2,'Government Primary School Talwada Querim',1,'N','N', 'AVEW',false),
    (16,80,'RCA0263046','Jaiwant','Naik','Arjun','Naik','F','M',27,'1993-01-21',14,1,'North Goa',1, 'Mandrem',2,'Government Primary School Talwada Querim',1,'N','N', 'AVEW',false),
    (17,81,'RCA0294769','Pratiksha','Naik','Arjun','Naik','F','F',25,'1995-04-18',14,1,'North Goa',1, 'Mandrem',2,'Government Primary School Talwada Querim',1,'N','N', 'AVEW',false),
    (18,82,'RCA0370247','Yamuna','Naik','Raghunath','Naik','H','F',74,'1900-01-01',15,1,'North Goa',1, 'Mandrem',2,'Government Primary School Talwada Querim',1,'N','Y', 'AVSC',false),
    (19,83,'RCA0370395','Prakash','Naik','Raghunath','Naik','F','M',51,'1900-01-01',15,1,'North Goa',1, 'Mandrem',2,'Government Primary School Talwada Querim',1,'Y','N', 'AVPD',false),
    (20,97,'RCA0321489','Gajain','Rodrigues','Vishwas','Antao Dos Reis','F','M',20,'2000-11-14',19,1,'North Goa',1, 'Mandrem',2,'Government Primary School Talwada Querim',1,'N','N', 'AVGE',false),
    (21,399,'JFF0030197','Lilawati','Mascarenhas','Topiyo','Mascarenhas','H','F',82,'1900-01-01',83,1,'North Goa',1, 'Mandrem',2,'Government Primary School Talwada Querim',4,'N','Y','AVSC',true);

INSERT INTO Visit (visit_id, voter_epic_no, voter_sl_no, voter_category, blo_id, is_physically_met, first_visit, form_12d_delivered, filled_form_12d_received, is_opting_for_postal_ballot,is_voter_expired,second_visit) VALUES
    ('1', 'RCA0370171', 1, 'AVPD', 1, true, true, true, true, true,false,false),
    ('2', 'RCA0200832', 2, 'AVPD', 1, true, true, false, false, false,false,false),
    ('3', 'JFF4923926', 3, 'AVPD', 1, true, true, true, false, false,false,false),
    ('4', 'LJN1474048', 4, 'AVSC', 1, true, true, true, true, true,false,false),
    ('5', 'RCA0370239', 5, 'AVSC', 1, true, true, true, true, false,false,false),
    ('6', 'RCA0263038', 6, 'AVSC', 1, false, true, false, false, false,false,true),
    ('7', 'RCA0377408', 7, 'AVCO', 1, true, true, true, true, true,false,false),
    ('8', 'MGX5455639', 8, 'AVCO', 1, true, true, false, false, false,false,false),
    ('9', 'RCA0201681', 9, 'AVCO', 1, true, true, true, true, false,false,false),
    ('10', 'JFF2134658', 10, 'AVGE', 1, true, true, true, true, true,false,false),
    ('11', 'RCA0281998', 15, 'AVEW', 1, true, true, true, true, true,false,false);