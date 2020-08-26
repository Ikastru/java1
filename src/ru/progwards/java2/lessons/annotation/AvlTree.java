/**
 * Пакет для исследования деревьев в JAVA.
 * Содержит классы BinaryTree, AvlTree и RBTree, а также калсс для обработки
 * исключении по деревьям TreeException.
 * Для документирования информации о пакете требуется файл package-info.java.
 */
package ru.progwards.java2.lessons.annotation;

import java.util.function.Consumer;

/**
 * Класс самобалансирующегося дерева, зависит от класса TreeException.java, {@link TreeException}
 * @author Ik
 * @version 1.0
 * @see TreeException
 *
 */
public class AvlTree<K extends Comparable<K>, V>  {

    /**
     * свойство - корень дерева
     */
    public AvlLeaf root;

    /**
     * Класс лист дерева, подкласс класса AvlTree, {@link AvlTree}
     *
     */
    static class AvlLeaf <K extends Comparable<K>, V> {
        private AvlLeaf<K , V> left, right, parent;
        private int height = 1;
        private K key;
        private V value;

        private AvlLeaf (K key, V val) {
            this.key = key;
            this.value = val;
        }

        public void process(Consumer<AvlLeaf<K,V>> consumer) {
            if (left != null)
                left.process(consumer);
            consumer.accept(this);
            if (right != null)
                right.process(consumer);
        }

        private AvlLeaf<K,V> find(K key) {
            int cmp = key.compareTo(this.key);
            if (cmp > 0)
                if (right != null)
                    return right.find(key);
                else
                    return this;
            if (cmp < 0)
                if (left != null)
                    return left.find(key);
                else
                    return this;
            return this;
        }

        public String toString() {
            return "("+key+","+value+")";
        }
    }

    private int getHeight(AvlLeaf leaf) {
        return (leaf== null) ? 0 : leaf.height;
    }

    private int getBalance(AvlLeaf leaf)
    {
        if (leaf== null) return 0;
        return getHeight(leaf.left) - getHeight(leaf.right);
    }

    /**
     * @param key номер листа дерева
     * @param value значение листа дерева
     *  Вставляет лист в дерево
     */
    public void put(K key, V value) throws TreeException {
        root = insert(root, key, value);
        updateHeight(root);
    }

    /**
     * @param node узел дерева
     * @param key номер листа дерева
     * @param value значение листа дерева
     * @return узел дерева
     */
    public AvlLeaf insert(AvlLeaf<K, V> node, K key, V value) throws TreeException {
        if (node == null) {
            return new AvlLeaf<K,V>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if ( cmp < 0)
            node.left  = insert(node.left, key, value);
        else
            node.right = insert(node.right, key, value);

        /* 2. Update height of this ancestor node */
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        /* 3. Get the balance factor of this ancestor node to check whether this node became unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && key.compareTo(node.left.key) < 0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key.compareTo(node.right.key)>0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key.compareTo(node.left.key) > 0)
        {
            node.left =  leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key.compareTo(node.right.key) < 0)
        {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    /**
     * Правый разворот дерева
     * @param y дерево
     * @return дерево с правым разворотом
     */
    private AvlLeaf rightRotate(AvlLeaf y) {
        AvlLeaf x = y.left;
        AvlLeaf T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(getHeight(y.left), getHeight(y.right))+1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right))+1;

        // Return new root
        return x;
    }

    /**
     * Левый разворот дерева
     * @param x дерево
     * @return дерево с левым разворотом
     */
    private AvlLeaf leftRotate(AvlLeaf x) {
        AvlLeaf y = x.right;
        AvlLeaf T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = Math.max(getHeight(x.left), getHeight(x.right))+1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right))+1;

        // Return new root
        return y;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    AvlLeaf delete(AvlLeaf node, K key) {
        int cmp = 0;
        // STEP 1: PERFORM STANDARD BST DELETE
        //System.out.println("key=" + key);
        if (node == null)
            return node;

        // If the value to be deleted is smaller than the root's value,
        // then it lies in left subtree
        cmp = key.compareTo((K)node.key);
        if ( cmp < 0 )
            node.left = delete(node.left, key);

            // If the value to be deleted is greater than the root's value,
            // then it lies in right subtree
        else if( cmp > 0 )
            node.right = delete(node.right, key);

            // if value is same as root's value, then This is the node
            // to be deleted
        else {
            // node with only one child or no child
            if( (node.left == null) || (node.right == null) ) {

                AvlLeaf temp;
                if (node.left != null)
                    temp = node.left;
                else
                    temp = node.right;

                // No child case
                if(temp == null) {
                    temp = node;
                    node = null;
                }
                else // One child case
                    node = temp; // Copy the contents of the non-empty child

                temp = null;
            }
            else {
                // node with two children: Get the inorder successor (smallest
                // in the right subtree)
                AvlLeaf temp = minValueNode(node.right);

                // Copy the inorder successor's data to this node
                node.key = temp.key;
                node.value = temp.value;

                // Delete the inorder successor
                node.right = delete(node.right, (K) temp.key);
            }
        }

        // If the tree had only one node then return
        if (node == null)
            return node;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        // Left Right Case
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left =  leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        // Right Left Case
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private AvlLeaf minValueNode(AvlLeaf node) {
        AvlLeaf current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;
        return current;
    }


    private void updateHeight(AvlLeaf leaf) {
        leaf.height = Math.max(getHeight(leaf.left), getHeight(leaf.right)) + 1;
    }

    AvlLeaf findinner(K key) {
        AvlLeaf current = root;
        while (current != null) {
            if (current.key.compareTo(key) == 0) {
                break;
            }
            current = current.key.compareTo(key) < 0 ? current.right : current.left;
        }
        return current;
    }

    /**
     * @param key номер листа дерева
     * @return значение узла дерева
     */
    public V find(K key) {
        if (root == null)
            return null;
        AvlLeaf found = root.find(key);
        return found.key.compareTo(key) == 0 ? (V)found.value : null;
    }

    public void change(K oldKey, K newKey) {
        AvlLeaf node =  findinner(oldKey);
        node.key = newKey;

    }
}

