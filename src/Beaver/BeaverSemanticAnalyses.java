public class BeaverInterpreter extends BeaverBaseVisitor<boolean> {

   @Override public boolean visitProgram(BeaverParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitContainers(BeaverParser.ContainersContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitStatsPallete(BeaverParser.StatsPalleteContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitStatsColor(BeaverParser.StatsColorContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitStatsSet(BeaverParser.StatsSetContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitStatsContains(BeaverParser.StatsContainsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitIdsList(BeaverParser.IdsListContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitInlineSet(BeaverParser.InlineSetContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitExprMultDiv(BeaverParser.ExprMultDivContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitExprAddSub(BeaverParser.ExprAddSubContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitExprTypeProperty(BeaverParser.ExprTypePropertyContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitExprParentesis(BeaverParser.ExprParentesisContext ctx) {
      return visitChildren(ctx);
   }

   // SEPARATION
   
   @Override public boolean visitExprID(BeaverParser.ExprIDContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitExprUnary(BeaverParser.ExprUnaryContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitExprPower(BeaverParser.ExprPowerContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitExprNumber(BeaverParser.ExprNumberContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitPointsCenter(BeaverParser.PointsCenterContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitPointsExprCalc(BeaverParser.PointsExprCalcContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitPointsExprPoint(BeaverParser.PointsExprPointContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitPointsExprExpr(BeaverParser.PointsExprExprContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitColor(BeaverParser.ColorContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitPoint(BeaverParser.PointContext ctx) {
      return visitChildren(ctx);
   }

   @Override public boolean visitAngle(BeaverParser.AngleContext ctx) {
      return visitChildren(ctx);
   }
}
