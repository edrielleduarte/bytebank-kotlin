package br.com.alura.bytebank.modelo

abstract class Funcionario (val nome: String, val cpf: String, val salario: Double,
){
    abstract val bonicacao: Double
//        get() {
//            println("Bonificacao modelo.Funcionario")
//            return salario * 0.1
//        }

}