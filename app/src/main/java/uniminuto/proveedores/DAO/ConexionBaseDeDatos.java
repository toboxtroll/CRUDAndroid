package uniminuto.proveedores.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionBaseDeDatos extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DE_DATOS = "Uniminuto", NOMBRE_TABLA = "agenda";
    private static final int VERSION_BASE_DE_DATOS = 1;

    public ConexionBaseDeDatos(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, nombreproveedor text, email text, numero int)", NOMBRE_TABLA));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
