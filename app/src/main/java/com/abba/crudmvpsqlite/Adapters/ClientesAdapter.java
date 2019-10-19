package com.abba.crudmvpsqlite.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abba.crudmvpsqlite.DBHelpers.SqliteStringHelpers;
import com.abba.crudmvpsqlite.Helpers.StringsHelpers;
import com.abba.crudmvpsqlite.Models.Cliente;
import com.abba.crudmvpsqlite.R;
import com.abba.crudmvpsqlite.Views.DetailClient;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.ClientesViewholder> {

    private ArrayList<Cliente> clientes=new ArrayList<>();
    private Activity activity;
    private int layout;

    public ClientesAdapter(ArrayList<Cliente> clientes, Activity activity, int layout) {
        this.clientes = clientes;
        this.activity = activity;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ClientesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new ClientesViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientesViewholder holder, int position) {

            Cliente cliente=clientes.get(position);


            holder.txtName.setText(cliente.getName());
            holder.txtRuc.setText(cliente.getRuc());
            String id=String.valueOf(cliente.getId());
            Log.e("IdCliente",id);

            holder.cardContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent= new Intent(activity, DetailClient.class);

                    intent.putExtra(StringsHelpers.TIPO_ACTION,StringsHelpers.ACTION_EDIT);
                    intent.putExtra(SqliteStringHelpers.CAMPO_NAME,cliente.getName());
                    intent.putExtra(SqliteStringHelpers.CAMPO_RUC,cliente.getRuc());
                    intent.putExtra(SqliteStringHelpers.CAMPO_DIRECCION,cliente.getDireccion());
                    intent.putExtra(SqliteStringHelpers.CAMPO_RAZON,cliente.getRazon());
                    intent.putExtra(SqliteStringHelpers.CAMPO_ID,cliente.getId());

                    activity.startActivity(intent);


                }
            });


    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public class ClientesViewholder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardContent)
        CardView cardContent;

        @BindView(R.id.itmName)
        TextView txtName;
        @BindView(R.id.itmRuc)
        TextView txtRuc;

       // TextView txtName,txtRuc;


        public ClientesViewholder(@NonNull View itemView) {

            super(itemView);

           /* txtName=itemView.findViewById(R.id.itmName);
            txtRuc=itemView.findViewById(R.id.itmRuc);*/

            ButterKnife.bind(this,itemView);

        }
    }

    public void filterList(ArrayList<Cliente> newList)
    {
        clientes = new ArrayList<>();
        clientes.addAll(newList);
        notifyDataSetChanged();

    }

}
