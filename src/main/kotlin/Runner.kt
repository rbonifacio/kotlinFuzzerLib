package br.unb.cic.fuzzer

import java.io.File
import java.io.StringReader
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
        val proc = Runtime.getRuntime().exec("echo \"$inp\" | $program")
        proc.waitFor(10, TimeUnit.SECONDS)
        val exitValue = proc.exitValue()
        val output = proc.inputStream.bufferedReader().readText()
        return Pair(exitValue, output)
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


