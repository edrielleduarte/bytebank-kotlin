import br.com.alura.bytebank.exception.SaldoInsuficienteException
import br.com.alura.bytebank.modelo.*

fun testaContasDiferentes() {
    val nome = "Edrielle"
    println(nome)


    val contaCorrente: Conta = ContaCorrente(Cliente("Alex", "",Endereco("Rua Dom Pedo II"),3, ), 1000)

    val fran = Cliente("Fran", "", Endereco("Rua Dom Pedo III"),2)
    val contaPoupanca: Conta = ContaPoupanca(fran, 1001)

    contaCorrente.deposita(1000.0)
    contaPoupanca.deposita(1000.0)

    println("Saldo corrente: ${contaCorrente.saldo}")
    println("Saldo pupanca: ${contaPoupanca.saldo}")

    contaCorrente.saca(100.0)
    contaPoupanca.saca(100.0)

    println("Apos saque conta corrente: ${contaCorrente.saldo}")
    println("Apos saque conta poupanca: ${contaPoupanca.saldo}")

    contaCorrente.transferencia(100.0, contaPoupanca, 2)
    println("Saldo corrente após transferir para poupança: ${contaCorrente.saldo}")
    println("Saldo poupança apos transferencia: ${contaPoupanca.saldo}")
    println("endereco do titular ${contaCorrente.titular.endereco.logradouro}")
    println("endereco do titular ${contaCorrente.saldo}")

    try {

        contaCorrente.transferencia(100.0, contaPoupanca, 3)
        println("Trasnferencia sucedida")
    } catch (e: SaldoInsuficienteException){
        println("Falha na trasnferencia")
    }
}