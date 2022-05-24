--liquibase formatted sql

--changeSet olga:1
CREATE INDEX student_name_index ON student (name);
--changeSet olga:2
CREATE INDEX faculty_name_color ON faculty (name, color);
