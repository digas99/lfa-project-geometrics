import java.util.HashMap;
import java.util.List;

import org.stringtemplate.v4.*;

public class Compiler extends QuestionsBaseVisitor<ST> {

   @Override public ST visitProgram(QuestionsParser.ProgramContext ctx) {
      ST module = templates.getInstanceOf("module");
      module.add("name", "Questions");
      module.add("hasMap", " ");
      ctx.question().stream().forEach(question -> {
         module.add("stat", visit(question));
      });
      return module;
   }

   @Override public ST visitQuestion(QuestionsParser.QuestionContext ctx) {
      ST decl = templates.getInstanceOf("declaration");
      decl.add("type", "String");
      thisVar = ctx.ID(0)+"_"+ctx.ID(1);
      decl.add("var", "title_"+thisVar);
      decl.add("value", ctx.TEXT().getText());

      ST array = templates.getInstanceOf("literalArray");
      array.add("type", "String");
      array.add("var", thisVar);
      for (int i = 0; i<ctx.answer().size(); i++) {
         array.add("value",visit(ctx.answer(i)));
         if (i < ctx.answer().size()-1)
            array.add("value", ",");
      }
      System.out.println(array.render());

      ST print = templates.getInstanceOf("print");
      print.add("value", "title_"+thisVar);
      print.add("stat", decl.render());
      print.add("stat", array.render());

      ST streamPrint = templates.getInstanceOf("stream_loop_print");
      streamPrint.add("stat", print);
      streamPrint.add("var", thisVar);

      return streamPrint;
   }

   @Override public ST visitAnswer(QuestionsParser.AnswerContext ctx) {
      ST stats = templates.getInstanceOf("stats");
      stats.add("stat", ctx.TEXT().getText());
      return stats;
   }


   private String newVar() {
      numVars++;
      return "var"+numVars;
   }
   private int numVars = 0;
   private String thisVar;
   private STGroup templates = new STGroupFile("ex10.stg");
   private HashMap<String, List<ST>> qa = new HashMap<>();
}
