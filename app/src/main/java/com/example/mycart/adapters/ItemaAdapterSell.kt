package com.example.mycart.adapters

import android.content.Context
import android.content.Intent
import android.icu.text.Transliterator.Position
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mycart.FetchActivity
import com.example.mycart.R
import com.example.mycart.model.ItemString
import kotlinx.android.synthetic.main.item_custom_row.*
import kotlinx.android.synthetic.main.item_custom_row.view.*


class ItemaAdapterSell (var context: Context ,var  items: ArrayList<ItemString>,val onClickListener: OnClickListener) :
    RecyclerView.Adapter<ItemaAdapterSell.ItemHolder>(){
    private lateinit var mListener :OnItemClickListener
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setonItemClickListener(clickListener: ItemaAdapterSell.OnItemClickListener){
        mListener = clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_custom_row_seller,parent,false)

        return ItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {



        val item = items[position]
        val itemString:ItemString = this.items[position]
        holder.icons.setImageResource(item.iconString)
        holder.String.text =itemString.empName
//        holder.String.setOnClickListener{
//            Toast.makeText(context,itemString.empName, Toast.LENGTH_LONG).show()

        holder.cardView.setOnClickListener{
            val intent = Intent(context , FetchActivity::class.java)
            intent.putExtra( "empName", itemString.toString())
            intent.putExtra( "empPrice",itemString.empPrice )
            intent.putExtra("empNum",itemString.empNum)
            intent.putExtra("empUrl",itemString.empUrl)
            context.startActivity(intent)

        }
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }}
        override fun getItemCount(): Int {
            return this.items.size
        }
        class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val cardView = itemView.findViewById<CardView>(R.id.cartItem)
//            val button18 = itemView.findViewById<Button>(R.id.button18)
//            val button12 = itemView.findViewById<Button>(R.id.button12)
            val i =0
            val icons: ImageView =itemView.findViewById(R.id.milk)
            var String: TextView = itemView.findViewById<TextView>(R.id.tv_item1_name)

        }
        class OnClickListener(val clickListener: (itemString:ItemString)->Unit){
            fun onClick(itemString:ItemString) = clickListener(itemString)
        }


    }







