--=========================================================================
-- MyTheatre
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
INSERT INTO LesSpectacles VALUES (20, 'L avare', 10.0, 'toutPublic', 'drame');
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
-- LesRepresentations_base
---------------------------------------------------------------------------
 --Representations à la même heure
INSERT INTO LesRepresentations_base VALUES ('13/03/2020 20h',20,0.0);

--@ violates PK_Rep
INSERT INTO LesRepresentations_base VALUES ('13/03/2020 20h',46, 0.0);

-- Representation pour un spectacle n'existant pas
--@ violates FK_Rep_numeroSpe
INSERT INTO LesRepresentations_base VALUES ('14/03/2032 13h', 6,0.4);

--@ violates DOM_Rep_tauxReducRep
INSERT INTO LesRepresentations_base VALUES ('14/03/2032 13h',20,1.5);
INSERT INTO LesRepresentations_base VALUES ('14/03/2032 13h',20,1);


---------------------------------------------------------------------------
-- LesRangs
---------------------------------------------------------------------------

INSERT INTO LesRangs VALUES (5);

--@ violates PK_Ran
INSERT INTO LesRangs VALUES (5);


--@ violates DOM_Ran_numeroRan
INSERT INTO LesRangs VALUES (0);
INSERT INTO LesRangs VALUES (-2);

---------------------------------------------------------------------------
-- LesPlaces
---------------------------------------------------------------------------
  INSERT INTO LesPlaces VALUES (5,40);

 --@ violates PK_Pla
 INSERT INTO LesPlaces VALUES (5,40);

--@ violates FK_Pla_numeroRan
 INSERT INTO LesPlaces VALUES (3,15);


--@ violates DOM_Pla_numeroPla
 INSERT INTO LesPlaces VALUES (1,0);
 INSERT INTO LesPlaces VALUES (5,-3);

---------------------------------------------------------------------------
-- LesDossiersAchats_base
---------------------------------------------------------------------------

INSERT INTO LesDossiersAchats_base VALUES (25);

--@ violates PK_Dos

INSERT INTO LesDossiersAchats_base VALUES (25);



--@ violates DOM_Dos_numeroDos
INSERT INTO LesDossiersAchats_base VALUES (0);
INSERT INTO LesDossiersAchats_base VALUES (-5);


-- ---------------------------------------------------------------------------
-- LesTickets_base
-------------------------------------------------------------------------------

----INSERT INTO LesTickets_base VALUES ('2020-03-15 20:00', 1, 5, 3, '2020-03-10 18:20:10', 25);

--@ violates PK_Tic
--INSERT INTO LesTickets_base VALUES ('2020-03-15 20:00', 1, 5, 3, '2020-03-11 17:20:20', 25);
--INSERT INTO LesTickets_base VALUES ('2020-03-12 16:00', 15, 12, 2, '2020-03-06 13:42:30', 30);

--@ violates DOM_Tic_numeroTic
--INSERT INTO LesTickets_base VALUES ('2020-03-27 18:00', 12, 3, 6, '2020-03-20 17:20:10', -25);
--INSERT INTO LesTickets_base VALUES ('2020-03-13 18:00', 1, 5, 1, '2020-03-02 16:45:20', -25);


-- ---------------------------------------------------------------------------
-- LesUtilisateurs
---------------------------------------------------------------------------
INSERT INTO LesUtilisateurs VALUES ('robin32','Miquel','Robin','robin32@gmail','azerty');

--@ violates PK_Uti
INSERT INTO LesUtilisateurs VALUES ('robin32','sima','Marc','marc32@gmail','admin');


-- ---------------------------------------------------------------------------
-- LesTicketsReserves
---------------------------------------------------------------------------

--@ violates FK_TicR_numeroTic
--INSERT INTO LesTicketsReserves VALUES (3,'marc20');

--@ violates FK_TicR_loginUti
--INSERT INTO LesTicketsReserves VALUES (6,'robin32');


-- ---------------------------------------------------------------------------
-- LesTicketsAchetes
---------------------------------------------------------------------------

--@ violates FK_TicA_numeroTic
--INSERT INTO LesTicketsReserves VALUES (1,'sami05');

--@ violates FK_TicA_loginUti
--INSERT INTO LesTicketsAchetes VALUES (8,'nico58');






-- Verifications Supplémentaires
---------------------------------------------------------------------------

---------------------------------------------------------------------------
-- LesHumoristiques et LesOperas
---------------------------------------------------------------------------

-- Manque les INSERT dans LesOperas et LesHumoristiques pour les Spectacles humoristiques/opera
INSERT INTO LesSpectacles VALUES (1, 'L avare', 10.0, 'toutPublic', 'humoristique');
INSERT INTO LesSpectacles VALUES (2, 'Le Cid', 10.0, 'toutPublic', 'opera');


