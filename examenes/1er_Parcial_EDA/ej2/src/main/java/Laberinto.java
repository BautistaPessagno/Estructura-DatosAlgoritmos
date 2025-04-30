package BPesagno;

public class Laberinto {
    public static boolean existeCamino(int[][] laberinto, int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        // Check if position is out of bounds
        if (filaInicio < 0 || filaInicio >= laberinto.length || columnaInicio < 0 || columnaInicio >= laberinto[0].length) {
            return false;
        }
        // Check if position is a wall (1) or already visited (-1)
        if (laberinto[filaInicio][columnaInicio] == 1 || laberinto[filaInicio][columnaInicio] == -1) {
            return false;
        }
        // Check if we reached the destination
        if (filaInicio == filaFin && columnaInicio == columnaFin) {
            return true;
        }

        // Mark current position as visited
        laberinto[filaInicio][columnaInicio] = -1;

        if(existeCamino(laberinto, filaInicio + 1, columnaInicio, filaFin, columnaFin) || existeCamino(laberinto, filaInicio - 1, columnaInicio, filaFin, columnaFin) ||
                existeCamino(laberinto, filaInicio, columnaInicio + 1, filaFin, columnaFin) ||
                existeCamino(laberinto, filaInicio, columnaInicio - 1, filaFin, columnaFin)) {
            return true;
        }
        else return false;

    }
    public static void main(String[] args) {
        int[][] laberinto = {
                {0, 0, 1, 0},
                {1, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 1}
        };

        boolean existe = existeCamino(laberinto, 0, 0, 3, 0);
        if (existe) {
            System.out.println("Existe un camino en el laberinto.");
        } else {
            System.out.println("No existe un camino en el laberinto.");
        }
        System.out.println("Caminos recorridos:");
        imprimirLaberinto(laberinto);

        int[][] laberintoSinSalida = {
                {0, 0, 1, 0},
                {1, 0, 1, 1},
                {0, 0, 0, 0},
                {0, 1, 1, 1}
        };
        boolean existeSinSalida = existeCamino(laberintoSinSalida, 0, 0, 0, 3);
        if (existeSinSalida) {
            System.out.println("Existe un camino en el laberinto con salida (¡error!).");
        } else {
            System.out.println("No existe un camino en el laberinto sin salida.");
        }
        System.out.println("Caminos recorridos:");
        imprimirLaberinto(laberintoSinSalida);

        int[][] laberintoConSalida = {
                {0, 0, 1, 0}, //0
                {1, 0, 1, 1},//1
                {0, 0, 1, 0},//2
                {0, 1, 0, 0},
                {0, 0, 0, 1}
        };
        boolean existeConSalida = existeCamino(laberintoConSalida, 0, 0, 2, 3);
        if (existeConSalida) {
            System.out.println("Existe un camino en el laberinto con salida ");
        } else {
            System.out.println("No existe un camino en el laberinto con salida.(¡error!).");
        }
        System.out.println("Caminos recorridos:");
        imprimirLaberinto(laberintoConSalida);

    }

    public static void imprimirLaberinto(int[][] laberinto) {
        for (int[] fila : laberinto) {
            for (int celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }
}
