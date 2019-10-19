package com.abba.crudmvpsqlite.Interfaces;

public interface InterfaceDetailView {

    void setModeDetail(int idClient, String nameClient,String ruc, String direcc, String razon);

    void setModeEdit();

    void saveData();

    void cancelEdit();

    void setAddClient();

   void showAction();

    void insertClient(String ruc,String nombre, String direccion, String razon);

    void showMessage(String mensaje);

    void goActivityAdd(Class clase);

    void showErrorRuc(String message);

    void showErrorName(String message);





}
