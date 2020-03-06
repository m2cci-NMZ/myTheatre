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



--------------------------------------------------------------------------
-- LesRepresentations
---------------------------------------------------------------------------

INSERT INTO LesRepresentations VALUES ('2020-03-14 18:00', 45);
INSERT INTO LesRepresentations VALUES ('2020-03-13 18:00', 46);
INSERT INTO LesRepresentations VALUES ('2020-03-14 15:00', 47);
INSERT INTO LesRepresentations VALUES ('2020-03-13 20:00', 20);
INSERT INTO LesRepresentations VALUES ('2020-03-15 20:00', 25);


SELECT S.numeroSpe, nomSpe, prixDeBaseSpe, cibleSpe, typeSpe, estUnOneWomanManShowHum, aUnOrchestreOpe, dateRep
FROM LesSpectacles S LEFT OUTER JOIN LesOperas O ON S.numeroSpe = O.numeroSpe
		LEFT OUTER JOIN LesHumoristiques H ON S.numeroSpe = H.numeroSpe
		JOIN LesRepresentations R ON R.numeroSpe = S.numeroSpe
WHERE dateRep < '2020-03-15' AND dateRep > '2020-03-13 19:00';



