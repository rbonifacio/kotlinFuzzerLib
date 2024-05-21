package br.unb.cic.fuzzer

import kotlin.random.Random

abstract class Fuzzer {

    abstract fun fuzz() : String

    fun <a>run(runner: Runner<a>): a {
        return runner.run(fuzz())  // Design Pattern Template Method
    }

    fun <a>runs(runner : Runner<a>, iter : Int = 10) : List<a> {
        var res = emptyList<a>()
        for (i in 0..iter) {
            res = res.plus(run(runner))
        }
        return res
    }
}

class RandomFuzzer(val minLength : Int = 10, val maxLength : Int = 20, val charStart : Int = 32, val charRange : Int = 32) : Fuzzer() {
    override fun fuzz(): String {
        val length = Random.nextInt(minLength, maxLength)
        var res = ""
        for(i in 0..length) {
            val chr = Random.nextInt(charStart, charStart+charRange).toChar()
            res += chr
        }
        return res
    }
}