package com.example.tinkoff_lab.other

import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.disposables.DisposableContainer

operator fun DisposableContainer.plusAssign(d: Disposable) {
    add(d)
}