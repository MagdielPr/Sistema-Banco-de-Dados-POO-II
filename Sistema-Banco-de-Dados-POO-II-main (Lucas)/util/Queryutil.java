package util;

public class Queryutil {
// cria os comandos que vão para o workbench, preciso ajustar ainda 
	
    public static String createDatabaseQuery(String databaseName) {
        return "CREATE DATABASE " + databaseName;
    }

    public static String dropDatabaseQuery(String databaseName) {
        return "DROP DATABASE " + databaseName;
    }

    public static String createTableQuery(String tableName, String columnsDefinition) {
        return "CREATE TABLE " + tableName + " (" + columnsDefinition + ")";
    }

    public static String dropTableQuery(String tableName) {
        return "DROP TABLE " + tableName;
    }

    public static String addColumnQuery(String tableName, String columnName, String columnType) {
        return "ALTER TABLE " + tableName + " ADD " + columnName + " " + columnType;
    }

    public static String dropColumnQuery(String tableName, String columnName) {
        return "ALTER TABLE " + tableName + " DROP COLUMN " + columnName;
    }

    public static String listTablesQuery(String databaseName) {
        return "SHOW TABLES FROM " + databaseName;
    }

    public static String listColumnsQuery(String tableName) {
        return "SHOW COLUMNS FROM " + tableName;
    }
}
