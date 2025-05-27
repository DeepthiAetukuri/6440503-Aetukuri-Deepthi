public class DeCompile {
    public void greet() {
        System.out.println("Hello, Bytecode!");
    }
    public static void main(String[] args) {
        HelloWorld h = new HelloWorld();
        h.greet();
    }
}
