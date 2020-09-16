package com.edvardas.amf3

abstract class AmfPrimitive<T> : AmfValue {
    var value: T? = null
        set(value) {
            if (value == null) {
                throw UnsupportedOperationException("Primitive value cannot be null")
            }
            field = value
        }

    internal constructor(value: T) {
        this.value = value
    }

    override fun equals(`val`: AmfValue?): Boolean {
        if (`val` is AmfPrimitive<*> && `val`.type == type) {
            return value == `val`.value
        }
        return false
    }

    override fun equals(other: Any?): Boolean {
        if (other is AmfValue) {
            return equals(other)
        }
        return false
    }

    override fun hashCode(): Int {
        return value?.hashCode() ?: 0
    }
}