package com.edvardas.amf3

import java.time.Instant

class AmfDate(value: Double = 0.0) : AmfDouble(value) {
    val instant: Instant
        get() = Instant.ofEpochMilli(value!!.toLong())

    fun setInstant(instant: Instant) {
        this.value = instant.toEpochMilli().toDouble()
    }

    override val type: AmfType
        get() = AmfType.Date

    override fun toString(): String {
        val buf = StringBuilder()
        buf.append("Date[")
            .append(instant.toString())
            .append("]")
        return buf.toString()
    }
}