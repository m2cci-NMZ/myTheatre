(X1) Un directeur de théâtre désire informatiser son système de réservation de places pour les spectacles qui se déroulent dans son théâtre au cours d’une même saison.

(X2) A la fin d’une saison, le contenu de la base de données est archivé et vidé.

(X3) Pour les besoins du sujet les données de l’application décrites ci-après ont été simplifiées.

(X4) Dans le cadre de réunions avec le directeur les différents incréments suivant on été définis.

(X5) I1_LesSpectacles. Un spectacle est identifié par un numéro et on connaı̂t son nom, son prix de base, le public cible (1-5 ans, jeune public, tout public et adultes) et le type de spectacle (opéra, drame, humoristique, musical et cirque).

(X6) Sur les spectacles du type opéra, on indique s’il intègre une orchestre.

(X7) Sur les spectacles du type humoristique on indique s’il s’agit d’un OneWomen(Men)Show.

(X8) Un spectacle fait généralement l’objet de plusieurs représentations proposées à des moments différents.

(X9) Il y a au plus une représentation par spectacle et par jour.

(X10) Une représentation débute à un moment donné exprimé à la granularité de l’heure (par exemple 28/11/2007 20H).

(X11) I2_LesPlaces. La seule salle du théâtre est partitionnée en zones numérotées, regroupant chacune un ensemble de places.

(X12) Une place est identifiée par un numéro de rang (unique par salle), et un numéro de place dans le rang.

(X13) Une zone est associée à une seule catégorie tarifaire (orchestre, balcon, poulailler, etc).

(X14) Une catégorie peut être associé à plusieurs zones.

(X15) Toutes les places de la même zone sont dans la même catégorie.

(X16) Un taux par rapport au prix de base est associé à chaque catégorie (ex. 1 pour le poulailler, 1.2 pour l’orchestre et 1.5 pour le balcon).

(X17) Ce taux est fixé pour l’ensemble de tous les spectacles.

(X18) I3_LesTickets. Chaque place vendue par représentation fait l’objet de l’émission d’un ticket identifié par un numéro de série et estampillé par la date au moment de l’emission (instant à la granularité
de la seconde).

(X19) Un achat concerne un ou plusieurs places (i.e., plusieurs tickets) se traduisant par la création d’un dossier achat (identifié par un numéro) auquel est associé le prix global des places.

(X20) Un suivi du nombre de places disponibles pour chaque représentation programmée pour un spectacle est
envisagé.

(X21) I4_LesRéductions. Pour certaines représentations, le prix des places fait l’objet d’une promotion.

(X22) Ainsi, à chaque représentation est associé un taux de promotion (1 : pas de promotion, 0,5 : promotion
de 50, etc.).

(X23) Les personnes qui achètent des places peuvent être des spectateurs ordinaires ou des adhérents et bénéficier d’un tarif réduit.

(X24) Certaines personnes (étudiants, scolaires, militaires et séniors) bénéficient aussi d’une réduction.

(X25) Ainsi le prix d’une place fait éventuellement l’objet d’une réduction qui dépend de son type (adhérent, sénior, étudiant et militaire).

(X26) Un taux de réduction est associé a chacun de ces types (1 pour le tarif sans réduction, 0.8 pour le tarif réduit de 20%, etc.).

(X27) Le cas échéant, la réduction vient s’ajouter à la promotion associée à la représentation et événtuellement à d’autres promotions (ex. un adhérent avec 20% qui est aussi étudiant avec 10% de réduction, il aura 30% de reduction).