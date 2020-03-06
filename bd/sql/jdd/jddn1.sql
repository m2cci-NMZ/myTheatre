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
-- LesSpectacles
---------------------------------------------------------------------------

-- Spectacle avec le même numéro qu'un autre ou avec un numero invalide
--@ violates PK_Spe
INSERT INTO LesSpectacles VALUES (20, 'Andromaque', 10.0 ,'toutPublic','drame');

-- Entier <0 dans Spectacle
--@ violates CK_Spe_numeroSpe
INSERT INTO LesSpectacles VALUES (-2, 'Le Cid', 10.0 ,'toutPublic','drame');
--@ violates CK_Spe_prixDeBaseSpe
INSERT INTO LesSpectacles VALUES (1, 'Le Cid', -10.0 ,'toutPublic','drame');

-- Type et cible incorrectss
--@ violates CK_Spe_cibleSpe
INSERT INTO LesSpectacles VALUES (2, 'Le Cid', 10.0 ,'tousPublique','drame');
--@ violates CK_Spe_typeSpe
INSERT INTO LesSpectacles VALUES (3, 'Le Cid', 10.0 ,'toutPublic','dramatique');



---------------------------------------------------------------------------
-- LesHumoristiques et LesOperas
---------------------------------------------------------------------------

-- Un humoriste pas dans la table des Spectacles
--@ violates FK_Hum_numeroSpe
INSERT INTO LesHumoristiques VALUES (2, 0);
--@ violates FK_ope_numeroSpe
INSERT INTO LesOperas VALUES (3, 0);

-- Valeurs incorrectes pour les booléens
INSERT INTO LesSpectacles VALUES (4, 'Test', 10.0, 'jeunePublic', 'humoristique');
--@ violates CK_Hum_estUnOneWomanManShow
INSERT INTO LesHumoristiques VALUES (4, 4);
DELETE FROM LesSpectacles WHERE (numeroSpe = 4);  -- Supprime le Spectacle factice

INSERT INTO LesSpectacles VALUES (5, 'Test', 10.0, 'jeunePublic', 'opera');
--@ violates CK_Ope_aUnOrchestre
INSERT INTO LesOperas VALUES (5, 3);
DELETE FROM LesSpectacles WHERE (numeroSpe = 5);  -- Supprime le Spectacle factice



---------------------------------------------------------------------------
-- LesRepresentations
---------------------------------------------------------------------------
-- Representations à la même heure
--@ violates PK_Rep
INSERT INTO LesRepresentations VALUES ('14/03/2020 18h', 25);

-- Representation pour un spectacle n'existant pas
--@ violates FK_Rep_numeroSpe
INSERT INTO LesRepresentations VALUES ('14/03/2032 13h', 6);


