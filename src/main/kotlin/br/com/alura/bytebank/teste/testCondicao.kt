fun testCondicao(saldo: Double) {

    when {
        saldo > 0.0 -> {
            println("conta positiva")
        }
        saldo == 0.0 -> {
            println("modelo.Conta negativada")
        }
        else -> {
            println("Sem saldo")
        }
    }
}