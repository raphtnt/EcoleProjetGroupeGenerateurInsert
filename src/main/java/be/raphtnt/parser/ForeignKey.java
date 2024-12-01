package be.raphtnt.parser;

/**
 * Represents a foreign key constraint that maps a column in a table to a column in another table.
 */
public class ForeignKey {
    /**
     * Represents the name of the column involved in a foreign key constraint.
     */
    private final String columnName;
    /**
     * The name of the table that is being referenced by this foreign key.
     */
    private final String referencedTable;
    /**
     * This variable represents the name of the column in another table that is being referenced by this foreign key.
     */
    private final String referencedColumn;

    /**
     * Represents a foreign key that maps a column in a table to a column in another table.
     *
     * @param columnName the name of the column in this table that is part of the foreign key
     * @param referencedTable the name of the table being referenced
     * @param referencedColumn the name of the column in the referenced table that is being used as a foreign key
     */
    public ForeignKey(String columnName, String referencedTable, String referencedColumn) {
        this.columnName = columnName;
        this.referencedTable = referencedTable;
        this.referencedColumn = referencedColumn;
    }

    /**
     * Retrieves the name of the column associated with this foreign key.
     *
     * @return the name of the column
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Retrieves the name of the referenced table in the foreign key constraint.
     *
     * @return A String representing the name of the referenced table
     */
    public String getReferencedTable() {
        return referencedTable;
    }

    /**
     * Get the referenced column of the foreign key.
     *
     * @return The referenced column of the foreign key.
     */
    public String getReferencedColumn() {
        return referencedColumn;
    }
}