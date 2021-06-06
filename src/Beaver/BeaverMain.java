import java.io.IOException;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.List;

import structures.Figure;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class BeaverMain {
   public static void main(String[] args) {
      try {
         // create a CharStream that reads from standard input:
         CharStream input = CharStreams.fromStream(System.in);
         // create a lexer that feeds off of input CharStream:
         BeaverLexer lexer = new BeaverLexer(input);
         // create a buffer of tokens pulled from the lexer:
         CommonTokenStream tokens = new CommonTokenStream(lexer);
         // create a parser that feeds off the tokens buffer:
         BeaverParser parser = new BeaverParser(tokens);
         // replace error listener:
         //parser.removeErrorListeners(); // remove ConsoleErrorListener
         //parser.addErrorListener(new ErrorHandlingListener());
         // begin parsing at program rule:
         ParseTree tree = parser.program();
         if (parser.getNumberOfSyntaxErrors() == 0) {
            // print LISP-style tree:
            // System.out.println(tree.toStringTree(parser));
            System.out.println("Checking semantic...\n");
            BeaverSemanticAnalyses beaverSemantic = new BeaverSemanticAnalyses();
            boolean semanticCheck = beaverSemantic.visit(tree);
            if (semanticCheck) {
               System.out.println("\nSemantic passed with no Errors!\nInterpreting Beaver...\n");
               BeaverInterpreter beaverInterpreter = new BeaverInterpreter();
               beaverInterpreter.visit(tree);

               // print figures sorted by number of figures they have
               List<Figure> sortedByNmrFigures = beaverInterpreter.figures().entrySet().stream().map(figure -> figure.getValue()).collect(Collectors.toList()).stream().sorted(Comparator.comparingInt(Figure::numberFigures).reversed()).collect(Collectors.toList());
               System.out.println("");
               printFigureFamilyTree(sortedByNmrFigures.get(0), 0);
               System.out.println("");
               // print default sorting
               beaverInterpreter.palletes().entrySet().stream().forEach(pallete -> System.out.println(pallete.getValue()));
            }
         }
      }
      catch(IOException e) {
         e.printStackTrace();
         System.exit(1);
      }
      catch(RecognitionException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }

   public static void printFigureFamilyTree(Figure figure, int level) {
      List<Figure> children = figure.figures();
      for (int i=0; i<level; i++) {
         System.out.print(" ");
      }
      System.out.printf("-%s\n", figure.id());
      if (children.size() > 0) {
         level++;
         for (Figure child : children) {
            printFigureFamilyTree(child, level);
         }
      }
   }
}
