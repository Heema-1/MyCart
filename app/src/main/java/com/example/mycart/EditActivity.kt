package com.example.mycart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditActivity : AppCompatActivity() {

    private lateinit var etEmpName: EditText
    private lateinit var etEmpPrice: EditText
    private lateinit var etEmpNum: EditText
    private lateinit var etEmpUrl: EditText
    private lateinit var btnSaveData: Button
    private lateinit var btnBackData: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        etEmpName = findViewById(R.id.etEmpName)
        etEmpPrice = findViewById(R.id.etEmpPrice)
        etEmpNum = findViewById(R.id.etEmpNum)
        etEmpUrl= findViewById(R.id.etEmpUrl)
        btnSaveData = findViewById(R.id.btnSaveData)
        btnBackData = findViewById(R.id.btnBackData)
        dbRef = FirebaseDatabase.getInstance().getReference("Products")
        btnBackData.setOnClickListener{
            startActivity(Intent(this@EditActivity, SellActivity::class.java))
        }
        btnSaveData.setOnClickListener {
            saveProductData()
        }


    }

    private fun saveProductData(){
        val empName = etEmpName.text.toString()
        val empPrice = etEmpPrice.text.toString()
        val empNum = etEmpNum.text.toString()
        val empUrl = etEmpUrl.text.toString()
        if(empName.isEmpty()){
            etEmpName.error = "Please Enter Product Name"
        }
        if(empPrice.isEmpty()){
            etEmpPrice.error = "Please Enter Product Price"
        }
        if(empNum.isEmpty()){
            etEmpNum.error = "Please Enter Product Quantity"
        }
        if(empUrl.isEmpty()){
            etEmpUrl.error = "Please Enter Image URL"
        }
        val empId = dbRef.push().key!!
        val product = ProductModel(empId, empName, empPrice,empNum,empUrl)
        dbRef.child(empId).setValue(product)
            .addOnCompleteListener {
                Toast.makeText(this,"Data inserted Successfully",Toast.LENGTH_LONG).show()

                etEmpName.text.clear()
                etEmpPrice.text.clear()
                etEmpNum.text.clear()
                etEmpUrl.text.clear()

            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()

            }
    }

}







