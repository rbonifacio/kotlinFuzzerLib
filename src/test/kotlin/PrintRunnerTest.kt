import br.unb.cic.fuzzer.PrintRunner
import br.unb.cic.fuzzer.Result
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PrintRunnerTest {

    @Test
    fun testPrintRun() {
        val runner = PrintRunner()
        assertEquals(Pair("input", Result.UNRESOLVED), runner.run("input"))
    }
}