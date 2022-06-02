/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Municipiospkg;

/**
 *
 * @author Luis
 */
public class Edge { //Para las aristas
    
     public double distance;//variables publicas y constructores
  public Vertex source;
  public Vertex target;

  public Edge(Vertex vertex1, Vertex vertex2, double weight) {
    source = vertex1;
    target = vertex2;
    this.distance = weight;
  }

  public String toString() {
    return source + " - " + target;
  }
    
}
