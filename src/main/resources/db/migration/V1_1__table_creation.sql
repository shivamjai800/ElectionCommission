CREATE TABLE if not exists District
(
    district_id SERIAL PRIMARY KEY,
    district_name VARCHAR(30) NOT NULL
);

CREATE TABLE if not exists Constituency
(
    constituency_id SERIAL PRIMARY KEY,
    constituency_name VARCHAR(30) NOT NULL,
    district_id INT NOT NULL CHECK (district_id > 0) REFERENCES District (district_id)
);

CREATE TABLE if not exists Part
(
    part_id SERIAL PRIMARY KEY,
    constituency_part_no INT NOT NULL,
    part_name TEXT NOT NULL,
    constituency_id INT NOT NULL CHECK (constituency_id > 0) REFERENCES Constituency (constituency_id),
    district_id INT NOT NULL CHECK (district_id > 0) REFERENCES District (district_id)
);

CREATE TABLE if not exists Users
(
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    mobile_number CHAR(10),
    otp VARCHAR(200),
    otp_generation_time TIMESTAMP,
    user_name VARCHAR(30),
    password VARCHAR (200),
    user_role VARCHAR(5) NOT NULL,
    part_id INT REFERENCES Part (part_id),
    constituency_id INT REFERENCES Constituency (constituency_id),
    district_id INT REFERENCES District (district_id)
);

CREATE TABLE if not exists Voter
(
    list_sl_no SERIAL NOT NULL,
    sl_no_in_part INT NOT NULL,
    epic_no CHAR(10) PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    relative_first_name VARCHAR(30),
    relative_last_name VARCHAR(30),
    rln_type CHAR(1),
    gender CHAR(1) NOT NULL,
    age INT NOT NULL CHECK (age > 0),
    dob DATE NOT NULL,
    c_house_no TEXT NOT NULL,
    mobile_no CHAR(10),
    district_id INT NOT NULL CHECK (district_id > 0) REFERENCES District (district_id),
    district_name VARCHAR(30) NOT NULL,
    constituency_id INT NOT NULL CHECK (constituency_id > 0) REFERENCES Constituency (constituency_id),
    constituency_name VARCHAR(30) NOT NULL,
    part_id INT NOT NULL CHECK (part_id > 0) REFERENCES Part (part_id),
    part_name TEXT NOT NULL,
    section_no INT NOT NULL,
    pwd_yn CHAR(1),
    age_above_80_yn CHAR(1),
    category VARCHAR(4),
    image TEXT,
    is_eligible BOOLEAN,
    is_vote_casted BOOLEAN
);

CREATE TABLE if not exists Visit
(
    visit_id TEXT PRIMARY KEY,
    voter_epic_no CHAR(10) NOT NULL REFERENCES Voter (epic_no),
    voter_sl_no INT,
    voter_category VARCHAR(4),
    blo_id INT NOT NULL CHECK (blo_id > 0) REFERENCES Users (user_id),
    voter_mobile_no CHAR(10),
    is_physically_met BOOLEAN,
    first_visit BOOLEAN,
    first_visit_remarks TEXT,
    first_visit_timestamp TIMESTAMP,
    first_visit_gps_coord_lat VARCHAR(30),
    first_visit_gps_coord_lon VARCHAR(30),
    is_voter_expired BOOLEAN,
    second_visit BOOLEAN,
    second_visit_remarks TEXT,
    second_visit_timestamp TIMESTAMP,
    second_visit_gps_coord_lat VARCHAR(30),
    second_visit_gps_coord_lon VARCHAR(30),
    form_12d_delivered BOOLEAN,
    form_12d_delivered_remarks TEXT,
    certificate_image_id VARCHAR (30),
    form_12d_image_id VARCHAR (30),
    selfie_with_voter_image_id VARCHAR (30),
    voter_id_image_id VARCHAR (30),
    filled_form_12d_received BOOLEAN,
    filled_form_12d_received_remarks TEXT,
    is_opting_for_postal_ballot BOOLEAN
);

CREATE TABLE if not exists Vote
(
    vote_id SERIAL PRIMARY KEY,
    voter_epic_no CHAR(10) NOT NULL REFERENCES Voter (epic_no),
    voter_category VARCHAR(4) NOT NULL,
    voter_first_name VARCHAR(30),
    voter_last_name VARCHAR(30),
    user_id INT NOT NULL REFERENCES Users (user_id),
    is_vote_casted BOOLEAN,
    vote_cast_timestamp TIMESTAMP,
    gps_coord_lat VARCHAR(30),
    gps_coord_lon VARCHAR(30),
    document_produced_for_identification TEXT
);
-- create sequence if not exists visit_id INCREMENT 1
--   MINVALUE 1
--   MAXVALUE 9223372036854775807
--   START 1
--   CACHE 1;
-- ALTER TABLE visit ALTER COLUMN visit_id SET DEFAULT TO_CHAR(nextval('visit_id'::regclass),'"visit"fm000000');
