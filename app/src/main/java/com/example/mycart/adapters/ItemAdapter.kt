package com.example.mycart.adapters
import android.content.Context
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mycart.R
import com.example.mycart.model.ItemString
import kotlinx.android.synthetic.main.item_custom_row.*
import kotlinx.android.synthetic.main.item_custom_row.view.*



class ItemAdapter (var context: Context, var  items: ArrayList<ItemString>,val onClickListener: ItemAdapter.OnClickListener) :
    RecyclerView.Adapter<ItemAdapter.ItemHolder>(){
    private lateinit var cListener : ItemAdapter.OnItemClickListener
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setonItemClickListener(clickListener: ItemAdapter.OnItemClickListener){
        cListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_custom_row,parent,false)

        return ItemHolder(itemView)
    }


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {


        val item = items[position]
        val s=items.indices
        val item2=items[position+1]
        val item3=items[position+2]
        val item4=items[position+3]
        val item5=items[position+4]


        Glide.with(holder.icons.context).load(item.empUrl).into(holder.icons)
        Glide.with(holder.icons.context).load(item2.empUrl).into(holder.icons)
        Glide.with(holder.icons.context).load(item3.empUrl).into(holder.icons)
        Glide.with(holder.icons.context).load(item4.empUrl).into(holder.icons)
        Glide.with(holder.icons.context).load(item5.empUrl).into(holder.icons)


        //Glide.with(holder.icons.context).load(item.empUrl).into(holder.icons)
        val itemString:ItemString = this.items[position]
        holder.icons.setImageResource(item.iconString)
        holder.String.text =itemString.empName
        holder.StringPrice.text = itemString.empPrice
//        holder.textView6.text = itemString.
//        holder.String.setOnClickListener{
//       Toast.makeText(context,itemString.empName, Toast.LENGTH_LONG).show()
//      }


    }

    override fun getItemCount(): Int {
        return this.items.size
    }
    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        val button16 = itemView.findViewById<Button>(R.id.button16)
        val button17 = itemView.findViewById<Button>(R.id.button17)
        var icons: ImageView =itemView.findViewById(R.id.milk)
        var String: TextView = itemView.findViewById<TextView>(R.id.tv_item1_name)
        var StringPrice :TextView = itemView.findViewById<TextView>(R.id.textView7)
        val textView6:TextView= itemView.findViewById(R.id.textView6)
//        val textView7:TextView= itemView.findViewById(R.id.textView7)

    }
//    class OnClickListener(val clickListener: (itemString:ItemString)->Unit){
//        fun onClick(itemString:ItemString) = clickListener(itemString)
//    }
//


    class OnClickListener(val clickListener: (itemString:ItemString)->Unit){
        fun onClick(itemString:ItemString) = clickListener(itemString)
    }

    }
















































