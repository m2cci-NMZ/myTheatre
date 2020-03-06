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

INSERT INTO LesRepresentations VALUES ('14/03/2020 18h', 45);
INSERT INTO LesRepresentations VALUES ('13/03/2020 18h', 46);
INSERT INTO LesRepresentations VALUES ('14/03/2020 15h', 47);
INSERT INTO LesRepresentations VALUES ('13/03/2020 20h', 20);
INSERT INTO LesRepresentations VALUES ('15/03/2020 18h', 25);


SELECT R.numeroSpe, nomSpe, prixDeBaseSpe, cibleSpe, typeSpe, dateRep
FROM LesSpectacles S RIGHT OUTER JOIN LesOperas O ON S.numeroSpe = O.numeroSpe
		RIGHT OUTER JOIN LesHumoristiques H ON S.numeroSpe = H.numeroSpe
		JOIN LesRepresentations R ON R.numeroSpe = S.numeroSpe \n"
WHERE cibleSpe='toutPublic' AND typeSpe='drame';



