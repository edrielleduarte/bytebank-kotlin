package br.com.alura.bytebank.teste

import br.com.alura.bytebank.modelo.*

fun testaObjects() {
    val fran = object : Autenticavel {
        val nome: String = "Fran"
        val cpf: String = "111.111.111-11"
        val senha: Int = 1000
        override fun autentifica(senha: Int) = this.senha == senha

    }


    val sistemaInterno = SistemaInterno()
    sistemaInterno.entra(fran, 1000)

    println("Nome do cliente ${fran.nome}")


    val alex = Cliente("Alex", "", Endereco("Rua Dom"), 3)
    val contaPoupanca = ContaPoupanca(alex, 1000)
    val contaCorrente = ContaCorrente(alex, 1001)
    println("Total de contas: ${Conta.total}")
}