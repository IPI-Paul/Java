DROP TABLE Authors;
CREATE TABLE Authors (Author_Id INT NOT NULL AUTO_INCREMENT,firstName varchar(20) NOT NULL,lastName varchar(30) NOT NULL,Name varchar(50) NOT NULL,PRIMARY KEY (Author_Id));
INSERT INTO Authors (firstName, lastName, Name) VALUES ('Paul','Deitel','Paul Deitel');
INSERT INTO Authors (firstName, lastName, Name) VALUES ('Harvey','Deitel','Harvey Deitel');
INSERT INTO Authors (firstName, lastName, Name) VALUES ('Abbey','Deitel','Abbey Deitel');
INSERT INTO Authors (firstName, lastName, Name) VALUES ('Dan','Quirk','Dan Quirk');
INSERT INTO Authors (firstName, lastName, Name) VALUES ('Michael','Morgano','Michael Morgano');
INSERT INTO Authors (firstName, lastName, Name) VALUES ('Tem','Nieto','Tem Nieto');
INSERT INTO Authors (firstName, lastName, Name) VALUES ('Sean','Santry','Sean Santry');
exit