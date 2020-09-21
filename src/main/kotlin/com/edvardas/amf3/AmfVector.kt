package com.edvardas.amf3

abstract class AmfVector<E> : ArrayList<E>, AmfValue {

    open class Double : AmfVector<AmfDouble>() {
        override val type: AmfType
            get() = AmfType.VectorDouble

        override fun toString(): String {
            val buf = StringBuilder()
            buf.append("VectorDouble{")
            var first = true
            this.forEach {
                if (!first) buf.append(",")
                first = false
                buf.append(it.value)
            }
            buf.append("}")
            return buf.toString()
        }
    }

    open class Generic(var typeName: String = "*") : AmfVector<AmfValue>() {
        override val type: AmfType
            get() = AmfType.VectorGeneric

        override fun toString(): String {
            val buf = StringBuilder()
            buf.append("Vector{")
            var first = true
            this.forEach {
                if (!first) buf.append(",")
                first = false
                buf.append(it)
            }
            buf.append("}")
            return buf.toString()
        }
    }

    open class Integer : AmfVector<AmfInteger>() {
        override val type: AmfType
            get() = AmfType.VectorInt

        override fun toString(): String {
            val buf = StringBuilder()
            buf.append("VectorInt{")
            var first = true
            this.forEach {
                if (!first) buf.append(",")
                first = false
                buf.append(it.value)
            }
            buf.append("}")
            return buf.toString()
        }
    }

    open class UnsignedInteger : AmfVector<AmfInteger>() {
        override val type: AmfType
            get() = AmfType.VectorUInt

        override fun toString(): String {
            val buf = StringBuilder()
            buf.append("VectorUInt{")
            var first = true
            this.forEach {
                if (!first) buf.append(",")
                first = false
                buf.append(it.unsignedValue)
            }
            buf.append("}")
            return buf.toString()
        }
    }

    var capacity: Int
    var isFixedLength: Boolean = false
        set(value) {
            field = value
            if (value && capacity < 0) {
                capacity = size
            }
        }

    constructor() {
        capacity = -1
        isFixedLength = false
    }

    constructor(size: Int) {
        capacity = size
    }

    override fun add(element: E): Boolean {
        if (size + 1 > capacity) {
            throw UnsupportedOperationException("This vector is fixed length and cannot contain more than $capacity entries.")
        }
        return super.add(element)
    }

    override fun equals(value: AmfValue?): Boolean {
        if (value?.type == type) {
            val vec = value as AmfVector<*>
            if (vec.size == this.size && vec.isFixedLength == isFixedLength) {
                for (i in 0 until size) {
                    if (this[i] != vec[i]) {
                        return false
                    }
                }
                return true
            }
        }
        return false
    }

    override fun equals(other: Any?): Boolean {
        return if (other is AmfValue) equals(other) else false
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + capacity
        result = 31 * result + isFixedLength.hashCode()
        return result
    }
}