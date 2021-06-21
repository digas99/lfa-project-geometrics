package Beaver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import structures.Figure;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class BeaverMain {

   public static void main(String[] args) {
      try {
         CharStream input = CharStreams.fromStream(new FileInputStream(new File(args[0])));
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

               // sort all figures by number of figures
               List<Figure> sortedByNmrFigures = beaverInterpreter.figures().entrySet().stream().map(figure -> figure.getValue()).collect(Collectors.toList()).stream().sorted(Comparator.comparingInt(Figure::numberFigures).reversed()).collect(Collectors.toList());
               // get all containers
               containers = beaverInterpreter.containers();
               containers.stream().forEach(container -> {
                  System.out.println("\n");
                  container.printFamilyTree(0);
                  System.out.println("");
                  container.printFamily();
                  System.out.println("___________________________________________________________");
               });
               System.out.println("");

               //sortedByNmrFigures.forEach(figure -> System.out.println(figure.printFigure()));

               // print default sorting
               // beaverInterpreter.palletes().entrySet().stream().forEach(pallete -> System.out.println(pallete.getValue()));
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

   private static List<Figure> containers = new ArrayList<>();

   public static List<Figure> getContainers() {
      return containers;
   }

   public static Figure getContainer(String id) {
      for (Figure f : containers) {
         if (f.id().equals(id))
            return f;
      }
      return null;
   }

}
