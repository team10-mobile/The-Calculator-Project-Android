package com.group10.calculator;

public class STACK {
    public Node pTop;

    public STACK() {
        pTop = null;
    }

    /**
     * @return true if stack is empty else return false
     */
    public boolean StackIsEmpty() {
        return pTop == null;
    }

    /**
     * @param infoNode: data of node
     *                  This is function add a node to the top of stack
     */
    public void Push(String infoNode) {
        Node p = new Node(infoNode);
        if (StackIsEmpty()) {
            pTop = p;
        } else {
            p.pNext = pTop;
            pTop = p;
        }
    }

    /**
     * @return a node at the top of stack and delete itself
     */
    public String Pop() {
        if (StackIsEmpty()) {
            return null;
        } else {
            Node p = pTop;
            pTop = pTop.pNext;
            return p.infoNode;
        }
    }

    /**
     * @return a node at the top of stack and don't delete itself
     */
    public String Peak() {
        return pTop.infoNode;
    }

    /**
     * @param infoNode: data of node
     * @return info of node if it exist in stack
     */
    public String SearchNode(String infoNode) {
        for (Node p = pTop; p != null; p = p.pNext) {
            if (infoNode.equals(p.infoNode)) return p.infoNode;
        }
        return null;
    }
}
