package com.elisson.elissuhfoods

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.elisson.elissuhfoods.databinding.ActivityDetailsRequestsBinding

class DetailsRequests() : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailsRequestsBinding
    private var nameProductSelected: ArrayList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsRequestsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getNamesProductsSelected()
        window.statusBarColor = Color.parseColor("#E0E0E0")
        binding.btConfirmObs.setOnClickListener(this)
    }

    override fun onClick(viewButton: View) {
        if (viewButton.id == R.id.btConfirmObs) {
            saveObs()
        }
    }

    fun saveObs() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("flagLayoutDetailsRequest", true)
        intent.putExtra("nameProductSelected", nameProductSelected)
        intent.putExtra("txtEditTextObs", binding.editTextDetails.text.toString())
        startActivity(intent)
        finish()
    }

    private fun getNamesProductsSelected() {
        nameProductSelected = intent.extras?.getStringArrayList("nameProductSelected")
        val txtEditTextObsPrev = intent.extras?.getString("txtEditTextObs")
        binding.editTextDetails.setText(txtEditTextObsPrev)
    }

}