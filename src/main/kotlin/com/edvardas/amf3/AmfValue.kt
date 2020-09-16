package com.edvardas.amf3

interface AmfValue {
    fun equals(value: AmfValue?): Boolean

    val type: AmfType
}