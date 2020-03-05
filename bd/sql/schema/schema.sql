--=========================================================================
--  MyTheatre
--=========================================================================
-- Based on the course of M.C. Fauvet
---------------------------------------------------------------------------


---------------------------------------------------------------------------
-- Database schema
---------------------------------------------------------------------------


CREATE TABLE LesSpectacles(
      numeroSpe Integer,
      nomSpe VARCHAR(100)NOT NULL,
      prixDeBaseSpe Real,
      cibleSpe VARCHAR(11),
      typeSpe VARCHAR(12),

    CONSTRAINT PK_Spe
        PRIMARY KEY (numeroSpe),
    CONSTRAINT CK_Spe_cibleSpe
        CHECK (cibleSpe in ("unCinqAns", "jeunePublic", "toutPublic","adulte")),
    CONSTRAINT CK_Spe_typeSpe
        CHECK (typeSpe in ("opera", "drame", " humoristique", "musical", "cirque")),
    CONSTRAINT CK_Spe_numeroSpe
        CHECK ( 0 < numeroSpe),
    CONSTRAINT CK_Spe_prixDeBasSpe
        CHECK ( 0 < prixDeBasSpe)

);


CREATE TABLE LesRepresentations(
      dateRep VARCHAR (15),
      numeroSpe Integer,
    -- placesDispo_d Integer,--
     --tauxReduc Real,--

     CONSTRAINT PK_Rep
        PRIMARY KEY (dateRep),
     CONSTRAINT FK_Rep_numeroSep 
        FOREIGN KEY (numeroSep) REFERENCES LesSpectacles(numeroSpe),
     CONSTRAINT DOM_Rep_tauxReducRep
         CHECK ( 0 <= tauxReducRep AND tauxReducRep <= 1 )

);

CREATE TABLE LesHumoristiques(
		numeroSpe Integer,
		estUnOneWomanManShowHum Integer,

		CONSTRAINT PK_Hum
        PRIMARY KEY (numeroSpe),
     CONSTRAINT FK_Hum_numeroSep 
        FOREIGN KEY (numeroSep) REFERENCES LesSpectacles(numeroSpe),
		CONSTRAINT Dom_Hum_estUnOneWomanManShow
			CHECK (estUnOneWomanManShow in (0,1))
);


CREATE TABLE LesOperas(
		numeroSpe Integer,
		aUnOrchestreOpe Integer,
		
		CONSTRAINT PK_Ope
        PRIMARY KEY (numeroSpe),
     CONSTRAINT FK_Ope_numeroSep 
        FOREIGN KEY (numeroSep) REFERENCES LesSpectacles(numeroSpe),
		CONSTRAINT Dom_Ope_aUnOrchestre
     	CHECK (aUnOrchestre in (0,1))

);

       
      


