package com.example.mycart.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycart.R
import com.example.mycart.model.CartModel
import com.example.mycart.model.ItemString
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class CartAdapter(private val newArrayList:ArrayList<CartModel>):
//class CartAdapter(private val itemsList:ArrayList<CartItems>):


    RecyclerView.Adapter<CartAdapter.MyViewHolder>() {
    private var firebaseRef= Firebase.database

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.cart,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item=newArrayList[position]

        println("fdshugbsd fedsfs " + item.name)
//        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.itemName.text=item.name
        holder.itemquantity.text= item.quantity

        holder.itemPrice.text= item.price
        holder.plus.setOnClickListener {

            item.price?.let { it1 -> plusOne( item, holder, it1) }
        }
        holder.minus.setOnClickListener {

            minusOne( holder.itemName.text.toString(),
                holder.itemquantity.text.toString(),
                holder.itemPrice.text.toString() , holder,item.price)        }
    }



    override fun getItemCount(): Int {
        return newArrayList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        //        if there are more items in the recyclerview/list_items  we need to define these items first in the viewholder
        //val titleImage : ShapeableImageView =itemView.findViewById(R.id.title_image)
        val itemName : TextView =itemView.findViewById(R.id.cartname)
        val itemPrice : TextView =itemView.findViewById(R.id.cartprice)
        val itemquantity : TextView =itemView.findViewById(R.id.carquantity)
        val plus: Button =itemView.findViewById(R.id.button20)
        val minus: ImageView=itemView.findViewById(R.id.button21)

    }
    private fun plusOne(item: CartModel , holder: MyViewHolder,price:String) {

//        var maxquan=0
//        var sellerDatabase = firebaseRef.getReference("Seller")
//        sellerDatabase.child(item.heading).child("quantity")
//        sellerDatabase.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for (childSnapshot in snapshot.children) {
//                    Log.d("TESTTTTTTTTT" , Firebase.auth.currentUser?.uid.toString())
//                    if (childSnapshot.child("heading").value.toString().equals(item.heading) ){
//
//                           maxquan=childSnapshot.child("quantity").value.toString().toInt()
//
//                    }
//                }
//            }
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })
//        if(item.quantity.toInt()<maxquan){

        val orgprc=item.price
        var quantity = (item.quantity.toInt() + 1).toString()
        val cost = (item.totalPrice.toDouble() + item.price.toDouble()).toString()

        var origPrice=cost.toDouble()/quantity.toInt()

        var name=item.name
        println("fdshugbsd fedsfs " + item.name)

        //val updatedItems = CartModel(name,orgprc,quantity,cost)
       // firebaseRef.getReference("Cart").child(Firebase.auth.currentUser?.uid.toString()).child(item.name).setValue(updatedItems)
        holder.itemPrice.text = cost
        holder.itemquantity.text = quantity
//        }

    }
    fun minusOne(Name: String, quantity: String, price: String , holder: MyViewHolder,origPrice: String) {
        var cost = ""
        var qua = ""
        if(quantity.toInt() >1){
            val orgprc=price
            cost = (price.toDouble()-orgprc.toDouble()).toString()
            qua = (quantity.toInt()-1).toString()
            //val updatedItems = CartModel(Name, orgprc, quantity,cost)
            //firebaseRef.getReference("Cart").child(Firebase.auth.currentUser?.uid.toString()).child(Name).setValue(updatedItems)

        }
        else{

            firebaseRef.getReference("Cart").child(Firebase.auth.currentUser?.uid.toString()).child(Name).removeValue()//.setValue(updatedItems)
        }

        holder.itemPrice.text = cost
        holder.itemquantity.text = qua
    }


}





















