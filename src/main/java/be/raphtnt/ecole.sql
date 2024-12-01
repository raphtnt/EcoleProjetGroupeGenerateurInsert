#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: etudiant
#------------------------------------------------------------

CREATE TABLE etudiant(
                         id_etudiant Int NOT NULL,
                         name        Varchar (50) NOT NULL,
    CONSTRAINT etudiant_PK PRIMARY KEY (id_etudiant)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: cours
#------------------------------------------------------------

CREATE TABLE cours(
                      id_cours Int  Auto_increment  NOT NULL,
                      nom      Varchar (50) NOT NULL,
    CONSTRAINT cours_PK PRIMARY KEY (id_cours)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: professeur
#------------------------------------------------------------

CREATE TABLE professeur(
                           id_professeur Int  Auto_increment  NOT NULL,
                           nom           Varchar (50) NOT NULL,
    CONSTRAINT professeur_PK PRIMARY KEY (id_professeur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: inscription
#------------------------------------------------------------

CREATE TABLE inscription(
                            id_inscription Int  Auto_increment  NOT NULL,
                            inscription    Varchar (50) NOT NULL,
    CONSTRAINT inscription_PK PRIMARY KEY (id_inscription)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: classe
#------------------------------------------------------------

CREATE TABLE classe(
                       id_classe Int  Auto_increment  NOT NULL,
                       nom       Varchar (50) NOT NULL,
    CONSTRAINT classe_PK PRIMARY KEY (id_classe)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: evaluation
#------------------------------------------------------------

CREATE TABLE evaluation(
                           id_evaluation Int  Auto_increment  NOT NULL,
                           evaluation    Varchar (50) NOT NULL,
    CONSTRAINT evaluation_PK PRIMARY KEY (id_evaluation)
)ENGINE=InnoDB;
