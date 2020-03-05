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
actor Client
      | Client est une `Personne`. Le role du Client est de consulter la 
      | `Programmation` de `MyTheatre`.
      | Pour realiser cette tâche, Client possède une application web.

