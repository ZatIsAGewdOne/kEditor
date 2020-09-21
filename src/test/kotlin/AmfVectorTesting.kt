import com.edvardas.amf3.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.math.roundToInt

class AmfVectorTesting {
    private val integerVec = object : AmfVector.Integer() {
        init {
            capacity = 5
            isFixedLength = true
        }
    }
    private val doubleVec = object : AmfVector.Double() {
        init {
            isFixedLength = true
            capacity = 10
        }
    }
    private val valueVec = object : AmfVector.Generic() {
        init {
            isFixedLength = true
            capacity = 10
        }
    }
    private val unsignedVec = object : AmfVector.UnsignedInteger() {
        init {
            isFixedLength = true
            capacity = 5
        }
    }

    private val amfIntegerVec = object : AmfVector<AmfInteger>(10) {
        init {
            isFixedLength = false
        }

        override val type: AmfType
            get() = AmfType.VectorInt

    }

    private val collectionOfVectors = HashSet<AmfVector<*>>()

    @Before
    fun setup() {
        for (i in 0 until 5) {
            integerVec.add(AmfInteger(i * 5))
            doubleVec.add(AmfDouble(i * 5.0))
            unsignedVec.add(AmfInteger(i * 10))
        }
        amfIntegerVec.add(AmfInteger())
        amfIntegerVec.add(AmfInteger())
        valueVec.add(integerVec)
        valueVec.add(doubleVec)
        valueVec.add(unsignedVec)
        collectionOfVectors.add(integerVec)
        collectionOfVectors.add(doubleVec)
        collectionOfVectors.add(unsignedVec)
        collectionOfVectors.add(valueVec)
        collectionOfVectors.sortedBy { it.capacity }
    }

    @Test
    fun `testing vector types`() {
        assertTrue(integerVec.type == AmfType.VectorInt)
        assertTrue(doubleVec.type == AmfType.VectorDouble)
        assertTrue(valueVec.type == AmfType.VectorGeneric)
        assertTrue(unsignedVec.type == AmfType.VectorUInt)
    }

    @Test
    fun `testing vector outputs`() {
        assertEquals(integerVec.toString(), "VectorInt{0,5,10,15,20}")
        assertEquals(doubleVec.toString(), "VectorDouble{0.0,5.0,10.0,15.0,20.0}")
        assertEquals(unsignedVec.toString(), "VectorUInt{0,10,20,30,40}")
        assertEquals(valueVec.toString(), "Vector{VectorInt{0,5,10,15,20},VectorDouble{0.0,5.0,10.0,15.0,20.0},VectorUInt{0,10,20,30,40}}")
        assertEquals(amfIntegerVec.toString(), "[Int[0], Int[0]]")
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `testing vector size exception`() {
        val testVec = AmfVector.Generic()
        testVec.add(AmfArray())
    }

    @Test
    fun `testing vector equality`() {
        assertFalse(integerVec.equals(doubleVec))
        val anotherIntVec = object : AmfVector.Integer() {
            init {
                isFixedLength = true
                capacity = 5
            }
        }
        anotherIntVec.add(AmfInteger())
        anotherIntVec.add(AmfInteger(5))
        anotherIntVec.add(AmfInteger(10))
        anotherIntVec.add(AmfInteger(15))
        anotherIntVec.add(AmfInteger(20))
        assertTrue(integerVec.equals(anotherIntVec))
        anotherIntVec[4] = AmfInteger(18)
        assertFalse(integerVec.equals(anotherIntVec))
        assertFalse(doubleVec.equals(15))
    }
}