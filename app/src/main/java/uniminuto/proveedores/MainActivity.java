package uniminuto.proveedores;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import uniminuto.proveedores.controllers.AdaptadorProveedor;
import uniminuto.proveedores.controllers.ProveedorController;
import uniminuto.proveedores.controllers.RecyclerTouchListener;
import uniminuto.proveedores.modelos.Proveedor;
import uniminuto.proveedores.vista.AgregarProveedorActivity;
import uniminuto.proveedores.vista.EditarProveedorActivity;

public class MainActivity extends AppCompatActivity {
    private List<Proveedor> listaProveedores;
    private RecyclerView recyclerView;
    private AdaptadorProveedor adaptadorProveedor;
    private ProveedorController proveedorController;
    private FloatingActionButton fabAgregarProveedor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proveedorController = new ProveedorController(MainActivity.this);

        // Instanciar vistas
        recyclerView = findViewById(R.id.recyclerViewMascotas);
        fabAgregarProveedor = findViewById(R.id.fabAgregarMascota);

        listaProveedores = new ArrayList<>();
        adaptadorProveedor = new AdaptadorProveedor(listaProveedores);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorProveedor);

        refrescarListaProveedores();

        // Listener de los clicks en la lista, o sea el RecyclerView
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override // Un toque sencillo
            public void onClick(View view, int position) {
                Proveedor proveedor = listaProveedores.get(position);
                Intent intent = new Intent(MainActivity.this, EditarProveedorActivity.class);
                intent.putExtra("id", proveedor.getId());
                intent.putExtra("nombreProveedor", proveedor.getNombreProveedor());
                intent.putExtra("numero", proveedor.getNumeroTelefono());
                intent.putExtra("email", proveedor.getEmail());
                startActivity(intent);
            }

            @Override // Un toque largo
            public void onLongClick(View view, int position) {
                final Proveedor proveedroAEliminar = listaProveedores.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(MainActivity.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                proveedorController.eliminarProveedor(proveedroAEliminar);
                                refrescarListaProveedores();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar el proveedor " + proveedroAEliminar.getNombreProveedor() + "?")
                        .create();
                dialog.show();

            }
        }));

        fabAgregarProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgregarProveedorActivity.class);
                startActivity(intent);
            }
        });

        fabAgregarProveedor.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Integrantes del Grupo 1")
                        .setMessage("Mairon Tobias Sandoval Becerra\nJosé Ulices Vargas Castro\nCristian Camilo Agudelo Calderón" +
                                "\nDidier Aleisso Gutierrez Lozano\nJorge Luis Otalvaro Contreras")
                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogo, int which) {
                                dialogo.dismiss();
                            }
                        }).show();
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        refrescarListaProveedores();
    }

    public void refrescarListaProveedores() {
        if (adaptadorProveedor == null) return;
        listaProveedores = proveedorController.obtenerProveedores();
        adaptadorProveedor.setListaProveedores(listaProveedores);
        adaptadorProveedor.notifyDataSetChanged();
    }
}
