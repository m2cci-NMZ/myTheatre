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
	placesDispoRep_d Integer,
	tauxReducRep Real,

	CONSTRAINT PK_Rep
		PRIMARY KEY (horaireRep),
	CONSTRAINT FK_Rep_numeroSpe 
		FOREIGN KEY (numeroSpe) REFERENCES LesSpectacles(numeroSpe) ON DELETE CASCADE,
	CONSTRAINT DOM_Rep_tauxReducRep
		CHECK ( 0 < tauxReducRep AND tauxReducRep <= 1 ),
	CONSTRAINT DOM_Rep_placesDispoRep_d
		CHECK ( 0 <= placesDispoRep_d)
);

CREATE TABLE LesRangs(
	numeroRan INTEGER,
	
	CONSTRAINT PK_Ran
		PRIMARY KEY (numeroRan),
	CONSTRAINT DOM_Ran_numeroRan
		CHECK ( 0 < numeroRan)
	
);

CREATE TABLE LesPlaces(
	numeroPla Integer,
	numeroRan Integer,

	CONSTRAINT PK_Pla
		PRIMARY KEY (numeroPla,numeroRan),
	CONSTRAINT FK_Pla_numeroRan
		FOREIGN KEY (numeroRan) REFERENCES LesRangs (numeroRan),
	CONSTRAINT DOM_Pla_numeroPla
		CHECK ( 0 < numeroPla)
);

CREATE TABLE LesDossiersAchats(
	numeroDos Integer,
	prixGlobalDos_d Real,

	CONSTRAINT PK_Dos
		PRIMARY KEY (numeroDos),
	CONSTRAINT DOM_Dos_numeroDos
		CHECK ( 0 < numeroDos),
     CONSTRAINT DOM_Dos_prixGlobalDos_d
	   CHECK ( 0 < prixGlobalDos_d)
);

CREATE TABLE LesUtilisateurs(
	loginUti VARCHAR(40),
	nomUti VARCHAR(50),
    prenomUti VARCHAR(50),
	mailUti VARCHAR (30),
	mdpUti VARCHAR (30),

	CONSTRAINT PK_Uti
		PRIMARY KEY (loginUti)
	
);

CREATE TABLE LesTickets(
	horaireRep VARCHAR(30),
	numeroRan INTEGER,
	numeroPla INTEGER,
	numeroTic Integer,
	dateEmissionTic VARCHAR (30),
	prixTic_d Real,
    prixTicketTic_d  Real,

	CONSTRAINT PK_Tic
		PRIMARY KEY (horaireRep,numeroRan,numeroPla),
	CONSTRAINT PK_Tic
		PRIMARY KEY (numeroTic),
	CONSTRAINT FK_Pla_horaireRep
		FOREIGN KEY (horaireRep) REFERENCES LesRepresentations(horaireRep),
	CONSTRAINT FK_Pla_numeroRan_numeroPla
		FOREIGN KEY (numeroRan,numeroPla) REFERENCES LesPlaces(numeroRan,numeroPla),
	CONSTRAINT DOM_Tic_numeroTic
		CHECK ( 0 < numeroTic),
	CONSTRAINT DOM_Tic_prixTic_d
	    CHECK ( 0 < prixTicketTic_d),
);

CREATE TABLE LesTicketsReserves(
	numeroTic Integer,
	loginUti VARCHAR (40),
	

	CONSTRAINT PK_TicR
		PRIMARY KEY (numeroTic),
	CONSTRAINT FK_TicR_numeroTic
		FOREIGN KEY (numeroTic) REFERENCES LesTickets(numeroTic),
    CONSTRAINT FK_TicR_loginUti
		FOREIGN KEY (loginUti) REFERENCES LesUtilisateurs(loginUti)

);

CREATE TABLE LesTicketsAchetes(
	numeroTic Integer ,
	loginUti VARCHAR (40),
	

	CONSTRAINT PK_TicA
		PRIMARY KEY (numeroTic),
	CONSTRAINT FK_TicA_numeroTic
		FOREIGN KEY (numeroTic) REFERENCES LesTickets(numeroTic),
    CONSTRAINT FK_TicA_loginUti
		FOREIGN KEY (loginUti) REFERENCES LesUtilisateurs(loginUti)

);