package com.abba.crudmvpsqlite.Interfaces;

import com.abba.crudmvpsqlite.Models.Cliente;

import java.util.ArrayList;

public interface InterfaceModelCliente {

    void insertClient(String ruc,String nombre, String direccion, String razon);

    ArrayList<Cliente> getAllClient();

    void updateClient(String id,String ruc,String nombre, String direccion, String razon);

    void deleteClient(String id,String name);

}
