package com.example.profnotes.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.profnotes.MainActivity
import com.example.profnotes.data.model.util.ResponseWrapper
import com.example.profnotes.databinding.ActivitySplashScreenBinding
import com.example.profnotes.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()


    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        // setContentView(binding.root)

        lifecycleScope.launch{
            viewModel.note.collectLatest {
                withContext(Dispatchers.Main){
                    when(it){
                       is ResponseWrapper.Idle -> {}
                       is ResponseWrapper.Error -> {
                           Log.e("Error", "${it.code}")
                       }
                        is ResponseWrapper.Success -> {
                            Log.e("Success", "${it.body}")
                        }
                    }
                }
            }
        }
        viewModel.getNote()

        Toast.makeText(this,viewModel.getIsFirstEnter().toString(),Toast.LENGTH_SHORT).show()
        viewModel.setIsFirstEnter(false)

        //  Handler(Looper.getMainLooper()).postDelayed({
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        //  }, 3000)

    }
}