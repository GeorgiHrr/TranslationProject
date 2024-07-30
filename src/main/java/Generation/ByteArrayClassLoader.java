package Generation;

public class ByteArrayClassLoader extends ClassLoader{

    static{
        registerAsParallelCapable();
    }
    public static final ByteArrayClassLoader INSTANCE = new ByteArrayClassLoader();
    public Class<?> defineClass(String binaryName, byte[] bytecode){
        return defineClass(binaryName, bytecode,0, bytecode.length);
    }

}
