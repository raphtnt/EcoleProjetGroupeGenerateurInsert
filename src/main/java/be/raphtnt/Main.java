package be.raphtnt;

import be.raphtnt.parser.DDLParser;
import be.raphtnt.parser.Table;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Main class contains the main method to execute the program for generating random data rows for database tables from a DDL file.
 */
public class Main {

    /**
     * Represents an identifier with an initial value of 0.
     */
    public static int id = 0;
    /**
     * Executer le programme pour générer des lignes de données aléatoires pour des tables de base de données à partir d'un fichier DDL.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            // Création du fichier d'insertion SQL
            FileWriter file = new FileWriter("./src/main/java/be/raphtnt/filename.sql");
            String ddlFilePath = "./src/main/java/be/raphtnt/company.sql";  // Chemin du fichier DDL
            // Création du parseur
            DDLParser parser = new DDLParser(ddlFilePath);
            List<Table> tables = parser.readFile();  // Lire les tables du fichier DDL

            System.out.println("Combien de lignes voulez-vous générer pour chaque table ?");
            int numLignes = Integer.parseInt(sc.nextLine());
            for (Table table : tables) {
                System.out.println("Table : " + table.getName());

                List<String> columns = table.getColumns();
                String columnNames = String.join(",", columns);

                List<Integer> columnTypes = new ArrayList<>();
                for (String column : columns) {
                    int type = typeDonnees(column);
                    columnTypes.add(type);
                }

                // Générer les lignes en fonction du nombre spécifié par l'utilisateur
                for (int i = 0; i < numLignes; i++) {
                    List<String> row = genererDonneesAleatoireChaqueLigne(columnTypes, columns, numLignes);
                    String values = String.join(",", row);
                    String insertSQL = String.format("INSERT INTO %s (%s) VALUES (%s);", table.getName(), columnNames, values);
//                    System.out.println(insertSQL);
                    file.write(insertSQL + "\n");
                }
                id = 0;
                System.out.println();
            }

            // Fermer le fichier une fois que tout est écrit
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates random data for each column of a table row based on the specified column types and names.
     *
     * @param columnTypes A list of integers representing the data types of each column.
     * @param columns     A list of strings representing the names of each column.
     * @param numLignes   The number of rows to generate random data for.
     * @return A list of strings representing the randomly generated data for each column in a row.
     */
    // Méthode pour générer des données aléatoires pour une ligne donnée une table et ses colonnes
    public static List<String> genererDonneesAleatoireChaqueLigne(List<Integer> columnTypes, List<String> columns, int numLignes) {
        List<String> row = new ArrayList<>();
        for (int i = 0; i < columns.size(); i++) {
            int type = columnTypes.get(i);
            String randomValue = getRandomDataForColumn(type, numLignes);
            row.add(randomValue);
        }
        return row;
    }

    /**
     * Generates random data based on the specified type for a given number of rows.
     *
     * @param type the type of data to generate:
     *             0 - AutoIncrement
     *             1 - Key
     *             2 - Number
     *             3 - Name
     *             4 - First Name
     *             5 - Date
     *             6 - Address
     *             7 - Postal Code
     *             8 - Country
     * @param numLignes the number of rows to be generated
     * @return a String representing random data based on the specified type
     */
    public static String getRandomDataForColumn(int type, int numLignes) {
        String filePath;
        List<String[]> data;
        String[] randomData;
        switch (type) {
            case 0:  // AutoIncrement
                id++;
                return String.valueOf(id);
            case 1:  // Key
                return String.valueOf((int) (Math.random() * numLignes) + 1);
            case 2:  // Nombre
                return String.valueOf((int) (Math.random() * 1000) + 1);
            case 3:  // Nom
                filePath = "./src/main/java/be/raphtnt/Nom.csv";
                data = Donnees.readCSV(filePath);
                randomData = Donnees.getRandomData(data);
                return "'" + String.join(", ", randomData) + "'";
            case 4:  // Prénom
                filePath = "./src/main/java/be/raphtnt/Prenom.csv";
                data = Donnees.readCSV(filePath);
                randomData = Donnees.getRandomData(data);
                return "'"+String.join(", ", randomData)+"'";
            case 5:  // Date
                return "NOW() - INTERVAL FLOOR(1 + (RAND() * 10000)) DAY";
            case 6:  // Adresse
                filePath = "./src/main/java/be/raphtnt/Rue.csv";
                data = Donnees.readCSV(filePath);
                randomData = Donnees.getRandomData(data);
                return "'"+String.join(", ", randomData)+"'";
            case 7:  // Code postal
                filePath = "./src/main/java/be/raphtnt/PostalcodeLocality.csv";
                data = Donnees.readCSV(filePath);
                randomData = Donnees.getRandomData(data);
                return "'"+String.join(", ", randomData)+"'";
            case 8:  // Pays
                filePath = "./src/main/java/be/raphtnt/NomPays.csv";
                data = Donnees.readCSV(filePath);
                randomData = Donnees.getRandomData(data);
                return "'"+String.join(", ", randomData)+"'";
            default:
                return null;
        }
    }

    /**
     * Méthode pour demander à l'utilisateur le type de donnée pour chaque colonne.
     *
     * @param column Le nom de la colonne pour laquelle le type de donnée doit être spécifié.
     * @return Le type de donnée choisi par l'utilisateur sous forme d'entier.
     */
    // Méthode pour demander à l'utilisateur le type de donnée pour chaque colonne
    public static int typeDonnees(String column) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel type de données voulez-vous pour la colonne \"" + column + "\" ?");
        System.out.println("----------------------");
        System.out.println("0. AutoIncrement (Nombre)");
        System.out.println("1. Key (Nombre)");
        System.out.println("2. Nombre");
        System.out.println("3. Nom");
        System.out.println("4. Prénom");
        System.out.println("5. Date");
        System.out.println("6. Adresse");
        System.out.println("7. Code postal");
        System.out.println("8. Pays");
        System.out.println("----------------------");
        String typeDonnee = sc.nextLine();
        return Integer.parseInt(typeDonnee);
    }

}