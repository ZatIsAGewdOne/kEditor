package com.edvardas.amf3

class AmfBoolean(value: Boolean = false) : AmfPrimitive<Boolean>(value) {
    override val type: AmfType
        get() = if (value!!) AmfType.True else AmfType.False

    override fun toString(): String = if (value!!) "Boolean[True]" else "Boolean[False]"
}