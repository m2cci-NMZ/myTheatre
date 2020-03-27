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
-- Init
INSERT INTO LesSpectacles VALUES (20, 'L avare', 10.0, 'toutPublic', 'drame');


-- Spectacle avec le même numéro qu'un autre
--@ violates PK_Spe
INSERT INTO LesSpectacles VALUES (20, 'Andromaque', 10.0 ,'toutPublic','drame');

-- Type et cible incorrectss
--@ violates DOM_Spe_cibleSpe
INSERT INTO LesSpectacles VALUES (2, 'Le Cid', 10.0 ,'tousPublique','drame');
--@ violates DOM_Spe_typeSpe
INSERT INTO LesSpectacles VALUES (3, 'Le Cid', 10.0 ,'toutPublic','dramatique');

-- Entier <0 dans Spectacle
--@ violates DOM_Spe_numeroSpe
INSERT INTO LesSpectacles VALUES (-2, 'Le Cid', 10.0 ,'toutPublic','drame');
INSERT INTO LesSpectacles VALUES (0, 'Le Cid', 10.0 ,'toutPublic','drame');
--@ violates DOM_Spe_prixDeBaseSpe
INSERT INTO LesSpectacles VALUES (1, 'Le Cid', -10.0 ,'toutPublic','drame');


-- End
DELETE FROM LesSpectacles WHERE (numeroSpe = 20);


---------------------------------------------------------------------------
-- LesHumoristiques et LesOperas
---------------------------------------------------------------------------
-- Init 
INSERT INTO LesSpectacles VALUES (4, 'TestPK', 10.0, 'jeunePublic', 'humoristique');
INSERT INTO LesHumoristiques VALUES (4, 0);
INSERT INTO LesSpectacles VALUES (5, 'TestPK', 10.0, 'jeunePublic', 'opera');
INSERT INTO LesOperas VALUES (5, 0);
INSERT INTO LesSpectacles VALUES (6, 'TestBool', 10.0, 'jeunePublic', 'humoristique');
INSERT INTO LesSpectacles VALUES (7, 'TestBool', 10.0, 'jeunePublic', 'opera');


-- Deux fois le même numeroSpe
--@ violates PK_Hum
INSERT INTO LesHumoristiques VALUES (4, 1);
--@ violates PK_ope
INSERT INTO LesOperas VALUES (5, 1);

-- Un humoriste pas dans la table des Spectacles
--@ violates FK_Hum_numeroSpe
INSERT INTO LesHumoristiques VALUES (2, 0);
--@ violates FK_ope_numeroSpe
INSERT INTO LesOperas VALUES (3, 0);

-- Valeurs incorrectes pour les booléens
--@ violates DOM_Hum_estUnOneWomanManShow
INSERT INTO LesHumoristiques VALUES (6, 4);
--@ violates DOM_Ope_aUnOrchestre
INSERT INTO LesOperas VALUES (7, 3);


-- End
DELETE FROM LesSpectacles WHERE (numeroSpe >=4 AND numeroSpe <=7);	-- Supprime tous les Spectacles 
-- Grâce à ON DELETE CASCADE, supprime dans les sous tables


---------------------------------------------------------------------------
-- LesRepresentations_base
---------------------------------------------------------------------------
-- Init 
INSERT INTO LesSpectacles VALUES (20, 'L avare', 10.0, 'toutPublic', 'drame');
INSERT INTO LesSpectacles VALUES (25, 'L avare 2', 10.0, 'toutPublic', 'drame');
INSERT INTO LesRepresentations_base VALUES ('2020-03-13 20:00', 20, 0.0);


-- Representations à la même heure
--@ violates PK_Rep
INSERT INTO LesRepresentations_base VALUES ('2020-03-13 20:00', 25, 0.5);

-- Representation pour un spectacle n'existant pas
--@ violates FK_Rep_numeroSpe
INSERT INTO LesRepresentations_base VALUES ('2025-03-13 21:42', 6, 0.0);

--@ violates DOM_Rep_tauxReducRep
INSERT INTO LesRepresentations_base VALUES ('2020-03-15 22:47', 25, 1.0);
INSERT INTO LesRepresentations_base VALUES ('2020-03-15 22:47', 25, -0.1);


-- End
DELETE FROM LesSpectacles WHERE (numeroSpe = 20);
DELETE FROM LesSpectacles WHERE (numeroSpe = 25);
-- Grâce à ON DELETE CASCADE, supprime aussi la Representation


---------------------------------------------------------------------------
-- LesRangs
---------------------------------------------------------------------------
-- Init 
INSERT INTO LesRangs VALUES (5);


-- Deux fois le même rang
--@ violates PK_Ran
INSERT INTO LesRangs VALUES (5);

-- Rangs à numéro négatif
--@ violates DOM_Ran_numeroRan
INSERT INTO LesRangs VALUES (0);


-- End
DELETE FROM LesRangs WHERE (numeroRan = 5);


---------------------------------------------------------------------------
-- LesPlaces
---------------------------------------------------------------------------
-- Init 
INSERT INTO LesRangs VALUES (5);
INSERT INTO LesPlaces VALUES (5,40);


-- Deux fois la même place dans le même rang
--@ violates PK_Pla
INSERT INTO LesPlaces VALUES (5,40);

-- Place dans un rang qui n'existe pas
--@ violates FK_Pla_numeroRan
INSERT INTO LesPlaces VALUES (3,15);

-- Place de numéro négatif
--@ violates DOM_Pla_numeroPla
INSERT INTO LesPlaces VALUES (5,0);


-- End
DELETE FROM LesRangs WHERE (numeroRan = 5);
-- Grâce à ON DELETE CASCADE, supprime aussi la Place


---------------------------------------------------------------------------
-- LesDossiersAchats_base
---------------------------------------------------------------------------
-- Init
INSERT INTO LesDossiersAchats_base VALUES (25);


-- Deux fois le même numéro de dossier
--@ violates PK_Dos
INSERT INTO LesDossiersAchats_base VALUES (25);

-- Dossier de numéro négatif
--@ violates DOM_Dos_numeroDos
INSERT INTO LesDossiersAchats_base VALUES (0);


-- End
DELETE FROM LesDossiersAchats_base WHERE (numeroDos = 25);


---------------------------------------------------------------------------
-- LesTickets_base
---------------------------------------------------------------------------
-- Init 
INSERT INTO LesSpectacles VALUES (20, 'L avare', 10.0, 'toutPublic', 'drame');
INSERT INTO LesRepresentations_base VALUES ('2020-03-13 20:00', 20, 0.0);
INSERT INTO LesRepresentations_base VALUES ('2020-03-14 20:00', 20, 0.0);
INSERT INTO LesRangs VALUES (5);
INSERT INTO LesPlaces VALUES (5,1);
INSERT INTO LesDossiersAchats_base VALUES (25);
INSERT INTO LesTickets_base VALUES ('2020-03-13 20:00', 5, 1, 100, '2020-03-10 18:20:10', 25);


-- Deux fois les mêmes place pour la même representation
--@ violates PK_Tic
INSERT INTO LesTickets_base VALUES ('2020-03-13 20:00', 5, 1, 101, '2020-03-10 19:30:10', 25);

-- Deux fois le même numéro de ticket
--@ violates UN_Tic_numeroTic
INSERT INTO LesTickets_base VALUES ('2020-03-14 20:00', 5, 1, 100, '2020-03-11 15:30:10', 25);

-- Un ticket pour un representation qui n'existe pas
--@ violates FK_Tic_horaireRep
INSERT INTO LesTickets_base VALUES ('2020-03-15 20:00', 5, 1, 101, '2020-03-12 13:30:10', 25);

-- Un ticket pour une place qui n'existe pas
--@ violates FK_Tic_numeroRan_numeroPla
INSERT INTO LesTickets_base VALUES ('2020-03-14 20:00', 5, 2, 101, '2020-03-11 11:30:10', 25);

-- Un ticket pour un numero de dossier qui n'existe pas
--@ violates FK_Tic_numeroDos
INSERT INTO LesTickets_base VALUES ('2020-03-14 20:00', 5, 1, 101, '2020-03-10 09:30:10', 26);

-- Un numero de ticket négatif
--@ violates DOM_Tic_numeroTic
INSERT INTO LesTickets_base VALUES ('2020-03-14 20:00', 5, 1, 0, '2020-03-10 07:30:10', 25);


-- End
DELETE FROM LesSpectacles WHERE (numeroSpe = 20);
-- Grâce à ON DELETE CASCADE, supprime aussi les deux représentations
-- Ca supprime donc aussi le ticket 100
DELETE FROM LesRangs WHERE (numeroRan = 5);
-- Grâce à ON DELETE CASCADE, supprime aussi la Place
DELETE FROM LesDossiersAchats_base WHERE (numeroDos = 25);


---------------------------------------------------------------------------
-- LesUtilisateurs
---------------------------------------------------------------------------
-- Init 
INSERT INTO LesUtilisateurs VALUES ('robin32','Miquel','Robin','robin32@gmail','azerty');


-- Deux fois le même login
--@ violates PK_Uti
INSERT INTO LesUtilisateurs VALUES ('robin32','Sima','Marc','marc32@gmail','admin');


-- End
DELETE FROM LesUtilisateurs WHERE (loginUti = 'robin32');


---------------------------------------------------------------------------
-- LesTicketsReserves
---------------------------------------------------------------------------
-- Init
INSERT INTO LesSpectacles VALUES (20, 'L avare', 10.0, 'toutPublic', 'drame');
INSERT INTO LesRepresentations_base VALUES ('2020-03-13 20:00', 20, 0.0);
INSERT INTO LesRangs VALUES (5);
INSERT INTO LesPlaces VALUES (5,1);
INSERT INTO LesDossiersAchats_base VALUES (25);
INSERT INTO LesTickets_base VALUES ('2020-03-13 20:00', 5, 1, 100, '2020-03-10 18:20:10', 25);

INSERT INTO LesUtilisateurs VALUES ('robin32', 'Miquel', 'Robin', 'robin32@gmail', 'azerty');
INSERT INTO LesTicketsReserves VALUES (100, 'robin32');


-- Deux fois le meme numeroTic
--@ violates PK_TicR
INSERT INTO LesTicketsReserves VALUES (100, 'robin32');

-- Ticket dont le numero n'existe pas
--@ violates FK_TicR_numeroTic
INSERT INTO LesTicketsReserves VALUES (99, 'robin32');

-- Pour un utilisateur qui n'existe pas
--@ violates FK_TicR_loginUti
INSERT INTO LesTicketsReserves VALUES (99, 'celia45');


-- End
DELETE FROM LesSpectacles WHERE (numeroSpe = 20);
-- Grâce à ON DELETE CASCADE, supprime aussi les deux représentations
-- Ca supprime donc aussi le ticket 100
-- Ca supprime donc aussi dans TicketReserves
DELETE FROM LesRangs WHERE (numeroRan = 5);
-- Grâce à ON DELETE CASCADE, supprime aussi la Place
DELETE FROM LesDossiersAchats_base WHERE (numeroDos = 25);
DELETE FROM LesUtilisateurs WHERE (loginUti = 'robin32');


---------------------------------------------------------------------------
-- LesTicketsReserves
---------------------------------------------------------------------------
-- Init
INSERT INTO LesSpectacles VALUES (20, 'L avare', 10.0, 'toutPublic', 'drame');
INSERT INTO LesRepresentations_base VALUES ('2020-03-13 20:00', 20, 0.0);
INSERT INTO LesRangs VALUES (5);
INSERT INTO LesPlaces VALUES (5,1);
INSERT INTO LesDossiersAchats_base VALUES (25);
INSERT INTO LesTickets_base VALUES ('2020-03-13 20:00', 5, 1, 100, '2020-03-10 18:20:10', 25);

INSERT INTO LesUtilisateurs VALUES ('robin32', 'Miquel', 'Robin', 'robin32@gmail', 'azerty');
INSERT INTO LesTicketsAchetes VALUES (100, 'robin32');


-- Deux fois le meme numeroTic
--@ violates PK_TicA
INSERT INTO LesTicketsAchetes VALUES (100, 'robin32');

-- Ticket dont le numero n'existe pas
--@ violates FK_TicA_numeroTic
INSERT INTO LesTicketsAchetes VALUES (99, 'robin32');

-- Pour un utilisateur qui n'existe pas
--@ violates FK_TicA_loginUti
INSERT INTO LesTicketsAchetes VALUES (99, 'celia45');


-- End
DELETE FROM LesSpectacles WHERE (numeroSpe = 20);
-- Grâce à ON DELETE CASCADE, supprime aussi les deux représentations
-- Ca supprime donc aussi le ticket 100
-- Ca supprime donc aussi dans TicketAchetes
DELETE FROM LesRangs WHERE (numeroRan = 5);
-- Grâce à ON DELETE CASCADE, supprime aussi la Place
DELETE FROM LesDossiersAchats_base WHERE (numeroDos = 25);
DELETE FROM LesUtilisateurs WHERE (loginUti = 'robin32');




--=========================================================================
-- On vérifie que toutes les tables sont vides
--=========================================================================
SELECT * FROM LesSpectacles;
SELECT * FROM LesOperas;
SELECT * FROM LesHumoristiques;
SELECT * FROM LesRepresentations_base;
SELECT * FROM LesRangs;
SELECT * FROM LesPlaces;
SELECT * FROM LesDossiersAchats_base;
SELECT * FROM LesTickets_base;
SELECT * FROM LesUtilisateurs;
SELECT * FROM LesTicketsReserves;
SELECT * FROM LesTicketsAchetes;




--=========================================================================
-- Verifications Supplémentaires (verif.sql)
--=========================================================================

---------------------------------------------------------------------------
-- LesHumoristiques et LesOperas
---------------------------------------------------------------------------

-- Manque les INSERT dans LesOperas et LesHumoristiques pour les Spectacles humoristiques/opera
INSERT INTO LesSpectacles VALUES (1000, 'L avare', 10.0, 'toutPublic', 'humoristique');
INSERT INTO LesSpectacles VALUES (1001, 'Le Cid', 10.0, 'toutPublic', 'opera');


INSERT INTO LesSpectacles VALUES (1002, 'Un drame', 10.0, 'toutPublic', 'drame');
-- Spectacle pas 'opera' ou 'humoristique' dans LesOperas ou LesHumoristiques
INSERT INTO LesOperas VALUES (1002, 0);
INSERT INTO LesHumoristiques VALUES (1002, 0);


---------------------------------------------------------------------------
-- LesRangs
---------------------------------------------------------------------------

-- Rang sans place
INSERT INTO LesRangs VALUES (1000);


---------------------------------------------------------------------------
-- LesTickets
---------------------------------------------------------------------------
INSERT INTO LesRepresentations_base VALUES ('2020-12-31 12:00', 1000, 0.0);
INSERT INTO LesRangs VALUES (1001);
INSERT INTO LesPlaces VALUES (1001,1000);
INSERT INTO LesPlaces VALUES (1001,1001);
INSERT INTO LesPlaces VALUES (1001,1002);
INSERT INTO LesDossiersAchats_base VALUES (1000);
INSERT INTO LesTickets_base VALUES ('2020-12-31 12:00', 1001, 1000, 1000, '2020-12-01 12:00:00', 1000);
INSERT INTO LesUtilisateurs VALUES ('robin1000', 'Miquel', 'Robin', 'robin32@gmail', 'azerty');

-- Ticket reservé et acheté
INSERT INTO LesTicketsReserves VALUES (1000, 'robin1000'); 
INSERT INTO LesTicketsAchetes VALUES (1000, 'robin1000');

-- Ticket ni acheté ni réservé
INSERT INTO LesTickets_base VALUES ('2020-12-31 12:00', 1001, 1001, 1001, '2020-12-01 12:00:01', 1000);

-- Ticket émis après la représentation
INSERT INTO LesTickets_base VALUES ('2020-12-31 12:00', 1001, 1002, 1002, '2020-12-31 23:00:02', 1000);
-- (Pour ne pas trigger celle avant, on l'achète)
INSERT INTO LesTicketsAchetes VALUES (1002, 'robin1000');

