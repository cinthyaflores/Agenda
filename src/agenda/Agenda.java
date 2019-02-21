/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author cinthya flores
 */
public class Agenda {

    public static void menu(ArrayList contactos) {
        int resp = JOptionPane.showOptionDialog(null, "Agenda 2019\nQué desea hacer?", "Inicio",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Salir", "Eliminar Contacto", "Editar Contacto", "Ver Contactos", "Agregar Contacto"}, null);
        switch (resp) {
            case 4: // AGREGAR
                agregar(contactos);
                break;
            case 3: // VER
                if (contactos.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "Lo sentimos! No hay contactos registrados");
                    menu(contactos);
                } else {
                    ver(contactos);
                }
                break;
            case 2: // EDITAR
                if (contactos.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "Lo sentimos! No hay contactos registrados");
                    menu(contactos);
                } else {
                    editar(contactos);
                }
                break;
            case 1: // ELIMINAR
                if (contactos.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "Lo sentimos! No hay contactos registrados");
                    menu(contactos);
                } else {
                    eliminar(contactos);
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Hasta luego!");
                break;
        }
    }

    public static void agregar(ArrayList contactos) {
        String nombre = JOptionPane.showInputDialog(null, "Ingresa el nombre: ");
        boolean numerico;
        String numero;
        do{
            numero = JOptionPane.showInputDialog(null, "Ingresa el número telefónico: ");
            numerico = numerico(numero);
        }while (numerico==false);
        String correoElectronico = JOptionPane.showInputDialog(null, "Ingresa el correo electronico: ");
        System.out.println(nombre);
        System.out.println(numero);
        System.out.println(correoElectronico);
        Contacto c = new Contacto(nombre, numero, correoElectronico);
        contactos.add(c);
        menu(contactos);

    }
    
    public static boolean numerico(String cadena){
        boolean b;
        try {
		Integer.parseInt(cadena);
		b= true;
                return b;
	} catch (NumberFormatException nfe){
		b= false;
                return b;
	}
    }
    

    public static void eliminar(ArrayList contactos) {
        int elim = JOptionPane.showOptionDialog(null, "Seleccione cómo desea a buscar el contacto a Eliminar", "Inicio",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Cancelar", "Por Correo Electrónico", "Por Teléfono", "Por Nombre"}, null);
        switch (elim) {
            case 3:
                String nombre = JOptionPane.showInputDialog("Introduzca el nombre del contacto a eliminar:");
                int casilla = buscarNombre(contactos, nombre);
                if (casilla != -1) {
                    contactos.remove(casilla);
                    JOptionPane.showMessageDialog(null, "El contacto " + nombre.toUpperCase() + " fue eliminado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(null, "El contacto " + nombre.toUpperCase() + " no fue encontrado");
                }
                break;
            case 2:
                String telefono = JOptionPane.showInputDialog("Introduzca el Teléfono del contacto a eliminar:");
                int casilla2 = buscarTelefono(contactos, telefono);
                if (casilla2 != -1) {
                    contactos.remove(casilla2);
                    JOptionPane.showMessageDialog(null, "El contacto con el No. Telefónico: " + telefono + " fue eliminado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(null, "El contacto con el No. Telefónico: " + telefono + " no fue encontrado");
                }
                break;
            case 1:
                String email = JOptionPane.showInputDialog("Introduzca el Correo Electrónico del contacto a eliminar:");
                int casilla3 = buscarEmail(contactos, email);
                if (casilla3 != -1) {
                    contactos.remove(casilla3);
                    JOptionPane.showMessageDialog(null, "El contacto con el Correo Electrónico: " + email + " fue eliminado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(null, "El contacto con el Correo Electrónico: " + email + " no fue encontrado");
                }
                break;
            default:
                break;


        }
        menu(contactos);
    }

    public static void editar(ArrayList contactos) {
        Contacto c = null;
        int casilla = 0, dato = 0;
        int editar = JOptionPane.showOptionDialog(null, "Seleccione cómo desea buscar a su contacto a Editar", "Inicio",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Cancelar", "Por Correo Electrónico", "Por Teléfono", "Por Nombre"}, null);
        switch (editar) {
            case 3:
                String nombre = JOptionPane.showInputDialog("Introduzca el Nombre del contacto a editar:");
                casilla = buscarNombre(contactos, nombre);
                break;
            case 2:
                String telefono = JOptionPane.showInputDialog("Introduzca el Número telefónico del contacto a editar:");
                casilla = buscarTelefono(contactos, telefono);
                break;
            case 1:
                String email = JOptionPane.showInputDialog("Introduzca el Correo electrónico del contacto a editar:");
                casilla = buscarEmail(contactos, email);
                break;
            default:
                menu(contactos);
                break;
        }
        if (casilla != -1) {
            c = (Contacto) contactos.get(casilla);
            dato = datoEditar(casilla, contactos);
            if (dato == 1) {
                String email = JOptionPane.showInputDialog("Introduce el nuevo Correo Electrónico: ");
                c.setEmail(email);
            } else if (dato == 2) {
                String tel = JOptionPane.showInputDialog("Introduce el nuevo número telefónico: ");
                c.setNúmero(tel);
            } else if (dato == 3) {
                String nom = JOptionPane.showInputDialog("Introduce el nuevo nombre del contacto: ");
                c.setNombre(nom);
            } 
            JOptionPane.showMessageDialog(null, "El dato fue editado exitósamente");
        } else {
            JOptionPane.showMessageDialog(null, "El contacto No fue encontrado");
            menu(contactos);
        }
        menu(contactos);
    }

    public static int datoEditar(int casilla, ArrayList contactos) {
        Contacto c = (Contacto) contactos.get(casilla);
        int editar = JOptionPane.showOptionDialog(null, "Datos del contacto: \n"
                + "Nombre: " + c.nombre + "\nTeléfono: " + c.número
                + "\nCorreo Electrónico: " + c.email + "\n¿Qué dato desea editar?", "Inicio",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Cancelar","Correo Electrónico", "Teléfono", "Nombre"}, null);
        return editar; // 1 = EMAIL, 2 = TELEFONO Y 3 = NOMBRE

    }

    public static void ver(ArrayList contactos) {
        Contacto c;
        String datos = "Lista de contactos registrados: \nNOMBRE  -  TELÉFONO  -  CORREO ELECTRÓNICO\n";
        for (int i = 0; i < contactos.size(); i++) {
            c = (Contacto) contactos.get(i);
            datos=datos+c.nombre+"   -   "+c.número+"   -   "+c.email+"\n";
        }
        JOptionPane.showMessageDialog(null, datos);
        menu(contactos);
    }

    public static int buscarNombre(ArrayList contactos, String nombre) {
        int casilla = -1;
        for (int i = 0; i < contactos.size(); i++) {
            Contacto cont = (Contacto) contactos.get(i);
            System.out.println(cont.nombre);
            if (cont.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
                casilla = i;
                break;
            }
        }
        return casilla;
    }

    public static int buscarTelefono(ArrayList contactos, String telefono) {
        int casilla = -1;
        for (int i = 0; i < contactos.size(); i++) {
            Contacto cont = (Contacto) contactos.get(i);
            System.out.println(cont.número);
            if (cont.número.equals(telefono)) {
                casilla = i;
                break;
            }
        }
        return casilla;
    }

    public static int buscarEmail(ArrayList contactos, String email) {
        int casilla = -1;
        for (int i = 0; i < contactos.size(); i++) {
            Contacto cont = (Contacto) contactos.get(i);
            System.out.println(cont.email);
            if (cont.email.equals(email)) {
                casilla = i;
                break;
            }
        }
        return casilla;
    }

    public static void main(String[] args) {
        ArrayList contactos = new ArrayList();
        menu(contactos);
    }

}
