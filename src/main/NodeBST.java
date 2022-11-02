package main;

import java.util.ArrayList;
/**
 * Clase ára crear NodeBST
 */
public class NodeBST {

    //atributos
    public Integer ocurrencias;
    public String palabra;
    public NodeBST left;
    public NodeBST right;

    /**
     * Método constructor
     */
    public NodeBST(Integer ocurrencias, String palabra) {
        this.ocurrencias = ocurrencias;
        this.palabra = palabra;
        this.left = null;
        this.right = null;
    }
}
