//=========================================================================
//                      Participant model
//-------------------------------------------------------------------------
// This glossary model is expressed in ParticipantScript
// See https://modelscript.rtfd.io/en/latest/languages/participants/index.html
//=========================================================================

participant model MyTheatre
import glossary model from "../glossaries/glossaries.gls"

//==========================================================================
// "Class" level participants
//==========================================================================
// "Actors" are defined by UML usecase model ; they represent (classes of) users.
// "Stakeholders" have some interest in the system and/or its development.
// "Team roles" collaborate to design and develop the system/
//==========================================================================

// ----actors---------------------------------------------------------------
//-------Scenario 1 -----------------------------------------------------

actor Spectateur
      | `Spectateur` est une `Personne`. Le role du `Spectateur` est de consulter la 
      | `Programmation` de `MyTheatre`.
      | Pour realiser cette tâche, `Spectateur` possède une application web.


//-------Scenario 3 -----------------------------------------------------
actor Gerant 
      | `Gerant` est une `Personne`. le role du `Gerant` est de gèrer le planning 
      | des `Representation`s de `MyTheatre`.
      | pour realiser cette tâche, le `Gerant` connecte à son mail et voir les               
      | propositions.
      | Le `Gerant` peut planifier des `Representation`s 
      | Il peut accéder au  planning des `Representation`s et le modifier 
