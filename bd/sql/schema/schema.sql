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
	CONSTRAINT DOM_Spe_cibleSpe
		CHECK (cibleSpe in ("unCinqAns", "jeunePublic", "toutPublic","adulte")),
	CONSTRAINT DOM_Spe_typeSpe
		CHECK (typeSpe in ("opera", "drame", "humoristique", "musical", "cirque")),
	CONSTRAINT DOM_Spe_numeroSpe
		CHECK (0 < numeroSpe),
	CONSTRAINT DOM_Spe_prixDeBaseSpe
		CHECK (0 < prixDeBaseSpe)
);


CREATE TABLE LesOperas(
	numeroSpe INTEGER,
	aUnOrchestreOpe INTEGER,

	CONSTRAINT PK_Ope
		PRIMARY KEY (numeroSpe),
	CONSTRAINT FK_Ope_numeroSpe
		FOREIGN KEY (numeroSpe) REFERENCES LesSpectacles(numeroSpe) ON DELETE CASCADE,
	CONSTRAINT DOM_Ope_aUnOrchestre
		CHECK (aUnOrchestreOpe in (0,1))
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


CREATE TABLE LesRepresentations_base(
	horaireRep VARCHAR(16),
	numeroSpe INTEGER,
	tauxReducRep REAL,

	CONSTRAINT PK_Rep
		PRIMARY KEY (horaireRep),
	CONSTRAINT FK_Rep_numeroSpe 
		FOREIGN KEY (numeroSpe) REFERENCES LesSpectacles(numeroSpe) ON DELETE CASCADE,
	CONSTRAINT DOM_Rep_tauxReducRep
		CHECK (0 <= tauxReducRep AND tauxReducRep < 1)
);


CREATE TABLE LesRangs(
	numeroRan INTEGER,
	
	CONSTRAINT PK_Ran
		PRIMARY KEY (numeroRan),
	CONSTRAINT DOM_Ran_numeroRan
		CHECK (0 < numeroRan)
);


CREATE TABLE LesPlaces(
	numeroRan INTEGER,
	numeroPla INTEGER,

	CONSTRAINT PK_Pla
		PRIMARY KEY (numeroPla, numeroRan),
	CONSTRAINT FK_Pla_numeroRan
		FOREIGN KEY (numeroRan) REFERENCES LesRangs(numeroRan) ON DELETE CASCADE,
	CONSTRAINT DOM_Pla_numeroPla
		CHECK (0 < numeroPla)
);


CREATE TABLE LesDossiersAchats_base(
	numeroDos INTEGER,

	CONSTRAINT PK_Dos
		PRIMARY KEY (numeroDos),
	CONSTRAINT DOM_Dos_numeroDos
		CHECK (0 < numeroDos)
);


CREATE TABLE LesTickets_base(
	horaireRep VARCHAR(16),
	numeroRan INTEGER,
	numeroPla INTEGER,
	numeroTic INTEGER,
	dateEmissionTic VARCHAR (19),
	numeroDos INTEGER,

	CONSTRAINT PK_Tic
		PRIMARY KEY (horaireRep, numeroRan, numeroPla),
	CONSTRAINT UN_Tic_numeroTic
		UNIQUE (numeroTic),
	CONSTRAINT FK_Tic_horaireRep
		FOREIGN KEY (horaireRep) REFERENCES LesRepresentations_base(horaireRep) ON DELETE CASCADE,
	CONSTRAINT FK_Tic_numeroRan_numeroPla
		FOREIGN KEY (numeroRan, numeroPla) REFERENCES LesPlaces(numeroRan, numeroPla) ON DELETE CASCADE,
<<<<<<< HEAD
	CONSTRAINT FK_Tic_numeroDos
		FOREIGN KEY (numeroDos) REFERENCES LesDossiersAchats_base(numeroDos) ON DELETE CASCADE,
=======
>>>>>>> achatPlaces
	CONSTRAINT DOM_Tic_numeroTic
		CHECK (0 < numeroTic)
);


CREATE TABLE LesUtilisateurs(
	loginUti VARCHAR(30),
	nomUti VARCHAR(50),
    prenomUti VARCHAR(50),
	mailUti VARCHAR (50),
	mdpUti VARCHAR (20),

	CONSTRAINT PK_Uti
		PRIMARY KEY (loginUti)
);


CREATE TABLE LesTicketsReserves(
	numeroTic INTEGER,
	loginUti VARCHAR (30),
	
	CONSTRAINT PK_TicR
		PRIMARY KEY (numeroTic),
	CONSTRAINT FK_TicR_numeroTic
		FOREIGN KEY (numeroTic) REFERENCES LesTickets_base(numeroTic) ON DELETE CASCADE,
    CONSTRAINT FK_TicR_loginUti
		FOREIGN KEY (loginUti) REFERENCES LesUtilisateurs(loginUti) ON DELETE CASCADE

);


CREATE TABLE LesTicketsAchetes(
	numeroTic INTEGER,
	loginUti VARCHAR (30),

	CONSTRAINT PK_TicA
		PRIMARY KEY (numeroTic),
	CONSTRAINT FK_TicA_numeroTic
		FOREIGN KEY (numeroTic) REFERENCES LesTickets_base(numeroTic) ON DELETE CASCADE,
    CONSTRAINT FK_TicA_loginUti
		FOREIGN KEY (loginUti) REFERENCES LesUtilisateurs(loginUti) ON DELETE CASCADE
);


CREATE VIEW LesRepresentations(horaireRep, numeroSpe, tauxReducRep, placesDispoRep) AS
	SELECT R.horaireRep, numeroSpe, tauxReducRep, 
		((SELECT COUNT(numeroPla) FROM LesPlaces) - COUNT(numeroTic)) AS placesDispoRep
	FROM LesRepresentations_base R LEFT OUTER JOIN LesTickets_base T ON (R.horaireRep = T.horaireRep)
	GROUP BY R.horaireRep, numeroSpe, tauxReducRep ;


CREATE VIEW LesTickets(horaireRep, numeroRan, numeroPla, numeroTic,	dateEmissionTic, numeroDos, prixTic) AS
	SELECT R.horaireRep, T.numeroRan, T.numeroPla, T.numeroTic, T.dateEmissionTic, T.numeroDos, (1 - R.tauxReducRep)*S.prixDeBaseSpe AS prixTic
	FROM LesTickets_base T JOIN LesRepresentations_base R ON (T.horaireRep = R.horaireRep)
							JOIN LesSpectacles S ON (R.numeroSpe = S.numeroSpe) ;


CREATE VIEW LesDossiersAchats(numeroDos, prixGlobalDos) AS 
	SELECT DA.numeroDos, SUM(T.prixTic)
	FROM LesDossiersAchats_base DA JOIN LesTickets T ON (DA.numeroDos = T.numeroDos)
	GROUP BY DA.numeroDos ;

