package com.edvardas.amf3

class SimpleTrait(override val name: String, override val isDynamic: Boolean, override val isExternalizable: Boolean, vararg properties: String) : Trait {
    override val properties: MutableList<String> = mutableListOf(*properties)
}