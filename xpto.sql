DROP TABLE Cidade;
CREATE TABLE Cidade(id      NUMBER(38) CONSTRAINT PK_CIDADE PRIMARY KEY,
                    nome    VARCHAR2(150) CONSTRAINT NN_CIDADE_NOME NOT NULL,
                    capital NUMBER(1) DEFAULT 0 CONSTRAINT CK_CIDADE_CAPITAL_BOOLEAN CHECK (capital in (0,1)),
                    uf      VARCHAR2(2) CONSTRAINT NN_CIDADE_UF NOT NULL,
                    idibge  NUMBER(38),
                    posX    NUMBER(38),
                    posY    NUMBER(38));
                    
INSERT INTO Cidade c (c.id, c.nome, c.capital, c.uf, c.idibge, c.posX, c.posY) VALUES (1, 'Blumenau',       0, 'SC', 1, 1, 1);
INSERT INTO Cidade c (c.id, c.nome, c.capital, c.uf, c.idibge, c.posX, c.posY) VALUES (2, 'Rodeio',         0, 'SC', 1, 10, 11);
INSERT INTO Cidade c (c.id, c.nome, c.capital, c.uf, c.idibge, c.posX, c.posY) VALUES (3, 'Florianópolis',  1, 'SC', 2, 10, 12);
INSERT INTO Cidade c (c.id, c.nome, c.capital, c.uf, c.idibge, c.posX, c.posY) VALUES (4, 'Curitiba',       1, 'PR', 2, 100, 100);
INSERT INTO Cidade c (c.id, c.nome, c.capital, c.uf, c.idibge, c.posX, c.posY) VALUES (5, 'Pato Branco',    0, 'PR', 2, 10, 13); 

COMMIT;