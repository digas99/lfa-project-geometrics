package Geometrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import java.io.File;

public class GeometricsSemanticAnalyses extends GeometricsBaseVisitor<String> {

   // grupo 1
   @Override
   public String visitProgram(GeometricsParser.ProgramContext ctx) {
      List<String> values = ctx.stats().stream().map(stat -> visit(stat)).collect(Collectors.toList());
      values.addAll(ctx.use().stream().map(use -> visit(use)).collect(Collectors.toList()));
      return anyNull(values) ? null : ctx.STRING().getText();
   }

   @Override
   public String visitUse(GeometricsParser.UseContext ctx) {
      int line = ctx.getStart().getLine();
      int col = ctx.getStart().getCharPositionInLine();
      // check if file is file exists
      String path = ctx.STRING().getText();
      File file = new File(path);
      if (!file.exists() || !file.isFile()) {
         throwError(line, col, String.format(fileDoesNotExitErrorMessage, path));
         path = null;
      } else {
         String[] split = path.split(".");
         // check for Beaver file extension
         if (!split[split.length - 1].equals("bvr")) {
            throwError(line, col, String.format(fileNotValidErrorMessage, path));
            path = null;
         }
      }

      List<String> values = ctx.useAttribs().stream().map(attr -> visit(attr)).collect(Collectors.toList());
      values.add(path);

      return anyNull(values) ? null : path;
   }

   @Override
   public String visitUseAttribs(GeometricsParser.UseAttribsContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public String visitStatVarsInit(GeometricsParser.StatVarsInitContext ctx) {
      return visit(ctx.varsInit());
   }

   @Override
   public String visitStatVarsSet(GeometricsParser.StatVarsSetContext ctx) {
      return visit(ctx.varsSet());
   }

   @Override
   public String visitStatDraw(GeometricsParser.StatDrawContext ctx) {
      // check if they are all figures
      if (!allTrue(ctx.ID().stream().map(id -> {
         String var = id.getText();
         boolean valid = contains(figureTypes, getType(var));
         if (!valid)
            throwError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                  String.format(notAFigureErrorMessage, var));
         return valid;
      }).collect(Collectors.toList())))
         return null;
      return "draw";
   }

   @Override
   public String visitStatLoop(GeometricsParser.StatLoopContext ctx) {
      return visit(ctx.loop());
   }

   @Override
   public String visitStatConditional(GeometricsParser.StatConditionalContext ctx) {
      return visit(ctx.conditional());
   }

   @Override
   public String visitStatConsoleLog(GeometricsParser.StatConsoleLogContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public String visitStatContainer(GeometricsParser.StatContainerContext ctx) {
      return visit(ctx.container());
   }

   @Override
   public String visitContainer(GeometricsParser.ContainerContext ctx) {
      return visitChildren(ctx);
   }

   // Expr functions start------------------------------------------------------
   @Override
   public String visitExprMultDiv(GeometricsParser.ExprMultDivContext ctx) {
      String expr0 = visit(ctx.expr(0));
      String expr1 = visit(ctx.expr(1));
      if(anyNull(expr0,expr1))
      return null;
   return "mult/div";
   }

   @Override
   public String visitExprAddSub(GeometricsParser.ExprAddSubContext ctx) {
      String expr0 = visit(ctx.expr(0));
      String expr1 = visit(ctx.expr(1));
      if(anyNull(expr0,expr1))
      return null;
   return "add/sub";
   }

   @Override
   public String visitExprParentesis(GeometricsParser.ExprParentesisContext ctx) {
      return visit(ctx.expr());
   }

   @Override
   public String visitExprId(GeometricsParser.ExprIdContext ctx) {
      return visit(ctx.identifiers());
   }

   @Override
   public String visitExprUnary(GeometricsParser.ExprUnaryContext ctx) {
      return visit(ctx.expr());
   }

   @Override
   public String visitExprPower(GeometricsParser.ExprPowerContext ctx) {
      String expr0 = visit(ctx.expr(0));
      String expr1 = visit(ctx.expr(1));
      if(anyNull(expr0,expr1))
      return null;
   return "Power";
   }

   // grupo 1
   @Override
   public String visitExprNumber(GeometricsParser.ExprNumberContext ctx) {
      String n = ctx.NUMBER().getText();
      if (!isNumber(n))
         return null;
      return n;
   }

   @Override
   public String visitPointsExprCalc(GeometricsParser.PointsExprCalcContext ctx) {
      String expr0 = visit(ctx.pointsExpr(0));
      String expr1 = visit(ctx.pointsExpr(1));
      if (anyNull(expr0,expr1))
         return null;
      return expr0 + ";" + expr1;
   }

   @Override
   public String visitPointsExprPoint(GeometricsParser.PointsExprPointContext ctx) {
      return visit(ctx.point());
   }

   @Override
   public String visitBoolLogicExpr(GeometricsParser.BoolLogicExprContext ctx) {
      return visit(ctx.expr());
   }

   @Override
   public String visitVarsSetExpr(GeometricsParser.VarsSetExprContext ctx) {
      return visitChildren(ctx);   
   }
   
   @Override
   public String visitIdentifiers(GeometricsParser.IdentifiersContext ctx) {
      return visitChildren(ctx);   
   }

   @Override
   public String visitPointsId(GeometricsParser.PointsIdContext ctx) {
      return visit(ctx.identifiers());
   }
   

   @Override
   public String visitPointsCenter(GeometricsParser.PointsCenterContext ctx) {
      return "container-center";
   }

   @Override
   public String visitBoolLogicParentesis(GeometricsParser.BoolLogicParentesisContext ctx) {
      return visit(ctx.booleanLogic());
   }

   @Override
   public String visitBoolLogicNot(GeometricsParser.BoolLogicNotContext ctx) {
      return visit(ctx.booleanLogic());
   }

   // grupo 2
   @Override
   public String visitBoolLogicOps(GeometricsParser.BoolLogicOpsContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public String visitBoolLogicCollides(GeometricsParser.BoolLogicCollidesContext ctx) {
      String id1 = ctx.ID(0).getText();
      String id2 = ctx.ID(1).getText();
      if(anyNull(id1,id2)){
         return null;
      }
      return id1 + "colides" + id2 ;
   }

   @Override
   public String visitBoolLogicTruthval(GeometricsParser.BoolLogicTruthvalContext ctx) {
      return ctx.TRUTHVAL().getText();
   }

   @Override
   public String visitVarsOnlyInit(GeometricsParser.VarsOnlyInitContext ctx) {
      
      String resObj = ctx.OBJECT().getText();
      String resFig = ctx.FIGURE().getText();
      String resID = ctx.ID().getText();
     
      if(resID == null){
         return null;
      }
      else if(resObj == null && resFig == null){
         return null;
      }
      else if(resFig == null){
         return resObj + resID;
      }
      else{
      return resFig + resID ;
      }
   }

   @Override
   public String visitVarsInitObject(GeometricsParser.VarsInitObjectContext ctx) {
      String resObj = ctx.OBJECT().getText();
      String resID = ctx.ID().getText();
      String attr = visit(ctx.attribs());
      if(anyNull(resObj,resID,attr)){
         return null;
      }
      return resObj + resID + "->" + attr;
   }

   @Override
   public String visitVarsInitFigure(GeometricsParser.VarsInitFigureContext ctx) {
      String id = ctx.ID().getText();
      idOfBlockSet = id;
      String type = ctx.FIGURE().getText();

      varsTypes.put(id, type);
      return visitChildren(ctx);
   }

   @Override
   public String visitInlineSet(GeometricsParser.InlineSetContext ctx) {
      String id = ctx.ID().getText();
      switch(visit(ctx.attribs())){
         case "expr":
            if (contains(propsAsExpr, id))
               return id;
            break;
         case "pointsExpr":
            if (contains(propsAsPointsExpr, id))
               return id;
            break;
         case "angle":
            if (contains(propsAsAngle, id))
               return id;
            break;
         case "TRUTHVAL":
            if (contains(propsAsTruthVal, id))
               return id;
            break;
      }

      throwError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), String.format(notValueOfPropErrorMessage, id));
      return null;
   }

   // grupo 1
   @Override public String visitBlockSet(GeometricsParser.BlockSetContext ctx) {
      String id = idOfBlockSet;
      String type = varsTypes.get(id);
      List<String> props = ctx.inlineSet().stream().map(value -> visit(value)).collect(Collectors.toList());
      boolean valid = false;
      switch(type) {
         case "Figure":
            valid = containsAll(figureProps, props);
            break;
         case "Triangle":
            valid = containsAll(triangleProps, props);
            break;          
         case "Rectangle":
            valid = containsAll(rectangleProps, props);
            break;
         case "Circle":
            valid = containsAll(circleProps, props);
            break;
         case "Line":
            valid = containsAll(lineProps, props);
            break;
      }
      
      if (!valid)
         throwError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), String.format(invalidPropsOfFigureErrorMessage, id));

      return valid ? id : null;
   }

   @Override
   public String visitAttribs(GeometricsParser.AttribsContext ctx) {
      if(ctx.STRING() != null)
         return "STRING";
      else if(ctx.expr() != null)
         return "expr";
      else if(ctx.angle() != null)
         return "angle";
      else if (ctx.time() != null)    
         return "time";
      else if (ctx.pointsExpr() != null)
         return "pointsExpr";
      else
         return "TRUTHVAL";
   }

   @Override
   public String visitVarsSetProperties(GeometricsParser.VarsSetPropertiesContext ctx) {
      String resID = ctx.ID().getText();
      String lineSET = visit(ctx.inlineSet());
      String blockSET = visit(ctx.blockSet());

      if(resID == null){

         return null;

      }
      else if(lineSET == null && blockSET == null){

         return null;
      }
      else if(lineSET == null){

         return resID + blockSET;
      }
      else{

         return resID + lineSET;
      }

     
   }

   @Override
   public String visitVarsSetCalc(GeometricsParser.VarsSetCalcContext ctx) {
      
      String resID = ctx.ID().getText();
      String expr0 = visit(ctx.expr());

      if(anyNull(resID,expr0)){
         return null;
      }
      
      return resID + expr0;
   }

   @Override public String visitColorId(GeometricsParser.ColorIdContext ctx) {
      return ctx.ID().getText();
      
   }

   @Override public String visitColorHex(GeometricsParser.ColorHexContext ctx) {
      String id = ctx.ID() != null ? ctx.ID().getText() : ctx.NUMBER().getText();
      // check if color code has more than 6 characters
      boolean valid = id.length() <= 6;
      if (!valid){
         throwError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), String.format(notValidColorCodeErrorMessage, id));
         return null;
      }   
      return id;
   }

   @Override public String visitColorRGB(GeometricsParser.ColorRGBContext ctx) {
      String expr0 = visit(ctx.expr(0));
      String expr1 = visit(ctx.expr(1));
      String expr2 = visit(ctx.expr(2));
      if(anyNull(expr0,expr1,expr2))
         return null;
      return expr0 + "," + expr1 + "," + expr2;   
   }

   @Override
   public String visitConditional(GeometricsParser.ConditionalContext ctx) {
       String bologic = visit(ctx.booleanLogic());
       List<String> blocks = ctx.blockStats().stream().map(attr -> visit(attr)).collect(Collectors.toList());
       if (bologic == null || blocks == null) {}
      return "if" + bologic + ":" + blocks + "end";
   }

   @Override
   public String visitBlockStats(GeometricsParser.BlockStatsContext ctx){
      return visit(ctx.stats());

   }

   @Override
   public String visitLoop(GeometricsParser.LoopContext ctx) {
      String timed = visit(ctx.time());
      String resID = ctx.ID().getText();
      List<String> resStats = ctx.stats().stream().map(stat -> visit(stat)).collect(Collectors.toList());
      if( anyNull(resStats) == true){
         return null;
      }
      else if (resID == null && timed == null){
         return null;
      }
      else if (resID == null) {
          return "each" + timed + resStats + "end";
      }
      else{
          return "each" + resID + resStats + "end";
      }
      
   }


   @Override
   public String visitAngle(GeometricsParser.AngleContext ctx) {
      return visit(ctx.expr());
   }

   @Override
   public String visitTime(GeometricsParser.TimeContext ctx) {
      int line = ctx.getStart().getLine();
      int col = ctx.getStart().getCharPositionInLine();
      String timed = visit(ctx.expr());
      if (!(isNumber(timed))){
         throwError(line, col, String.format(notVarTypeNumberErrorMessage, timed));
         return null;
      } 
      int time = Integer.parseInt(timed);
      if (time < 0)  
      {
         throwError(line, col, String.format(notValueOfPropErrorMessage, timed));
         return null;
      }
      return timed;
      
   }

   @Override
   public String visitPoint(GeometricsParser.PointContext ctx) {
      String res = null;
      String expr0 = visit(ctx.expr(0));
      String expr1 = visit(ctx.expr(1));
      if (expr0 == null || expr1 == null) {
         res = null;
      } else
         res = expr0;
      return res;
   }

   private static boolean isNumber(String val) {
      try {
         Integer.parseInt(val);
      } catch (NumberFormatException e) {
         return false;
      }
      return true;
   }

   private static boolean allNull(List<String> values) {
      return values.stream().allMatch(val -> val == null);
   }

   private static boolean allNull(String... values) {
      return allNull(Arrays.asList(values));
   }

   private static boolean allTrue(boolean... values) {
      return IntStream.range(0, values.length).mapToObj(i -> values[i]).allMatch(val -> val);
   }

   private static boolean allTrue(List<Boolean> values) {
      return values.stream().allMatch(val -> val);
   }

   private static boolean anyNull(List<String> values) {
      return values.stream().anyMatch(val -> val == null);
   }

   private static boolean anyNull(String... values) {
      return anyNull(Arrays.asList(values));
   }

   private static boolean anyTrue(boolean... values) {
      return IntStream.range(0, values.length).mapToObj(i -> values[i]).anyMatch(val -> val);
   }

   private static boolean anyTrue(List<Boolean> values) {
      return values.stream().anyMatch(val -> val);
   }

   private static boolean someTrue(boolean... values) {
      return !allTrue(values) && !noneTrue(values);
   }

   private static boolean someTrue(List<Boolean> values) {
      return !allTrue(values) && !noneTrue(values);
   }

   private static boolean someNull(String... values) {
      return !allNull(values) && !noneNull(values);
   }

   private static boolean someNull(List<String> values) {
      return !allNull(values) && !noneNull(values);
   }

   private static boolean noneTrue(boolean... values) {
      return !anyTrue(values);
   }

   private static boolean noneTrue(List<Boolean> values) {
      return !anyTrue(values);
   }

   private static boolean noneNull(String... values) {
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
      if (varsRectangle.contains(var))
         return "Rectangle";
      if (varsCircle.contains(var))
         return "Circle";
      if (varsLine.contains(var))
         return "Line";
      if (varsTriangle.contains(var))
         return "Triangle";
      if (vars.contains(var))
         return "Var";
      if (varsPallete.contains(var))
         return "Pallete";
      if (varsColor.contains(var))
         return "Color";
      if (varsFigure.contains(var))
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

   private static boolean containsAll(String[] arr, List<String> target) {
      for (String t : target) {
         if (!contains(arr, t))
            return false;
      }
      return true;
   }
  


   static private String notInitVarErrorMessage = "Variable %s might have not been initialized!";
   static private String notInitVarsErrorMessage = "Variable %s or variable %s might have not been initialized!";
   static private String notInit3VarsErrorMessage = "Variable %s,variable %s or variable %s might have not been initialized!";
   static private String notValidColorCodeErrorMessage = "%s is not a valid color code!";
   static private String notAFigureErrorMessage = "%s is not a Figure!";
   static private String notPropOfFigureErrorMessage = "%s is not a valid property of %s!";
   static private String invalidPropsOfFigureErrorMessage = "Invalid set of properties of %s!";
   static private String notValueOfPropErrorMessage = "Invalid value for property %s!";
   static private String notVarOfPropErrorMessage = "Invalid variable for property!";
   static private String multVarInitWarningMessage = "Variable %s was initialized multiple times!";
   static private String notVarTypeNumberErrorMessage = "%s is not a variable of type Number!";
   static private String colorNotInPalleteErrorMessage = "Variable %s is not in %s!";
   static private String notAPointErrorMessage = "Variable %s is not a Point";
   static private String fileDoesNotExitErrorMessage = "File %s is invalid or doesn't exist!";
   static private String fileNotValidErrorMessage = "%s is not a valid Beaver File!";

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
   private String idOfBlockSet = "";
   private HashMap<String, String> varsTypes = new HashMap<>();

   static private String[] figureTypes = { "Figure", "Rectangle", "Circle", "Triangle", "Line" };
   private HashMap<String, String[]> propsAssoc = new HashMap<>();
   static private String[] pointProps = { "x", "y" };
   static private String[] rectangleProps = { "filled", "collide", "display", "color", "border", "width", "height",
         "center", "angle", "size" };
   static private String[] circleProps = { "filled", "collide", "display", "color", "border", "diameter", "radius",
         "center", "startingPoint", "endingPoint" };
   static private String[] lineProps = { "filled", "collide", "display", "color", "border", "angle", "center",
         "startingPoint", "endingPoint", "length" };
   static private String[] triangleProps = { "filled", "collide", "display", "color", "border", "p0", "p1", "p2" };
   static private String[] figureProps = { "filled", "collide", "display", "color", "border", "center" };

   static private String[] propsAsTruthVal = { "filled", "display", "collide" };
   static private String[] propsAsExpr = { "center", "width", "height", "diameter", "radius", "color", "x", "y" };
   static private String[] propsAsPointsExpr = { "center", "startingPoint", "endingPoint", "p0", "p1", "p2", "size" };
   static private String[] propsAsColor = { "color" };
   static private String[] propsAsExprColor = { "border" };
   static private String[] propsAsAngle = { "angle" };
}