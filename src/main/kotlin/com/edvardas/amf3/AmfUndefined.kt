package com.edvardas.amf3

/**
 * Associated with AMF undefined type
 */
class AmfUndefined : AmfValue {
    override fun equals(value: AmfValue?): Boolean {
        return value?.type == AmfType.Undefined
    }

    override fun equals(other: Any?): Boolean {
        return if (other is AmfValue) equals(other) else false
    }

    override val type: AmfType
        get() = AmfType.Undefined

    override fun toString(): String = "Undefined[]"

    override fun hashCode(): Int = javaClass.hashCode()
}