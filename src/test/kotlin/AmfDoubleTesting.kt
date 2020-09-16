import com.edvardas.amf3.AmfDouble
import com.edvardas.amf3.AmfType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.random.Random

@RunWith(Parameterized::class)
class AmfDoubleTesting(private val value: Double) {
    companion object {
        @Parameterized.Parameters
        @JvmStatic
        fun data(): Collection<*> {
            return listOf(
                arrayOf(Math.PI),
                arrayOf(12.34),
                arrayOf(Math.random() * Double.MAX_VALUE),
                arrayOf(Random(Int.MAX_VALUE).nextDouble())
            )
        }
    }

    @Test
    fun `parameterized testing of amf double`() {
        val d = AmfDouble(value)
        assertEquals(d.value, value)
    }

    @Test
    fun `testing if values exist`() {
        val d = AmfDouble(value)
        assertNotNull(d.value)
    }

    @Test
    fun `testing amf double type`() {
        val d = AmfDouble(value)
        assertEquals(d.type, AmfType.Double)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `testing amf double being null`() {
        val d = AmfDouble(value)
        d.value = null
    }

    @Test
    fun `testing amf double string value`() {
        assertEquals(AmfDouble(value).toString(), "Double[$value]")
    }
}