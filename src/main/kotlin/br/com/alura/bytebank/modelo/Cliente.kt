package br.com.alura.bytebank.modelo

class Cliente (var nome: String, cpf: String, var endereco: Endereco = Endereco(), private val senha: Int): Autenticavel {
    override fun autentifica(senha: Int): Boolean {
         if(this.senha == senha){
              return true
         }
            return false
    }
}