package main;

/**
 * Clase para crear arboles AVL
 */
public class AVLTree {
    private NodeAVL root;
    public String owner;
    public String date;

    /**
     * Método constructor del AVL
     */

    public AVLTree(String owner, String date) {
        root = null; //inicializa la raíz en nulo
        this.owner = owner;
        this.date = date;
    }

    /**
     * Método para obtener raíz del AVL
     */
    public NodeAVL obtenerRaiz(){
        return root;
    }

    /**
     * Método para buscar en árbol AVL
     */
    public NodeAVL buscar(int d, NodeAVL r) {
        if(root == null) {
            return null;
        }else if(r.dato==d) {
            return r;
        }else if(r.dato<d) {
            return buscar(d, r.hijoDe);
        }else{
            return buscar(d, r.hijoIz);
        }
    }
    /**
     * Método para obtener factor de balanceo
     */
    public int feObs(NodeAVL x) {
        if(x == null) {
            return -1;
        }else{
            return x.fe;
        }

    }
    /**
     * Método para rotar a la izquierda
     */

    public NodeAVL leftRotation(NodeAVL c) {
        NodeAVL aux = c.hijoIz; //puntero al c del hijo izquierdo.
        c.hijoIz = aux.hijoDe; // reacomodar los punteros
        aux.hijoDe = c;
        c.fe = Math.max(feObs(c.hijoIz), feObs(c.hijoDe)) +1; // el nivel más 1 es el factor de equilibrio.
        aux.fe = Math.max(feObs(aux.hijoIz), feObs(aux.hijoDe)) + 1;
        return aux;
    }

    /**
     * Método para rotar a la derecha
     */

    public NodeAVL rightRotation(NodeAVL c) {
        NodeAVL aux = c.hijoDe;
        c.hijoDe = aux.hijoIz;
        aux.hijoIz = c;
        c.fe = Math.max(feObs(c.hijoIz), feObs(c.hijoDe)) +1;
        aux.fe = Math.max(feObs(aux.hijoIz), feObs(aux.hijoDe)) + 1;
        return aux;
    }

    /**
     * Método para rotar doblemente a la izquierda
     */
    public NodeAVL leftDR(NodeAVL c) {
        NodeAVL temporal;
        c.hijoIz = rightRotation(c.hijoIz);
        temporal = leftRotation(c);
        return temporal;
    }

    /**
     * Método para rotar doblemente a la derecha
     */

    public NodeAVL rightDR(NodeAVL c) {
        NodeAVL temp;
        c.hijoDe = leftRotation(c.hijoDe);
        temp = rightRotation(c);
        return temp;
    }

    /**
     * Método para insertar nodos de manera balanceada
     */
    public NodeAVL insertAVL(NodeAVL nuevo, NodeAVL subAr) {
        NodeAVL nuevoPadre = subAr;
        if(nuevo.dato< subAr.dato) {
            if(subAr.hijoIz == null) {
                subAr.hijoIz = nuevo;
            }else{
                subAr.hijoIz=insertAVL(nuevo,subAr.hijoIz);
                if(feObs(subAr.hijoIz) - feObs(subAr.hijoDe) == 2) {
                    if(nuevo.dato < subAr.hijoIz.dato) {
                        nuevoPadre = leftRotation(subAr);
                    }else {
                        nuevoPadre = leftDR(subAr);
                    }
                }
            }
        } else if (nuevo.dato > subAr.dato) {
            if (subAr.hijoDe == null) {
                subAr.hijoDe = nuevo;
            }else{
                subAr.hijoDe = insertAVL(nuevo, subAr.hijoDe);
                if((feObs(subAr.hijoDe) - feObs(subAr.hijoIz) == 2)) {
                    if(nuevo.dato > subAr.hijoDe.dato) {
                        nuevoPadre = rightRotation(subAr);
                    }else{
                        nuevoPadre = rightDR(subAr);
                    }
                }
            }
            // nodo duplicado
        }else {
            System.out.println("Nodo duplicado");
        }
        // actualizar la altura
        if ((subAr.hijoIz == null ) && (subAr.hijoDe != null)) {
            subAr.fe = subAr.hijoDe.fe + 1; //sumar 1 para balancear
        }else if((subAr.hijoDe == null) && (subAr.hijoIz != null)){
            subAr.fe = subAr.hijoIz.fe + 1; // actualizar factor de equilibrio
        }else {
            subAr.fe = Math.max(feObs(subAr.hijoIz), feObs(subAr.hijoDe)) + 1; // obtiene el máximo y le suma un 1
        }
        return nuevoPadre;
    }
    /**
     * Método para insertar nodo
     */
    public void insertar(int d ) {
        NodeAVL nuevo = new NodeAVL(d);
        if (root == null){
            root = nuevo;
        }else {
            root = insertAVL(nuevo, root);
        }
    }
    // Recorridos

    /**
     * Método recorrido inOrden
     */
    public void inOrden (NodeAVL r) {
        if(r != null){
            inOrden(r.hijoIz);
            System.out.print(r.dato + ", ");
            inOrden(r.hijoDe);
        }
    }
    /**
     * Método recorrido preOrden
     */
    public void preOrden (NodeAVL r) {
        if (r != null) {
            System.out.print(r.dato + ", ");
            inOrden(r.hijoIz);
            inOrden(r.hijoDe);
        }
    }
    /**
     * Método recorrido postOrden
     */
    public void postOrden (NodeAVL r) {
        if (r != null) {
            postOrden(r.hijoIz);
            postOrden(r.hijoDe);
            System.out.print(r.dato + ", ");
        }
    }

}