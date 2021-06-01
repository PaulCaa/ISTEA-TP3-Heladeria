package ar.com.pablocaamano.heladeria

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.pablocaamano.heladeria.adapter.StaticLayoutAdapter
import ar.com.pablocaamano.heladeria.dao.SalesDBHelper
import ar.com.pablocaamano.heladeria.model.RegisterSale
import ar.com.pablocaamano.heladeria.model.Static
import ar.com.pablocaamano.heladeria.utils.ActivityUtils

class StaticsActivity : AppCompatActivity() {

    private val utils: ActivityUtils = ActivityUtils();

    private val db: SalesDBHelper = SalesDBHelper(this,null);

    private val names: Map<Int,String> = mapOf(
        1 to "Caja 1 - Efectivo",
        2 to "Caja 2 - tarjeta",
        3 to "Caja 3 - Pago Electronico"
    );

    private lateinit var rvStatics: RecyclerView;
    private lateinit var btnExit: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statics);

        initializeElements();

        btnExit.setOnClickListener(View.OnClickListener {
            utils.goToActivity(this,MainActivity::class.java,null);
        });
    }

    @SuppressLint("WrongConstant")
    private fun initializeElements() {
        rvStatics = findViewById(R.id.statics_rv_boxs);
        btnExit = findViewById(R.id.statics_btn_exit);

        rvStatics.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false);
        rvStatics.adapter = StaticLayoutAdapter(this.getInfoStatic());
    }

    private fun getInfoStatic() : MutableList<Static> {
        var info: MutableList<Static> = mutableListOf();

        val cash: List<RegisterSale> = this.getResultBy(1);
        if(cash.isNotEmpty()){
            info.add(this.getStaticObject(cash));
        }
        val card: List<RegisterSale> = this.getResultBy(2);
        if(card.isNotEmpty()){
            info.add(this.getStaticObject(card));
        }
        val elec: List<RegisterSale> = this.getResultBy(3);
        if(elec.isNotEmpty()){
            info.add(this.getStaticObject(elec));
        }

        return info;
    }

    private fun getResultBy(idBox: Int) : List<RegisterSale> {
        return db.selectBy(idBox);
    }

    private fun getStaticObject(s: List<RegisterSale>) : Static {
        var money: Float = 0F;
        for(i in s) money += i.price;
        val name: String = names[s[0].idCaja].toString();
        return Static(name,s.size,money);
    }
}