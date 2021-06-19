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
      values.add(visit(ctx.time()));
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
   public String visitStatList(GeometricsParser.StatListContext ctx) {
      return visit(ctx.list());
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
   public String visitStatFuncCall(GeometricsParser.StatFuncCallContext ctx) {
      return visit(ctx.funcCall());
   }

   // grupo 2
   @Override
   public String visitStatEasterEgg(GeometricsParser.StatEasterEggContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public String visitStatConsoleLog(GeometricsParser.StatConsoleLogContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public String visitStatContainer(GeometricsParser.StatContainerContext ctx) {
      return visitChildren(ctx);
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
      return null;
   }

   @Override
   public String visitExprAddSub(GeometricsParser.ExprAddSubContext ctx) {
      String expr0 = visit(ctx.expr(0));
      String expr1 = visit(ctx.expr(1));
      return null;
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
      return null;
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
      if (expr0 == null || expr1 == null)
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
   // Expr functions end------------------------------------------------------

   // provavelmente para apagar entretanto
   // @Override public String visitIdProp(GeometricsParser.IdPropContext ctx) {
   // int line = ctx.getStart().getLine();
   // int col = ctx.getStart().getCharPositionInLine();
   // String var = ctx.ID(0).getText();
   // String type = getType(var);
   // if (type != null) {
   // String[] propsList = propsAssoc.get(type);
   // String prop = ctx.ID(1).getText();
   // if (!contains(propsList, prop))
   // throwError(line, col, String.format(notPropOfFigureErrorMessage, prop,
   // type));
   // else {
   // if (ctx.ID(2) != null) {
   // // if var[prop] is not a point
   // if (!contains(propsAsPointsExpr, prop))
   // throwError(line, col, String.format(notAPointErrorMessage,
   // var+"["+prop+"]"));
   // // otherwise, check if the third ID is a point property
   // else {
   // String thirdId = ctx.ID(2).getText();
   // if (!contains(pointProps, thirdId)) {
   // throwError(line, col, String.format(notPropOfFigureErrorMessage, thirdId,
   // "Point"));
   // type = null;
   // }
   // }
   // }
   // }
   // }
   // else
   // throwError(line, col, String.format(notInitVarErrorMessage, type));
   // return type;

   // }

   // todo
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
      return visitChildren(ctx);
   }

   @Override
   public String visitBoolLogicTruthval(GeometricsParser.BoolLogicTruthvalContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public String visitVarsOnlyInit(GeometricsParser.VarsOnlyInitContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public String visitVarsInitList(GeometricsParser.VarsInitListContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public String visitVarsInitObject(GeometricsParser.VarsInitObjectContext ctx) {

      return visitChildren(ctx);
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
   public String visitVarsInitFunc(GeometricsParser.VarsInitFuncContext ctx) {
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
         // case "funcCall":
         //    if (contains(propsAsFuncCall, id))
         //       return id;
         //    break;
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
      else if (ctx.funcCall() != null)
         return "funcCall";
      else
         return "TRUTHVAL";
   }

   @Override
   public String visitFuncCall(GeometricsParser.FuncCallContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public String visitVarsSetProperties(GeometricsParser.VarsSetPropertiesContext ctx) {
      String id = ctx.ID().getText();
      idOfBlockSet = id;
      return visitChildren(ctx);
   }

   @Override
   public String visitVarsSetCalc(GeometricsParser.VarsSetCalcContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitColorId(GeometricsParser.ColorIdContext ctx) {
      return visit(ctx.ID());
   }

   @Override public String visitColorHex(GeometricsParser.ColorHexContext ctx) {
      String id = ctx.ID() != null ? ctx.ID().getText() : ctx.NUMBER().getText();
      // check if color code has more than 6 characters
      boolean valid = id.length() <= 6;
      if (!valid)
         return null;
      return id;
   }

   @Override public String visitColorRGB(GeometricsParser.ColorRGBContext ctx) {
      String expr0 = visit(ctx.expr(0));
      String expr1 = visit(ctx.expr(1));
      String expr2 = visit(ctx.expr(2));
      if(expr0 == null || expr1 == null|| expr2 == null)
         return null;
      return expr0 + "," + expr1 + "," + expr2;   
   }

   @Override
   public String visitListAdd(GeometricsParser.ListAddContext ctx) {
      return visitChildren(ctx);
   }

   // grupo 2
   @Override
   public String visitListRemove(GeometricsParser.ListRemoveContext ctx) {

      
      return visitChildren(ctx);
   }

   // If function
   @Override
   public String visitConditional(GeometricsParser.ConditionalContext ctx) {
      // String res = null;
       String bologic = visit(ctx.booleanLogic());
       String blockstat = visit(ctx.blockStats());
       if (bologic == null || blockstat == null) {}
      //    res = null;
      // } else
      //    res = bologic;
      // return res;
      return "if" + bologic + ":" + blockstat + "end";
   }

   // Loop functions start -----------------------------------------
   @Override
   public String visitLoop(GeometricsParser.LoopContext ctx) {

      // String res = null;
      String timed = visit(ctx.time());
      String resID = visit(ctx.ID().getText());
      String loopSpec = visit(ctx.loopSpecifics());

      if (loopSpec == null){
         return null;
      }
      else if (resID == null && timed == null){
         return null;

      }

      else if (resID == null) {
          return "each" + timed + loopSpecifics + "end";

      }
      else{
          return "each" + resID + loopSpecifics + "end";
      }
      //    res = null;
      // } else
      //    res = loopSpec;

      // return res;
      // Verify time and loopSpecifics(eachtime,while,for)
   }

   @Override
   public String visitEachTime(GeometricsParser.EachTimeContext ctx) {
      return ":" + visit(ctx.stats()) + "stop";
      // return visit(ctx.stats());
      // Verify stats
      // Verify stop(interruption trigger)
      //return null;
   }

   @Override
   public String visitEachWhile(GeometricsParser.EachWhileContext ctx) {
      // String res = null;
       String bologic = visit(ctx.booleanLogic());
       String stat = visit(ctx.stats());
       if (bologic == null || stat == null) {
          return null;
       }
      //    res = null;
      // } else
      //    res = bologic;
      // return res;
      // Verify booleanLogic and stats
      // Verify stop(interruption trigger)
      return "until" + bologic + ":" + stat + "stop" ;
   }

   @Override
   public String visitEachFor(GeometricsParser.EachForContext ctx) {
      // String res = null;

       String resID = visit(ctx.ID().getText());
       String expr0 = visit(ctx.expr(0));
       String expr1 = visit(ctx.expr(1));
       String stat = visit(ctx.stats());

      if (resID == null || expr0 == null || expr1 == null || stat == null) {}
         return null;
      }
      return "with" + resID + "from" + expr0 + "to" + expr1 + ":" + stat + "stop";   

      // /*
      //  * else if(expr0 != null){ res = expr0; } else if(expr1 != null){ res = expr1; }
      //  */
      
      // Verify ID, 2 expr and stats
      //Verify stop(interruption trigger)
      //return null;
   }

   // Loop functions end -----------------------------------------
   @Override
   public String visitEasteregg(GeometricsParser.EastereggContext ctx) {
      return "where is " + visit(ctx.ID()) + "?";
   }

   @Override
   public String visitAngle(GeometricsParser.AngleContext ctx) {

      /*angled = visit (ctx.expr());
      double angle = Double.parseDouble(angled);
      if (angle > 2pi && angle < -2pi){
         return angled + "deg";
      }*/
      return visit(ctx.expr());
      // verificar se o angulo é menor ou maior que 360 no caso de ser º ou deg e se é
      // maior ou menor que 2pi caso rad?
   }

   @Override
   public String visitTime(GeometricsParser.TimeContext ctx) {

      String timed = visit(ctx.expr());
      int time = Integer.parseInt(timed);
      if (time < 0)  
      {
         return null;
      }
      return timed;
      //return visit(ctx.expr());
      // verificar se tempo é positivo ?
   }

   @Override
   public String visitPoint(GeometricsParser.PointContext ctx) {
      //String res = null;
      String expr0 = visit(ctx.expr(0));
      String expr1 = visit(ctx.expr(1));
      if (expr0 == null || expr1 == null) {
         return null;
      }
      return expr0 + "," + expr1;
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

   private static boolean allNull(String[] values) {
      return allNull(Arrays.asList(values));
   }

   private static boolean allTrue(boolean[] values) {
      return IntStream.range(0, values.length).mapToObj(i -> values[i]).allMatch(val -> val);
   }

   private static boolean allTrue(List<Boolean> values) {
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

   private static boolean anyTrue(List<Boolean> values) {
      return values.stream().anyMatch(val -> val);
   }

   private static boolean someTrue(boolean[] values) {
      return !allTrue(values) && !noneTrue(values);
   }

   private static boolean someTrue(List<Boolean> values) {
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

   private static boolean noneTrue(List<Boolean> values) {
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
