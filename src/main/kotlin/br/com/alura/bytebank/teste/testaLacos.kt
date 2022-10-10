fun testaLacos() {
    println("Bem vindo ao bytebank")
    var i = 0

    while (i < 5) {

        val titular: String = "Edrielle $i"
        val numeroConta: Int = 1000 + i
        var saldo = i + 10.0
//        var saldo: Double = 0.0

        println("titular $titular")
        println("Numedo da conta $numeroConta")
        println("Numero do saldo $saldo")
        println()

        i++
    }
}
