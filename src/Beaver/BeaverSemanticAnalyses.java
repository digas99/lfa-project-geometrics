import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BeaverSemanticAnalyses extends BeaverBaseVisitor<Boolean> {

   @Override public Boolean visitProgram(BeaverParser.ProgramContext ctx) {
      return allTrue(ctx.stats().stream()
               .map(stat -> visit(stat))
               .collect(Collectors.toList())) && visit(ctx.containers());
   }

   @Override public Boolean visitContainers(BeaverParser.ContainersContext ctx) {
      return visit(ctx.idsList());
   }

   @Override public Boolean visitStatsPallete(BeaverParser.StatsPalleteContext ctx) {
      String var = ctx.ID().getText();
      if (hasBeenInit(var))
         throwWarning(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), String.format(multVarInitWarningMessage, var));

      varsPallete.add(var);

      return visit(ctx.idsList());
   }

   @Override public Boolean visitStatsColor(BeaverParser.StatsColorContext ctx) {
      String var = ctx.ID().getText();
      if (hasBeenInit(var))
         throwWarning(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), String.format(multVarInitWarningMessage, var));

      vars.add(var);

      return visit(ctx.color());
   }

   @Override public Boolean visitStatsNumber(BeaverParser.StatsNumberContext ctx) {
      String var = ctx.ID().getText();
      if (hasBeenInit(var))
         throwWarning(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), String.format(multVarInitWarningMessage, var));

      vars.add(var);

      return true;
   }

   @Override public Boolean visitStatsSet(BeaverParser.StatsSetContext ctx) {
      String var = ctx.ID().getText();
      if (hasBeenInit(var))
         throwWarning(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), String.format(multVarInitWarningMessage, var));

      currentVar = var;
      switch(ctx.FIGURE().getText()) {
         case "Point":
            varsPoint.add(var);
            break;
         case "Rectangle":
            varsRectangle.add(var);
            break;
         case "Circle":
            varsCircle.add(var);
            break;
         case "Line":
            varsPoint.add(var);
            break;
         case "Triangle":
            varsPoint.add(var);
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

      return valid && allTrue(ctx.stats().stream()
               .map(stat -> visit(stat))
               .collect(Collectors.toList()));
   }

   @Override public Boolean visitIdsList(BeaverParser.IdsListContext ctx) {
      // check if there variables not initialized, and if so then return false
      // if all variables haven't returned true, then return false
      return allTrue(ctx.ID().stream().map(var -> {
         if (!hasBeenInit(var.getText())) {
            throwError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), String.format(notInitVarErrorMessage, var.getText()));
            return false;
         }
         return true;
      }).collect(Collectors.toList()));
   }

   @Override public Boolean visitInlineSet(BeaverParser.InlineSetContext ctx) {
      int line = ctx.getStart().getLine();
      int col = ctx.getStart().getCharPositionInLine();
      String var = currentVar;
      String prop = ctx.ID().getText(); 
      boolean isPoint = varsPoint.contains(var);
      boolean isRectangle = varsRectangle.contains(var);
      boolean isCircle = varsCircle.contains(var);
      boolean isLine = varsLine.contains(var);
      boolean isTriangle = varsTriangle.contains(var);
      String figure;
      boolean valid = false;
      if (isPoint && !contains(pointProps, prop)) figure = "Point";
      else if (isRectangle && !contains(rectangleProps, prop)) figure = "Rectangle";
      else if (isCircle && !contains(circleProps, prop)) figure = "Circle";
      else if (isLine && !contains(lineProps, prop)) figure = "Line";
      else if (isTriangle && !contains(triangleProps, prop)) figure = "Triangle";
      else figure = "";

      if (!figure.equals(""))
         throwError(line, col, String.format(notPropOfFigureErrorMessage, prop, figure));
      // if it is a correct property
      else {
         if (ctx.expr() != null && visit(ctx.expr()) && !contains(propsAsExpr, prop)
          || ctx.pointsExpr() != null && visit(ctx.pointsExpr()) && !contains(propsAsPointsExpr, prop)
          || ctx.color() != null && visit(ctx.color()) && !contains(propsAsColor, prop)
          || ctx.borderValue() != null && visit(ctx.borderValue()) && !contains(propsAsExprColor, prop)
          || ctx.angle() != null && visit(ctx.angle()) && !contains(propsAsAngle, prop))
            throwError(line, col, String.format(notValueOfPropErrorMessage, prop));
         else
            valid = true;
      }

      return valid;
   }

   @Override public Boolean visitBorderValue(BeaverParser.BorderValueContext ctx) {
      return visit(ctx.expr()) && visit(ctx.color());
   }

   @Override public Boolean visitExprMultDiv(BeaverParser.ExprMultDivContext ctx) {
      return visit(ctx.expr(0)) && visit(ctx.expr(1));
   }

   @Override public Boolean visitExprAddSub(BeaverParser.ExprAddSubContext ctx) {
      return visit(ctx.expr(0)) && visit(ctx.expr(1));
   }

   @Override public Boolean visitExprTypeProperty(BeaverParser.ExprTypePropertyContext ctx) {
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
         else
            valid = true;
      }

      return valid;
   }

   @Override public Boolean visitExprParentesis(BeaverParser.ExprParentesisContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Boolean visitExprID(BeaverParser.ExprIDContext ctx) {
      String id = ctx.ID().getText();
      boolean valid = vars.contains(id);
      if (!valid)
         throwError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), String.format(notInitVarErrorMessage, id));
      return valid;
   }

   @Override public Boolean visitExprUnary(BeaverParser.ExprUnaryContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Boolean visitExprPower(BeaverParser.ExprPowerContext ctx) {
      return visit(ctx.expr(0)) && visit(ctx.expr(1));
   }

   @Override public Boolean visitExprNumber(BeaverParser.ExprNumberContext ctx) {
      String n = ctx.NUMBER().getText();
      return isNumber(n) || n.equals("pi");
   }

   @Override public Boolean visitPointsExprCalc(BeaverParser.PointsExprCalcContext ctx) {
      return visit(ctx.pointsExpr(0)) && visit(ctx.pointsExpr(1));
   }

   @Override public Boolean visitPointsExprPoint(BeaverParser.PointsExprPointContext ctx) {
      return visit(ctx.point());
   }

   @Override public Boolean visitPointsExprExpr(BeaverParser.PointsExprExprContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Boolean visitColor(BeaverParser.ColorContext ctx) {
      List<Boolean> allChecks = new ArrayList<>();
      // if hexadecimal color code
      if (ctx.ID() != null || ctx.NUMBER() != null) {
         boolean validId = true;
         String id = ctx.ID() != null ? ctx.ID().getText() : ctx.NUMBER().getText();
         // check if color code has more than 6 characters
         if (id.length() > 6) {
            validId = false;
            throwError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), String.format(notValidColorCodeErrorMessage, "#"+id));
         }
         allChecks.add(validId);
      }
      // then it is rgb
      else
         allChecks.addAll(Arrays.asList(visit(ctx.expr(0)), visit(ctx.expr(1)), visit(ctx.expr(2))));

      return allTrue(allChecks);
   }

   @Override public Boolean visitPoint(BeaverParser.PointContext ctx) {
      return visit(ctx.expr(0)) && visit(ctx.expr(1));
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

   private static boolean hasBeenInit(String var) {
      boolean isPoint = varsPoint.contains(var);
      boolean isRectangle = varsRectangle.contains(var);
      boolean isCircle = varsCircle.contains(var);
      boolean isLine = varsLine.contains(var);
      boolean isTriangle = varsTriangle.contains(var);
      boolean isVar = vars.contains(var);
      boolean isPallete = varsPallete.contains(var);
      return anyTrue(Arrays.asList(isPoint, isRectangle, isCircle, isLine, isTriangle, isVar, isPallete));
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
   static private String multVarInitWarningMessage = "Variable %s was initialized multiple times!";

   static private List<String> vars = new ArrayList<>();
   static private List<String> varsPoint = new ArrayList<>();
   static private List<String> varsRectangle = new ArrayList<>();
   static private List<String> varsCircle = new ArrayList<>();
   static private List<String> varsLine = new ArrayList<>();
   static private List<String> varsTriangle = new ArrayList<>();
   static private List<String> varsPallete = new ArrayList<>();
   static private String currentVar;

   static private String[] pointProps = {"color", "border", "x", "y", "startingPoint", "endingPoint"};
   static private String[] rectangleProps = {"color", "border", "width", "height", "center", "angle", "size"};
   static private String[] circleProps = {"color", "border", "diameter", "radius", "center", "startingPoint", "endingPoint"};
   static private String[] lineProps = {"color", "border", "angle", "center", "startingPoint", "endingPoint"};
   static private String[] triangleProps = {"color", "border", "p0", "p1", "p2"};

   static private String[] propsAsExpr = {"width", "height", "diameter", "radius", "color"};
   static private String[] propsAsPointsExpr = {"x", "y", "center", "startingPoint", "endingPoint", "p0", "p1", "p2", "size"};
   static private String[] propsAsColor = {"color"};
   static private String[] propsAsExprColor = {"border"};
   static private String[] propsAsAngle = {"angle"};
}
