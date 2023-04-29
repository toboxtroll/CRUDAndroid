package uniminuto.proveedores.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uniminuto.proveedores.R;
import uniminuto.proveedores.controllers.ProveedorController;
import uniminuto.proveedores.modelos.Proveedor;

//import me.parzibyte.crudsqlite.R;

public class EditarProveedorActivity extends AppCompatActivity {
    private EditText etEditarNombre, etEditarNumero, etEmail;
    private Button btnGuardarCambios, btnCancelarEdicion;
    private Proveedor proveedor;
    private ProveedorController proveedorController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        // Recuperar datos que enviaron
        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            finish();
            return;
        }
        proveedorController = new ProveedorController(EditarProveedorActivity.this);

        long id = extras.getLong("id");
        String nombreProveedor = extras.getString("nombreProveedor");
        int numero = extras.getInt("numero");
        String email = extras.getString("email");
        proveedor = new Proveedor(nombreProveedor, email, numero, id);

        etEditarNumero = findViewById(R.id.etEditarNumero);
        etEditarNombre = findViewById(R.id.etEditarNombre);
        etEmail = findViewById(R.id.etEditarEmail);
        btnCancelarEdicion = findViewById(R.id.btnCancelarEdicionMascota);
        btnGuardarCambios = findViewById(R.id.btnGuardarCambiosMascota);

        etEditarNumero.setText(String.valueOf(proveedor.getNumeroTelefono()));
        etEditarNombre.setText(proveedor.getNombreProveedor());
        etEmail.setText(proveedor.getEmail());

        // Listener del click del botón para salir, simplemente cierra la actividad
        btnCancelarEdicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Listener del click del botón que guarda cambios
        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remover previos errores si existen
                etEditarNombre.setError(null);
                etEditarNumero.setError(null);
                etEmail.setError(null);

                String nuevoNombre = etEditarNombre.getText().toString();
                String nuevoNumeroString = etEditarNumero.getText().toString();
                String nuevoEmail = etEmail.getText().toString();

                if (nuevoNombre.isEmpty()) {
                    etEditarNombre.setError("Escribe el nombre del proveedor");
                    etEditarNombre.requestFocus();
                    return;
                }
                if (nuevoNumeroString.isEmpty()) {
                    etEditarNumero.setError("Escribe el número de teléfono");
                    etEditarNumero.requestFocus();
                    return;
                }

                if (nuevoEmail.isEmpty()) {
                    etEmail.setError("Escribe el email");
                    etEmail.requestFocus();
                    return;
                }

                int nuevaNumero;
                nuevaNumero = Integer.parseInt(nuevoNumeroString);
                Toast.makeText(EditarProveedorActivity.this, "numero "+nuevaNumero, Toast.LENGTH_SHORT).show();
                try {
                    nuevaNumero = Integer.parseInt(nuevoNumeroString);
                } catch (NumberFormatException e) {
                    etEditarNumero.setError("Escribe un número valido");
                    etEditarNumero.requestFocus();
                    return;
                }

                Proveedor proveedorEditado = new Proveedor(nuevoNombre, nuevoEmail, nuevaNumero, proveedor.getId());
                int filasModificadas = proveedorController.guardarCambios(proveedorEditado);

                if (filasModificadas != 1) {
                    Toast.makeText(EditarProveedorActivity.this, "Error guardando cambios. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                } else {

                    finish();
                }
            }
        });
    }
}
