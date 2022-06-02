/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafosPkg;

/**
 *
 * @author Ruldin Ayala
 * Preguntas:
 * Para qué sirve la implementación Comparable?
 * y porqué es de tipo Nodo
 */
public class Nodo implements Comparable<Nodo> {//es para comparar objetos entre si y tambien para poder ordenarlos en matrices o listas
    String id; //era char
    int  distancia   = Integer.MAX_VALUE;
    Nodo procedencia = null; //nodo anterior que comineza con un null
    
    public Nodo(String x, int d, Nodo p) { //constructores para el id, distancia y la procedencia //era x char
        id=x; distancia=d; procedencia=p; 
    }
    
    public Nodo(String x) { //era char x
        this(x, 0, null); 
    }
    
    public int compareTo(Nodo tmp) { 
        return this.distancia-tmp.distancia; 
    }
    
    public boolean equals(Object o) {
        Nodo tmp = (Nodo) o;
        if(tmp.id==this.id) return true;
        return false;
    }
}
