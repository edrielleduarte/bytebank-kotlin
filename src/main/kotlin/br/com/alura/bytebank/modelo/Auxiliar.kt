package br.com.alura.bytebank.modelo

class Auxiliar (nome: String, cpf: String, salario: Double
) : Funcionario(nome = nome, cpf = cpf, salario = salario) {
//    (nome = nome, cpf = cpf, salario = salario)
    override val bonicacao: Double
        get() = salario * 0.05
}