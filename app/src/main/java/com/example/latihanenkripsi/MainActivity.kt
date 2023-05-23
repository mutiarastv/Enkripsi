package com.example.latihanenkripsi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tempatData = getPreferences(Context.MODE_PRIVATE)
        findViewById<Button>(R.id.btn_simpan).setOnClickListener{
            var nama = findViewById<EditText>(R.id.et_nama).text.toString()
            if (nama.isEmpty()){
                Toast.makeText( this, "masukkan nama Anda",
                Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val simpanNama = enskripsiData(nama)
            val simpanData = tempatData.edit()
            simpanData.putString("nama", simpanNama)
            simpanData.apply()
            Toast.makeText(
                this,
                "Nama Anda Tersimpan",
                Toast.LENGTH_SHORT
            ).show()
            kosongkanText()
        }

        findViewById<Button>(R.id.btn_panggil).setOnClickListener{
            var panggilNama = tempatData.getString("nama",null)
            val panggilHasil = deskripsiData(panggilNama.toString())
            findViewById<TextView>(R.id.tv_hasil).text = "$panggilHasil \n $panggilNama"
        }
    }
    private fun enskripsiData(namaEnkripsi: String): String{
        val enkripsi = android.util.Base64.encode(
            namaEnkripsi.toByteArray(),
            android.util.Base64.DEFAULT
        )
        return String(enkripsi)
    }
    private fun kosongkanText(){
        findViewById<EditText>(R.id.et_nama).setText("")
        findViewById<TextView>(R.id.tv_hasil).setText("")
    }
    private fun deskripsiData(namaEnkripsi: String): String{
        val enkripsi = android.util.Base64.decode(
            namaEnkripsi.toByteArray(),
            android.util.Base64.DEFAULT
        )
        return String(enkripsi)
    }
}