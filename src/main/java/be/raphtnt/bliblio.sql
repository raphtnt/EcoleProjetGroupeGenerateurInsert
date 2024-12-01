#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: livre
#------------------------------------------------------------

CREATE TABLE livre(
                      id_livre Int  Auto_increment  NOT NULL,
                      titre    Varchar (50) NOT NULL,
    CONSTRAINT livre_PK PRIMARY KEY (id_livre)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: adherent
#------------------------------------------------------------

CREATE TABLE adherent(
                         id_adherent Int  Auto_increment  NOT NULL,
                         nom         Varchar (50) NOT NULL,
    CONSTRAINT adherent_PK PRIMARY KEY (id_adherent)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: emprunt
#------------------------------------------------------------

CREATE TABLE emprunt(
                        id_emprunt Int  Auto_increment  NOT NULL,
                        emprunt    Varchar (50) NOT NULL,
    CONSTRAINT emprunt_PK PRIMARY KEY (id_emprunt)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: exemplaire
#------------------------------------------------------------

CREATE TABLE exemplaire(
                           id_exemplaire Int  Auto_increment  NOT NULL,
                           etat          Varchar (50) NOT NULL,
    CONSTRAINT exemplaire_PK PRIMARY KEY (id_exemplaire)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: bibliothecaire
#------------------------------------------------------------

CREATE TABLE bibliothecaire(
                               id_bibliothecaire Int  Auto_increment  NOT NULL,
                               nom               Varchar (50) NOT NULL,
    CONSTRAINT bibliothecaire_PK PRIMARY KEY (id_bibliothecaire)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: section
#------------------------------------------------------------

CREATE TABLE section(
                        id_section Int  Auto_increment  NOT NULL,
                        nom        Varchar (50) NOT NULL,
    CONSTRAINT section_PK PRIMARY KEY (id_section)
)ENGINE=InnoDB;
