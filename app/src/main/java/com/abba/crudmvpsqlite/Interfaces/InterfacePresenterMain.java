package com.abba.crudmvpsqlite.Interfaces;

import com.abba.crudmvpsqlite.Models.Cliente;

import java.util.ArrayList;

public interface InterfacePresenterMain {

    ArrayList<Cliente> getAllClient();

    void updateLista();

    ArrayList<Cliente> filterClient(String text, ArrayList<Cliente> oldList);


}
