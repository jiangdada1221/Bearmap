package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;
import bearmaps.proj2c.utils.Trie;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    List<Node> nodes;
    HashMap<Point,Node> hashmap;
    KDTree kd;
    List<Point> points;

    // add
    Trie trie;
    HashMap<String,Node> stored;
    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
         this.nodes = this.getNodes();
         hashmap = new HashMap<>();
         points = new ArrayList<>();
        stored = new HashMap<>();
         //
        trie = new Trie();
         for (Node n:nodes) {
             if (n.name()!= null && n.name().length()>=1){
                 String input = cleanString(n.name());
                 trie.insert(input);
                 stored.put(input,n);
             }
             if (neighbors(n.id()).isEmpty()) {
                 continue;
             }
             Point input = new Point(n.lon(),n.lat());
            hashmap.put(input,n);
            points.add(input);
         }
         kd = new KDTree(points);


    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        Point close = kd.nearest(lon,lat);
        Node result = hashmap.get(close);
        return result.id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) { // i store the clean type inputs
        List<String> res = new ArrayList<>();
        List<String> inputs = trie.findPrefix(prefix);
        for (String s: inputs) {
            res.add(stored.get(s).name());
        }
        return res;
    }
    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        locationName = cleanString(locationName);
        List<Map<String,Object>> res = new ArrayList<>();
        for (String s : stored.keySet()) {
            if (s.equals(locationName)){
                Map<String, Object> map = new HashMap<>();
                Node n = stored.get(s);
                map.put("lat",n.lat());
                map.put("lon",n.lon());
                map.put("name",n.name());
                map.put("id",n.id());
                res.add(map);
            }
        }
        return res;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    public static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }


}
