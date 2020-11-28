package tp4;

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
    // Complexite finale : O(c*(n*log(n)+(a*log(n)))) + O(n*log(n)) -> (si 'a' et 'c' tendent vers 'n') -> O(n^2*log(n))
    public static List<Integer> getFriendsToRemove(Integer circleSize, List<Integer> centers, List<Point> points) {// O(n^2*log(n)) + O(n*log(n)) -> O(n^2*log(n))
        // TODO
        int pointsSize = points.size();
        PriorityQueue<Integer> pqBadFriends = new PriorityQueue<>();
        ArrayList<Integer> badFriendsInOrder = new ArrayList<>();


        // Iteration centres
        for (int valueCenter : centers) {// O(c) * (O(n*log(n)) + O(a*log(n))) -> O(c*(n*log(n)+(a*log(n))))
            Point pointC = points.get(valueCenter);


            // Pour comparaisons queue
            PriorityQueue<Point> pqFriends = new PriorityQueue<>(pointsSize, (p1, p2) -> {
                int distance1 = pointC.compareTo(p1);
                int distance2 = pointC.compareTo(p2);
                if (distance1 > distance2) {
                    return 1;
                } else if (distance1 < distance2) {
                    return -1;
                } else {
                    return p1.getIndex().compareTo(p2.getIndex());
                }
            });


            // Ajoute points a queue
            for (int i = 0; i < pointsSize; i++) {// O(n) * O(log(n)) -> O(n*log(n))
                Point p = points.get(i);
                p.setIndex(i);
                if (i != valueCenter) {
                    pqFriends.add(p);// O(log(n))
                }
            }


            // Traite amis
            for (int i = 0; i < circleSize; i++) {// O(a) * (O(log(n)) + O(log(n)) -> O(a*log(n))
                Point friend = pqFriends.poll();// O(log(n))
                assert friend != null;
                int friendIndex = friend.getIndex();
                if (friend.isKnown() && !friend.isBadFriend()) {
                    pqBadFriends.add(friendIndex);// O(log(n))
                    friend.setBadFriend(true);
                } else {
                    friend.setKnown(true);
                }
            }
        }

        // Sort queue dans arrayList
        int pqBadFriendsSize = pqBadFriends.size();
        for (int i = 0; i < pqBadFriendsSize; i++) {// O(n) * O(log(n)) -> O(n*log(n))
            badFriendsInOrder.add(pqBadFriends.poll());// O(1) + O(log(n)) -> O(log(n))
        }
        return badFriendsInOrder;
    }
}
