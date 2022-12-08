package com.example.mycart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycart.adapters.ItemAdapter
import com.example.mycart.adapters.ItemaAdapterSell
import com.example.mycart.model.ItemString
import com.google.firebase.database.*

class SellActivity : AppCompatActivity()   {
    private var recyclerView: RecyclerView?=null
    var arraylist:ArrayList<ItemString>?=null
    private var itemAdapter: ItemAdapter?=null
    private lateinit var tvloadingData: TextView
    val items :ArrayList<ItemString> = ArrayList()
    private lateinit var dbRef : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell)
        dbRef = FirebaseDatabase.getInstance().getReference("Products")
        //getDATA()
        val button12 = findViewById(R.id.button12) as Button
        val button18 = findViewById(R.id.button18) as Button
        recyclerView = findViewById(R.id.recycler_view_item2)
        button12.setOnClickListener {
            startActivity(Intent(this@SellActivity, MainActivity::class.java))
    }
        button18.setOnClickListener {
            startActivity(Intent(this@SellActivity, EditActivity::class.java))}


        //recyclerView?.layoutManager = GridLayoutManager(applicationContext ,2, LinearLayoutManager.VERTICAL,false)
        //arraylist = ArrayList()
           // arraylist = setlist()
          //itemAdapter = ItemAdapter(applicationContext,setItemList())
        //recyclerView?.adapter = itemAdapter
        setItemList()

    }
    private fun setItemList(): ArrayList<ItemString> {

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
            val layoutManager=LinearLayoutManager(this)
            recyclerView = findViewById(R.id.recycler_view_item2)
            recyclerView?.layoutManager =layoutManager
            recyclerView?.setHasFixedSize(true)
            recyclerView?.adapter = ItemaAdapterSell(applicationContext,items,ItemaAdapterSell.OnClickListener{

                startActivity(Intent(this@SellActivity, FetchActivity::class.java))
            })
        }
//        items.add(ItemString(R.drawable.img,"MILK",10,0,"20 EGP"))
//        items.add(ItemString(R.drawable.img_1,"CHEESE",10,0,"25EGP"))
//        items.add(ItemString(R.drawable.img_3,"BREAD",10,0,"15EGP"))
//        items.add(ItemString(R.drawable.img_4,"MEAT",10,0,"120EGP"))
//        items.add(ItemString(R.drawable.img_5,"CHICKEN",10,0,"70EGP"))
//        items.add(ItemString(R.drawable.img_6,"COLA",10,0,"7EGP"))

        return items
    }

//    private fun getDATA() {
//        recyclerView?.visibility = View.GONE
//        tvloadingData.visibility = View.VISIBLE
//        dbRef = FirebaseDatabase.getInstance().getReference("items")
//        dbRef.addValueEventListener(object: ValueEventListener
//        {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                items.clear()
//                if(snapshot.exists())
//                {
//                    for(empSnap in snapshot.children )
//                    {
//                        val empData = empSnap.getValue(ItemString::class.java)
//                        items.add(empData!!)
//
//                    }
//
//                    //val itemAdapter = ItemAdapter(context  = applicationContext ,items)
//                   /* itemAdapter.setonItemClickListener(object: ItemAdapter.onItemClickListener
//                    {
//                        override fun onItemClick(position: Int) {
//                            val intent = Intent(this@SellActivity,FetchActivity::class.java)
//                            intent.putExtra("empId",items[position].empId)
//                            intent.putExtra("empName",items[position].empName)
//                            intent.putExtra("empPrice",items[position].empPrice)
//                            intent.putExtra("empNum",items[position].empNum)
//                            intent.putExtra("empUrl",items[position].empUrl)
//                            startActivity(intent)
//
//                        }
//                    })*/
//
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//            }
//
//        })
//
//    }




}