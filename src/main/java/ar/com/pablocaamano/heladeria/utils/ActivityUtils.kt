package ar.com.pablocaamano.heladeria.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import ar.com.pablocaamano.heladeria.model.Order
import ar.com.pablocaamano.heladeria.model.RegisterSale

class ActivityUtils {

    fun <T>goToActivity(context: Context, view: Class<T>) {
        this.goToActivity(context,view,null);
    }

    fun <T>goToActivity(context: Context, view: Class<T>, param: Order?) {
        val intent: Intent = Intent(context,view);
        if(param != null) {
            intent.putExtra("order", param);
        }
        startActivity(context,intent,null);
    }

}