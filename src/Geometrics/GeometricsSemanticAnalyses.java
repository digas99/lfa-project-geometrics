import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import java.io.File;

public class GeometricsSemanticAnalyses extends GeometricsBaseVisitor<String> {

   // grupo 1
   @Override public String visitProgram(GeometricsParser.ProgramContext ctx) {
      List<String> values = ctx.stats().stream().map(stat -> visit(stat)).collect(Collectors.toList());
      values.addAll(ctx.use().stream().map(use -> visit(use)).collect(Collectors.toList()));
      values.add(visit(ctx.time()));
      return anyNull(values) ? null : ctx.STRING().getText();
   }

   @Override public String visitUse(GeometricsParser.UseContext ctx) {
      int line = ctx.getStart().getLine();
      int col = ctx.getStart().getCharPositionInLine();
      // check if file is file exists
      String path = ctx.STRING().getText();
      File file = new File(path);
      if (!file.exists() || !file.isFile()) {
         throwError(line, col, String.format(fileDoesNotExit, path));
         path = null;
      }
      else {
         String[] split = path.split(".");
         // check for Beaver file extension
         if (!split[split.length-1].equals("bvr")) {
            throwError(line, col, String.format(fileNotValid, path));
            path = null;
         }
      }

      List<String> values = ctx.useAttribs().stream().map(attr -> visit(attrib)).collect(Collectos.toList());
      values.add(path);

      return anyNull(values) ? null : path;
   }

   @Override public String visitUseAttribs(GeometricsParser.UseAttribsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStatVarsInit(GeometricsParser.StatVarsInitContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStatVarsSet(GeometricsParser.StatVarsSetContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStatList(GeometricsParser.StatListContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStatDraw(GeometricsParser.StatDrawContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStatLoop(GeometricsParser.StatLoopContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStatConditional(GeometricsParser.StatConditionalContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStatFuncCall(GeometricsParser.StatFuncCallContext ctx) {
      return visitChildren(ctx);
   }

   // grupo 2
   @Override public String visitStatEasterEgg(GeometricsParser.StatEasterEggContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStatConsoleLog(GeometricsParser.StatConsoleLogContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStatContainer(GeometricsParser.StatContainerContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitContainer(GeometricsParser.ContainerContext ctx) {
      return visitChildren(ctx);
   }

//Expr functions start------------------------------------------------------
   @Override public String visitExprMultDiv(GeometricsParser.ExprMultDivContext ctx) {
      boolean expr0 = visit(ctx.expr(0));
      boolean expr1 = visit(ctx.expr(1));
      return expr0 && expr1;
   }

   @Override public String visitExprAddSub(GeometricsParser.ExprAddSubContext ctx) {
      boolean expr0 = visit(ctx.expr(0));
      boolean expr1 = visit(ctx.expr(1));
      return expr0 && expr1;
   }

   @Override public String visitExprParentesis(GeometricsParser.ExprParentesisContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitExprId(GeometricsParser.ExprIdContext ctx) {
      return visit(ctx.identifiers());
   }

   @Override public String visitExprUnary(GeometricsParser.ExprUnaryContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitExprPower(GeometricsParser.ExprPowerContext ctx) {
      boolean expr0 = visit(ctx.expr(0));
      boolean expr1 = visit(ctx.expr(1));
      return expr0 && expr1;
   }
   // grupo 1
   @Override public String visitExprNumber(GeometricsParser.ExprNumberContext ctx) {
      

   @Override public String visitPointsExprCalc(GeometricsParser.PointsExprCalcContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitPointsExprPoint(GeometricsParser.PointsExprPointContext ctx) {
      return visit(ctx.point());
   }

   @Override public String visitBoolLogicExpr(GeometricsParser.BoolLogicExprContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitVarsSetExpr(GeometricsParser.VarsSetExprContext ctx) {
      return visitChildren(ctx);
   }
  //Expr functions end------------------------------------------------------
   @Override public String visitIdProp(GeometricsParser.IdPropContext ctx) {
      int line = ctx.getStart().getLine();
      int col = ctx.getStart().getCharPositionInLine();
      String type = getType(ctx.ID(0).getText());
      if (type != null) {
         String[] propsList = propsAssoc.get(type);
         String prop = ctx.ID(1).getText();
         if (!contains(propsList, prop))
            throwError(line, col, String.format(notPropOfFigureErrorMessage, prop, type));
      }
      throwError(line, col, String.format(notInitVarErrorMessage, type));
      return type;

   }

   @Override public String visitId(GeometricsParser.IdContext ctx) {
      return getType(ctx.ID().getText());
   }

   @Override public String visitPointsId(GeometricsParser.PointsIdContext ctx) {
      return visit(ctx.identifiers());
   }

   @Override public String visitPointsCenter(GeometricsParser.PointsCenterContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitBoolLogicParentesis(GeometricsParser.BoolLogicParentesisContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitBoolLogicNot(GeometricsParser.BoolLogicNotContext ctx) {
      return visitChildren(ctx);
   }
   
   // grupo 2
   @Override public String visitBoolLogicOps(GeometricsParser.BoolLogicOpsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitBoolLogicCollides(GeometricsParser.BoolLogicCollidesContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitBoolLogicTruthval(GeometricsParser.BoolLogicTruthvalContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitVarsInit(GeometricsParser.VarsInitContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitVarsOnlyInit(GeometricsParser.VarsOnlyInitContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitVarsInitList(GeometricsParser.VarsInitListContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitVarsInitObject(GeometricsParser.VarsInitObjectContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitVarsInitFigure(GeometricsParser.VarsInitFigureContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitVarsInitFunc(GeometricsParser.VarsInitFuncContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitInlineSet(GeometricsParser.InlineSetContext ctx) {
      return visitChildren(ctx);
   }
   
   // grupo 1
   @Override public String visitBlockSet(GeometricsParser.BlockSetContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitAttribs(GeometricsParser.AttribsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitFuncCall(GeometricsParser.FuncCallContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitVarsSetProperties(GeometricsParser.VarsSetPropertiesContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitVarsSetCalc(GeometricsParser.VarsSetCalcContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitColorId(GeometricsParser.ColorIdContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitColorHex(GeometricsParser.ColorHexContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitColorRGB(GeometricsParser.ColorRGBContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitListAdd(GeometricsParser.ListAddContext ctx) {
      return visitChildren(ctx);
   }
   
   // grupo 2
   @Override public String visitListRemove(GeometricsParser.ListRemoveContext ctx) {
      return visitChildren(ctx);
   }
   //If function
   @Override public String visitConditional(GeometricsParser.ConditionalContext ctx) {
      return visitChildren(ctx);
   }
   //Loop functions start -----------------------------------------
   @Override public String visitLoop(GeometricsParser.LoopContext ctx) {
      return visitChildren(ctx);
      //Verify time and loopSpecifics(eachtime,while,for)
   }

   @Override public String visitEachTime(GeometricsParser.EachTimeContext ctx) {
      return visit(ctx.stats());
      //Verify stats
      //Verify stop(interruption trigger)
   }

   @Override public String visitEachWhile(GeometricsParser.EachWhileContext ctx) {
      String bologic = visit(ctx.booleanlogic());
      String stat = visit(ctx.stats());
      return null;
      //Verify booleanLogic and stats
      //Verify stop(interruption trigger)
   }

   @Override public String visitEachFor(GeometricsParser.EachForContext ctx) {
      String expr0 = visit(ctx.expr(0));
      String expr1 = visit(ctx.expr(1));
      String stat = visit(ctx.stats());
      
      return null;
      //Verify ID, 2 expr and stats
      //Verify stop(interruption trigger)
   }
   //Loop functions end -----------------------------------------
   @Override public String visitEasteregg(GeometricsParser.EastereggContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitAngle(GeometricsParser.AngleContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitTime(GeometricsParser.TimeContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitPoint(GeometricsParser.PointContext ctx) {
      boolean expr0 = visit(ctx.expr(0));
      boolean expr1 = visit(ctx.expr(1));
      return expr0 && expr1; 
   }

   private static boolean isNumber(String val) {
      try {
         Integer.parseInt(val);
      } catch(NumberFormatException e) {
         return false;
      }
      return true;
   }

   private static boolean allNull(List<String> values) {
      return values.stream().allMatch(val -> val == null);
   }

   private static boolean allNull(String[] values) {
      return allNull(Arrays.asList(values));
   }

   private static boolean allTrue(boolean[] values) {
      return IntStream.range(0, values.length).mapToObj(i -> values[i]).allMatch(val -> val);
   }

   private static boolean allTrue(List<String> values) {
      return values.stream().allMatch(val -> val);
   }

   private static boolean anyNull(List<String> values) {
      return values.stream().anyMatch(val -> val == null);
   }

   private static boolean anyNull(String[] values) {
      return anyNull(Arrays.asList(values));
   }

   private static boolean anyTrue(boolean[] values) {
      return IntStream.range(0, values.length).mapToObj(i -> values[i]).anyMatch(val -> val);
   }

   private static boolean anyTrue(List<String> values) {
      return values.stream().anyMatch(val -> val);
   }

   private static boolean someTrue(boolean[] values) {
      return !allTrue(values) && !noneTrue(values);
   }

   private static boolean someTrue(List<String> values) {
      return !allTrue(values) && !noneTrue(values);
   }

   private static boolean someNull(String[] values) {
      return !allNull(values) && !noneNull(values);
   }

   private static boolean someNull(List<String> values) {
      return !allNull(values) && !noneNull(values);
   }

   private static boolean noneTrue(boolean[] values) {
      return !anyTrue(values);
   }

   private static boolean noneTrue(List<String> values) {
      return !anyTrue(values);
   }

   private static boolean noneNull(String[] values) {
      return !anyNull(values);
   }

   private static boolean noneNull(List<String> values) {
      return !anyNull(values);
   }

   private static void throwWarning(int line, int col, String message) {
      System.err.printf("Warning@%d:%d -> %s\n", line, col, message);
   }

   private static void throwError(int line, int col, String message) {
      System.err.printf("Error@%d:%d -> %s\n", line, col, message);
   }

   private String getType(String var) {
      if (varsPoint.contains(var))
         return "Point";
      if (isRectangle.contains(var))
         return "Rectangle";
      if (isCircle.contains(var))
         return "Circle";
      if (isLine.contains(var))
         return "Line";
      if (isTriangle.contains(var))
         return "Triangle";
      if (isVar.contains(var))
         return "Var";
      if (isPallete.contains(var))
         return "Pallete";
      if (isColor.contains(var))
         return "Color";
      if (isFigure.contains(var))
         return "Figure";
      return null;
   }

   private static boolean contains(String[] arr, String target) {
      for (String s : arr) {
         if (s.equals(target))
            return true;
      }
      return false;
   }

   static private String notInitVarErrorMessage = "Variable %s might have not been initialized!";
   static private String notValidColorCodeErrorMessage = "%s is not a valid color code!";
   static private String notAFigureErrorMessage = "%s is not a Figure!";
   static private String notPropOfFigureErrorMessage = "%s is not a valid property of %s!";
   static private String notValueOfPropErrorMessage = "Invalid value for property %s!";
   static private String notVarOfPropErrorMessage = "Invalid variable for property!";
   static private String multVarInitWarningMessage = "Variable %s was initialized multiple times!";
   static private String notVarTypeNumberErrorMessage = "%s is not a variable of type Number!";
   static private String colorNotInPalleteErrorMessage = "Variable %s is not in %s!";
   static private String fileDoesNotExit = "File %s is invalid or doesn't exist!";
   static private String fileNotValid = "%s is not a valid Beaver File!";

   private List<String> vars = new ArrayList<>();
   private List<String> varsColor = new ArrayList<>();
   private List<String> varsPoint = new ArrayList<>();
   private List<String> varsRectangle = new ArrayList<>();
   private List<String> varsCircle = new ArrayList<>();
   private List<String> varsLine = new ArrayList<>();
   private List<String> varsTriangle = new ArrayList<>();
   private List<String> varsPallete = new ArrayList<>();
   private List<String> varsFigure = new ArrayList<>();
   private HashMap<String, List<String>> palletes = new HashMap<>();
   private String currentVar;
   private String currentPallete;
   private int openFigures = 0;

   private HashMap<String, String[]> propsAssoc = new HashMap<>();
   static private String[] pointProps = {"x", "y"};
   static private String[] rectangleProps = {"filled", "collide", "visibility", "color", "border", "width", "height", "center", "angle", "size"};
   static private String[] circleProps = {"filled", "collide", "visibility", "color", "border", "diameter", "radius", "center", "startingPoint", "endingPoint"};
   static private String[] lineProps = {"filled", "collide", "visibility", "color", "border", "angle", "center", "startingPoint", "endingPoint", "length"};
   static private String[] triangleProps = {"filled", "collide", "visibility", "color", "border", "p0", "p1", "p2"};
   static private String[] figureProps = {"filled", "collide", "visibility", "color", "border", "center"};
}
