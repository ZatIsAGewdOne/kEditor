package com.edvardas.amf3

class AmfNull : AmfValue {
    override fun equals(value: AmfValue?): Boolean {
        return value?.type == AmfType.Null
    }

    override fun equals(other: Any?): Boolean {
        return if (other is AmfValue) equals(other) else false
    }

    override val type: AmfType
        get() = AmfType.Null

    override fun toString(): String = "Null[]"

    override fun hashCode(): Int = javaClass.hashCode()
}