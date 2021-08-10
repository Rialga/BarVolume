package com.example.barvolume

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //Kalkulasi
    private lateinit var panjangEt: EditText
    private lateinit var lebarEt: EditText
    private lateinit var tinggiEt: EditText
    private lateinit var kalkulasiBtn: Button
    private lateinit var hasilTv: TextView

    //Intent
    private lateinit var moveBtn: Button
    private lateinit var moveDataBtn: Button
    private lateinit var moveObjectBtn: Button
    private lateinit var dialBtn: Button
    private lateinit var resultBtn: Button
    private lateinit var intentHasilTv: TextView


    companion object {
        private const val STATE_RESULT = "state_result"
        private const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //kalulasi
        panjangEt = findViewById(R.id.et_panjang)
        lebarEt = findViewById(R.id.et_lebar)
        tinggiEt = findViewById(R.id.et_tinggi)
        hasilTv = findViewById(R.id.tv_result)
        kalkulasiBtn = findViewById(R.id.btn_kalkulasi)

        kalkulasiBtn.setOnClickListener(this)

        //intent
        intentHasilTv = findViewById(R.id.tv_result_intent)
        moveBtn = findViewById(R.id.btn_pindah)
        moveDataBtn = findViewById(R.id.btn_pindah_data)
        moveObjectBtn = findViewById(R.id.btn_pindah_object)
        dialBtn = findViewById(R.id.btn_dial)
        resultBtn = findViewById(R.id.btn_pindah_result)

        moveBtn.setOnClickListener(this)
        moveDataBtn.setOnClickListener(this)
        moveObjectBtn.setOnClickListener(this)
        dialBtn.setOnClickListener(this)
        resultBtn.setOnClickListener(this)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            hasilTv.text = result
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_kalkulasi -> {
                val panjang = panjangEt.text.toString().trim()
                val lebar = lebarEt.text.toString().trim()
                val tinggi = tinggiEt.text.toString().trim()

                if (panjang.isEmpty()) {
                    panjangEt.error = "Filed ini tidak boleh kosong!"
                } else if (lebar.isEmpty()) {
                    lebarEt.error = "Filed ini tidak boleh kosong!"

                } else if (tinggi.isEmpty()) {
                    tinggiEt.error = "Filed ini tidak boleh kosong!"

                } else {
                    val volume = panjang.toDouble() * lebar.toDouble() * tinggi.toDouble()
                    hasilTv.text = volume.toString()
                }
            }

            R.id.btn_pindah -> {
                val moveIntent = Intent(this@MainActivity , MoveActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_pindah_data -> {
                val moveWithDataIntent = Intent(this@MainActivity, MoveActivityData::class.java)
                moveWithDataIntent.putExtra(MoveActivityData.EXTRA_NAME, "Rialga Corp")
                moveWithDataIntent.putExtra(MoveActivityData.EXTRA_AGE, 23)
                startActivity(moveWithDataIntent)
            }
            R.id.btn_pindah_object -> {
                val person = Person(
                    "DicodingAcademy",
                    5,
                    "academy@dicoding.com",
                    "Bandung"
                )
                val moveWithObjectIntent = Intent(this@MainActivity, MainActivityMoveObject::class.java)
                moveWithObjectIntent.putExtra(MainActivityMoveObject.EXTRA_PERSON, person)
                startActivity(moveWithObjectIntent)
            }
            R.id.btn_dial -> {
                val phoneNumber = "081210841382"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }
            R.id.btn_pindah_result -> {
                val moveForResultIntent = Intent(this@MainActivity, MainActivityMoveResult::class.java)
                startActivityForResult(moveForResultIntent, REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == MainActivityMoveResult.RESULT_CODE) {
                val selectedValue = data?.getIntExtra(MainActivityMoveResult.EXTRA_SELECTED_VALUE, 0)
                intentHasilTv.text = "Hasil : $selectedValue"
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, hasilTv.text.toString())
    }
}

