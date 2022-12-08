package com.example.mycart


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycart.adapters.CartAdapter
import com.example.mycart.adapters.ItemAdapter
import com.example.mycart.adapters.ItemaAdapterSell
import com.example.mycart.model.CartModel
import com.example.mycart.model.ItemString
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
//import com.google.firebase.storage.ktx.storage


class CartActivity : AppCompatActivity() {
    private lateinit var sellerDatabase: DatabaseReference
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<CartModel>
    lateinit var imageId:Array<Int>
    lateinit var name: String
    lateinit var price: String
    lateinit var quantity:String
    lateinit var totalPrice:String
    private var UserRef= Firebase.auth
    //private var storageRef= Firebase.storage
    private lateinit var firebaseRef:DatabaseReference
    var total=0.0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        val button13 = findViewById<Button>(R.id.button13)
        button13.setOnClickListener {
            startActivity(Intent(this@CartActivity, BuyActivity::class.java))
        }
//        val plus : Button=findViewById(R.id.Plus)
        //val checkout : Button =findViewById(R.id.checkoutButton)
        firebaseRef=FirebaseDatabase.getInstance().getReference("Products")

//        imageId=arrayOf(R.drawable.apple)
//
//        heading=arrayOf("apple")
//        price= arrayOf(0)
//        quantity= arrayOf(0)

        //newRecyclerView=findViewById(R.id.CartrecyclerView)

        newArrayList= arrayListOf<CartModel>()



//        checkout.setOnClickListener{
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            Toast.makeText(this, "order placed", Toast.LENGTH_SHORT)
////                                .show()
//        }

    }

//    private fun getUserdata() {
//        Log.d("TESTTTTTTTTT" , Firebase.auth.currentUser?.uid.toString())
//        sellerDatabase=firebaseRef.getReference("Products").child(Firebase.auth.currentUser?.uid.toString())
//        sellerDatabase.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                for (childSnapshot in snapshot.children) {
//                    var tmp2 = childSnapshot.getValue(CartModel::class.java)
//                    tmp2!!.id = childSnapshot.key!!
//                    newArrayList.add(tmp2)
//                //                    var name = childSnapshot.child("heading").value.toString()
////                    var totalPrice = childSnapshot.child("price").value.toString()
////                    var quantity = childSnapshot.child("quantity").value.toString()
////                    var price=childSnapshot.child("origPrice").value.toString()
//
//
////                        val img=storageRef.getReference("images").child(UserRef.currentUser?.uid.toString()).child("$newName.jpeg")
//                    //val BuyerItem = CartItems(newName,newQuantity, newPrice,orgQ)
//                    //newArrayList.add(BuyerItem)
//                }
//                //newRecyclerView.adapter= CartAdapter(newArrayList)
////                newRecyclerView?.adapter = ItemAdapter(applicationContext,ItemAdapter.ItemHolder, ItemAdapter.OnClickListener{
////
////
////                })
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })}
private fun setItemList(): ArrayList<ItemString> {

    val items :ArrayList<ItemString> = ArrayList()
    firebaseRef=FirebaseDatabase.getInstance().getReference("Products")
    firebaseRef.get().addOnSuccessListener{
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
        newRecyclerView = findViewById(R.id.recycler_view_cart)
        newRecyclerView.layoutManager= LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)


        newRecyclerView.adapter = CartAdapter(newArrayList)


    }
//        items.add(ItemString(R.drawable.img,"MILK",10,0,"20 EGP"))
//        items.add(ItemString(R.drawable.img_1,"CHEESE",10,0,"25EGP"))
//        items.add(ItemString(R.drawable.img_3,"BREAD",10,0,"15EGP"))
//        items.add(ItemString(R.drawable.img_4,"MEAT",10,0,"120EGP"))
//        items.add(ItemString(R.drawable.img_5,"CHICKEN",10,0,"70EGP"))
//        items.add(ItemString(R.drawable.img_6,"COLA",10,0,"7EGP"))

    return items
}








}


