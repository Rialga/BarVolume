package com.example.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var panjangEt : EditText
    private lateinit var lebarEt : EditText
    private lateinit var tinggiEt : EditText
    private lateinit var kalkulasiBtn : Button
    private lateinit var hasilTv : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        panjangEt = findViewById(R.id.et_panjang)
        lebarEt = findViewById(R.id.et_lebar)
        tinggiEt = findViewById(R.id.et_tinggi)
        kalkulasiBtn = findViewById(R.id.btn_kalkulasi)
        hasilTv = findViewById(R.id.tv_result)

        kalkulasiBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id == R.id.btn_kalkulasi){
                val panjang = panjangEt.text.toString().trim()
                val lebar = lebarEt.text.toString().trim()
                val tinggi = tinggiEt.text.toString().trim()

                val volume = panjang.toDouble() * lebar.toDouble() * tinggi.toDouble()
                hasilTv.text = volume.toString()
            }
        }
    }
}