package com.abba.crudmvpsqlite.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.abba.crudmvpsqlite.DBHelpers.ConnectionSqliteHelper;
import com.abba.crudmvpsqlite.DBHelpers.SqliteStringHelpers;
import com.abba.crudmvpsqlite.Interfaces.InterfaceDetailView;
import com.abba.crudmvpsqlite.Interfaces.InterfaceMainView;
import com.abba.crudmvpsqlite.Interfaces.InterfaceModelCliente;
import com.abba.crudmvpsqlite.Interfaces.InterfacePresenterDetail;
import com.abba.crudmvpsqlite.Interfaces.InterfacePresenterMain;
import com.abba.crudmvpsqlite.Views.MainActivity;

import java.util.ArrayList;

public class Cliente implements InterfaceModelCliente {

    int ID;
    String ruc;
    String name;
    String direccion;
    String razon;

    InterfacePresenterDetail presenterDetail;
    InterfaceDetailView detailView;
    InterfacePresenterMain presenterMain;
    InterfaceMainView viewMain;


    public Cliente() {



    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public Cliente(InterfacePresenterMain presenterMain, InterfaceMainView interfaceMainView)
    {
        this.presenterMain=presenterMain;
        this.viewMain=interfaceMainView;

    }



    public Cliente(InterfacePresenterDetail presenterDetail, InterfaceDetailView detailView)
    {
        this.presenterDetail=presenterDetail;
        this.detailView=detailView;


    }

    public Cliente(int id,String ruc, String name, String direccion, String razon) {
        this.ID=id;
        this.ruc = ruc;
        this.name = name;
        this.direccion = direccion;
        this.razon = razon;
    }

    public Cliente(String ruc, String name, String direccion, String razon) {
        this.ruc = ruc;
        this.name = name;
        this.direccion = direccion;
        this.razon = razon;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }




    @Override
    public void insertClient(String ruc, String nombre, String direccion, String razon) {

        try
        {
            ConnectionSqliteHelper conn=new ConnectionSqliteHelper((Context)detailView,"bd_empresa",null,1);

            SQLiteDatabase db=conn.getWritableDatabase();

            ContentValues values=new ContentValues();
            values.put(SqliteStringHelpers.CAMPO_RUC,ruc);
            values.put(SqliteStringHelpers.CAMPO_NAME,nombre);
            values.put(SqliteStringHelpers.CAMPO_DIRECCION,direccion);
            values.put(SqliteStringHelpers.CAMPO_RAZON,razon);

            long result= db.insert(SqliteStringHelpers.TABLA_CLIENTE,null,values);

            String message;

            if(result==-1)
            {
                message="Error insert";
            }
            else
            {
                message="Successful insert";
            }

            db.close();
            conn.close();

            presenterDetail.showMessage(message);
            presenterDetail.goActivityAdd(MainActivity.class);

        }catch (Exception ex)
        {
            presenterDetail.showMessage(ex.getMessage());
            presenterDetail.goActivityAdd(MainActivity.class);

        }


    }

    @Override
    public ArrayList<Cliente> getAllClient() {

        try
        {
            ConnectionSqliteHelper conn=new ConnectionSqliteHelper((Context)viewMain,"bd_empresa",null,1);
            SQLiteDatabase db=conn.getReadableDatabase();

            Cursor cursor=db.rawQuery(SqliteStringHelpers.SELECT_ALL_CLIENT,null);

            if(cursor.getCount()==0)
            {
                return null;
            }
            else
            {
                ArrayList<Cliente> clientes=new ArrayList<>();
                // cursor.moveToFirst();

                while(cursor.moveToNext())
                {
                    Cliente clien=new Cliente();
                    clien.setId(cursor.getInt(0));
                    clien.setName(cursor.getString(1));
                    clien.setRuc(cursor.getString(2));
                    clien.setDireccion(cursor.getString(3));
                    clien.setRazon(cursor.getString(4));

                    clientes.add(clien);

                }

                return clientes;
            }



        }catch (Exception ex){

            return  null;

        }




    }

    @Override
    public void updateClient(String id, String ruc, String nombre, String direccion, String razon) {

       try
       {

           ConnectionSqliteHelper conn=new ConnectionSqliteHelper((Context)detailView,"bd_empresa",null,1);
           SQLiteDatabase db=conn.getWritableDatabase();

           String[] myId={id};
           ContentValues values=new ContentValues();
           values.put(SqliteStringHelpers.CAMPO_RUC,ruc);
           values.put(SqliteStringHelpers.CAMPO_NAME,nombre);
           values.put(SqliteStringHelpers.CAMPO_DIRECCION,direccion);
           values.put(SqliteStringHelpers.CAMPO_RAZON,razon);

           db.update(SqliteStringHelpers.TABLA_CLIENTE,values,"id=?",myId);

           db.close();
           conn.close();

           presenterDetail.showMessage("Successful update");

           presenterDetail.goActivityAdd(MainActivity.class);


       }catch (Exception ex){

           presenterDetail.showMessage(ex.getMessage());

       }

    }

    @Override
    public void deleteClient(String id, String name) {

        ConnectionSqliteHelper conn=new ConnectionSqliteHelper((Context)detailView,"bd_empresa",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        String[] myId={id};

        db.delete(SqliteStringHelpers.TABLA_CLIENTE,"id=?",myId);

        db.close();
        conn.close();

        presenterDetail.showMessage("Deleted customer: "+ name);

        presenterDetail.goActivityAdd(MainActivity.class);


    }
}
