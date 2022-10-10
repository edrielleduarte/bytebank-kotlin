import br.com.alura.bytebank.modelo.*

fun testaAutenticacao() {
    val gerente = Gerente("Edrielle", "111.111.111-12", 1000.0, 1000)
    val diretora = Diretor("Rosa", "222.222.222-22", 2000.0, 2000, 200.0)

    val cliente = Cliente("edri", "333.333.333.22", Endereco("Rua Dom Pedo II"),1234)
    val sistema = SistemaInterno()

    sistema.entra(gerente, 1000)
    sistema.entra(diretora, 2000)
    sistema.entra(cliente, 1234)

}
