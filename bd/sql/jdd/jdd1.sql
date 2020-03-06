--=========================================================================
-- 
--=========================================================================
-- Based on the course of M.C. Fauvet
---------------------------------------------------------------------------



---------------------------------------------------------------------------
-- Prolog for sqlite
---------------------------------------------------------------------------

PRAGMA foreign_keys = ON;

---------------------------------------------------------------------------
-- Spectacle
---------------------------------------------------------------------------

INSERT INTO Spectale VALUES (45,'Cyrano de Bergerac', 20.0 ,'toutPublic','drame');
INSERT INTO Spectale VALUES (20,'L avare', 10.0,'toutPublic', 'humoristique',0);
INSERT INTO Spectale VALUES (25,'Don Juan', 10.0,'adulte', 'opera',1);
INSERT INTO Spectale VALUES (46,'Les Animaux', 8.0,'unCinqAns', 'cirque');
INSERT INTO Spectale VALUES (47,'Sonorites Etranges', 10.0,'jeunePublic', 'musical');


---------------------------------------------------------------------------
-- Insertions Incorrectes
---------------------------------------------------------------------------

-----LesSpectacles-----
-- Spectacle avec le même numéro qu'un autre ou avec un numero invalide
INSERT INTO LesSpectacles VALUES (20, 'Andromaque', 10.0 ,'toutPublic','drame');

-- Entier <0 dans Spectacle
INSERT INTO LesSpectacles VALUES (-2, 'Le Cid', 10.0 ,'toutPublic','drame');  -- numeroSpe
INSERT INTO LesSpectacles VALUES (1, 'Le Cid', 10.0 ,'toutPublic','drame');   -- prixDeBaseSpe

-- Type et cible incorrectss
INSERT INTO LesSpectacles VALUES (2, 'Le Cid', 10.0 ,'tousPublique','drame');     -- cibleSpe
INSERT INTO LesSpectacles VALUES (3, 'Le Cid', 10.0 ,'toutPublic','dramatique');  -- typeSpe


-----LesHumoristes et LesOperas-----
-- Un humoriste pas dans la table des Spectacles
INSERT INTO LesHumoristiques VALUES (2, 0);
INSERT INTO LesOperas VALUES (3, 1);


-----LesRepresentations-----
-- Representations à la même heure
INSERT INTO LesRepresentations VALUES ('14/03/2020 18h', 25);

-- Representation pour un spectacle n'existant pas
INSERT INTO LesRepresentations VALUES ('14/03/2032 13h', 4);











