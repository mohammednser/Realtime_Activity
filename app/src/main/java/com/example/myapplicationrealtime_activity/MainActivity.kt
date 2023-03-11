package com.example.myapplicationrealtime_activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var count:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference()

      btn_save.setOnClickListener {
          val name = pt_pname.text.toString()
          val id = pt_pid.text.toString()
          val age = pt_page.text.toString()

          val Person = hashMapOf(
              "name" to name ,
              "id" to id,
              "age" to age
          )
          myRef.child("person").child("$count").setValue(Person)
          count++
          Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT).show()
      }
        btn_getdata.setOnClickListener {
            // Read from the database
            // Read from the database
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue()
                     tv_data.text = value.toString()
                    Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT).show()

                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()


                }
            })
        }

    }
}