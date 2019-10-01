# Bearmap
  Bearmap is a simplified map for Berkeley and it is capable of performing most features you would expect of a mapping 
application. 
  I implement the back end of this application, including map dragging/zooming, map rastering, autocomplete, and finding
shortest path between two locations by A* search algorithm.
<br>
Map rastering <br>
The back end is done by <a href="https://github.com/jiangdada1221/Bearmap/blob/master/bearmaps/proj2c/server/handler/APIRouteHandler.java">RasterAPIHandler</a>
<br>
<img src="https://media.giphy.com/media/gdNtnEYJpli6GJ3kXM/giphy.gif" >

Autocomplete <br>
The back end is done by <a href="https://github.com/jiangdada1221/Bearmap/blob/master/bearmaps/proj2c/utils/Trie.java">Trie</a> <br>
<img src="https://media.giphy.com/media/mF49G0H2YzxoDUl4n6/giphy.gif" >

Shortest Path <br>
The back end is done by <a href="https://github.com/jiangdada1221/Bearmap/blob/master/bearmaps/hw4/AStarSolver.java">AstarSolver</a>, <a href="https://github.com/jiangdada1221/Bearmap/blob/master/bearmaps/proj2ab/KDTree.java">KDTree(find the nearest neighbour)</a>, and <a href="https://github.com/jiangdada1221/Bearmap/blob/master/bearmaps/proj2ab/ArrayHeapMinPQ.java">ArrayMinHeap(A priority Queue used for A star search)</a>
<br>
<img src="https://media.giphy.com/media/J5YeArVoe51PTftMtE/giphy.gif" >
<br>
The app link is <a href="http://bearmap-jyp.herokuapp.com">
