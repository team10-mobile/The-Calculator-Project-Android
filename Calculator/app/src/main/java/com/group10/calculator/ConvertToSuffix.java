package com.group10.calculator;

import java.util.ArrayList;

/**
 * This is class coverts an intermediate expression to suffix expression
 */
public class ConvertToSuffix {
    public STACK operandStack;
    public STACK operatorStack;

    private String Result;

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public ConvertToSuffix() {
        operandStack = new STACK();
        operatorStack = new STACK();
    }

    /**
     * @param infoNode: data of node
     * @return 1 if infoNode is kind of (+,-), 2 if is kind of (*,/,%), default 0
     */
    public int GetOperator(String infoNode) {
        if ("*".equals(infoNode) || "/".equals(infoNode) || "%".equals(infoNode)) {
            return 2;
        } else if ("+".equals(infoNode) || "-".equals(infoNode)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * @param infoNode: data of Node
     * @return 0 if infoNode is number, 1 if is kind of "(" or ")", default 2
     */
    public int IsOperator(String infoNode) {
        if (GetOperator(infoNode) == 0) {
            if (!"(".equals(infoNode) && !")".equals(infoNode))
                return 0;
            else
                return 1;
        } else
            return 2;
    }

    /**
     * @param infoNode: data of node
     *                  This is function to add operand or "(" or ")" to the top of stack
     */
    public void PushOperand(String infoNode) {
        operandStack.Push(infoNode);
    }

    /**
     * @param infoNode: data of node
     *                  This is function to add operator to the top of stack
     */
    public void PushOperator(String infoNode) {
        while (!operatorStack.StackIsEmpty()
                && GetOperator(operatorStack.Peak()) >= GetOperator(infoNode)) {
            operandStack.Push(operatorStack.Pop());
        }
        operatorStack.Push(infoNode);
    }

    /**
     * This is function calculates the result of an expression
     */
    public void ResultOfExpression() {
        STACK expression = new STACK();
        ArrayList<String> op = new ArrayList<>();

        for (Node p = operandStack.pTop; p != null; p = p.pNext) {
            op.add(p.infoNode);
        }
        for (int i = op.size() - 1; i >= 0; i--) {
            if (IsOperator(op.get(i)) == 0) {
                expression.Push(op.get(i));
            } else {
                double b = Double.parseDouble(expression.Pop());
                double a = Double.parseDouble(expression.Pop());

                if (op.get(i).equals("+")) {
                    expression.Push((a + b) + "");
                } else if (op.get(i).equals("-")) {
                    expression.Push((a - b) + "");
                } else if (op.get(i).equals("*")) {
                    expression.Push((a * b) + "");
                } else if (op.get(i).equals("/")) {
                    expression.Push((a / b) + "");
                } else if (op.get(i).equals("%")) {
                    expression.Push((a / 100) + "");
                }
            }
        }
        setResult(expression.Pop());
    }

    /**
     * @return an expression after conversion
     * This is function converts a suffix expression to intermediate expression
     */
    public String ConvertSuffixToIntermediate() {
        STACK expression = new STACK();
        ArrayList<String> op = new ArrayList<>();

        for (Node p = operandStack.pTop; p != null; p = p.pNext) {
            op.add(p.infoNode);
        }
        for (int i = op.size() - 1; i >= 0; i--) {
            if (IsOperator(op.get(i)) == 0) {
                expression.Push(op.get(i));
            } else {
                String b = expression.Pop();
                String a = expression.Pop();

                if (op.get(i).equals("%")) {
                    expression.Push("(" + a + "%" + ")");
                } else {
                    expression.Push("(" + a + " " + op.get(i) + " " + b + ")");
                }
            }
        }
        return expression.Pop();
    }
}
