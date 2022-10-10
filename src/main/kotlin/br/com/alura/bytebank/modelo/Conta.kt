package br.com.alura.bytebank.modelo

import br.com.alura.bytebank.exception.FalhaAutenticacaoExcepction
import br.com.alura.bytebank.exception.SaldoInsuficienteException


abstract class Conta(
    var titular: Cliente,
    val numero: Int = 0
): Autenticavel {
    var saldo = 0.0
        protected set

    companion object Contador {
        var total = 0
            private set
    }

    init {
        println("Criando conta")
        total++
    }

    override fun autentifica(senha: Int): Boolean {
        return titular.autentifica(senha)
    }
    fun deposita(valor: Double) {
        if (valor > 0) {
            this.saldo += valor
        }
    }

    abstract fun saca(valor: Double)

    fun transferencia(valor: Double, destino: Conta, senha: Int) {
        if(saldo < valor){
            throw SaldoInsuficienteException("O saldo é insuficente valor menor $valor")
        }

        if(!autentifica(senha)){
            throw FalhaAutenticacaoExcepction()
        }

            this.saldo -= valor
            destino.deposita(valor)

    }
}