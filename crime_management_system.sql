CREATE DATABASE Crime;
USE Crime;

CREATE TABLE Crime (
  CrimeID INT PRIMARY KEY,
  IncidentType VARCHAR(255),
  IncidentDate DATE,
  Location VARCHAR(255),
  Description TEXT,
  Status VARCHAR(20)
  );
  
  CREATE TABLE Victim(
   VictimID INT PRIMARY KEY,
   CrimeID INT,
   Name VARCHAR(255),
   ContactInfo VARCHAR(255),
   Injuries VARCHAR(255),
   FOREIGN KEY(CrimeID) REFERENCES Crime(CrimeID)
   );
   
CREATE TABLE Suspect(
 SuspectID INT PRIMARY KEY,
 CrimeID INT,
 SuspectName VARCHAR(255),
 SuspectDescription TEXT,
 CriminalHistory TEXT,
 FOREIGN KEY(CrimeID) REFERENCES Crime(CrimeID)
 );
 
 INSERT INTO Crime(CrimeID, IncidentType, IncidentDate, Location, Description, Status) VALUES
   (1, 'Robbery', '2023-09-15', '123 Main St, Cityville', 'Armed robbery at a convenience store', 'Open'),
   (2, 'Homicide', '2023-09-20', '456 Elm St, Townsville', 'Investigation into a murder case', 'Under Investigation'),
   (3, 'Theft', '2023-09-10', '789 Oak St, Villagetown', 'Shoplifting incident at a mall', 'Closed');
   
INSERT INTO Victim (VictimID, CrimeID, Name, ContactInfo, Injuries) VALUES
   (1,1,'John Doe','johndoe@example.com','Minor injuries'),
   (2, 2, 'Jane Smith', 'janesmith@example.com', 'Deceased'),
   (3, 3, 'Alice Johnson', 'alicejohnson@example.com', 'None');
   
INSERT INTO Suspect(SuspectID, CrimeID, SuspectName, SuspectDescription, CriminalHistory) VALUES
	(1, 1, 'Robber 1', 'Armed and masked robber', 'Previous robbery convictions'),
	(2, 2, 'Unknown', 'Investigation ongoing', NULL),
	(3, 3, 'Suspect 1', 'Shoplifting suspect', 'Prior shoplifting arrests');

SELECT*FROM Suspect;
SELECT*FROM Crime;
SELECT*FROM Victim;

-- 1.Select all open incidents.alter

SELECT*FROM Crime WHERE Status = 'Open';

-- 2.Find the total number of incidents.

SELECT COUNT(*) CrimeID FROM Crime;

-- 3.List all unique incident types.

SELECT DISTINCT IncidentType FROM Crime;

-- 4.Retrieve incidents that occurred between '2023-09-01' and '2023-09-10'.

SELECT*FROM Crime WHERE IncidentDate BETWEEN '2023-09-01' AND '2023-09-10';

-- 5.List persons involved in incidents in descending order of age.

ALTER TABLE Victim ADD Age INT;
ALTER TABLE Suspect ADD Age INT;

UPDATE Victim SET Age = 34 WHERE VictimID = 1;
UPDATE Victim SET Age = 45 WHERE VictimID = 2; 
UPDATE Victim SET Age = 28 WHERE VictimID = 3;

UPDATE Suspect SET AGE = 38 WHERE SuspectID =1;
UPDATE Suspect SET AGE = 50 WHERE SuspectID =2;
UPDATE Suspect SET AGE = 30 WHERE SuspectID =3;

-- 6.Find the average age of persons involved in incidents.

SELECT AVG(Age) AS AverageAge
FROM ( SELECT Age FROM Victim
UNION 
SELECT Age FROM Suspect)
AS AllPersons;

-- 7.List incident types and their counts, only for open cases.

SELECT IncidentType, COUNT(CrimeID) AS Total_Count
FROM Crime WHERE Status = 'Open'
GROUP BY IncidentType;

-- 8.Find persons with names containing 'Doe'

SELECT name
FROM Victim
WHERE name LIKE  '%Doe%';

-- 9.Retrieve the names of persons involved in open cases and closed cases.

SELECT Name 
FROM Victim AS Victim
JOIN Crime ON Victim.CrimeID = Crime.CrimeID
WHERE Crime.Status IN ('Open','Closed'); 

-- 10.List incident types where there are persons aged 30 or 35 involved.

SELECT Crime.IncidentType
FROM Crime
 WHERE CrimeID IN (SELECT CrimeID FROM Victim WHERE Age IN (30,35)
 UNION 
 SELECT CrimeID FROM Suspect WHERE Age IN(30,35));

 -- EXTRA----
 
 SELECT Crime.IncidentType, Victim.Name
FROM Crime 
JOIN (SELECT Victim.Name, CrimeID FROM Victim WHERE Age IN (30, 35)
UNION
SELECT SuspectName AS Name, CrimeID FROM Suspect WHERE Age IN (30, 35))
AS Victim ON Crime.CrimeID = Victim.CrimeID;

-- 11.Find persons involved in incidents of the same type as 'Robbery'.

SELECT Name AS PersonName, 'Victim' AS Role
FROM Victim
WHERE CrimeID IN (
SELECT CrimeID 
FROM Crime
WHERE IncidentType = (
SELECT IncidentType FROM Crime WHERE IncidentType = 'Robbery' LIMIT 1))
UNION
SELECT SuspectName AS PersonName, 'Suspect' AS Role
FROM Suspect
WHERE CrimeID IN (
SELECT CrimeID 
FROM Crime
WHERE IncidentType = (
SELECT IncidentType FROM Crime WHERE IncidentType = 'Robbery' LIMIT 1));

-- 12.List incident types with more than one open case.

SELECT IncidentType, Status
FROM Crime
WHERE Status = 'Open'
GROUP BY IncidentType
HAVING COUNT(*) >1;

-- 13.List all incidents with suspects whose names also appear as victims in other incidents.

SELECT IncidentType, SuspectName
FROM Crime
JOIN Suspect ON Crime.CrimeID = Suspect.CrimeID 
WHERE  Suspect.SuspectName IN ( SELECT Name FROM Victim)
AND Suspect.CrimeID NOT IN (
SELECT CrimeID FROM Victim WHERE Name = Suspect.SuspectName);

-- 14.Retrieve all incidents along with victim and suspect details.

SELECT Crime.CrimeID, Crime.IncidentType, Crime.IncidentDate, Crime.Location, Crime.Description, Crime.Status, 
Victim.Name AS VictimName, Victim.ContactInfo, Victim.Injuries, Suspect.SuspectID, Suspect.SuspectName, Suspect.SuspectDescription, Suspect.CriminalHistory
FROM Crime
LEFT JOIN Victim ON Crime.CrimeID = Victim.CrimeID
LEFT JOIN Suspect ON Crime.CrimeID = Suspect.CrimeID; 


-- 15.Find incidents where the suspect is older than any victim.

SELECT Crime.CrimeID, Crime.IncidentType, Suspect.SuspectName, Suspect.Age AS SuspectAge
FROM Crime
JOIN Suspect ON Crime.CrimeID = Suspect.CrimeID
WHERE Suspect.Age > ANY (SELECT Age FROM Victim WHERE Age IS NOT NULL);

-- 16.Find suspects involved in multiple incidents:

SELECT SuspectName, COUNT(*) AS IncidentCount
FROM Suspect
GROUP BY SuspectName
HAVING COUNT(*) >1;

-- 17.List incidents with no suspects involved.

SELECT Crime.*
FROM Crime
LEFT JOIN Suspect ON Crime.CrimeID = Suspect.CrimeID
WHERE Suspect.CrimeID IS NULL;

-- 18.List all cases where at least one incident is of type 'Homicide' and all other incidents are of type 'Robbery'.

SELECT CrimeID, IncidentType
FROM Crime
WHERE EXISTS ( 
	SELECT 1 
	FROM Crime Crime1
    WHERE Crime2.IncidentType = 'Homicide'
)
AND NOT EXISTS (
    SELECT 1
    FROM Crime c3
    WHERE c3.IncidentType NOT IN ('Homicide', 'Robbery')
);

-- 19.Retrieve a list of all incidents and the associated suspects, showing suspects 
-- for each incident, or 'No Suspect' if there are none.

SELECT Crime.CrimeID, Crime.IncidentType, Crime.IncidentDate, Crime.Location,
COALESCE(Suspect.SuspectName, 'No Suspect') AS SuspectName
FROM Crime
LEFT JOIN Suspect ON Crime.CrimeID = Suspect.CrimeID;


-- 20.List all suspects who have been involved in incidents with incident types 'Robbery' or 'Assault'

SELECT Suspect.*
FROM Suspect 
LEFT JOIN Crime ON Suspect.CrimeID = Crime.CrimeID
WHERE IncidentType IN ('Robbery','Assault');

