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


relation LesSpectacle 

    transformation 
      from R_Class(Spectacle)
     
    columns 
      numeroSpe_: Integer
      nomSpe : String
      prixDeBaseSpe : Real
      cibleSpe : Cible
      typeSpe : Type
      
      
relation LesOpera
     transformation 
      from R_Reference(Spectacle)

     columns 
       aUnOrchestre : boolean 
       numeroSpe:Integer
        

relation LesHumoristique
     transformation 
      from R_Reference(Spectacle)

     columns 
       estUnOneWomanManShow: boolean 
       numeroSpe:Integer


relation LesRepresentation

    transformation 
      from R_Class (Representation)
      from R_OneToMany (Spectacle)

    columns 
      dateRep_: String
      numeroSpe: Integer
      // placesDispo_dRep : Integer 
      //tauxReducRep : Real


    


