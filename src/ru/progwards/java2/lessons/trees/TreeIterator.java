package ru.progwards.java2.lessons.trees;

/**
 * Для обычного BinaryTree из примера в лекциях сделать итератор, который позволяет в обычном for получить прямой обход дерева.
 * В самом дереве дополнительную информацию для этого хранить нельзя, все хранить только в итераторе.
 * В BinaryTree добавить метод public TreeIterator<K,V> getIterator()
 */

import java.util.Iterator;
import java.util.Stack;

public class TreeIterator <K extends Comparable<K>, V> implements Iterator<BinaryTree.TreeLeaf> {
    BinaryTree<K,V> tree;

    BinaryTree.TreeLeaf root;
    Stack<BinaryTree.TreeLeaf> stack = new Stack<>();

    public TreeIterator(BinaryTree<K,V> tree) {
        this.tree = tree;
        root = tree.getRoot();
        stack.push(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public BinaryTree.TreeLeaf next() {
        BinaryTree.TreeLeaf cur = stack.peek();
        if (cur.left !=null) {
            stack.push(cur.left);
        } else  {
            BinaryTree.TreeLeaf tmp = stack.pop();
            while (tmp.right == null) {
                if (stack.isEmpty()) return cur;
                tmp = stack.pop();
            }
            stack.push(tmp.right);
        }
        return cur;
    }

}
