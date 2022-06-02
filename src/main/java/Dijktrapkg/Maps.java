/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijktrapkg;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Mapa
 *
 * @author Luis
 * @param <T> clave primaria de nodo
 */
public class Maps<T> {

    /**
     * Colección de todos los nodos Nodo Id-Nodo
     */
    private Map<T, Node<T>> nodes = new HashMap<T, Node<T>>();

    /**
     * Constructor de mapas
     *
     * @author Luis
     *
     */
    public static class MapBuilder<T> {

        /**
         * ejemplo de mapa
         */
        private Maps<T> map = new Maps<T>();

        /**
         * Construir MapBuilder
         *
         * @return MapBuilder
         */
        public MapBuilder<T> create() {
            return new MapBuilder<T>();
        }

        /**
         * Agregar nodo
         *
         * @param node
         * @return
         */
        public MapBuilder<T> addNode(Node<T> node) {
            map.nodes.put(node.getId(), node);
            return this;
        }

        /**
         * Agregar ruta
         *
         * @param node1Id nodeId
         * @param node2Id nodeId
         * @param peso
         * @return
         */
        public MapBuilder<T> addPath(T node1Id, T node2Id, int weight) {
            Node<T> node1 = map.nodes.get(node1Id);
            if (node1 == null) {//lanzar una nueva RuntimeException ("No se puede encontrar el nodo:" + node1Id);
                //7throws new RuntimeException ("No se puede encontrar el nodo:" + node1Id);
            }

            Node<T> node2 = map.nodes.get(node2Id);
            if (node2 == null) {
                //lanzar una nueva RuntimeException ("No se puede encontrar el nodo:" + node2Id);
            }

            node1.getChilds().put(node2, weight);
            node2.getChilds().put(node1, weight);
            return this;
        }

        /**
         * Construir mapa
         *
         * @return map
         */
        public Maps<T> build() {
            return this.map;
        }

    }

    /**
     * Nodo
     *
     * @author jake
     * @date 2014-7-26- 9:51:31 pm
     * @param <T> tipo de clave primaria de nodo
     */
    public static class Node<T> {

        /**
         * Clave primaria de nodo
         */
        private T id;

        /**
         * Ruta de conectividad de nodo Nodos-pesos conectados
         */
        private Map<Node<T>, Integer> childs = new HashMap<Node<T>, Integer>();

        /**
         * Método de construcción Clave primaria del nodo de identificación
         * @param
         */
        public Node(T id) {
            this.id = id;
        }

        /**
         * Obtener ejemplos
         *
         * @param id clave principal
         * @return
         */
        public static <T> Node<T> valueOf(T id) {
            return new Node<T>(id);
        }

        /**
         * es efectivo Para cambiar dinámicamente la disponibilidad de nodos
         *
         * @return
         */
        public boolean validate() {
            return true;
        }

        public T getId() {
            return id;
        }

        public void setId(T id) {
            this.id = id;
        }

        public Map<Node<T>, Integer> getChilds() {
            return childs;
        }

        protected void setChilds(Map<Node<T>, Integer> childs) {
            this.childs = childs;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.id).append("[");
            for (Iterator<Entry<Node<T>, Integer>> it = childs.entrySet().iterator(); it.hasNext();) {
                Entry<Node<T>, Integer> next = it.next();
                sb.append(next.getKey().getId()).append("=").append(next.getValue());
                if (it.hasNext()) {
                    sb.append(",");
                }
            }
            sb.append("]");
            return sb.toString();
        }

    }

    /**
     * Obtenga el nodo gráfico no dirigido del mapa
     *
     * @return node Id-node
     */
    public Map<T, Node<T>> getNodes() {
        return nodes;
    }

}
