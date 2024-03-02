package com.examen;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        Controlador cc = new Controlador(); //Me creo el controlador
        
        //Creo valoracionRevista
        valoracionRevistas val1 = new valoracionRevistas("revista1", 10);
        valoracionRevistas val2 = new valoracionRevistas("revista2", 10);
        valoracionRevistas val3 = new valoracionRevistas("revista3", 10);
        valoracionRevistas val4 = new valoracionRevistas("revista4", 10);
        valoracionRevistas val5 = new valoracionRevistas("revista5", 10);

        //Creo equipos
        equipos equipo1 = new equipos("equipo1", "Cuenca", "1", "primera");
        equipos equipo2 = new equipos("equipo2", "Cuenca", "2", "primera");
        equipos equipo3 = new equipos("equipo3", "Cuenca", "3", "primera");
        equipos equipo4 = new equipos("equipo4", "Cuenca", "4", "primera");

        //Creo partidos
        partidos partido1 = new partidos(equipo3, equipo4, 12, 34, "2023-2024");
        partidos partido2 = new partidos(equipo1, equipo2, 162, 384, "2023-2024");
        partidos partido3 = new partidos(equipo2, equipo3, 124, 334, "2023-2024");
        partidos partido4 = new partidos(equipo1, equipo4, 122, 134, "2023-2024");
        partidos partido5 = new partidos(equipo1, equipo3, 122, 134, "2023-2024");
        partidos partido6 = new partidos(equipo4, equipo2, 122, 134, "2023-2024");
        List<partidos> partList = new ArrayList<>();
        partList.add(partido2);
        partList.add(partido4);
        partList.add(partido5);
        List<partidos> partList2 = new ArrayList<>();
        partList2.add(partido2);
        partList2.add(partido3);
        partList2.add(partido6);
        List<partidos> partList3 = new ArrayList<>();
        partList3.add(partido1);
        partList3.add(partido3);
        partList3.add(partido5);
        List<partidos> partList4 = new ArrayList<>();
        partList4.add(partido1);
        partList4.add(partido4);
        partList4.add(partido6);

        //Creo patrocinadores
        patrocinador patroc1 = new patrocinador("Nike");
        patrocinador patroc2 = new patrocinador("Nike");
        patrocinador patroc3 = new patrocinador("Nike");
        patrocinador patroc4 = new patrocinador("Nike");
        patrocinador patroc5 = new patrocinador("Nike");

        //Creo jugadores
        jugadores jug1 = new jugadores("jugador1", "procedencia1", "198", 102, "alero", equipo1);
        jugadores jug2 = new jugadores("jugador2", "procedencia2", "200", 110, "pivote", equipo2);
        jugadores jug3 = new jugadores("jugador3", "procedencia3", "201", 100, "base", equipo3);
        jugadores jug4 = new jugadores("jugador4", "procedencia4", "215", 105, "alero", equipo4);
        jugadores jug5 = new jugadores("jugador5", "procedencia5", "210", 108, "alero", equipo4);
        /*jug1.setPartidos(partList2);
        jug2.setPartidos(partList2);
        jug3.setPartidos(partList3);
        jug4.setPartidos(partList4);
        jug5.setPartidos(partList4);*/
        List<jugadores> jugList1 = new ArrayList<jugadores>();
        jugList1.add(jug1);
        List<jugadores> jugList2 = new ArrayList<jugadores>();
        jugList2.add(jug2);
        List<jugadores> jugList3 = new ArrayList<jugadores>();
        jugList3.add(jug3);
        List<jugadores> jugList4 = new ArrayList<jugadores>();
        jugList4.add(jug4);
        jugList4.add(jug5);
        jug1.setValoracionRevistas(val1);
        jug2.setValoracionRevistas(val2);
        jug3.setValoracionRevistas(val3);
        jug4.setValoracionRevistas(val4);
        jug5.setValoracionRevistas(val5);
        jug1.getPatrocinadores().add(patroc1);
        jug2.getPatrocinadores().add(patroc2);
        jug3.getPatrocinadores().add(patroc3);
        jug4.getPatrocinadores().add(patroc4);
        jug5.getPatrocinadores().add(patroc5);

        /*patroc1.getJugadores().add(jug1);
        patroc2.getJugadores().add(jug2);
        patroc3.getJugadores().add(jug3);
        patroc4.getJugadores().add(jug4);
        patroc5.getJugadores().add(jug5);*/

        /*partido1.setJugadores(jugList3);
        partido1.setJugadores(jugList4);
        partido2.setJugadores(jugList2);
        partido2.setJugadores(jugList1);
        partido3.setJugadores(jugList3);
        partido3.setJugadores(jugList2);
        partido4.setJugadores(jugList4);
        partido4.setJugadores(jugList1);
        partido5.setJugadores(jugList1);
        partido5.setJugadores(jugList3);
        partido6.setJugadores(jugList4);
        partido6.setJugadores(jugList2);*/
        /*partido1.getJugadores().add(jug3);
        partido1.getJugadores().add(jug4);
        partido1.getJugadores().add(jug5);
        partido2.getJugadores().add(jug2);
        partido2.getJugadores().add(jug1);
        partido3.getJugadores().add(jug3);
        partido3.getJugadores().add(jug2);
        partido4.getJugadores().add(jug4);
        partido4.getJugadores().add(jug5);
        partido4.getJugadores().add(jug1);
        partido5.getJugadores().add(jug1);
        partido5.getJugadores().add(jug3);
        partido6.getJugadores().add(jug4);
        partido6.getJugadores().add(jug5);
        partido6.getJugadores().add(jug2);*/

        equipo1.setJugadores(jugList1);
        equipo2.setJugadores(jugList2);
        equipo3.setJugadores(jugList3);
        equipo4.setJugadores(jugList4);

        //establecimiento del jugador a la valoracion
        /*val1.setJugador(jug1);
        val2.setJugador(jug2);
        val3.setJugador(jug3);
        val4.setJugador(jug4);
        val5.setJugador(jug5);*/

        cc.meterValoracionRevista(val1);
        cc.meterValoracionRevista(val2);
        cc.meterValoracionRevista(val3);
        cc.meterValoracionRevista(val4);
        cc.meterValoracionRevista(val5);
        System.out.println("equipos*******************************************************************");

        cc.meterEquipo(equipo1);
        cc.meterEquipo(equipo2);
        cc.meterEquipo(equipo3);
        cc.meterEquipo(equipo4);
        System.out.println("partidos++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        cc.meterPartido(partido1);
        cc.meterPartido(partido2);
        cc.meterPartido(partido3);
        cc.meterPartido(partido4);

        cc.meterPatrocinador(patroc1);
        cc.meterPatrocinador(patroc2);
        cc.meterPatrocinador(patroc3);
        cc.meterPatrocinador(patroc4);
        cc.meterPatrocinador(patroc5);
        System.out.println("jugadores---------------------------------------------------------------------");
        
        cc.meterJugador(jug1);
        cc.meterJugador(jug2);
        cc.meterJugador(jug3);
        cc.meterJugador(jug4);
        cc.meterJugador(jug5);

        cc.imprimeTodosEquipos();
        cc.imprimeTodosJugadores();
        cc.eliminaUno(1);
        cc.imprimeTodosJugadores();
        cc.editaJugador(2, "Pepe", "No tiene procedencia", "210", 99, "pivot");
        cc.imprimeTodosJugadores();
        cc.jugadoresAltos("210");

        Controlador.closeEntityManager();
    
    }//main


}//Main