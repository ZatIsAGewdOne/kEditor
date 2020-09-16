package com.edvardas.amf3

class AmfInteger(value: Int = 0) : AmfPrimitive<Int>(value) {
    override val type: AmfType
        get() = AmfType.Integer

    val unsignedValue: Long
        get() = value!!.toLong() and 0xFFFFFFFFL

    fun setUnsignedValue(value: Long) {
        this.value = (value and 0xFFFFFFFFL).toInt()
    }

    override fun toString(): String {
        val buf = StringBuilder()
        buf.append("Int[")
            .append(value)
            .append("]")
        return buf.toString()
    }
}