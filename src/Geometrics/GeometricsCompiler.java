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
      return visitChildren(ctx);
   }

   @Override
   public ST visitStatVarsSet(GeometricsParser.StatVarsSetContext ctx) {
      /*
       * ST vars = template.getInstanceOf("declNewTypeVar"); vars.add("type", );
       * vars.add("var", visit(varSet).ID()); vars.add("value", visit(varSet)); return
       * vars;
       */
      return null;
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
      return visitChildren(ctx);
   }

   @Override
   public ST visitStatContainer(GeometricsParser.StatContainerContext ctx) {
      return visit(ctx.container());
   }

   @Override
   public ST visitContainer(GeometricsParser.ContainerContext ctx) {
      // ST container = template.getInstanceOf("container");
      // container.add("var", ctx.ID());
      // container.add("id", );
      // for(int i = 0; i < ctx.stats().size(); i++){
      // container.add("stat", visit(stats).render());
      // }
      // return container;
      return null;
   }

   @Override
   public ST visitExprMultDiv(GeometricsParser.ExprMultDivContext ctx) {
      ST declVar = template.getInstanceOf("declVar");
      ctx.var = newExprVar();
      declVar.add("stat", visit(ctx.expr(0)));
      declVar.add("stat", visit(ctx.expr(1)));
      declVar.add("type", "boolean");
      declVar.add("var", ctx.var);
      declVar.add("value", ctx.expr(0).var);
      declVar.add("value", symbAssoc.get(ctx.op.getText()));
      declVar.add("value", ctx.expr(1).var);
      return declVar;
   }

   @Override
   public ST visitExprAddSub(GeometricsParser.ExprAddSubContext ctx) {
      ST declVar = template.getInstanceOf("declVar");
      ctx.var = newExprVar();
      declVar.add("stat", visit(ctx.expr(0)));
      declVar.add("stat", visit(ctx.expr(1)));
      declVar.add("type", "boolean");
      declVar.add("var", ctx.var);
      declVar.add("value", ctx.expr(0).var);
      declVar.add("value", symbAssoc.get(ctx.op.getText()));
      declVar.add("value", ctx.expr(1).var);
      return declVar;
   }

   @Override
   public ST visitExprParentesis(GeometricsParser.ExprParentesisContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitExprId(GeometricsParser.ExprIdContext ctx) {
      return visit(ctx.identifiers());
   }

   @Override
   public ST visitExprUnary(GeometricsParser.ExprUnaryContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitExprPower(GeometricsParser.ExprPowerContext ctx) {
      ST power = template.getInstanceOf("to_the_power");
      power.add("type", "Dobule");
      power.add("var", newExprVar());
      power.add("value1", ctx.expr(0));
      power.add("value2", ctx.expr(1));
      return power;
   }

   // grupo 1
   @Override
   public ST visitExprNumber(GeometricsParser.ExprNumberContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitIdProp(GeometricsParser.IdPropContext ctx) {
      // ST number = template.getText();
      // if (map.containsKey(ctx.ID().getText())) {
      // number.add("number", map.get(ctx.ID().getText()));
      // }
      // return number;
      return null;
   }

   @Override
   public ST visitId(GeometricsParser.IdContext ctx) {
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
      return visitChildren(ctx);
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
      ST addToMap = template.getInstanceOf("add_to_map");
      String type, map, var, value;
      if (ctx.OBJECT() != null) {
         type = ctx.OBJECT().getText();
         switch (type) {
            case "Text":
               map = "varsLabel";
               value = null;
               break;
            case "Point":
               map = "varsPoint";
               value = null;
               break;
            case "Number":
               map = "varsNumber";
               value = "0";
               break;
            case "Angle":
               map = "varsAngle";
               value = null;
               break;
            case "Time":
               map = "varsTime";
               value = null;
               break;
         }
      } else {
         type = ctx.FIGURE().getText();
         map = "varsFigure";
         value = null;
      }

      addToMap.add("type", type);
      addToMap.add("varMap", map);
      addToMap.add("var", ctx.ID().getText());
      addToMap.add("value", value);
      return addToMap;
   }

   @Override
   public ST visitVarsInitList(GeometricsParser.VarsInitListContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsInitObject(GeometricsParser.VarsInitObjectContext ctx) {
      ST objIn = template.getInstanceOf("declVar");

      String type;
      return visitChildren(ctx);
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
      return visitChildren(ctx);
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
      else
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

   int exprVars = 0;
   int pointExprVars = 0;
   int boolExprVars = 0;
   Map<String, String> symbAssoc = Map.ofEntries(entry("or", "||"), entry("and", "&&"), entry("diffente", "^"),
         entry("equals", "=="), entry("greater", ">"), entry("lower", "<"), entry("greater equal", ">="),
         entry("lower equal", "<="), entry("|", "||"), entry("&", "&&"), entry("!", "!="), entry("=", "=="));
   HashMap<String, String> map = new HashMap<>();
   HashMap<String, Point> point_map = new HashMap<>();
}
