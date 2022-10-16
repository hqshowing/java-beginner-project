public class Demo {
    public static void main(String[] args) {
        int a = 19;
        int b = 28;
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println(a + " " + b);
    }
}
