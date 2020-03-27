MyTheatre
=============

Application permettant de gérer un `Theatre`.
Elle permet a un `Spectateur` de consulter la `Programmation` du `Theatre`, et de reserver des `Places` pour une `Representation`.
Elle permet aussi au `Gerant` de modifier la `Programmation` en ajoutant des `Representation`s et des `Spectacle`s. 


Documentation pour le déploiement:
---------------------
* Créer la base de données en se placant dans de dossier bd/sql et en exécutant le script creer_la_bd.sh avec le fichier jdd1.sql
* Modifier le fichier context.xml afin de mettre le bon chemin d'accès à la base de données
* Créer un fichier properties.jdbc contenant deux lignes: "jdbcDriver=org.sqlite.JDBC" et "dataBaseUrl=jdbc:sqlite:/mon_chemin_d'acces/dev/MyTheatre/MyTheatreDAO/bd/test.db". Ce fichier doit se trouver dans le repertoire "/mon_chemin_d'acces/dev/MyTheatre/MyTheatreDAO/bd/"


Contenu du répertoire
---------------------
La structure du répertoire est conforme à la [structure ModelScript](https://modelscript.readthedocs.io/en/latest/artefacts/index.html).


Differentes versions
---------------------
v3.xx
* Version complète pour les scénario S1 et S3 et de bonne qualité
* Elle permet a un `Spectateur` de consulter la `Programmation` du `Theatre`
* Elle permet aussi au `Gerant` de modifier la `Programmation` en ajoutant des `Representation`s et des `Spectacle`s

v4.xx
* Version partielle pour le scénario S1bis et de qualité moyenne
* Elle permet de reserver des `Places` pour les `Representation`
* Réalisation du scénario incomplete, et qualité de développement en dessous de celle des autres versions : absence de tests et de recupération d'erreur
* Reduit un peu la qualité de la consultation de la programmation à cause d'une modification de l'IHM (scénario S1)
* En pratique, cette version n'aurait pas été released, mais etant donné que c'est la version la plus finale du projet, elle l'a été pour que cette partie soit évaluée


Auteurs
---------------------
* [Celia Kezmane](https://github.com/m2cci-CKE)
* [Matthieu Lehugeur](https://github.com/m2cci-MLR)
* [Nicolas Martinez](https://github.com/m2cci-NMZ)
* [Robin Miquel](https://github.com/m2cci-RML)


Remerciements
---------------------
* Jean-Marie Favre
* Sybille Caffiau
* Philippe Genoud
* Mario Cortes-Cornax
