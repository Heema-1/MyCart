package com.example.mycart


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.inflate
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycart.adapters.ItemAdapter
import com.example.mycart.model.ItemString
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_fetch.*

class FetchActivity : AppCompatActivity() {
//    private lateinit var tvEmpId: EditText
    private lateinit var tvEmpName: EditText
    private lateinit var tvEmpPrice: EditText
    private lateinit var tvEmpNum: EditText
    private lateinit var tvEmpUrl: EditText
    private lateinit var btnSaveData2: Button
    private lateinit var btnBackData2: Button
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch)
        tvEmpName = findViewById(R.id.etEmpName)
        tvEmpPrice = findViewById(R.id.etEmpPrice)
        tvEmpNum = findViewById(R.id.etEmpNum)
        tvEmpUrl= findViewById(R.id.etEmpUrl)
        btnSaveData2 = findViewById(R.id.btnSaveData2)
        btnBackData2 = findViewById(R.id.btnBackData2)
        initView()
        setValuesToViews()
        dbRef = FirebaseDatabase.getInstance().getReference("Products")
        btnBackData2.setOnClickListener{
            startActivity(Intent(this@FetchActivity, SellActivity::class.java))
        }
        btnSaveData2.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("empId").toString(),
                intent.getStringExtra("empName").toString(),
                intent.getStringExtra("empPrice").toString(),
                intent.getStringExtra("empNum").toString(),
                intent.getStringExtra("empUrl").toString()
            )

        }
    }


    private fun initView(){
        tvEmpName = findViewById<EditText>(R.id.etEmpName)
        tvEmpPrice = findViewById<EditText>(R.id.etEmpPrice)
        tvEmpNum = findViewById<EditText>(R.id.etEmpNum)
        tvEmpUrl = findViewById<EditText>(R.id.etEmpUrl)

    }


    private fun setValuesToViews(){
//        tvEmpId.setText(intent.getStringExtra("empId"))
        tvEmpName.setText( intent.getStringExtra("empName"))
        tvEmpPrice.setText( intent.getStringExtra("empPrice"))
        tvEmpNum.setText( intent.getStringExtra("empNum"))
        tvEmpUrl.setText( intent.getStringExtra("empUrl"))

    }


    private fun openUpdateDialog(
        empId :String,
        empName :String,
        empPrice :String,
        empNum : String,
        empUrl: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogueView = inflater.inflate(R.layout.activity_fetch,null)

        mDialog.setView(mDialogueView)

        val etEmpName = mDialogueView.findViewById<EditText>(R.id.etEmpName)
        val etEmpPrice = mDialogueView.findViewById<EditText>(R.id.etEmpPrice)
        val etEmpNum = mDialogueView.findViewById<EditText>(R.id.etEmpNum)
        val etEmpUrl = mDialogueView.findViewById<EditText>(R.id.etEmpUrl)
        val btnUpdateData = mDialogueView.findViewById<Button>(R.id.btnSaveData2)
        val btnBack = mDialogueView.findViewById<Button>(R.id.btnBackData2)

        etEmpName.setText(  intent.getStringExtra("empName").toString())
        etEmpPrice.setText(  intent.getStringExtra("empPrice").toString())
        etEmpNum.setText(  intent.getStringExtra("empNum").toString())
        etEmpUrl.setText(  intent.getStringExtra("empUrl").toString())

        mDialog.setTitle("Updating $empName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            Toast.makeText(applicationContext,"Product Data Updated",Toast.LENGTH_LONG).show()


            updateEmpData(
                empId,
                etEmpName.text.toString(),
                etEmpPrice.text.toString(),
                etEmpNum.text.toString(),
                etEmpUrl.text.toString(),

                )

            Toast.makeText(applicationContext,"Product Data Updated",Toast.LENGTH_LONG).show()

//            val empName = etEmpName.text.toString()
//            val empPrice = etEmpPrice.text.toString()
//            val empNum = etEmpNum.text.toString()
//            val empUrl = etEmpUrl.text.toString()
            tvEmpName.text =etEmpName.text
            tvEmpPrice.text = etEmpPrice.text
            tvEmpNum.text = etEmpNum.text
            tvEmpUrl.text = etEmpUrl.text


            alertDialog.dismiss()
        }


    }

    private fun updateEmpData(
        id :String,
        name :String,
        price :String,
        num : String,
        url: String


    ){
       val dbRef = FirebaseDatabase.getInstance().getReference("Products").child(id)
        val product = ProductModel(id, name, price,num,url)
        dbRef.setValue(product)
    }
//    private fun saveProductEdits(){
//        val empName = etEmpName.text.toString()
//        val empPrice = etEmpPrice.text.toString()
//        val empNum = etEmpNum.text.toString()
//        val empUrl = etEmpUrl.text.toString()
//
//
//        val empId = dbRef.push().key!!
//        val product = ProductModel(empId, empName, empPrice,empNum,empUrl)
//        dbRef.child(empId).setValue(product)
//            .addOnCompleteListener {
//                Toast.makeText(this,"Data Edited Successfully", Toast.LENGTH_LONG).show()
//
//            }.addOnFailureListener { err ->
//                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
//
//            }
//    }



}












