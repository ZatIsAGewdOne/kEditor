package com.edvardas.amf3

/**
 * Defines a single value that has a simple format. Such as a number or string.
 */
abstract class AmfPrimitive<T> : AmfValue {
    var value: T? = null
        set(value) {
            if (value == null) {
                throw UnsupportedOperationException("Primitive value cannot be null")
            }
            field = value
        }

    /**
     * Creates new primitive with a specified value
     *
     * @param value The value of this primitive
     */
    internal constructor(value: T) {
        this.value = value
    }

    /**
     * Determines if the given object is an AmfValue and equals this value.
     * Makes use of [equals(AmfValue)] to determine equality
     */
    override fun equals(`val`: AmfValue?): Boolean {
        if (`val` is AmfPrimitive<*> && `val`.type == type) {
            return value == `val`.value
        }
        return false
    }

    /**
     * Determines if the given object is an AmfValue and equals this value.
     * Makes use of [equals(AmfValue)] to determine equality
     */
    override fun equals(other: Any?): Boolean {
        return if (other is AmfValue) equals(other) else false
    }

    override fun hashCode(): Int = value?.hashCode() ?: 0
}