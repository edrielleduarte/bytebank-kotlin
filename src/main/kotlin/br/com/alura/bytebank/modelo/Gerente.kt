package br.com.alura.bytebank.modelo

import br.com.alura.bytebank.modelo.FuncionarioAdmin

class Gerente (nome: String, cpf: String, salario: Double,
               senha: Int
) : FuncionarioAdmin(nome = nome, cpf = cpf, salario = salario, senha = senha) {

    override val bonicacao: Double
        get() {
            println("Bonificacao modelo.Gerente")
            return salario
        }


}