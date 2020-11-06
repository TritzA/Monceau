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
        this.isMin = isMin;//à revoir
        this.data = new ArrayList<>();
        for (ValueType value : data) {
            this.insert(value);
        }
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
            return first.compareTo(second) < 0;// à inverser?
        } else {
            return first.compareTo(second) > 0;// à inverser?
        }
    }

    // O(1): on donne l'indice du parent
    private int parentIdx(int idx) {
        // TODO
        //à revoir, parent de 0 c'est 0, à utiliser ?
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

    private int bonEnfant(int idx) {
        int nouvelIdx = leftChildIdx(idx);
        int droitIdx = rightChildIdx(idx);
        boolean hasRightChild = hasIndex(droitIdx);

        if (hasRightChild) {
            if (compare(data.get(droitIdx), data.get(nouvelIdx))) {//idx.value<parent.value
                nouvelIdx = droitIdx;
            }
        }
        return nouvelIdx;
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
        // TODO
        boolean doitMonter = false;
        if(hasIndex(parentIdx(idx))) {
            doitMonter = compare(data.get(idx), data.get(parentIdx(idx)));//idx.value<parent.value
        }
        boolean doitDescendre = false;

        if (hasIndex(leftChildIdx(idx))) {
            doitDescendre = compare(data.get(leftChildIdx(idx)), data.get(idx));//idx.valie>left.value
        }

        int parentIndexe = parentIdx(idx);
        if (doitMonter) {//si min, idx.value<parent.value
            //on doit monter
            while (doitMonter && idx != 0) {
                swap(idx, parentIdx(idx));

                idx = parentIndexe;
                parentIndexe = parentIdx(idx);

                doitMonter = compare(data.get(idx), data.get(parentIdx(idx)));
            }
        } else if (doitDescendre) {// on sait qu'on doit déscendre
            //trouver le min ou max a changer,
            int nouvelIdx = bonEnfant(idx);
            swap(nouvelIdx, idx);
            heapify(idx);
        }//gérer si égaux
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
        // TODO

    }

    // O(log(n)): on retire le min ou le max et on preserve les proprietes du monceau
    public ValueType pop() {
        // TODO
        ValueType min = data.get(0);
        if (data.size() == 1) {
            data.remove(this.size() - 1);
        } else {
            data.set(0, data.remove(this.size() - 1));
        }
        this.heapify(0);//SKIP UNE CONDITION ? ON SAIT QU'ON PARCOURRIRA TT

        return min;

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
        return null;
    }

    // Creation d'un iterateur seulement utilise dans les tests, permet de faire des boucles "for-each"
    @Override
    public Iterator<ValueType> iterator() {
        // TODO
        return null;
    }

    //POUR DÉBUGGAGE
    public void print() {
        for (ValueType value:data) {
            System.out.print(value);
        }
        System.out.println();
    }
}
