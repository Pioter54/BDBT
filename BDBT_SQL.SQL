/*
Created: 01.12.2024
Modified: 08.12.2024
Project: Klub wysokogórski
Model: Oracle 19c logicall
Company: Politechnika Warszawska
Author: Piotr Stępień, Rafał Pyda
Version: 1.0
Database: Oracle 19c
*/


-- Create sequences section -------------------------------------------------

CREATE SEQUENCE Sequence1
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Sequence2
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Sequence3
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Sequence4
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Sequence5
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Sequence6
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Sequence7
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Sequence8
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Sequence9
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

-- Create tables section -------------------------------------------------

-- Table Kluby_wysokogorskie

CREATE TABLE Kluby_wysokogorskie(
  Nr_klubu Integer NOT NULL,
  Data_zalozenia Date NOT NULL,
  Nazwa Varchar2(20 ) NOT NULL,
  Imie_wlasciciela Varchar2(20 ) NOT NULL,
  Nazwisko_wlasciciela Varchar2(30 ) NOT NULL,
  Nr_adresu Integer NOT NULL
)
/

-- Create indexes for table Kluby_wysokogorskie

CREATE INDEX IX_Adres_klubu ON Kluby_wysokogorskie (Nr_adresu)
/

-- Add keys for table Kluby_wysokogorskie

ALTER TABLE Kluby_wysokogorskie ADD CONSTRAINT Kluby_wysokogorskie_PK PRIMARY KEY (Nr_klubu)
/

-- Table Pracownicy

CREATE TABLE Pracownicy(
  Nr_pracownika Integer NOT NULL,
  Imie Varchar2(20 ) NOT NULL,
  Drugie_imie Varchar2(20 ),
  Nazwisko Varchar2(30 ) NOT NULL,
  Data_urodzenia Date NOT NULL,
  Pesel Char(11 ),
  Data_zatrudnienia Date NOT NULL,
  Data_zwolnienia Date,
  Plec Char(1 ) NOT NULL
        CONSTRAINT CheckConstraintA1 CHECK (Plec IN ('M', 'K'))
        CHECK (Plec IN ('M', 'K')),
  Telefon Varchar2(15 ) NOT NULL,
  Nr_klubu Integer NOT NULL,
  Nr_adresu Integer NOT NULL
)
/

-- Create indexes for table Pracownicy

CREATE INDEX IX_Klub_pracownika ON Pracownicy (Nr_klubu)
/

CREATE INDEX IX_Adres_pracownika ON Pracownicy (Nr_adresu)
/

-- Add keys for table Pracownicy

ALTER TABLE Pracownicy ADD CONSTRAINT PK_Pracownicy PRIMARY KEY (Nr_pracownika)
/

-- Table Schroniska

CREATE TABLE Schroniska(
  Nr_schroniska Integer NOT NULL,
  Nazwa Varchar2(30 ) NOT NULL,
  Nocleg Char(1 ) NOT NULL,
  Telefon Varchar2(15 ) NOT NULL,
  Nr_klubu Integer NOT NULL,
  Nr_adresu Integer NOT NULL
)
/

-- Create indexes for table Schroniska

CREATE INDEX IX_Klub_schroniska ON Schroniska (Nr_klubu)
/

CREATE INDEX IX_Adres_schroniska ON Schroniska (Nr_adresu)
/

-- Add keys for table Schroniska

ALTER TABLE Schroniska ADD CONSTRAINT PKK_Schroniska PRIMARY KEY (Nr_schroniska)
/

-- Table Wyprawy

CREATE TABLE Wyprawy(
  Nr_wycieczki Integer NOT NULL,
  Data_od Date NOT NULL,
  Data_do Date NOT NULL,
  Opis Varchar2(400 ) NOT NULL,
  Cena Number(10,2) NOT NULL,
  Nr_klubu Integer NOT NULL
)
/

-- Create indexes for table Wyprawy

CREATE INDEX IX_Wyprawa_klubu ON Wyprawy (Nr_klubu)
/

-- Add keys for table Wyprawy

ALTER TABLE Wyprawy ADD CONSTRAINT Inique_valiue234_PK PRIMARY KEY (Nr_wycieczki)
/

-- Table Czlonkowie_klubu

CREATE TABLE Czlonkowie_klubu(
  Nr_czlonka_klubu Integer NOT NULL,
  Pesel Char(11 ),
  Data_urodzeia Date NOT NULL,
  Plec Char(1 ) NOT NULL
        CONSTRAINT CheckConstraintA11 CHECK (Plec IN ('M', 'K'))
        CHECK (Plec IN ('M', 'K')),
  Telefon Varchar2(15 ) NOT NULL,
  Data_dolaczenia Date NOT NULL,
  Data_odejscia Date,
  Nr_klubu Integer NOT NULL,
  Nr_adresu Integer NOT NULL
)
/

-- Create indexes for table Czlonkowie_klubu

CREATE INDEX IX_Klub_czlonka ON Czlonkowie_klubu (Nr_klubu)
/

CREATE INDEX IX_Adres_czlonka ON Czlonkowie_klubu (Nr_adresu)
/

-- Add keys for table Czlonkowie_klubu

ALTER TABLE Czlonkowie_klubu ADD CONSTRAINT Unique_Identifier1 PRIMARY KEY (Nr_czlonka_klubu)
/

-- Table Szlaki

CREATE TABLE Szlaki(
  Nr_trasy Integer NOT NULL,
  Dlugosc Float NOT NULL,
  Trudnosc Char(1 ) NOT NULL
        CHECK (Trudnosc IN ('T','S','L')),
  Ekspozycja Char(1 ) NOT NULL,
  Nr_klubu Integer
)
/

-- Create indexes for table Szlaki

CREATE INDEX IX_Relationship1 ON Szlaki (Nr_klubu)
/

-- Add keys for table Szlaki

ALTER TABLE Szlaki ADD CONSTRAINT Unique_Identifier100 PRIMARY KEY (Nr_trasy)
/

-- Table Ratownicy

CREATE TABLE Ratownicy(
  Nr_pracownika Integer NOT NULL,
  Data_waznoscy_licencji Date NOT NULL,
  Licencja_pilota Char(1 ) NOT NULL
)
/

-- Add keys for table Ratownicy

ALTER TABLE Ratownicy ADD CONSTRAINT Unique_Identifier19 PRIMARY KEY (Nr_pracownika)
/

-- Table Instruktorzy

CREATE TABLE Instruktorzy(
  Nr_pracownika Integer NOT NULL,
  Przeszkolenie_przeciwlawinowe Char(1 ) NOT NULL
)
/

-- Add keys for table Instruktorzy

ALTER TABLE Instruktorzy ADD CONSTRAINT Unique_Identifier10 PRIMARY KEY (Nr_pracownika)
/

-- Table Ksiegowi

CREATE TABLE Ksiegowi(
  Nr_pracownika Integer NOT NULL,
  Dostep_do_sejfu Char(1 ) NOT NULL,
  Stopien_naukowy Varchar2(50 ) NOT NULL
)
/

-- Add keys for table Ksiegowi

ALTER TABLE Ksiegowi ADD CONSTRAINT Unique_Identifierr1 PRIMARY KEY (Nr_pracownika)
/

-- Table Obslugi

CREATE TABLE Obslugi(
  Nr_pracownika Integer NOT NULL,
  Nr_wycieczki Integer NOT NULL
)
/

-- Table Zawarcia

CREATE TABLE Zawarcia(
  Nr_wycieczki Integer NOT NULL,
  Nr_trasy Integer NOT NULL
)
/

-- Table Uczestnictwa

CREATE TABLE Uczestnictwa(
  Nr_czlonka_klubu Integer NOT NULL,
  Nr_wycieczki Integer NOT NULL
)
/

-- Table Wykorzystaia

CREATE TABLE Wykorzystaia(
  Nr_schroniska Integer NOT NULL,
  Nr_wycieczki Integer NOT NULL
)
/

-- Table Znajomosc_jezykow_obcych

CREATE TABLE Znajomosc_jezykow_obcych(
  Nr_pracownika Integer NOT NULL,
  Nr_jezyka Integer NOT NULL,
  Stopien_znajomosci Varchar2(2 ) NOT NULL
)
/

-- Add keys for table Znajomosc_jezykow_obcych

ALTER TABLE Znajomosc_jezykow_obcych ADD CONSTRAINT PK_Znajomosc_jezykow_obcych PRIMARY KEY (Nr_pracownika,Nr_jezyka)
/

-- Table Jezyki_obce

CREATE TABLE Jezyki_obce(
  Nr_jezyka Integer NOT NULL,
  Nazwa_jezyka Varchar2(30 ) NOT NULL
)
/

-- Add keys for table Jezyki_obce

ALTER TABLE Jezyki_obce ADD CONSTRAINT PK_Jezyki_obce PRIMARY KEY (Nr_jezyka)
/

-- Table Wynagrodzenia

CREATE TABLE Wynagrodzenia(
  Nr_wynagrodzenia Integer NOT NULL,
  Data_przyznania Date NOT NULL,
  Wysokosc_wynagrodzenia Number(10,2) NOT NULL,
  Premia Number(10,2),
  Nr_pracownika Integer NOT NULL
)
/

-- Create indexes for table Wynagrodzenia

CREATE INDEX IX_Wynagrodzenie_pracownika ON Wynagrodzenia (Nr_pracownika)
/

-- Add keys for table Wynagrodzenia

ALTER TABLE Wynagrodzenia ADD CONSTRAINT PK_Wynagrodzenia PRIMARY KEY (Nr_wynagrodzenia)
/

-- Table Adresy

CREATE TABLE Adresy(
  Nr_adresu Integer NOT NULL,
  Kraj Varchar2(20 ) NOT NULL,
  Miasto Varchar2(30 ) NOT NULL,
  Ulica Varchar2(40 ) NOT NULL,
  Nr_budynku Varchar2(6 ) NOT NULL,
  Nr_lokalu Varchar2(5 ),
  Kod_pocztowy Char(6 ) NOT NULL
)
/

-- Add keys for table Adresy

ALTER TABLE Adresy ADD CONSTRAINT PK_Adresy PRIMARY KEY (Nr_adresu)
/

-- Trigger for sequence Sequence1 for column Nr_klubu in table Kluby_wysokogorskie ---------
CREATE OR REPLACE TRIGGER ts_Kluby_wysokogorskie_Sequence1 BEFORE INSERT
ON Kluby_wysokogorskie FOR EACH ROW
BEGIN
  :new.Nr_klubu := Sequence1.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Kluby_wysokogorskie_Sequence1 AFTER UPDATE OF Nr_klubu
ON Kluby_wysokogorskie FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_klubu in table Kluby_wysokogorskie as it uses sequence.');
END;
/

-- Trigger for sequence Sequence2 for column Nr_pracownika in table Pracownicy ---------
CREATE OR REPLACE TRIGGER ts_Pracownicy_Sequence2 BEFORE INSERT
ON Pracownicy FOR EACH ROW
BEGIN
  :new.Nr_pracownika := Sequence2.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Pracownicy_Sequence2 AFTER UPDATE OF Nr_pracownika
ON Pracownicy FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_pracownika in table Pracownicy as it uses sequence.');
END;
/

-- Trigger for sequence Sequence8 for column Nr_schroniska in table Schroniska ---------
CREATE OR REPLACE TRIGGER ts_Schroniska_Sequence8 BEFORE INSERT
ON Schroniska FOR EACH ROW
BEGIN
  :new.Nr_schroniska := Sequence8.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Schroniska_Sequence8 AFTER UPDATE OF Nr_schroniska
ON Schroniska FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_schroniska in table Schroniska as it uses sequence.');
END;
/

-- Trigger for sequence Sequence6 for column Nr_wycieczki in table Wyprawy ---------
CREATE OR REPLACE TRIGGER ts_Wyprawy_Sequence6 BEFORE INSERT
ON Wyprawy FOR EACH ROW
BEGIN
  :new.Nr_wycieczki := Sequence6.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Wyprawy_Sequence6 AFTER UPDATE OF Nr_wycieczki
ON Wyprawy FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_wycieczki in table Wyprawy as it uses sequence.');
END;
/

-- Trigger for sequence Sequence9 for column Nr_trasy in table Szlaki ---------
CREATE OR REPLACE TRIGGER ts_Szlaki_Sequence9 BEFORE INSERT
ON Szlaki FOR EACH ROW
BEGIN
  :new.Nr_trasy := Sequence9.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Szlaki_Sequence9 AFTER UPDATE OF Nr_trasy
ON Szlaki FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_trasy in table Szlaki as it uses sequence.');
END;
/

-- Trigger for sequence Sequence4 for column Nr_jezyka in table Jezyki_obce ---------
CREATE OR REPLACE TRIGGER ts_Jezyki_obce_Sequence4 BEFORE INSERT
ON Jezyki_obce FOR EACH ROW
BEGIN
  :new.Nr_jezyka := Sequence4.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Jezyki_obce_Sequence4 AFTER UPDATE OF Nr_jezyka
ON Jezyki_obce FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_jezyka in table Jezyki_obce as it uses sequence.');
END;
/

-- Trigger for sequence Sequence5 for column Nr_wynagrodzenia in table Wynagrodzenia ---------
CREATE OR REPLACE TRIGGER ts_Wynagrodzenia_Sequence5 BEFORE INSERT
ON Wynagrodzenia FOR EACH ROW
BEGIN
  :new.Nr_wynagrodzenia := Sequence5.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Wynagrodzenia_Sequence5 AFTER UPDATE OF Nr_wynagrodzenia
ON Wynagrodzenia FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_wynagrodzenia in table Wynagrodzenia as it uses sequence.');
END;
/

-- Trigger for sequence Sequence3 for column Nr_adresu in table Adresy ---------
CREATE OR REPLACE TRIGGER ts_Adresy_Sequence3 BEFORE INSERT
ON Adresy FOR EACH ROW
BEGIN
  :new.Nr_adresu := Sequence3.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Adresy_Sequence3 AFTER UPDATE OF Nr_adresu
ON Adresy FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_adresu in table Adresy as it uses sequence.');
END;
/


-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE Pracownicy ADD CONSTRAINT Zatrudnia FOREIGN KEY (Nr_klubu) REFERENCES Kluby_wysokogorskie (Nr_klubu)
/



ALTER TABLE Schroniska ADD CONSTRAINT Jest_partnerem_z FOREIGN KEY (Nr_klubu) REFERENCES Kluby_wysokogorskie (Nr_klubu)
/



ALTER TABLE Czlonkowie_klubu ADD CONSTRAINT Posiada_czlonka FOREIGN KEY (Nr_klubu) REFERENCES Kluby_wysokogorskie (Nr_klubu)
/



ALTER TABLE Wyprawy ADD CONSTRAINT Organizuje FOREIGN KEY (Nr_klubu) REFERENCES Kluby_wysokogorskie (Nr_klubu)
/



ALTER TABLE Znajomosc_jezykow_obcych ADD CONSTRAINT Zna_jezyk FOREIGN KEY (Nr_pracownika) REFERENCES Instruktorzy (Nr_pracownika)
/



ALTER TABLE Znajomosc_jezykow_obcych ADD CONSTRAINT Jezyk_jest_znany FOREIGN KEY (Nr_jezyka) REFERENCES Jezyki_obce (Nr_jezyka)
/



ALTER TABLE Wynagrodzenia ADD CONSTRAINT Dostaje FOREIGN KEY (Nr_pracownika) REFERENCES Pracownicy (Nr_pracownika)
/



ALTER TABLE Kluby_wysokogorskie ADD CONSTRAINT Klub_ma_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Czlonkowie_klubu ADD CONSTRAINT Czlonek_ma_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Pracownicy ADD CONSTRAINT Pracownik_ma_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Schroniska ADD CONSTRAINT Schronisko_ma_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Szlaki ADD CONSTRAINT Szlak_klub FOREIGN KEY (Nr_klubu) REFERENCES Kluby_wysokogorskie (Nr_klubu)
/





