-- Create the ADR table
-- Note that the validlgcs column stores its data in a VARCHAR.
-- This means that data should be packed into a string array (semicolon delimiter preferable)
CREATE TABLE adr(
boro SMALLINT,
block INTEGER,
lot INTEGER,
bin INTEGER,
lhnd VARCHAR(255),
lhns VARCHAR(255),
lcontpar CHAR,
lsos CHAR,
hhnd VARCHAR(255),
hhns VARCHAR(255),
hcontpar CHAR,
hsos CHAR,
scboro SMALLINT,
sc5 INTEGER,
sclgc SMALLINT,
stname VARCHAR(255),
addrtype CHAR,
realb7sc INTEGER,
validlgcs VARCHAR(255),
parity SMALLINT,
b10sc BIGINT,
segid INTEGER
)