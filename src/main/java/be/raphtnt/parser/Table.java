package be.raphtnt.parser;

import java.util.*;

/**
 * Represents a database table with information about columns, primary keys, unique columns, and foreign key constraints.
 */
public class Table {
    /**
     * Represents the name of the table.
     */
    private final String name;
    /**
     * Represents the list of columns in a table.
     */
    private final List<String> columns = new ArrayList<>();
    /**
     * Represents the list of primary key columns in a table.
     * Primary keys uniquely identify each record in a table.
     * The primary keys are stored as a List of String values in the order they are defined.
     */
    private final List<String> primaryKeys = new ArrayList<>();
    /**
     * Represents a list of unique columns in a database table.
     */
    private final List<String> uniqueColumns = new ArrayList<>();
    /**
     * Represents a list of foreign key constraints that are associated with a table.
     * This list stores instances of the ForeignKey class which represent mapping of
     * columns in the current table to columns in other tables.
     */
    private final List<ForeignKey> foreignKeys = new ArrayList<>();

    /**
     * Constructs a new Table object with the specified name.
     *
     * @param name the name of the table
     */
    public Table(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the table.
     *
     * @return A String representing the name of the table.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the list of column names in the table.
     *
     * @return A List<String> containing the column names in the table.
     */
    public List<String> getColumns() {
        return columns;
    }

    /**
     * Retrieves the primary keys of the table.
     *
     * @return A List of String representing the primary keys of the table.
     */
    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    /**
     * Retrieves a list of unique columns in the table.
     *
     * @return A List containing the names of unique columns in the table.
     */
    public List<String> getUniqueColumns() {
        return uniqueColumns;
    }

    /**
     * Retrieves a list of foreign keys associated with this object.
     *
     * @return a List of ForeignKey objects representing the foreign keys
     */
    public List<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    /**
     * Adds a new column to the table with the specified column name and column type.
     *
     * @param columnName The name of the column to be added
     * @param columnType The data type of the column to be added
     */
    public void addColumn(String columnName, String columnType) {
//        columns.add(columnName + " " + columnType);
        columns.add(columnName);
    }

    /**
     * Sets the primary keys of the table to the specified list of keys.
     *
     * @param keys A list of strings representing the primary key columns to be set
     */
    public void setPrimaryKeys(List<String> keys) {
        primaryKeys.clear();
        primaryKeys.addAll(keys);
    }

    /**
     * Adds a column to the list of unique columns in the table.
     *
     * @param columnName The name of the column to be marked as unique
     */
    public void addUniqueColumn(String columnName) {
        uniqueColumns.add(columnName);
    }

    /**
     * Adds a foreign key constraint to the table. The foreign key maps a column in this table to a column in another table.
     *
     * @param columnName the name of the column in this table that is part of the foreign key
     * @param referencedTable the name of the table being referenced
     * @param referencedColumn the name of the column in the referenced table that is being used as a foreign key
     */
    public void addForeignKey(String columnName, String referencedTable, String referencedColumn) {
        foreignKeys.add(new ForeignKey(columnName, referencedTable, referencedColumn));
    }
}