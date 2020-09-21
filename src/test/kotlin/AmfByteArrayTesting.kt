import com.edvardas.amf3.AmfArray
import com.edvardas.amf3.AmfByteArray
import com.edvardas.amf3.AmfType
import com.edvardas.amf3.AmfValue
import org.junit.Assert.*
import org.junit.Test
import java.util.Comparator

class AmfByteArrayTesting {
    private val ba = AmfByteArray()
    private val another = AmfByteArray()
    private val amfBa = object : HashSet<AmfByteArray>() {
        init {
            add(ba)
            add(another)
        }
    }

    @Test
    fun `testing adding to amf byte array`() {
        ba.push("Hello World".toByteArray())
        assertEquals(ba.size, 11)
    }

    @Test
    fun `testing amf byte array capacity`() {
        assertEquals(ba.capacity, 8)
        ba.push("Hello World".toByteArray())
        assertEquals(ba.capacity, 22)
    }

    @Test
    fun `testing clearing byte array`() {
        ba.push("Hello World".toByteArray())
        assertEquals(ba.size, 11)
        ba.clear()
        assertEquals(ba.size, 0)
    }

    @Test
    fun `testing amf byte array equality`() {
        assertTrue(ba.equals(another))
        another.push(51)
        assertFalse(ba.equals(another))
        assertFalse(ba.equals(5))
        assertFalse(ba.equals(AmfArray()))
    }

    @Test
    fun `testing amf backing array`() {
        assertEquals(ba.backingArray.size, ba.capacity)
        ba.push("Hello World".toByteArray())
        assertEquals(ba.backingArray.size, 22)
    }

    @Test
    fun `testing amf byte array type`() {
        assertTrue(ba.type == AmfType.ByteArray)
    }

    @Test
    fun `testing amf byte array emptiness`() {
        assertTrue(ba.isEmpty)
        ba.push("Hello World".toByteArray())
        assertFalse(ba.isEmpty)
    }

    @Test
    fun `testing popping values from amf byte array`() {
        ba.push("Lorem ipsum dolor sit amet, consectetur adipiscing elit".toByteArray())
        assertEquals(ba.toString(), "ByteArray{4c,6f,72,65,6d,20,69,70,73,75,6d,20,64,6f,6c,6f,72,20,73,69,74,20," +
                "61,6d,65,74,2c,20,63,6f,6e,73,65,63,74,65,74,75,72,20,61,64,69,70,69,73,63,69,6e,67,20,65,6c,69,74}")
        assertEquals(ba.size, 55)
        ba.pop()
        assertEquals(ba.size, 54)
        ba.pop(12)
        assertEquals(ba.size, 42)
        assertEquals(ba.toString(), "ByteArray{4c,6f,72,65,6d,20,69,70,73,75,6d,20,64,6f,6c,6f,72,20,73,69,74,20," +
                "61,6d,65,74,2c,20,63,6f,6e,73,65,63,74,65,74,75,72,20,61,64}")
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `testing popping too much`() {
        another.push("Hello".toByteArray())
        another.pop(6)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `testing popping empty array`() {
        ba.pop()
    }

    @Test
    fun `testing amf byte array popTo`() {
        ba.push("Lorem ipsum dolor sit amet, consectetur adipiscing elit".toByteArray())
        assertEquals(another.backingArray[0], 0.toByte())
        assertEquals(another.backingArray[6], 0.toByte())
        ba.popTo(another.backingArray, 2, 6)
        assertEquals(ba.size, 49)
        assertEquals(another.backingArray[0], 0.toByte())
        assertEquals(another.backingArray[6], 105.toByte())
        assertEquals(another.backingArray[2], 103.toByte())
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `testing failing amf byte array popto`() {
        ba.popTo("Hello".toByteArray(), 0, 6)
    }

    @Test
    fun `testing amf byte array to string`() {
        ba.push(5)
        ba.push(51)
        ba.push(127)
        ba.push(14)
        assertEquals(ba.toString(), "ByteArray{05,33,7f,0e}")
    }

    @Test
    fun `testing amf ba push from`() {
        ba.pushFrom("abcdefgh".toByteArray(), 2, 6)
        assertEquals(ba.size, 8)
        assertEquals(ba.toString(), "ByteArray{63,64,65,66,67,68,00,00}")
        ba.pushFrom("ijklmn".toByteArray(), 0, 4)
        assertEquals(ba.toString(), "ByteArray{63,64,65,66,67,68,00,00,69,6a,6b,6c,00,00}")
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `testing amf ba push from out of bounds`() {
        ba.pushFrom("abcd".toByteArray(), 2, 6)
    }

    @Test
    fun `testing amf testing hash code for amf byte array`() {
        amfBa.sortedWith(Comparator.comparing { it.size })
        assertTrue(amfBa.toMutableList()[0] == ba)
    }
}