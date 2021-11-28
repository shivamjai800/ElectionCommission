CREATE TYPE "category" AS ENUM (
  'AVCO',
  'AVPD',
  'AVSC',
  'ElectionDuty'
);

CREATE TABLE "Users" (
  "user_id" SERIAL PRIMARY KEY,
  "first_name" varchar,
  "last_name" varchar,
  "mobile_number" int,
  "role" varchar,
  "part_id" int,
  "constituency_id" int,
  "district_id" int
);

CREATE TABLE "District" (
  "district_id" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "district_name" varchar
);

CREATE TABLE "Constituency" (
  "constituency_id" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "constituency_name" varchar,
  "district_id" int
);

CREATE TABLE "Part" (
  "part_id" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "part_name" varchar,
  "constituency_id" int,
  "district_id" int
);

CREATE TABLE "Voter" (
  "epic_no" int UNIQUE PRIMARY KEY NOT NULL,
  "category" varchar,
  "first_name" varchar,
  "last_name" varchar,
  "fathers_name" varchar,
  "address" varchar,
  "mobile_no" varchar,
  "gender" varchar,
  "age" int,
  "dob" varchar,
  "constituency_id" int,
  "constituency_name" varchar,
  "part_no" int,
  "part_name" varchar,
  "part_sl_no" int,
  "list_sl_no" int
);

CREATE TABLE "Visit" (
  "visit_id" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "voter_epic_no" int,
  "voter_sl_no" int,
  "voter_category" varchar,
  "blo_id" int,
  "voter_mobile_no" int,
  "first_visit" boolean,
  "first_visit_date" varchar,
  "first_visit_remarks" varchar,
  "is_voter_expired" boolean,
  "second_visit" boolean,
  "second_visit_date" varchar,
  "second_visit_remarks" varchar,
  "form_12d_delivered" boolean,
  "form_12d_delivered_remarks" varchar,
  "certificate_image_id" int,
  "event_image_id" int,
  "filled_form_12d_received" boolean,
  "filled_form_12d_received_remarks" varchar,
  "is_opting_for_postal_ballot" boolean
);

CREATE TABLE "Vote" (
  "vote_id" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "voter_epic_no" int,
  "voter_category" varchar,
  "voter_first_name" varchar,
  "voter_last_name" varchar,
  "blo_id" int,
  "is_vote_casted" boolean,
  "vote_cast_date" varchar,
  "document_produced_for_identification" varchar
);

ALTER TABLE "Users" ADD FOREIGN KEY ("part_id") REFERENCES "Part" ("part_id");

ALTER TABLE "Users" ADD FOREIGN KEY ("constituency_id") REFERENCES "Constituency" ("constituency_id");

ALTER TABLE "Users" ADD FOREIGN KEY ("district_id") REFERENCES "District" ("district_id");

ALTER TABLE "Constituency" ADD FOREIGN KEY ("district_id") REFERENCES "District" ("district_id");

ALTER TABLE "Part" ADD FOREIGN KEY ("constituency_id") REFERENCES "Constituency" ("constituency_id");

ALTER TABLE "Part" ADD FOREIGN KEY ("district_id") REFERENCES "District" ("district_id");

ALTER TABLE "Voter" ADD FOREIGN KEY ("constituency_id") REFERENCES "Constituency" ("constituency_id");

ALTER TABLE "Voter" ADD FOREIGN KEY ("part_no") REFERENCES "Part" ("part_id");

ALTER TABLE "Visit" ADD FOREIGN KEY ("voter_epic_no") REFERENCES "Voter" ("epic_no");

ALTER TABLE "Visit" ADD FOREIGN KEY ("voter_category") REFERENCES "Voter" ("category");

ALTER TABLE "Visit" ADD FOREIGN KEY ("voter_mobile_no") REFERENCES "Voter" ("mobile_no");

ALTER TABLE "Vote" ADD FOREIGN KEY ("voter_epic_no") REFERENCES "Voter" ("epic_no");

ALTER TABLE "Vote" ADD FOREIGN KEY ("voter_category") REFERENCES "Voter" ("category");

ALTER TABLE "Vote" ADD FOREIGN KEY ("voter_first_name") REFERENCES "Voter" ("first_name");

ALTER TABLE "Vote" ADD FOREIGN KEY ("voter_last_name") REFERENCES "Voter" ("last_name");
