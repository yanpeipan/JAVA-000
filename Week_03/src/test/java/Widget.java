import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * 如果内置锁不是可重入的，这段代码将发生死锁
 */
@Immutable
public class Widget {
    public synchronized void doSomething() {
        System.out.println("Widget doSomething");
    }

    public static class LoggingWidget extends Widget {
        public synchronized void doSomething() {
            System.out.println("LoggingWidget doSomething");
            // 无法获取Wdiget上的锁
            super.doSomething();
        }
    }

    public static void main(String[] args) {
        LoggingWidget loggingWidget = new LoggingWidget();
        loggingWidget.doSomething();
    }
}

