package Geometrics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileWriter;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class GeometricsMain {
   public static void main(String[] args) {
      try {
         CharStream input = CharStreams.fromStream(new FileInputStream(new File(args[0])));
         // create a lexer that feeds off of input CharStream:
         GeometricsLexer lexer = new GeometricsLexer(input);
         // create a buffer of tokens pulled from the lexer:
         CommonTokenStream tokens = new CommonTokenStream(lexer);
         // create a parser that feeds off the tokens buffer:
         GeometricsParser parser = new GeometricsParser(tokens);
         // replace error listener:
         // parser.removeErrorListeners(); // remove ConsoleErrorListener
         // parser.addErrorListener(new ErrorHandlingListener());
         // begin parsing at program rule:
         ParseTree tree = parser.program();
         if (parser.getNumberOfSyntaxErrors() == 0) {
            // print LISP-style tree:
            // System.out.println(tree.toStringTree(parser));
            GeometricsCompiler compiler = new GeometricsCompiler(args[1]);
            String finalContent = compiler.visit(tree).render();
            FileWriter writer = new FileWriter(args[1] + ".java");
            writer.write(finalContent);
            writer.close();
         }
      } catch (IOException e) {
         e.printStackTrace();
         System.exit(1);
      } catch (RecognitionException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }
}
