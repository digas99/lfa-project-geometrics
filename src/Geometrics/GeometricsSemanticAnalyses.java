import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
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
//Expr functions start------------------------------------------------------
   @Override public Boolean visitExprMultDiv(GeometricsParser.ExprMultDivContext ctx) {
      boolean expr0 = visit(ctx.expr(0));
      boolean expr1 = visit(ctx.expr(1));
      return expr0 && expr1;
   }

   @Override public Boolean visitExprAddSub(GeometricsParser.ExprAddSubContext ctx) {
      boolean expr0 = visit(ctx.expr(0));
      boolean expr1 = visit(ctx.expr(1));
      return expr0 && expr1;
   }

   @Override public Boolean visitExprParentesis(GeometricsParser.ExprParentesisContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Boolean visitExprId(GeometricsParser.ExprIdContext ctx) {
      return visit(ctx.identifiers());
   }

   @Override public Boolean visitExprUnary(GeometricsParser.ExprUnaryContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Boolean visitExprPower(GeometricsParser.ExprPowerContext ctx) {
      boolean expr0 = visit(ctx.expr(0));
      boolean expr1 = visit(ctx.expr(1));
      return expr0 && expr1;
   }
   // grupo 1
   @Override public Boolean visitExprNumber(GeometricsParser.ExprNumberContext ctx) {
      String n = ctx.NUMBER().getText();
      return isNumber(n) || n.equals("pi");
   }

   @Override public Boolean visitPointsExprCalc(GeometricsParser.PointsExprCalcContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitPointsExprPoint(GeometricsParser.PointsExprPointContext ctx) {
      return visit(ctx.point());
   }

   @Override public Boolean visitBoolLogicExpr(GeometricsParser.BoolLogicExprContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Boolean visitVarsSetExpr(GeometricsParser.VarsSetExprContext ctx) {
      return visitChildren(ctx);
   }
  //Expr functions end------------------------------------------------------
   @Override public Boolean visitIdProp(GeometricsParser.IdPropContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitId(GeometricsParser.IdContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitPointsId(GeometricsParser.PointsIdContext ctx) {
      return visit(ctx.identifiers());
   }

   @Override public Boolean visitPointsCenter(GeometricsParser.PointsCenterContext ctx) {
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
   //If function
   @Override public Boolean visitConditional(GeometricsParser.ConditionalContext ctx) {
      return visitChildren(ctx);
   }
   //Loop functions start -----------------------------------------
   @Override public Boolean visitLoop(GeometricsParser.LoopContext ctx) {
      return visitChildren(ctx);
      //Verify time and loopSpecifics(eachtime,while,for)
   }

   @Override public Boolean visitEachTime(GeometricsParser.EachTimeContext ctx) {
      return visit(ctx.stats());
      //Verify stats
      //Verify stop(interruption trigger)
   }

   @Override public Boolean visitEachWhile(GeometricsParser.EachWhileContext ctx) {
      Boolean bologic = visit(ctx.booleanlogic());
      Boolean stat = visit(ctx.stats());
      return bologic && stat;
      //Verify booleanLogic and stats
      //Verify stop(interruption trigger)
   }

   @Override public Boolean visitEachFor(GeometricsParser.EachForContext ctx) {
      Boolean expr0 = visit(ctx.expr(0));
      Boolean expr1 = visit(ctx.expr(1));
      Boolean stat = visit(ctx.stats());
      
      return expr0 && expr1 && stat;
      //Verify ID, 2 expr and stats
      //Verify stop(interruption trigger)
   }
   //Loop functions end -----------------------------------------
   @Override public Boolean visitEasteregg(GeometricsParser.EastereggContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitAngle(GeometricsParser.AngleContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Boolean visitTime(GeometricsParser.TimeContext ctx) {
      String n = ctx.NUMBER().getText();
      return isNumber(n);
   }

   @Override public Boolean visitPoint(GeometricsParser.PointContext ctx) {
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
}
