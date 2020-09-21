import com.edvardas.amf3.AmfArray
import com.edvardas.amf3.AmfDouble
import com.edvardas.amf3.AmfInteger
import com.edvardas.amf3.AmfType
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.Comparator

class AmfArrayTesting {
    private val arr = AmfArray()
    private val another = AmfArray()
    private val amfArrCollection = object : HashSet<AmfArray>() {
        init {
            add(arr)
            add(another)
        }
    }

    @Before
    fun setup() {
        arr.add.invoke(AmfDouble())
        arr.add(AmfInteger())
        arr["Double"] = AmfDouble()
        arr["Integer"] = AmfInteger()
    }

    @Test
    fun `testing adding values to array`() {
        assertEquals(arr.size, 4)
    }

    @Test
    fun `testing removing values from array`() {
        arr.remove(0)
        assertEquals(arr.size, 3)
        arr.remove("Double")
        assertEquals(arr.size, 2)
    }

    @Test
    fun `testing clearing array`() {
        arr.clear()
        assertEquals(arr.size, 0)
    }

    @Test
    fun `testing amf array equality`() {
        assertFalse(arr.equals(another))
        another.add(AmfDouble())
        another.add(AmfInteger())
        another["Double"] = AmfDouble()
        another["Integer"] = AmfInteger()
        assertTrue(arr.equals(another))
    }

    @Test
    fun `testing amf equality`() {
        assertFalse(arr.equals(5))
    }

    @Test
    fun `testing getting arr values`() {
        assertEquals(arr[0], AmfDouble())
        assertEquals(arr["Double"], AmfDouble())
    }

    @Test
    fun `testing arr sizes`() {
        assertEquals(arr.associativeSize, 2)
        assertEquals(arr.denseSize, 2)
        arr.remove(0)
        assertEquals(arr.denseSize, 1)
    }

    @Test
    fun `testing amf arr type`() {
        assertTrue(arr.type == AmfType.Array)
    }

    @Test
    fun `testing amf arr string value`() {
        assertEquals(arr.toString(), "Array{Double=Double[0.0],Integer=Int[0],Double[0.0],Int[0]}")
        arr.remove("Integer")
        assertEquals(arr.toString(), "Array{Double=Double[0.0],Double[0.0],Int[0]}")
    }

    @Test
    fun `testing associative collection keys`() {
        assertEquals(arr.keys.size, 2)
        arr.clear()
        assertEquals(arr.keys.size, 0)
    }

    @Test
    fun `testing hash sorting`() {
        val amfarr = amfArrCollection.toMutableList()
        amfarr.sortWith(Comparator.comparing { it.size })
        assertEquals(amfarr[0], arr)
    }
}