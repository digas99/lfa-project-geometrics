package Geometrics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.Map.entry;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.misc.Triple;

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

      // ctx.stats().stream().forEach(stat -> module.add("stat",
      // visit(stat).render()));
      for (int i = 0; i < ctx.stats().size(); i++) {
         module.add("stat", visit(ctx.stats(i)));
      }

      module.add("stat", "if (firstPaint) firstPaint = false;");

      if (timerValue != null)
         module.add("timer", timerValue.render());

      for (Entry<String, String> entry : figuresVarAssoc.entrySet()) {
         module.add("varsInit", entry.getValue()+" "+entry.getKey()+";\n");
      }

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
      ST declVar = template.getInstanceOf("declVar");
      String type = "structures." + ctx.FIGURE().getText();
      String var = ctx.ID(0).getText();
      figuresVarAssoc.put(var+"Figure", type);
      declVar.add("type", type);
      declVar.add("var", var);
      declVar.add("value", "BeaverMain.getContainer(\"" + ctx.ID(1).getText() + "\")");

      ST figureMaking = template.getInstanceOf("figureMaking");
      figureMaking.add("type", type);
      figureMaking.add("var", var);
   
      ST rectangleMaking = template.getInstanceOf("rectangleMaking");
      rectangleMaking.add("var", var);
      figureMaking.add("stat", rectangleMaking.render());

      declVar.add("stat", figureMaking.render());

      return declVar;
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
   public ST visitStatDraw(GeometricsParser.StatDrawContext ctx) {
      ST stats = template.getInstanceOf("stats");
      ctx.ID().stream().forEach(id -> {
         ST draw = template.getInstanceOf("draw");
         String idText = id.getText();
         //draw.add("filled", filledAssoc.get(idText) ? "fill" : "draw");

         draw.add("var", idText);
         stats.add("stat", draw.render());
      });
      return stats;
   }

   @Override
   public ST visitStatLoop(GeometricsParser.StatLoopContext ctx) {
      return visit(ctx.loop());
   }

   @Override
   public ST visitStatConditional(GeometricsParser.StatConditionalContext ctx) {
      return visit(ctx.conditional());
   }

   // grupo 2
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
      String lastFunc = functions[functions.length - 1].split("\\(\\)")[0];
      String type = "";
      if (functions.length > 1) {
         for (List<String> funcList : funcTypesAssoc.keySet()) {
            if (funcList.contains(lastFunc))
               type = funcTypesAssoc.get(funcList);
         }
      } else
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
      if (ctx.value.getText().equals("-")) {
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
      stats.add("stat", ctx.ID(0).getText()+(ctx.ID().size() > 1 ? "Figure" : ""));
      for (int i = 1; i < ctx.ID().size(); i++) {
         stats.add("stat", ".");
         stats.add("stat", ctx.ID(i).getText() + "()");
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
      ST declVar = template.getInstanceOf("declVar");
      ctx.var = newPointExprVar();
      declVar.add("stat", visit(ctx.pointsExpr(0)));
      declVar.add("stat", visit(ctx.pointsExpr(1)));
      declVar.add("type", "structures.Point");
      declVar.add("var", ctx.var);
      String function = ctx.op.getText().equals("+") ? "sum" : "sub";
      declVar.add("value", "new structures.Point." + function + "(");
      declVar.add("value", ctx.pointsExpr(0).var);
      declVar.add("value", ",");
      declVar.add("value", ctx.pointsExpr(1).var);
      declVar.add("value", ")");
      return declVar;
   }

   @Override
   public ST visitPointsExprPoint(GeometricsParser.PointsExprPointContext ctx) {
      ST visit = visit(ctx.point());
      ctx.var = ctx.point().var;
      return visit;
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
      ST declVar = template.getInstanceOf("declVar");
      ctx.var = newBoolExprVar();
      declVar.add("stat", visit(ctx.booleanLogic()).render());
      declVar.add("type", "boolean");
      declVar.add("var", ctx.var);
      declVar.add("value", ctx.booleanLogic().var);
      return declVar;
   }

   @Override
   public ST visitBoolLogicNot(GeometricsParser.BoolLogicNotContext ctx) {
      ST declVar = template.getInstanceOf("declVar");
      ctx.var = newBoolExprVar();
      declVar.add("stat", visit(ctx.booleanLogic()).render());
      declVar.add("type", "boolean");
      declVar.add("var", ctx.var);
      declVar.add("value", "!"+ctx.booleanLogic().var);
      return declVar;
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
      ST declVar = template.getInstanceOf("declVar");
      ctx.var = newBoolExprVar();
      String left, right;
      if (ctx.board0 != null || ctx.board1 != null) {
         left = ctx.board0 != null ? ctx.board0.getText() : ctx.board1.getText();
         right = ctx.id0 != null ? ctx.id0.getText() : ctx.id1.getText();
      }
      else {
         left = ctx.id0.getText();   
         right = ctx.id1.getText();
      }

      declVar.add("value", left+"Bounds.intersects("+right+"Bounds)");
      declVar.add("type", "boolean");
      declVar.add("var", ctx.var);
      return declVar;
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
   public ST visitVarsInitObject(GeometricsParser.VarsInitObjectContext ctx) {
      ST decl = template.getInstanceOf("declVar");

      String type = ctx.OBJECT().getText();
      String convertedType = typesAssoc.containsKey(type) ? typesAssoc.get(type) : type;
      String var = ctx.ID().getText();
      figuresVarAssoc.put(var, typesAssoc.get(type));
      decl.add("var", "if (firstPaint) "+var);
      varsTypes.put(var, convertedType);

      String value = "";
      switch (type) {
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
      initFigure = true;
      String type = ctx.FIGURE().getText();
      String var = ctx.ID().getText();
      figuresVarAssoc.put(var+"Figure", "structures."+type);
      idOfBlockSet = var;
      ST figureMaking = template.getInstanceOf("figureMaking");
      figureMaking.add("type", type);
      figureMaking.add("var", var);
      figureMaking.add("properties", visit(ctx.blockSet()));

      switch (type) {
         case "Rectangle":
            ST rectangleMaking = template.getInstanceOf("rectangleMaking");
            rectangleMaking.add("var", var);
            figureMaking.add("stat", rectangleMaking.render());
            break;
         case "Circle":
            ST circleMaking = template.getInstanceOf("circleMaking");
            circleMaking.add("var", var);
            figureMaking.add("stat", circleMaking.render());
            break;
         case "Line":
            ST lineMaking = template.getInstanceOf("lineMaking");
            lineMaking.add("var", var);
            figureMaking.add("stat", lineMaking.render());
            break;
         case "Triangle":
            ST triangleMaking = template.getInstanceOf("triangleMaking");
            triangleMaking.add("var", var);
            figureMaking.add("stat", triangleMaking.render());
            break;
      }

      initFigure = false;
      return figureMaking;
   }

   @Override
   public ST visitInlineSet(GeometricsParser.InlineSetContext ctx) {
      String id = ctx.ID().getText();
      ST setter = template.getInstanceOf("figureSetter");
      String attrib = visit(ctx.attribs()).render();

      if (!contains(propsAsTruthVal, id))
         setter.add("stat", attrib);

      if (contains(propsAsExpr, id)) {
         setter.add("value", ctx.attribs().var);
      } else if (contains(propsAsAngle, id)) {
         setter.add("stat", String.format("\n"+ (initFigure ? "if(firstPaint)" : "") +"angles.put(\"%s\", %s);", idOfBlockSet, ctx.attribs().var));
         setter.add("value", ctx.attribs().var);
      } else if (contains(propsAsColor, id)) {
         setter.add("value", ctx.attribs().var);
      } else if (contains(propsAsPointsExpr, id)) {
         String[] split = attrib.split("[()]");
         if (split.length > 3)
            setter.add("stat", String.format("\npositions.put(\"%s\", new Pair<Double, Double>(%s));", idOfBlockSet, split[split.length - 2]));
         else
            setter.add("stat", String.format("\nif(firstPaint) positions.put(\"%s\", new Pair<Double, Double>(%s));", idOfBlockSet, split[split.length - 2]));
         setter.add("value", ctx.attribs().var);
      } else if (contains(propsAsTruthVal, id)) {
         if (id.equals("filled")) {
            filledAssoc.put(idOfBlockSet, attrib.equals("false") ? false : true);
         }
         setter.add("value", attrib);
      }
      
      setter.add("var", idOfBlockSet + "Figure");
      setter.add("funcName", (id.charAt(0) + "").toUpperCase() + id.substring(1));
      return setter;
   }

   // grupo 1
   @Override
   public ST visitBlockSet(GeometricsParser.BlockSetContext ctx) {
      ST stats = template.getInstanceOf("stats");
      ctx.inlineSet().stream().forEach(inline -> stats.add("stat", visit(inline)));
      return stats;
   }

   @Override
   public ST visitAttribs(GeometricsParser.AttribsContext ctx) {
      if (ctx.expr() != null) {
         ST visitExpr = visit(ctx.expr());
         ctx.var = ctx.expr().var;
         return visitExpr;
      } else if (ctx.STRING() != null) {
         ST stats = template.getInstanceOf("stats");
         stats.add("stat", ctx.STRING().getText());
         return stats;
      } else if (ctx.angle() != null) {
         ST visitAngle = visit(ctx.angle());
         ctx.var = ctx.angle().var;
         return visitAngle;
      } else if (ctx.TRUTHVAL() != null) {
         ST stats = template.getInstanceOf("stats");
         stats.add("stat", ctx.TRUTHVAL().getText());
         return stats;
      } else if (ctx.color() != null) {
         ST visitColor = visit(ctx.color());
         ctx.var = ctx.color().var;
         return visitColor;
      } else if (ctx.pointsExpr() != null) {
         ST visitExpr = visit(ctx.pointsExpr());
         ctx.var = ctx.pointsExpr().var;
         return visitExpr;
      }
      return null;
   }
   
   @Override
   public ST visitVarsSetProperties(GeometricsParser.VarsSetPropertiesContext ctx) {
      ST stats = template.getInstanceOf("stats");
      idOfBlockSet = ctx.ID().getText();
      stats.add("stat", ctx.inlineSet() != null ? visit(ctx.inlineSet()) : visit(ctx.blockSet()));
      return stats;
   }

   @Override
   public ST visitVarsSetExpr(GeometricsParser.VarsSetExprContext ctx) {
      ST set = template.getInstanceOf("set_var");
      set.add("id", ctx.ID().getText());
      set.add("stat", visit(ctx.expr()));
      set.add("value", ctx.expr().var);
      return set;
   }

   @Override
   public ST visitVarsSetCalc(GeometricsParser.VarsSetCalcContext ctx) {
      ST set = template.getInstanceOf("set_calc");
      set.add("id", ctx.ID().getText());
      String op = ctx.op.getText();
      set.add("value", symbAssoc.containsKey(op) ? symbAssoc.get(op) : op);
      set.add("value", visit(ctx.expr()));
      return set;
   }

   @Override
   public ST visitColorId(GeometricsParser.ColorIdContext ctx) {
      ST stats = template.getInstanceOf("stats");
      ctx.var = newExprVar();
      stats.add("stat", "structures.Color " + ctx.var + " = new structures.Color(" + ctx.ID().getText() + ");");
      return stats;
   }

   @Override
   public ST visitColorHex(GeometricsParser.ColorHexContext ctx) {
      ST stats = template.getInstanceOf("stats");
      ctx.var = newExprVar();
      stats.add("stat", String.format("structures.Color %s = new structures.Color(\"%s\");", ctx.var,
            ctx.ID() != null ? ctx.ID().getText() : ctx.NUMBER().getText()));
      return stats;
   }

   @Override
   public ST visitColorRGB(GeometricsParser.ColorRGBContext ctx) {
      ST stats = template.getInstanceOf("stats");
      stats.add("stat", visit(ctx.expr(0)));
      stats.add("stat", visit(ctx.expr(1)));
      stats.add("stat", visit(ctx.expr(2)));
      ctx.var = newExprVar();
      stats.add("stat", "structures.Color " + ctx.var + " = new structures.Color(new RGB((int)" + ctx.expr(0).var + ",(int)" + ctx.expr(1).var + ",(int)"
            + ctx.expr(2).var + "));");
      return stats;
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
      ST timeST = visit(ctx.time());
      timerValue = template.getInstanceOf("stats");
      timerValue.add("stat", timeST.render());
      timerValue.add("stat", "private Timer timer = new Timer((int) "+ctx.time().var+", this);");

      ST stats = template.getInstanceOf("stats");

      ctx.stats().stream().forEach(stat -> stats.add("stat", visit(stat)));

      stats.add("stat", "timer.start();");
      
      return stats;
   }

   @Override
   public ST visitAngle(GeometricsParser.AngleContext ctx) {
      ST stats = template.getInstanceOf("stats");
      stats.add("stat", visit(ctx.expr()));
      String type = ctx.type.getText();
      String varExpr = ctx.expr().var;
      ctx.var = newExprVar();
      anglesMap.put(idOfBlockSet, ctx.var);
      if (type.equals("rad"))
         stats.add("stat", "Angle " + ctx.var + " = new Angle(" + varExpr + ");");
      else
         stats.add("stat", "Angle " + ctx.var + " = new Angle((int) " + varExpr + ");");
      return stats;
   }

   @Override
   public ST visitTime(GeometricsParser.TimeContext ctx) {
      ST declVar = template.getInstanceOf("declVar");
      ctx.var = newExprVar();
      declVar.add("stat", visit(ctx.expr()));
      declVar.add("type", "double");
      declVar.add("var", ctx.var);
      declVar.add("value", ctx.expr().var);
      return declVar;
   }

   @Override
   public ST visitPoint(GeometricsParser.PointContext ctx) {
      ST declVar = template.getInstanceOf("declVar");
      ctx.var = newPointExprVar();
      declVar.add("stat", visit(ctx.expr(0)));
      declVar.add("stat", visit(ctx.expr(1)));
      declVar.add("type", "structures.Point");
      declVar.add("var", ctx.var);
      declVar.add("value", "new structures.Point(");
      String expr0Var = ctx.expr(0).var;
      String expr1Var = ctx.expr(1).var;
      positionsMap.put(idOfBlockSet, new Pair<String, String>(expr0Var, expr1Var));
      declVar.add("value", expr0Var);
      declVar.add("value", ",");
      declVar.add("value", expr1Var);
      declVar.add("value", ")");
      return declVar;
   }

   private static boolean contains(String[] arr, String target) {
      for (String s : arr) {
         if (s.equals(target))
            return true;
      }
      return false;
   }

   private STGroup template = new STGroupFile("Geometrics/template.stg");

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

   private boolean initFigure = false;
   private ST timerValue;
   private String idOfBlockSet = "";
   private boolean hasVars = false;
   private int exprVars = 0;
   private int pointExprVars = 0;
   private int boolExprVars = 0;
   private Map<String, String> symbAssoc = Map.ofEntries(entry("or", "||"), entry("and", "&&"), entry("diffente", "^"),
         entry("equals", "=="), entry("greater", ">"), entry("lower", "<"), entry("greater equal", ">="),
         entry("lower equal", "<="), entry("|", "||"), entry("&", "&&"), entry("!", "!="), entry("=", "=="));
   private Map<String, String> typesAssoc = Map.ofEntries(entry("Label", "String"), entry("Number", "double"),
         entry("Point", "structures.Point"));
   private Map<List<String>, String> funcTypesAssoc = Map
         .ofEntries(entry(Arrays.asList("width", "height", "diameter", "radius", "x", "y"), "double"),
               entry(Arrays.asList("center", "startingPoint", "endingPoint", "p0", "p1", "p2", "size"),
                     "structures.Point"),
               entry(Arrays.asList("filled", "visibility", "collide"), "boolean"),
               entry(Arrays.asList("color"), "Color"), entry(Arrays.asList("angle"), "Angle"));
   private HashMap<String, String> varsTypes = new HashMap<>();
   static private String[] propsAsTruthVal = { "filled", "display" };
   static private String[] propsAsExpr = { "width", "height", "diameter", "radius", "color", "x", "y", "thickness",
         "depth" };
   static private String[] propsAsPointsExpr = { "center", "startingPoint", "endingPoint", "p0", "p1", "p2", "size" };
   static private String[] propsAsColor = { "color" };
   static private String[] propsAsAngle = { "angle" };
   private HashMap<String, Pair<String, String>> positionsMap = new HashMap<>();
   private HashMap<String, String> anglesMap = new HashMap<>();
   private HashMap<String, String> figuresVarAssoc = new HashMap<>();
   private HashMap<String, Boolean> filledAssoc = new HashMap<>();
}
