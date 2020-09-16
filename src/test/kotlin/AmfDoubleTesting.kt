import com.edvardas.amf3.AmfDouble
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

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
}