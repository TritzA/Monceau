package tp4;

import java.util.*;

public final class Interview {
    /**
     * @param circleSize le nombre d'amis que l'on doit inclure dans le cercle
     * @param centers les indices des centres dans "points"
     * @param points la liste des individus
     * @return les indices dans "points" qui creent l'intersection de deux cercles d'amis.
     *
     * On veut que vous indiquiez la complexite sur chaque ligne en fonction de:
     *  - circleSize -> O(a) a est pour le nombre d' "amis" communs
     *  - centers -> O(c) c est pour le nombre de "centres"
     *  - points -> O(n) n est simplement le nombre de d'individus dans la population
     * Vous n'avez pas besoin d'indiquer la complexite des lignes etant en O(1)
     * Lorsque vous avez des boucles, indiquez clairement la complexite de la boucle, par exemple:
     *   for (Integer p1 : points) { // O(n) * O(n^2*log(n)) -> O(n^3*log(n))
     *     for (Integer p2 : points) { // O(n) * O(n*log(n) -> O(n^2*log(n))
     *       Collections.sort(points); // O(n*log(n)
     *     }
     *   }
     * Ceci est un exemple, on voit clairement que la boucle sur "p2" est en O(n) et puisque son interieur est
     * en O(n*log(n), la complexite totale sera la multiplication, O(n^2*log(n)).
     *
     * O(n^2 * log(n)): ceci est la complexite en "worst case" ou 'a' et 'c' tendent vers 'n'
     * Il est peut etre possible d'avoir une meilleure complexite, soyez clair dans vos explications si vous croyez
     * avoir trouve :)
     */
    public static List<Integer> getFriendsToRemove(Integer circleSize, List<Integer> centers, List<Point> points) {
        // TODO
        // ERREUR circleSize n'est pas la distance, c'est le nombre d'amis dans le cercle (sans compter le centre)
        ArrayList<Integer> mauvaisAmis = new ArrayList<Integer>();//liste
        ArrayList<Integer> amisConnus = new ArrayList<Integer>();
        //PriorityQueue
        for (Integer indiceCentre: centers) {//pour chaque personne étant le centre de son cercle d'ami
            Point pCentre = points.get(indiceCentre);//on trouve le point qu'est ce centre grâce à son indice

            for (int i = 0; i < points.size(); i++){//pour tous les points
                Point p = points.get(i);

                if (pCentre.compareTo(p) <= circleSize && !pCentre.equals(p)){//si on trouve un ami (p) dans le centre de pCentre
                    //p est un ami de pCentre
                    if(amisConnus.contains(p)){//s'il a déjà été ajouté dans les amis connus
                        //il devient un mauvais amis
                        mauvaisAmis.add(i);
                    }else{
                        //sinon il est un bon ami
                        amisConnus.add(i);
                    }
                }
            }
        }
        return mauvaisAmis;
    }
}
