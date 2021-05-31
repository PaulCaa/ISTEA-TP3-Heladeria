package ar.com.pablocaamano.heladeria

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import ar.com.pablocaamano.heladeria.model.IceCream
import ar.com.pablocaamano.heladeria.model.Order
import ar.com.pablocaamano.heladeria.utils.ActivityUtils
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val utils: ActivityUtils = ActivityUtils();

    private lateinit var prices: Map<String, IceCream>;

    private lateinit var order: Order;
    private lateinit var iceCreams: MutableList<IceCream>;

    private lateinit var rgOptions: RadioGroup;
    private lateinit var  rbSelection: RadioButton;
    private lateinit var rbCono: RadioButton;
    private lateinit var priceCono: TextView;
    private lateinit var rbCuarto: RadioButton;
    private lateinit var priceCuarto: TextView;
    private lateinit var rbMedio: RadioButton;
    private lateinit var priceMedio: TextView;
    private lateinit var rbKilo: RadioButton;
    private lateinit var priceKilo: TextView;

    private lateinit var backBtn: Button;
    private lateinit var nextBtn: Button;

    private lateinit var previewImageView: ImageView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeElems();

        backBtn.setOnClickListener(View.OnClickListener {
            utils.goToActivity(this,OrderActivity::class.java,order);
        });

        nextBtn.setOnClickListener(View.OnClickListener {
            validateSelection();
        });
    }

    private fun initializeElems() {
        prices = mapOf(
            "CONO" to IceCream(1,250F,null,2),
            "CUARTO" to IceCream(2, 280F,null,3),
            "MEDIO" to IceCream(3, 500F,null,3),
            "KILO" to IceCream(4, 930F,null,4)
        );

        rgOptions = findViewById(R.id.main_rg_items);
        rbCono = findViewById(R.id.main_rb_cono);
        priceCono = findViewById(R.id.main_tv_cono_precio);
        rbCuarto = findViewById(R.id.main_rb_cuarto);
        priceCuarto = findViewById(R.id.main_tv_cuarto_precio);
        rbMedio = findViewById(R.id.main_rb_medio);
        priceMedio = findViewById(R.id.main_tv_medio_precio);
        rbKilo = findViewById(R.id.main_rb_kilo);
        priceKilo = findViewById(R.id.main_tv_kilo_precio);

        backBtn = findViewById(R.id.main_btn_back);
        nextBtn = findViewById(R.id.main_btn_next);

        previewImageView = findViewById(R.id.main_iv_preview);

        if(intent.getSerializableExtra("order") != null){
            this.order = intent.getSerializableExtra("order") as Order;
            this.iceCreams = order.iceCreams;
            this.backBtn.visibility = View.VISIBLE;
        } else {
            this.iceCreams = mutableListOf();
            this.order = Order(Random.nextInt(0,99999),iceCreams);
        }

        priceCono.text = "$ ${prices["CONO"]?.price}";
        priceCuarto.text = "$ ${prices["CUARTO"]?.price}";
        priceMedio.text = "$ ${prices["MEDIO"]?.price}";
        priceKilo.text = "$ ${prices["KILO"]?.price}";
    }

    fun rbAction(view: View) {
        if(view is RadioButton){
            when(view.id) {
                R.id.main_rb_cono -> {
                    previewImageView.setImageResource(R.mipmap.cono);
                }
                R.id.main_rb_cuarto -> {
                    previewImageView.setImageResource(R.mipmap.cuarto);
                }
                R.id.main_rb_medio-> {
                    previewImageView.setImageResource(R.mipmap.medio);
                }
                R.id.main_rb_kilo -> {
                    previewImageView.setImageResource(R.mipmap.kilo);
                }
            }
            nextBtn.isEnabled = true;
        }
    }


    private fun validateSelection() {
        try {
            rbSelection = findViewById(rgOptions.checkedRadioButtonId);

            if(rbSelection == null) throw Exception("No se seleccionó un helado");

            when(rbSelection.id) {
                rbCono.id -> {
                    order.iceCreams += prices["CONO"]!!;
                }
                rbCuarto.id -> {
                    order.iceCreams += prices["CUARTO"]!!;
                }
                rbMedio.id -> {
                    order.iceCreams += prices["MEDIO"]!!;
                }
                rbKilo.id -> {
                    order.iceCreams += prices["KILO"]!!;
                }
            }
            Toast.makeText(this,order.iceCreams.toString(),Toast.LENGTH_SHORT).show();
            // pasar a la siguiente activity
            utils.goToActivity(this,FlavorActivity::class.java,order);
        } catch (e: Exception) {
            Log.e("mainActivity", e.message.toString());
            Toast.makeText(this,"Seleccione un item para continuar",Toast.LENGTH_LONG).show();
        }
    }
}

