import br.com.alura.bytebank.modelo.Endereco

fun main() {
    var enderecoNulo: Endereco? = Endereco("Rua dom pedro ll")
    println(enderecoNulo?.logradouro?.length?.toBigDecimal())

    enderecoNulo?.let {
        println(it.logradouro)
        val tamanhoComplemento: Int = it.complemento?.length ?: 0
        println(tamanhoComplemento)
    }
}

