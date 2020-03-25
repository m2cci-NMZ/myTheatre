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
		LesOperas[numeroSpe] n LesHumoristiques[numeroSpe] = {}
  

relation LesHumoristiques
	transformation 
		from R_Reference(Humoristique)

	columns
		numeroSpe_ : Integer
		estUnOneWomanManShowHum : Boolean
		
	constraints
		key numeroSpe_
		LesHumoristiques[numeroSpe_] C= LesSpectacles[numeroSpe_]


relation LesRepresentations
	transformation 
		from R_Class(Representation)
		from R_OneToMany(Spectacle)

	columns 
		horaireRep_: Date
		numeroSpe : Integer
		placesDispoRep_d : Integer 
		tauxReducRep : Real
		
	constraints
		key horaireRep_
		LesRepresentations[numeroSpe] C= LesSpectacles[numeroSpe_]
		placesDispoRep_d >= 0
		tauxReducRep > 0
		tauxReducRep <= 1


relation LesRangs
	transformation 
		from R_Class(Rang)	

	columns 
		numeroRan_ : Integer
		
	constraints
		key numeroRan_ 
		numeroRan > 0
		
		
relation LesPlaces
	transformation 
		from R_Class(Place)
		from R_Compo(Contient)

	columns 
		numeroPla_ : Integer
		numeroRan_ : Integer
		
	constraints
		key numeroPla_ 
		numeroPla_ > 0
		LesPlaces[numeroRan] = LesRangs[numeroRan]
		

relation LesTickets
	transformation 
		from R_ManyToManyAC(Ticket)

	columns
		horaireRep_id1 : Date
		numeroPla_id1 : Integer
		numeroTic_id2 : Integer
		dateEmissionTic : String
		prixTic_d : Real
		
	constraints
		key numeroTic_ 
		numeroTic_ > 0
		prixTicketTic_d > 0


		
relation LesDossiersAchats
	transformation 
		from R_Class(DossierAchat)

	columns 
		numeroDos_ : Integer
		prixGlobalDos_d : Real
		
	constraints
		key numeroDos_ 
		numeroDos_ > 0
		prixGlobalDos_d > 0


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
		
		
relation LesTickets
	transformation 
		from R_ManyToManyAC(Ticket)

	columns 
		numeroTic_ : Integer
		dateEmiTic : String
		prixTicketTic_d : Real
		
	constraints
		key numeroTic_ 
		numeroTic_ > 0
		prixTicketTic_d > 0
		
		
relation LesTicketsReserves
	transformation 
		from R_OneToMany (AReserve)

	columns 
		numeroTic_ : Integer
		loginUti : String
		
	constraints
		key numeroTic_
		LesTicketsReserves [numeroTic] n LesTicketsAchetes [numeroTic] = {}
		LesTicketsReserves [numeroTic] u LesTicketsAchetes [numeroTic] = LesTickets [numeroTic]
		LesTicketsReserves[loginUti] C= LesUtilisateurs[loginUti]
		LesTicketsReserves[numeroTic] C= LesTickets[numeroTic]
		
		
relation LesTicketsAchetes
	transformation 
		from R_OneToMany(AAchete)

	columns 
		numeroTic_ : Integer
		loginUti : String
		
	constraints
		key loginUti_, numeroTic_
		LesTicketsAchetes[loginUti] C= LesUtilisateurs[loginUti]
		LesTicketsAchetes[numeroTic] C= LesTickets[numeroTic]		