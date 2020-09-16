package com.edvardas.amf3

class AmfDouble(value: Double = 0.0) : AmfPrimitive<Double>(value) {
    override val type: AmfType
        get() = AmfType.Double

    override fun toString(): String {
        val buf = StringBuilder()
        buf.append("Double[")
            .append(value)
            .append("]")
        return buf.toString()
    }
}