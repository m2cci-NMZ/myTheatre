--=========================================================================
-- CyberCinemas
--=========================================================================
-- Based on the course of M.C. Fauvet
---------------------------------------------------------------------------

---------------------------------------------------------------------------
--  This negative dataset tests all constraints defined in the schema
---------------------------------------------------------------------------

---------------------------------------------------------------------------
-- Prolog for sqlite
---------------------------------------------------------------------------

PRAGMA foreign_keys = ON;

---------------------------------------------------------------------------
-- Movies
---------------------------------------------------------------------------

INSERT INTO Movies VALUES ('Guardians Of The Galaxy','2014');
--@ violates Movie.PKINSERT INTO Movies VALUES ('Guardians Of The Galaxy','2014');
--@ violates Movie.PKINSERT INTO Spectacles VALUES (45,'Cyrano de Bergerac', 20.0 ,'toutPublic','drame');
INSERT INTO Spectacles VALUES(45,'Cyrano de Bergerac', 20.0 ,'toutPublic','drame');

INSERT INTO Movies VALUES ('Guardians Of The Galaxy','<==== VIOLATION');
INSERT INTO Movies VALUES ('The Inbetweeners 2','2014');
INSERT INTO Movies VALUES ('The Hundred Foot Journey','2014');
INSERT INTO Movies VALUES ('Lucy','2014');

