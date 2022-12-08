package com.example.mycart

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mycart.R.drawable

import com.example.mycart.adapters.ItemAdapter
import com.example.mycart.adapters.ItemaAdapterSell
import com.example.mycart.model.ItemString
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class BuyActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView?=null
    private lateinit var dbRef: DatabaseReference
    private var Quantity=0
    private lateinit var storageref:StorageReference



    @SuppressLint("MissingInflatedId")
    override fun onCreate
        (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)
        storageref=FirebaseStorage.getInstance().getReference("IMGS")
        val button14 = findViewById<Button>(R.id.button14)
        val button15 = findViewById<Button>(R.id.button15)
//        val button16 = findViewById<Button>(R.id.button16)
//        val button17 = findViewById<Button>(R.id.button17)

        //dbRef = FirebaseDatabase.getInstance().getReference("Products")

        button14.setOnClickListener {
            startActivity(Intent(this@BuyActivity, CartActivity::class.java))
        }
        button15.setOnClickListener {
                startActivity(Intent(this@BuyActivity, MainActivity::class.java))
            }
//        button16.setOnClickListener {
//            if(Quantity!=0){
//                Quantity--
//            }
//        }
//        button17.setOnClickListener {
//            Quantity++
//
        val items :ArrayList<ItemString> = ArrayList()
        val layoutManager= LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recycler_view_item1)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.setHasFixedSize(true)

        FirebaseDatabase.getInstance().getReference("Products").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (myProduct in dataSnapshot.children) {
                        //var img=storageref.getInstance().getReference("IMGS").child(UserRefcurrentUser?.uid.toString()).child("$name jpeg")
                        //Glide.with(holder.icons.context).load(items.empUrl).into(holder.icons)
                        var tmp = myProduct.getValue(ItemString::class.java)
                        tmp!!.empId = myProduct.key!!

                        items.add(tmp!!)

                    }
                    recyclerView?.adapter = ItemAdapter(applicationContext, items,ItemAdapter.OnClickListener{

                    }
                    )

                    Toast.makeText(applicationContext, "items size: ${items.size}", Toast.LENGTH_SHORT).show()

                }
                else{
                    Toast.makeText(applicationContext, "herere", Toast.LENGTH_SHORT).show()

                }
            }
            override fun onCancelled(error: DatabaseError) {
                //
            }
        })

        }






        //setItemList()

   /* private fun setItemList(): ArrayList<ItemString> {

        val items :ArrayList<ItemString> = ArrayList()
        dbRef=FirebaseDatabase.getInstance().getReference("Products")
        dbRef.get().addOnSuccessListener{
            for(i in it.children){
                var empId=i.child("empId").getValue().toString()
                var empName=i.child("empName").getValue().toString()
                var empPrice=i.child("empPrice").getValue().toString()
                var empNum=i.child("empNum").getValue().toString()
                var empUrl=i.child("empUrl").getValue().toString()

                var item=ItemString(R.drawable.img,empId,empName,empPrice,empNum,empUrl)
                items.add(item)



            }
            //Toast.makeText(applicationContext, items.size + 1, Toast.LENGTH_SHORT).show()

        }
        //Toast.makeText(applicationContext, items.size, Toast.LENGTH_SHORT).show()

        return items

    }*/


}


