package com.example.tinkoff_lab.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tinkoff_lab.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!isDataHostFragmentAlreadyAdded()) {
            setDataHostFragmentIntoContainer()
        }
    }

    private fun isDataHostFragmentAlreadyAdded() =
        supportFragmentManager.findFragmentByTag(DataHostFragment::class.java.canonicalName) != null

    // TODO: Добавить анимашки + вынести тэг в константы какие - нибудь
    private fun setDataHostFragmentIntoContainer() {
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_main_host, DataHostFragment(), DataHostFragment::class.java.canonicalName)
            .commit()
    }
}
