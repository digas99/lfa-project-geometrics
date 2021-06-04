import org.stringtemplate.v4.*;

public class GeometricsCompiler extends GeometricsBaseVisitor<ST> {
   // grupo1
   @Override
   public ST visitProgram(GeometricsParser.ProgramContext ctx) {
      return visitChildren(ctx);
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
      return visitChildren(ctx);
   }

   @Override
   public ST visitStatList(GeometricsParser.StatListContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitStatDraw(GeometricsParser.StatDrawContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitStatLoop(GeometricsParser.StatLoopContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitStatConditional(GeometricsParser.StatConditionalContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitStatFuncCall(GeometricsParser.StatFuncCallContext ctx) {
      return visitChildren(ctx);
   }

   // grupo2 Mariana
   @Override
   public ST visitStatEasterEgg(GeometricsParser.StatEasterEggContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitStatConsoleLog(GeometricsParser.StatConsoleLogContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitStatContainer(GeometricsParser.StatContainerContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitContainer(GeometricsParser.ContainerContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitExprMultDiv(GeometricsParser.ExprMultDivContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitExprAddSub(GeometricsParser.ExprAddSubContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitExprTypeProperty(GeometricsParser.ExprTypePropertyContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitExprParentesis(GeometricsParser.ExprParentesisContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitExprID(GeometricsParser.ExprIDContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitExprUnary(GeometricsParser.ExprUnaryContext ctx) {
      return visitChildren(ctx);
   }

   // grupo1
   @Override
   public ST visitExprPower(GeometricsParser.ExprPowerContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitExprNumber(GeometricsParser.ExprNumberContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitPointsExprID(GeometricsParser.PointsExprIDContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitPointsExprTypeProperty(GeometricsParser.PointsExprTypePropertyContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitPointsCenter(GeometricsParser.PointsCenterContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitPointsExprCalc(GeometricsParser.PointsExprCalcContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitPointsExprPoint(GeometricsParser.PointsExprPointContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitPointsExprExpr(GeometricsParser.PointsExprExprContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitBoolLogicExpr(GeometricsParser.BoolLogicExprContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitBoolLogicParentesis(GeometricsParser.BoolLogicParentesisContext ctx) {
      return visitChildren(ctx);
   }

   // grupo2 Leandro
   @Override
   public ST visitBoolLogicNot(GeometricsParser.BoolLogicNotContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitBoolLogicOps(GeometricsParser.BoolLogicOpsContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitBoolLogicCollides(GeometricsParser.BoolLogicCollidesContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitBoolLogicTruthval(GeometricsParser.BoolLogicTruthvalContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsInit(GeometricsParser.VarsInitContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsOnlyInit(GeometricsParser.VarsOnlyInitContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsInitList(GeometricsParser.VarsInitListContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsInitText(GeometricsParser.VarsInitTextContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsInitNumber(GeometricsParser.VarsInitNumberContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsInitAngle(GeometricsParser.VarsInitAngleContext ctx) {
      return visitChildren(ctx);
   }

   // grupo1
   @Override
   public ST visitVarsInitTime(GeometricsParser.VarsInitTimeContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsInitPoint(GeometricsParser.VarsInitPointContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsInitTriangle(GeometricsParser.VarsInitTriangleContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsInitRectangle(GeometricsParser.VarsInitRectangleContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsInitLine(GeometricsParser.VarsInitLineContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsInitCircle(GeometricsParser.VarsInitCircleContext ctx) {
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
   public ST visitFuncCall(GeometricsParser.FuncCallContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsSetProperties(GeometricsParser.VarsSetPropertiesContext ctx) {
      return visitChildren(ctx);
   }

   // grupo2 Leandro
   @Override
   public ST visitVarsSetExpr(GeometricsParser.VarsSetExprContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitVarsSetCalc(GeometricsParser.VarsSetCalcContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitInlineSet(GeometricsParser.InlineSetContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitBlockSet(GeometricsParser.BlockSetContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitCommonPropNumbers(GeometricsParser.CommonPropNumbersContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitCommonPropColors(GeometricsParser.CommonPropColorsContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitCommonPropDisplay(GeometricsParser.CommonPropDisplayContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitCommonPropCollisions(GeometricsParser.CommonPropCollisionsContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitLinePropertyWidth(GeometricsParser.LinePropertyWidthContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitLinePropertyPoints(GeometricsParser.LinePropertyPointsContext ctx) {
      return visitChildren(ctx);
   }

   // grupo1
   @Override
   public ST visitLinePropertyAngle(GeometricsParser.LinePropertyAngleContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitRectanglePropertySize(GeometricsParser.RectanglePropertySizeContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitRectanglePropertyCenter(GeometricsParser.RectanglePropertyCenterContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitRectanglePropertyAngle(GeometricsParser.RectanglePropertyAngleContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitCirclePropertySize(GeometricsParser.CirclePropertySizeContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitCirclePropertyPointss(GeometricsParser.CirclePropertyPointssContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitTriangleProperty(GeometricsParser.TrianglePropertyContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitColor(GeometricsParser.ColorContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitListAdd(GeometricsParser.ListAddContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public ST visitListRemove(GeometricsParser.ListRemoveContext ctx) {
      return visitChildren(ctx);
   }

   // grupo2 Mariana
   @Override
   public ST visitConditional(GeometricsParser.ConditionalContext ctx) {
      return visitChildren(ctx);
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
}
