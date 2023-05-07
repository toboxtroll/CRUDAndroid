package uniminuto.proveedores.test;

import static org.junit.Assert.*;

import android.app.Instrumentation;
import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import uniminuto.proveedores.controllers.ProveedorController;
import uniminuto.proveedores.modelos.Proveedor;
import uniminuto.proveedores.vista.EditarProveedorActivity;

//import androidx.test.platform.app.InstrumentationRegistry;
public class ProveedorControllerTest {
    private ProveedorController proveedorController;
    private Proveedor proveedor;
    public Context contexto;

    @Before
    public void setUp() throws Exception {
        proveedorController = new ProveedorController(contexto);
        proveedor = new Proveedor("Equipo 1","equipo1@uniminuto.edu.co",123456789);

    }

    @Test
    public void nuevoProveedor() {
        long idProveedorTest = proveedorController.nuevoProveedor(proveedor);

        ArrayList<Proveedor> proveedores = new ArrayList<>();
        
        proveedores = proveedorController.obtenerProveedores();

        for (Proveedor indexProveedor:proveedores) {
            if (indexProveedor.getId() == idProveedorTest){
                assertEquals("Equipo 1",indexProveedor.getNombreProveedor());
                assertEquals("equipo1@uniminuto.edu.co",indexProveedor.getEmail());
                assertEquals(123456789,indexProveedor.getNumeroTelefono());
            }
        }
    }

    @Test
    public void editarProveedor() {
        Proveedor proveedorUpdateTest = new Proveedor("Equipo update 1",
                                                        "equipo1@uniminuto.edu.co",
                                                    987654321);

        long idProveedorTest = proveedorController.guardarCambios(proveedorUpdateTest);

        ArrayList<Proveedor> proveedores = new ArrayList<>();

        proveedores = proveedorController.obtenerProveedores();

        for (Proveedor indexProveedor:proveedores) {
            if (indexProveedor.getId() == idProveedorTest){
                assertEquals("Equipo update 1",indexProveedor.getNombreProveedor());
                assertEquals("equipo1@uniminuto.edu.co",indexProveedor.getEmail());
                assertEquals(987654321,indexProveedor.getNumeroTelefono());
            }
        }
    }

    @Test
    public void eliminarProveedor() {
        Proveedor proveedorDeleteTest = new Proveedor("Equipo update 1","equipo1@uniminuto.edu.co",987654321);

        long idProveedorTest = proveedorController.eliminarProveedor(proveedorDeleteTest);

        ArrayList<Proveedor> proveedores = new ArrayList<>();

        proveedores = proveedorController.obtenerProveedores();

        boolean proveedorEnBaseDeDatos = false;

        for (Proveedor indexProveedor:proveedores) {
            if (indexProveedor.getId() == idProveedorTest){
                proveedorEnBaseDeDatos = true;
                break;
            }
        }
        assertEquals(false,proveedorEnBaseDeDatos);
    }




}