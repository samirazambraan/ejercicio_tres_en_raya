import java.util.ArrayList;
import java.util.Scanner;

public class TresEnRaya {
    private static Scanner entrada = new Scanner(System.in);

    private static enum Jugador {
        player1, player2
    };
    private static Jugador player;
    private static ArrayList<ArrayList<String>> tablero = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Designando jugador");
        GenerarJugadorAleatorio();
        generarTablero();
        System.out.println("Se trabajara con X para el player1 y O para el player2");
        
        while (AgregarSigno() && verificarVacios()){
            CambiarTurnoDe();
        }
    }

    private static boolean AgregarSigno() {
        String signo;
        signo = (player == Jugador.player1) ? "X" : "O";
        System.out.println("Turno de " + player + " con signo " + signo);
        while (agregarSigno(signo));
        return !ganoPlayer();
    }

    private static void generarTablero() {
        for (int i = 0; i < 3; i++) {
            ArrayList<String> fila = new ArrayList<>();
            tablero.add(fila);
            for (int j = 0; j < 3; j++) {
                fila.add("*");
                System.out.print("*");
            }
            System.out.println();
        }
    }

    private static void CambiarTurnoDe() {
        switch (player) {
            case player1:
                player = Jugador.player2;
                break;
            case player2:
                player = Jugador.player1;
                break;
        }
    }

    private static void GenerarJugadorAleatorio() {
        player = ((int) (Math.random() * 2) == 0) ? Jugador.player1 : Jugador.player2;
    }

    private static boolean agregarSigno(String signo) {
        int fila;
        int columna;
        boolean vacio;
        System.out.print("Ingrese la fila(1-3):");
        fila = Integer.parseInt(entrada.nextLine());
        System.out.print("Ingrese la columna(1-3):");
        columna = Integer.parseInt(entrada.nextLine());
        fila = fila - 1;
        columna = columna - 1;
        if (fila >= 3 || columna >= 3) {
            System.out.println("Error por favor ingresa una fila o columna que este dentro del tablero");
            vacio = true;
        } else {
            vacio = !EstaVacio(fila, columna);
        }
        if (vacio) {
            System.out.println("ese campo ya esta ocupado");
        } else {
            tablero.get(fila).set(columna, signo);
            imprimirTablero();
        }
        return vacio;
    }

    private static void imprimirTablero() {
        for (ArrayList<String> fila : tablero) {
            for (String dato : fila) {
                System.out.print(dato);
            }
            System.out.println();
        }
    }

    private static boolean EstaVacio(int i, int j) {
        return tablero.get(i).get(j).equals("*");
    }

    private static boolean ganoPlayer() {
        return verificarGanadorSigno((player == Jugador.player1) ? "X" : "O");
    }

    private static boolean verificarGanadorSigno(String signo) {
        return esGanandorPorDiagonal(signo) || esGanadorPorDiagonalOpuesta(signo) || esGanadorPorFila(signo) || esGanadorPorColumna(signo);

    }

    private static boolean esGanandorPorDiagonal(String signo) {
        int bandera = 0;
        for (int n = 0; n < 3; n++) {
            if (tablero.get(n).get(n).equals(signo)) {
                bandera++;
            }
        }
        return bandera >= 3;
    }

    private static boolean esGanadorPorDiagonalOpuesta(String signo) {
        int bandera = 0;
        int opuesto = 2;
        for (int n = 0; n < 3; n++) {
            if (tablero.get(n).get(opuesto).equals(signo)) {
                bandera++;
            }
            opuesto--;
        }
        return bandera >= 3;
    }

    private static boolean esGanadorPorFila(String signo) {
        int bandera = 0;
        for (int i = 0; i < 3; i++) {
            if (bandera >= 3) {
                break;
            } else {
                bandera = 0;
            }
            for (int j = 0; j < 3; j++) {
                if (tablero.get(i).get(j).equals(signo)) {
                    bandera++;
                }
            }
        }
        return bandera >= 3;
    }

    private static boolean esGanadorPorColumna(String signo) {
        int bandera = 0;
        for (int i = 0; i < 3; i++) {
            if (bandera >= 3) {
                break;
            } else {
                bandera = 0;
            }
            for (int j = 0; j < 3; j++) {
                if (tablero.get(j).get(i).equals(signo)) {
                    bandera++;
                }
            }
        }
        return bandera >= 3;
    }

    private static boolean verificarVacios() {
        int cuenta=0;
        for(ArrayList<String >fila:tablero){
            for(String dato:fila){
                if(dato.equals("*")){
                    cuenta++;
                }
            }
        }
        return cuenta>0;
    }
}