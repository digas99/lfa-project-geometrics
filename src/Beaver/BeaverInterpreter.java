import java.util.ArrayList;
import java.util.List;

import structures.SuperFigure;
import structures.Color;
import structures.Pallete;

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

   @Override public String visitStatsNumber(BeaverParser.StatsNumberContext ctx) {
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

   @Override public String visitIdProp(BeaverParser.IdPropContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitExprIds(BeaverParser.ExprIdsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitExprParentheses(BeaverParser.ExprParenthesesContext ctx) {
      return visitChildren(ctx);
   }

   // SEPARATION
   
   @Override public String visitId(BeaverParser.IdContext ctx) {
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

   @Override public String visitPointsIds(BeaverParser.PointsIdsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitPointsExprCalc(BeaverParser.PointsExprCalcContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitPointsExprPoint(BeaverParser.PointsExprPointContext ctx) {
      return visit(ctx.point());
   }

   @Override public String visitColorHex(BeaverParser.ColorHexContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitColorRGB(BeaverParser.ColorRGBContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitColorPallete(BeaverParser.ColorPalleteContext ctx) {
      return visitChildren(ctx);
   }
   
   @Override public String visitPoint(BeaverParser.PointContext ctx) {
      return visit(ctx.expr(0))+","+visit(ctx.expr(1));
   }

   @Override public String visitAngle(BeaverParser.AngleContext ctx) {
      return visit(ctx.expr());
   }

   List<SuperFigure> containers = new ArrayList<>();
   List<Color> colors = new ArrayList<>();
   List<Pallete> palletes = new ArrayList<>();
}
