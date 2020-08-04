package ru.progwards.java2.lessons.trees;

public class NodeRB {
    private int key;
    private NodeRB left, right, parent;
    private String color;
    public NodeRB(){

    }
    public NodeRB(String color){
        this.color = color;
    }


    public int getKey() {
        return key;
    }
    public NodeRB getLeft() {
        return left;
    }
    public NodeRB getRight() {
        return right;
    }
    public NodeRB getParent() {
        return parent;
    }
    public String getColor() {
        return color;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public void setLeft(NodeRB left) {
        this.left = left;
    }
    public void setRight(NodeRB right) {
        this.right = right;
    }
    public void setParent(NodeRB parent) {
        this.parent = parent;
    }
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        NodeRB other = (NodeRB) obj;
        if(other.color == this.color && other.key == this.key){
            return true;
        }
        else {
            return false;
        }

    }
    @Override
    public String toString() {
        return "Node [key=" + key + ", left=" + left + ", right=" + right + ", parent=" + parent + ", color=" + color + "]";
    }
}
