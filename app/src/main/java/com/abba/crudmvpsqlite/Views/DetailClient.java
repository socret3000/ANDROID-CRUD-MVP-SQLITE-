package com.abba.crudmvpsqlite.Views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.abba.crudmvpsqlite.Helpers.StringsHelpers;
import com.abba.crudmvpsqlite.Interfaces.InterfaceDetailView;
import com.abba.crudmvpsqlite.Interfaces.InterfacePresenterDetail;
import com.abba.crudmvpsqlite.Presenters.PresenterDetail;
import com.abba.crudmvpsqlite.R;

public class DetailClient extends AppCompatActivity implements InterfaceDetailView {


    @BindView(R.id.etRuc)
    public EditText txtRuc;
    @BindView(R.id.etName)
    public EditText txtName;
    @BindView(R.id.etDirecc)
    public EditText txtDirecc;
    @BindView(R.id.etRazon)
    public EditText txtRazon;

    @BindView(R.id.toolbarDetailClient)
    public Toolbar toolbar;

    private Menu myMenu;
    MenuItem menuItemEdit,menuItemDone,menuItemDelete,menuItemCancel,menuItemSave;

    String nameToolbar;
    int idClient;

    InterfacePresenterDetail presenterDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_client);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenterDetail=new PresenterDetail(this);


       // setTitleToolbar();


    }

    private void setTitleToolbar(String title) {



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        this.myMenu=menu;
        getMenuInflater().inflate(R.menu.menu_detail_client,menu);



       /* MenuItem menuItemEdit=menu.findItem(R.id.menEdit);
        MenuItem menuItemDone=menu.findItem(R.id.menDone);
        MenuItem menuItemDelete=menu.findItem(R.id.menDelete);
        MenuItem menuItemCancel=menu.findItem(R.id.menCancel);

        menuItemDone.setVisible(false);
        menuItemCancel.setVisible(false);*/


        setItemsMenu();

        showAction();


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menSave:

                insertClient(txtRuc.getText().toString(),txtName.getText().toString(),
                        txtDirecc.getText().toString(),txtRazon.getText().toString());

                return true;

            case R.id.menEdit:

                //Toast.makeText(this, "HoliEdit", Toast.LENGTH_SHORT).show();

                setModeEdit();

                return true;

            case R.id.menDone:

                presenterDetail.updateClient(idClient,txtRuc.getText().toString(),txtName.getText().toString(),
                        txtDirecc.getText().toString(),txtRazon.getText().toString());

                return true;

            case R.id.menCancel:

                finish();

                return true;

            case R.id.menDelete:

                showDialogDelete();

                return true;

            default:

                return super.onOptionsItemSelected(item);

        }


    }

    private void showDialogDelete() {


        AlertDialog dialog= new AlertDialog.Builder(this).create();

        dialog.setTitle("Eliminar cliente?");
        dialog.setCancelable(true);
        dialog.setMessage("Desea eliminar a este cliente?");
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                presenterDetail.deleteClient(idClient,txtName.getText().toString());

            }
        });

        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialog.hide();

            }
        });

        dialog.show();

    }

    private void setItemsMenu() {



        menuItemEdit=myMenu.findItem(R.id.menEdit);
        menuItemDone=myMenu.findItem(R.id.menDone);
        menuItemDelete=myMenu.findItem(R.id.menDelete);
        menuItemCancel=myMenu.findItem(R.id.menCancel);
        menuItemSave=myMenu.findItem(R.id.menSave);

    }


    @Override
    public void setModeDetail(int id,String name,String ruc,String direcc, String razon) {



        menuItemDone.setVisible(false);
        menuItemCancel.setVisible(false);
        menuItemEdit.setVisible(true);
        menuItemDelete.setVisible(true);
        menuItemSave.setVisible(false);

        this.nameToolbar=name;
        idClient=id;
        getSupportActionBar().setTitle(name);

        txtName.setText(name);
        txtRuc.setText(ruc);
        txtDirecc.setText(direcc);
        txtRazon.setText(razon);


        modeAction(false,R.drawable.border_edittext_block);


    }

    @Override
    public void setModeEdit() {


        menuItemDone.setVisible(true);
        menuItemCancel.setVisible(true);
        menuItemEdit.setVisible(false);
        menuItemDelete.setVisible(false);
        menuItemSave.setVisible(false);

       modeAction(true,R.drawable.border_edittext);



    }

    @Override
    public void saveData() {




    }

    @Override
    public void cancelEdit() {

       // setModeDetail(nameToolbar);


    }

    @Override
    public void setAddClient() {

        menuItemDone.setVisible(false);
        menuItemCancel.setVisible(false);
        menuItemEdit.setVisible(false);
        menuItemDelete.setVisible(false);
        menuItemSave.setVisible(true);

        setTitleToolbar(StringsHelpers.TITLE_ADD_CLIENT);


    }

    @Override
    public void showAction() {

        Bundle bundle=getIntent().getExtras();
        String tipo=bundle.getString(StringsHelpers.TIPO_ACTION);
        presenterDetail.showAction(tipo,bundle);

    }

    @Override
    public void insertClient(String ruc, String name, String direccion, String razon) {

        Bundle bundle=getIntent().getExtras();
        String tipo=bundle.getString(StringsHelpers.TIPO_ACTION);
        presenterDetail.insertClient(tipo,ruc,name,direccion,razon);


    }

    @Override
    public void showMessage(String mensaje) {

        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void goActivityAdd(Class clase) {

        startActivity(new Intent(this,clase));

    }

    @Override
    public void showErrorRuc(String message) {

        txtRuc.setError(message);

    }

    @Override
    public void showErrorName(String message) {

        txtName.setError(message);

    }

    public void modeAction(Boolean enable, int background)
    {

        txtName.setEnabled(enable);
        txtRuc.setEnabled(enable);
        txtDirecc.setEnabled(enable);
        txtRazon.setEnabled(enable);


        txtName.setBackgroundResource(background);
        txtRuc.setBackgroundResource(background);
        txtDirecc.setBackgroundResource(background);
        txtRazon.setBackgroundResource(background);

    }


}
