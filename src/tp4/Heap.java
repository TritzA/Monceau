package tp4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Heap<ValueType extends Comparable<? super ValueType>> implements Iterable<ValueType> {
    private ArrayList<ValueType> data;
    private boolean isMin;

    // O(1)
    public Heap() {
        this(true);
    }

    // O(1): construction sans donnees initiales
    public Heap(boolean isMin) {
        // TODO
        this.isMin = isMin;
        this.data = new ArrayList<>();
    }

    // O(n)
    public Heap(Collection<ValueType> data) {
        this(true, data);
    }

    // O(n): construction avec donnees initiales, allez voir le lien dans la description pour vous aider
    public Heap(boolean isMin, Collection<ValueType> data) {
        // TODO
        this.isMin = isMin;
        this.data = new ArrayList<>();

        this.data.addAll(data);
        build();
    }

    // O(1): on retourne le nombre d'elements dans la liste
    public int size() {
        // TODO
        return data.size();
    }

    // O(1): on compare deux elements en fonction du type de monceau
    private boolean compare(ValueType first, ValueType second) {
        // TODO
        if (isMin) {
            return first.compareTo(second) < 0;
        } else {
            return first.compareTo(second) > 0;
        }
    }

    // O(1): on donne l'indice du parent
    private int parentIdx(int idx) {
        // TODO
        return (idx - 1) / 2;
    }

    // O(1): on donne l'indice de l'enfant de gauche
    private int leftChildIdx(int idx) {
        // TODO
        return (2 * idx) + 1;
    }

    // O(1): on donne l'indice de l'enfant de droite
    private int rightChildIdx(int idx) {
        // TODO
        return (2 * idx) + 2;
    }

    private boolean hasIndex(int idx) {
        return idx < size();
    }

    // O(1): on echange deux elements dans le tableau
    private void swap(int firstIdx, int secondIdx) {
        // TODO
        ValueType temp;
        temp = data.get(firstIdx);
        data.set(firstIdx, data.get(secondIdx));
        data.set(secondIdx, temp);
    }

    // O(log(n)): l'index courant represente le parent, on s'assure que le parent soit le min/max avec ses enfants
    // De facon visuelle, ceci ammene un noeud le plus haut possible dans l'arbre
    // Par exemple: si le min/max est une feuille, on appelera resursivement log(n) fois la methode pour monter le noeud
    private void heapify(int idx) {
        int parentIdx = parentIdx(idx);
        ValueType parentValue = data.get(parentIdx);

        int leftChildIdx = leftChildIdx(idx);
        int rightChildIdx = rightChildIdx(idx);

        if (hasIndex(leftChildIdx)) {
            ValueType leftChildValue;
            ValueType rightChildValue;
            int childIdx;

            do {
                //attribution de la valeur de l'enfant de droite
                leftChildValue = data.get(leftChildIdx);
                rightChildValue = null;

                if (hasIndex(rightChildIdx)) {
                    rightChildValue = data.get(rightChildIdx);
                }

                //choix bon enfant
                childIdx = leftChildIdx;

                if (hasIndex(rightChildIdx) && compare(rightChildValue, leftChildValue)) {
                    childIdx = rightChildIdx;
                }


                //on déscent
                if (compare(data.get(childIdx), data.get(idx))) {
                    swap(idx, childIdx);
                } else {
                    break;
                }

                idx = childIdx;
                leftChildIdx = leftChildIdx(idx);
                rightChildIdx = rightChildIdx(idx);
            } while (hasIndex(leftChildIdx));

        } else if (compare(data.get(idx), parentValue)) {//on monte

            while (idx != 0 && compare(data.get(idx), data.get(parentIdx(idx)))) {
                swap(idx, parentIdx);

                idx = parentIdx;
                parentIdx = parentIdx(idx);
            }
        }
    }

    // O(log(n)): on ajoute un element et on preserve les proprietes du monceau
    public void insert(ValueType element) {
        // TODO
        if (data == null) {
            this.data = new ArrayList<>();//VOIR AVEC CONSTRUCTEUR
        }
        this.data.add(element);
        heapify(size() - 1);//SKIP UNE CONDITION ? ON SAIT QU'ON PARCOURRIRA TT
    }

    // O(n): on s'assure que tous les elements sont bien places dans le tableau,
    // allez voir le lien dans la description pour vous aider
    public void build() {
        for (int i = size() / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    // O(log(n)): on retire le min ou le max et on preserve les proprietes du monceau
    public ValueType pop() {
        // TODO
        if (size() == 1) {
            return data.remove(0);
        }

        ValueType pop = data.get(0);
        data.set(0, data.remove(this.size() - 1));
        this.heapify(0);

        return pop;
    }

    // O(1): on retourne sans retirer le plus petit ou plus grand element.
    public ValueType peek() {
        // TODO
        if (data.size() == 0) {
            return null;
        } else {
            return data.get(0);
        }
    }

    // O(nlog(n)): On applique l'algorithme Heap Sort, on s'attend a ce que le monceau soit vide a la fin.
    public List<ValueType> sort() {
        // TODO
        ArrayList<ValueType> list = new ArrayList();
        int size = size();
        for (int i = 0; i < size; i++) {
            list.add(pop());
        }
        return list;
    }

    // Creation d'un iterateur seulement utilise dans les tests, permet de faire des boucles "for-each"
    @Override
    public Iterator<ValueType> iterator() {
        // TODO
        Iterator<ValueType> it = data.iterator();
        return it;
    }
}
