import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Stack;

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
      String palleteId = ctx.ID().getText();
      String[] colorIds = visit(ctx.idsList()).split(",");
      List<Color> colors = Arrays.asList(colorIds).stream().map(id -> colorVars.get(id)).collect(Collectors.toList()); 
      Pallete pallete = new Pallete(palleteId, colors);
      palletes.put(palleteId, pallete);
      return null;
   }

   @Override public String visitStatsColor(BeaverParser.StatsColorContext ctx) {
      String id = ctx.ID(0).getText();
      colorVars.put(id, ctx.ID(1) != null ? colorVars.get(ctx.ID(0).getText()) : new Color(id, Color.parseColor(visit(ctx.color()))));
      return null;
   }

   @Override public String visitStatsNumber(BeaverParser.StatsNumberContext ctx) {
      numVars.put(ctx.ID().getText(), Double.parseDouble(ctx.NUMBER().getText()));
      return null;
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
      boolean container = false;

      // see if there is already an uppermost container, if not, then set this as an uppermost container
      if (containerId.equals("")) {
         container = true;
         containerId = id;
      }

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

      Figure f = new Figure(id, color, borderColor, border, center, filled, thickness, collide, visibility, container);
      switch(figure) {
         case "Rectangle":
            f = new Rectangle(id, color, borderColor, border, center, filled, thickness, collide, visibility, container, width, height, angle);
            break;
         case "Circle":
            if (diameter > 0)
               f = new Circle(id, color, borderColor, border, center, filled, thickness, collide, visibility, container, diameter);
            else
               f = new Circle(id, color, borderColor, border, center, filled, thickness, collide, visibility, container, startingPoint, endingPoint);
            break;
         case "Line":
            f = new Line(id, color, borderColor, border, center, filled, thickness, collide, visibility, container, startingPoint, endingPoint, angle);
            break;
         case "Triangle":
            f = new Triangle(id, color, borderColor, border, center, filled, thickness, collide, visibility, container, startingPoint, middlePoint, endingPoint);
            break;
      }

      // if there is a figure above, then this figure belongs to it
      if (!openFigures.empty())
         openFigures.peek().addFigure(f);

      figures.put(id, f);

      return null;
   }

   @Override public String visitStatsContains(BeaverParser.StatsContainsContext ctx) {
      String id = ctx.ID().getText();

      openFigures.push(figures.get(id));

      ctx.stats().stream().forEach(stat -> visit(stat));

      // after visitng all stats
      if (containerId.equals(id))
         containerId = "";

      openFigures.pop();
      
      return null;
   }

   @Override public String visitIdsList(BeaverParser.IdsListContext ctx) {
      return String.join(",", ctx.ID().stream().map(id -> visit(id)).collect(Collectors.toList()));
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

   @Override public String visitBorderValue(BeaverParser.BorderValueContext ctx) {
      String border = visit(ctx.expr());
      String color = ctx.ID() != null ? colorVars.get(ctx.ID().getText()).toString() : visit(ctx.color());
      return border + " " + color;
   }

   @Override public String visitExprMultDiv(BeaverParser.ExprMultDivContext ctx) {
      double x = Double.parseDouble(visit(ctx.expr(0)));
      double y = Double.parseDouble(visit(ctx.expr(1)));
      double result = 0;
      switch(ctx.op.getText()) {
         case "*":
            result = x*y;
            break;
         case "/":
            result = x/y;
            break;
      }
      return Double.toString(result);
   }

   @Override public String visitExprAddSub(BeaverParser.ExprAddSubContext ctx) {
      double x = Double.parseDouble(visit(ctx.expr(0)));
      double y = Double.parseDouble(visit(ctx.expr(1)));
      double result = 0;
      switch(ctx.op.getText()) {
         case "+":
            result = x+y;
            break;
         case "-":
            result = x-y;
            break;
      }
      return Double.toString(result);
   }

   @Override public String visitExprIds(BeaverParser.ExprIdsContext ctx) {
      return visit(ctx.identifiers());
   }

   @Override public String visitExprParentheses(BeaverParser.ExprParenthesesContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitIdProp(BeaverParser.IdPropContext ctx) {
      String id = ctx.ID(0).getText();
      String prop = ctx.ID(1).getText();
      Figure figure = figures.get(id);
      Class c = figure.getClass();
      Method wantedPropertyMethod = Arrays.asList(c.getDeclaredMethods()).stream().filter(method -> method.getName().equals(prop)).collect(Collectors.toList()).get(0);
      String methodReturn = "";
      try {
         methodReturn = wantedPropertyMethod.invoke(figure).toString();
		}
		catch (IllegalAccessException|InvocationTargetException e) {
         System.err.println(e);
		}

      return methodReturn;
   }

   @Override public String visitId(BeaverParser.IdContext ctx) {
      String id = ctx.ID().getText();
      return numVars.containsKey(id) ? Double.toString(numVars.get(id)) : colorVars.get(id).toString();
   }

   @Override public String visitExprUnary(BeaverParser.ExprUnaryContext ctx) {
      return ctx.value.getText()+visit(ctx.expr());
   }

   @Override public String visitExprPower(BeaverParser.ExprPowerContext ctx) {
      return Double.toString(Math.pow(Double.parseDouble(visit(ctx.expr(0))), Double.parseDouble(ctx.value.getText()+visit(ctx.expr(1)))));
   }

   @Override public String visitExprNumber(BeaverParser.ExprNumberContext ctx) {
      return ctx.NUMBER().getText();
   }

   // TODO
   @Override public String visitPointsCenter(BeaverParser.PointsCenterContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitPointsIds(BeaverParser.PointsIdsContext ctx) {
      return visit(ctx.identifiers());
   }

   @Override public String visitPointsExprCalc(BeaverParser.PointsExprCalcContext ctx) {
      Point p0 = Point.parsePoint(visit(ctx.pointsExpr(0)));
      Point p1 = Point.parsePoint(visit(ctx.pointsExpr(1)));
      Point result = new Point(0,0);      
      switch(ctx.op.getText()) {
         case "+":
            result = Point.sum(p0, p1);
            break;
         case "-":
            result = Point.sub(p0,p1);
            break;
      }
      return result.toString();
   }

   @Override public String visitPointsExprPoint(BeaverParser.PointsExprPointContext ctx) {
      return visit(ctx.point());
   }

   @Override public String visitColorHex(BeaverParser.ColorHexContext ctx) {
      return "#"+(ctx.ID() != null ? ctx.ID().getText() : ctx.NUMBER().getText());
   }

   @Override public String visitColorRGB(BeaverParser.ColorRGBContext ctx) {
      return visit(ctx.expr(0))+","+visit(ctx.expr(1))+","+visit(ctx.expr(2));
   }

   @Override public String visitColorPallete(BeaverParser.ColorPalleteContext ctx) { 
      String palleteId;
      Pallete pallete;
      Color color;
      if (ctx.var != null) {
         // fix this later
         // not sure if can search for a color by its id in the pallete
         color = new Color(new RGB(0,0,0));
      }
      else {
         if (ctx.NUMBER() != null) {
            palleteId = ctx.ID(0).getText();
            pallete = palletes.get(palleteId);
            color = pallete.get(Integer.parseInt(ctx.NUMBER().getText()));
         }
         else {
            palleteId = ctx.ID(1).getText();
            pallete = palletes.get(palleteId);
            color = pallete.get(Integer.parseInt(ctx.ID(0).getText()));
         }
      }
      return color.toString();
   }
   
   @Override public String visitPoint(BeaverParser.PointContext ctx) {
      return visit(ctx.expr(0))+","+visit(ctx.expr(1));
   }

   @Override public String visitAngle(BeaverParser.AngleContext ctx) {
      return visit(ctx.expr());
   }

   private Stack<Figure> openFigures = new Stack<>();
   private String containerId = "";
   private HashMap<String, Figure> figures = new HashMap<>();
   private HashMap<String, Pallete> palletes = new HashMap<>();
   private HashMap<String, Double> numVars = new HashMap<>();
   private HashMap<String, Color> colorVars = new HashMap<>();

   public HashMap<String, Figure> figures() {
      return this.figures;
   }

   public HashMap<String, Pallete> palletes() {
      return this.palletes;
   }
}
