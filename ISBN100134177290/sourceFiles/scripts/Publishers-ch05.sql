DROP TABLE Publishers;
CREATE TABLE Publishers (Publisher_Id INT NOT NULL GENERATED ALWAYS AS IDENTITY,Name varchar(80) NOT NULL,PRIMARY KEY (Publisher_Id));
INSERT INTO Publishers (Name) VALUES ('Prentice Hall');
INSERT INTO Publishers (Name) VALUES ('Prentice Hall PTG');
exit
