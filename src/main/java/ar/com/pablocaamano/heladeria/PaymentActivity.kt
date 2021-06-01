package ar.com.pablocaamano.heladeria

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import ar.com.pablocaamano.heladeria.dao.SalesDBHelper
import ar.com.pablocaamano.heladeria.model.Order
import ar.com.pablocaamano.heladeria.model.RegisterCash
import ar.com.pablocaamano.heladeria.model.RegisterSale
import ar.com.pablocaamano.heladeria.utils.ActivityUtils

class PaymentActivity : AppCompatActivity() {

    private val utils: ActivityUtils = ActivityUtils();

    private val db: SalesDBHelper = SalesDBHelper(this,null);

    private lateinit var order: Order;
    private lateinit var cashBoxs: List<RegisterCash>;
    private lateinit var register: RegisterSale;

    private lateinit var cbCash: CheckBox;
    private lateinit var titleCash: TextView;
    private lateinit var descCash: TextView;
    private lateinit var imgCash: ImageView;
    private lateinit var cbCard: CheckBox;
    private lateinit var titleCard: TextView;
    private lateinit var descCard: TextView;
    private lateinit var imgCard: ImageView;
    private lateinit var cbElectro: CheckBox;
    private lateinit var titleElectro: TextView;
    private lateinit var descElectro: TextView;
    private lateinit var imgElectro: ImageView;
    private lateinit var backBtn: Button;
    private lateinit var nextBtn: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        initializeElements();

        cbCash.setOnClickListener(View.OnClickListener {
            this.cbAction(cbCash);
        });

        cbCard.setOnClickListener(View.OnClickListener {
            this.cbAction(cbCard);
        });

        cbElectro.setOnClickListener(View.OnClickListener {
            this.cbAction(cbElectro);
        });

        backBtn.setOnClickListener(View.OnClickListener {
            utils.goToActivity(this,OrderActivity::class.java,order);
        });

        nextBtn.setOnClickListener(View.OnClickListener {
            if(this.boxValidation()){
                this.registerSale();
            } else {
                Toast.makeText(this,"La caja alcanzo el maximo de cobros, seleccione otra opcion",Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("WrongConstant")
    private fun initializeElements() {
        if(intent.getSerializableExtra("order") != null){
            this.order = intent.getSerializableExtra("order") as Order;
        } else {
            utils.goToActivity(this, OrderActivity::class.java,order);
        }

        this.cashBoxs = listOf(
            RegisterCash(1,"Caja - Efectivo","Solo se acepta pago con efectivo en moneda nacional",15,order),
            RegisterCash(2, "Caja - Tarjeta","Se aceptan pagos con tarjeta de debito y credito Visa y Mastercard", 10,order),
            RegisterCash(3,"Caja - Pago Electronico","Se aceptan pagos con sistemas como MercadoPago, TodoPago y MODO. Aplican promociones vigentes",5,order)
        );

        register = RegisterSale(9999,0,0F);

        cbCash = findViewById(R.id.payment_cb_cash);
        titleCash = findViewById(R.id.payment_tv_cash_title);
        descCash = findViewById(R.id.payment_tv_cash_detail);
        imgCash = findViewById(R.id.payment_iv_cash);
        cbCard = findViewById(R.id.payment_cb_card);
        titleCard = findViewById(R.id.payment_tv_card_title);
        descCard = findViewById(R.id.payment_tv_card_detail);
        imgCard = findViewById(R.id.payment_iv_card);
        cbElectro = findViewById(R.id.payment_cb_electronic);
        titleElectro = findViewById(R.id.payment_tv_electronic_title);
        descElectro = findViewById(R.id.payment_tv_electronic_detail);
        imgElectro = findViewById(R.id.payment_iv_electronic);
        backBtn = findViewById(R.id.payment_btn_back);
        nextBtn = findViewById(R.id.payment_btn_next);

        titleCash.text = this.cashBoxs[0].name;
        descCash.text = this.cashBoxs[0].description;
        imgCash.setImageResource(R.mipmap.efectivo);
        titleCard.text = this.cashBoxs[1].name;
        descCard.text = this.cashBoxs[1].description;
        imgCard.setImageResource(R.mipmap.tarjeta);
        titleElectro.text = this.cashBoxs[2].name;
        descElectro.text = this.cashBoxs[2].description;
        imgElectro.setImageResource(R.mipmap.electronico);
    }

    private fun cbAction(cb: CheckBox) {
        if(cb.isChecked && register.idCaja == 9999) {
            when(cb.id) {
                cbCash.id -> {
                    register = RegisterSale(cashBoxs[0].id,cashBoxs[0].order.idOrder,this.getTotalOrder(order));
                    nextBtn.isEnabled = true;
                }
                cbCard.id -> {
                    register = RegisterSale(cashBoxs[1].id,cashBoxs[1].order.idOrder,this.getTotalOrder(order));
                    nextBtn.isEnabled = true;
                }
                cbElectro.id -> {
                    register = RegisterSale(cashBoxs[2].id,cashBoxs[2].order.idOrder,this.getTotalOrder(order));
                    nextBtn.isEnabled = true;
                }
            }
        } else if(!cb.isChecked) {
            register = RegisterSale(9999,0,0F);
            nextBtn.isEnabled = false;
        } else {
            cb.isChecked = false;
        }
    }

    private fun getTotalOrder(o: Order): Float {
        var total: Float = 0F;
        for(ic in o.iceCreams) total += ic.price;
        return total;
    }

    // Se valida que la caja no haya llegado al maximo de cobros
    private fun boxValidation() : Boolean {
        val result: List<RegisterSale> = db.selectBy(register.idCaja);
        if(result.size < this.cashBoxs[register.idCaja-1].count) {
            return true;
        }
        return false;
    }

    // Se graba compra asociado a la caja
    private fun registerSale() {
        if (register.idCaja != 9999) {
            val result: Boolean = db.insert(register);
            if(result) Toast.makeText(this,"Pago exitoso :D",Toast.LENGTH_SHORT).show();
            else Toast.makeText(this,"Fallo el pago :(",Toast.LENGTH_SHORT).show();
        }
    }
}