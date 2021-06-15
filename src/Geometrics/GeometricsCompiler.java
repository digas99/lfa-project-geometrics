import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;

import java.util.Arrays;

import structures.*;

import org.stringtemplate.v4.*;

public class GeometricsCompiler extends GeometricsBaseVisitor<ST> {

   private String className;

   public GeometricsCompiler(String className) {
      this.className = className;
   }

   // grupo 1
   @Override
   public ST visitProgram(GeometricsParser.ProgramContext ctx) {
      ST module = template.getInstanceOf("module");
      module.add("name", this.className);
      module.add("boardName", ctx.STRING().getText());

      if (ctx.use().size() > 0) {
         module.add("stat", visit(ctx.use(0)));
      }

      ctx.stats().stream().forEach(stat -> module.add("stat", visit(stat).render()));
      if (hasVars)
         module.add("hasVars", hasVars);

      return module;
   }

   @Override
   public ST visitUse(GeometricsParser.UseContext ctx) {
      ST main = template.getInstanceOf("callMain");
      main.add("class", "BeaverMain");
      main.add("args", ctx.STRING().getText());
      ctx.useAttribs().stream().forEach(attrib -> main.add("stat", visit(attrib)));
      return main;
   }

   @Override
   public ST visitUseAttribs(GeometricsParser.UseAttribsContext ctx) {
      ST addList = template.getInstanceOf("add_to_list");
      addList.add("type", ctx.FIGURE().getText());
      addList.add("var", ctx.ID(0).getText());
      addList.add("varList", "figures");
      addList.add("value", "BeaverMain.getContainer(\""+ctx.ID(1).getText()+"\")");
      return addList;
   }

   @Override
   public ST visitStatVarsInit(GeometricsParser.StatVarsInitContext ctx) {
      return visit(ctx.varsInit());
   }

   @Override
   public ST visitStatVarsSet(GeometricsParser.StatVarsSetContext ctx) {
      return visit(ctx.varsSet());
   }

   @Override
   public ST visitStatList(GeometricsParser.StatListContext ctx) {
      return visit(ctx.list());
   }

   @Override
   public ST visitStatDraw(GeometricsParser.StatDrawContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitStatLoop(GeometricsParser.StatLoopContext ctx) {
      return visit(ctx.loop());
   }

   @Override
   public ST visitStatConditional(GeometricsParser.StatConditionalContext ctx) {
      return visit(ctx.conditional());
   }

   @Override
   public ST visitStatFuncCall(GeometricsParser.StatFuncCallContext ctx) {
      return visit(ctx.funcCall());
   }

   // grupo 2
   @Override
   public ST visitStatEasterEgg(GeometricsParser.StatEasterEggContext ctx) {
      return visit(ctx.easteregg());
   }

   @Override
   public ST visitStatConsoleLog(GeometricsParser.StatConsoleLogContext ctx) {
      ST print = template.getInstanceOf("print");
      print.add("value", ctx.ID() != null ? ctx.ID().getText() : ctx.STRING().getText());
      return print;
   }

   @Override
   public ST visitStatContainer(GeometricsParser.StatContainerContext ctx) {
      return visit(ctx.container());
   }

   @Override
   public ST visitContainer(GeometricsParser.ContainerContext ctx) {
      /*
       * ST container = template.getInstanceOf("container"); container.add("var",
       * ctx.ID().getText()); container.add("id", ); for(int i = 0; i <
       * ctx.stats().size(); i++){ container.add("stat", visit(stats).render()); }
       * return container;
       */
      return null;
   }

   @Override
   public ST visitExprMultDiv(GeometricsParser.ExprMultDivContext ctx) {
      ST declVar = template.getInstanceOf("declVar");
      ctx.var = newExprVar();
      declVar.add("stat", visit(ctx.expr(0)));
      declVar.add("stat", visit(ctx.expr(1)));
      declVar.add("type", "double");
      declVar.add("var", ctx.var);
      declVar.add("value", ctx.expr(0).var);
      String op = ctx.op.getText();
      declVar.add("value", symbAssoc.containsKey(op) ? symbAssoc.get(op) : op);
      declVar.add("value", ctx.expr(1).var);
      return declVar;
   }

   @Override
   public ST visitExprAddSub(GeometricsParser.ExprAddSubContext ctx) {
      ST declVar = template.getInstanceOf("declVar");
      ctx.var = newExprVar();
      declVar.add("stat", visit(ctx.expr(0)));
      declVar.add("stat", visit(ctx.expr(1)));
      declVar.add("type", "double");
      declVar.add("var", ctx.var);
      declVar.add("value", ctx.expr(0).var);
      String op = ctx.op.getText();
      declVar.add("value", symbAssoc.containsKey(op) ? symbAssoc.get(op) : op);
      declVar.add("value", ctx.expr(1).var);
      return declVar;
   }

   @Override
   public ST visitExprParentesis(GeometricsParser.ExprParentesisContext ctx) {
      ST visit = visit(ctx.expr());
      ctx.var = ctx.expr().var;
      return visit;
   }

   @Override
   public ST visitExprId(GeometricsParser.ExprIdContext ctx) {
      ST declVar = template.getInstanceOf("declVar");
      ctx.var = newExprVar();
      ST visit = visit(ctx.identifiers());
      declVar.add("value", visit);
      String[] functions = visit.render().split("\\.");
      String lastFunc = functions[functions.length-1].split("\\(\\)")[0];
      String type = "";
      if (functions.length > 1) {
         for (List<String> funcList : funcTypesAssoc.keySet()) {
            if (funcList.contains(lastFunc))
               type = funcTypesAssoc.get(funcList);
         }
      }
      else
         type = varsTypes.get(visit.render());
      declVar.add("type", type); 
      declVar.add("var", ctx.var);
      return declVar;
   }

   @Override
   public ST visitExprUnary(GeometricsParser.ExprUnaryContext ctx) {
      ST unu = template.getInstanceOf("declVar");
      ctx.var = newExprVar();
      unu.add("stat", visit(ctx.expr()));
      unu.add("type", "double");
      unu.add("var", ctx.var);
      if (ctx.value.getText() == "-") {
         unu.add("value", "-" + ctx.expr().var);
      } else {
         unu.add("value", ctx.expr().var);
      }
      return unu;
   }

   @Override
   public ST visitExprPower(GeometricsParser.ExprPowerContext ctx) {
      ST power = template.getInstanceOf("to_the_power");
      ctx.var = newExprVar();
      power.add("stat", visit(ctx.expr(0)));
      power.add("stat", visit(ctx.expr(1)));
      power.add("type", "double");
      power.add("var", ctx.var);
      power.add("value1", ctx.expr(0).var);
      if (ctx.value != null) {
         if (ctx.value.getText() == "-") {
            power.add("value2", "-" + ctx.expr(1).var);
         } else {
            power.add("value2", ctx.expr(1).var);
         }
      } else {
         power.add("value2", ctx.expr(1).var);
      }
      return power;
   }

   // grupo 1
   @Override
   public ST visitExprNumber(GeometricsParser.ExprNumberContext ctx) {
      ST declVar = template.getInstanceOf("declVar");
      ctx.var = newExprVar();
      declVar.add("type", "double");
      declVar.add("var", ctx.var);
      declVar.add("value", ctx.NUMBER().getText());
      return declVar;
   }

   // todo
   @Override
   public ST visitIdentifiers(GeometricsParser.IdentifiersContext ctx) {
      ST stats = template.getInstanceOf("stats_line");
      stats.add("stat", ctx.ID(0).getText());
      for (int i = 1; i < ctx.ID().size(); i++) {
         stats.add("stat", ".");
         stats.add("stat", ctx.ID(i).getText()+"()");
      }
      return stats;
   }

   @Override
   public ST visitPointsId(GeometricsParser.PointsIdContext ctx) {
      return visit(ctx.identifiers());
   }

   @Override
   public ST visitPointsCenter(GeometricsParser.PointsCenterContext ctx) {
      // ST cent = template.getInstanceOf("center");
      // cent.add("center", );
      // return center;
      return null;
   }

   @Override
   public ST visitPointsExprCalc(GeometricsParser.PointsExprCalcContext ctx) {
      ST calcP = template.getInstanceOf("calcPoints");
      String type;
      if (ctx.op.getText() == "+")
         type = "sum";
      else
         type = "sub";
      calcP.add("type", type);
      calcP.add("var", newExprVar());
      calcP.add("p0", ctx.pointsExpr(0));
      calcP.add("p1", ctx.pointsExpr(1));
      return calcP;
   }

   @Override
   public ST visitPointsExprPoint(GeometricsParser.PointsExprPointContext ctx) {
      return visit(ctx.point());
   }

   @Override
   public ST visitBoolLogicExpr(GeometricsParser.BoolLogicExprContext ctx) {
      ST declVar = template.getInstanceOf("declVar");
      ctx.var = newExprVar();
      declVar.add("type", "double");
      declVar.add("var", ctx.var);
      declVar.add("stat", visit(ctx.expr()));
      declVar.add("value", ctx.expr().var);
      return declVar;
   }

   @Override
   public ST visitBoolLogicParentesis(GeometricsParser.BoolLogicParentesisContext ctx) {
      return visit(ctx.booleanLogic());
   }

   @Override
   public ST visitBoolLogicNot(GeometricsParser.BoolLogicNotContext ctx) {
      ST not = template.getInstanceOf("not_bool");
      not.add("value", ctx.booleanLogic());
      return not;
   }

   // grupo 2
   @Override
   public ST visitBoolLogicOps(GeometricsParser.BoolLogicOpsContext ctx) {
      ST declVar = template.getInstanceOf("declVar");
      ctx.var = newBoolExprVar();
      declVar.add("stat", visit(ctx.booleanLogic(0)));
      declVar.add("stat", visit(ctx.booleanLogic(1)));
      declVar.add("type", "boolean");
      declVar.add("var", ctx.var);
      declVar.add("value", ctx.booleanLogic(0).var);
      declVar.add("value", symbAssoc.get(ctx.op.getText()));
      declVar.add("value", ctx.booleanLogic(1).var);
      return declVar;
   }

   @Override
   public ST visitBoolLogicCollides(GeometricsParser.BoolLogicCollidesContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitBoolLogicTruthval(GeometricsParser.BoolLogicTruthvalContext ctx) {
      ST declVar = template.getInstanceOf("declVar");
      ctx.var = newBoolExprVar();
      declVar.add("type", "boolean");
      declVar.add("var", ctx.var);
      declVar.add("value", ctx.TRUTHVAL().getText());
      return declVar;
   }

   @Override
   public ST visitVarsOnlyInit(GeometricsParser.VarsOnlyInitContext ctx) {
      ST decl = template.getInstanceOf("declVar");
      String type = ctx.OBJECT() != null ? ctx.OBJECT().getText() : ctx.FIGURE().getText();
      String convertedType = typesAssoc.containsKey(type) ? typesAssoc.get(type) : type;
      String var = ctx.ID().getText();
      decl.add("type", convertedType);
      decl.add("var", var);
      varsTypes.put(var, convertedType);
      return decl;
   }

   @Override
   public ST visitVarsInitList(GeometricsParser.VarsInitListContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsInitObject(GeometricsParser.VarsInitObjectContext ctx) {
      ST decl = template.getInstanceOf("declVar");

      String type = ctx.OBJECT().getText();
      String convertedType = typesAssoc.containsKey(type) ? typesAssoc.get(type) : type; 
      String var = ctx.ID().getText();
      decl.add("type", convertedType);
      decl.add("var", var);
      varsTypes.put(var, convertedType);

      String value = "";
      switch(type) {
         case "Number":
            decl.add("stat", visit(ctx.attribs()));
            value = ctx.attribs().var;
            break;
         case "Label":
            value = visit(ctx.attribs()).render();
            break;
         case "Point":
            decl.add("stat", visit(ctx.attribs()));
            value = ctx.attribs().var;
            break;
      }
      decl.add("value", value);

      return decl;
   }

   @Override
   public ST visitVarsInitFigure(GeometricsParser.VarsInitFigureContext ctx) {
      String type = ctx.FIGURE().getText();
      String var = ctx.ID().getText();
      ST figureMaking = template.getInstanceOf("figureMaking");
      figureMaking.add("type",type);
      figureMaking.add("var",var);
      figureMaking.add("properties", visit(ctx.blockSet()));

      switch(type){
         case "Rectangle":
            ST rectangleMaking = template.getInstanceOf("rectangleMaking");
            rectangleMaking.add("var", var);
            figureMaking.add("stat", rectangleMaking.render());
            break;
      }
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsInitFunc(GeometricsParser.VarsInitFuncContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitInlineSet(GeometricsParser.InlineSetContext ctx) {
      // ordernar InLineSet tendo em conta o construtor
      // (add blank construtor)
      return visitChildren(ctx);
   }

   // grupo 1
   @Override
   public ST visitBlockSet(GeometricsParser.BlockSetContext ctx) {
      ST stats = template.getInstanceOf("stats"); 
      for(int i=0; i<ctx.inlineSet().size(); i++){
         stats.add("stat", ",");
         stats.add("stat", ctx.inlineSet(i));
      }
      return stats;
   }

   @Override
   public ST visitAttribs(GeometricsParser.AttribsContext ctx) {
      if (ctx.expr() != null) {
         ST visitExpr = visit(ctx.expr());
         ctx.var = ctx.expr().var;
         return visitExpr;
      }
      else if (ctx.STRING() != null) {
         ST stats = template.getInstanceOf("stats");
         stats.add("stat", ctx.STRING().getText());
         return stats;
      }

      return null;
   }

   @Override
   public ST visitFuncCall(GeometricsParser.FuncCallContext ctx) {
      ST func = template.getInstanceOf("call_func");
      func.add("name", ctx.ID().getText());
      for (int i = 0; i < ctx.expr().size() - 1; i++) {
         func.add("value", ctx.expr(i) + ",");
      }
      func.add("value", ctx.expr(ctx.expr().size() - 1));
      return func;
   }

   @Override
   public ST visitVarsSetProperties(GeometricsParser.VarsSetPropertiesContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsSetExpr(GeometricsParser.VarsSetExprContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsSetCalc(GeometricsParser.VarsSetCalcContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitColorId(GeometricsParser.ColorIdContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitColorHex(GeometricsParser.ColorHexContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitColorRGB(GeometricsParser.ColorRGBContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitListAdd(GeometricsParser.ListAddContext ctx) {
      ST addList = template.getInstanceOf("add_to_list");
      addList.add("var", ctx.ID(0));
      addList.add("value", ctx.ID(1));
      return addList;
   }

   // grupo 2
   @Override
   public ST visitListRemove(GeometricsParser.ListRemoveContext ctx) {
      ST remList = template.getInstanceOf("remove_from_list");
      remList.add("var", ctx.ID());
      remList.add("value", ctx.expr());
      return visitChildren(ctx);
   }

   @Override
   public ST visitConditional(GeometricsParser.ConditionalContext ctx) {
      ST conditional = template.getInstanceOf("conditional");
      conditional.add("stat", visit(ctx.booleanLogic()));
      conditional.add("var", ctx.booleanLogic().var);
      ctx.blockStats().stream().forEach(stat -> conditional.add("stat_true", visit(stat).render()));
      return conditional;
   }

   @Override
   public ST visitBlockStats(GeometricsParser.BlockStatsContext ctx) {
      ST stats = template.getInstanceOf("stats");
      if (ctx.stats() != null)
         stats.add("stat", visit(ctx.stats()));
      else if (ctx.stop != null)
         stats.add("stat", "break;");
      return stats;
   }

   @Override
   public ST visitLoop(GeometricsParser.LoopContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitEachTime(GeometricsParser.EachTimeContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitEachWhile(GeometricsParser.EachWhileContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitEachFor(GeometricsParser.EachForContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitEasteregg(GeometricsParser.EastereggContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitAngle(GeometricsParser.AngleContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitTime(GeometricsParser.TimeContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitPoint(GeometricsParser.PointContext ctx) {
      return visitChildren(ctx);
   }

   private STGroup template = new STGroupFile("template.stg");

   private String newExprVar() {
      exprVars++;
      return "varExpr" + exprVars;
   }

   private String newPointExprVar() {
      pointExprVars++;
      return "varPointExpr" + pointExprVars;
   }

   private String newBoolExprVar() {
      boolExprVars++;
      return "varBoolExpr" + boolExprVars;
   }

   boolean hasVars = false;
   private int exprVars = 0;
   private int pointExprVars = 0;
   private int boolExprVars = 0;
   private Map<String, String> symbAssoc = Map.ofEntries(
      entry("or", "||"),
      entry("and", "&&"),
      entry("diffente", "^"),
      entry("equals", "=="),
      entry("greater", ">"),
      entry("lower", "<"),
      entry("greater equal", ">="),
      entry("lower equal", "<="),
      entry("|", "||"),
      entry("&", "&&"),
      entry("!", "!="),
      entry("=", "=="));
   private Map<String, String> typesAssoc = Map.ofEntries(
      entry("Label", "String"),
      entry("Number", "double"),
      entry("Point", "structures.Point"));
   private Map<List<String>, String> funcTypesAssoc = Map.ofEntries(
      entry(Arrays.asList("width", "height", "diameter", "radius", "x", "y"), "double"),
      entry(Arrays.asList("center", "startingPoint", "endingPoint", "p0", "p1", "p2", "size"), "structures.Point"),
      entry(Arrays.asList("filled", "visibility", "collide"), "boolean"),
      entry(Arrays.asList("color"), "Color"),
      entry(Arrays.asList("angle"), "Angle"));
   private HashMap<String, String> varsTypes = new HashMap<>();
}
