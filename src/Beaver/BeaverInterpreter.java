public class BeaverInterpreter extends BeaverBaseVisitor<String> {

   @Override public String visitProgram(BeaverParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitContainers(BeaverParser.ContainersContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStatsPallete(BeaverParser.StatsPalleteContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStatsColor(BeaverParser.StatsColorContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStatsSet(BeaverParser.StatsSetContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStatsContains(BeaverParser.StatsContainsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitIdsList(BeaverParser.IdsListContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitInlineSet(BeaverParser.InlineSetContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitExprMultDiv(BeaverParser.ExprMultDivContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitExprAddSub(BeaverParser.ExprAddSubContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitExprTypeProperty(BeaverParser.ExprTypePropertyContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitExprParentesis(BeaverParser.ExprParentesisContext ctx) {
      return visitChildren(ctx);
   }

   // SEPARATION
   
   @Override public String visitExprID(BeaverParser.ExprIDContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitExprUnary(BeaverParser.ExprUnaryContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitExprPower(BeaverParser.ExprPowerContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitExprNumber(BeaverParser.ExprNumberContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitPointsCenter(BeaverParser.PointsCenterContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitPointsExprCalc(BeaverParser.PointsExprCalcContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitPointsExprPoint(BeaverParser.PointsExprPointContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitPointsExprExpr(BeaverParser.PointsExprExprContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitColor(BeaverParser.ColorContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitPoint(BeaverParser.PointContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitAngle(BeaverParser.AngleContext ctx) {
      return visitChildren(ctx);
   }
}
