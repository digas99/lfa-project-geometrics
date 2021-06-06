import java.io.IOException;
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

               beaverInterpreter.figures().entrySet().stream().forEach(figure -> System.out.println(figure.getValue().printFigure()+"\n"));
               //System.out.println(beaverInterpreter.palletes());
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
}
