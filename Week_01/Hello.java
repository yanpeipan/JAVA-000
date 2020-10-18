public class Hello {

    private final static boolean btrue = true;

    public static void main(String[] args) {
        int ia = -10, ib = 1000;
        double d1 = 2L;
        final boolean bfalse = false;

        try {
            d1 += add(ia, ib);
            if (btrue) {
                System.out.println(d1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static long add(int a, int b) {
        return a + b;
    }
}