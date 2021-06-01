package ar.com.pablocaamano.heladeria.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.pablocaamano.heladeria.R
import ar.com.pablocaamano.heladeria.model.Static

class StaticLayoutAdapter(private val dataSet: MutableList<Static>) : RecyclerView.Adapter<StaticLayoutAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.layout_statics_title);
        val orders: TextView = view.findViewById(R.id.layout_statics_qantity);
        val money: TextView = view.findViewById(R.id.layout_statics_money);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_statics_boxs,parent,false);
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataSet[position].name;
        holder.orders.text = "Cantidad de ventas: ${dataSet[position].orders}";
        holder.money.text = "Dinero total: $ ${dataSet[position].money}";
    }

    override fun getItemCount(): Int {
        return dataSet.size;
    }
}