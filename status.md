Status actuel
=============

Fait
----
concepts/. est complété :
	- le diagramme de classe est dans une version un peu allégée
	- diagramme d'objet correspond au diagramme de classe
	- le glossaire et les scénario ont été réécrit

bd/. est complété :
	- schéma de la BD à jour
	- jdd1 de la BD à jour avec le diagramme d'objet
	- jddn1 permettant de tester la BD
	- verif permet de valider des contraintes supplémentaires

cu/. et participants/. sont complétés :
	- cu pour les scénarii S1 S3 et S1bis (pas allégé)
	- participants pour pour les scénarii S1 S3 et S1bis (pas allégé)

ihm/. est complété pour les scénarii S1 et S3 :
	- modèles de tâche pour S1 S3 et S1bis
	- charte graphique respectée pour S1 et S3
	- maquette pour S1 et S3
	- analyse experte pour S1 et S3 (S1bis pas fini donc pas évaluable)

projet/. est complété
	- plannings et retrospectives pour sprint1, sprint2 et sprint3
	- audit et compte rendu d'audit pour sprint1 et sprint2
	- soutenance prete
	- suivi effectué

dev pour S1 et S3 (cf v3.xx) 
	- application répondant aux scenarii S1 et S3
	- partie modele testée
	- partie DAO testée


A compléter/améliorer
---------------------
dev pour S1bis (cf v4.xx)
	- application fonctionnelle pour une partie du scénario
	- aucun test (manque de temps)
	- aucune recuperation d'erreur 
	- impact très légèrement négatif sur les scénario précédents (modification de l'ihm du S1 : onglet programmation qui n'a pas été faite en respectant tous les critères de qualité par manque de temps)
	- nécessiterait une bonne semaine supplémentaire pour être au niveau de qualité des scenarii S1 et S3 de la version v3.xx


A faire
-------
ihm/. est a compléter pour S1bis :
	- maquette à faire et dev pour faire correspondre l'appli avec la maquette (pour le moment on utilise l'affichae par defaut de M. Genoud)
	- alignement avec la charte graphique
	- analyse experte à faire une fois le dev terminé

	

Synthèse
--------
v3.xx : S1, S3 complet et de bonne qualité
        Partie concept (tout sauf dev) de S1bis (allégé) complet est de bonne qualité
v4.xx : S1 un petit peu affecté par des ajouts au niveau de l'interface qui ne sont pas conformes aux critères de qualité
        S3 complet et de bonne qualité
        S1bis : Reservation possible mais qualité assez moyenne de part l'absence de test et l'absence de gestion des erreurs
                Pas d'achat possible, uniquement des reservations


