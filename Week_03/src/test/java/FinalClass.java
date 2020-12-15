import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * 如果内置锁不是可重入的，这段代码将发生死锁
 */
@Immutable
public class FinalClass {
    private final Integer i;

    public FinalClass() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i = 0;
    }

    public static void main(String[] args) {
        FinalClass finalClass = new FinalClass();

    }
}

