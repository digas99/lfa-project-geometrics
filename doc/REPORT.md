## Group members
&nbsp;

| NMec | Name | email | Contribution (%) | Detailed contribution [1]
|:-:|:--|:--|:-:|:--|
| 90327 | Diogo Correia | diogo.correia99@ua.pt | 15% | primary-grammar (20%)<br>examples (40%)<br>testing (30%)|
| 73150 | Gonçalo Maranhão | goncalo.rodrigues@ua.pt | 15% | primary-grammar (20%)<br>examples (40%)<br>testing (30%)|
| 93460 | João Fernandes | joaogilfernandes@ua.pt | 15% | primary-grammar (20%)<br>examples (40%)<br>testing (30%)|
| 93427 | Lara Rodrigues| laravieirarodrigues@ua.pt | 15% | primary-grammar (20%)<br>examples (40%)<br>testing (30%)|
| 92975 | Leandro Rito | leandro.rito@ua.pt | 15% | primary-grammar (20%)<br>examples (40%)<br>testing (30%)|
| 73211 | Mariana Gomes | marianapinheiro@ua.pt | 15% | primary-grammar (20%)<br>examples (40%)<br>testing (30%)|


[1] Topics:<br>
   primary-grammar (%)<br>primary-semantic-analysis (%)<br>code-generation (%)<br>secondary-grammar (%)<br>secondary-semantic-analysis (%)<br>secondary-interpretation/secondary-code-generation (%)<br>examples (%)<br>testing (%)<br>other (%) (explain)

- Beware that within the group the sum for each topic must be 100% (obviously).

## Material to be evaluated

- Beware that **only** the code in the **master** branch will be considered for evaluation.

## Compilation & Run

- Explain how to compile and run language code.

- Preferable option is to provide scripts *compile* and *run*, accepting program filename as argument.

## Working examples (at least two)

Use examples to show the language functionalities.

1. `.../geometrics-lfa-14/src/Beaver$ java BeaverMain ../../doc/Beaver/working/working-test1.bvr`

    Explain what the example does, how it is compiled and run.

2. `path-to-example`

    Explain what the example does, how it is compiled and run.

...

## Semantic error examples

Fizemos análise semântica para as duas linguagens com mensagens personalizadas de erro.
Dentro da pasta doc está a pasta Beaver onde estão dois ficheiros de teste da linguagem com alguns erros criados propositadamente para vermos as mensagens de erro.
As mensagens de erro vem com a espicificação da linha e da coluna em que ocorrem no código.<br>

1. `.../geometrics-lfa-14/src/Beaver`

    Neste exemplo são detetados dois erros de semântica:
    O primeiro, é um erro na propreidade size que deveria receber dois parâmetros e recebeu apenas um. A mensagem de erro mostrada ao utilizador será:<br>
    `Error@6:4 -> Invalid value for property size! `<br>
    O segundo, é um erro no atributo color que recebeu um código de cor inválido.
    A mensagem de erro apresentada será:<br>
    `Error@8:10 -> #f4444444 is not a valid color code! `<br>



    `path-to-example`
    Neste segundo exemplo são detetados três erros de semântica:
    O primeiro, é um erro 

    

    Explain the detected semantic error and how it is compiled.

...
