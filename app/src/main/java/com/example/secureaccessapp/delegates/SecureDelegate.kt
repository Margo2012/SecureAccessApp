package com.example.secureaccessapp.delegates

class SecureDelegate<T>(
    private var value: T,
    private val accessChecker: () -> Boolean
) {
    operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): T? {
        return if (accessChecker()) {
            value
        } else {
            null
            //throw IllegalAccessException("Access denied.")
        }
    }

    operator fun setValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>, newValue: T) {
        if (accessChecker()) {
            value = newValue
        } else {
            throw IllegalAccessException("Access denied to change value.")
        }
    }

}