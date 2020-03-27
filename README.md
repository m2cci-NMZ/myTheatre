MyTheatre
=============

Application permettant de gérer un `Theatre`.
Elle permet a un `Spectateur` de consulter la `Programmation` du `Theatre`, et de reserver des `Places` pour une `Representation`.
Elle permet aussi au `Gerant` de modifier la `Programmation` en ajoutant des `Representation`s et des `Spectacle`s. 

Documentation pour le déploiement:
---------------------

	-Créer la base de données en se placant dans de dossier bd/sql et en exécutant le script creer_la_bd.sh avec le fichier jdd1.sql
	-Modifier le fichier context.xml afin de mettre le bon chemin d'accès à la base de données
	-Créer un fichier properties.jdbc contenant deux lignes: "jdbcDriver=org.sqlite.JDBC" et "dataBaseUrl=jdbc:sqlite:/mon_chemin_d'acces/dev/MyTheatre/MyTheatreDAO/bd/test.db". Ce fichier doit se trouver dans le repertoire "/mon_chemin_d'acces/dev/MyTheatre/MyTheatreDAO/bd/"

Contenu du répertoire
---------------------

La structure du répertoire est conforme à la [structure ModelScript](https://modelscript.readthedocs.io/en/latest/artefacts/index.html).


