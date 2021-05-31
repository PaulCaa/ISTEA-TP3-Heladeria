package ar.com.pablocaamano.heladeria.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.pablocaamano.heladeria.R
import ar.com.pablocaamano.heladeria.model.RegisterCash

class PaymentAdapter(private val dataSet: List<RegisterCash>) : RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.payment_layout_title);
        val des: TextView = view.findViewById(R.id.payment_layout_descript);
        val img: ImageView = view.findViewById(R.id.payment_layout_img);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_payment_method,parent,false);
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataSet[position].name;
        holder.des.text = dataSet[position].description;
        holder.img.setImageResource(getImgResourceBy(dataSet[position].id));
    }

    override fun getItemCount(): Int {
        return dataSet.size;
    }

    private fun getImgResourceBy(id: Int) : Int {
        var img = 0;
        when(id) {
            1 -> img = R.mipmap.efectivo;
            2 -> img = R.mipmap.tarjeta;
            3 -> img = R.mipmap.electronico;
        }
        return img;
    }
}