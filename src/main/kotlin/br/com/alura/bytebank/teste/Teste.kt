class Teste (val nome: String){
    fun mostrar(){
        println(this.nome)
    }
}

open class Cons(val nome: String, val car: String){
    var new: Boolean? = null
    var colour: String = ""
    constructor(nome: String, car: String, new: Boolean): this(nome, car){
        this.new = new
    }
    constructor(nome: String, car: String, new: Boolean, colour: String): this(nome, car, new){
        this.colour = colour
    }
    fun mostraValor() {
        println("Os valores são: ${this.nome}, ${this.car}, ${this.new
        }, ${this.colour}")
    }
}

class fi(nome: String, car: String, new: Boolean, colour: String): Cons(nome = nome, car = car, new = new, colour = colour){
    
    fun i(){
        super.colour
    }

}
class testeComp(val idade: Int) {
    companion object{
        const val valorTeste = "dri"
    }

    fun mostrar(){
        println("O valor da idade é $idade e o nome $valorTeste")
    }
}