import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class GeometricsSemanticAnalyses extends GeometricsBaseVisitor<Boolean> {

   // grupo 1
   @Override public Boolean visitProgram(GeometricsParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitUse(GeometricsParser.UseContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitUseAttribs(GeometricsParser.UseAttribsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatVarsInit(GeometricsParser.StatVarsInitContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatVarsSet(GeometricsParser.StatVarsSetContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatList(GeometricsParser.StatListContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatDraw(GeometricsParser.StatDrawContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatLoop(GeometricsParser.StatLoopContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatConditional(GeometricsParser.StatConditionalContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatFuncCall(GeometricsParser.StatFuncCallContext ctx) {
      return visitChildren(ctx);
   }

   // grupo 2
   @Override public Boolean visitStatEasterEgg(GeometricsParser.StatEasterEggContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatConsoleLog(GeometricsParser.StatConsoleLogContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatContainer(GeometricsParser.StatContainerContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitContainer(GeometricsParser.ContainerContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitExprMultDiv(GeometricsParser.ExprMultDivContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitExprAddSub(GeometricsParser.ExprAddSubContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitExprParentesis(GeometricsParser.ExprParentesisContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitExprId(GeometricsParser.ExprIdContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitExprUnary(GeometricsParser.ExprUnaryContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitExprPower(GeometricsParser.ExprPowerContext ctx) {
      return visitChildren(ctx);
   }

   // grupo 1
   @Override public Boolean visitExprNumber(GeometricsParser.ExprNumberContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitIdProp(GeometricsParser.IdPropContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitId(GeometricsParser.IdContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitPointsId(GeometricsParser.PointsIdContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitPointsCenter(GeometricsParser.PointsCenterContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitPointsExprCalc(GeometricsParser.PointsExprCalcContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitPointsExprPoint(GeometricsParser.PointsExprPointContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitBoolLogicExpr(GeometricsParser.BoolLogicExprContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitBoolLogicParentesis(GeometricsParser.BoolLogicParentesisContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitBoolLogicNot(GeometricsParser.BoolLogicNotContext ctx) {
      return visitChildren(ctx);
   }
   
   // grupo 2
   @Override public Boolean visitBoolLogicOps(GeometricsParser.BoolLogicOpsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitBoolLogicCollides(GeometricsParser.BoolLogicCollidesContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitBoolLogicTruthval(GeometricsParser.BoolLogicTruthvalContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitVarsInit(GeometricsParser.VarsInitContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitVarsOnlyInit(GeometricsParser.VarsOnlyInitContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitVarsInitList(GeometricsParser.VarsInitListContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitVarsInitObject(GeometricsParser.VarsInitObjectContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitVarsInitFigure(GeometricsParser.VarsInitFigureContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitVarsInitFunc(GeometricsParser.VarsInitFuncContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitInlineSet(GeometricsParser.InlineSetContext ctx) {
      return visitChildren(ctx);
   }
   
   // grupo 1
   @Override public Boolean visitBlockSet(GeometricsParser.BlockSetContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitAttribs(GeometricsParser.AttribsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitFuncCall(GeometricsParser.FuncCallContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitVarsSetProperties(GeometricsParser.VarsSetPropertiesContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitVarsSetExpr(GeometricsParser.VarsSetExprContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitVarsSetCalc(GeometricsParser.VarsSetCalcContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitColorId(GeometricsParser.ColorIdContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitColorHex(GeometricsParser.ColorHexContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitColorRGB(GeometricsParser.ColorRGBContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitListAdd(GeometricsParser.ListAddContext ctx) {
      return visitChildren(ctx);
   }
   
   // grupo 2
   @Override public Boolean visitListRemove(GeometricsParser.ListRemoveContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitConditional(GeometricsParser.ConditionalContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitLoop(GeometricsParser.LoopContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitEachTime(GeometricsParser.EachTimeContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitEachWhile(GeometricsParser.EachWhileContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitEachFor(GeometricsParser.EachForContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitEasteregg(GeometricsParser.EastereggContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitAngle(GeometricsParser.AngleContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Boolean visitTime(GeometricsParser.TimeContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Boolean visitPoint(GeometricsParser.PointContext ctx) {
      boolean expr0 = visit(ctx.expr(0));
      boolean expr1 = visit(ctx.expr(1));
      return expr0 && expr1; 
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

   private static void throwWarning(int line, int col, String message) {
      System.err.printf("Warning@%d:%d -> %s\n", line, col, message);
   }

   private static void throwError(int line, int col, String message) {
      System.err.printf("Error@%d:%d -> %s\n", line, col, message);
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


}
