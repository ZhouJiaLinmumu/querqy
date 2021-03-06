/**
 * 
 */
package querqy.trie;


/**
 * @author René Kriegler, @renekrie
 *
 */
public class TrieMap<T> {
    
    Node<T> root;
    
    public void put(CharSequence seq, T value) {
        int length = seq.length();
        if (length == 0) {
            throw new IllegalArgumentException("Must not put empty sequence into trie");
        }
        if (root == null) {
            synchronized (this) {
                if (root == null) {
                    root = new Node<T>(seq.charAt(0));
                }
            }
        }
        
        root.put(seq, 0, value);
    }
    
    public void putPrefix(CharSequence seq, T value) {
        int length = seq.length();
        if (length == 0) {
            throw new IllegalArgumentException("Must not put empty sequence into trie");
        }
        if (root == null) {
            synchronized (this) {
                if (root == null) {
                    root = new Node<T>(seq.charAt(0));
                }
            }
        }
        
        root.putPrefix(seq, 0, value);
    }
    
    public States<T> get(CharSequence seq) {
        if (seq.length() == 0) {
            return new States<>(new State<T>(false, null, null));
        }
        return (root == null) ? new States<>(new State<T>(false, null, null)) : root.get(seq, 0);
    }
    

    public States<T> get(CharSequence seq, State<T> stateInfo) {
        if (!stateInfo.isKnown()) {
            throw new IllegalArgumentException("Known state expected");
        }
        if (seq.length() == 0) {
            return new States<>(new State<T>(false, null, null));
        }
        return stateInfo.node.getNext(seq, 0);
    }

}
