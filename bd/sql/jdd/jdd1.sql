--=========================================================================
-- MyTheatre
--=========================================================================
-- Based on the course of M.C. Fauvet
---------------------------------------------------------------------------



---------------------------------------------------------------------------
-- Prolog for sqlite
---------------------------------------------------------------------------s

PRAGMA foreign_keys = ON;

---------------------------------------------------------------------------
-- LesSpectacles
--------------------------------------------------------------------------
INSERT INTO LesSpectacles VALUES (46, 'Les Animaux', 9.5, 'unCinqAns', 'cirque');
INSERT INTO LesSpectacles VALUES (20, 'L''avare', 10.0, 'toutPublic', 'humoristique');
INSERT INTO LesHumoristiques VALUES (20, 0);
INSERT INTO LesSpectacles VALUES (17, 'Andromaque', 15.0, 'adulte', 'drame');


--------------------------------------------------------------------------
-- LesRepresentations_base
---------------------------------------------------------------------------
INSERT INTO LesRepresentations_base VALUES('2020-03-13 18:00', 46, 0.0);
INSERT INTO LesRepresentations_base VALUES('2020-03-15 20:00', 20, 0.2);
INSERT INTO LesRepresentations_base VALUES('2020-03-13 20:00', 20, 0.0);


--------------------------------------------------------------------------
-- LesRang
----------------------------------------------------------------------
INSERT INTO LesRangs VALUES (1);
INSERT INTO LesRangs VALUES (5);


--------------------------------------------------------------------------
-- LesPlaces
----------------------------------------------------------------------
INSERT INTO LesPlaces VALUES (1, 5);
INSERT INTO LesPlaces VALUES (1, 10);
INSERT INTO LesPlaces VALUES (5, 40);


--------------------------------------------------------------------------
-- LesDossierAchats_base
----------------------------------------------------------------------
INSERT INTO LesDossiersAchats_base VALUES (1);
INSERT INTO LesDossiersAchats_base VALUES (25);


--------------------------------------------------------------------------
-- LesTickets_base
---------------------------------------------------------------------------
INSERT INTO LesTickets_base VALUES ('2020-03-15 20:00', 1, 5, 3, '2020-03-10 18:20:10', 25);
INSERT INTO LesTickets_base VALUES ('2020-03-13 18:00', 1, 5, 1, '2020-03-01 17:45:25', 1);
INSERT INTO LesTickets_base VALUES ('2020-03-13 18:00', 1, 10, 2, '2020-03-05 14:45:35', 1);


--------------------------------------------------------------------------
-- LesUtilisateurs
---------------------------------------------------------------------------
INSERT INTO LesUtilisateurs VALUES ('robin32','Miquel','Robin','robin32@gmail','azerty');
INSERT INTO LesUtilisateurs VALUES ('nico58','Martinez','Nicolas','nico58@gmail','admin');
INSERT INTO LesUtilisateurs VALUES ('celia18','kezmane','celia','celia18@gmail','admin');


-------------------------------------------------------------------------
-- LesTicketsReserves
----------------------------------------------------------------------
INSERT INTO LesTicketsReserves VALUES (3,'robin32');


--------------------------------------------------------------------------
-- LesTicketsAchetes
----------------------------------------------------------------------
INSERT INTO LesTicketsAchetes VALUES (1,'nico58');
INSERT INTO LesTicketsAchetes VALUES (2,'nico58');

