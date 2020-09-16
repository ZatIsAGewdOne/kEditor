package com.edvardas.amf3

enum class AmfType(val id: Int) {
    Array(0x09),
    ByteArray(0x0C),
    Date(0x08),
    Dictionary(0x11),
    Double(0x05),
    False(0x02),
    Integer(0x04),
    Null(0x01),
    Object(0x0A),
    String(0x06),
    True(0x03),
    Undefined(0x00),
    VectorDouble(0x0F),
    VectorGeneric(0x10),
    VectorInt(0x0D),
    VectorUInt(0x0E),
    Xml(0x0B),
    XmlDoc(0x07);

    companion object {
        @JvmStatic
        operator fun get(id: Int): AmfType? {
            values().forEach { if (it.id == id) return it }
            return null
        }
    }
}