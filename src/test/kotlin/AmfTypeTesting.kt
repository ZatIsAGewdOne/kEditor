import com.edvardas.amf3.AmfType
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class AmfTypeTesting(private val id: Int, private val result: AmfType) {

    companion object {
        @Parameterized.Parameters
        @JvmStatic
        fun data(): Collection<*> {
            return listOf(
                arrayOf(0, AmfType.Undefined), arrayOf(8, AmfType.Date),
                arrayOf(1, AmfType.Null), arrayOf(9, AmfType.Array),
                arrayOf(2, AmfType.False), arrayOf(10, AmfType.Object),
                arrayOf(3, AmfType.True), arrayOf(11, AmfType.Xml),
                arrayOf(4, AmfType.Integer), arrayOf(12, AmfType.ByteArray),
                arrayOf(5, AmfType.Double), arrayOf(13, AmfType.VectorInt),
                arrayOf(6, AmfType.String), arrayOf(14, AmfType.VectorUInt),
                arrayOf(7, AmfType.XmlDoc), arrayOf(15, AmfType.VectorDouble),
                arrayOf(16, AmfType.VectorGeneric), arrayOf(17, AmfType.Dictionary)
            )
        }
    }

    @Test
    fun `testing amf types`() {
        assertEquals(AmfType[id], result)
    }
}