package uniminuto.proveedores.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import uniminuto.proveedores.DAO.ConexionBaseDeDatos;
import uniminuto.proveedores.modelos.Proveedor;


public class ProveedorController {
    private final ConexionBaseDeDatos conexionBaseDeDatos;
    private final String NOMBRE_TABLA = "agenda";

    public ProveedorController(Context contexto) {
        conexionBaseDeDatos = new ConexionBaseDeDatos(contexto);
    }

    public int eliminarProveedor(Proveedor mascota) {

        SQLiteDatabase baseDeDatos = conexionBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(mascota.getId())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }

    public long nuevoProveedor(Proveedor proveedor) {
        // writable porque vamos a insertar
        SQLiteDatabase baseDeDatos = conexionBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombreproveedor", proveedor.getNombreProveedor());
        valoresParaInsertar.put("numero", proveedor.getNumeroTelefono());
        valoresParaInsertar.put("email", proveedor.getEmail());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int guardarCambios(Proveedor proveedor) {
        SQLiteDatabase baseDeDatos = conexionBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombreproveedor", proveedor.getNombreProveedor());
        valoresParaActualizar.put("numero", proveedor.getNumeroTelefono());
        valoresParaActualizar.put("email", proveedor.getEmail());
        // where id...
        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(proveedor.getId())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<Proveedor> obtenerProveedores() {
        ArrayList<Proveedor> proveedors = new ArrayList<>();
        SQLiteDatabase baseDeDatos = conexionBaseDeDatos.getReadableDatabase();

        String[] columnasAConsultar = { "email", "nombreproveedor", "numero", "id"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null || !cursor.moveToFirst()) {
            // Si ocurre un error o no existen datos
            return proveedors;
        }

        do {
            // El 0 es el número de la columna, como seleccionamos
            // nombre, edad,id entonces el nombre es 0, edad 1 e id es 2
            String nombreObtenidoDeBD = cursor.getString(1);
            int numeroObtenidaDeBD = cursor.getInt(2);
            String emailObtenidoDeBD = cursor.getString(0);
            long idProveedor = cursor.getLong(3);
            Proveedor proveedorBD = new Proveedor(nombreObtenidoDeBD, emailObtenidoDeBD, numeroObtenidaDeBD, idProveedor);
            proveedors.add(proveedorBD);
        } while (cursor.moveToNext());

        for (Proveedor p: proveedors) {
            System.out.println("Nombre: " + p.getNombreProveedor()
                    + " telefono: " + p.getNumeroTelefono()
                    + " email: " + p.getEmail());
        }

        cursor.close();
        return proveedors;
    }
}