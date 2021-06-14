import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

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
      ctx.stats().stream().forEach(stat -> module.add("stat", visit(stat).render()));
      if (hasVars)
         module.add("hasVars", hasVars);
      return module;
   }

   @Override
   public ST visitUse(GeometricsParser.UseContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitUseAttribs(GeometricsParser.UseAttribsContext ctx) {
      return visitChildren(ctx);
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
      return visit(ctx.expr());
   }

   @Override
   public ST visitExprId(GeometricsParser.ExprIdContext ctx) {
      return visit(ctx.identifiers());
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
      return visitChildren(ctx);
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
      return visit(ctx.expr());
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
      // ST addToList = template.getInstanceOf("add_to_list");
      // String type, map = null;
      // if (ctx.OBJECT() != null) {
      // type = ctx.OBJECT().getText();
      // switch (type) {
      // case "Text":
      // map = "varsLabel";
      // break;
      // case "Point":
      // map = "varsPoint";
      // break;
      // case "Number":
      // map = "varsNumber";
      // break;
      // case "Angle":
      // map = "varsAngle";
      // break;
      // case "Time":
      // map = "varsTime";
      // break;
      // }
      // } else {
      // type = ctx.FIGURE().getText();
      // map = "varsFigure";
      // }

      // hasVars = true;
      // addToList.add("type", typesAssoc.containsKey(type) ? typesAssoc.get(type):
      // type);
      // addToList.add("varList", map);
      // addToList.add("var", ctx.ID().getText());
      // return addToList;
      ST decl = template.getInstanceOf("declVar");
      String type = ctx.OBJECT() != null ? ctx.OBJECT().getText() : ctx.FIGURE().getText();
      decl.add("type", typesAssoc.containsKey(type) ? typesAssoc.get(type) : type);
      decl.add("var", ctx.ID().getText());
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
      decl.add("type", typesAssoc.containsKey(type) ? typesAssoc.get(type) : type);
      decl.add("var", ctx.ID().getText());

      switch (type) {
         case "Number":
            decl.add("stat", visit(ctx.attribs()));
            decl.add("value", ctx.attribs().var);
            break;
      }

      return decl;
   }

   @Override
   public ST visitVarsInitFigure(GeometricsParser.VarsInitFigureContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsInitFunc(GeometricsParser.VarsInitFuncContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitInlineSet(GeometricsParser.InlineSetContext ctx) {
      return visitChildren(ctx);
   }

   // grupo 1
   @Override
   public ST visitBlockSet(GeometricsParser.BlockSetContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitAttribs(GeometricsParser.AttribsContext ctx) {
      if (ctx.expr() != null) {
         ST visitExpr = visit(ctx.expr());
         ctx.var = ctx.expr().var;
         return visitExpr;
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
   private Map<String, String> symbAssoc = Map.ofEntries(entry("or", "||"), entry("and", "&&"), entry("diffente", "^"),
         entry("equals", "=="), entry("greater", ">"), entry("lower", "<"), entry("greater equal", ">="),
         entry("lower equal", "<="), entry("|", "||"), entry("&", "&&"), entry("!", "!="), entry("=", "=="));
   private Map<String, String> typesAssoc = Map.ofEntries(entry("Text", "String"), entry("Number", "double"));
   private HashMap<String, String> map = new HashMap<>();
   private HashMap<String, Point> point_map = new HashMap<>();
}
