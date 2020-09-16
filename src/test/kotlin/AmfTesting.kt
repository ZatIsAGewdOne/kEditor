import com.edvardas.amf3.AmfType
import com.edvardas.amf3.SimpleTrait
import com.edvardas.amf3.Trait
import com.edvardas.amf3.UnexpectedDataException
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
        assertEquals(AmfType[10], AmfType.Object)
    }
}