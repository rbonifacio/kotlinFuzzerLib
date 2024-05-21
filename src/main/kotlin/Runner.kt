package br.unb.cic.fuzzer

import java.util.concurrent.TimeUnit


enum class Result { PASS, FAIL, UNRESOLVED }

interface Runner<a> {
    fun run(inp: String): a
}

class PrintRunner : Runner<Pair<String, Result>> {
    override fun run(inp: String): Pair<String, Result> {
        println(inp)
        return Pair(inp, Result.UNRESOLVED)
    }
}

class ProgramRunner(val program: String, val args: List<String> = emptyList()) : Runner<Pair<Result, String> > {

    fun runProcess(inp: String)  : Pair<Int, String> {
        val parts: Array<String> = arrayOf("/bin/sh", "-c", "echo \"$inp\" | $program")
        val proc = ProcessBuilder(*parts)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        proc.waitFor(60, TimeUnit.SECONDS)
        val code = proc.exitValue()
        val output =  proc.inputStream.bufferedReader().readText()
        return Pair(code, output)
    }

    override fun run(inp: String): Pair<Result, String> {
        val execution = runProcess(inp)
        val outcome = when (execution.first) {
            0 -> Result.PASS
            else -> Result.FAIL
        }
        return Pair(outcome, execution.second)
    }
}


