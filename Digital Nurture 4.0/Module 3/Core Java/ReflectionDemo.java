import java.lang.reflect.Method;

public class ReflectionDemo {
    public static void main(String[] args) throws Exception {
        // Load the class dynamically by name
        Class<?> cls = Class.forName("Sample");

        // Create an instance (assumes default constructor)
        Object obj = cls.getDeclaredConstructor().newInstance();

        // Get all declared methods
        Method[] methods = cls.getDeclaredMethods();

        // Print method names and parameter types
        for (Method method : methods) {
            System.out.print("Method: " + method.getName() + " (Parameters: ");
            Class<?>[] params = method.getParameterTypes();
            for (int i = 0; i < params.length; i++) {
                System.out.print(params[i].getSimpleName());
                if (i < params.length - 1) System.out.print(", ");
            }
            System.out.println(")");
        }

        // Invoke method 'sayHello' dynamically
        Method sayHelloMethod = cls.getDeclaredMethod("sayHello", String.class);
        sayHelloMethod.invoke(obj, "Alice");
    }
}
