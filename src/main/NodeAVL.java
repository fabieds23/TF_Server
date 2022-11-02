package main;



/**
 * Clase nodo AVL
 */
public class
NodeAVL {
    int dato, fe, ocurrencias; // factor de equilibrio
    String palabra;
    NodeAVL hijoIz, hijoDe;
    // constructor

    public NodeAVL(int dato, String palabra) {
        this.dato = dato;
        this.palabra = palabra;
        this.fe = 0; // no se sabe que tiene
        this.hijoIz = null;
        this.hijoDe = null;
    }
}
