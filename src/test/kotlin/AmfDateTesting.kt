import com.edvardas.amf3.AmfDate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.time.Instant
import kotlin.math.roundToInt
import kotlin.random.Random

@RunWith(Parameterized::class)
class AmfDateTesting(private val value: Double, private val result: String) {
    companion object {
        @Parameterized.Parameters
        @JvmStatic
        fun data(): Collection<*> {
            return listOf(
                arrayOf(Math.PI, "Date[1970-01-01T00:00:00.003Z]"),
                arrayOf(12.34, "Date[1970-01-01T00:00:00.012Z]"),
                arrayOf(Math.random() * Double.MAX_VALUE, "Date[+292278994-08-17T07:12:55.807Z]"),
                arrayOf(Random(Int.MAX_VALUE).nextDouble(), "Date[1970-01-01T00:00:00Z]"),
                arrayOf(912435124900.0, "Date[1998-11-30T14:12:04.900Z]")
            )
        }
    }

    @Test
    fun `testing amf date values`() {
        assertEquals(AmfDate(value).toString(), result)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `testing setting date value null`() {
        AmfDate(value).value = null
    }

    @Test
    fun `testing date value existing`() {
        assertNotNull(AmfDate(value).value)
    }

    @Test
    fun `testing setting amf date instant`() {
        val d = AmfDate()
        d.setInstant(Instant.ofEpochMilli(value.toLong()))
        assertEquals(d.value, Instant.ofEpochMilli(value.toLong()).toEpochMilli().toDouble())
    }
}