/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijktrapkg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import Dijktrapkg.Maps;
import Dijktrapkg.Maps.MapBuilder;

/**
 * Algoritmo de búsqueda de ruta más corta del gráfico Dijkstra (Dijkstra)
 * <br/> Cada vez que inicia una nueva búsqueda, debe crear dicho objeto
 *
 * @param <T> El tipo de clave principal del nodo
 * @author jake
 * @date 2014-7-26- 9:45:07 pm
 */

public class MapSearcher<T> {

    /**
     * Clase de resultado de búsqueda de ruta más corta
     *
     * @author Luis
     *
     * @param <T> El tipo de clave principal del nodo
     */
    public static class SearchResult<T> {

        /**
         * Resultado de la ruta más corta
         */
        List<T> path;
        /**
         * La distancia más corta
         */
        Integer distance;

        /**
         * Obtener ejemplo
         *
         * @param ruta resultado de ruta más corta
         * @param distance La distancia de ruta más corta
         * @return
         */
        protected static <T> SearchResult<T> valueOf(List<T> path, Integer distance) {
            SearchResult<T> r = new SearchResult<T>();
            r.path = path;
            r.distance = distance;
            return r;
        }

        public List<T> getPath() {
            return path;
        }

        public Integer getDistance() {
            return distance;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("path:");
            for (Iterator<T> it = this.path.iterator(); it.hasNext();) {
                sb.append(it.next());
                if (it.hasNext()) {
                    sb.append("->");
                }
            }
            sb.append("\n").append("distance:").append(distance);
            return sb.toString();
        }

    }

    /**
     * Mapa de objetos
     */
    Maps<T> map;
    /**
     * Iniciar nodo
     */
    Maps.Node<T> startNode;
    /**
     * Nodo final
     */
    Maps.Node<T> targetNode;
    /**
     * Nodo abierto
     */
    Set<Maps.Node<T>> open = new HashSet<Maps.Node<T>>();
    /**
     * Nodo cerrado
     */
    Set<Maps.Node<T>> close = new HashSet<Maps.Node<T>>();
    /**
     * Distancia de camino más corta
     */
    Map<Maps.Node<T>, Integer> path = new HashMap<Maps.Node<T>, Integer>();
    /**
     * Ruta más corta
     */
    Map<T, List<T>> pathInfo = new HashMap<T, List<T>>();

    /**
     * Punto de inicio de inicialización
     * <br /> Inicialmente, S contiene solo el punto de inicio s; U contiene
     * otros vértices excepto s, y la distancia del vértice en U es "la
     * distancia desde el punto de inicio s hasta el vértice" [Por ejemplo, la
     * distancia del vértice v en U es la longitud de (s, v), y luego s y v no
     * son adyacentes, entonces la distancia de v es ∞]. ID de origen @param del
     * nodo inicial
     *
     * @param map mapa global
     * @param closeSet se ha cerrado la lista de nodos
     * @return
     */
    @SuppressWarnings("unchecked")
    public Maps.Node<T> init(T source, Maps<T> map, Set<T> closeSet) {

        Map<T, Maps.Node<T>> nodeMap = map.getNodes();
        Maps.Node<T> startNode = nodeMap.get(source);
        // Poner el nodo inicial cerca
        close.add(startNode);
        // Poner otros nodos para abrir
        for (Maps.Node<T> node : nodeMap.values()) {
            if (!closeSet.contains(node.getId()) && !node.getId().equals(source)) {
                this.open.add(node);
            }
        }

        // Ruta inicial
        T startNodeId = startNode.getId();
        for (Entry<Maps.Node<T>, Integer> entry : startNode.getChilds().entrySet()) {
            Maps.Node<T> node = entry.getKey();
            if (open.contains(node)) {
                T nodeId = node.getId();
                path.put(node, entry.getValue());
                pathInfo.put(nodeId, new ArrayList<T>(Arrays.asList(startNodeId, nodeId)));
            }
        }

        for (Maps.Node<T> node : nodeMap.values()) {
            if (open.contains(node) && !path.containsKey(node)) {
                path.put(node, Integer.MAX_VALUE);
                pathInfo.put(node.getId(), new ArrayList<T>(Arrays.asList(startNodeId)));
            }
        }
        this.startNode = startNode;
        this.map = map;
        return startNode;
    }

    /**
     * Dijkstra recursivo
     *
     * @param start ha seleccionado el nodo más cercano
     */
    protected void computePath(Maps.Node<T> start) {
        // Seleccione "Vértice k con la distancia más corta" de U, y agregue el vértice k a S; mientras tanto, elimine el vértice k de U.
        Maps.Node<T> nearest = getShortestPath(start);
        if (nearest == null) {
            return;
        }
        // Actualiza la distancia desde cada vértice en U hasta el punto de partida s.
        // La razón para actualizar la distancia de los vértices en U es porque k se determinó en el paso anterior para encontrar la ruta más corta, de modo que k pueda usarse para actualizar la distancia de otros vértices
        // Por ejemplo, la distancia de (s, v) puede ser mayor que la distancia de (s, k) + (k, v).
        close.add(nearest);
        open.remove(nearest);
        // El resultado ha sido encontrado
        if (nearest == this.targetNode) {
            return;
        }
        Map<Maps.Node<T>, Integer> childs = nearest.getChilds();
        for (Maps.Node<T> child : childs.keySet()) {
            if (open.contains(child)) {// Si el nodo hijo está abierto
                Integer newCompute = path.get(nearest) + childs.get(child);
                if (path.get(child) > newCompute) {// La distancia establecida anteriormente es mayor que la distancia recién calculada
                    path.put(child, newCompute);

                    List<T> path = new ArrayList<T>(pathInfo.get(nearest.getId()));
                    path.add(child.getId());
                    pathInfo.put(child.getId(), path);
                }
            }
        }
        // computePath (start); // Repítelo para asegurarte de que todos los nodos secundarios estén atravesados
        //computePath(el más cercano);

        //); // Recurre hacia afuera hasta que se atraviesen todos los vértices
    }

    /**
     * Obtener el nodo hijo más cercano al nodo
     */
    private Maps.Node<T> getShortestPath(Maps.Node<T> node) {
        Maps.Node<T> res = null;
        int minDis = Integer.MAX_VALUE;
        for (Maps.Node<T> entry : path.keySet()) {
            if (open.contains(entry)) {
                int distance = path.get(entry);
                if (distance < minDis) {
                    minDis = distance;
                    res = entry;
                }
            }
        }
        return res;
    }

    /**
     * Obtén el camino más corto hasta el punto objetivo
     *
     * @param target Target
     * @return
     */
    public SearchResult<T> getResult(T target) {
        Maps.Node<T> targetNode = this.map.getNodes().get(target);
        if (targetNode == null) {
            //lanzar nueva RuntimeException("¡El nodo de destino no existe!");
        }
        this.targetNode = targetNode;
        // comienza a calcular
        this.computePath(startNode);
        return SearchResult.valueOf(pathInfo.get(target), path.get(targetNode));
    }

    /**
     * Imprime el camino más corto de todos los puntos
     */
    public void printPathInfo() {
        Set<Map.Entry<T, List<T>>> pathInfos = pathInfo.entrySet();
        for (Map.Entry<T, List<T>> pathInfo : pathInfos) {
            System.out.println(pathInfo.getKey() + ":" + pathInfo.getValue());
        }
    }

    /**
     * Métodos de prueba
     */
    //@org.junit.Test //ver si sera de utilidad esto
    public void test() {

        MapBuilder<String> mapBuilder = new Maps.MapBuilder<String>().create();
        // Construir nodo
        mapBuilder.addNode(Maps.Node.valueOf("A"));
        mapBuilder.addNode(Maps.Node.valueOf("B"));
        mapBuilder.addNode(Maps.Node.valueOf("C"));
        mapBuilder.addNode(Maps.Node.valueOf("D"));
        mapBuilder.addNode(Maps.Node.valueOf("E"));
        mapBuilder.addNode(Maps.Node.valueOf("F"));
        mapBuilder.addNode(Maps.Node.valueOf("G"));
        mapBuilder.addNode(Maps.Node.valueOf("H"));
        mapBuilder.addNode(Maps.Node.valueOf("I"));
        // Construir camino
        mapBuilder.addPath("A", "B", 1);
        mapBuilder.addPath("A", "F", 2);
        mapBuilder.addPath("A", "D", 4);
        mapBuilder.addPath("A", "C", 1);
        mapBuilder.addPath("A", "G", 5);
        mapBuilder.addPath("C", "G", 3);
        mapBuilder.addPath("G", "H", 1);
        mapBuilder.addPath("H", "B", 4);
        mapBuilder.addPath("B", "F", 2);
        mapBuilder.addPath("E", "F", 1);
        mapBuilder.addPath("D", "E", 1);
        mapBuilder.addPath("H", "I", 1);
        mapBuilder.addPath("C", "I", 1);

        // Construir mapa global
        Maps<String> map = mapBuilder.build();

        // Crea un buscador de ruta (necesitas crear un nuevo MapSearcher cada vez que buscas)
        MapSearcher<String> searcher = new MapSearcher<String>();
        // Crear una colección de nodos cerrados
        Set<String> closeNodeIdsSet = new HashSet<String>();
        closeNodeIdsSet.add("C");
        // Establecer el nodo inicial
        searcher.init("A", map, closeNodeIdsSet);
        // Obtenga el resultado
        SearchResult<String> result = searcher.getResult("G");
        System.out.println(result);
        //test.printPathInfo();
    }
}
