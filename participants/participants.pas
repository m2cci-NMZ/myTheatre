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
      | `Spectateur` est une personne. Le role du `Spectateur` est de consulter la 
      | `Programmation` de `MyTheatre`.
      | Pour realiser cette tâche, `Spectateur` possède une application web.
      
      | Le role du `Spectateur` est aussi d'acheter des `Place`s pour des `Representation`s
      | Pour réaliser cette tâche, `Spectateur` possède une application web.

//-------Scenario 3 -----------------------------------------------------

actor Gerant 
      | `Gerant` est une personne. Le role du `Gerant` est de gèrer la `Programmation`s de `MyTheatre`.
      | Pour realiser cette tâche, `Gerant` utilise une application web.
      | Il peut visualiser la `Programmation` de manière globale et il peut ajouter des `Spectacle`s
      | et des `Representation`s
	
//-------Scenario 1bis -----------------------------------------------------

actor Utilisateur
      | `Utilisateur` est un `Spectateur` enregistré dans le système de `MyTheatre`. 
      | Le role de `Utilisateur` est de faire des `Reservation`.
      | Pour realiser cette tâche, le `Utilisateur` utilise une application web.



//  --- team roles ------------------------------------------------------

team role Developer
    | A developer is responsible to design, develop, test and
    | maintain models and pieces of code.

team role ScrumMaster
    | The `ScrumMaster` is the team role responsible for
    | ensuring the team lives agile values and principles and
    | follows the processes and practices that the team
    | agreed they would use.
    | The responsibilities of this role include:
    | * clearing obstacles,
    | * Establishing an environment where the team can be effective
    | * Addressing team dynamics
    | * Ensuring a good relationship between the team and
    |   product owner as well as others outside the team
    | Protecting the team from outside interruptions and distractions

consultant role ConsultantBD
    | Le `ConsultantBD` est un expert dans le domaine de la BD

consultant role ConsultantIHM
    | Le `ConsultantIHM` est un expert dans le domaine de l'IHM

consultant role ConsultantJava
    | Le `ConsultantJava` est un expert en langage Java

consultant role ConsultantProjet
    | Le `ConsultantProjet` est un expert dans le domaine de l'UML, de ModelScript et de la gestion de projet




//=========================================================================
//   "Instance" level participants
//-------------------------------------------------------------------------
// Both personae and persons are at the instance level: they belong to
// one of many participant class (actor, stakeholder or team role)
// Personae are fictional characters that serve as instance of actors.
// Persons are real-life people.
//=========================================================================

person celiaKezmane : Developer
    name : "Celia Kezmane"
    trigram : [CKE](https://github.com/m2cci-CKE)

person matthieuLehugeur : Developer
    name : "Matthieu Lehugeur"
    trigram : [MLR](https://github.com/m2cci-MLR)

person nicolasMartinez : Developer ScrumMaster
    name : "Nicolas Martinez"
    trigram : [NMZ](https://github.com/m2cci-NMZ)

person robinMiquel : Developer
    name : "Robin Miquel"
    trigram : [RML](https://github.com/m2cci-RML)

consultant naxcor : ConsultantBD
    github : [naxcor](https://github.com/naxcor)

consultant sybilleCaffiau : ConsultantIHM
    github : [SybilleCaffiau](https://github.com/SybilleCaffiau)

consultant gloucklegnou : ConsultantJava
    github : [gloucklegnou](https://github.com/gloucklegnou)

consultant escribis : ConsultantProjet
    github : [JFE](https://github.com/escribis)
    