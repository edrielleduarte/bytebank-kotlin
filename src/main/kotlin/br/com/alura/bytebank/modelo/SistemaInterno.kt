package br.com.alura.bytebank.modelo

class SistemaInterno {

    fun entra(admin: Autenticavel, senha: Int){
        if(admin.autentifica(senha)){
             println("Bem vindo ao bytebank")
        } else {
            println("Falha na autentificação")

        }
    }

}