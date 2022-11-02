package main;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Clase árbol binario de busqueda
 */
public class ArbolBinarioBusqueda {
    static final int COUNT = 10;
    //atributos
    public NodeBST root;
    public String owner;
    public String date;
    public ArbolBinarioBusqueda nextTree;
    public ArbolBinarioBusqueda prevTree;
    public String res;
    /**
     * Método constructor
     */
    public ArbolBinarioBusqueda(String owner, String date) {
        this.root = null;
        this.owner = owner;
        this.date = date;
    }

    public String getOwner() {
        return owner;
    }
    public String getDate() {
        return date;
    }

    /**
     * Método añadir nodo
     */
    public void addNode(Integer ocurrencias, String palabra){
        NodeBST newNode = new NodeBST(ocurrencias, palabra);
        if (root == null){
            root = newNode;
        } else {
            NodeBST carro = root;
            NodeBST parent;
            while(true){
                parent = carro;
                if (ocurrencias < carro.ocurrencias){
                    carro = carro.left;
                    if (carro == null){
                        parent.left = newNode;
                        return;
                    }
                }
                else{
                    carro = carro.right;
                    if(carro == null){
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }
    /**
     * Método encontrar nodoAux
     */
    public String findNodeAux(NodeBST comp, String palabra){
        if (comp == null) {
            return "nop";
        }
        if (Objects.equals(comp.palabra, palabra)){
            res = getOwner() + " posee " + comp.ocurrencias + " veces la palabra: " + palabra + " Creado el " + getDate();
            System.out.println(1);
            return "res:"+comp.palabra+comp.ocurrencias+getOwner();
        }

        /* then recur on left subtree */
        findNodeAux(comp.left, palabra);

        /* now recur on right subtree */
        findNodeAux(comp.right, palabra);
        return res;
    }
    /**
     * Método encontrar nodo
     */
    public String findNode(String palabra) {
        NodeBST comp = root;
        System.out.println(findNodeAux(comp, palabra));
        return findNodeAux(comp, palabra);
    }
    public String findNodeAuxB(NodeBST comp, String palabra){
        if (comp == null) {
            return "nop";
        }
        if (Objects.equals(comp.palabra, palabra)){
            res = comp.ocurrencias + " veces sale  " + palabra + " en el documento " + getOwner() + " del " + getDate();
            System.out.println(1);
            return "res:"+comp.palabra+comp.ocurrencias+getOwner();
        }

        /* then recur on left subtree */
        findNodeAuxB(comp.left, palabra);

        /* now recur on right subtree */
        findNodeAuxB(comp.right, palabra);
        return res;
    }
    public String findNodeB(String palabra) {
        NodeBST comp = root;
        System.out.println(findNodeAuxB(comp, palabra));
        return findNodeAuxB(comp, palabra);
    }
    public String findNodeAuxR(NodeBST comp, String palabra){
        if (comp == null) {
            return "nop";
        }
        if (Objects.equals(comp.palabra, palabra)){
            res = getDate() + " es la fecha en la que " + getOwner() + " fue creado y  " + palabra + " sale " + comp.ocurrencias;
            System.out.println(1);
            return "res:"+comp.palabra+comp.ocurrencias+getOwner();
        }

        /* then recur on left subtree */
        findNodeAuxR(comp.left, palabra);

        /* now recur on right subtree */
        findNodeAuxR(comp.right, palabra);
        return res;
    }
    public String findNodeR(String palabra) {
        NodeBST comp = root;
        System.out.println(findNodeAuxR(comp, palabra));
        return findNodeAuxR(comp, palabra);
    }
    /**
     * Método print
     */
    static void print2DUtil(NodeBST root, int space)
    {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += COUNT;

        // Process right child first
        print2DUtil(root.right, space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.ocurrencias+","+root.palabra + "\n");

        // Process left child
        print2DUtil(root.left, space);
    }

    static void print2D(NodeBST root)
    {
        // Pass initial space count as 0
        print2DUtil(root, 0);
    }
    private void inOrder(NodeBST node) { if (node == null) { return; } inOrder(node.left); System.out.printf("%s ", node.ocurrencias+node.palabra); inOrder(node.right); }

    public void showRice(){
        print2DUtil(root, 0);
        inOrder(root);
    }
}
