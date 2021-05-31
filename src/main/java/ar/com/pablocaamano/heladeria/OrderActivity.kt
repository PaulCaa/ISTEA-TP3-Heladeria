package ar.com.pablocaamano.heladeria

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.pablocaamano.heladeria.adapter.ItemsLayoutAdapter
import ar.com.pablocaamano.heladeria.model.IceCream
import ar.com.pablocaamano.heladeria.model.Order
import ar.com.pablocaamano.heladeria.utils.ActivityUtils

class OrderActivity : AppCompatActivity() {

    private val utils: ActivityUtils = ActivityUtils();

    private lateinit var order: Order;

    private lateinit var title: TextView;

    private lateinit var itemsLayout: RecyclerView;

    private lateinit var totalOrder: TextView;

    private lateinit var finishBtn: Button;
    private lateinit var addBtn: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initializeElements();

        addBtn.setOnClickListener(View.OnClickListener {
            utils.goToActivity(this,MainActivity::class.java,order);
        });
    }

    @SuppressLint("WrongConstant")
    private fun initializeElements() {
        var items: MutableList<IceCream> = mutableListOf();
        if(intent.getSerializableExtra("order") != null){
            this.order = intent.getSerializableExtra("order") as Order;
            items = order.iceCreams;
        } else {
            utils.goToActivity(this, MainActivity::class.java,order);
        }

        title = findViewById(R.id.order_tv_title);
        var t: String = title.text.toString();
        title.text = t + " " + this.order.idOrder;

        itemsLayout = findViewById(R.id.order_rv_items);
        itemsLayout.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        itemsLayout.adapter = ItemsLayoutAdapter(items);

        totalOrder = findViewById(R.id.order_tv_total_value);
        totalOrder.text = calculateTotalOrder();

        finishBtn = findViewById(R.id.order_btn_finish);
        addBtn = findViewById(R.id.order_btn_more);
    }

    private fun calculateTotalOrder() : String {
        var result: Float = 0F;
        for(ic in order.iceCreams){
            result += ic.price;
        }
        return "$ ${result}";
    }
}