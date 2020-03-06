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
INSERT INTO LesSpectacles VALUES (46, 'Les Animaux', 8.0, 'unCinqAns', 'cirque');
INSERT INTO LesSpectacles VALUES (47, 'Sonorites Etranges', 10.0, 'jeunePublic', 'musical');
INSERT INTO LesSpectacles VALUES (20, 'L avare', 10.0, 'toutPublic', 'humoristique');
INSERT INTO LesHumoristiques VALUES (20, 0);
INSERT INTO LesSpectacles VALUES (25, 'Don Juan', 10.0, 'adulte', 'opera');
INSERT INTO LesOperas VALUES (25, 1);



---------------------------------------------------------------------------
-- LesRepresentations
---------------------------------------------------------------------------

INSERT INTO LesRepresentations VALUES ('14/03/2020 18h', 45);
INSERT INTO LesRepresentations VALUES ('13/03/2020 18h', 46);
INSERT INTO LesRepresentations VALUES ('14/03/2020 15h', 47);
INSERT INTO LesRepresentations VALUES ('13/03/2020 20h', 20);
INSERT INTO LesRepresentations VALUES ('15/03/2020 18h', 25);



---------------------------------------------------------------------------
-- Insertions Incorrectes
---------------------------------------------------------------------------

-----LesSpectacles-----
-- Spectacle avec le même numéro qu'un autre ou avec un numero invalide
INSERT INTO LesSpectacles VALUES (20, 'Andromaque', 10.0 ,'toutPublic','drame');

-- Entier <0 dans Spectacle
INSERT INTO LesSpectacles VALUES (-2, 'Le Cid', 10.0 ,'toutPublic','drame');  -- numeroSpe
INSERT INTO LesSpectacles VALUES (1, 'Le Cid', -10.0 ,'toutPublic','drame');   -- prixDeBaseSpe

-- Type et cible incorrectss
INSERT INTO LesSpectacles VALUES (2, 'Le Cid', 10.0 ,'tousPublique','drame');     -- cibleSpe
INSERT INTO LesSpectacles VALUES (3, 'Le Cid', 10.0 ,'toutPublic','dramatique');  -- typeSpe


-----LesHumoristes et LesOperas-----
-- Un humoriste pas dans la table des Spectacles
INSERT INTO LesHumoristiques VALUES (2, 0);
INSERT INTO LesOperas VALUES (3, 0);

-- Valeurs incorrectes pour les booléens
INSERT INTO LesSpectacles VALUES (4, 'Test', 10.0, 'jeunePublic', 'humoristique');
INSERT INTO LesHumoristiques VALUES (4, 4);
DELETE FROM LesSpectacles WHERE (numeroSpe = 4);  -- Supprime le Spectacle factice
INSERT INTO LesSpectacles VALUES (5, 'Test', 10.0, 'jeunePublic', 'opera');
INSERT INTO LesOperas VALUES (5, 3);
DELETE FROM LesSpectacles WHERE (numeroSpe = 5);  -- Supprime le Spectacle factice


-----LesRepresentations-----
-- Representations à la même heure
INSERT INTO LesRepresentations VALUES ('14/03/2020 18h', 25);

-- Representation pour un spectacle n'existant pas
INSERT INTO LesRepresentations VALUES ('14/03/2032 13h', 6);


