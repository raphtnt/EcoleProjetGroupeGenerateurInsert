package parser;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.logging.*;

public class DDLParser {

    private static final Logger logger = Logger.getLogger(DDLParser.class.getName());
    private final String filePath;

    public DDLParser(String filePath) {
        this.filePath = filePath;
    }

    public List<Table> readFile() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<Table> tables = new ArrayList<>();
        Table currentTable = null;

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            // Détection de la création d'une table
            Matcher matchTable = Pattern.compile("CREATE TABLE (\\w+)", Pattern.CASE_INSENSITIVE).matcher(line);
            if (matchTable.find()) {
                String tableName = matchTable.group(1);
                currentTable = new Table(tableName);
                tables.add(currentTable);
                logger.info("Création de la table détectée : " + tableName);
                continue;
            }

            if (currentTable != null) {
                // Détection de la clé primaire
                Matcher matchPrimary = Pattern.compile("PRIMARY KEY\\s*\\(([^)]+)\\)", Pattern.CASE_INSENSITIVE).matcher(line);
                if (matchPrimary.find()) {
                    List<String> primaryKeys = Arrays.asList(matchPrimary.group(1).split(","));
                    primaryKeys.replaceAll(String::trim);
                    currentTable.setPrimaryKeys(primaryKeys);
                    logger.info("Clés primaires définies : " + primaryKeys + " pour la table " + currentTable.getName());
                    continue;
                }

                // Détection des clés étrangères
                Matcher matchForeign = Pattern.compile("FOREIGN KEY\\s*\\((\\w+)\\)\\s*REFERENCES\\s*(\\w+)\\s*\\((\\w+)\\)", Pattern.CASE_INSENSITIVE).matcher(line);
                if (matchForeign.find()) {
                    String columnName = matchForeign.group(1);
                    String referencedTable = matchForeign.group(2);
                    String referencedColumn = matchForeign.group(3);
                    currentTable.addForeignKey(columnName, referencedTable, referencedColumn);
                    logger.info("Clé étrangère ajoutée : " + columnName + " -> " + referencedTable + "." + referencedColumn + " dans la table " + currentTable.getName());
                    continue;
                }

                // Détection des colonnes
                String regex = "^(\\w+)\\s+([\\w()]+)(?:\\s+((?:UNIQUE|NOT\\s+NULL|AUTO_INCREMENT|PRIMARY\\s+KEY)(?:\\s+(?:UNIQUE|NOT\\s+NULL|AUTO_INCREMENT|PRIMARY\\s+KEY))*))?,?$";
                Matcher matchColumn = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(line);
                if (matchColumn.find()) {
                    String columnName = matchColumn.group(1);
                    String columnType = matchColumn.group(2);
                    String constraints = matchColumn.group(3);

                    currentTable.addColumn(columnName, columnType);
                    logger.info("Colonne ajoutée : " + columnName + " de type " + columnType + " dans la table " + currentTable.getName());

                    if (constraints != null) {
                        if (constraints.toUpperCase().contains("UNIQUE")) {
                            currentTable.addUniqueColumn(columnName);
                            logger.info("Colonne unique : " + columnName);
                        } else if (constraints.toUpperCase().contains("PRIMARY KEY")) {
                            currentTable.setPrimaryKeys(Collections.singletonList(columnName));
                            logger.info("Clé primaire définie : " + columnName);
                        }
                    }
                    continue;
                }

                // Détection de UNIQUE
                Matcher matchUnique = Pattern.compile("UNIQUE\\s*\\(([^)]+)\\)", Pattern.CASE_INSENSITIVE).matcher(line);
                if (matchUnique.find()) {
                    List<String> uniqueColumns = Arrays.asList(matchUnique.group(1).split(","));
                    uniqueColumns.replaceAll(String::trim);
                    for (String col : uniqueColumns) {
                        currentTable.addUniqueColumn(col);
                        logger.info("Colonne unique ajoutée : " + col);
                    }
                    continue;
                }

                // Fin de la définition de table
                if (line.endsWith(");")) {
                    logger.info("Fin de la définition de la table : " + currentTable.getName());
                    currentTable = null;
                }
            }
        }

        return tables;
    }

    public List<Table> sortTablesByDependencies(List<Table> tables) {
        List<Table> sortedTables = new ArrayList<>();
        Map<String, Table> tablesWithDependencies = new HashMap<>();

        for (Table table : tables) {
            if (table.getForeignKeys().isEmpty()) {
                sortedTables.add(table);
                logger.info("Table ajoutée sans dépendances : " + table.getName());
            } else {
                tablesWithDependencies.put(table.getName(), table);
            }
        }

        while (!tablesWithDependencies.isEmpty()) {
            boolean added = false;
            for (Iterator<Map.Entry<String, Table>> it = tablesWithDependencies.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, Table> entry = it.next();
                Table table = entry.getValue();
                if (table.getForeignKeys().stream().allMatch(fk -> sortedTables.stream().anyMatch(t -> t.getName().equals(fk.getReferencedTable())))) {
                    sortedTables.add(table);
                    it.remove();
                    logger.info("Table ajoutée après résolution des dépendances : " + table.getName());
                    added = true;
                }
            }
            if (!added) {
                logger.severe("Cycle de dépendances détecté ou références manquantes.");
                break;
            }
        }

        return sortedTables;
    }
}

