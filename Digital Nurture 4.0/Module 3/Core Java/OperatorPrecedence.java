public class OperatorPrecedence {
    public static void main(String[] args) {
        int result1 = 10 + 5 * 2;  
        System.out.println("10+5*2 = " + result1);
        int result2 = (10 + 5) * 2;  
        System.out.println("(10+5)*2 = " +result2);
        int result3 = 20 / 4 + 3 * 2;  
        System.out.println("20 /4 + 3 * 2 = " + result3);
        int result4 = 20 % 3 + 4 * 2;  
        System.out.println("20 % 3 + 4 * 2 = " + result4);
    }
}
