(X1) Un directeur de théâtre désire informatiser son système de réservation de `Place`s pour les `Spectacle`s qui se déroulent dans son `MyTheatre` au cours d’une même saison.

(X2) A la fin d’une saison, le contenu de la base de données est archivé et vidé.

(X3) Pour les besoins du sujet les données de l’application décrites ci-après ont été simplifiées.

(X4) Dans le cadre de réunions avec le directeur les différents incréments suivant on été définis.

(X5) I1_LesSpectacles. Un `Spectacle` est identifié par un numéro et on connaı̂t son nom, son prix de base, le public cible (1-5 ans, jeune public, tout public et adultes) et le type de `Spectacle` (`Opera`, `Drame`, `Humoristique`, `Musical` et `Cirque`).

(X6) Sur les `Spectacle`s du type `Opera`, on indique s’il intègre une orchestre.

(X7) Sur les `Spectacle` du type `Humoristique` on indique s’il s’agit d’un OneWomen(Men)Show.

(X8) Un `Spectacle` fait généralement l’objet de plusieurs `Représentation`s proposées à des `Horaire`s différentes.

(X9) Il y a au plus une `Representation` par `Spectacle` et par jour.

(X10) Une `Repreentation` débute à une `Horaire` donnée exprimée à la granularité de l’heure (par exemple 28/11/2007 20H).

(X11) I2_LesPlaces. La seule salle de `MyTheatre` est partitionnée en `Zone`s numérotées, regroupant chacune un ensemble de `Place`s.

(X12) Une `Place` est identifiée par un numéro de rang (unique par salle), et un numéro de `Place` dans le rang.

(X13) Une `Zone` est associée à une seule `Categorie Tarifaire` (orchestre, balcon, poulailler, etc).

(X14) Une `Categorie Tarifaire` peut être associé à plusieurs `Zone`s.

(X15) Toutes les `Place`s de la même `Zone` sont dans la même `Categorie Tarifaire`.

(X16) Un taux par rapport au prix de base est associé à chaque `Categorie Tarifaire` (ex. 1 pour le poulailler, 1.2 pour l’orchestre et 1.5 pour le balcon).

(X17) Ce taux est fixé pour l’ensemble de tous les `Spectacle`s.

(X18) I3_LesTickets. Chaque `Place` vendue par `Representation` fait l’objet de l’émission d’un `Ticket` identifié par un numéro de série et estampillé par la date au moment de l’emission (instant à la granularité
de la seconde).

(X19) Un achat concerne un ou plusieurs `Ticket`s se traduisant par la création d’un `Dossier Achat` (identifié par un numéro) auquel est associé le prix global des `Place`s.

(X20) Un suivi du nombre de `Place`s disponibles pour chaque `Représentation` programmée pour un `Spectacle` est envisagé.

(X21) I4_LesRéductions. Pour certaines `Representation`s, le prix des `Place`s fait l’objet d’une `Reduction`.

(X22) Ainsi, à chaque `Representation` est associé un taux de promotion (1 : pas de promotion, 0,5 : promotion de 50, etc.).

(X23) Les `Spectateur`s qui achètent des `Place`s peuvent être des spectateurs ordinaires ou des `Adherent`s et bénéficier d’une `Reduction`.

(X24) Certains `Spectateur`s (étudiants, scolaires, militaires et séniors) bénéficient aussi d’une `Reduction`.

(X25) Ainsi le prix d’une `Place` fait éventuellement l’objet d’une `Reduction` qui dépend de son `Status` (adhérent, sénior, étudiant et militaire).

(X26) Un taux de réduction est associé a chacun de ces `Status` (1 pour le tarif sans réduction, 0.8 pour le tarif réduit de 20%, etc.).

(X27) Le cas échéant, la `Reduction` vient s’ajouter à la `Reduction` associée à la `Représentation` et événtuellement à d’autres `Reduction`s (ex. un `Adherent` avec 20% qui est aussi étudiant avec 10% de réduction, il aura 30% de reduction).
