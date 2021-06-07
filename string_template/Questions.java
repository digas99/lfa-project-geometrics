import java.util.Arrays;

public class Questions {
		public static void main(String[] args) {
        	String title_antlr_P1 = "O ANTLR4 permite...";
        	String[] antlr_P1 = {"construir um interpretador para uma linguagem","construir um compilador para uma linguagem","paralelizar um programa","processar ficheiros de especificação de linguagens concebidos para lex e yacc","a geração automática de código de processamento de uma linguagem em qualquer outra linguagem","verificar se um programa está correcto"};
        	System.out.println(title_antlr_P1);
        	Arrays.asList(antlr_P1).stream().forEach(ans -> System.out.println("\t"+ans));
        	String title_outro_P1 = "Um gato é...";
        	String[] outro_P1 = {"um insecto","um mamífero","o melhor amigo do Homem","um animal da família dos felinos","um peixe"};
        	System.out.println(title_outro_P1);
        	Arrays.asList(outro_P1).stream().forEach(ans -> System.out.println("\t"+ans));
        	String title_q1_COLORS = "O que é azul com verde";
        	String[] q1_COLORS = {"preto","amarelo","roxo","branco","impossível","roxo outra vex"};
        	System.out.println(title_q1_COLORS);
        	Arrays.asList(q1_COLORS).stream().forEach(ans -> System.out.println("\t"+ans));
    }
}