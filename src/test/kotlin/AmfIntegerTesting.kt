import com.edvardas.amf3.AmfInteger
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.math.roundToInt

@RunWith(Parameterized::class)
class AmfIntegerTesting(private val value: Int) {
    companion object {
        @Parameterized.Parameters
        @JvmStatic
        fun data(): Collection<*> {
            return listOf(
                arrayOf(Math.PI.roundToInt()),
                arrayOf(1234),
                arrayOf((Math.random() * Int.MAX_VALUE).roundToInt()),
                arrayOf(4715)
            )
        }
    }

    @Test
    fun `testing amf int creation`() {
        val amf = AmfInteger(value)
        assertEquals(amf.value, value)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `testing amf int primitive being null`() {
        AmfInteger(value).value = null
    }

    @Test
    fun `testing if amf int value exists`() {
        assertNotNull(AmfInteger(value).value)
    }

    @Test
    fun `testing amf int string value`() {
        assertEquals(AmfInteger(value).toString(), "Int[$value]")
    }

    @Test
    fun `testing amf unsigned value`() {
        assertEquals(AmfInteger(value).unsignedValue, value.toLong())
    }

    @Test
    fun `testing setting unsigned value`() {
        val amf = AmfInteger()
        amf.setUnsignedValue(value.toLong())
        assertTrue(amf.value == value)
    }
}