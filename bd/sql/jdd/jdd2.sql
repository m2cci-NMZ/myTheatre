--=========================================================================
-- MyTheatre
--=========================================================================
-- Based on the course of M.C. Fauvet
---------------------------------------------------------------------------



---------------------------------------------------------------------------
-- Prolog for sqlite
---------------------------------------------------------------------------

PRAGMA foreign_keys = ON;

---------------------------------------------------------------------------
-- LesSpectacles
---------------------------------------------------------------------------

-- Jamais programmé
INSERT INTO LesSpectacles VALUES (15, 'Bel Ami', 10, 'jeunePublic', 'drame');

INSERT INTO LesSpectacles VALUES (17, 'Andromaque', 15.0, 'adulte', 'drame');
INSERT INTO LesSpectacles VALUES (18, 'Les Accrobates', 5, 'unCinqAns', 'cirque');
INSERT INTO LesSpectacles VALUES (19, 'Le Prenom', 17.75, 'toutPublic', 'humoristique');
INSERT INTO LesHumoristiques VALUES (19, 0);
INSERT INTO LesSpectacles VALUES (20, 'L''avare', 10.0, 'toutPublic', 'humoristique');
INSERT INTO LesHumoristiques VALUES (20, 0);
INSERT INTO LesSpectacles VALUES (25, 'Don Juan', 10.0, 'adulte', 'opera');
INSERT INTO LesOperas VALUES (25, 1);
INSERT INTO LesSpectacles VALUES (21, 'Le Cid', 15.5, 'toutPublic', 'drame');
INSERT INTO LesSpectacles VALUES (22, 'Le Malade Imaginaire', 12.0, 'toutPublic', 'humoristique');
INSERT INTO LesHumoristiques VALUES (22, 0);
INSERT INTO LesSpectacles VALUES (23, 'Les Perses', 15.5, 'adulte', 'drame');
INSERT INTO LesSpectacles VALUES (24, 'Romeo et Juliette', 20, 'toutPublic', 'drame');
INSERT INTO LesSpectacles VALUES (26, 'Symphony', 25.0, 'adulte', 'opera');
INSERT INTO LesOperas VALUES (26, 1);
INSERT INTO LesSpectacles VALUES (27, 'Tous Petits', 5.5, 'unCinqAns', 'musical');
INSERT INTO LesSpectacles VALUES (28, 'TwoWomanShow', 20.0, 'toutPublic', 'humoristique');
INSERT INTO LesHumoristiques VALUES (28, 1);
INSERT INTO LesSpectacles VALUES (29, 'Famille Poule', 15.5, 'jeunePublic', 'musical');
INSERT INTO LesSpectacles VALUES (45, 'Cyrano de Bergerac', 20.0, 'toutPublic', 'drame');
INSERT INTO LesSpectacles VALUES (46, 'Les Animaux', 9.5, 'unCinqAns', 'cirque');
INSERT INTO LesSpectacles VALUES (47, 'Sonorites Etranges', 10.0, 'jeunePublic', 'musical');


--------------------------------------------------------------------------
-- LesRepresentations
---------------------------------------------------------------------------
INSERT INTO LesRepresentations_base VALUES('2020-03-13 18:00', 46, 0.0);
INSERT INTO LesRepresentations_base VALUES('2020-03-15 20:00', 20, 0.2);
INSERT INTO LesRepresentations_base VALUES('2020-03-13 20:00', 20, 0.0);

INSERT INTO LesRepresentations_base VALUES ('2020-03-28 20:00', 46, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-29 18:00', 22, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-06 20:00', 25, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-07 18:30', 17, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-07 20:30', 28, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-08 22:00', 24, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-09 12:00', 23, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-10 18:00', 46, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-10 20:00', 20, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-10 22:00', 17, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-11 15:00', 47, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-11 18:00', 45, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-12 18:00', 25, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-12 20:00', 20, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-12 22:00', 45, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-16 15:00', 27, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-18 18:00', 24, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-18 20:00', 25, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-18 23:00', 17, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-19 18:00', 21, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-19 21:00', 26, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-22 12:00', 18, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-23 20:00', 19, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-23 22:00', 17, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-04-24 15:00', 29, 0.0);


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