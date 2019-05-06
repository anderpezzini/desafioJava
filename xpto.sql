DROP TABLE Cidade;
CREATE TABLE Cidade(idibge              NUMBER(38) CONSTRAINT PK_CIDADE PRIMARY KEY,
                    uf                  VARCHAR2(2) CONSTRAINT NN_CIDADE_UF NOT NULL,
                    name                VARCHAR2(150) CONSTRAINT NN_CIDADE_NAME NOT NULL,
                    capital             NUMBER(1) DEFAULT 0 CONSTRAINT CK_CIDADE_CAPITAL_BOOLEAN CHECK (capital in (0,1)),
                    lon                 NUMBER(38,10) CONSTRAINT NN_CIDADE_LON NOT NULL,
                    lat                 NUMBER(38,10) CONSTRAINT NN_CIDADE_LAT NOT NULL,
                    no_accents          VARCHAR2(150),
                    alternative_names   VARCHAR2(150),
                    microregion         VARCHAR2(150),
                    mesoregion          VARCHAR2(150));