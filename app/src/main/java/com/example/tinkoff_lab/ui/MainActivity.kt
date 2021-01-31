package com.example.tinkoff_lab.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tinkoff_lab.R
import com.example.tinkoff_lab.ui.host.DataHostFragment

private val dataHostTag = DataHostFragment::class.java.canonicalName

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)

        if (!isDataHostFragmentAlreadyAdded()) {
            setDataHostFragmentIntoContainer()
        }
    }

    private fun isDataHostFragmentAlreadyAdded() = supportFragmentManager.findFragmentByTag(dataHostTag) != null

    private fun setDataHostFragmentIntoContainer() {
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_main_host, DataHostFragment(), dataHostTag)
            .commit()
    }
}
