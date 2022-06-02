/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Municipiospkg;

import java.util.Collection; 
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class Dijkstra {
    
    // Mantener un index rapido de los nodos en el mapa dibujado
	private Map<String, Vertex> vertexNames;

	/**
	 * Construye un Dijkstra vacio con un mapa. La clave de un mapa es el nombre de un vertex
	 * y el valor del mapa es el objeto del vertice.
	 */
	public Dijkstra() {
		vertexNames = new HashMap<String, Vertex>();
	}

	/**
	 * Añade un vertice. Throws IllegalArgumentException
	 * excepcion si hay dos vertices con el mismo nombre
	 * 
	 * @param v
	 *          (Vertex) vertex para ser añadido dijkstra
	 */
	public void addVertex(Vertex v) {
		if (vertexNames.containsKey(v.name))
			throw new IllegalArgumentException("Cannot create new vertex with existing name.");

		vertexNames.put(v.name, v);
	}

	/**
	 * Obtiene una colección de todos los vértices de la dijkstra
	 * 
	 * @return (Collection<Vertex>) colección de todos los vértices de la dijkstra
	 */
	public Collection<Vertex> getVertices() {
		return vertexNames.values();
	}

	/**
	 * Obtiene el objeto de vértice con el nombre dado
	 * 
	 * @param name
	 *          (String) nombre del objeto de vértice solicitado
	 * @return (Vertex) objeto de vértice asociado con el nombre
	 */
	public Vertex getVertex(String name) {
		return vertexNames.get(name);
	}

	/**
	 * Adds a directed edge from vertex u to vertex v
	 * 
	 * @param nameU
	 *          (String) nombre del vertex u
	 * @param nameV
	 *          (String) nombre del vertex v
	 * @param cost
	 *          (double) costo del nodo entre vertex u y v
	 */
	public void addEdge(String nameU, String nameV, Double cost) {
		if (!vertexNames.containsKey(nameU))
			throw new IllegalArgumentException(nameU + " No existe, no se puede crear la arista.");
		if (!vertexNames.containsKey(nameV))
			throw new IllegalArgumentException(nameV + " no existe, no se puede crear la arista o vertice");
		Vertex sourceVertex = vertexNames.get(nameU);
		Vertex targetVertex = vertexNames.get(nameV);
		Edge newEdge = new Edge(sourceVertex, targetVertex, cost);
		sourceVertex.addEdge(newEdge);
	}

	/**
	 * Agrega un vertice no dirigido entre el vértice u y el vértice v agregando un borde dirigido
	 * 
	 * 
	 * @param nameU
	 *          (String) nombre del nodo u
	 * @param nameV
	 *          (String) nombre del nodo v
	 * @param cost
	 *          (double) costo del vertice entre vertex o nodo u y v
	 */
	public void addUndirectedEdge(String nameU, String nameV, double cost) {
		addEdge(nameU, nameV, cost);
		addEdge(nameV, nameU, cost);
	}


	/**
	 * Computes the euclidean distance between two points as described by their
	 * coordinates
	 * 
	 * @param ux
	 *          (double) x coordenada del punto u
	 * @param uy
	 *          (double) y coordenada para el punto u
	 * @param vx
	 *          (double) x coordinada para el v
	 * @param vy
	 *          (double) y coordinada del punto v
	 * @return (double) distancia entre los dos puntos
	 */
	public double computeEuclideanDistance(double ux, double uy, double vx, double vy) {
		double Xs = Math.pow(ux-vx, 2);
		double Ys = Math.pow(uy-vy, 2);
		double distance = Math.pow((Xs+Ys), 0.5);
		return distance;
	}

	/**
	 * Calcula la distancia euclidiana para todos los bordes del mapa utilizando el
	 * método calculado de Distancia Euclidiana.
	 */
	public void computeAllEuclideanDistances() {
		//para cada nodo en el mapa
		for (String u : vertexNames.keySet()) {
			//recorre todas las aristas del nodo
			for (Edge e : vertexNames.get(u).adjacentEdges) {
				//obtiene las coordenadas del P1 y P2
				double edgeSourceX = e.source.x;
				double edgeSourceY = e.source.y;
				double edgeTargetX = e.target.x;
				double edgeTargetY = e.target.y;
				//calcula la distancia euclidiana llamando
				//calcular el método de Distancia Euclidiana
				e.distance = computeEuclideanDistance(edgeSourceX, edgeSourceY,
						edgeTargetX, edgeTargetY);
			}
		}
	}

	/**
	 * Algoritmo de Dijkstra
	 * @param s
	 *          (String) Nombre del municipio inicial
	 */
	public void doDijkstra(String s) {

		List<String> LefttoCheck = new ArrayList<>();
		//añadir todos los vértices a la lista
		for (Vertex u : vertexNames.values()){
			LefttoCheck.add(u.name); 
		}
		//Maneje la ciudad de inicio, elimínela de la lista.
		int idxOfchecking = LefttoCheck.indexOf(s);
		Vertex checking = vertexNames.get(LefttoCheck.remove(idxOfchecking));

		//Ya sabemos que cada ciudad comienza con null anterior y una distancia de 0
		checking.distance = 0;//asi que se checkeas eso

		//Establezca cada distancia de vértice en 1000000
		for (int i = 0; i < LefttoCheck.size(); i++){
			Vertex v = vertexNames.get(LefttoCheck.get(i));
			v.distance = 10000000;
		}

		/* Revise la lista de adyacencia de la ciudad y actualice la distancia de los Vertices
* y campo fotovoltaico*/
		for (Edge e : checking.adjacentEdges){
			//Obtiene el Nodo del borde a editar
			Vertex target = e.target;
			int idxOfModifying = LefttoCheck.indexOf(target.name);
			Vertex modifying = vertexNames.get(LefttoCheck.get(idxOfModifying));

			//Asignar el previo
			modifying.prev = checking;
			//Actualziar la distancia
			modifying.distance = checking.distance + e.distance;
		}

		//mientras que los vértices aún no se han resuelto
		while (!LefttoCheck.isEmpty())
		{
			double smallestSoFar = 10000000;
			int indexToRemove = 0;
			//encuentra el nodo con el dv más bajo disponible
			for (int i = 0; i < LefttoCheck.size(); i++){
				Vertex v = vertexNames.get(LefttoCheck.get(i));
				if (v.distance < smallestSoFar){
					smallestSoFar = v.distance;
					indexToRemove = i;
				}
			}
			checking = vertexNames.get(LefttoCheck.remove(indexToRemove));

			for (Edge e : checking.adjacentEdges){
				
				//Obtiene el nodo del vertice a editar
				Vertex target = e.target;
				
				//Compruebe si se conoce el vertice
				if (LefttoCheck.contains(target.name)) {
					int idxOfModifying = LefttoCheck.indexOf(target.name);
					Vertex modifying = vertexNames.get(target.name);
					double tmp = checking.distance + e.distance;
					
					if (modifying.distance > tmp ) {
						//Actualizar la distancia 
						modifying.distance = tmp;
		
						//Asignar el previo
						modifying.prev = checking;
					}
				}
			}
		}
	}

	/**
	 * Devuelve una lista de aristas para una ruta del municipio s al municipio t. Esta será la
         * ruta más corta de s a t según lo prescrito por el algoritmo de Dijkstra
*
         * @param s
         * (Cadena) nombre de la ciudad inicial
         * @param t
         * (Cadena) terminación nombre de la ciudad
         * @return (List<Edge>) lista de aristas de s a t
	 */
	public List<Edge> getDijkstraPath(String s, String t) {
		//Restablezca todas las variables, revise todos los objetos de vértices
		for (Vertex u : vertexNames.values()){
			u.distance = 10000000;
			u.prev = null;
		}
		
		/* configura todos los campos anteriores y las distancias respectivamente
		 * usando el algoritmo de Dijkstra */
		doDijkstra(s);

		//Hacer lista
		List<Edge> path = new ArrayList<>();
		
		//Obtener el nodo v, del punto final
		Vertex v = vertexNames.get(t);
		while (v.prev != null){
			for (Edge e : v.adjacentEdges){
				if (e.target.equals(v.prev)){
					path.add(0, e);
				}
			}
			v = v.prev;
		}
		return path;
	}


	/**
	 * Imprime la lista de adyacencia de la dijkstra para la depuración
	 */
	public void printAdjacencyList() {
		for (String u : vertexNames.keySet()) {
			StringBuilder sb = new StringBuilder();
			sb.append(u);
			sb.append(" -> [ ");
			for (Edge e : vertexNames.get(u).adjacentEdges) {
				sb.append(e.target.name);
				sb.append("(");
				sb.append(e.distance);
				sb.append(") ");
			}
			sb.append("]");
			System.out.println(sb.toString());
		}
	}


	/** 
	 *Un método principal que ilustra cómo la GUI usa Dijkstra.java
         * lee un mapa y representarlo como un gráfico.
         * Puede modificar este método para probar el código en la línea de comandos. 
	 */
	public static void main(String[] argv) throws IOException {
		String vertexFile = "cityxy.txt"; 
		String edgeFile = "citypairs.txt";

		Dijkstra dijkstra = new Dijkstra();
		String line;

		// Read in the vertices
		BufferedReader vertexFileBr = new BufferedReader(new FileReader(vertexFile));
		while ((line = vertexFileBr.readLine()) != null) {
			String[] parts = line.split(",");
			if (parts.length != 3) {
				vertexFileBr.close();
				throw new IOException("Invalid line in vertex file " + line);
			}

			String cityname = parts[0];
			int x = Integer.valueOf(parts[1]);
			int y = Integer.valueOf(parts[2]);
			Vertex vertex = new Vertex(cityname, x, y);
			dijkstra.addVertex(vertex);
		}
		vertexFileBr.close();

		//Leer en los vertices
		BufferedReader edgeFileBr = new BufferedReader(new FileReader(edgeFile));
		while ((line = edgeFileBr.readLine()) != null) {
			String[] parts = line.split(",");
			if (parts.length != 3) {
				edgeFileBr.close();
				throw new IOException("Invalid line in edge file " + line);
			}
			dijkstra.addUndirectedEdge(parts[0], parts[1], Double.parseDouble(parts[2]));
		}
		edgeFileBr.close();

		// Calcular distancias.
		// Esto es lo que sucede cuando hace clic en el botón "Calcular todas las distancias".
		dijkstra.computeAllEuclideanDistances();

		// imprima una representación de lista de adyacencia del gráfico
		dijkstra.printAdjacencyList();

		// Esto es lo que sucede cuando haces clic en el botón "Dibujar la ruta de Dijkstra".

		// En la GUI, estos se configuran a través de los menús desplegables.
		String startCity = "Atlanta";//jutiapa municipio inicial
		String endCity = "Boston";//municipio final

		// Obtenga la ruta más corta ponderada entre la ciudad de inicio y la ciudad de finalización.
		List<Edge> path = dijkstra.getDijkstraPath(startCity, endCity);

		System.out.print("Shortest path between "+startCity+" and "+endCity+": ");
		System.out.println(path);
	}
    
}
