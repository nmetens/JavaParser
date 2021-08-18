/*
Author: Nathan Metens         CS 202      Instructor: Karla Fant
In this file, we implement the tree class. The tree contains a root
node and also various functions that help with insertion and balancing
of the tree. We have a display, set, fix violations, and rotation functions
that all work together to balance the red black tree according to the
red-black tree properties and algorithms. Further comments are supplied
for each function and its purpose in the creation of a balanced tree.
 */
package com.company;

public class redBlackTree {
    private node root; // redBlackTree "has a" node

    // this is the display function for the balanced tree
    public int display() {
        if(root != null) {
            inOrderDisplay(root); // calls the in-order traversal on the root
            return 1; // successful display
        }
        return 0; // there was nothing to display
    }

    // this is the in-order traversal of the red-black tree.
    // We first visit the left nodes (if they exist), then
    // we display the names of the students, then we traverse
    // to the right nodes and do the same
    private void inOrderDisplay(node Node){
        if(Node != null){ // base case
            if (Node.getLeft() != null) inOrderDisplay(Node.getLeft()); // traverse left first
            Node.displayingProgram(); // displays the user's program output
            System.out.println("\n****************************"); // delimiter for each display
            if (Node.getRight() != null) inOrderDisplay(Node.getRight()); // then go right and repeat
        }
    }

    // this function takes the name and code of a student and creates a new node
    // of it. That node is then passed into the tree where the value is compared
    // to each node and it is placed accordingly, just like in a normal BST insert.
    // If the red-black tree properties are not met, and the tree is unbalanced,
    // then we enter the fixViolations function.
    public void insert(String new_name){
        node Node = new node(new_name);
        Node.creatingProgram(); // saves the user program into the node
        root = insertData(root, Node);
        fixViolations(Node); // always check if the RED-BLACK tree properties are satisfied after each insertion to remain balanced
    }

    // compare the names of each student's code by the sum of their ASCII values.
    // The larger names are stored on the right, with the corresponding programs
    // and the smaller names are stored to the left of the tree.
    // This is the same algorithm as BST. The difference happens when we fix the
    // violations of the red-black tree.
    private node insertData(node root, node Node){
        if(root == null) return Node; // no data to add, we only have one Node
        if(root.lessThan(Node.getName())) { // compares sum of ASCII values
            root.setLeft(insertData(root.getLeft(), Node)); // setting the left pointer of root to new data
            root.getLeft().setParent(root); // set the parent of the new added node to be this root
        } else if(!root.lessThan(Node.getName())) { // the ASCII values of the Node were equal to or larger than this root
            root.setRight(insertData(root.getRight(), Node)); // setting the new data to the right of the root
            root.getRight().setParent(root); // set the parent of the newly added node
        }
        return root;
    }

    // here we perform a right rotation of the tree when the tree
    // is unbalanced. Following the right rotation algorithm.
    private void rightRotate(node Node){
        // set the node's left child to be its child's right child
        node leftTemp = Node.getLeft(); Node.setLeft(leftTemp.getRight());

        // check nullity of leftTemp
        if(Node.getLeft() != null) Node.getLeft().setParent(Node); // set leftTemp's left to Node's parent

        // we have rotated this Node, it's left and it's parent to the right
        leftTemp.setParent(Node.getParent());

        // going to be the new root node
        if(leftTemp.getParent() == null) root = leftTemp;
        else if(Node == Node.getParent().getLeft()) Node.getParent().setLeft(leftTemp);
        else Node.getParent().setRight(leftTemp);

        // right rotation is complete
        leftTemp.setRight(Node); Node.setParent(leftTemp);
    }

    // this is the same exact things as the right rotation
    // algorithm except that all the left nodes are flipped
    // with the right nodes. This is a symmetrical case.
    private void leftRotate(node Node){
        node rightTemp = Node.getRight(); Node.setRight(rightTemp.getLeft());

        if(Node.getRight() != null) Node.getRight().setParent(Node);

        rightTemp.setParent(Node.getParent());

        if(rightTemp.getParent() == null) root = rightTemp;
        else if(Node == Node.getParent().getLeft()) Node.getParent().setLeft(rightTemp);
        else Node.getParent().setRight(rightTemp);

        rightTemp.setLeft(Node);Node.setParent(rightTemp);
    }

    // This is the intensive algorithm for testing and checking
    // whether all of the red-black tree properties are satisfied
    // in order to have a balanced binary tree.
    private void fixViolations(node Node){
        node parentNode = null; node grandParentNode = null;

        /** Properties of a red-black tree:
         1) The root node is always BLACK
         2) All nodes in the tree are either RED or BLACK
         3) Every null pointer is a BLACK
         4) If a node is RED, both children must be BLACK
         5) Every path from any particular node to any descendant
            null pointers has the same number of BLACK nodes.
         **/

        if(Node.getParent() != null) { // check for null ptr before entering
            if(Node.getParent().getParent() != null) { // we don't want to access a null ptr
                // the root node must always be black. If it is not black, we have to complete rotations and re-coloring...
                while (Node != root && Node.getColor() != nodeColor.BLACK && Node.getParent().getColor() == nodeColor.RED) { // check the properties
                    parentNode = Node.getParent(); grandParentNode = Node.getParent().getParent();

                    // check the properties
                    if (parentNode == grandParentNode.getLeft()) { // parent node is the left child of the grandparent
                        node uncle = grandParentNode.getRight(); // make the uncle the other child of the grandparent

                        if (uncle != null && uncle.getColor() == nodeColor.RED) { // violates property (4)
                            // must re-color
                            grandParentNode.setColor(nodeColor.RED);
                            // the children of a red node must always be black
                            parentNode.setColor(nodeColor.BLACK);
                            uncle.setColor(nodeColor.BLACK);
                            Node = grandParentNode;
                        } else { // if the uncle is black, we must make a rotation
                            if (Node == parentNode.getRight()) { // need to rotate the parent of Node to the left
                                leftRotate(parentNode);
                                Node = parentNode; // replace the parent with the Node
                                parentNode = Node.getParent(); // complete rotation
                            }
                            rightRotate(grandParentNode); // check for a right rotation
                            // set the correct colors again
                            nodeColor tempColor = parentNode.getColor();
                            parentNode.setColor(grandParentNode.getColor());
                            grandParentNode.setColor(tempColor);
                            Node = parentNode;
                        }
                    } else { // the parent is a right child. this is symmetric to the previous block in the if statement
                        node uncle = grandParentNode.getLeft();

                        if (uncle != null && uncle.getColor() == nodeColor.RED) {
                            // same as before, just the mirroring result
                            grandParentNode.setColor(nodeColor.RED);
                            parentNode.setColor(nodeColor.BLACK);
                            uncle.setColor(nodeColor.BLACK);
                            Node = grandParentNode;
                        } else {
                            if (Node == parentNode.getLeft()) { // now rotate to the right
                                rightRotate(parentNode);
                                Node = parentNode;
                                parentNode = Node.getParent();
                            }
                            leftRotate(grandParentNode); // again check the left
                            nodeColor tempColor = parentNode.getColor();
                            parentNode.setColor(grandParentNode.getColor());
                            grandParentNode.setColor(tempColor);
                            Node = parentNode;
                        }
                    }
                    if (root.getColor() == nodeColor.RED) { // check for case (1)
                        root.setColor(nodeColor.BLACK); // the root node must always be black
                    }
                }
            }
        }
    }
}
