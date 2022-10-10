package br.com.alura.bytebank.modelo

class Analista (nome: String, cpf: String, salario: Double
) : Funcionario(nome = nome, cpf = cpf, salario = salario) {

    override val bonicacao: Double
        get() {
            println("Bonificacao modelo.Analista")
            return salario * 0.1
        }
}