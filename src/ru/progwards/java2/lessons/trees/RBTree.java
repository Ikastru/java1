package ru.progwards.java2.lessons.trees;

public class RBTree {
    public NodeRB root = new NodeRB("black");
    public NodeRB nil = new NodeRB("black");


    public void LeftRotate(RBTree tree, NodeRB x){
        NodeRB y = x.getRight();
        x.setRight(y.getLeft());
        if(y.getLeft()!=tree.nil){
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if(x.getParent() == tree.nil){
            tree.root = y;
        }
        else if(x == x.getParent().getLeft()){
            x.getParent().setLeft(y);
        }
        else {
            x.getParent().setRight(y);
            y.setLeft(x);
        }
        x.setParent(y);
    }

    public void RightRotate(RBTree tree, NodeRB x){
        NodeRB y = x.getLeft();
        x.setLeft(y.getRight());
        if(y.getRight()!=tree.nil){
            y.getRight().setParent(x);
        }
        y.setParent(x.getParent());
        if(x.getParent() == tree.nil){
            tree.root = y;
        }
        else if(x == x.getParent().getRight()){
            x.getParent().setRight(y);
        }
        else {
            x.getParent().setLeft(y);
            y.setRight(x);
        }
        x.setParent(y);
    }

    public void RBInsert(RBTree tree, int key){
        NodeRB z = new NodeRB();
        z.setKey(key);
        NodeRB y = tree.nil;
        NodeRB x = tree.root;
        while(x != tree.nil){
            y = x;
            if(z.getKey() < x.getKey()){
                x = x.getLeft();
            }
            else x = x.getRight();
        }
        z.setParent(y);
        if(y == tree.nil){
            tree.root = z;
        }
        else if(z.getKey()<y.getKey()){
            y.setLeft(z);
        }
        else{
            y.setRight(z);
        }
        z.setLeft(tree.nil);
        z.setRight(tree.nil);
        z.setColor("red");
        RBInsertFixUp(tree, z);
    }

    public void RBInsertFixUp(RBTree tree, NodeRB z){
        while (z.getParent().getColor()=="red"){
            if(z.getParent() == z.getParent().getParent().getLeft()){
                NodeRB y = z.getParent().getParent().getRight();
                if(y.getColor() == "red"){
                    z.getParent().setColor("black");
                    y.setColor("black");
                    z.getParent().getParent().setColor("red");
                    z = z.getParent().getParent();
                    System.out.println("1 cicl");
                }
                else if(z == z.getParent().getRight()){
                    z = z.getParent();
                    LeftRotate(tree, z);
                    System.out.println("2 cicl");
                }
                z.getParent().setColor("black");
                z.getParent().getParent().setColor("red");
                RightRotate(tree, z.getParent().getParent());
            }
            else{
                NodeRB y = z.getParent().getParent().getLeft();
                if(y.getColor() == "red"){
                    z.getParent().setColor("black");
                    y.setColor("black");
                    z.getParent().getParent().setColor("red");
                    z = z.getParent().getParent();
                }
                else if(z == z.getParent().getLeft()){
                    z = z.getParent();
                    LeftRotate(tree, z);
                }
                z.getParent().setColor("black");
                z.getParent().getParent().setColor("red");
                RightRotate(tree, z.getParent().getParent());
            }
        }
        tree.root.setColor("black");
    }

    public NodeRB TreeMinimum(NodeRB x, RBTree tree){
        while(x.getRight()!=tree.nil){
            x = x.getRight();
        }
        return x;
    }

    public NodeRB Search(int k, RBTree tree){
        NodeRB x = tree.root;
        while(x!=tree.nil && k!=x.getKey()){
            if(k<x.getKey()){
                x = x.getLeft();
            }
            else {
                x = x.getRight();
            }
        }
        return x;
    }

    public void AllKey(NodeRB x, RBTree tree){
        if(x!=tree.nil){
            AllKey(x.getLeft(), tree);
            System.out.println(x.getKey());
            AllKey(x.getRight(), tree);
        }
    }
}
