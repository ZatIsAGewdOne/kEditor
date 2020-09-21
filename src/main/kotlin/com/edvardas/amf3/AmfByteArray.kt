package com.edvardas.amf3

class AmfByteArray : AmfValue {
    private var data = ByteArray(8)
    var size = 0
        private set

    val capacity: Int
        get() = data.size

    val clear = fun() {
        size = 0
    }

    override fun equals(value: AmfValue?): Boolean {
        if (value is AmfByteArray) {
            val ba: AmfByteArray = value
            return ba.toArray().contentEquals(toArray())
        }
        return false
    }

    override fun equals(other: Any?): Boolean {
        return if (other is AmfValue) equals(other) else false
    }

    internal val backingArray: ByteArray
        get() = data

    override val type: AmfType
        get() = AmfType.ByteArray

    val isEmpty: Boolean
        get() = size == 0

    fun pop(): Byte {
        if (size == 0) throw IndexOutOfBoundsException("Cannot pop values in an empty array")
        return data[--size]
    }

    fun pop(count: Int): ByteArray {
        if (size < count) {
            throw IndexOutOfBoundsException("Cannot pop more values than there are available in array")
        }
        val ret = data.copyOfRange(size - count, size)
        size -= count
        return ret
    }

    val popTo = fun(b: ByteArray, offset: Int, length: Int) {
        if (size < length) {
            throw IndexOutOfBoundsException("Cannot pop more values than there are available in array")
        }
        System.arraycopy(data, size - length, b, offset, length)
        size -= length
    }

    fun push(b: Byte) {
        data[size++] = b
        if (size == data.size) {
            data = data.copyOf(data.size shl 1)
        }
    }

    fun push(b: ByteArray) {
        if (size + b.size >= data.size) {
            val nCap = (size + b.size) shl 1
            data = data.copyOf(nCap)
        }
        System.arraycopy(b, 0, data, size, b.size)
        size += b.size
    }

    val pushFrom = fun(b: ByteArray, offset: Int, length: Int) {
        if (offset + length > b.size) {
            throw IndexOutOfBoundsException("Offset and length exceeds the size of source array")
        }
        if (size + length >= data.size) {
            val nCap = (size + length) shl 1
            data = data.copyOf(nCap)
        }
        System.arraycopy(b, offset, data, size, length)
        size += b.size
    }

    val toArray = fun(): ByteArray {
        return data.copyOf(size)
    }

    override fun toString(): String {
        val buf = StringBuilder()
        buf.append("ByteArray{")
        for (i in 0 until size) {
            if (i != 0) buf.append(",")
            buf.append(String.format("%02x", data[i]))
        }
        buf.append("}")
        return buf.toString()
    }

    override fun hashCode(): Int {
        var result = data.contentHashCode()
        result = 31 * result + size
        return result
    }
}