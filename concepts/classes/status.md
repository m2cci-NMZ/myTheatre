Status actuel
=============

Fait
----
Diagramme de classe terminé pour le scénario 1 et 3 :
	`Spectacle`, `Opera`, `Humoristique` et `Representation`

Pour les scénarios suivants, nous avons pris une version allégée du diagramme :
- On a gardé `Rang`, `Place` mais pas `Zone` et `Categorie` -> Pas de variation des prix des places liée à la catégorie tarifaire de la Zone
- On a gardé `Utilisateur` mais pas `Spectateur` -> En l'état, pour acheter un `Ticket` il faut être un `Utilisateur`
- On n'a pas gardé `Statut` (d'un `Spectateur`) -> Pas de `Reduction`s liées à un statut particulier
- On a gardé `Ticket` et `DossierAchat`

Contraintes faites pour S1, S3 et S1bis (allégé)


A compléter/améliorer
---------------------


A faire
-------
Prendre en compte les classes qui ont été mises de côté pour le moment


Synthèse
--------
Diagramme complet pour S1 et S3 mais partiel vis à vis du scénario S1bis
Il compile avec USE
Il est autosuffisant (il est fonctionnel en l'état)
Contraintes spécifiées
Prêt pour le rendu