package ar.com.pablocaamano.heladeria.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.pablocaamano.heladeria.R
import ar.com.pablocaamano.heladeria.model.Flavor
import ar.com.pablocaamano.heladeria.model.IceCream

class ItemsLayoutAdapter(private val dataSet: MutableList<IceCream>) : RecyclerView.Adapter<ItemsLayoutAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.item_layout_name_product);
        val itemFlavours: TextView = view.findViewById(R.id.item_layout_product_flavors);
        val itemPrice: TextView = view.findViewById(R.id.item_layout_product_price);
        val itemImg: ImageView = view.findViewById(R.id.item_layout_product_img);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_order,parent,false);
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = this.getICNameBy(dataSet[position].type);
        holder.itemFlavours.text = getFlavors(dataSet[position].flavors);
        holder.itemPrice.text = "$ ${dataSet[position].price}";
        holder.itemImg.setImageResource(getImgResourseBy(dataSet[position].type));
    }

    override fun getItemCount(): Int {
        return dataSet.size;
    }


    private fun getICNameBy(id: Int) : String {
        var name: String = "";
        when(id) {
            1 -> name = "Cono de 2 sabores";
            2 -> name = "Pote de 1/4 Kg";
            3 -> name = "Pote de 1/2 Kg";
            4 -> name = "Pote de 1Kg";
        }
        return name;
    }

    private fun getFlavors(flavors: MutableList<Flavor>?) : String {
        var fStr: String = "";
        if(flavors != null) {
            for(f in flavors){
                fStr += f.name + ", ";
            }
        }
        return fStr;
    }

    private fun getImgResourseBy(id: Int) : Int {
        var img = 0;
        when(id) {
            1 -> img = R.mipmap.cono;
            2 -> img = R.mipmap.cuarto;
            3 -> img = R.mipmap.medio;
            4 -> img = R.mipmap.kilo;
        }
        return img;
    }
}