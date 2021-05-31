package ar.com.pablocaamano.heladeria

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.pablocaamano.heladeria.adapter.PaymentAdapter
import ar.com.pablocaamano.heladeria.model.Order
import ar.com.pablocaamano.heladeria.model.RegisterCash
import ar.com.pablocaamano.heladeria.utils.ActivityUtils

class PaymentActivity : AppCompatActivity() {

    private val utils: ActivityUtils = ActivityUtils();

    private lateinit var order: Order;

    private lateinit var cashBoxs: List<RegisterCash>;

    private lateinit var paymentLayout: RecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        
        initializeElements();
    }

    @SuppressLint("WrongConstant")
    private fun initializeElements() {
        this.cashBoxs = listOf(
            RegisterCash(1,"Caja - Efectivo","Solo se acepta pago con efectivo en moneda nacional",15),
            RegisterCash(2, "Caja - Tarjeta","Se aceptan pagos con tarjeta de debito y credito Visa y Mastercard", 10),
            RegisterCash(3,"Caja - Pago Electronico","Se aceptan pagos con sistemas como MercadoPago, TodoPago y MODO. Aplican promociones vigentes",5)
        );

        if(intent.getSerializableExtra("order") != null){
            this.order = intent.getSerializableExtra("order") as Order;
        } else {
            utils.goToActivity(this, OrderActivity::class.java,order);
        }

        paymentLayout = findViewById(R.id.payment_rv_cash_boxs);
        paymentLayout.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false);
        paymentLayout.adapter = PaymentAdapter(cashBoxs);
    }

}