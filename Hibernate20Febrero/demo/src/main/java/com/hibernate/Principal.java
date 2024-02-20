package com.hibernate;

public class Principal 
{
    public static void main( String[] args )
    {
        Controlador cc = new Controlador(); //Me creo el controlador
        //Meto el primer usuario
        Usuario user1 = new Usuario("nombre1", "apellido1", 23);
        Usuario user2 = new Usuario("nombre2", "apellido2", 23);
        Usuario user3 = new Usuario("nombre3", "apellido3", 23);
        Usuario user4 = new Usuario("nombre4", "apellido4", 23);

        cc.meterUsuario(user1);
        cc.meterUsuario(user2);
        cc.meterUsuario(user3);
        cc.meterUsuario(user4);
        cc.imprimeTodos();
        cc.imprimeUno(3);
        cc.eliminaUno(4);
        cc.imprimeTodos();
        //Editamos un usuario
        cc.editaUsuario(2, "nombre", "apellido", 45);
        cc.imprimeTodos();
    }
}
