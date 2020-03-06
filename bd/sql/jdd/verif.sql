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

SELECT *
FROM LesSpectacles
WHERE typeSpe = 'humoristique' AND numeroSpe NOT IN (
	SELECT numeroSpe FROM LesHumoristiques
);
	
SELECT *
FROM LesSpectacles
WHERE typeSpe = 'opera' AND numeroSpe NOT IN (
	SELECT numeroSpe FROM LesOperas
);

