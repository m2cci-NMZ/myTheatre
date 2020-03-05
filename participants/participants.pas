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
actor Spectateur
      | `Spectateur` est une `Personne`. Le role du `Spectateur` est de consulter la 
      | `Programmation` de `MyTheatre`.
      | Pour realiser cette tâche, `Spectateur` possède une application web.

