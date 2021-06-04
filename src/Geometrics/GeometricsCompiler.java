public class GeometricsCompiler extends GeometricsBaseVisitor<ST> {

   // grupo 1
   @Override public ST visitProgram(GeometricsParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitUse(GeometricsParser.UseContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitUseAttribs(GeometricsParser.UseAttribsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitStatVarsInit(GeometricsParser.StatVarsInitContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitStatVarsSet(GeometricsParser.StatVarsSetContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitStatList(GeometricsParser.StatListContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitStatDraw(GeometricsParser.StatDrawContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitStatLoop(GeometricsParser.StatLoopContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitStatConditional(GeometricsParser.StatConditionalContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitStatFuncCall(GeometricsParser.StatFuncCallContext ctx) {
      return visitChildren(ctx);
   }

   // grupo 2
   @Override public ST visitStatEasterEgg(GeometricsParser.StatEasterEggContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitStatConsoleLog(GeometricsParser.StatConsoleLogContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitStatContainer(GeometricsParser.StatContainerContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitContainer(GeometricsParser.ContainerContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitExprMultDiv(GeometricsParser.ExprMultDivContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitExprAddSub(GeometricsParser.ExprAddSubContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitExprParentesis(GeometricsParser.ExprParentesisContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitExprId(GeometricsParser.ExprIdContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitExprUnary(GeometricsParser.ExprUnaryContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitExprPower(GeometricsParser.ExprPowerContext ctx) {
      return visitChildren(ctx);
   }

   // grupo 1
   @Override public ST visitExprNumber(GeometricsParser.ExprNumberContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitIdProp(GeometricsParser.IdPropContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitId(GeometricsParser.IdContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitPointsId(GeometricsParser.PointsIdContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitPointsCenter(GeometricsParser.PointsCenterContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitPointsExprCalc(GeometricsParser.PointsExprCalcContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitPointsExprPoint(GeometricsParser.PointsExprPointContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitBoolLogicExpr(GeometricsParser.BoolLogicExprContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitBoolLogicParentesis(GeometricsParser.BoolLogicParentesisContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitBoolLogicNot(GeometricsParser.BoolLogicNotContext ctx) {
      return visitChildren(ctx);
   }
   
   // grupo 2
   @Override public ST visitBoolLogicOps(GeometricsParser.BoolLogicOpsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitBoolLogicCollides(GeometricsParser.BoolLogicCollidesContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitBoolLogicTruthval(GeometricsParser.BoolLogicTruthvalContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarsInit(GeometricsParser.VarsInitContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarsOnlyInit(GeometricsParser.VarsOnlyInitContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarsInitList(GeometricsParser.VarsInitListContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarsInitObject(GeometricsParser.VarsInitObjectContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarsInitFigure(GeometricsParser.VarsInitFigureContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarsInitFunc(GeometricsParser.VarsInitFuncContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitInlineSet(GeometricsParser.InlineSetContext ctx) {
      return visitChildren(ctx);
   }
   
   // grupo 1
   @Override public ST visitBlockSet(GeometricsParser.BlockSetContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitAttribs(GeometricsParser.AttribsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitFuncCall(GeometricsParser.FuncCallContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarsSetProperties(GeometricsParser.VarsSetPropertiesContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarsSetExpr(GeometricsParser.VarsSetExprContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarsSetCalc(GeometricsParser.VarsSetCalcContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitColorId(GeometricsParser.ColorIdContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitColorHex(GeometricsParser.ColorHexContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitColorRGB(GeometricsParser.ColorRGBContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitListAdd(GeometricsParser.ListAddContext ctx) {
      return visitChildren(ctx);
   }
   
   // grupo 2
   @Override public ST visitListRemove(GeometricsParser.ListRemoveContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitConditional(GeometricsParser.ConditionalContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitLoop(GeometricsParser.LoopContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitEachTime(GeometricsParser.EachTimeContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitEachWhile(GeometricsParser.EachWhileContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitEachFor(GeometricsParser.EachForContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitEasteregg(GeometricsParser.EastereggContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitAngle(GeometricsParser.AngleContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitTime(GeometricsParser.TimeContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitPoint(GeometricsParser.PointContext ctx) {
      return visitChildren(ctx);
   }
}
