package com.edvardas.amf3

interface Trait {
    val name: String

    val properties: MutableList<String>

    val isExternalizable: Boolean

    val isDynamic: Boolean
}