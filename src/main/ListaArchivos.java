package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.io.*;
import java.util.*;
import static main.ArbolBinarioBusqueda.*;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 * Clase Lista Doblemente Enlazada Circular
 */
public class ListaArchivos implements Serializable {
    public Node head;
    public Node tail;
    public Node tmp;
    public Integer size;

    public ListaArchivos() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Método para añadir elementos a la lista
     */
    public void appendItem(String path, String name, String type, String date) {
        Node nuevaAdd = new Node(path, name, type, date);
        if (head == null) {
            head = nuevaAdd;
            nuevaAdd.next = head;
            nuevaAdd.prev = head;
            tail = nuevaAdd;
            size++;
        } else {
            Node ultimo = head.prev;
            tail.next = nuevaAdd;
            nuevaAdd.next = head;
            tail = nuevaAdd;
            head.prev = tail;
            tail.prev = ultimo;
            size++;
        }
    }
    /**
     * Método para borrar un elemento de la lista
     */
    public void delete(String name){
        int c = 0;
        if (Objects.equals(head.getName(), name)){
            head = head.next;
            size -= 1;
        }
        tmp = head;
        while (tmp.next != null){
            c++;
            if(c > size){
                break;
            }
            if (Objects.equals(tmp.next.getName(), name)){
                tmp.next = tmp.next.next;
                size -= 1;
            }
            else {
                tmp = tmp.next;
            }
        }
    }

    public void showList() {
        Node carro = head;
        int breaker = 0;
        while (breaker < this.size) {
            breaker++;
            System.out.println(carro.getName() + "," + carro.getType() + "," + carro.getPath());
            carro = carro.next;
        }
    }
    /**
     * Método para parsear documentos
     */

    public ArrayList<String> parseParseamos(String busca){
        ArrayList<ArbolBinarioBusqueda> neverita =new ArrayList<>();
        ArrayList<String> resultado = new ArrayList<>();
        Node carro = head;
        int breaker = 0;
        while (breaker < this.size) {
            breaker++;
            String nombre = carro.getName();
            String tipo = carro.getType();
            String path = carro.getPath();
            String date = carro.getDate();
            System.out.println("adele");
            if (Objects.equals(tipo, ".pdf")){
                try{
                    ArbolBinarioBusqueda tempdf = new ArbolBinarioBusqueda(nombre, date);
                    ArrayList<Integer> listaOcur = new ArrayList<>();
                    File file = new File(carro.getPath());
                    PDDocument document = Loader.loadPDF(file);
                    PDFTextStripper pdftext = new PDFTextStripper();
                    String pdftextdata = pdftext.getText(document);
                    String[] resSplit = pdftextdata.split("[ \\n\\t\\r\\s,.;:!?(){}]+");
                    for (String check : resSplit) {
                        int apariciones = 0;
                        for (String s : resSplit) {
                            if (Objects.equals(check, s)) {
                                apariciones++;
                            }
                        }
                        tempdf.addNode(apariciones,check);
                    }
                    tempdf.addNode(7,"pepe");
                    tempdf.addNode(2,"juan");
                    tempdf.addNode(1,"diego");
                    tempdf.addNode(5,"carlos");
                    System.out.println(listaOcur);



                    //tempdf.showRice();
                    neverita.add(tempdf);
                }
                catch (IOException e){
                    System.out.println("No funca");
                }
            }

            if (Objects.equals(tipo, "docx")){
                System.out.println(2);
                try{
                    ArbolBinarioBusqueda tempdf = new ArbolBinarioBusqueda(nombre, date);
                    XWPFDocument docx = new XWPFDocument(new FileInputStream(carro.getPath()));
                    List<XWPFParagraph> parrafo = docx.getParagraphs();
                    String textoFullDocx = "";
                    List finalDocx = new ArrayList();
                    for (XWPFParagraph paragraph : parrafo) {
                        System.out.println(3);
                        textoFullDocx = textoFullDocx + " " + paragraph.getText();
                    }
                    String[] divididoDocx = textoFullDocx.split(" ");
                    String Docx = "";
                    for (int j = 0; j < divididoDocx.length; j++) {
                        String elemento = divididoDocx[j];
                        if (!elemento.equals(" ")) {
                            System.out.println(1);
                            finalDocx.add(divididoDocx[j]);
                            String Docxtemp = finalDocx.toString().replace("[", "");
                            Docx = Docxtemp.replace("]", "");
                        }
                    }

                    for (int i = 0; i < finalDocx.size(); i++){
                        finalDocx.remove(" ");
                        finalDocx.remove("");
                    }
                    for (Object check : finalDocx) {
                        int apariciones = 0;
                        for (Object s : finalDocx) {
                            if (Objects.equals(check, s)) {
                                apariciones++;
                            }
                        }
                        tempdf.addNode(apariciones,check.toString());
                    }
                    //tempdf.showRice();
                    neverita.add(tempdf);
                }
                catch (IOException e){
                    System.out.println("No funca");
                }
            }
            if (Objects.equals(tipo, ".txt")){
                try{
                    ArbolBinarioBusqueda tempdf = new ArbolBinarioBusqueda(nombre, date);
                    File file = new File(carro.getPath());
                    Scanner scan = new Scanner(file);
                    String textoFulltxt = "";
                    List finalTXT = new ArrayList();
                    while (scan.hasNextLine()) {
                        textoFulltxt = textoFulltxt + scan.nextLine();
                    }
                    String[] divididoTXT = textoFulltxt.split("[ \n\t\r,.;:!?(){}]");
                    String TXT = "";
                    for (int j = 0; j < divididoTXT.length; j++) {
                        String elemento = divididoTXT[j];
                        if (!elemento.equals("")) {
                            finalTXT.add(divididoTXT[j]);
                            String TXTtemp = finalTXT.toString().replace("[", "");
                            TXT = TXTtemp.replace("]", "");
                        }
                    }
                    for (Object check : finalTXT) {
                        int apariciones = 0;
                        for (Object s : finalTXT) {
                            if (Objects.equals(check, s)) {
                                apariciones++;
                            }
                        }
                        tempdf.addNode(apariciones,check.toString());
                    }
                    //tempdf.showRice();
                    neverita.add(tempdf);
                }
                catch (IOException e){
                    System.out.println("ay mama");
                }

            }
            carro = carro.next;
        }
        for (ArbolBinarioBusqueda arbolBinarioBusqueda : neverita) {
            resultado.add(arbolBinarioBusqueda.findNode(busca));
        }
        return resultado;
    }
    /**
     * Método para parsear documentos
     */
    public ArrayList<String> parseParseamosB(String busca){
        ArrayList<ArbolBinarioBusqueda> neverita =new ArrayList<>();
        ArrayList<String> resultado = new ArrayList<>();
        Node carro = head;
        int breaker = 0;
        while (breaker < this.size) {
            breaker++;
            String nombre = carro.getName();
            String tipo = carro.getType();
            String path = carro.getPath();
            String date = carro.getDate();
            System.out.println("adele");
            if (Objects.equals(tipo, ".pdf")){
                try{
                    ArbolBinarioBusqueda tempdf = new ArbolBinarioBusqueda(nombre, date);
                    ArrayList<Integer> listaOcur = new ArrayList<>();
                    File file = new File(carro.getPath());
                    PDDocument document = Loader.loadPDF(file);
                    PDFTextStripper pdftext = new PDFTextStripper();
                    String pdftextdata = pdftext.getText(document);
                    String[] resSplit = pdftextdata.split("[ \\n\\t\\r\\s,.;:!?(){}]+");
                    for (String check : resSplit) {
                        int apariciones = 0;
                        for (String s : resSplit) {
                            if (Objects.equals(check, s)) {
                                apariciones++;
                            }
                        }
                        tempdf.addNode(apariciones,check);
                    }
                    tempdf.addNode(7,"pepe");
                    tempdf.addNode(2,"juan");
                    tempdf.addNode(1,"diego");
                    tempdf.addNode(5,"carlos");
                    System.out.println(listaOcur);



                    //tempdf.showRice();
                    neverita.add(tempdf);
                }
                catch (IOException e){
                    System.out.println("No funca");
                }
            }

            if (Objects.equals(tipo, "docx")){
                System.out.println(2);
                try{
                    ArbolBinarioBusqueda tempdf = new ArbolBinarioBusqueda(nombre, date);
                    XWPFDocument docx = new XWPFDocument(new FileInputStream(carro.getPath()));
                    List<XWPFParagraph> parrafo = docx.getParagraphs();
                    String textoFullDocx = "";
                    List finalDocx = new ArrayList();
                    for (XWPFParagraph paragraph : parrafo) {
                        System.out.println(3);
                        textoFullDocx = textoFullDocx + " " + paragraph.getText();
                    }
                    String[] divididoDocx = textoFullDocx.split(" ");
                    String Docx = "";
                    for (int j = 0; j < divididoDocx.length; j++) {
                        String elemento = divididoDocx[j];
                        if (!elemento.equals(" ")) {
                            System.out.println(1);
                            finalDocx.add(divididoDocx[j]);
                            String Docxtemp = finalDocx.toString().replace("[", "");
                            Docx = Docxtemp.replace("]", "");
                        }
                    }

                    for (int i = 0; i < finalDocx.size(); i++){
                        finalDocx.remove(" ");
                        finalDocx.remove("");
                    }
                    for (Object check : finalDocx) {
                        int apariciones = 0;
                        for (Object s : finalDocx) {
                            if (Objects.equals(check, s)) {
                                apariciones++;
                            }
                        }
                        tempdf.addNode(apariciones,check.toString());
                    }
                    //tempdf.showRice();
                    neverita.add(tempdf);
                }
                catch (IOException e){
                    System.out.println("No funca");
                }
            }
            if (Objects.equals(tipo, ".txt")){
                try{
                    ArbolBinarioBusqueda tempdf = new ArbolBinarioBusqueda(nombre, date);
                    File file = new File(carro.getPath());
                    Scanner scan = new Scanner(file);
                    String textoFulltxt = "";
                    List finalTXT = new ArrayList();
                    while (scan.hasNextLine()) {
                        textoFulltxt = textoFulltxt + scan.nextLine();
                    }
                    String[] divididoTXT = textoFulltxt.split("[ \n\t\r,.;:!?(){}]");
                    String TXT = "";
                    for (int j = 0; j < divididoTXT.length; j++) {
                        String elemento = divididoTXT[j];
                        if (!elemento.equals("")) {
                            finalTXT.add(divididoTXT[j]);
                            String TXTtemp = finalTXT.toString().replace("[", "");
                            TXT = TXTtemp.replace("]", "");
                        }
                    }
                    for (Object check : finalTXT) {
                        int apariciones = 0;
                        for (Object s : finalTXT) {
                            if (Objects.equals(check, s)) {
                                apariciones++;
                            }
                        }
                        tempdf.addNode(apariciones,check.toString());
                    }
                    //tempdf.showRice();
                    neverita.add(tempdf);
                }
                catch (IOException e){
                    System.out.println("ay mama");
                }

            }
            carro = carro.next;
        }
        for (ArbolBinarioBusqueda arbolBinarioBusqueda : neverita) {
            resultado.add(arbolBinarioBusqueda.findNodeB(busca));
        }
        return resultado;
    }
    /**
     * Método para parsear documentos
     */
    public ArrayList<String> parseParseamosR(String busca){
        ArrayList<ArbolBinarioBusqueda> neverita =new ArrayList<>();
        ArrayList<String> resultado = new ArrayList<>();
        Node carro = head;
        int breaker = 0;
        while (breaker < this.size) {
            breaker++;
            String nombre = carro.getName();
            String tipo = carro.getType();
            String path = carro.getPath();
            String date = carro.getDate();
            System.out.println("adele");
            if (Objects.equals(tipo, ".pdf")){
                try{
                    ArbolBinarioBusqueda tempdf = new ArbolBinarioBusqueda(nombre, date);
                    ArrayList<Integer> listaOcur = new ArrayList<>();
                    File file = new File(carro.getPath());
                    PDDocument document = Loader.loadPDF(file);
                    PDFTextStripper pdftext = new PDFTextStripper();
                    String pdftextdata = pdftext.getText(document);
                    String[] resSplit = pdftextdata.split("[ \\n\\t\\r\\s,.;:!?(){}]+");
                    for (String check : resSplit) {
                        int apariciones = 0;
                        for (String s : resSplit) {
                            if (Objects.equals(check, s)) {
                                apariciones++;
                            }
                        }
                        tempdf.addNode(apariciones,check);
                    }
                    tempdf.addNode(7,"pepe");
                    tempdf.addNode(2,"juan");
                    tempdf.addNode(1,"diego");
                    tempdf.addNode(5,"carlos");
                    System.out.println(listaOcur);



                    //tempdf.showRice();
                    neverita.add(tempdf);
                }
                catch (IOException e){
                    System.out.println("No funca");
                }
            }

            if (Objects.equals(tipo, "docx")){
                System.out.println(2);
                try{
                    ArbolBinarioBusqueda tempdf = new ArbolBinarioBusqueda(nombre, date);
                    XWPFDocument docx = new XWPFDocument(new FileInputStream(carro.getPath()));
                    List<XWPFParagraph> parrafo = docx.getParagraphs();
                    String textoFullDocx = "";
                    List finalDocx = new ArrayList();
                    for (XWPFParagraph paragraph : parrafo) {
                        System.out.println(3);
                        textoFullDocx = textoFullDocx + " " + paragraph.getText();
                    }
                    String[] divididoDocx = textoFullDocx.split(" ");
                    String Docx = "";
                    for (int j = 0; j < divididoDocx.length; j++) {
                        String elemento = divididoDocx[j];
                        if (!elemento.equals(" ")) {
                            System.out.println(1);
                            finalDocx.add(divididoDocx[j]);
                            String Docxtemp = finalDocx.toString().replace("[", "");
                            Docx = Docxtemp.replace("]", "");
                        }
                    }

                    for (int i = 0; i < finalDocx.size(); i++){
                        finalDocx.remove(" ");
                        finalDocx.remove("");
                    }
                    for (Object check : finalDocx) {
                        int apariciones = 0;
                        for (Object s : finalDocx) {
                            if (Objects.equals(check, s)) {
                                apariciones++;
                            }
                        }
                        tempdf.addNode(apariciones,check.toString());
                    }
                    //tempdf.showRice();
                    neverita.add(tempdf);
                }
                catch (IOException e){
                    System.out.println("No funca");
                }
            }
            if (Objects.equals(tipo, ".txt")){
                try{
                    ArbolBinarioBusqueda tempdf = new ArbolBinarioBusqueda(nombre, date);
                    File file = new File(carro.getPath());
                    Scanner scan = new Scanner(file);
                    String textoFulltxt = "";
                    List finalTXT = new ArrayList();
                    while (scan.hasNextLine()) {
                        textoFulltxt = textoFulltxt + scan.nextLine();
                    }
                    String[] divididoTXT = textoFulltxt.split("[ \n\t\r,.;:!?(){}]");
                    String TXT = "";
                    for (int j = 0; j < divididoTXT.length; j++) {
                        String elemento = divididoTXT[j];
                        if (!elemento.equals("")) {
                            finalTXT.add(divididoTXT[j]);
                            String TXTtemp = finalTXT.toString().replace("[", "");
                            TXT = TXTtemp.replace("]", "");
                        }
                    }
                    for (Object check : finalTXT) {
                        int apariciones = 0;
                        for (Object s : finalTXT) {
                            if (Objects.equals(check, s)) {
                                apariciones++;
                            }
                        }
                        tempdf.addNode(apariciones,check.toString());
                    }
                    //tempdf.showRice();
                    neverita.add(tempdf);
                }
                catch (IOException e){
                    System.out.println("ay mama");
                }

            }
            carro = carro.next;
        }
        for (ArbolBinarioBusqueda arbolBinarioBusqueda : neverita) {
            resultado.add(arbolBinarioBusqueda.findNodeR(busca));
        }
        return resultado;
    }
}

