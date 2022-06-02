/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Municipiospkg;

import java.util.LinkedList; 
import java.util.List;

/**
 *
 * @author Luis
 */
public class Vertex { //mas que todo constructores
    
     public String name;
  public int x;
  public int y;
  public boolean known;
  public double distance; // Distancia total desde el punto de origen
  public Vertex prev; //nodo anterior
  public List<Edge> adjacentEdges;

  public Vertex(String name, int x, int y) {
    this.name = name;
    this.x = x;
    this.y = y;
    // inicia como bool no inicializado
    // asi que  es == false and distance == 0.0
    adjacentEdges = new LinkedList<Edge>();
    prev = null;
  }

  @Override
  public int hashCode() {
    // we assume that each vertex has a unique name
    return name.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (!(o instanceof Vertex)) {
      return false;
    }
    Vertex oVertex = (Vertex) o;

    return name.equals(oVertex.name) && x == oVertex.x && y == oVertex.y;
  }

  public void addEdge(Edge edge) {
    adjacentEdges.add(edge);
  }

  public String toString() {
    return name + " (" + x + ", " + y + ")";
  }
    
}
