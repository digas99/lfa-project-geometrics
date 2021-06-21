package Beaver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BeaverSemanticAnalyses extends BeaverBaseVisitor<Boolean> {

   @Override public Boolean visitProgram(BeaverParser.ProgramContext ctx) {
      boolean statsValid = allTrue(ctx.stats().stream()
               .map(stat -> visit(stat))
               .collect(Collectors.toList()));
      boolean containersValid = visit(ctx.containers());
      return statsValid && containersValid;
   }

   @Override public Boolean visitContainers(BeaverParser.ContainersContext ctx) {
      return ctx.idsList() != null ? visit(ctx.idsList()) : true;
   }

   @Override public Boolean visitStatsPallete(BeaverParser.StatsPalleteContext ctx) {
      String var = ctx.ID().getText();
      if (hasBeenInit(var))
         throwWarning(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), String.format(multVarInitWarningMessage, var));

      currentPallete = var;
      palletes.put(var, new ArrayList<>());
      varsPallete.add(var);

      return visit(ctx.idsList());
   }

   @Override public Boolean visitStatsColor(BeaverParser.StatsColorContext ctx) {
      int line = ctx.getStart().getLine();
      int col = ctx.getStart().getCharPositionInLine();
      String var = ctx.ID(0).getText();
      
      if (hasBeenInit(var))
         throwWarning(line, col, String.format(multVarInitWarningMessage, var));

      varsColor.add(var);

      return ctx.color() != null ? visit(ctx.color()) : hasBeenInit(line, col, ctx.ID(1).getText());
   }

   @Override public Boolean visitStatsNumber(BeaverParser.StatsNumberContext ctx) {
      String var = ctx.ID().getText();

      if (hasBeenInit(var))
         throwWarning(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), String.format(multVarInitWarningMessage, var));

      vars.add(var);

      return visit(ctx.expr());
   }

   @Override public Boolean visitStatsPoint(BeaverParser.StatsPointContext ctx) {
      String var = ctx.ID().getText();

      if (hasBeenInit(var))
         throwWarning(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), String.format(multVarInitWarningMessage, var));

      varsPoint.add(var);

      boolean validVal = visit(ctx.pointsExpr());
      return validVal;
   }

   @Override public Boolean visitStatsSet(BeaverParser.StatsSetContext ctx) {
      String var = ctx.ID().getText();
      if (hasBeenInit(var))
         throwWarning(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), String.format(multVarInitWarningMessage, var));

      currentVar = var;
      switch(ctx.FIGURE().getText()) {
         case "Rectangle":
            varsRectangle.add(var);
            break;
         case "Circle":
            varsCircle.add(var);
            break;
         case "Line":
            varsLine.add(var);
            break;
         case "Triangle":
            varsTriangle.add(var);
            break;
      }
      
      return allTrue(ctx.inlineSet().stream()
               .map(inlineSet -> visit(inlineSet))
               .collect(Collectors.toList()));
   }

   @Override public Boolean visitStatsContains(BeaverParser.StatsContainsContext ctx) {
      int line = ctx.getStart().getLine();
      int col = ctx.getStart().getCharPositionInLine();
      String var = ctx.ID().getText();
      boolean isPoint = varsPoint.contains(var);
      boolean isRectangle = varsRectangle.contains(var);
      boolean isCircle = varsCircle.contains(var);
      boolean isLine = varsLine.contains(var);
      boolean isTriangle = varsTriangle.contains(var);
      boolean valid = true;
      openFigures++;
      if (noneTrue(Arrays.asList(isPoint, isRectangle, isCircle, isLine, isTriangle))) {
         // it is a number
         String errorMessage;
         if (vars.contains(var))
            errorMessage = notAFigureErrorMessage;
         else
            errorMessage = notInitVarErrorMessage; 

         throwError(line, col, String.format(errorMessage, var));
         valid = false;
      }

      boolean validStats = allTrue(ctx.stats().stream().map(stat -> visit(stat)).collect(Collectors.toList()));

      openFigures--;

      return valid && validStats;
   }

   @Override public Boolean visitIdsList(BeaverParser.IdsListContext ctx) {
      // check if there variables not initialized, and if so then return false
      // if all variables haven't returned true, then return false
      return allTrue(ctx.ID().stream()
               .map(var -> {
                  String varText = var.getText();
                  if (currentPallete != null)
                     palletes.get(currentPallete).add(varText);
                  return hasBeenInit(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), varText);
               })
               .collect(Collectors.toList()));
   }

   @Override public Boolean visitInlineSet(BeaverParser.InlineSetContext ctx) {
      int line = ctx.getStart().getLine();
      int col = ctx.getStart().getCharPositionInLine();
      String var = currentVar;
      String prop = ctx.ID().getText(); 
      boolean isRectangle = varsRectangle.contains(var);
      boolean isCircle = varsCircle.contains(var);
      boolean isLine = varsLine.contains(var);
      boolean isTriangle = varsTriangle.contains(var);
      String figure;
      boolean valid = false;
      if (isRectangle && !contains(rectangleProps, prop)) figure = "Rectangle";
      else if (isCircle && !contains(circleProps, prop)) figure = "Circle";
      else if (isLine && !contains(lineProps, prop)) figure = "Line";
      else if (isTriangle && !contains(triangleProps, prop)) figure = "Triangle";
      else figure = "";
    
      if (!figure.equals(""))
         throwError(line, col, String.format(notPropOfFigureErrorMessage, prop, figure));
      // if it is a correct property
      else {
         if (ctx.color() != null && visit(ctx.color()) && !contains(propsAsColor, prop)
          || ctx.angle() != null && visit(ctx.angle()) && !contains(propsAsAngle, prop)
          || ctx.TRUTHVAL() != null && !contains(propsAsTruthVal, prop))
            throwError(line, col, String.format(notValueOfPropErrorMessage, prop));
         else {
            // if it is an expr and the visit doesn't return false
            if (!(ctx.expr() != null && !visit(ctx.expr())
            || ctx.pointsExpr() != null && !visit(ctx.pointsExpr())))
               valid = true;
         }
      }
      return valid;
   }

   @Override public Boolean visitExprMultDiv(BeaverParser.ExprMultDivContext ctx) {
      boolean expr0 = visit(ctx.expr(0));
      boolean expr1 = visit(ctx.expr(1));
      return expr0 && expr1;
   }

   @Override public Boolean visitExprAddSub(BeaverParser.ExprAddSubContext ctx) {
      boolean expr0 = visit(ctx.expr(0));
      boolean expr1 = visit(ctx.expr(1));
      return expr0 && expr1;
   }

   @Override public Boolean visitIdProp(BeaverParser.IdPropContext ctx) {
      int line = ctx.getStart().getLine();
      int col = ctx.getStart().getCharPositionInLine();
      String var = ctx.ID(0).getText();
      String prop = ctx.ID(1).getText();
      boolean isPoint = varsPoint.contains(var);
      boolean isRectangle = varsRectangle.contains(var);
      boolean isCircle = varsCircle.contains(var);
      boolean isLine = varsLine.contains(var);
      boolean isTriangle = varsTriangle.contains(var);
      boolean valid = false;
      if (noneTrue(Arrays.asList(isPoint, isRectangle, isCircle, isLine, isTriangle))) {
         // it is a number
         String errorMessage;
         if (vars.contains(var))
            errorMessage = notAFigureErrorMessage;
         else
            errorMessage = notInitVarErrorMessage; 

         throwError(line, col, String.format(errorMessage, var));
      }
      // it is a figure
      else {
         String figure;
         if (isPoint && !contains(pointProps, prop)) figure = "Point";
         else if (isRectangle && !contains(rectangleProps, prop)) figure = "Rectangle";
         else if (isCircle && !contains(circleProps, prop)) figure = "Circle";
         else if (isLine && !contains(lineProps, prop)) figure = "Line";
         else if (isTriangle && !contains(triangleProps, prop)) figure = "Triangle";
         else figure = "";

         if (!figure.equals(""))
            throwError(line, col, String.format(notPropOfFigureErrorMessage, prop, figure));
         else {
            if (ctx.ID(2) != null) {
               // if var is a point or the property is not a point
               // in any of the conditions above there can be a third ID,
               // as in var[prop][x]
               if (isPoint || !contains(propsAsPointsExpr, prop))
                  throwError(line, col, String.format(notAPointErrorMessage, var+"["+prop+"]"));
               // otherwise, check if the third ID is a point property 
               else {
                  String thirdId = ctx.ID(2).getText();
                  if (!contains(pointProps, thirdId))
                     throwError(line, col, String.format(notPropOfFigureErrorMessage, thirdId, "Point"));
                  else
                     valid = true;
               }
            }
            else
               valid = true;
         }
      }

      return valid;
   }

   @Override public Boolean visitExprIds(BeaverParser.ExprIdsContext ctx) {
      return visit(ctx.identifiers());
   }

   @Override public Boolean visitExprParentheses(BeaverParser.ExprParenthesesContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Boolean visitId(BeaverParser.IdContext ctx) {
      int line = ctx.getStart().getLine();
      int col = ctx.getStart().getCharPositionInLine();
      String var = ctx.ID().getText();
      boolean valid = hasBeenInit(line, col, var);
      if (valid) {
         if (!vars.contains(var) && !varsColor.contains(var) && !varsPoint.contains(var)) {
            throwError(line, col, notVarOfPropErrorMessage);
            valid = false;
         }
      }
      else
         throwError(line, col, String.format(notInitVarErrorMessage, var));
      
      return valid;
   }

   @Override public Boolean visitExprUnary(BeaverParser.ExprUnaryContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Boolean visitExprPower(BeaverParser.ExprPowerContext ctx) {
      boolean expr0 = visit(ctx.expr(0));
      boolean expr1 = visit(ctx.expr(1));
      return expr0 && expr1;
   }

   @Override public Boolean visitExprNumber(BeaverParser.ExprNumberContext ctx) {
      String n = ctx.NUMBER().getText();
      return isNumber(n) || n.equals("pi");
   }

   @Override public Boolean visitPointsCenter(BeaverParser.PointsCenterContext ctx) {
      return openFigures < 2 ? false : true;
   }

   @Override public Boolean visitPointsExprCalc(BeaverParser.PointsExprCalcContext ctx) {
      boolean expr0 = visit(ctx.pointsExpr(0));
      boolean expr1 = visit(ctx.pointsExpr(1));
      return expr0 && expr1;
   }

   @Override public Boolean visitPointsIds(BeaverParser.PointsIdsContext ctx) {
      return visit(ctx.identifiers());
   }

   @Override public Boolean visitPointsExprPoint(BeaverParser.PointsExprPointContext ctx) {
      return visit(ctx.point());
   }

   @Override public Boolean visitColorHex(BeaverParser.ColorHexContext ctx) {
      String id = ctx.ID() != null ? ctx.ID().getText() : ctx.NUMBER().getText();
      // check if color code has more than 6 characters
      boolean valid = id.length() <= 6;
      if (!valid)
         throwError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), String.format(notValidColorCodeErrorMessage, "#"+id));
      
      return valid;
   }

   @Override public Boolean visitColorRGB(BeaverParser.ColorRGBContext ctx) {
      return allTrue(Arrays.asList(visit(ctx.expr(0)), visit(ctx.expr(1)), visit(ctx.expr(2))));
   }

   @Override public Boolean visitColorPallete(BeaverParser.ColorPalleteContext ctx) {
      int line = ctx.getStart().getLine();
      int col = ctx.getStart().getCharPositionInLine();
      String pallete = ctx.ID(1).getText();
      boolean validVar = true;
      String var;
      if (ctx.var != null) {
         var = ctx.var.getText();
         validVar = hasBeenInit(line, col, var);

         List<String> colors = palletes.get(pallete);
         if (validVar && colors != null && !colors.contains(var)) {
            throwError(line, col, String.format(colorNotInPalleteErrorMessage, var, pallete));
            validVar = false;
         }
      }
      else {
         var = ctx.index.getText();
         // if is a variable and it doesn't represent a number
         if (!isNumber(var) && !vars.contains(var)) {
            throwError(line, col, String.format(notVarTypeNumberErrorMessage, var));
            validVar = false;
         }
      }
      boolean validPallete = hasBeenInit(line, col, pallete);
      return validVar && validPallete;
   }

   @Override public Boolean visitPoint(BeaverParser.PointContext ctx) {
      boolean expr0 = visit(ctx.expr(0));
      boolean expr1 = visit(ctx.expr(1));
      return expr0 && expr1; 
   }

   @Override public Boolean visitAngle(BeaverParser.AngleContext ctx) {
      return visit(ctx.expr());
   }

   private static boolean allTrue(boolean[] values) {
      return IntStream.range(0, values.length).mapToObj(i -> values[i]).allMatch(val -> val);
   }

   private static boolean allTrue(List<Boolean> values) {
      return values.stream().allMatch(val -> val);
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

   private static boolean noneTrue(boolean[] values) {
      return !anyTrue(values);
   }

   private static boolean noneTrue(List<Boolean> values) {
      return !anyTrue(values);
   }

   private static boolean isNumber(String val) {
      try {
         Integer.parseInt(val);
      } catch(NumberFormatException e) {
         return false;
      }
      return true;
   }

   private static boolean contains(String[] arr, String target) {
      for (String s : arr) {
         if (s.equals(target))
            return true;
      }
      return false;
   }

   private boolean hasBeenInit(String var) {
      boolean isPoint = varsPoint.contains(var);
      boolean isRectangle = varsRectangle.contains(var);
      boolean isCircle = varsCircle.contains(var);
      boolean isLine = varsLine.contains(var);
      boolean isTriangle = varsTriangle.contains(var);
      boolean isVar = vars.contains(var);
      boolean isPallete = varsPallete.contains(var);
      boolean isColor = varsColor.contains(var);
      return anyTrue(Arrays.asList(isPoint, isRectangle, isCircle, isLine, isTriangle, isVar, isPallete, isColor));
   }

   private boolean hasBeenInit(int line, int col, String var) {
      boolean valid = hasBeenInit(var);
      if (!valid)
         throwError(line, col, String.format(notInitVarErrorMessage, var));
      
      return valid;
   }

   private static void throwWarning(int line, int col, String message) {
      System.err.printf("Warning@%d:%d -> %s\n", line, col, message);
   }

   private static void throwError(int line, int col, String message) {
      System.err.printf("Error@%d:%d -> %s\n", line, col, message);
   }
   
   static private String notInitVarErrorMessage = "Variable %s might have not been initialized!";
   static private String notValidColorCodeErrorMessage = "%s is not a valid color code!";
   static private String notAFigureErrorMessage = "%s is not a Figure!";
   static private String notPropOfFigureErrorMessage = "%s is not a valid property of %s!";
   static private String notValueOfPropErrorMessage = "Invalid value for property %s!";
   static private String notVarOfPropErrorMessage = "Invalid variable for property!";
   static private String multVarInitWarningMessage = "Variable %s was initialized multiple times!";
   static private String notVarTypeNumberErrorMessage = "%s is not a variable of type Number!";
   static private String colorNotInPalleteErrorMessage = "Variable %s is not in %s";
   static private String notAPointErrorMessage = "Variable %s is not a Point";

   private List<String> vars = new ArrayList<>();
   private List<String> varsColor = new ArrayList<>();
   private List<String> varsPoint = new ArrayList<>();
   private List<String> varsRectangle = new ArrayList<>();
   private List<String> varsCircle = new ArrayList<>();
   private List<String> varsLine = new ArrayList<>();
   private List<String> varsTriangle = new ArrayList<>();
   private List<String> varsPallete = new ArrayList<>();
   private HashMap<String, List<String>> palletes = new HashMap<>();
   private String currentVar;
   private String currentPallete;
   private int openFigures = 0;

   static private String[] pointProps = {"x", "y"};
   static private String[] rectangleProps = {"filled", "collide", "visibility", "color", "border", "width", "height", "center", "angle", "size"};
   static private String[] circleProps = {"filled", "collide", "visibility", "color", "border", "diameter", "radius", "center", "startingPoint", "endingPoint"};
   static private String[] lineProps = {"filled", "collide", "visibility", "color", "border", "angle", "center", "startingPoint", "endingPoint", "length"};
   static private String[] triangleProps = {"filled", "collide", "visibility", "color", "border", "center", "p0", "p1", "p2"};

   static private String[] propsAsTruthVal = {"filled", "visibility", "collide"};
   static private String[] propsAsExpr = {"center", "width", "height", "diameter", "radius", "color", "x", "y"};
   static private String[] propsAsPointsExpr = {"center", "startingPoint", "endingPoint", "p0", "p1", "p2", "size"};
   static private String[] propsAsColor = {"color"};
   static private String[] propsAsAngle = {"angle"};
}
