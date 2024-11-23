package parser;

public class ForeignKey {
    private final String columnName;
    private final String referencedTable;
    private final String referencedColumn;

    public ForeignKey(String columnName, String referencedTable, String referencedColumn) {
        this.columnName = columnName;
        this.referencedTable = referencedTable;
        this.referencedColumn = referencedColumn;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getReferencedTable() {
        return referencedTable;
    }

    public String getReferencedColumn() {
        return referencedColumn;
    }
}