import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import structures.SuperFigure;
import structures.Triangle;
import structures.Circle;
import structures.Color;
import structures.Figure;
import structures.Line;
import structures.Pallete;
import structures.Point;
import structures.RGB;
import structures.Rectangle;

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
      String figure = ctx.FIGURE().getText();
      String id = ctx.ID().getText();
      // figure properties
      Color color = new Color(new RGB(255, 255, 255));
      Color borderColor = new Color(new RGB(0, 0, 0));
      double border = 0;
      Point center = new Point(0, 0);
      boolean filled = false;
      double thickness = 0;
      boolean collide = true;
      boolean visibility = true;
      // rectangle properties
      double width = 0;
      double height = 0;
      // circle properties
      double diameter = 0;
      // triangle properties
      Point middlePoint = new Point(0, 0);
      // properties shared by several figures, but not all
      Point startingPoint = new Point(0, 0);
      Point endingPoint = new Point(0, 0);
      double angle = 0;
      List<String> properties = ctx.inlineSet().stream().map(prop -> visit(prop)).collect(Collectors.toList());
      for(String prop : properties) {
         String[] split = prop.split(";");
         String value = split[1];
         switch(split[0]) {
            case "color":
               color = Color.parseColor(value);   
               break;
            case "border":
               String[] values = value.split(" ");
               border = Double.parseDouble(values[0]);
               borderColor = Color.parseColor(values[1]);
               break;
            case "width":
               width = Double.parseDouble(value);
               break;
            case "height":
               height = Double.parseDouble(value);
               break;
            case "center":
               center = Point.parsePoint(value);
               break;
            case "angle":
               angle = Double.parseDouble(value);
               break;
            case "size":
               Point s = Point.parsePoint(value);
               width = s.x();
               height = s.y(); 
               break;
            case "diameter":
               diameter = Double.parseDouble(value);   
               break;
            case "radius":
               diameter = Double.parseDouble(value)*2;
               break;
            case "startingPoint":
               startingPoint = Point.parsePoint(value);
               break;
            case "endingPoint":
               endingPoint = Point.parsePoint(value);
               break;
            case "p0":
               startingPoint = Point.parsePoint(value);
               break;
            case "p1":
               middlePoint = Point.parsePoint(value);
               break;
            case "p2":
               endingPoint = Point.parsePoint(value);
               break;
         } 
      }

      Figure f;
      switch(figure) {
         case "Rectangle":
            f = new Rectangle(id, color, borderColor, border, center, filled, thickness, collide, visibility, width, height, angle);
            break;
         case "Circle":
            if (diameter > 0)
               f = new Circle(id, color, borderColor, border, center, filled, thickness, collide, visibility, diameter);
            else
               f = new Circle(id, color, borderColor, border, center, filled, thickness, collide, visibility, startingPoint, endingPoint);
            break;
         case "Line":
            f = new Line(id, color, borderColor, border, center, filled, thickness, collide, visibility, startingPoint, endingPoint, angle);
            break;
         case "Triangle":
            f = new Triangle(id, color, borderColor, border, center, filled, thickness, collide, visibility, startingPoint, middlePoint, endingPoint);
            break;
      }
      return null;
   }

   @Override public String visitStatsContains(BeaverParser.StatsContainsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitIdsList(BeaverParser.IdsListContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitInlineSet(BeaverParser.InlineSetContext ctx) {
      String prop = ctx.ID().getText();
      String value;
      if (ctx.expr() != null)
         value = visit(ctx.expr());
      else if (ctx.pointsExpr() != null)
         value = visit(ctx.pointsExpr());
      else if (ctx.color() != null)
         value = visit(ctx.color());
      else if (ctx.borderValue() != null)
         value = visit(ctx.borderValue());
      else
         value = visit(ctx.angle());       
      return prop+";"+value;
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
