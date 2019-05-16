CREATE TABLE characters_detail_anime (
  character_id    int NOT NULL, 
  detail_anime_id int NOT NULL, 
  role            int NULL, 
  PRIMARY KEY (character_id, 
  detail_anime_id));
CREATE TABLE detail_anime (
  detail_anime_id   int IDENTITY NOT NULL, 
  anime_id          int NOT NULL, 
  name_japan        varchar(50) NOT NULL, 
  name_english      varchar(50) NOT NULL, 
  series_type       varchar(50) NOT NULL, 
  total_episode     int NOT NULL, 
  airing_status     int NULL, 
  airing_start_date date NULL, 
  airing_end_date   date NULL, 
  airing_day        varchar(50) NULL, 
  airing_time       varchar(50) NULL, 
  sysnopsis         int NULL, 
  background        int NULL, 
  duration          int NULL, 
  source_type       varchar(50) NULL, 
  thumbnail         varchar(50) NULL, 
  genre_id          int NULL, 
  favorited         int NULL, 
  studio_id         int NOT NULL, 
  created_by        varchar(50) NULL, 
  created_date      datetime DEFAULT GETDATE() NULL, 
  status_active     int NULL, 
  status_confirm    int NULL, 
  PRIMARY KEY (detail_anime_id));
EXEC sp_addextendedproperty 
  @NAME = N'MS_Description', @VALUE = N'
TV - Television Series (usually aired on TV at some point in time)
OVA - Original Video Animation (these tend to not air over TV)
Movie - Anime movies
ONA - Original Net Animation
 ', 
  @LEVEL0TYPE = N'Schema', @LEVEL0NAME = N'dbo', 
  @LEVEL1TYPE = N'Table', @LEVEL1NAME = N'detail_anime', 
  @LEVEL2TYPE = N'Column', @LEVEL2NAME = N'series_type';
EXEC sp_addextendedproperty 
  @NAME = N'MS_Description', @VALUE = N'0 not yet airing
1 currently airing
2 finished airing', 
  @LEVEL0TYPE = N'Schema', @LEVEL0NAME = N'dbo', 
  @LEVEL1TYPE = N'Table', @LEVEL1NAME = N'detail_anime', 
  @LEVEL2TYPE = N'Column', @LEVEL2NAME = N'airing_status';
EXEC sp_addextendedproperty 
  @NAME = N'MS_Description', @VALUE = N'0 = belum di periksa
1 = ditolak
2 = diterima', 
  @LEVEL0TYPE = N'Schema', @LEVEL0NAME = N'dbo', 
  @LEVEL1TYPE = N'Table', @LEVEL1NAME = N'detail_anime', 
  @LEVEL2TYPE = N'Column', @LEVEL2NAME = N'status_confirm';
CREATE TABLE detail_character (
  detail_character_id int IDENTITY NOT NULL, 
  character_id        int NOT NULL, 
  name                varchar(50) NULL, 
  thumbnail           varchar(50) NULL, 
  favorited           int NULL, 
  about               varchar(255) NULL, 
  created_by          varchar(50) NULL, 
  created_date        datetime DEFAULT GETDATE() NOT NULL, 
  status_active       int NULL, 
  status_confirm      int NULL, 
  PRIMARY KEY (detail_character_id));
CREATE TABLE detail_episode (
  episode_id  int IDENTITY NOT NULL, 
  anime_id    int NOT NULL, 
  episode_no  int NULL, 
  title       int NULL, 
  airing_date date NULL, 
  synopsis    varchar(255) NULL, 
  PRIMARY KEY (episode_id));
CREATE TABLE detail_people (
  detail_people_id int IDENTITY NOT NULL, 
  people_id        int NOT NULL, 
  first_name       varchar(30) NOT NULL, 
  last_name        varchar(30) NOT NULL, 
  birth_day        date NOT NULL, 
  thumbnail        varchar(50) NULL, 
  favorited        int NULL, 
  about            varchar(255) NULL, 
  created_by       varchar(50) NULL, 
  created_date     datetime DEFAULT GETDATE() NULL, 
  status_active    int NULL, 
  status_confirm   int NULL, 
  PRIMARY KEY (detail_people_id));
CREATE TABLE detail_user_customer (
  customer_id int IDENTITY NOT NULL, 
  user_id     varchar(50) NOT NULL, 
  birth_day   date NULL, 
  PRIMARY KEY (customer_id));
CREATE TABLE detail_user_staff (
  staff_id     int IDENTITY NOT NULL, 
  user_id      varchar(50) NOT NULL, 
  birth_day    date NOT NULL, 
  phone_number varchar(50) NOT NULL, 
  address      varchar(50) NOT NULL, 
  role_staff   int NOT NULL, 
  PRIMARY KEY (staff_id));
EXEC sp_addextendedproperty 
  @NAME = N'MS_Description', @VALUE = N'1 = Super Admin
2 = Admin
3 = Verifikasi
4 = Manager', 
  @LEVEL0TYPE = N'Schema', @LEVEL0NAME = N'dbo', 
  @LEVEL1TYPE = N'Table', @LEVEL1NAME = N'detail_user_staff', 
  @LEVEL2TYPE = N'Column', @LEVEL2NAME = N'role_staff';
CREATE TABLE genres (
  genre_id      int IDENTITY NOT NULL, 
  name          varchar(30) NOT NULL, 
  created_by    varchar(50) NULL, 
  modified_by   varchar(50) NULL, 
  created_date  datetime DEFAULT GETDATE() NOT NULL, 
  modified_date datetime DEFAULT GETDATE() NOT NULL, 
  is_active     int DEFAULT 1 NOT NULL, 
  PRIMARY KEY (genre_id));
EXEC sp_addextendedproperty 
  @NAME = N'MS_Description', @VALUE = N'1 is active
0 is inactive', 
  @LEVEL0TYPE = N'Schema', @LEVEL0NAME = N'dbo', 
  @LEVEL1TYPE = N'Table', @LEVEL1NAME = N'genres', 
  @LEVEL2TYPE = N'Column', @LEVEL2NAME = N'is_active';
CREATE TABLE genres_detail_anime (
  genre_id        int NOT NULL, 
  detail_anime_id int NOT NULL, 
  PRIMARY KEY (genre_id, 
  detail_anime_id));
CREATE TABLE licensors (
  licensor_id   int IDENTITY NOT NULL, 
  name          varchar(30) NOT NULL, 
  created_by    varchar(50) NULL, 
  modified_by   varchar(50) NULL, 
  created_date  datetime DEFAULT GETDATE() NULL, 
  modified_date datetime DEFAULT GETDATE() NULL, 
  is_active     int DEFAULT 1 NOT NULL, 
  PRIMARY KEY (licensor_id));
EXEC sp_addextendedproperty 
  @NAME = N'MS_Description', @VALUE = N'1 is active
0 is inactive', 
  @LEVEL0TYPE = N'Schema', @LEVEL0NAME = N'dbo', 
  @LEVEL1TYPE = N'Table', @LEVEL1NAME = N'licensors', 
  @LEVEL2TYPE = N'Column', @LEVEL2NAME = N'is_active';
CREATE TABLE licensors_detail_anime (
  llicensor_id    int NOT NULL, 
  detail_anime_id int NOT NULL, 
  PRIMARY KEY (llicensor_id, 
  detail_anime_id));
CREATE TABLE master_anime (
  anime_id           int IDENTITY NOT NULL, 
  last_modified_date datetime DEFAULT GETDATE() NULL, 
  last_modified_by   varchar(50) NULL, 
  created_date       datetime DEFAULT GETDATE() NULL, 
  created_by         varchar(50) NULL, 
  status_delete      int NULL, 
  PRIMARY KEY (anime_id));
CREATE TABLE master_characters (
  character_id       int IDENTITY NOT NULL, 
  created_date       datetime DEFAULT GETDATE() NOT NULL, 
  created_by         varchar(50) NULL, 
  last_modified_date datetime DEFAULT GETDATE() NULL, 
  last_modified_by   varchar(50) NULL, 
  status_delete      int NULL, 
  PRIMARY KEY (character_id));
CREATE TABLE master_people (
  people_id          int IDENTITY NOT NULL, 
  created_by         varchar(50) NULL, 
  created_date       datetime DEFAULT GETDATE() NULL, 
  last_modified_by   varchar(50) NOT NULL, 
  last_modified_date datetime DEFAULT GETDATE() NULL, 
  status_delete      int NULL, 
  PRIMARY KEY (people_id));
CREATE TABLE master_user (
  user_id          varchar(50) NOT NULL, 
  name             varchar(50) NULL, 
  email            varchar(50) NOT NULL, 
  username         varchar(50) NOT NULL, 
  password         varchar(50) NOT NULL, 
  thumbnail        varchar(50) NULL, 
  role_user        int NOT NULL, 
  created_date     datetime DEFAULT GETDATE() NULL, 
  last_online_date datetime NULL, 
  is_active        int NOT NULL, 
  PRIMARY KEY (user_id));
EXEC sp_addextendedproperty 
  @NAME = N'MS_Description', @VALUE = N'1 = customer
2 = staff', 
  @LEVEL0TYPE = N'Schema', @LEVEL0NAME = N'dbo', 
  @LEVEL1TYPE = N'Table', @LEVEL1NAME = N'master_user', 
  @LEVEL2TYPE = N'Column', @LEVEL2NAME = N'role_user';
CREATE TABLE people_detail_anime (
  people_id       int NOT NULL, 
  detail_anime_id int NOT NULL, 
  PRIMARY KEY (people_id, 
  detail_anime_id));
CREATE TABLE producers (
  licensor_id   int IDENTITY NOT NULL, 
  name          varchar(30) NOT NULL, 
  created_by    varchar(50) NULL, 
  modified_by   varchar(50) NULL, 
  created_date  datetime DEFAULT GETDATE() NOT NULL, 
  modified_date datetime DEFAULT GETDATE() NOT NULL, 
  is_active     int DEFAULT 1 NOT NULL, 
  PRIMARY KEY (licensor_id));
EXEC sp_addextendedproperty 
  @NAME = N'MS_Description', @VALUE = N'1 is active
0 is inactive', 
  @LEVEL0TYPE = N'Schema', @LEVEL0NAME = N'dbo', 
  @LEVEL1TYPE = N'Table', @LEVEL1NAME = N'producers', 
  @LEVEL2TYPE = N'Column', @LEVEL2NAME = N'is_active';
CREATE TABLE producers_detail_anime (
  producer_id     int NOT NULL, 
  detail_anime_id int NOT NULL, 
  PRIMARY KEY (producer_id, 
  detail_anime_id));
CREATE TABLE studios (
  studio_id     int IDENTITY NOT NULL, 
  name          varchar(30) NOT NULL, 
  created_by    varchar(50) NULL, 
  modified_by   varchar(50) NULL, 
  created_date  datetime DEFAULT GETDATE() NOT NULL, 
  modified_date datetime DEFAULT GETDATE() NOT NULL, 
  is_active     int DEFAULT 1 NOT NULL, 
  PRIMARY KEY (studio_id));
EXEC sp_addextendedproperty 
  @NAME = N'MS_Description', @VALUE = N'1 is active
0 is inactive', 
  @LEVEL0TYPE = N'Schema', @LEVEL0NAME = N'dbo', 
  @LEVEL1TYPE = N'Table', @LEVEL1NAME = N'studios', 
  @LEVEL2TYPE = N'Column', @LEVEL2NAME = N'is_active';
ALTER TABLE detail_anime ADD CONSTRAINT FKdetail_ani4391 FOREIGN KEY (anime_id) REFERENCES master_anime (anime_id);
ALTER TABLE detail_episode ADD CONSTRAINT FKdetail_epi143974 FOREIGN KEY (anime_id) REFERENCES detail_anime (detail_anime_id);
ALTER TABLE detail_anime ADD CONSTRAINT FKdetail_ani604464 FOREIGN KEY (studio_id) REFERENCES studios (studio_id);
ALTER TABLE genres_detail_anime ADD CONSTRAINT FKgenres_det159557 FOREIGN KEY (genre_id) REFERENCES genres (genre_id);
ALTER TABLE genres_detail_anime ADD CONSTRAINT FKgenres_det373760 FOREIGN KEY (detail_anime_id) REFERENCES detail_anime (detail_anime_id);
ALTER TABLE licensors_detail_anime ADD CONSTRAINT FKlicensors_236918 FOREIGN KEY (llicensor_id) REFERENCES licensors (licensor_id);
ALTER TABLE licensors_detail_anime ADD CONSTRAINT FKlicensors_254925 FOREIGN KEY (detail_anime_id) REFERENCES detail_anime (detail_anime_id);
ALTER TABLE people_detail_anime ADD CONSTRAINT FKpeople_det367547 FOREIGN KEY (people_id) REFERENCES detail_people (detail_people_id);
ALTER TABLE people_detail_anime ADD CONSTRAINT FKpeople_det510087 FOREIGN KEY (detail_anime_id) REFERENCES detail_anime (detail_anime_id);
ALTER TABLE producers_detail_anime ADD CONSTRAINT FKproducers_799014 FOREIGN KEY (producer_id) REFERENCES producers (licensor_id);
ALTER TABLE producers_detail_anime ADD CONSTRAINT FKproducers_121657 FOREIGN KEY (detail_anime_id) REFERENCES detail_anime (detail_anime_id);
ALTER TABLE detail_user_customer ADD CONSTRAINT FKdetail_use720863 FOREIGN KEY (user_id) REFERENCES master_user (user_id);
ALTER TABLE detail_user_staff ADD CONSTRAINT FKdetail_use9073 FOREIGN KEY (user_id) REFERENCES master_user (user_id);
ALTER TABLE master_anime ADD CONSTRAINT FKmaster_ani833238 FOREIGN KEY (created_by) REFERENCES master_user (user_id);
ALTER TABLE master_anime ADD CONSTRAINT FKmaster_ani731628 FOREIGN KEY (last_modified_by) REFERENCES master_user (user_id);
ALTER TABLE detail_anime ADD CONSTRAINT FKdetail_ani149817 FOREIGN KEY (created_by) REFERENCES master_user (user_id);
ALTER TABLE studios ADD CONSTRAINT FKstudios580918 FOREIGN KEY (created_by) REFERENCES master_user (user_id);
ALTER TABLE studios ADD CONSTRAINT FKstudios680922 FOREIGN KEY (modified_by) REFERENCES master_user (user_id);
ALTER TABLE characters_detail_anime ADD CONSTRAINT FKcharacters203903 FOREIGN KEY (character_id) REFERENCES master_characters (character_id);
ALTER TABLE characters_detail_anime ADD CONSTRAINT FKcharacters899867 FOREIGN KEY (detail_anime_id) REFERENCES detail_anime (detail_anime_id);
ALTER TABLE licensors ADD CONSTRAINT FKlicensors121294 FOREIGN KEY (created_by) REFERENCES master_user (user_id);
ALTER TABLE licensors ADD CONSTRAINT FKlicensors978709 FOREIGN KEY (modified_by) REFERENCES master_user (user_id);
ALTER TABLE detail_character ADD CONSTRAINT FKdetail_cha530571 FOREIGN KEY (character_id) REFERENCES master_characters (character_id);
ALTER TABLE detail_people ADD CONSTRAINT FKdetail_peo523210 FOREIGN KEY (people_id) REFERENCES master_people (people_id);
ALTER TABLE detail_character ADD CONSTRAINT FKdetail_cha877644 FOREIGN KEY (created_by) REFERENCES master_user (user_id);
ALTER TABLE master_characters ADD CONSTRAINT FKmaster_cha322768 FOREIGN KEY (created_by) REFERENCES master_user (user_id);
ALTER TABLE master_characters ADD CONSTRAINT FKmaster_cha221158 FOREIGN KEY (last_modified_by) REFERENCES master_user (user_id);
ALTER TABLE detail_people ADD CONSTRAINT FKdetail_peo763665 FOREIGN KEY (created_by) REFERENCES master_user (user_id);
ALTER TABLE master_people ADD CONSTRAINT FKmaster_peo749158 FOREIGN KEY (created_by) REFERENCES master_user (user_id);
ALTER TABLE master_people ADD CONSTRAINT FKmaster_peo850768 FOREIGN KEY (last_modified_by) REFERENCES master_user (user_id);
ALTER TABLE genres ADD CONSTRAINT FKgenres224287 FOREIGN KEY (created_by) REFERENCES master_user (user_id);
ALTER TABLE genres ADD CONSTRAINT FKgenres647299 FOREIGN KEY (modified_by) REFERENCES master_user (user_id);
ALTER TABLE producers ADD CONSTRAINT FKproducers879989 FOREIGN KEY (created_by) REFERENCES master_user (user_id);
ALTER TABLE producers ADD CONSTRAINT FKproducers991596 FOREIGN KEY (modified_by) REFERENCES master_user (user_id);

