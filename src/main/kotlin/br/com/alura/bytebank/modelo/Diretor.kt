package br.com.alura.bytebank.modelo

class Diretor (nome: String, cpf: String, salario: Double,
               senha: Int, val plr: Double
) : FuncionarioAdmin(nome = nome, cpf = cpf, salario = salario, senha = senha){

    override val bonicacao: Double
        get() {
            println("Bonificacao modelo.Diretor")
            return salario + plr
        }
}