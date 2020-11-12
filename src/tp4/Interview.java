package tp4;

import java.sql.SQLOutput;
import java.util.*;

public final class Interview {
    /**
     * @param circleSize le nombre d'amis que l'on doit inclure dans le cercle
     * @param centers    les indices des centres dans "points"
     * @param points     la liste des individus
     * @return les indices dans "points" qui creent l'intersection de deux cercles d'amis.
     * <p>
     * On veut que vous indiquiez la complexite sur chaque ligne en fonction de:
     * - circleSize -> O(a) a est pour le nombre d' "amis" communs
     * - centers -> O(c) c est pour le nombre de "centres"
     * - points -> O(n) n est simplement le nombre de d'individus dans la population
     * Vous n'avez pas besoin d'indiquer la complexite des lignes etant en O(1)
     * Lorsque vous avez des boucles, indiquez clairement la complexite de la boucle, par exemple:
     * for (Integer p1 : points) { // O(n) * O(n^2*log(n)) -> O(n^3*log(n))
     * for (Integer p2 : points) { // O(n) * O(n*log(n) -> O(n^2*log(n))
     * Collections.sort(points); // O(n*log(n)
     * }
     * }
     * Ceci est un exemple, on voit clairement que la boucle sur "p2" est en O(n) et puisque son interieur est
     * en O(n*log(n), la complexite totale sera la multiplication, O(n^2*log(n)).
     * <p>
     * O(n^2 * log(n)): ceci est la complexite en "worst case" ou 'a' et 'c' tendent vers 'n'
     * Il est peut etre possible d'avoir une meilleure complexite, soyez clair dans vos explications si vous croyez
     * avoir trouve :)
     */
    public static List<Integer> getFriendsToRemove(Integer circleSize, List<Integer> centers, List<Point> points) {
        // TODO
        ArrayList<Integer> mauvaisAmis = new ArrayList<Integer>();//liste à retourner
        ArrayList<Integer> amisConnus = new ArrayList<Integer>();//liste des amis connus une fois
        int pointsSize = points.size();

        
        //Itération sur tout les centres
        for (int idxCentre = centers.size() - 1; idxCentre >= 0; idxCentre--) {
            int valeurCentre = centers.get(idxCentre);
            Point pCentre = points.get(valeurCentre);//on trouve le point qu'est ce centre grâce à son indice


            //Permettra à la PriorityQueue un sort selon la distance au centre
            PriorityQueue<Point> pq = new PriorityQueue<Point>(pointsSize, new Comparator<Point>() {
                @Override
                public int compare(Point p1, Point p2) {
                    int distance1 = pCentre.compareTo(p1);
                    int distance2 = pCentre.compareTo(p2);
                    if (distance1 > distance2) {
                        return 1;
                    } else if (distance1 < distance2) {
                        return -1;
                    } else {
                        if (p1.getIndex() > p2.getIndex()) {
                            return 1;
                        } else if (p1.getIndex() < p2.getIndex()) {
                            return -1;
                        } else {
                            System.out.println("Erreur : On compare un élément avec lui-même");
                            return 0;
                        }
                    }
                }

            });


            //Ajoute tous les points sauf le centre dans un monceau
            for (int i = 0; i < pointsSize; i++) {//intération sur les points
                Point p = points.get(i);
                p.setIndex(i);//on profite du passage dans les points pour stocker leur indice
                
                if (p.getIndex() != valeurCentre /*&& !pq.contains(p)*/) {//2e cond necesaire
                    pq.add(p);
                }
            }


            //Retire un nombre (circleSize) d'élément du monceau, ce sont les amis du groupe
            for (int i = 0; i < circleSize; i++) {
                Point inCircle = pq.poll();
                int indiceAmi = inCircle.getIndex();
                if (amisConnus.contains(indiceAmi) && !mauvaisAmis.contains(indiceAmi)) {
                    //Il devient un mauvais amis
                    mauvaisAmis.add(indiceAmi);
                } else {
                    //Sinon il devient un ami connu
                    amisConnus.add(indiceAmi);
                }
            }
        }

        // A cette étape, on a une liste qui contient tous les mauvais amis, mais cette liste est non triée
        int tailleMauvaisAmis = mauvaisAmis.size();
        //Nous allons trier la liste à l'aide d'une priorityQueue
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        for (int i = 0; i < tailleMauvaisAmis; i++) {
            //ajouter tt les éléments à la Queue
            q.add(mauvaisAmis.get(i));
        }
        for (int i = 0; i < tailleMauvaisAmis; i++) {
            //sortir les éléments du plus petit au plus grand
            mauvaisAmis.set(i, q.poll());
        }
        return mauvaisAmis;
    }
}

