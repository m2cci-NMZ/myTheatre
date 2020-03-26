//=========================================================================
//                      Relation model
//-------------------------------------------------------------------------
// This relation model is expressed in RelationScript
// See https://modelscript.rtfd.io/en/latest/languages/relations/index.html
//=========================================================================

relation model MyTheatre
import glossary model from "../glossaries/glossaries.gls"
import class model from "../classes/classes.cl1"

relation model MyTheatre 


relation LesSpectacles 
	transformation 
		from R_Class(Spectacle)

	columns 
		numeroSpe_ : Integer
		nomSpe : String
		prixDeBaseSpe : Real
		cibleSpe : Cible
		typeSpe : Type

	constraints
		key numeroSpe_
		numeroSpe_ > 0
		prixDeBaseSpe > 0


relation LesOperas
	transformation 
		from R_Reference(Opera)

	columns 
		numeroSpe_ : Integer
		aUnOrchestreOpe : Boolean
		
	constraints
		key numeroSpe_
		LesOperas[numeroSpe_] C= LesSpectacles[numeroSpe_]
  

relation LesHumoristiques
	transformation 
		from R_Reference(Humoristique)

	columns
		numeroSpe_ : Integer
		estUnOneWomanManShowHum : Boolean
		
	constraints
		key numeroSpe_
		LesHumoristiques[numeroSpe_] C= LesSpectacles[numeroSpe_]
		
constraints
	LesOperas[numeroSpe_] n LesHumoristiques[numeroSpe_] = {}


relation LesRepresentations_base
	transformation 
		from R_Class(Representation)
		from R_OneToMany(APourRepresentation)

	columns 
		horaireRep_: Date
		numeroSpe : Integer
		tauxReducRep : Real	
//		placesDispoRep_d : Integer 
		
	constraints
		key horaireRep_
		LesRepresentations[numeroSpe] C= LesSpectacles[numeroSpe_]
		tauxReducRep >= 0
		tauxReducRep < 1
//		placesDispoRep_d >= 0


relation LesRangs
	transformation 
		from R_Class(Rang)	

	columns 
		numeroRan_ : Integer
		
	constraints
		key numeroRan_ 
		numeroRan_ > 0
		
		
relation LesPlaces
	transformation 
		from R_Class(Place)
		from R_Compo(Contient)

	columns 
		numeroRan_ : Integer
		numeroPla_ : Integer	
		
	constraints
		key numeroRan_, numeroPla_
		numeroPla_ > 0
		LesPlaces[numeroRan_] = LesRangs[numeroRan_]


relation LesDossiersAchats_base
	transformation 
		from R_Class(DossierAchat)

	columns 
		numeroDos_ : Integer
//		prixGlobalDos_d : Real
		
	constraints
		key numeroDos_ 
		numeroDos_ > 0
//		prixGlobalDos_d > 0


relation LesTickets_base
	transformation 
		from R_ManyToManyAC(Ticket)
		from R_OneToMany(FaitPartieDUn)

	columns
		horaireRep_id1 : Date
		numeroRan_id1 : Integer
		numeroPla_id1 : Integer
		numeroTic_id2 : Integer
		dateEmissionTic : Date
//		prixTic_d : Real
		numeroDos : Integer
		
	constraints
		key horaireRep_id1, numeroRan_id1, numeroPla_id1
		key numeroTic_id2
		LesTickets[horaireRep_id1] C= LesRepresentations[horaireRep_]
		LesTickets[numeroRan_id1, numeroPla_id1] C= LesPlaces[numeroRan_, numeroPla_]
		LesTickets[numeroDos] = LesDossiersAchats[numeroDos_]
		numeroTic_ > 0
//  	prixTic_d > 0


relation LesUtilisateurs
	transformation 
		from R_Class(Utilisateur)

	columns 
		loginUti_ : String
		nomUti : String
		prenomUti : String
		mailUti : String
		mdpUti : String
		
	constraints
		key loginUti_


relation LesTicketsReserves
	transformation 
		from R_OneToMany(AReserve)

	columns 
		numeroTic_ : Integer
		loginUti : String
		
	constraints
		key numeroTic_
		LesTicketsReserves[numeroTic_] C= LesTickets[numeroTic_id2]
		LesTicketsReserves[loginUti] C= LesUtilisateurs[loginUti_]
		
		
relation LesTicketsAchetes
	transformation 
		from R_OneToMany(AAchete)

	columns 
		numeroTic_ : Integer
		loginUti : String
		
	constraints
		key numeroTic_
		LesTicketsAchetes[numeroTic_] C= LesTickets[numeroTic_id2]
		LesTicketsAchetes[loginUti] C= LesUtilisateurs[loginUti_]
		
constraints
	LesTicketsReserves[numeroTic_] n LesTicketsAchetes[numeroTic_] = {}
	LesTicketsReserves[numeroTic_] u LesTicketsAchetes[numeroTic_] = LesTickets[numeroTic_id2]