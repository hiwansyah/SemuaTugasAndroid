package com.example.jetpacknavigation.fakeuserexersize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpacknavigation.R
import com.example.jetpacknavigation.databinding.ActivityUsersBinding

class UsersActivity : AppCompatActivity() {
    private val binding by lazy { ActivityUsersBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}