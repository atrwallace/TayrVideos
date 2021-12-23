package com.example.tayrvideos

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tayrvideos.adapters.MainAdapter
import com.example.tayrvideos.databinding.ActivityMainBinding
import com.example.tayrvideos.repo.MainRepository
import com.example.tayrvideos.rest.RetrofitService
import com.example.tayrvideos.viewmodel.main.MainViewModel
import com.example.tayrvideos.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = MainAdapter {
        openVideo(it.link)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        binding.recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.livelist.observe(this, Observer { lives ->
            adapter.liveListSetUp(lives)
        })

        viewModel.errorMsg.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLiveDataModel()
    }

    fun openVideo(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}