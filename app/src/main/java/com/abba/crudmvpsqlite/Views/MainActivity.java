package com.abba.crudmvpsqlite.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.abba.crudmvpsqlite.Adapters.ClientesAdapter;
import com.abba.crudmvpsqlite.Helpers.StringsHelpers;
import com.abba.crudmvpsqlite.Interfaces.InterfaceMainView;
import com.abba.crudmvpsqlite.Interfaces.InterfacePresenterMain;
import com.abba.crudmvpsqlite.Models.Cliente;
import com.abba.crudmvpsqlite.Presenters.PresenterMain;
import com.abba.crudmvpsqlite.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.widget.GridLayout.HORIZONTAL;

public class MainActivity extends AppCompatActivity implements InterfaceMainView {

    @BindView(R.id.floBtnAdd)
    public FloatingActionButton btnAdd;

    @BindView(R.id.toolbarMainActivity)
    Toolbar toolbar;

    @BindView(R.id.recyClientes)
    RecyclerView recyclerView;

    ClientesAdapter adapter;
    ArrayList<Cliente> clientes=new ArrayList<>();

    InterfacePresenterMain presenterMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(StringsHelpers.PRINCIPAL_TOOLBAR);

        adapter=new ClientesAdapter(clientes,this,R.layout.row_data_client);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.addItemDecoration(new
                DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        presenterMain=new PresenterMain(this);

        llenarData();




    }

    private void llenarData() {

        getAllClient();
    }

    @OnClick(R.id.floBtnAdd)
    public void agregarUser()
    {
        goActivityAdd(DetailClient.class,StringsHelpers.ACTION_ADD);
        //getAllClient();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main_activity, menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView search = (SearchView) menu.findItem(R.id.menSearch).getActionView();

        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                ArrayList<Cliente> client=presenterMain.filterClient(newText,clientes);

                adapter.filterList(client);

                return true;

            }

        });

        return true;
    }

    @Override
    public void goActivityAdd(Class clase,String tipo) {

        Intent intent=new Intent(MainActivity.this,DetailClient.class);

        intent.putExtra(StringsHelpers.TIPO_ACTION,tipo);

        startActivity(intent);


    }

    @Override
    public void goActivityEdit(Class clase, Bundle bundle,String tipo) {

    }

    @Override
    public void getAllClient() {


        try {

            ArrayList<Cliente> client=presenterMain.getAllClient();

            if(client!=null)
            {
                clientes.addAll(client);

                adapter.notifyDataSetChanged();
            }



        }catch (Exception ex){

        }



    }

    @Override
    public void updateLista() {

        adapter.notifyDataSetChanged();


    }

    @Override
    public void filterClient(ArrayList<Cliente> cliente) {
		
		


    }
}
