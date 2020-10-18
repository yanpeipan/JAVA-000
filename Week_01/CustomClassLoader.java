import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class CustomClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String filename = System.getProperty(
                "user.dir"
        ) + "/" + name + ".xlass";
        System.out.println(filename);
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filename));
            for (int i = bytes.length - 1; i >= 0; i--) {
                bytes[i] = (byte) (255 - bytes[i]);
            }

            return defineClass(name, bytes, 0, bytes.length);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Class<?> clazz = customClassLoader.findClass("Hello");
        System.out.println(Arrays.toString(clazz.getMethods()));
        Object helloObject =clazz.newInstance();

        Method method = clazz.getDeclaredMethod("hello");
        method.invoke(helloObject);
    }
}
