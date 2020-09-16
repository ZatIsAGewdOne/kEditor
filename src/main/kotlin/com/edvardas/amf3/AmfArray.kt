package com.edvardas.amf3

class AmfArray : AmfValue {
    val associative = LinkedHashMap<String, AmfValue>()
    val dense = ArrayList<AmfValue>()

    val add = fun(value: AmfValue) {
        dense.add(value)
    }

    val clear = fun() {
        dense.clear()
        associative.clear()
    }

    override fun equals(value: AmfValue?): Boolean {
        if (value is AmfArray) {
            val arr: AmfArray = value
            if (arr.dense.size == dense.size && arr.associative.size == associative.size) {
                return arr.dense == dense && arr.associative == associative
            }
        }
        return false
    }

    override fun equals(other: Any?): Boolean {
        return if (other is AmfValue) equals(other) else false
    }

    operator fun get(index: Int): AmfValue {
        return dense[index]
    }

    operator fun get(key: String): AmfValue? {
        return associative[key]
    }

    val associativeSize: Int
        get() = associative.size

    val denseSize: Int
        get() = dense.size

    override val type: AmfType
        get() = AmfType.Array

    val keys: Set<String>
        get() = associative.keys

    operator fun set(key: String, value: AmfValue): AmfValue? {
        return associative.put(key, value)
    }

    fun remove(index: Int): AmfValue {
        return dense.removeAt(index)
    }

    fun remove(key: String): AmfValue? {
        return associative.remove(key)
    }

    val size: Int
        get() = dense.size + associative.size

    override fun toString(): String {
        val buf = StringBuilder()
        buf.append("Array{")
        var first = true
        associative.keys.forEach {
            if (!first) buf.append(",")
            first = false
            buf.append(it)
                .append("=")
                .append(associative[it])
        }
        dense.forEach {
            if (!first) buf.append(",")
            first = false
            buf.append(it)
        }
        buf.append("}")
        return buf.toString()
    }

    override fun hashCode(): Int {
        var result = associative.hashCode()
        result = 31 * result + dense.hashCode()
        return result
    }
}