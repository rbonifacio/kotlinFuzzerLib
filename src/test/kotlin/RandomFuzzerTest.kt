import br.unb.cic.fuzzer.ProgramRunner
import br.unb.cic.fuzzer.RandomFuzzer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RandomFuzzerTest {

    @Test
    fun intToChar() {
        assertEquals('A', 65.toChar())
        assertEquals('a', 97.toChar())
    }

    @Test
    fun simpleFuzzer() {
        val fuzzer = RandomFuzzer()

        for (i in 0..10) {
            val s = fuzzer.fuzz()
            assertTrue(s.length >= 10)
            assertTrue(s.length <= 20)
            println(s)
        }
    }

    @Test
    fun catRunner() {
        val cat = ProgramRunner("cat")
        val fuzzer = RandomFuzzer()
        val res = fuzzer.runs(cat)
        print(res)
    }
}