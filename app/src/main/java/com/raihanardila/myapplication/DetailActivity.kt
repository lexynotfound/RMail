package com.raihanardila.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.raihanardila.myapplication.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val SHARE_REQUEST_CODE = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Back to HomeFragment
        binding.BackButton.setOnClickListener{
            //Create an Intent to Start HomeFragment
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        try {
            //Mengambil data yang di kirimkan dari HomeFragment
            val mail = intent.getParcelableExtra<Mail>("key_mail")
            if (mail != null) {
                // Menampilkan data di UI
                binding.tvItemName.text = mail.name
                binding.textViewSubject.text = mail.subject
                binding.textViewContent.text = mail.description
                // Ganti dengan cara menampilkan gambar sesuai data mail
                binding.imgItemPhoto.setImageResource(mail.photo)
            } else {
                Log.e("DetailActivity", "Error Mail data is Null")
            }
        } catch (e: Exception) {
            // Tangani error
            Log.e("DetailActivity", "Error: ${e.message}")
            e.printStackTrace()
        }

        // Mengatur onClickListener untuk tombol reply
        binding.buttonReply.setOnClickListener {
            // Ambil isi TextView untuk diposting
            val subject = binding.textViewSubject.text.toString()
            val content = binding.textViewContent.text.toString()

            // Buat pesan yang akan dibagikan
            val message = "$subject\n$content"

            // Buat Intent untuk berbagi pesan
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, message)

            // Mulai aktivitas untuk berbagi pesan dengan requestCode
            startActivityForResult(
                Intent.createChooser(shareIntent, "Share via"), SHARE_REQUEST_CODE
            )
        }
    }

    // Menangani hasil dari aktivitas berbagi
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SHARE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // Tampilkan notifikasi bahwa berbagi berhasil
                Toast.makeText(this, "Shared successfully", Toast.LENGTH_SHORT).show()
            }
        }

    }
}