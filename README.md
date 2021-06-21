# Geometrics language

-----

## Group members

| NMec | Name | email |
|--:|---|---|
| 73150 | [Gonçalo Rodrigues Maranhão](https://github.com/GoncaloMaranhao) | goncalo.rodrigues@ua.pt |
| 73211 | [Mariana Pinheiro Gomes](https://github.com/marianapinheiro21) | marianapinheiro@ua.pt |
| 90327 | [Diogo Costa Correia](https://github.com/digas99) | diogo.correia99@ua.pt |
| 92975 | [Leandro Filipe Gomes Rito](https://github.com/Strikeneerman) | leandro.rito@ua.pt |
| 93427 | [Lara Vieira Rodrigues](https://github.com/Lararodrigues1) | laravieirarodrigues@ua.pt |
| 93460 | [João Gil Ferreira de Sousa Fernandes](https://github.com/GilFernandes2000) | joaogilfernandes@ua.pt |
|  |  |  |

## Folder organization

- **src** -- all source code should in this folder
- **doc** -- reports and any other documentation should be inside this folder
- **examples** -- example programs in your porposed language should be inside this folder

## Compilar e Correr

   -A primeira coisa a fazer é executar o comando: `bash setup.sh` no diretório `geometrics-lfa-14`<br>
    Para compilar e correr os ficheiros da linguagens principal executar o comando: `bash draw.sh fileGeo OutputClassName` novamente no diretório `geometrics-lfa-14`<br>
    onde fileGeo é o nome do programa .geo e OutputClassName é o nome do ficheiro java gerado automaticamente.


## Exemplos funcionais (pelo menos dois)

No ponto 1. encontram-se dois exemplos funcionais da linguagem secundária.

1. `geometrics-lfa-14/doc/Beaver/working`

    No ficheiro `working-test1.bvr` é criada a figura de um stickman que é constituido por diversas figuras que estão dentro de um container retangular.<br>
    No ficheiro `flags.bvr` são criadas figuras de duas bandeiras que, assim como no exemplo acima, são constituidas por diversas figuras.<br>
    Para compilar e correr este exemplo deve executar o comando :<br> `java BeaverMain ../../doc/Beaver/working/nome_do_ficheiro` <br> 
    no diretório : `geometrics-lfa-14/src/Beaver/`

No ponto 2. encontam-se 2 exemplos funcionais da linguagem principal. Em ambos os exemplos, pode-se fazer play/pause com as teclas P/S, respetivamente. 

2. `geometrics-lfa-14/examples`

    No ficheiro `test.geo` são criados 6 circulos pretos, após a primeira colisão com a border alteram a cor e vão alternando a troca de cor a cada colisão, simultaneamente, a cada colisão com a border o retangulo que está no centro da figura vai rodando segundo um determinado angulo.<br>
    Para compilar e correr este exemplo deve executar o comando : `bash draw.sh test OutputClassName` no diretório `geometrics-lfa-14`<br>

    No ficheiro `example1.geo` existem dois retangulos que quando colidem se movem em direções opostos, em seguida, quando estes retangulos chegam ao border ficam prenchidos e 4 novas figuras entram no board. Dois circulos que se movem, na vertical na mesma direção e após colidirem vão em direções opostas em direção à border, aquando desta colisão os circulos ficam prenchidos e os outros dois, que até ao momento estão parados nos cantos superior esquerdo e inferior direito do board, começam a andar na diagonal até o seu centro ser (0,0) e ficam parados.
    Para compilar e correr este exemplo deve executar o comando : `bash draw.sh example1 OutputClassName` no diretório `geometrics-lfa-14`<br>


## Exemplos de erros semânticos

O objetico era ter-mos feito análise semântica com especificação de erros para ambas as linguagens, mas por falta de tempo não conseguimos terminar a análise semântica da linguagem principal. No ponto 1. encontram-se os testes para a linguagem secundária.<br>
As mensagens de erro vem com a especificação da linha e da coluna onde ocorrem no código.<br>
Nas imagens que incluimos no relatório são visiveis a maioria das mesagens de erro criadas.<br>

1. `geometrics-lfa-14/src/Beaver/errors`
    Para compilar:<br>
    dentro do diretório : `geometrics-lfa-14/src/Beaver/`<br>
    utilizar o comando: `java BeaverMain ../../doc/Beaver/errors/nome_do_ficheiro`

    ![title](doc/images/test1.png)

    Neste exemplo (error-test1.bvr) são detetados vários erros de semântica relacionados com as figuras.<br>
    Tal como podemos observar as mensagens de erro indicam especificamente quais foram os erros cometidos.<br>

    ![title](doc/images/test2.png)
     
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

