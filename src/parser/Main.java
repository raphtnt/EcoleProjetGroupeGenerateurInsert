package parser;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;

public class Main {
    public static void main(String[] args) {
        // Configurer le logger pour afficher les messages dans la console
        Logger logger = Logger.getLogger(DDLParser.class.getName());
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL);

        // Chemin vers le fichier DDL
        String ddlFilePath = "src/company.ddl";

        // Création du parseur
        DDLParser parser = new DDLParser(ddlFilePath);

        try {
            List<Table> tables = parser.readFile();

            System.out.println("Fichier analysé avec succès !");
            for (Table table : tables) {
                // Afficher les détails de chaque table
                System.out.println("Table : " + table.getName());
                System.out.println("  Colonnes : ");
                for (String column : table.getColumns()) {
                    System.out.println("    - " + column);
                }
                System.out.println("  Clés primaires : " + String.join(", ", table.getPrimaryKeys()));
                System.out.println("  Colonnes uniques : " + String.join(", ", table.getUniqueColumns()));
                System.out.println("  Clés étrangères : ");
                for (ForeignKey fk : table.getForeignKeys()) {
                    System.out.println("    - Colonne : " + fk.getColumnName() +
                            ", Référence : " + fk.getReferencedTable() +
                            "." + fk.getReferencedColumn());
                }
                System.out.println();
            }

            // Trier les tables par dépendances
            List<Table> sortedTables = parser.sortTablesByDependencies(tables);
            System.out.println("\nTables triées par dépendances :");
            for (Table table : sortedTables) {
                System.out.println("- " + table.getName());
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }
}
