package ar.com.pablocaamano.heladeria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import ar.com.pablocaamano.heladeria.model.Order
import ar.com.pablocaamano.heladeria.utils.ActivityUtils

class FinalActivity : AppCompatActivity() {

    private val utils: ActivityUtils = ActivityUtils();

    private lateinit var order: Order;

    private lateinit var messaje: TextView;
    private lateinit var newOrder: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        initializeElements();

        newOrder.setOnClickListener(View.OnClickListener {
            utils.goToActivity(this,MainActivity::class.java,null);
        });
    }

    private fun initializeElements() {
        messaje = findViewById(R.id.final_tv_descrip);
        newOrder = findViewById(R.id.final_btn_new);

        if(intent.getSerializableExtra("order") != null) {
            this.order = intent.getSerializableExtra("order") as Order;
            this.messaje.text = "Puede retirar su pedido con el numero: ${order.idOrder}";
        }
    }
}