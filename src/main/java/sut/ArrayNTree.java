package sut;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author Joao Pedro Neto
 *
 * @param <T> The element's type that the tree contains 
 */
public class ArrayNTree<T extends Comparable<T>> implements NTree<T> {

	private int     capacity;        	// maximum number of children per tree node
	private boolean empty;              // is the tree empty?
	private T       data;               // the root's data
	private int     nChildren;          // number of successors
	private ArrayNTree<T>[] children;
	
	/**
	 * Creates an empty tree 
	 * @param capacity The capacity of each node, ie, the maximum number of direct successors
	 */
	@SuppressWarnings("unchecked")
	public ArrayNTree(int capacity) {
		this.empty     = true;
		this.children  = (ArrayNTree<T>[])Array.newInstance(ArrayNTree.class, capacity);
		this.nChildren = 0;
		this.capacity  = capacity;
	}

	/**
	 * Create a tree with one element
	 * @param elem     The element value
	 * @param capacity The capacity of each node, ie, the maximum number of direct successors
	 */
	public ArrayNTree(T elem, int capacity) {
		this(capacity);
		this.empty     = false;
		this.data      = elem;
	}
	
	/**
	 * Creates a tree with the elements inside the given list
	 * @param elem     The list with all the elements to insert
	 * @param capacity The capacity of each node, ie, the maximum number of direct successors
	 */
	public ArrayNTree(List<T> list, int capacity) {
		this(capacity);
		for(T elem : list)
			insert(elem);
	}
		
	/////////////////////////////////////

	public boolean isEmpty() {
		return empty;
	}

    /////////////////////////////////////

	public boolean isLeaf() {
		return !empty && nChildren == 0;
	}

    /////////////////////////////////////

	public int size() {
		int sum=0;
		for(NTree<T> brt : children)
			if (brt!=null)
			    sum += brt.size();
		return 1+sum;
	}

    /////////////////////////////////////

	public int countLeaves() {
		if (isLeaf())
			return 1;
		
		int sum=0;
		for(NTree<T> brt : children)
			if (brt!=null)
				sum += brt.countLeaves();
		return sum;
	}

    /////////////////////////////////////

	public int height() {
		if (isEmpty())
			return 0;
		
		int maxHeight=0;
		for(NTree<T> brt : children) {
			if (brt==null)
				continue;
			int currentHeight = brt.height();
			if (currentHeight>maxHeight)
				maxHeight = currentHeight;
		}
		return 1+maxHeight;
	}

    /////////////////////////////////////
	
	public T min() {
		return data;
	}
	
    /////////////////////////////////////
	
	public T max() {
		if (isLeaf())
			return data;
		
		return children[nChildren-1].max();
	}

    /////////////////////////////////////

	public boolean contains(T elem) {

		if (isEmpty()){// L1 B1
			//System.out.println("Entrei aqui B1");
			return false; // L2
		}


		if (data.compareTo(elem) == 0) { // elem == root // L3 B2
			//System.out.println("Entrei aqui B2");
			return true; // L4
		}

		// if there are no elements left, or the smallest child is > elem,
		// then the tree does not contain elem
		if (isLeaf() || elem.compareTo(children[0].data) < 0){ //L5 B3
			//System.out.println("Entrei aqui B3");
			return false; //L6
		}
		int position = proposePosition(elem); // L7
		
		if(position==nChildren) {  // elem>all children: need to look at last child // L18 B8
			//System.out.println("Entrei aqui B8"); //L19
			position = nChildren - 1; //L20
		}

		//L21
		//System.out.println("L21");
		return children[position].data.compareTo(elem)==0 || children[position].contains(elem);
	}



    /////////////////////////////////////

	public void insert(T elem) {
		
		if(isEmpty()) {
			data=elem;
			empty=false;
			return;
		}
		
		if (contains(elem)) // will not insert repetitions
			return;
		
		// if elem<data, elem should be at root, and we re-insert data
		if (data.compareTo(elem)>0) {
			T tmp = data; data = elem; elem = tmp; // swap values
		}
		
		if(isLeaf()) {
			insertAt(elem, 0);	
			return;
		}
		
		int position = proposePosition(elem);
		
		if (position==-1) {
			// element 'elem' is smaller than all children
			// then we place it at index 0, and insert the previous children[0] below 'elem'
			T previousValue = children[0].data;
			children[0].data = elem;
			this.insert(previousValue);
		} 
		
		else if (nChildren<capacity && children[position] == null) {
			// there's space available, and elem > all children
			if (elem.compareTo(children[position-1].max())>0)
				// if elem is also larger than all children of the last child, place it here 
				insertAt(elem, position);
			else
				// otherwise, place it below last child
				children[position-1].insert(elem);
		}
		
		else if (nChildren<capacity && elem.compareTo(children[position].max())>0) {
			// element can be placed after an existing node N (there's space and it's larger
			// than all children of N) but we must shift all those on the right
			insertAt(elem, position+1);
		}

		else if (nChildren==capacity || elem.compareTo(children[position].max())<0) {
			// if the node's capacity is full, and elem is larger than all children
			// place it below the last child
			if (position==capacity)
				children[position-1].insert(elem);
			else
				// otherwise, it must go under the proposed child's position
				children[position].insert(elem);
		}
	}

    /////////////////////////////////////
	
	public void delete(T elem) {
		
		// the minimum value is at the root, if something smaller
		// appears, the tree does not contain it
		if(isEmpty() || data.compareTo(elem)>0)
			return;
		
		if(isLeaf()) {
			empty = data.compareTo(elem)==0;
			return;
		}
		
		// is elem in the root?
		if (data.compareTo(elem)==0) {
			// we need to replace it with the lowest child (children[0])
			// and repeat it (to avoid duplicates) until we reach a leaf
			data = children[0].data;
			children[0].delete(data);
		} else {
			int position = proposePosition(elem);
			// if elem < all children, the element does not exist in the tree
			if (position<0)
				return;
			// if elem>all children: need to look below last child
			if(position==nChildren)  
				position--;
			children[position].delete(elem);			
		}
		// if we are at the tree's bottom, the last deletion
		// will produce an empty tree, so we might need to compact the array
		// to eliminate these empty nodes
		compact(children);		
	}

	// pre: nChildren>0
	// propose a position to insert/search element
	// returns the index of the largest of all the values < elem
	// if the element is the smallest, it returns -1
	private int proposePosition(T elem) {
		int index = 0; //L8
		for(int i=0; i<capacity; i++) { //L9 B4
			//System.out.println("Entrei aqui B4");
			if (children[i] == null || children[i].data.compareTo(elem)==0) { //L10 B5
				// found an empty slot or the element, return current index
				//System.out.println("Entrei aqui B5");
				break; //L11
			}
			if (children[i].data.compareTo(elem)>0) { // L12 B6
				// element should not be place here or ahead, go back one position, and end search
				index--; // L13
				//System.out.println("Entrei aqui B6");
				break; // L14
			}
			if (children[i].data.compareTo(elem)<0){  // L15 B7
				// this child is still smaller, check next one
				index++; // L16
				//System.out.println("Entrei aqui B7");
			}

		}
		//System.out.println("L17");
		return index; // L17
	}

    /////////////////////////////////////

	// find an eventual single empty tree, and shift all next elements to the left
	private void compact(ArrayNTree<T>[] children) {
		for(int i=0; i<nChildren; i++)
			if (children[i].isEmpty()) {
				for(int j=i+1; j<nChildren; j++)
					children[j-1] = children[j];
				children[nChildren-1] = null;
				nChildren--;
				break;
			}
	}

	// shift all indexes i>position one index to the right
	// and insert element at index position
	private void insertAt(T elem, int position) {
		for(int i=nChildren-1; i>=position; i--)
			children[i+1] = children[i];
		children[position] = new ArrayNTree<>(elem, capacity);
		nChildren++;
	}


	/////////////////////////////////////

	
	/**
	 * Is this tree equal to another object?
	 * Two NTrees are equal iff they have the same values
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object other) {
		return this == other || /* L1, B1 */
				other instanceof NTree && equalTrees(this, ((NTree<T>) other)); /* L2, B2*/
	}

	// compares the elements between to NTrees
	private boolean equalTrees(NTree<T> one, NTree<T> other) {

		if (one == other) /* L3, B3 */
			return true;  /* L4 */


		if(one.isEmpty() && other.isEmpty()){
			return true;
		} //POSSIVEL MELHORIA


		if (one != null && other != null && !other.isEmpty() && !one.isEmpty()) { /* L5, B4 */
			Iterator<T> it1 = one.iterator(); /* L6 */
			Iterator<T> it2 = other.iterator(); /* L7 */
			
			while(it1.hasNext() && it2.hasNext()) /* L8, B5 */

				if(!it1.next().equals(it2.next())){ /* L9, B6*/
					System.out.println("ENTREI AQUI");
					return false;} /* L10 */

			if(!it1.hasNext() && !it2.hasNext()) /* L11, B7*/
				return true; /* L12 */
		}
		System.out.println("Cheguei aqui");
		return false; /* L13 */
	}
	
	/////////////////////////////////////
	
	public List<T> toList() {
		List<T> list = new LinkedList<>();
		for(T elem : this)
			list.add(elem);
        return list;  // already sorted		
	}
	
	/////////////////////////////////////
	
	/**
	 * @returns a new tree with the same elements of this
	 */
	public ArrayNTree<T> clone() {
		List<T> list = toList();
		Collections.shuffle(list); // probably produces a more balanced tree	
		return new ArrayNTree<T>(list, capacity);
	}

	/////////////////////////////////////

	public String toString() {
		if (isEmpty())
			return "[]";

		if (isLeaf())
			return "["+data+"]";
		
		StringBuilder sb = new StringBuilder();
		sb.append("["+data+":");
		
		for(NTree<T> brt : children)
			if (brt!=null)
				sb.append(brt.toString());
		
		return sb.append("]").toString();				
	}
	
	// more detailed information about tree structure 
	public String info() {
		return this + ", size: " + size() + ", height: " + height() + ", nLeaves: " + countLeaves();
	}

	/////////////////////////////////////

	@Override
	public Iterator<T> iterator() {
		return new PrefixIterator(this);
	}
	
	private class PrefixIterator implements Iterator<T> {
		
		/**
		 * Stack of nodes whose descendants are currently being visited
		 */
		private LinkedList<ArrayNTree<T>> stack;
	
		/**
		 * Constructor
		 */
		public PrefixIterator(ArrayNTree<T> tree) {
			stack = new LinkedList<>();
			stack.push(tree);
		}
	
		/**
		 * Verifies if iterator still has elements
		 * @return true if the iterator still has elements, false otherwise
		 */
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		/**
		 * Returns the next element
		 * @return - next element
		 * @Throws NoSuchElementException if !hasNext()
		 */
		public T next() {
			if (stack.isEmpty()) 
				throw new NoSuchElementException();

			ArrayNTree<T> tree = stack.peek();
			stack.pop();
			for(int i=tree.nChildren-1; i>=0; i--)
				stack.push(tree.children[i]);
			
			return tree.data;
		}
	}
}
