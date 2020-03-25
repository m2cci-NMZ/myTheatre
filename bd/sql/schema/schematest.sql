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
		FOREIGN KEY (numeroSpe) REFERENCES LesSpectacles(numeroSpe),
	CONSTRAINT CK_Hum_estUnOneWomanManShow
		CHECK (estUnOneWomanManShowHum in (0,1))
);


CREATE TABLE LesOperas(
	numeroSpe Integer,
	aUnOrchestreOpe Integer,

	CONSTRAINT PK_Ope
		PRIMARY KEY (numeroSpe),
	CONSTRAINT FK_Ope_numeroSpe 
		FOREIGN KEY (numeroSpe) REFERENCES LesSpectacles(numeroSpe),
	CONSTRAINT CK_Ope_aUnOrchestre
		CHECK (aUnOrchestreOpe in (0,1))
);


CREATE TABLE LesRepresentations(
	horaireRep VARCHAR(14),
	numeroSpe INTEGER,
	--placesDispo Integer,--
	--tauxReduc Real,--

	CONSTRAINT PK_Rep
		PRIMARY KEY (horaireRep),
	CONSTRAINT FK_Rep_numeroSpe 
		FOREIGN KEY (numeroSpe) REFERENCES LesSpectacles(numeroSpe)
	--CONSTRAINT DOM_Rep_tauxReducRep
	--	CHECK ( 0 <= tauxReducRep AND tauxReducRep <= 1 )
);


CREATE TABLE LesTickets(
	numTic INTEGER,
	dateTic VARCHAR(14),
	horaireRep VARCHAR(14),  
	prixTic REAL,
	numPla INTEGER, 
	numRan INTEGER,
	numDA INTEGER,
	CONSTRAINT PK_Tick
		PRIMARY KEY (NumTic, dateTic, horaireRep, numPla, numRan),
	CONSTRAINT FK_Tick_horairespe 
		FOREIGN KEY (horaireRep) REFERENCES LesRepresentations(horaireRep)
--	CONSTRAINT FK_Tick_horairespe 
--		FOREIGN KEY (numPla,numRan) REFERENCES LesPlaces(numPla,numRan)
);

CREATE TABLE LesTicketsAchetes(
	numTic INTEGER,
	loginUti VARCHAR(14),
	CONSTRAINT PK_Tick
		PRIMARY KEY (numTic),
	CONSTRAINT FK_Tick_horairespe 
		FOREIGN KEY (numTic) REFERENCES LesTickets(numTic)
);

CREATE TABLE LesTicketsReserves(
	numTic INTEGER,
	loginUti VARCHAR(14),
	CONSTRAINT PK_Tick
		PRIMARY KEY (numTic),
	CONSTRAINT FK_Tick_horairespe 
		FOREIGN KEY (numTic) REFERENCES LesTickets(numTic)
);
	

