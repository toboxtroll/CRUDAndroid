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

public class AgregarProveedorActivity extends AppCompatActivity {
    private Button btnAgregar, btnCancelar;
    private EditText etNombre, etNumero, etEmail;
    private ProveedorController proveedorController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        // Instanciar vistas
        etNombre = findViewById(R.id.etNombre);
        etNumero = findViewById(R.id.etNumero);
        etEmail = findViewById(R.id.etEmail);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnCancelar = findViewById(R.id.btnCancelar);
        // Crear el controlador
        proveedorController = new ProveedorController(AgregarProveedorActivity.this);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resetear errores a ambos
                etNombre.setError(null);
                etNumero.setError(null);
                etEmail.setError(null);

                String nombre = etNombre.getText().toString(),
                        numeroString = etNumero.getText().toString(),
                        email = etEmail.getText().toString();

//                System.out.println("Nombre: " +nombre
//                        + " telefono: " + numeroString
//                        + " email: " + email);

                if ("".equals(nombre)) {
                    etNombre.setError("Escribe el nombre del proveedor");
                    etNombre.requestFocus();
                    return;
                }
                if ("".equals(numeroString)) {
                    etNumero.setError("Escribe el número de teléfono");
                    etNumero.requestFocus();
                    return;
                }
                if ("".equals(email)) {
                    etEmail.setError("Escribe el email");
                    etEmail.requestFocus();
                    return;
                }

                // Ver si es un entero
                int numero;
                try {
                     numero = Integer.parseInt(etNumero.getText().toString());
                } catch (NumberFormatException e) {
                    etNumero.setError("Escribe un número");
                    etNumero.requestFocus();
                    return;
                }

                Proveedor nuevoProveedor = new Proveedor(nombre, email, numero);
                long id = proveedorController.nuevoProveedor(nuevoProveedor);
                if (id == -1) {
                    Toast.makeText(AgregarProveedorActivity.this, "Error al guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
                } else {

                    finish();
                }
            }
        });

        // El de cancelar simplemente cierra la actividad
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
