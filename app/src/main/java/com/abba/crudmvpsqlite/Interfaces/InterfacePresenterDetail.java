package com.abba.crudmvpsqlite.Interfaces;

import android.os.Bundle;

import com.abba.crudmvpsqlite.Models.Cliente;

import java.util.ArrayList;

public interface InterfacePresenterDetail {

    void showAction(String tipo, Bundle bundle);

    void insertClient(String tipo,String ruc,String name, String direccion, String razon);

    void showMessage(String message);

    void updateClient(int id, String ruc,String name, String direcc, String razon);

    void goActivityAdd(Class clase);

    void deleteClient(int id, String name);

    void showErrorRuc();

    void showErrorName();



}
