import br.unb.cic.fuzzer.ProgramRunner
import br.unb.cic.fuzzer.Result
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ProgramRunnerTest {

    @Test
    fun run() {
        val runner = ProgramRunner("bc")
        val res = runner.run("4+4")
        assertEquals(Result.PASS, res.first)
        assertEquals("8", res.second.trim())
    }
}