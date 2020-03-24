--=========================================================================
-- MyTheatre
--=========================================================================
-- Based on the course of M.C. Fauvet
---------------------------------------------------------------------------

---------------------------------------------------------------------------
--  This tests the integrity of the DB
---------------------------------------------------------------------------

---------------------------------------------------------------------------
-- Prolog for sqlite
---------------------------------------------------------------------------s

PRAGMA foreign_keys = ON;

--------------------------------------------------------------------------
-- LesHumoristiques et LesOperas
---------------------------------------------------------------------------

-- Les Spectacles de type humoristique qui ne sont pas aussi dans la table LesHumoristiques
SELECT *
FROM LesSpectacles
WHERE typeSpe = 'humoristique' AND numeroSpe NOT IN (
	SELECT numeroSpe FROM LesHumoristiques
);

-- Les Spectacles de type opera qui ne sont pas aussi dans la table LesOperas	
SELECT *
FROM LesSpectacles
WHERE typeSpe = 'opera' AND numeroSpe NOT IN (
	SELECT numeroSpe FROM LesOperas
);

-- Les Spectacles dans LesHumoristique qui ne sont pas de type humoristiques
SELECT *
FROM LesHumoristiques
WHERE numeroSpe NOT IN (
	SELECT numeroSpe FROM LesSpectacles
	WHERE typeSpe = 'humoristique'
);

-- Les Spectacles dans LesOpera qui ne sont pas de type opera
SELECT *
FROM LesOperas
WHERE numeroSpe NOT IN (
	SELECT numeroSpe FROM LesSpectacles
	WHERE typeSpe = 'opera'
);
