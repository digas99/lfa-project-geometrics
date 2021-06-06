import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import structures.Point;

public class Test {

	public static void main(String[] args) {
		Point p = new Point(1, 2);
		Class c = p.getClass();
		//System.out.println(Point.class.getDeclaredMethods());
		List<Method> methods = Arrays.asList(c.getMethods()).stream()
				.filter(field -> field.getName().equals("y"))
				.collect(Collectors.toList());
		try {
			System.out.println(methods.get(0).invoke(p));
		}
		catch (IllegalAccessException e) {

		}
		catch (InvocationTargetException e) {

		}
	}
}
