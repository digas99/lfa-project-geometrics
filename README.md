## Membros do Grupo
&nbsp;

| NMec | Name | email | Contribution (%) | Detailed contribution [1]
|:-:|:--|:--|:-:|:--|
| 73150 | Gonçalo Maranhão | goncalo.rodrigues@ua.pt | 22,6% | gramática principal(16,6%)<br> criação de código java swing(backend estático)(100%)<br>geração de código(10%)<br>examples (50%)<br>testes (20%)|
| 73211 | Mariana Gomes | marianapinheiro@ua.pt | 0% | |
| 90327 | Diogo Correia | diogo.correia99@ua.pt | 26,7% |gramática principal (33,6%)<br> análise semântica gramática principal (20%)<br> geração de código (80%)<br> gramática secundária (50%)<br> análise semântica gramática secundária (70%)<br>interpretação/geração de código linguagem secundária (100%)<br>exemplos (50%)<br>testes(20%)<br>criação de scripts(100%)|
| 92975 | Leandro Rito | leandro.rito@ua.pt | 16,9% | gramática principal (16,6%)<br>análise semântica gramática principal (40%)<br>testes (20%)|
| 93427 | Lara Rodrigues| laravieirarodrigues@ua.pt | 16,9% | gramática principal (16,6%)<br>análise semântica gramática principal(40%)<br>gramática secundária(50%) <br> análise semântica gramática secundária (30%)<br>testes (20%)<br>relatório (100%) |
| 93460 | João Fernandes | joaogilfernandes@ua.pt | 16,9% | gramática principal(16,6%)<br>geração de código(10%)<br>testing (20%)|


[1] Topics:<br>
   gramática principal (%)<br> análise semântica gramática principal (%)<br> geração de código (%)<br> gramática secundária (%)<br> análise semântica gramática secundária (%)<br>interpretação/geração de código linguagem secundária (%)<br>exemplos (%)<br>testes(%)<br>relatório (%)<br> criação de código java swing("backend estático")(100%)<br>criação de scripts(%)


## Material para avaliação

Todo o código presente na master conta para avalição excepto a pasta JavaGraphicsExample e a pasta figure_making que apenas serviram de material de apoio à realização do projeto.<br> Na pasta doc os ficheiros fullExampleMainLanguage.txt e LFA_Geometrics_ExemploLinguagemPrincipal.txt representam ambições do que seria o nosso programa caso tivéssemos conseguido fazer tudo o que idealizámos. O princípio do projeto foi realizado com a ambição de conseguir fazer tudo o que está presente nesses ficheiros. 

## Compilar e Correr

A primeira coisa a fazer é entrar na `src` e executar o `antlr4-build.`
Ainda no `src`, se quiser correr os exemplos `example1.geo` e `test.geo`, execute o comando:<br> `java Geometrics.GeometricsMain ../examples/fileGeo.geo NomeDoFicheiro`, onde `fileGeo` é o nome do programa .geo e `NomeDoFicheiro` é o nome do ficheiro java gerado automaticamente.<br/>
Para executar os dois ficheiros de teste da linguagem secundária seria ``java Beaver.BeaverMain ../doc/Beaver/working/nome_do_ficheiro`




## Exemplos funcionais (pelo menos dois)

No ponto 1. encontram-se dois exemplos funcionais da linguagem secundária.

1. `geometrics-lfa-14/doc/Beaver/working`

    No ficheiro `working-test1.bvr` é criada a figura de um stickman que é constituido por diversas figuras que estão dentro de um container retangular.<br>
    No ficheiro `flags.bvr` são criadas figuras de duas bandeiras que, assim como no exemplo acima, são constituidas por diversas figuras.<br>

No ponto 2. encontam-se 2 exemplos funcionais da linguagem principal. Em ambos os exemplos, pode-se fazer play/pause com as teclas P/S, respetivamente. 

2. `geometrics-lfa-14/examples`

    No ficheiro `test.geo` são criados 6 circulos pretos, após a primeira colisão com a border alteram a cor e vão alternando a troca de cor a cada colisão, simultaneamente, a cada colisão com a border o retangulo que está no centro da figura vai rodando segundo um determinado angulo.<br>

    No ficheiro `example1.geo` existem dois retangulos que quando colidem se movem em direções opostos, em seguida, quando estes retangulos chegam ao border ficam prenchidos e 4 novas figuras entram no board. Dois circulos que se movem, na vertical na mesma direção e após colidirem vão em direções opostas em direção à border, aquando desta colisão os circulos ficam prenchidos e os outros dois, que até ao momento estão parados nos cantos superior esquerdo e inferior direito do board, começam a andar na diagonal até o seu centro ser (0,0) e ficam parados.


## Exemplos de erros semânticos

O objetico era ter-mos feito análise semântica com especificação de erros para ambas as linguagens, mas por falta de tempo não conseguimos terminar a análise semântica da linguagem principal. No ponto 1. encontram-se os testes para a linguagem secundária.<br>
As mensagens de erro aparecem com a especificação da linha e da coluna onde ocorrem no código.<br>
Nas imagens que incluimos no relatório são visiveis a maioria das mesagens de erro criadas.<br>

1. `geometrics-lfa-14/src/Beaver/errors`
    Para compilar:<br>
    dentro do diretório : `geometrics-lfa-14/src/Beaver/`<br>
    utilizar o comando: `java BeaverMain ../../doc/Beaver/errors/nome_do_ficheiro`

    ![title](images/test1.png)

    Neste exemplo (error-test1.bvr) são detetados vários erros de semântica relacionados com as figuras.<br>
    Tal como podemos observar as mensagens de erro indicam especificamente quais foram os erros cometidos.<br>

    ![title](images/test2.png)
     
    Este exemplo (error-test2.bvr) testa especificamente erros relacionados com as propriedades de cor. <br>


## Especificações da linguagem 

1. Palavras reservadas das linguagens:<br>
`Secundária:`<br>
containers, Color, Pallete, Rectangle, Number, Figure, Circle, Line, Triangle, Point, <br>
size,angle,color, border, filled,<br>
contains,<br>
startingPoint, endingPoint, center,<br>
pi,rad, º <br>
`Principal:` <br> 
use, Color, Board,<br> 
start, end , List, at, Text, set,<br> 
pos,posX,posY,<br> 
Figure, Line, Triangle, Rectangle, Circle, <br> 
display, width, heigth,thickness, color, collision, exposed, depth, hidden<br> 
true, false,<br> 
center,<br> 
add, remove, <br> 
draw, write,<br> 
Time, ms, <br> 
Angle, º, deg, pi, rad, <br> 
each, while,<br> 
Task, from, where is, close <br> 

2. Comentários:<br>
Linguagem secundária: `!!`<br>
Linguagem principal : `/-` <br>

## Observações
O projeto não está a funcionar como gostariamos que estivesse e apresenta uma mistura entre as caracteristicas obrigatórias e as desejaveis que sabemos que não é o ideal, mas fizemos o máximo esforço possível para recuperar o tempo perdido após a desistência de um dos membros do grupo.<br>
Por razões que nos são desconhecidas, quando o programa apresenta as animações, fica com quebras de fps. Para a animação ficar mais smooth, inexplicavelmente, se mexer no rato (continuamente) dentro do board a situação melhora.

## Lembrete 
Na última aula (21/06/2021) estivemos a resolver com o professor a melhor maneira de compilar e de correr os exemplos, visto que, anteriormente tinhamos feito de forma a ter que se correr um script que pedia para alterar a bashrc.<br>
Sendo que, não faria sentido ter de mudar a bashrc, o último commit é apenas referente a pequenas mudaças de código e localização de pastas para facilitar a compilação e execução do programa.

