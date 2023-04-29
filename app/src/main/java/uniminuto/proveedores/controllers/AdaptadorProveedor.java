package uniminuto.proveedores.controllers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import uniminuto.proveedores.R;
import uniminuto.proveedores.modelos.Proveedor;


public class AdaptadorProveedor extends RecyclerView.Adapter<AdaptadorProveedor.MyViewHolder> {

    private List<Proveedor> listaProveedores;

    public void setListaProveedores(List<Proveedor> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public AdaptadorProveedor(List<Proveedor> provedores) {
        this.listaProveedores = provedores;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View filaProveedor = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fila_proveedor, viewGroup, false);
        return new MyViewHolder(filaProveedor);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Proveedor proveedor = listaProveedores.get(i);

        // Obtener los datos de la lista
        String nombreProveedor = proveedor.getNombreProveedor();
        long numeroTelefono = proveedor.getNumeroTelefono();
        String email = proveedor.getEmail();

        myViewHolder.nombre.setText(nombreProveedor);
        myViewHolder.numero.setText(String.valueOf(numeroTelefono));
        myViewHolder.email.setText(email);
    }

    @Override
    public int getItemCount() {
        return listaProveedores.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, numero, email;

        MyViewHolder(View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.tvNombre);
            this.numero = itemView.findViewById(R.id.tvNumero);
            this.email = itemView.findViewById(R.id.tvEmail);
        }
    }
}
