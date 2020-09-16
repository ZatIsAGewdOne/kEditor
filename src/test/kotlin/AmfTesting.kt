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

    @Test
    fun `testing amf boolean`() {
        val bool = AmfBoolean()
        assertTrue(bool.value == false)
        assertEquals(bool.type, AmfType.False)
        assertEquals(bool.toString(), "Boolean[False]")
    }

    @Test
    fun `testing amf date`() {
        val date = AmfDate(912435124900.0)
        assertEquals(date.toString(), "Date[1998-11-30T14:12:04.900Z]")
        assertEquals(date.type, AmfType.Date)
    }

    @Test
    fun `testing amf string`() {
        val str = AmfString()
        val another = AmfString("Hello World")
        assertFalse(str.equals(another))
        assertNotNull(str.value)
        assertEquals(another.toString(), "String[Hello World]")
        assertFalse(another.equals(25))
        assertEquals(str.type, AmfType.String)
    }

    @Test
    fun `testing amf xml`() {
        val xml = AmfXml()
        val another = AmfXml()
        xml.value = "Hello World"
        assertEquals(xml.equals(another), false)
        assertEquals(xml.toString(), "XML[Hello World]")
        assertTrue(!xml.isXmlDocument)
    }

    @Test
    fun `testing amf xml as xmldoc`() {
        val xml = AmfXml(true)
        xml.value = "This is an xml doc"
        assertTrue(xml.isXmlDocument)
        assertEquals(xml.toString(), "XMLDoc[This is an xml doc]")
    }

    @Test
    fun `testing adding to array`() {
        val arr = AmfArray()
        val str = AmfString()
        arr.add(str)
        assertEquals(arr.denseSize, 1)
        arr.clear()
        assertEquals(arr.denseSize, 0)
    }

    @Test
    fun `testing more amf array`() {
        val arr = AmfArray()
        val int = AmfInteger(2)
        val d = AmfDouble(3.14)
        arr.add(int)
        arr.add(d)
        arr["integer"] = int
        arr["double"] = d
        assertEquals(arr.size, 4)
        assertEquals(arr.associativeSize, 2)
        val keys = arr.keys
        assertTrue(keys.contains("integer"))
        assertEquals(arr.toString(), "Array{integer=Int[2],double=Double[3.14],Int[2],Double[3.14]}")
        assertEquals(arr[1], AmfDouble(3.14))
        assertEquals(arr["integer"], AmfInteger(2))
        assertFalse(arr.equals(AmfArray()))
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `testing remove elements`() {
        val str = AmfString("Hello World")
        val arr = AmfArray()
        arr.add(str)
        arr["string"] = str
        arr.remove(0)
        arr.remove("string")
        assertEquals(arr.size, 0)
        arr.remove(5)
    }
}