# Bearmap
  Bearmap is a simplified map for Berkeley and it is capable of performing most features you would expect of a mapping 
application. 
  I implement the back end of this application, including map dragging/zooming, map rastering, autocomplete, and finding
shortest path between two locations by A* search algorithm.
<br>
__The app link is <a href="http://bearmap-jyp.herokuapp.com">http://bearmap-jyp.herokuapp.com</a>__. (Since Heroku is a free server, the app is a little bit slow right now. You will wait for some seconds before seeing the map) <br>
__If there are some images are invalid, just refresh this page__
## How to use : 

Map rastering and zooming <br>
You can scale up and scale down the map by scrolling your mouse <br>
The back end is done by <a href="https://github.com/jiangdada1221/Bearmap/blob/master/bearmaps/proj2c/server/handler/APIRouteHandler.java">RasterAPIHandler</a>
<br>
<img src="https://media.giphy.com/media/gdNtnEYJpli6GJ3kXM/giphy.gif" >

Autocomplete <br>
The back end is done by <a href="https://github.com/jiangdada1221/Bearmap/blob/master/bearmaps/proj2c/utils/Trie.java">Trie</a> <br>
<img src="https://media.giphy.com/media/mF49G0H2YzxoDUl4n6/giphy.gif" >

Shortest Path <br>
Double click on the map to select the start point and then double click on the map to select the end point. Then a blue line will appear indicating the shortest path between those two points you select.<br>
The back end is done by <a href="https://github.com/jiangdada1221/Bearmap/blob/master/bearmaps/hw4/AStarSolver.java">AstarSolver (implementation of A* search algorithm)</a>, <a href="https://github.com/jiangdada1221/Bearmap/blob/master/bearmaps/proj2ab/KDTree.java">KDTree(find the nearest neighbour)</a>, and <a href="https://github.com/jiangdada1221/Bearmap/blob/master/bearmaps/proj2ab/ArrayHeapMinPQ.java">ArrayMinHeap(A priority Queue used for A* search)</a>
<br>
<img src="https://media.giphy.com/media/J5YeArVoe51PTftMtE/giphy.gif" >
<br>

