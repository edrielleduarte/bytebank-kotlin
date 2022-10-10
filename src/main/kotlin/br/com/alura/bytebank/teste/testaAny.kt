package br.com.alura.bytebank.teste

import br.com.alura.bytebank.modelo.Endereco

fun testaAny() {
    val endereco = Endereco("Rua dom pedro II", 354, cep = "00000-00")
    val endereco2 = Endereco(
        "Rua dom pedro II",
        354,
        cep = "00000-000"
    )
    println(endereco.equals(endereco2))
    println(endereco.hashCode())
    println(endereco2.hashCode())
}
