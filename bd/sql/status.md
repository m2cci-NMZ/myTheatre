Status actuel
=============

Fait
-----
schema.sql correspond au diagramme de classe et à relations.res (sauf pour les contraintes qu'on ne peut pas définir en SQL)
jdd1.sql correspond au diagramme d'objet
jddn1.sql fait pour l'incrément I1

verif.sql permet de vérifier des CIR supplémentaires (eg : les spectacle de typeSpe='opera' tous dans la table LesOperas)
verif.sql est complet pour l'incément I1

jdd2.sql ne correspond à aucun diagramme d'objet mais il est plus complet et sert au test de l'application
Il est complet pour les scénario S1 et S3 (onglets Programmation et Administration) mais pour la dernière version (Réservation de places)
Pour les tables la dernière version, il y a les mêmes INSERT que dans jdd1.sql


A compléter/améliorer
---------------------
jddn1.sql est fait pour l'incrément I1 mais pas pour la dernière version de schema.sql
verif.sql est complet pour l'incément I1 mais pas pour la dernière version


A faire
-------


Synthèse
--------
La BD est fonctionnelle pour le diagramme de classe allégé, toutes les tables sont présentes sauf celles mises de côtés
Pour l'incrément I1 et les scénarios S1 et S3, le travail sur la BD est terminé
Pour la dernière version, il manque encore des données (jddn1, jdd2) et des vérifications sur la BD (verif)
Pret pour le rendu


