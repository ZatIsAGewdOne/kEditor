package com.edvardas.amf3

class AmfXml(var isXmlDocument: Boolean = false) : AmfString() {
    override fun equals(`val`: AmfValue?): Boolean {
        if (`val` is AmfXml && `val`.type == type) {
            val xml: AmfXml = `val`
            return xml.value == value
        }
        return false
    }

    override fun equals(other: Any?): Boolean {
        return if (other is AmfValue) equals(other) else false
    }

    override val type: AmfType
        get() = if (isXmlDocument) AmfType.XmlDoc else AmfType.Xml

    override fun toString(): String {
        val buf = StringBuilder()
        if (isXmlDocument) buf.append("XMLDoc[") else buf.append("XML[")
        buf.append(value)
            .append("]")
        return buf.toString()
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + isXmlDocument.hashCode()
        return result
    }
}