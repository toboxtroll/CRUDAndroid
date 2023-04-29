package uniminuto.proveedores.modelos;

public class Proveedor {

    private String nombreProveedor, email;
    private int numeroTelefono;
    private long id;

    public Proveedor(String nombreProveedor, String email, int numeroTelefono) {
        this.nombreProveedor = nombreProveedor;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
    }

    public Proveedor(String nombreProveedor, String email, int numeroTelefono, long id) {
        this.nombreProveedor = nombreProveedor;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.id = id;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "nombreProveedor='" + nombreProveedor + '\'' +
                ", numeroTelefono=" + numeroTelefono +
                '}';
    }
}
