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
        this.isMin = isMin;
        this.data = new ArrayList<>();
    }

    // O(n)
    public Heap(Collection<ValueType> data) {
        this(true, data);
    }

    // O(n): construction avec donnees initiales, allez voir le lien dans la description pour vous aider
    public Heap(boolean isMin, Collection<ValueType> data) {
        this.isMin = isMin;
        this.data = new ArrayList<>();

        this.data.addAll(data);
        build();
    }

    // O(1): on retourne le nombre d'elements dans la liste
    public int size() {
        return data.size();
    }

    // O(1): on compare deux elements en fonction du type de monceau
    private boolean compare(ValueType first, ValueType second) {
        if(isMin){
            return first.compareTo(second) < 0;
        }else{
            return second.compareTo(first) < 0;
        }
    }

    // O(1): on donne l'indice du parent
    private int parentIdx(int idx) {
        return (idx - 1)/2;
    }

    // O(1): on donne l'indice de l'enfant de gauche
    private int leftChildIdx(int idx) {
        return (2 * idx) + 1;
    }

    // O(1): on donne l'indice de l'enfant de droite
    private int rightChildIdx(int idx) {
        return (2 * idx) + 2;
    }

    private boolean hasIndex(int idx){
        return idx < size();
    }

    // O(1): on echange deux elements dans le tableau
    private void swap(int firstIdx, int secondIdx) {
        ValueType temp = data.get(firstIdx);
        data.set(firstIdx, data.get(secondIdx));
        data.set(secondIdx, temp);
    }

    // O(log(n)): l'index courant represente le parent, on s'assure que le parent soit le min/max avec ses enfants
    // De facon visuelle, ceci ammene un noeud le plus haut possible dans l'arbre
    // Par exemple: si le min/max est une feuille, on appelera resursivement log(n) fois la methode pour monter le noeud
    private void heapify(int idx) {
        int pI = parentIdx(idx);
        ValueType pV = data.get(pI);

        int lcI = leftChildIdx(idx);
        int rcI = rightChildIdx(idx);

        if(hasIndex(lcI)){
            ValueType lcV = data.get(lcI);
            ValueType rcV = null;
            int newCI;
            if(hasIndex(rcI)){
                rcV = data.get(rcI);
            }

            do{
                newCI = lcI;
                if(hasIndex(rcI) && compare(rcV, lcV)){
                    newCI =  rcI;
                }

                if(compare(data.get(newCI), data.get(idx))){
                    swap(idx, newCI);
                }else
                    break;

                idx = newCI;
                lcI = leftChildIdx(idx);
                rcI = rightChildIdx(idx);
            }while(hasIndex(lcI));
        }
        else if(compare(data.get(idx), pV)){
            while(idx!=0 && compare(data.get(idx), pV)){
                swap(idx, pI);

                idx = pI;
                pI = parentIdx(idx);
                pV = data.get(parentIdx(idx));
            }
        }
    }

    // O(log(n)): on ajoute un element et on preserve les proprietes du monceau
    public void insert(ValueType element) {
        if (data == null) {
            this.data = new ArrayList<>();//VOIR AVEC CONSTRUCTEUR
        }
        this.data.add(element);
        heapify(size() - 1);
    }

    // O(n): on s'assure que tous les elements sont bien places dans le tableau,
    // allez voir le lien dans la description pour vous aider
    public void build() {
        for (int i = size()/2 - 1; 0 <= i; i--) {
            heapify(i);
        }
    }

    // O(log(n)): on retire le min ou le max et on preserve les proprietes du monceau
    public ValueType pop() {
        if(size()==1){
            return data.remove(0);
        }

        ValueType pop = data.get(0);

        data.set(0, data.remove(this.size() - 1));
        this.heapify(0);

        return pop;
    }

    // O(1): on retourne sans retirer le plus petit ou plus grand element.
    public ValueType peek() {
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
        Iterator<ValueType> value = data.iterator();
        return value;
    }

    //POUR DÉBUGGAGE
    public void print() {
        for (ValueType value:data) {
            System.out.print(value);
        }
        System.out.println();
    }
}
