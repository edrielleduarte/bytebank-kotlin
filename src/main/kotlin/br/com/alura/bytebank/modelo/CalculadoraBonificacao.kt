package br.com.alura.bytebank.modelo

class CalculadoraBonificacao {
    var total: Double = 0.0
        private  set

    fun registra(funcionario: Funcionario){
        println("Nome: ${funcionario.nome}")
        this.total += funcionario.bonicacao
    }
    fun registra(gerente: Gerente){
        this.total += gerente.bonicacao
    }
    fun registra(diretor: Diretor){
        this.total += diretor.bonicacao
    }
}