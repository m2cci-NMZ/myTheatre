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
---------------------------------------------------------------------------

INSERT INTO LesSpectacles VALUES (45, 'Cyrano de Bergerac', 20.0, 'toutPublic', 'drame');
INSERT INTO LesSpectacles VALUES (46, 'Les Animaux', 9.5, 'unCinqAns', 'cirque');
INSERT INTO LesSpectacles VALUES (47, 'Sonorites Etranges', 10.0, 'jeunePublic', 'musical');
INSERT INTO LesSpectacles VALUES (20, 'L''avare', 10.0, 'toutPublic', 'humoristique');
INSERT INTO LesHumoristiques VALUES (20, 0);
INSERT INTO LesSpectacles VALUES (25, 'Don Juan', 10.0, 'adulte', 'opera');
INSERT INTO LesOperas VALUES (25, 1);
INSERT INTO LesSpectacles VALUES (17, 'Andromaque', 15.0, 'adulte', 'drame');

-- Jamais programmé
INSERT INTO LesSpectacles VALUES (15, 'Bel Ami', 10, 'jeunePublic', 'drame');

-- Ajouts supplémentaires pour agrémenter la BD
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


--------------------------------------------------------------------------
-- LesRepresentations
---------------------------------------------------------------------------

INSERT INTO LesRepresentations VALUES ('2020-04-14 18:00', 45);
INSERT INTO LesRepresentations VALUES ('2020-04-13 18:00', 46);
INSERT INTO LesRepresentations VALUES ('2020-04-14 15:00', 47);
INSERT INTO LesRepresentations VALUES ('2020-04-13 20:00', 20);
INSERT INTO LesRepresentations VALUES ('2020-04-15 20:00', 20);
INSERT INTO LesRepresentations VALUES ('2020-04-15 18:00', 25);

-- Ajouts supplémentaires pour agrémenter la BD
INSERT INTO LesRepresentations VALUES ('2020-03-31 20:00', 46);
INSERT INTO LesRepresentations VALUES ('2020-04-01 18:00', 22);
INSERT INTO LesRepresentations VALUES ('2020-04-09 20:00', 25);
INSERT INTO LesRepresentations VALUES ('2020-04-10 18:30', 17);
INSERT INTO LesRepresentations VALUES ('2020-04-10 20:30', 28);
INSERT INTO LesRepresentations VALUES ('2020-04-11 22:00', 24);
INSERT INTO LesRepresentations VALUES ('2020-04-12 12:00', 23);
INSERT INTO LesRepresentations VALUES ('2020-04-13 22:00', 17);
INSERT INTO LesRepresentations VALUES ('2020-04-15 22:00', 45);
INSERT INTO LesRepresentations VALUES ('2020-04-19 15:00', 27);
INSERT INTO LesRepresentations VALUES ('2020-04-21 18:00', 24);
INSERT INTO LesRepresentations VALUES ('2020-04-21 20:00', 25);
INSERT INTO LesRepresentations VALUES ('2020-04-21 23:00', 17);
INSERT INTO LesRepresentations VALUES ('2020-04-22 18:00', 21);
INSERT INTO LesRepresentations VALUES ('2020-04-22 21:00', 26);




