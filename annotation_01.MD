# IMPORTS KOTLIN 

* Link documentação com imports [Imports kotlin](https://kotlinlang.org/docs/packages.html)
Quando queremos importar varios tipos de clases que estão na mesma hierarquia ou seja tenho 5 arquivos e cada um contem
uma classe na qual importo: import modelo.listaDeCompra, import modelo.pessoa, importo essas duas no mesmo arquivo
no kotlin podemos facilitar colocando apenas o `import modelo.*` o * informa/representa as classes naquela pasta ali modelo.


* Link abstract [Link de info abstract](https://www.programiz.com/kotlin-programming/abstract-class)
## Variaveis Globais

Podemos ter o constructor secundario seria assim:

                abstract class Conta(
                    var titular: Cliente,
                    val numero: Int = 0
                    ) {
                    var saldo = 0.0
                    protected set
                    var total = 0
                    
                    constructor(numero: Int, titular: Cliente): this(titular, numero){
                    
                    }
                    fun deposita()

* Temos que alterar a posição dos valores do constructor primario no constructor secundario.
 
So que não faz sentindo pq so queremos a incrementação de execução no momento da construção da classe 
iremos usar o `Init { }` ele tem comportamento do constructor secundario, mas não precisa passar informações do constructor
primario. A classe inicia, o constructor primario é executado depois vem o init

                    init{
                        println("Criando conta")
                        total++
                    }

Para criar variavel global colocamos o contador fora do escopo recebendo 0, tipo:   

                         var totalContas = 0

                         fun main{
                            //body
                         }

                            // Outro arquivo

                         import totalContas

                         class Teste(nome: String) {
                            totalContas++                     
                         }

* Problema da variavel global é que todo mundo tem acesso, nunca é bem vindo usar variavel global

A variavel global ela é uma properties e ela pode ser tambem como private set, apenas o arquivo na qual ela ta sendo declarada
que consegue modificala, logo se eu importar para um outro arquivo, não consigo alterar. Como o arquivo conta que vai ter esse 
contador, onde ira se utilizado, colocamos ele como private no arquivo de contas.

                                No arquivo desejado inserir: var totalContas = 0    
                                                                  private set



## Conhecendo o Object Expressions

Permite criação objetos anonimos que não precisaria de uma representação de uma classe.


                    
                        val fran = object {
                            
                        }
Essa instrução acima, consigo indicar membros, conforme objetos em javascript, é um objeto anonimo, como fica:



                        val fran = object {
                            val nome: String = "Fran"
                            val cpf: String = "111.111.111-11"
                            val senha: Int = 1000
                    
                            fun autentica(senha: Int) = this.senha == senha
                        }
                    
                        println("Nome do cliente ${fran.nome}")


                        // outro exemplo de object anonimo extendendo uma interface

                               val fran = object : Autenticavel {
                                    val nome: String = "Fran"
                                    val cpf: String = "111.111.111-11"
                                    val senha: Int = 1000
                                    override fun autentifica(senha: Int) = this.senha == senha
                            
                                }
                            
                            
                                val sistemaInterno = SistemaInterno()
                                sistemaInterno.entra(fran, 1000)
                            
                                println("Nome do cliente ${fran.nome}")


Quando que é algo interessante?

    Quando você quiser ter uma estrtura de um objeto mas não quer criar uma classe.


## Utilizando o object declarations e companion object
Só que apenas a classe que queremos que faça a edição, consiga fazer a edição, sem permitir que outras classes consigam.
Então é isso que ele vai resolver para nós. Só que nós vamos ver aos poucos o que ele é e como que ele consegue fazer 
isso. Então, vamos lá!

* Seguindo a própria documentação, imediatamente percebemos que aqui no “Object declarations” está indicando que é uma 
maneira fácil de criar um “singleton”. Mas o que é um “singleton”?
Se considerarmos o “singleton” como um padrão de projeto, que é justamente o que está sendo mencionado aqui, é uma 
técnica que permite com que criemos um único objeto e que vai ser mantido desde o começo do programa, até o final.
É algo muito similar ao que vimos ali, em uma “property” global, porque ela tinha o mesmo valor desde o começo e
ela foi mantida ali até o final, considerando as modificações que foram feitas.


Mantem o valor do inicio do programa ate o final Singleton

* Diferença do object expression e o object declarations

O declarations tem o nome tipo: `object Nome` e o expression não pode, ele pode implementar interface, herança ele pode 
ficar no escopo de uma função, diferente do declarations. O declaration ele pode ficar em um arquivo, dentro de classes.

O declaration como a variavel global, ela fica fora do escopo função main, fica no arquivo com o valor setado onde pode 
ser importado, como sera utilizado na classe conta no exemplo:


                                object Contador {
                                         var total = 0
                                }
                                
                                fun main {
                            
                                }

                                // outro arquivo 

                                import Contador
                                
                                class Teste(val nome: String) {
                                    init {
                                        Contador.total++
                                    } 
                                }

Mas podemos deixar mais restrito e seguro, ele pode sim ser mantido em uma clase, vai manter o mesmo comportamento, sem 
ser uma porperties, ele vai ser da classe ao inves da instancia, diferença é que ele vai ser o mesmo valor, independendo 
de quantas instancias. Jogando pra classe:

                abstract class Conta(
                    var titular: Cliente,
                    val numero: Int = 0
                ) {
                    var saldo = 0.0
                    protected set
                    
                    object Contador {
                          var total = 0
                    }
                    
                    init {
                        println("Criando conta")
                        Contador.total++
                    }

Para usar na main ou em outro arquivo vc importa, não da pra instanciar ela como S{Conta.total}, pq ela não faz parte da 
instancia do objeto, ele faz parte da classe, não tem um objeto que usa ele, pra usar ele tem que ser: 
`${Conta.Contador.toal}`.

So que não conseguimos resolver o caso dele ser seguro e mais restrito, podemos colocar modicadores de acesso ou 
visibilade como `private object Contador { var total = 0 }`, não é interessante pq é um dado que queremos fazer leitura.
Podemos dizer que a propriedade do objeto pode ser um private set: 


                    private object Contador {
                            var total = 0
                                private set
                    }

ai podemos exibi-lo novamente para visibilidade, pra escrita não.

Ao fazer o private set não conseguimos acessar na class conta que é um membro, so pode mudar os membros que tem dentro 
do objeto ex:
                
               
                 object Contador {
                        var total = 0
                            private set
                        fun incremento(){
                            total++
                        }
                }

                 init{
                    Contador.incremento()
                 }

Conseguimos realizar o procedimento, mesmo que fizemos o encapsulamento,ainda assim outras contas consegue fazer uso
disso, que é o que a gente não quer, pois queremos que so a classe Conta, utiliza e faça o incremento.

Podemos fazer configurar que esse object declarations para que ele compartilhe o acesso dos seus membros, o tal que faça 
isso `Companion Objects`. Qual é a ideia do “Companion Objects”? É justamente fazer com que o “Object declaration” 
consiga compartilhar os seus membros quando ele está dentro aqui de uma classe. Então ele vai conseguir compartilhar 
o “total” com a nossa classe “Conta”.

                           companion object Contador {
                                 var total = 0
                                     private set
                           }
            
                             init{
                                Contador.total++
                             }

Podemos acessar o total como se ele fosse membro da classe e o total tem o set private, como ele é privado, apenas a 
classe tem acesso a ele.
Se deixasse como protected set no var total ex:

               companion object Contador {
                    var total = 0
                        protected set
               }
Ele ia restringir de acessar o total, de utilizar como: Contador.total++, ai não podemos utilizar.

Utilizar o COntador Contador.total++ ele nem é mais necessario pq ele faz parte do membro da classe, ele faz parte da 
classe e ele ta dividindo todos os seus membros com a classe, se ta privado a classe consegue acessar.

Na hora de acessar na main ou em outro lugar, considerando que é um membro, ele ja entende que é algo proprio da nossa 
classe

                    ${Conta.total}

Outro ponto não precisamos colocar o nome como estar ali companion object `Contador` podemos deixar sem. Mas ele tem um 
nome por baixo dos panos que é companion, companion.total++.

Então agora sim aprendemos uma técnica aqui, na orientação de objetos - que é justamente fazer com que mantenhamos um 
valor que seja considerado como estático, porque ele não muda o valor desde o começo do programa até o fim, de uma tal
maneira que ele seja membro da classe e apenas a classe consegue fazer a sua manipulação.
Como nós vimos, ele é muito interessante nesses casos, nos quais queremos fazer algum tipo de contador, algum tipo de 
registro que tem que ficar fixo e que não pode mudar. Mas que quem tem controle é a classe, não são objetos, não são as
classes que herdam dessa classe; e sim a própria classe

## Exemplos de Data Class com desistruturação de declaracoes

No date class, é uma classe que guarda informações,ex:

        data class documento(val rg: String, val cpf: String){  
        }
        
        val edrielle = documento("32098374", "2334343434")

Ao exibir um `println(edrielle)` o seu resultado é: `documento("32098374", "2334343434")`, para desestruturar
colocamos o `val(rg, cpf) = documento` e demos `println("rg: $rg e cpf: $cpf")` o resultado é: `rg: 32098374 e cpf:2334343434 ` 

O copy você pode copiar as informação de uma instancia criada sem precisar de criar outra:

                    val documento = documento("32098374", "2334343434")

                    //com copy
                
                    val documento2 = documento.copy()

* Lembrando que tem que ser um data class;
Podemos fazer com que na copia, passar o valor do rg diferente: `val documento2 = documento.copy(rg: 23434)` 
                    
## Conhecendo o Any
Funções que podem fazer execuções com valores diferentes, quer dizer que tem implementação de sobrecargas. Tipo classes e
funções que são reutilizados.

Então, no momento que você ver esse “Any”, significa que você pode sim considerar o uso do polimorfismo, você pode ]
trabalhar com qualquer tipo. Então, independentemente se estamos mandando um endereço, ou até mesmo um “Cliente”, 
por mais que eu não coloque aqui as informações para compilar, ele vai funcionar tranquilamente. Por que ele vai 
funcionar tranquilamente?
Porque temos aqui esse “Any”, que é uma classe, que qualquer classe que criamos, ele vai herdar. É a classe mais básica 
aqui do Kotlin. Então, no momento que você quiser trabalhar com um polimorfismo - porque ele é o mais genérico possível,
que atende qualquer tipo - você vai considerar esse “Any”.
E agora que descobrimos que existe esse “Any”, que meio que seria o polimorfismo global, vamos chamar assim; a seguir 
vamos falar mais a respeito das possibilidades que temos com o “Any”. Isso considerando o que teríamos de polimorfismo.



## Conhecendo e sobrescrevendo os métodos do Any

Comportamentos do ANY

* equals() compara um objeto com o outro, compara istancias:
                    

                     val documento = documento()
                     val documento2 = documento()
                    println(documento.equals(documento2))
A resposta vai ser false, mas se passase o mesmo valor sera true:

                     val documento = documento()
                     println(documento.equals(documento)) // true


* hasCode é como se fosse rg do objeto, o endereço vai ser o mesmo, se chamar ele novamente, ele vai ser o mesmo, vai ser
modo de identificar o objeto. 
                
                     val documento = documento()
                     println(documento)
                     impressão: 81721721 // será o endereçc


* toString retorna a representação do objeto.

               class Endereco (
                    var logradouro:  String = "",
                    var num: Int = 0,
                    var bairro: String = "",
                    var cidade:  String = "",
                    var estado:  String = "",
                    var cep:  String = "",
                    var complemento:  String = ""
                
               ){
                    override fun toString(): String {
                       return "Logradouro: $logradouro"
                   }
               }
        

                println(endereco)

Ele vai printar na hora de chamar o objeto intsanciado, ele mostra valor do logradouro.


Terão ferramentas e soluções pra personalizar toString, selecionando: alt + inseert e eles vao sugerir as 
porperties/membros que você quer apresentar:

        override fun toString(): String {
            return "Endereco(logradouro='$logradouro', num=$num, bairro='$bairro', cidade='$cidade', estado='$estado', cep='$cep', complemento='$complemento')"
        }

“raw string” acaba evitando. O que ele evita aqui? Basicamente, para usarmos o “raw string”, precisamos colocar ao invés
dessas aspas, colocarmos três aspas"
Ele pula linhas


              override fun toString(): String {
                  return """Endereco(logradouro='$logradouro', 
                      |num=$num,
                      | bairro='$bairro',
                      |  cidade='$cidade', 
                      |  estado='$estado', cep='$cep', 
                      |  complemento='$complemento')""".trimIndent()
              }

* Esse “trimIndent”, o que ele faz? Ele vai tirar os espaços vazio em branco. Por quê? Porque se deixarmos dessa
maneira, com o “raw string” - vamos testar os dois jeitos - vamos perceber que vai ficar uma linha vazia e vocês vão 
ver isso acontecer.


## Sobrescrevendo o equals e o hashcode

Aqui é pra informar o equals se é igual, vimos anterior se pegar endereco instanciado em duas variaveis diferente, vai 
da false, mas e se eu quiser dizer que se o cep de ambas for igual, da true? Fazemos o equals dentro da classe, para 
validarmos.

Implementamos na classe endereço do nosso codigo, com alt + insert ele implemeta automatico, mas alteramos algo:
        
                  override fun equals(other: Any?): Boolean {
                      if (other != null && other is Endereco){
                         return this.cep == other.logradouro
                      }
                      return false
                  }

                  println(endereco.equals(endereco2)) /// da true

Modificou o equals, tem que modificar o hashCode():


                      override fun hashCode(): Int {
                          return cep.hashCode()
                      }
                      
                      // main
  
                       println(endereco.hashCode())
                       println(endereco2.hashCode())

                       //resultado

                      -1173943107
                      -1173943107

* Como falei alt + insert faz automatico os methodos para o equals() e o hashCode()

## Exceptios 

O que seria essa “printStackTrace”? Vamos executar aqui e olhe só, essa tal da “printStackTrace” reúne aquelas três 
informações que vimos. Dentro dela vamos ter, além da identificação da “Exception” que foi capturada - como estamos 
vendo aqui, a mensagem que acabamos de ver agora, essa que fica aqui em uma única linha.

Ela vai ter a tal da “stackTrace”, que é isso que estamos vendo, que é também chamado de rastreamento da pilha.
Se tivesse uma causa, ela também iria nos mostrar.

Entenda que quando estamos trabalhando aqui com uma “Exception”, a “stackTrace” é bastante importante. Ela vai reunir
todas as informações que precisamos para que consigamos avaliar qual “Exception” que aconteceu, a mensagem que essa 
“Exception” está passando para nós, como podemos ver, e também essa parte do rastreamento da pilha, para identificarmos
o ponto do código que aconteceu uma “Exception”.

Porque quando também estamos usando uma ideia, que nesse caso é o “IntelliJ”, nós conseguimos até mesmo verificar:
“Foi a partir desse ponto da pilha que ocasionou a “Exception””, e eu posso clicar aqui e ele mostra exatamente o ponto
do código que está apresentando um problema.

## Criando uma Exception

Assim que criamos a class, referenciar sempre com Exception no final e tem que referenciar o `Throwable()` para ser 
instanciada.

* class SaldoInsuficienteException : Throwable()

A exception criada com a mensagem personalizada: `class SaldoInsuficienteException : Throwable("O saldo é insuficiente")`


* O atalho é com as teclas `“Ctrl + H”`, que mostra a hierarquia que temos aqui das classes quem tanto estamos herdando,
como herda da gente;
  Então é muito importante você saber que existe o “Throwable” e que existem aquelas duas classes que são as principais
no “Throwable”, que seriam – deixe-me até pegar aqui novamente - o “Error” e “Exception”. Quando nós vamos criar uma
“Exception” que é nossa, precisamos herdar diretamente de “Exception”, por esse significado que existe por trás
  gora vamos modificar aqui de “Throwable” para “Exception”, e vamos perceber que a classe “Exception” aqui vai ter a 
mesma assinatura que temos de “Throwable”. Tanto que ela herda de “Throwable”, é como se ela encapsulasse toda a regra 
de negócios que vai delegar para o “Throwable” aqui dentro do seu construtor.

* class SaldoInsuficienteException : Exception("O saldo é insuficiente")


### Perguntas questionadas

* Não devemos capturar Errors lançados pelo programa?
`Isso mesmo! Erros são problemas relacionados a JVM ou sistema que não podemos tratar, como é o caso de um problema
fatal que fecha a JVM, falta de memória ou o próprio estouro de pilha que simulamos.`

* Ao criarmos uma exception não podemos herdar diretamente de Throwable?
`Exatamente! Ao criarmos uma exception precisamos herdar diretamente de Exception, pois é a classe base para 
identificação de uma classe como exceção.`


## Lidando com mais de uma exception

Podemos fazer com que a nossa classe exception receba a mensagem: `
class SaldoInsuficienteException(mensagem: String): Exception(mensagem)` ou
`class SaldoInsuficienteException(mensagem: String = "O saldo é insuficente"): Exception(mensagem)`


Chamando ela: `throw SaldoInsuficienteException("O saldo é insuficente valor menor $valor")`

* quando uma classe é abstract e extende a uma interface, ela não é obrigada a implementar os methodos da 
interface, mas sim as suas filhas será obrigadas a obter o methodo da interface;



Exemplos:

      try {

        contaFran.transferencia(100000.00, contaAlex, 2)
        println("Trasnferencia sucedida")
      }
      catch (e: SaldoInsuficienteException){
          println("Falha na transferencia")
          e.printStackTrace()
  
      } catch (e: FalhaAutenticacaoExcepction){
          println("Falha na trasnferencia")
          println("Falha na autentificação")
          e.printStackTrace()
  
      } catch(e: Exception) {
          println("Erro desconhecido")
          e.printStackTrace()
      }


O ultimo catch: `catch(e: Exception) { println("Erro desconhecido") e.printStackTrace() } ` sempre deve vim por ultimo
pra não quebrar programa e falar de um erro desconhecido quando passamos ambos parametro divergente.


## Tipos que podem receber nulo
Vamos inicializar uma variavel com null, exemplo:

      var endereco: Endereco = null

Só que da erro, porque o Kotlin já dá uma mensagem falando que o nulo não pode ser um valor para um tipo que não pode 
ser nulo, ou seja, quando estamos trabalhando no Kotlin e temos aqui um tipo, como esse caso do “Endereco” que é uma 
classe, “string” ou “int”, seja qual for a classe, ele não vai permitir a inicialização por nulo.
Para que isso seja possível, temos que, obrigatoriamente, falar: “Isso pode receber nulo”. Para isso nós colocamos a 
tecla “?”. Essa tecla “?” sempre vai dar essa ideia de que podemos receber um valor nulo. Essa é a breve introdução
que temos de tipos que podem ser ou não nulos.

                 var endereco: Endereco? = null

Agora vem a questão: qual é o grande impacto em relação à uma variável que pode ou não ser nulo? Como já vimos,
o Kotlin não vai permitir utilizar os seus membros.
Ele até mostra uma mensagem aqui, que já vai direto a parte técnica, que vamos ver aos poucos, falando o seguinte:
ele só é seguro se você usar esse operador que ele está mostrando aqui e que vamos falar mais a respeito, ou então se
você utilizar esse outro operador que ele chama de “non-null asserted”, o [NON-NULL ASSERTION OPERATOR], que seria essa
tecla “!”.
Já vimos esse código em alguns outros pontos, vamos falar mais a respeito a seguir, não é o nosso foco nesse momento.
Mas nós ainda não vimos esse aqui, que é essa tecla “!”. Ele está falando que essa chamada só vai ser permitida nesses 
casos que podem ser nulos, apenas com isso e ponto.
O grande impacto é isso, é que o Kotlin vai tentar nos proteger dos nulos. Por que eu falo de proteção? Qual é o grande 
problema que temos em relação a essa questão.

Isso é assumir um grande impacto informando que ele é valido. Para acessar o mebro das classe, podemos fazer o seguinte:

                     var enderecoNulo: Endereco? = null
                     val enderecoNaoNulo: Endereco = enderecoNulo!!
                     enderecoNaoNulo.logradouro

## Utilizando o safe call

* Link para estudos [Link Estudos null safety](https://kotlinlang.org/docs/null-safety.html).

Vamos testar: 

             var enderecoNulo: Endereco? = null
             println(enderecoNulo.logradouro) // vai dar erro pq não é valido, mas o safe call resolve = ?

Então fica:
        
               var enderecoNulo: Endereco? = null
               println(enderecoNulo?.logradouro)  // entra o ? na frente 

o resultado é null

Agora passamos o logradouro:


              var enderecoNulo: Endereco? = Endereco("Rua dom pedro ll")
              println(enderecoNulo?.logradouro)
        
              // saida 
              Rua dom pedro ll

Se eu quiser ver o tamanho, melhor o length, sempre tenho que usar o safe call que é o `?`, exemplo:
          
              var enderecoNulo: Endereco? = Endereco("Rua dom pedro ll")
              println(enderecoNulo?.logradouro?.length)

              ou
              val logradouroNulo: String? = enederecoNulo?.logradouro

Se utilizar mais alguma coisa apos length

              enderecoNulo?.logradouro?.length?.toBigDecimal()

* Utilizando o let: como podemos ver, ele vai ser uma função que pode ser chamada por qualquer classe, isso porque
 ele utiliza esse recurso que é chamado de “generics”. O que podemos tirar de proveito disso? Que o “let” vai ser 
uma função também chamada de “scope functions”, e a partir dele nós pegamos um objeto que estamos utilizando e
o envolvemos para dentro de uma função.
 Se chegarmos aqui no nosso exemplo, podemos chegar no “endereco” e usar o “let”. O que o “let” vai fazer? Ele vai 
colocar o “endereco” para dentro de sua função. Tanto que já tem até mesmo o nome de maneira implícita, indicando qual
é o objeto que seria esse nosso endereço.

                 enderecoNulo.let {
                     println(it?.logradouro)
                 }

ou 

                 enderecoNulo.let {endereco ->
                     println(endereco?.logradouro)
                 }  

as agora você pode me perguntar: “Ele faz isso, mas o que ele faz de diferente em relação ao ‘safe call’?” A diferença 
é que podemos fazer a combinação, que é a seguinte: podemos falar que antes de chamar o “let”, queremos usar um “safe 
call”. Quando fazemos isso, nós temos a capacidade agora de usar o “Endereco” que não é nulo.
E é isso que acaba sendo interessante, porque agora podemos chamar tanto o “logradouro”.

         enderecoNulo?.let {
            println(it.logradouro)
         }

ou 
    
          enderecoNulo?.let {endereco ->
             println(endereco.logradouro)
             println(endereco.logradouro.length)
          }  

## Outros recursos do null safety: Elvis Operator

* [Elvis Operator info](https://kotlinlang.org/docs/null-safety.html#elvis-operator)

Aqui no “Elvis Operator” o que vamos ter de diferente? Ele vai ser uma alternativa do que temos do “if-xpression”, que
avalia se uma referência é diferente de nula, para então fazer a execução de um membro, esse caso, o “length”. 
Caso contrário, ele devolve um valor compatível para que tenha um valor que estamos vendo aqui, que é esse tal do “Int”.
Então vamos pegar aqui o “tamanhoComplemento” e vamos indicar que é um inteiro, sempre precisamos mandar o inteiro. 
Agora vamos utilizar o nosso “Elvis Operator”, para que não devolva “null” e devolva um valor ou outro, caso essa
referência ainda se mantenha nula.
Podemos pegar o “it.”, que é o nosso endereço, “complemento.”, e aqui vamos pegar o “length”. Como vimos, o “length”,
se fizermos com a nossa “safe call”, temos aquele probleminha, porque aqui ele vai ser um “int nullable”. Agora, com o
“Elvis Operator”, nós conseguimos fazer uma ação na qual devolvemos “0”, caso seja vazio, ou então devolvemos o
“length”, caso ele não seja nulo.

          enderecoNulo?.let {
              println(it.logradouro)
              val tamanhoComplemento: Int = it.complemento?.length ?: throw IllegalStateException("Complemento n deve existir")
              println(tamanhoComplemento)
          }

* Como se fosse true ou false ? :, mas assim ?: e o que vc quer passar de condição.
    
* Pode ser um 0 tb

          enderecoNulo?.let {
              println(it.logradouro)
              val tamanhoComplemento: Int = it.complemento?.length ?: 0
              println(tamanhoComplemento)
          }

Trabalhando com nulos e valor que esperamos:
E o que temos de diferente com o nosso “safe cast”? Como vimos, ele tem essa característica de colocar meio que um
“safe call” aqui no “cast”. O que ele vai fazer de diferente? Ele vai indicar que o valor que vamos pegar aqui pode ser nulo.
E dado que ele pode ser nulo, ele vai tentar converter. Se ele não conseguir, simplesmente esse número que estamos 
vendo aqui, vai receber um valor nulo. É uma alternativa que temos de fazer um “cast” seguro, além daquele “smart cast” 
que vimos.         
Então, tanto aqui com a “string”, como também com o “int”, ele vai rodar tranquilamente, sem nenhum problema. 
A diferença é que dessa forma nós conseguimos trabalhar com nulos ou então com os valores que esperamos.

          /// na main
  
          teste("")
          teste(1)

          // função
          
          fun teste(valor: Any){
              
            val numero: Int? = valor as? Int
            println(numero)      
          }

          /// saida
      
          null 
          1

Mas novamente, o foco aqui é só entender que com o nosso “safe casts”, nós evitamos aquela questão da “Exception” e 
trabalhamos com os nulos, de tal forma que o Kotlin nos oferece aquela segurança com os nulos.

### Perguntas e respostas;

* Se é identificada a possibilidade de referência nula o compilador do Kotlin não compila?
`Isso mesmo! Considerando a possibilidade de existir um nulo, o código não compilará enquanto não se prova o contrário.`


* Para inicializar uma variável com null, ela precisa ser nullable.
`Exatamente! Como vimos, existem os tipos nullable e non-null, que determinam se podem ou não receber referências nulas.`



### Links
[Elivis Operation info](https://www.javatpoint.com/kotlin-elvis-operator)