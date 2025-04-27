
public class Corredores {

    public int getClosestPosition(int key, int[] tiempos){
        int l = 0, r = tiempos.length;
        while(l < r){
            int m = (r + l)/ 2;
            if(tiempos[m] >= key){
                r = m;
            }
            else{
                l = m + 1;
            }
        }
        return l;
    }

    public int[] tiemposEntre(int[] tiempos, Pedido[] p){ // largos n y m
        int[] respuestas = new int[p.length];
        for(int i = 0; i < p.length; i++){ // entra m veces
            int s = getClosestPosition(p[i].desde, tiempos); // O(log n)
            if (s >= tiempos.length || tiempos[s] > p[i].hasta) {
                respuestas[i] = 0;
                continue;
            }

            // Find index of first element > hasta
            int e = getClosestPosition(p[i].hasta + 1, tiempos);

            respuestas[i] = e - s;
        }
        return respuestas;
    }
}

