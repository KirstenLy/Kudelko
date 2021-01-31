package com.example.tinkoff_lab.common

import android.content.Context
import android.content.res.TypedArray
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import android.util.DisplayMetrics

/**
 * Класс-обертка над ApplicationContext для предоставления ресурсов по id
 */
open class ResourceProvider(context: Context) {

    private val mContext = context.applicationContext

    fun getString(@StringRes resId: Int): String = mContext.getString(resId)

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String =
        mContext.getString(resId, *formatArgs)

    fun getStringArray(@ArrayRes resId: Int): Array<out String> =
        mContext.resources.getStringArray(resId)

    fun <T> runWithTypedArray(@ArrayRes resId: Int, action: TypedArray.() -> T): T {
        val typedArray = mContext.resources.obtainTypedArray(resId)
        return typedArray.action()
            .also { typedArray.recycle() }
    }

    fun getColor(@ColorRes resId: Int) = ContextCompat.getColor(mContext, resId)

    fun getDimensionPixelSize(@DimenRes resId: Int) =
        mContext.resources.getDimensionPixelSize(resId)

    fun getQuantityString(@PluralsRes resId: Int, quantity: Int, vararg formatArgs: Any) =
        mContext.resources.getQuantityString(resId, quantity, *formatArgs)

    fun getBoolean(@BoolRes resId: Int) = mContext.resources.getBoolean(resId)

    fun getFont(@FontRes resId: Int) = ResourcesCompat.getFont(mContext, resId)

    fun getDrawable(@DrawableRes resId: Int) =
        ResourcesCompat.getDrawable(mContext.resources, resId, null)

    fun getDisplayMetrics(): DisplayMetrics = mContext.resources.displayMetrics
}