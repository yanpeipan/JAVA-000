import java.lang.management.MemoryUsage;

public class Hello {

    private static boolean btrue = true;

    @Deprecated
    private static class World {
        private World() {
            System.out.println(btrue);
        }
    }

    public static void main(String[] args) throws Error {
        System.out.println(btrue = backspaceCompare("xywrrmp",
                "xywrrmu#p"));

        int ia = -10, ib = 1000;
        double d1 = 2L;

        try {
            d1 += add(ia, ib);
            if (btrue) {
                System.out.println(d1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        throw new IllegalAccessError();
    }

    public static long add(int a, int b) {
        return a + b;
    }

    public static boolean backspaceCompare(String S, String T) {
        int j = T.length() - 1, i = S.length() - 1;
        int skipJ = 0, skipI = 0;
        while (i >= 0) {

            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    i--;
                    skipI++;
                } else if (skipI > 0) {
                    i--;
                    skipI--;
                } else {
                    break;
                }
            }

            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    j--;
                    skipJ++;
                } else if (skipJ > 0) {
                    j--;
                    skipJ--;
                } else {
                    break;
                }
            }

            if (i >= 0 && j >= 0) {
                if (S.charAt(i) != T.charAt(j)) {
                    return false;
                }
            }

            i--;
            j--;
        }
        if (i == j) {
            return true;
        }

        return false;
    }
}