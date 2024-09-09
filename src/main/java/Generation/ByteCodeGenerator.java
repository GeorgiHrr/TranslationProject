package Generation;
import Generation.Nodes.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import Main.Table;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ByteCodeGenerator implements ASTVisitor{
    final private String className = "Program";
    private List<String> localVariables;
    private Table symbolTable;
    private ClassWriter cw;
    private MethodVisitor mv;
    //PrintWriter printWriter;
    //TraceClassVisitor tcv;
    public ByteCodeGenerator(Table symbolTable) {
        this.symbolTable = symbolTable;
        this.localVariables = new ArrayList<>();
        this.fillVariablesFromTable();
        this.cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        //this.printWriter = new PrintWriter(System.out,true);
        //this.tcv = new TraceClassVisitor(cw, printWriter);
        this.cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className, null, "java/lang/Object", null);
        this.mv = this.cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V",null,null);
        this.mv.visitCode();

        for (int i = 0; i <= localVariables.size(); i++){
            this.mv.visitLdcInsn(0);
            this.mv.visitVarInsn(Opcodes.ISTORE, i+1);
        }
    }

    public void visit(ProgramNode node) {
        for (StatementNode stmt : node.getStatements()) {
            stmt.accept(this);
        }

        mv.visitInsn(Opcodes.RETURN);
        mv.visitEnd();
        mv.visitMaxs(2, 4);
        cw.visitEnd();

        System.out.println("Inputs:");
        byte[] bytecode = cw.toByteArray();
        ByteArrayClassLoader loader = new ByteArrayClassLoader();
        Class<?> test = loader.defineClass(className, bytecode);
        try {
            test.getMethod("main", String[].class).invoke(null, (Object) new String[0]);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(StatementNode node) {
        if (node.getExpression() != null) {
            node.getExpression().accept(this);
        }
    }

    @Override
    public void visit(BinaryExpressionNode node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);

        if (node.getOperator().equals("+")){
            mv.visitInsn(Opcodes.IADD);
        }
        if (node.getOperator().equals("-")){
            mv.visitInsn(Opcodes.ISUB);
        }
        if (node.getOperator().equals("*")){
            mv.visitInsn(Opcodes.IMUL);
        }
        if (node.getOperator().equals("/")){
            mv.visitInsn(Opcodes.IDIV);
        }
        if (node.getOperator().equals("%")){
            mv.visitInsn(Opcodes.IREM);
        }
        if (node.getOperator().equals("|")){
            mv.visitInsn(Opcodes.IOR);
        }
        if (node.getOperator().equals("&")){
            mv.visitInsn(Opcodes.IAND);
        }
        if (node.getOperator().equals("=")){
            mv.visitVarInsn(Opcodes.ISTORE, getVariableIndex(node.getLeft().getName()));
        }
    }

    @Override
    public void visit(UnaryExpressionNode node) {
        node.getExpression().accept(this);

        if (node.getOperator().equals("++")){
            mv.visitIincInsn(getVariableIndex(node.getExpression().getName()),1);
        }
        if (node.getOperator().equals("--")){
            mv.visitIincInsn(getVariableIndex(node.getExpression().getName()),-1);
        }
        if (node.getOperator().equals("~")){
            mv.visitInsn(Opcodes.IXOR);
        }
    }

    @Override
    public void visit(IdentNode node) {
        mv.visitVarInsn(Opcodes.ILOAD, getVariableIndex(node.getName()));
    }

    @Override
    public void visit(NumberNode node) {
        mv.visitLdcInsn(Integer.parseInt(node.getValue()));
    }

    @Override
    public void visit(PrintFuncNode node) {
        node.getExpression().accept(this);
        // Print integer
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitInsn(Opcodes.SWAP);
        //mv.visitVarInsn(Opcodes.ILOAD, getVariableIndex(node.getExpression().getName()));
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
    }

    @Override
    public void visit(ScanfFuncNode node) {
        // Create Scanner object
        mv.visitTypeInsn(Opcodes.NEW, "java/util/Scanner");
        mv.visitInsn(Opcodes.DUP);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
        mv.visitVarInsn(Opcodes.ASTORE, 0);

        // Read integer
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);
        //mv.visitVarInsn(Opcodes.ISTORE, 1);
    }


    private int getVariableIndex(String varName) {
        for (int i = 0; i < localVariables.size(); i++) {
            if (varName.equals(localVariables.get(i))){
                return i + 1;
            }
        }
        return 0;
    }
    private void fillVariablesFromTable(){
        this.localVariables = this.symbolTable.getAllSymbolNames();
    }
}
