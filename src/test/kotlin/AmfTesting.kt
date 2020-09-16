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
}