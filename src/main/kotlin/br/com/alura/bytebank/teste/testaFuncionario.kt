import br.com.alura.bytebank.modelo.Analista
import br.com.alura.bytebank.modelo.CalculadoraBonificacao
import br.com.alura.bytebank.modelo.Diretor
import br.com.alura.bytebank.modelo.Gerente

fun testaFuncionario() {
    val edrielle = Analista("Edrielle", "14289165710", 2000.00)
    println("Nome: ${edrielle.nome}")
    println("CPF: ${edrielle.cpf}")
    println("Valor: ${edrielle.salario}")
    println("Bonificacao ${edrielle.bonicacao}")

    val fran = Gerente("Fran", "45343453453455", 30000.00, 1234)
    println("Fran bonificaçao: ${fran.bonicacao}")
    fran.autentifica(12934)

    val t = Teste("edri")

    val ari = Diretor("Ari", cpf = "001.002.003.04", 4000.0, 1234, 200.0)

    println("O seu nome é: ${ari.nome}")
    println("O seu prl é ${ari.plr}")
    println("O seu cpf é: ${ari.cpf}")
    println("A sua bonificacao é de: ${ari.bonicacao}")
    ari.autentifica(1234)


    val maria = Analista("Maria", "123.234.455.663.11", 2000.0)
    val calculadora = CalculadoraBonificacao()
    calculadora.registra(edrielle)
    calculadora.registra(fran)
    calculadora.registra(ari)
    calculadora.registra(maria)

    println(
        "Total de bonificacao: ${
            calculadora.total
        }"
    )
}

