package ar.com.pablocaamano.heladeria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import ar.com.pablocaamano.heladeria.model.Flavor
import ar.com.pablocaamano.heladeria.model.Order
import ar.com.pablocaamano.heladeria.utils.ActivityUtils

class FlavorActivity : AppCompatActivity() {

    private val utils: ActivityUtils = ActivityUtils();

    private lateinit var order: Order;
    private var flavorCount: Int = 0;
    private lateinit var flavors: MutableList<Flavor>;

    private lateinit var opt1: CheckBox;
    private lateinit var opt2: CheckBox;
    private lateinit var opt3: CheckBox;
    private lateinit var opt4: CheckBox;
    private lateinit var opt5: CheckBox;
    private lateinit var opt6: CheckBox;
    private lateinit var opt7: CheckBox;
    private lateinit var opt8: CheckBox;
    private lateinit var opt9: CheckBox;

    private lateinit var backBtn: Button;
    private lateinit var nextBtn: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flavor);

        initializeElements();

        backBtn.setOnClickListener(View.OnClickListener {
            utils.goToActivity(this, MainActivity::class.java,order);
        });

        nextBtn.setOnClickListener(View.OnClickListener {
            val s = order.iceCreams.size;
            order.iceCreams[s-1].flavors = flavors;
            utils.goToActivity(this,OrderActivity::class.java,order);
        });

        opt1.setOnClickListener(View.OnClickListener {
            cbAction(1,opt1);
        });
        opt2.setOnClickListener(View.OnClickListener {
            cbAction(2,opt2);
        });
        opt3.setOnClickListener(View.OnClickListener {
            cbAction(3,opt3);
        });
        opt4.setOnClickListener(View.OnClickListener {
            cbAction(4,opt4);
        });
        opt5.setOnClickListener(View.OnClickListener {
            cbAction(5,opt5);
        });
        opt6.setOnClickListener(View.OnClickListener {
            cbAction(6,opt6);
        });
        opt7.setOnClickListener(View.OnClickListener {
            cbAction(7,opt7);
        });
        opt8.setOnClickListener(View.OnClickListener {
            cbAction(8,opt8);
        });
        opt9.setOnClickListener(View.OnClickListener {
            cbAction(9,opt9);
        });
    }

    private fun initializeElements() {
        opt1 = findViewById(R.id.flavor_cb_1);
        opt2 = findViewById(R.id.flavor_cb_2);
        opt3 = findViewById(R.id.flavor_cb_3);
        opt4 = findViewById(R.id.flavor_cb_4);
        opt5 = findViewById(R.id.flavor_cb_5);
        opt6 = findViewById(R.id.flavor_cb_6);
        opt7 = findViewById(R.id.flavor_cb_7);
        opt8 = findViewById(R.id.flavor_cb_8);
        opt9 = findViewById(R.id.flavor_cb_9);

        backBtn = findViewById(R.id.flavor_btn_back);
        nextBtn = findViewById(R.id.flavor_btn_next);

        if(intent.getSerializableExtra("order") != null){
            this.order = intent.getSerializableExtra("order") as Order;
            val size = order.iceCreams.size;
            this.flavorCount = order.iceCreams[size -1].flavorsCount;
            this.flavors = mutableListOf();
        } else {
            utils.goToActivity(this, MainActivity::class.java,order);
        }
    }


    private fun cbAction(idFlavor: Int, cb: CheckBox) {
        if(cb.isChecked) {
            addFlavor(idFlavor,cb);
        } else {
            removeFlavor(Flavor(idFlavor,cb.text.toString()));
        }
    }

    private fun addFlavor(idFlavor: Int, selection: CheckBox) {
        if(flavors.size < this.flavorCount) {
            flavors += Flavor(idFlavor,selection.text.toString());
            enableNextBtn();
        } else {
            Toast.makeText(this, "Ya se seleccionaron los ${this.flavorCount} sabores",Toast.LENGTH_SHORT).show();
            selection.isChecked = false;
            enableNextBtn();
        }
    }

    private fun removeFlavor(toRemove: Flavor){
        for (f in flavors) {
            if(f.id == toRemove.id) {
                flavors.remove(toRemove);
            }
        }
    }

    private fun enableNextBtn() {
        if(flavors.size == this.flavorCount) {
            nextBtn.isEnabled = true;
        } else {
            nextBtn.isEnabled = false;
        }
    }
}