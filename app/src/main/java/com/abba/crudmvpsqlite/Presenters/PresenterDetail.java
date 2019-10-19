package com.abba.crudmvpsqlite.Presenters;

import android.os.Bundle;

import com.abba.crudmvpsqlite.DBHelpers.SqliteStringHelpers;
import com.abba.crudmvpsqlite.Helpers.StringsHelpers;
import com.abba.crudmvpsqlite.Interfaces.InterfaceDetailView;
import com.abba.crudmvpsqlite.Interfaces.InterfaceModelCliente;
import com.abba.crudmvpsqlite.Interfaces.InterfacePresenterDetail;
import com.abba.crudmvpsqlite.Models.Cliente;

import java.util.ArrayList;

public class PresenterDetail implements InterfacePresenterDetail {


    InterfaceDetailView detailView;
    InterfaceModelCliente modelCliente;

    public PresenterDetail(InterfaceDetailView detailView) {
        this.detailView = detailView;
        this.modelCliente=new Cliente(this,detailView);

    }

    @Override
    public void showAction(String tipo, Bundle bundle) {

        if(detailView!=null)
        {
            if(tipo.equals(StringsHelpers.ACTION_ADD))
            {
                detailView.setAddClient();
            }
            else
            {
                String nameClient=bundle.getString(SqliteStringHelpers.CAMPO_NAME);
                String ruc= bundle.getString(SqliteStringHelpers.CAMPO_RUC);
                String direcc= bundle.getString(SqliteStringHelpers.CAMPO_DIRECCION);
                String razon= bundle.getString(SqliteStringHelpers.CAMPO_RAZON);
                int idClient=bundle.getInt(SqliteStringHelpers.CAMPO_ID);

                detailView.setModeDetail(idClient, nameClient,ruc,direcc,razon);

            }
        }

    }

    @Override
    public void insertClient(String tipo,String ruc, String nombre, String direccion, String razon) {

        if(detailView!=null)
        {
            if(tipo.equals(StringsHelpers.ACTION_ADD)) {
                // modelCliente.insertClient(ruc,nombre,direccion,razon);

                // detailView.showMessage("Insertar cliente");

                if (ruc.trim().equals(""))
                {
                    showErrorRuc();
                }
                else
                {
                    if(!nombre.trim().equals(""))
                    {
                        modelCliente.insertClient(ruc,nombre,direccion,razon);
                    }
                    else
                    {
                        showErrorName();
                    }
                }



            }
            else
            {
                //detailView.setModeDetail();
            }


        }


    }

    @Override
    public void showMessage(String message) {

        if(detailView!=null)
        {
            detailView.showMessage(message);
        }



    }

    @Override
    public void updateClient(int id, String ruc, String name, String direcc, String razon) {

        if(detailView!=null)
        {
            String idCl=String.valueOf(id);
            modelCliente.updateClient(idCl,ruc,name,direcc,razon);
        }

    }

    @Override
    public void goActivityAdd(Class clase) {


        detailView.goActivityAdd(clase);

    }

    @Override
    public void deleteClient(int id, String name) {

        if(detailView!=null)
        {
            String idCli=String.valueOf(id);
            modelCliente.deleteClient(idCli,name);
        }


    }

    @Override
    public void showErrorRuc() {

        detailView.showErrorRuc("Insert RUC number");

    }

    @Override
    public void showErrorName() {

        detailView.showErrorName("Insert name customer");

    }


}
