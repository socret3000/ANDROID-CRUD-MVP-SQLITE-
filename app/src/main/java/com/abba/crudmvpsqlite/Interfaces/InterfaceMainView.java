package com.abba.crudmvpsqlite.Interfaces;

import android.os.Bundle;

import com.abba.crudmvpsqlite.Models.Cliente;

import java.util.ArrayList;

public interface InterfaceMainView {

    void goActivityAdd(Class clase, String tipo);

    void goActivityEdit(Class clase, Bundle bundle, String tipo);

    void getAllClient();

    void updateLista();

    void filterClient(ArrayList<Cliente> cliente);



}
