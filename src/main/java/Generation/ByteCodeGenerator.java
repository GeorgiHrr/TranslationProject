package Generation;

import Generation.Nodes.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.sql.SQLOutput;

public class ByteCodeGenerator implements ASTVisitor{
    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
    MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
    public void visit(ProgramNode node) {
        System.out.println("Generating bytecode for ProgramNode");
        System.out.println();
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Program", null, "java/lang/Object", null);
        for (StatementNode stmt : node.getStatements()) {
            stmt.accept(this);
        }
        cw.visitEnd();
    }

    @Override
    public void visit(StatementNode node) {
        System.out.println("Generating bytecode for StatementNode");
        System.out.println();
        if (node.getExpression() != null) {
            node.getExpression().accept(this);
        }
        mv.visitInsn(Opcodes.NOP);
        // Code generation for statement (e.g., append semicolon in bytecode)

    }

    @Override
    public void visit(BinaryExpressionNode node) {
        System.out.println("Generating bytecode for BinaryExpressionNode with operator: " + node.getLeft() + node.getOperator() + node.getRight());
        System.out.println();
        node.getLeft().accept(this);
        node.getRight().accept(this);
        // Code generation for binary operation (e.g., ADD, SUB, etc.) Left operator Right
        if (node.getOperator().equals("=")){

        }
        if (node.getOperator().equals("+")){

        }
        if (node.getOperator().equals("-")){

        }
        if (node.getOperator().equals("*")){

        }
        if (node.getOperator().equals("/")){

        }
        if (node.getOperator().equals("%")){

        }
        if (node.getOperator().equals("|")){

        }
        if (node.getOperator().equals("&")){

        }
    }

    @Override
    public void visit(UnaryExpressionNode node) {
        System.out.println("Generating bytecode for UnaryExpressionNode with operator: " + node.getOperator() + node.getExpression().toString());
        System.out.println();
        node.getExpression().accept(this);
        // Generate bytecode for unary operation (e.g., NEGATE)
    }

    @Override
    public void visit(IdentNode node) {
        System.out.println("Generating bytecode for IdentNode with name: " + node.getName());
        System.out.println();
        // Code generation for identifier (e.g., load variable)
    }

    @Override
    public void visit(NumberNode node) {
        System.out.println("Generating bytecode for NumberNode with value: " + node.getValue());
        System.out.println();
        // Code generation for number (e.g., push constant)
    }

    @Override
    public void visit(PrintFuncNode node) {
        System.out.println("Generating bytecode for PrintFuncNode");
        System.out.println();
        node.getExpression().accept(this);
        // Code generation for printf function
    }

    @Override
    public void visit(ScanfFuncNode node) {
        System.out.println("Generating bytecode for ScanfFuncNode");
        System.out.println();
        // Code generation for scanf function
    }
}
