DROP TABLE LesRepresentations;
DROP TABLE LesOperas;
DROP TABLE LesHumoristiques;
DROP TABLE LesSpectacles;

CREATE TABLE LesSpectacles(
	numeroSpe INTEGER,
	nomSpe VARCHAR(100) NOT NULL,
	prixDeBaseSpe REAL,
	cibleSpe VARCHAR(11),
	typeSpe VARCHAR(12),

	CONSTRAINT PK_Spe
		PRIMARY KEY (numeroSpe),
	CONSTRAINT CK_Spe_cibleSpe
		CHECK (cibleSpe in ("unCinqAns", "jeunePublic", "toutPublic","adulte")),
	CONSTRAINT CK_Spe_typeSpe
		CHECK (typeSpe in ("opera", "drame", "humoristique", "musical", "cirque")),
	CONSTRAINT CK_Spe_numeroSpe
		CHECK ( 0 < numeroSpe),
	CONSTRAINT CK_Spe_prixDeBaseSpe
		CHECK ( 0 < prixDeBaseSpe)
);


CREATE TABLE LesHumoristiques(
	numeroSpe INTEGER,
	estUnOneWomanManShowHum INTEGER,

	CONSTRAINT PK_Hum
		PRIMARY KEY (numeroSpe),
	CONSTRAINT FK_Hum_numeroSpe 
		FOREIGN KEY (numeroSpe) REFERENCES LesSpectacles(numeroSpe) ON DELETE CASCADE,
	CONSTRAINT CK_Hum_estUnOneWomanManShow
		CHECK (estUnOneWomanManShowHum in (0,1))
);


CREATE TABLE LesOperas(
	numeroSpe Integer,
	aUnOrchestreOpe Integer,

	CONSTRAINT PK_Ope
		PRIMARY KEY (numeroSpe),
	CONSTRAINT FK_Ope_numeroSpe 
		FOREIGN KEY (numeroSpe) REFERENCES LesSpectacles(numeroSpe) ON DELETE CASCADE,
	CONSTRAINT CK_Ope_aUnOrchestre
		CHECK (aUnOrchestreOpe in (0,1))
);


CREATE TABLE LesRepresentations(
	horaireRep VARCHAR(14),
	numeroSpe INTEGER,


	CONSTRAINT PK_Rep
		PRIMARY KEY (horaireRep),
	CONSTRAINT FK_Rep_numeroSpe 
		FOREIGN KEY (numeroSpe) REFERENCES LesSpectacles(numeroSpe) ON DELETE CASCADE
);

INSERT INTO LesSpectacles VALUES (45, 'Cyrano de Bergerac', 20.0, 'toutPublic', 'drame');
INSERT INTO LesSpectacles VALUES (46, 'Les Animaux', 8.0, 'unCinqAns', 'cirque');
INSERT INTO LesSpectacles VALUES (47, 'Sonorites Etranges', 10.0, 'jeunePublic', 'musical');
INSERT INTO LesSpectacles VALUES (20, 'L''avare', 10.0, 'toutPublic', 'humoristique');
INSERT INTO LesHumoristiques VALUES (20, 0);
INSERT INTO LesSpectacles VALUES (25, 'Don Juan', 10.0, 'adulte', 'opera');
INSERT INTO LesOperas VALUES (25, 1);
INSERT INTO LesSpectacles VALUES (17, 'Andromaque', 15.0, 'adulte', 'drame');


--------------------------------------------------------------------------
-- LesRepresentations
---------------------------------------------------------------------------

INSERT INTO LesRepresentations VALUES ('2020-03-14 18:00', 45);
INSERT INTO LesRepresentations VALUES ('2020-03-13 18:00', 46);
INSERT INTO LesRepresentations VALUES ('2020-03-14 15:00', 47);
INSERT INTO LesRepresentations VALUES ('2020-03-13 20:00', 20);
INSERT INTO LesRepresentations VALUES ('2020-03-15 20:00', 20);
INSERT INTO LesRepresentations VALUES ('2020-03-15 18:00', 25);
