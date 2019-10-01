package bearmaps.proj2c.utils;
import java.util.*;
/* this is for large alphabet
in this case, the alphabet is just 26
so see Trie2, just use array to store everything
 */

public class Trie {

    Node node;
    public Trie() {
        node = new Node('0',false);
    }

    public void insert(String word) {
        if (word.length() == 0) return;
        int index = 0;Node p = node;
        char c = word.charAt(index);
        while (p.next.containsKey(c)) {
            if (index == word.length()-1){
                p.next.get(c).isKey = true;
                return;
            }
            else {
                p = p.next.get(c);
                index++;
                c = word.charAt(index);
            }
        }

        insertHelper(word.substring(index),p);
    }
    private void insertHelper(String word,Node node) {
        if (word.length() == 0) return;
        if (word.length() == 1) {
            node.next.put(word.charAt(0),new Node(word.charAt(0),true));
        }
        else {
            Node newNode = new Node(word.charAt(0),false);
            node.next.put(word.charAt(0),newNode);
            insertHelper(word.substring(1),newNode);
        }
    }

    public boolean search(String word) {
        int index = 0;Node p = node;
        while (true) {

            char ch = word.charAt(index);
            if (!p.next.containsKey(ch)) return false;
            if (index == word.length()-1) {
                return p.next.get(word.charAt(index)).isKey == true;
            }
            p = p.next.get(ch);
            index++;
        }
    }

    public Node startsWith(String prefix) {
        int index = 0;Node p = node;
        while (true) {
            char ch = prefix.charAt(index);
            if (!p.next.containsKey(ch)) return null;
            p  =p.next.get(ch);
            index++;
            if (index == prefix.length()) break;
        }
        return p;
    }

    public List<String> findPrefix(String prefix) {
        List<String> res = new ArrayList<>();
        findHelper(res,startsWith(prefix),prefix);
        return res;
    }
    private void findHelper(List<String> res, Node node, String forNow) {
        if (node == null) return;
        if (node.isKey) res.add(forNow);
        for (char c : node.next.keySet()) {
            findHelper(res,node.next.get(c),forNow+c);
        }
    }

    private class Node {
        char ch;
        boolean isKey;
        Map<Character,Node> next;
        Node(char ch,boolean isKey) {
            this.ch = ch;
            this.isKey = isKey;
            next = new HashMap<>();
        }
    }


}
