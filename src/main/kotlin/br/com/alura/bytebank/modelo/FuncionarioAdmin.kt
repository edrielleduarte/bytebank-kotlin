package br.com.alura.bytebank.modelo

import br.com.alura.bytebank.modelo.Autenticavel
import br.com.alura.bytebank.modelo.Funcionario

abstract class FuncionarioAdmin (nome: String, cpf: String, salario: Double,
                                 protected val senha: Int): Funcionario(nome = nome, cpf = cpf, salario = salario), Autenticavel {

    override fun autentifica(senha: Int): Boolean{
        if(this.senha == senha){
            return true

        }
        return false
    }

}