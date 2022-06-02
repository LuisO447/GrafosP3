/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.graforuta;

import GrafosPkg.Grafo;//hacemos las importaciones
import java.util.Scanner;
import Dijktrapkg.Maps;
import Dijktrapkg.MapSearcher;
/**
 *
 * @author Ruldin Ayala
 */
public class inicio {
   /* 
    public static void antiguomain(){
        
        //String variable1;//eran char
        //String variable2;//guardar municipios
        String var1;
        String var2;//primero en String porque no deja guardar con scanner
        String municipiosJut[] = {"Jutiapa","Agua_blanca","Mita","Atescatempa","Comapa","Conguaco","El_adelanto","El_progreso","Jalpatagua","Jerez","Moyuta","Pasaco","Quesada","Acatempa","Catocha","Yupiltepeque","Zapotitlan"};
        Scanner teclado = new Scanner(System.in);
        System.out.println("Bienvenido desde que punto quieres viajar?");
        var1 = teclado.next();
        //variable1 = var1.charAt(0);
        System.out.println("Cual es el destino de la ruta??");
        var2 = teclado.next();//con teclado se guardan
        //variable2 = var2.charAt(0);
        
        //Grafo g = new Grafo("abcdef"); y ver captura
          Grafo g = new Grafo(municipiosJut);// se instancio la clase grafo, creo que hay que meter un array con nombres string
        g.agregarRuta("Jutiapa","El_progreso", 3); //g.agregarRuta(char'',char'',int);
        g.agregarRuta("Jutiapa","Catocha", 6);
        g.agregarRuta("Jutiapa","Mita",5);//ver cuales estan mas lejor y agregar aca los munis y sus km de distancia
        g.agregarRuta("Jutiapa","Yupiltepeque", 5); //g.agregarRuta('','',int);
        g.agregarRuta("Jutiapa","El_adelanto", 2);
        g.agregarRuta("Jutiapa","Comapa", 8); //g.agregarRuta('','',int kms);
        g.agregarRuta("Jutiapa","Jalpatagua", 10);//eran 10 interacciones y 6 letras al inicio
        g.agregarRuta("Jutiapa","Quesada", 7);//eran letras de la 'a' a la f
        g.agregarRuta("El_progreso","Catocha", 4);//agregar mas de estos de ser necesario
        g.agregarRuta("Catocha","El_progreso", 4);
        g.agregarRuta("Catocha","Agua_blanca", 5);
        g.agregarRuta("Catocha","Mita", 2);
        g.agregarRuta("Mita","Yupiltepeque", 6);
        g.agregarRuta("Mita","Agua_blanca", 3);
        g.agregarRuta("Mita","Atescatempa", 8);
        g.agregarRuta("Yupiltepeque","El_adelanto", 5);
        g.agregarRuta("Yupiltepeque","Atescatempa", 4);
        g.agregarRuta("Yupiltepeque","Jerez", 2);
        g.agregarRuta("Yupiltepeque","Zapotitlan", 6);
        g.agregarRuta("El_adelanto","Jerez", 3);
        g.agregarRuta("El_adelanto","Zapotitlan", 5);
        g.agregarRuta("El_adelanto","Comapa", 2);
        g.agregarRuta("Zapotitlan","Comapa", 1);
        g.agregarRuta("Comapa","Jalpatagua", 7);
        g.agregarRuta("Jalpatagua","Conguaco", 3);
        g.agregarRuta("Jalpatagua","Moyuta", 5);
        g.agregarRuta("Jalpatagua","Acatempa", 9);
        g.agregarRuta("Jalpatagua","Quezada", 5);//me señala acá el error
        g.agregarRuta("Quezada","Acatempa", 1);
        g.agregarRuta("Moyuta","Pasaco", 3);
        g.agregarRuta("Moyuta","Conguaco", 4);
        //ver si elimino estas lineas de abajo
        g.agregarRuta("Pasaco","Jutiapa", 9);
        g.agregarRuta("Moyuta","Jutiapa", 7);
        g.agregarRuta("Acatempa","Jutiapa", 5);
        g.agregarRuta("Conguaco","Jutiapa", 5);
        g.agregarRuta("Zapotitlan","Jutiapa", 6);
        g.agregarRuta("Jerez","Jutiapa", 7);
        g.agregarRuta("Atescatempa","Jutiapa", 5);
        g.agregarRuta("Agua_blanca","Jutiapa", 6);
        
        //char inicio = 'a';
        String inicio = var1;//eran char tambien y eran con variable1 y 2
        //char fin    = 'd';
        String fin = var2;
        String respuesta = g.encontrarRutaMinimaDijkstra(inicio, fin);//se mandan las variables a encontrar
        System.out.println("Esta es la ruta mas corta: ");
        System.out.println(respuesta);//imprime la ruta corta
        
    }
    
    
    
    
    
    public static void main(String[] args) {
        
        antiguomain();
        
    }
    */
}
