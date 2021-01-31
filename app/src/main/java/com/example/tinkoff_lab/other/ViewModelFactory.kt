package com.example.tinkoff_lab.other

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import javax.inject.Inject
import javax.inject.Provider

/**
 * Фабрика для получения VM,
 * функции-раширения для работы с Фабрикой VM
 */

/**@SelfDocumented */
class ViewModelFactory<VIEW_MODEL : ViewModel> @Inject constructor(private val viewModel: Provider<VIEW_MODEL>) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <VIEW_MODEL : ViewModel> create(modelClass: Class<VIEW_MODEL>): VIEW_MODEL {
        return viewModel.get() as VIEW_MODEL
    }
}

/**@SelfDocumented */
inline fun <reified VIEW_MODEL : ViewModel> ViewModelFactory<VIEW_MODEL>.get(fragment: Fragment): VIEW_MODEL =
    ViewModelProvider(fragment, this)[VIEW_MODEL::class.java]

/**
 * Получение VM с помощью фабрики [factory]
 */
inline fun <reified VIEW_MODEL : ViewModel> Fragment.withFactory(factory: ViewModelFactory<VIEW_MODEL>): VIEW_MODEL =
    ViewModelProvider(this, factory)[VIEW_MODEL::class.java]

/**
 * Получение VM с помощью фабрики [factory]
 */
inline fun <reified VIEW_MODEL : ViewModel> FragmentActivity.withFactory(factory: ViewModelFactory<VIEW_MODEL>): VIEW_MODEL =
    ViewModelProvider(this, factory)[VIEW_MODEL::class.java]