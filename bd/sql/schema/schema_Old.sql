--=========================================================================
--  MyTheatre
--=========================================================================
-- Based on the course of M.C. Fauvet
---------------------------------------------------------------------------


---------------------------------------------------------------------------
-- Database schema
---------------------------------------------------------------------------


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
	CONSTRAINT DOM_Spe_typeSpe
		CHECK (typeSpe in ("opera", "drame", "humoristique", "musical", "cirque")),
	CONSTRAINT DOM_Spe_numeroSpe
		CHECK ( 0 < numeroSpe),
	CONSTRAINT DOM_Spe_prixDeBaseSpe
		CHECK ( 0 < prixDeBaseSpe)
);


CREATE TABLE LesHumoristiques(
	numeroSpe INTEGER,
	estUnOneWomanManShowHum INTEGER,

	CONSTRAINT PK_Hum
		PRIMARY KEY (numeroSpe),
	CONSTRAINT FK_Hum_numeroSpe 
		FOREIGN KEY (numeroSpe) REFERENCES LesSpectacles(numeroSpe) ON DELETE CASCADE,
	CONSTRAINT DOM_Hum_estUnOneWomanManShow
		CHECK (estUnOneWomanManShowHum in (0,1))
);


CREATE TABLE LesOperas(
	numeroSpe Integer,
	aUnOrchestreOpe Integer,

	CONSTRAINT PK_Ope
		PRIMARY KEY (numeroSpe),
	CONSTRAINT FK_Ope_numeroSpe
		FOREIGN KEY (numeroSpe) REFERENCES LesSpectacles(numeroSpe) ON DELETE CASCADE,
	CONSTRAINT DOM_Ope_aUnOrchestre
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