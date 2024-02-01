package com.elisson.elissuhfoods

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.elisson.elissuhfoods.databinding.ActivityDetailsRequestsBinding
import com.elisson.elissuhfoods.databinding.ActivityThanksBinding

class ActivityThanks : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityThanksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThanksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btBackHome.setOnClickListener(this)
    }
    override fun onClick(viewBUtton: View) {
        if (viewBUtton.id == R.id.btBackHome) {
            backHome()
        }
    }

    fun backHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("flagFinishRequest", true)
        startActivity(intent)
        finish()
    }

}