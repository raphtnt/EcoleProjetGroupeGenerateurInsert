package parser;

import java.util.*;

public class Table {
    private final String name;
    private final List<String> columns = new ArrayList<>();
    private final List<String> primaryKeys = new ArrayList<>();
    private final List<String> uniqueColumns = new ArrayList<>();
    private final List<ForeignKey> foreignKeys = new ArrayList<>();

    public Table(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> getColumns() {
        return columns;
    }

    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public List<String> getUniqueColumns() {
        return uniqueColumns;
    }

    public List<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    public void addColumn(String columnName, String columnType) {
        columns.add(columnName + " " + columnType);
    }

    public void setPrimaryKeys(List<String> keys) {
        primaryKeys.clear();
        primaryKeys.addAll(keys);
    }

    public void addUniqueColumn(String columnName) {
        uniqueColumns.add(columnName);
    }

    public void addForeignKey(String columnName, String referencedTable, String referencedColumn) {
        foreignKeys.add(new ForeignKey(columnName, referencedTable, referencedColumn));
    }
}

