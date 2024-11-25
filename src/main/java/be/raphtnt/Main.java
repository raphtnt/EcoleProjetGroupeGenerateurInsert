package be.raphtnt;

import be.raphtnt.parser.DDLParser;
import be.raphtnt.parser.ForeignKey;
import be.raphtnt.parser.Table;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            int typeDonnees;
            FileWriter file = new FileWriter("C:\\Users\\LambrechtR\\PhpstormProjects\\Dilibel\\EcoleProjetGroupeGenerateurInserts\\src\\main\\java\\be\\raphtnt\\filename.sql");
            String ddlFilePath = "C:\\Users\\LambrechtR\\PhpstormProjects\\Dilibel\\EcoleProjetGroupeGenerateurInserts\\src\\main\\java\\be\\raphtnt\\company.sql";
//            file.write("Files in Java might be tricky, but it is fun enough!\n");

            // Création du parseur
            DDLParser parser = new DDLParser(ddlFilePath);
            int nbLigne = nombreLigne();
            List<Table> tables = parser.readFile();
            for (Table table : tables) {
                System.out.println("Table : " + table.getName());
                System.out.println("  Colonnes : ");
                for (String column : table.getColumns()) {
                    System.out.println("    - " + column);
                    typeDonnees = typeDonnees();
                    traitementDonnees(nbLigne, typeDonnees);
                }
                System.out.println();
            }
            file.close();
        }catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static int nombreLigne() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Combien de lignes voulez-vous ?");
        System.out.println("----------------------");
        String nbLigne = sc.nextLine();
        return Integer.parseInt(nbLigne);
    }

    public static int typeDonnees() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quelle donnée voulez-vous ?");
        System.out.println("----------------------");
        System.out.println("0. Nombre");
        System.out.println("1. Nom");
        System.out.println("2. Prénom");
        System.out.println("3. Date de naissance");
        System.out.println("4. Adresse");
        System.out.println("5. Code postal");
        System.out.println("6. Ville");
        System.out.println("----------------------");
        String typeDonnee = sc.nextLine();
        return Integer.parseInt(typeDonnee);
    }

    public static void traitementDonnees(int nombreLigne, int typeDonnees) {
        for (int i = 0; i < nombreLigne; i++) {
            switch (typeDonnees) {
                case 0:
                    System.out.print("Nombre : ");

                    break;
                case 1:
                    System.out.print("Nom : ");
                    break;
                case 2:
                    System.out.print("Prénom : ");
                    break;
                case 3:
                    System.out.print("Date de naissance : ");
                    break;
                case 4:
                    System.out.print("Adresse : ");
                    break;
                case 5:
                    System.out.print("Code postal : ");
                    break;
                case 6:
                    System.out.print("Ville : ");
                    break;
            }
        }
    }
}