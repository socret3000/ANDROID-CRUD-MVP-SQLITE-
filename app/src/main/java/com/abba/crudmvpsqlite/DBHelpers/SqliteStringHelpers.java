package com.abba.crudmvpsqlite.DBHelpers;

public class SqliteStringHelpers {

    public static final String CREATE_TABLE_CLIENTE="CREATE TABLE " +
            "cliente(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,name TEXT,ruc TEXT,direccion TEXT,razon TEXT)";

    public static final String CAMPO_ID="id";
    public static final String CAMPO_NAME="name";
    public static final String CAMPO_RUC="ruc";
    public static final String CAMPO_DIRECCION="direccion";
    public static final String CAMPO_RAZON="razon";
    public static final String TABLA_CLIENTE="cliente";


    public static final String SELECT_ALL_CLIENT="SELECT * FROM cliente";

}
