package com.example.finalproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.finalproject.ViewModel.QuackViewModel
import com.example.finalproject.databinding.ActivityMainBinding
import com.example.finalproject.model.local.QuackDatabase
import com.example.finalproject.model.local.tableQuack
import com.example.finalproject.view.QuackFragment
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, QuackFragment(1))
            .commit()


    }




}