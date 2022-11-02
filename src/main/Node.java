package main;

import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.Serializable;

/**
 * Clase Node para alimentar la Lista Doblemente Enlazada Circular
 */
public class Node implements Serializable {
    public String path;
    public String name;
    public String type;
    public String date;
    public Node next;
    public Node prev;

    /**
     * Método constructor
     */
    public  Node (String path, String name, String type, String date) {
        this.path = path;
        this.name  = name;
        this.type = type;
        this.date = date;
        this.next = null;
        this.prev = null;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
