import br.unb.cic.fuzzer.ProgramRunner
import br.unb.cic.fuzzer.Result
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ProgramRunnerTest {

    @Test
    fun run() {
        val runner = ProgramRunner("echo \"4+4\" | bc")
        val res = runner.run("echo \"4+4\" | bc")
        assertEquals(res.first, Result.PASS)
        print("****************** ${res.second}")
    }
}