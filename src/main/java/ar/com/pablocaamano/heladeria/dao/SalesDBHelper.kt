package ar.com.pablocaamano.heladeria.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import ar.com.pablocaamano.heladeria.model.RegisterSale
import java.lang.Exception

class SalesDBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "sales-heladeria-test1.db";
        private const val DATABASE_VERSION = 1;

        const val TABLE_SALES = "sales";
        const val COLUMN_ID = "idCaja";
        const val COLUMN_ORDER = "order";
        const val COLUMN_PRICE = "price";
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = (
                "CREATE TABLE $TABLE_SALES  (" +
                        "$COLUMN_ID  INTEGER PRIMARY KEY," +
                        "$COLUMN_ORDER TEXT," +
                        "$COLUMN_PRICE NUMBER)"
                );
        db?.execSQL(CREATE_TABLE);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion != newVersion) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_SALES");
            onCreate(db);
        }
    }

    fun insert(register: RegisterSale) : Boolean {
        Log.i("SalesDBHelper", "registrando venta= {idCaja: ${register.idCaja}, order: ${register.order}, price ${register.price}");
        var result: Boolean;
        try {
            val db = this.writableDatabase;

            val values = ContentValues();
            values.put(COLUMN_ID,register.idCaja);
            values.put(COLUMN_ORDER,register.order);
            values.put(COLUMN_PRICE,register.price);

            db.insert(TABLE_SALES,null,values);

            Log.i("SalesDBHelper", "se registro operacion");
            result = true;
        }  catch (e: Exception) {
            Log.e("SalesDBHelper",e.message.toString());
            result = false;
        }
        return result;
    }
}