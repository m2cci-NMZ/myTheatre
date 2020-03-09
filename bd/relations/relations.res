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



relation LesOperas
	transformation 
		from R_Reference(Opera)

	columns 
		numeroSpe_ : Integer
		aUnOrchestreOpe : Boolean
		
	constraints
		key numeroSpe_

  

relation LesHumoristiques
	transformation 
		from R_Reference(Humoristique)

	columns
		numeroSpe_ : Integer
		estUnOneWomanManShowHum : Boolean
		
	constraints
		key numeroSpe_


constraints
		LesOperas[numeroSpe_] C= LesSpectacles[numeroSpe_]
		LesHumoristiques[numeroSpe_] C= LesSpectacles[numeroSpe_]



relation LesRepresentations
	transformation 
		from R_Class (Representation)
		from R_OneToMany (Spectacle)

	columns 
		horaireRep_: Date
		numeroSpe : Integer
		//placesDispoRep_d : Integer 
		//tauxReducRep : Real
		
	constraints
		key dateRep_


constraints
    LesRepresentations[numeroSpe] C= LesSpectacles[numeroSpe_]

    


