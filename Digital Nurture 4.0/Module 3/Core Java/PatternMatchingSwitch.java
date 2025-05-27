public class PatternMatchingSwitch {
    public static void main(String[] args) {
        testObjectType(42);
        testObjectType("Hello, Java 21!");
        testObjectType(3.14);
        testObjectType(true);
        testObjectType(null);
    }
    static void testObjectType(Object obj) {
        String result = switch (obj) {
            case Integer i -> "It's an Integer with value: " + i;
            case String s -> "It's a String: \"" + s + "\"";
            case Double d -> "It's a Double: " + d;
            case Boolean b -> "It's a Boolean: " + b;
            case null -> "It's null.";
            default -> "Unknown type.";
        };
        System.out.println(result);
    }
}
