-- Create the ADR table
-- Note that the validlgcs column stores its data in a VARCHAR.
-- This means that data should be packed into a string array (semicolon delimiter preferable)
CREATE TABLE IF NOT EXISTS `adr`(
boro SMALLINT,
block INTEGER,
lot INTEGER,
bin INTEGER,
lhnd VARCHAR,
lhns VARCHAR,
lcontpar CHAR,
lsos CHAR,
hhnd VARCHAR,
hhns VARCHAR,
hcontpar CHAR,
hsos CHAR,
scboro SMALLINT,
sc5 INTEGER,
sclgc SMALLINT,
stname VARCHAR,
addrtype CHAR,
realb7sc INTEGER,
validlgcs VARCHAR,
parity SMALLINT,
b10sc BIGINT,
segid INTEGER
);

-- Create the BBL table
CREATE TABLE IF NOT EXISTS `bbl`(
loboro SMALLINT,
loblock INTEGER,
lolot INTEGER,
lobblscc SMALLINT,
hiboro SMALLINT,
hiblock INTEGER,
hilot INTEGER,
hibblscc SMALLINT,
boro SMALLINT,
block INTEGER,
lot INTEGER,
bblscc SMALLINT,
billboro SMALLINT,
billblock INTEGER,
billlot INTEGER,
billbblscc SMALLINT,
condoflag CHAR,
condonum VARCHAR,
coopnum INTEGER,
numbf SMALLINT,
numaddr INTEGER,
vacant CHAR,
interior CHAR
);
