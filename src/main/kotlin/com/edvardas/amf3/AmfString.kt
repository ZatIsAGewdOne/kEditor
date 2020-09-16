package com.edvardas.amf3

open class AmfString(value: String = "") : AmfPrimitive<String>(value) {
    override fun equals(other: Any?): Boolean {
        return if (other is AmfValue) equals(other) else false
    }

    override fun equals(`val`: AmfValue?): Boolean {
        if (`val` is AmfString) {
            val a = value
            val b = `val`.value
            return a == b
        }
        return false
    }

    override val type: AmfType
        get() = AmfType.String

    override fun toString(): String {
        val buf = StringBuilder()
        buf.append("String[")
            .append(value)
            .append("]")
        return buf.toString()
    }

}