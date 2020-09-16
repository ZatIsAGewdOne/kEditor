import com.edvardas.amf3.*
import org.junit.Assert.*
import org.junit.Test

class AmfTesting {

    @Test(expected = UnexpectedDataException::class)
    fun `test throwing unexpected data exception`() {
        throw UnexpectedDataException("This is a test throw")
    }

    @Test(expected = UnexpectedDataException::class)
    fun `testing throwing unexpected data exception with no message`() {
        throw UnexpectedDataException()
    }

    @Test
    fun `testing creating trait`() {
        val trait = SimpleTrait("Test trait", isDynamic = true, isExternalizable = true, "These", "are", "trait", "properties")
        assertNotNull(trait)
        assertEquals(trait.name, "Test trait")
        assertEquals(trait.properties.size, 4)
    }

    @Test
    fun `testing getting amf type`() {
        assertNull(AmfType[256])
    }

    @Test
    fun `testing default AmfDouble`() {
        val d = AmfDouble()
        assertEquals(d.value, 0.0)
        assertNotNull(d.value)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `testing primitive value being null`() {
        val d = AmfDouble()
        d.value = null
    }

    @Test
    fun `testing default amf integer`() {
        assertEquals(AmfInteger().value, 0)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `testing amf int primitive being null`() {
        AmfInteger().value = null
    }

    @Test
    fun `testing primitive equality`() {
        val amf1 = AmfInteger(12)
        val amf2 = AmfInteger()
        assertFalse(amf1.equals(amf2))
        amf1.value = 25
        amf2.value = 25
        assertTrue(amf1.equals(amf2))

        val amf3 = AmfDouble()
        assertFalse(amf1.equals(amf3))
        assertFalse(amf3.equals(52))
    }

    @Test
    fun `testing amf undefined`() {
        val undef = AmfUndefined()
        val another = AmfUndefined()
        assertTrue(undef.type == AmfType.Undefined)
        assertTrue(undef == another)
        assertEquals(undef.toString(), "Undefined[]")
    }

    @Test
    fun `testing amf null`() {
        val `null` = AmfNull()
        val another = AmfNull()
        assertTrue(`null`.type == AmfType.Null)
        assertTrue(`null` == another)
        assertEquals(`null`.toString(), "Null[]")
    }
}