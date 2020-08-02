# Bear Maps

*Disclaimer*: This guide is not comprehensive! It is not meant to list out the
solutions, but rather point out things students may struggle on and how to
address those bugs.

Most of the confusion will probably come from understanding the spec. It's a
lot to go through and understand! **KNOW THE SPEC AND THE SKELETON CODE** The
comments in the skeleton code are pretty descriptive.

## Before Starting

Have students watch Josh's Bear Maps videos. The videos include setup, how to
deploy the app, and starting tips.

Make sure they read the entire spec and the skeleton code!

## General tips for helping students

- Most of the work is done in understanding the spec. So first ask them what
  they are working on and see if their understanding of the problem/solution
aligns with what the spec says.
- Encourage them to take a look at the values already provided for them.
- For the rastering part, draw a diagram and ask them how they would calculate
  everything on paper. If they understand this, turning it into code should be
pretty straightforward.
- Ask students what they have tried already.
- Be mindful of Piazza!

## Map Rastering

The heart of rastering is completing the function `getMapRaster` in
`Rasterer.java`. A couple things that were tricky when first starting with the
project is orienting yourself with the given coordinate system.

- Longitude is defined as **vertical lines** that define position in the
  x-direction. `ROOT_ULLON = -122.2998046875` is the farthest left x-coordinate
and `ROOT_LRLON = -122.2119140625` is the farthest right x-coordinate. Keep in
mind, the more negative the value, the farther left you are.
- Latitude is defined as the **horizontal lines** that define position in the
  y-direction. `ROOT_ULLAT = 37.892195547244356` is the highest y-coordinate and `ROOT_LRLAT = 37.82280243352756` is the lowest y-coordinate. Again, it's important to
remember the more positive the value, the higher you are in the vertical
direction.

### Calculating Depth

Depth is defined as how much distance is represented per pixel. The larger the
depth, the less distance is represented per pixel (meaning we are more zoomed
in and there is more detail). We are given height, width, upper left longitude,
lower right longitude, upper left latitude, and lower right latitude. We use
these values to calculate the longitudinal distance per pixel and latitudinal
distance per pixel. We define these values as follows:

- `lonDpp = (lrlon - ullon) / w`
- `latDpp = (ullat - lrlat) / h`

This gives us an approximate distance per pixel and we can choose the depth
that has approximately the same DPP.

### Getting Raster Bounds and Tile Indices

**Students will most likely not read the MapServer file carefully, so encourage
them to do so first. There are some helpful fields there.**

To do this part correctly, it is important to define your coordinate system
appropriately. It seems most logical to define changes in longitude as the
x-direction and changes in latitude as the y-direction. Now at a given depth,
there are `(2^depth)^2` total tiles (`2^depth` x `2^depth` grid).

- The goal is to find the x-index and y-index of the upper left coordinate and
  the lower right coordinate.
- Using the depth information to calculate the number of tiles per side at a
  given depth, and the `ROOT...` coordinate values in `MapServer`, we can
calculate the size of each tile in terms of lat/lon units.
- One way to build the final image is to loop through the axes starting from `ROOT_ULLON` and `ROOT_ULLAT`. While this is a reasonable approach, it is also heavily prone to off-by-one errors and other tricky bugs. Instead, note that we can generate a final image by finding the four corner tiles and including the images between them.
- Students may be struggling with finding overlapping tiles. Walk them through
  how to find whether or not a tile is overlapping the query area on paper
(you're gonna need the ullon, ullat, lrlon, lrlat values of both tile and query
box. Since each tile doesn't have that information ready available, I suggest
writing a method to calculate it).

More important information to save is the rastering latitude and longitude
bounds. Whenever we find that the point is within a given bounds, we can set
the raster latitude/longitude to the appropriate value.

- `ullon`: upper left longitude: left bound
- `ullat`: upper left latitude: upper bound
- `lrlon`: lower right longitude: right bound
- `lrlat`: lower right latitude: upper bound

This might be kind of tricky so recommend students to draw it out if they are
struggling.

### Translating Raster Bounds and Tile Indices to Output

The output tile indices will give us the bounds for the x-coordinates and
y-coordinates which we will then use to select the appropriate images. These
selected images are then passed into the `render_grid` argument.

All other information is generally pretty straightforward to output or already
calculated above. If students have problems, make sure they read the spec
closely.

## Graphs & Map Data

Students will have to parse data from the `berkeley-2018.osm` file and use that
data to construct nodes and edges that make up the graph.

Tell students to read `berkeley-2018.osm` and try to understand what each
element consists of. Point out that there are two types of elements: nodes and
ways.  Nodes are pretty straightforward. Ways, on the other hand, might take
some more time to understand. This is probably the hardest part. After
understanding it, the code is pretty straightforward. The details are in the
spec, so point them to that if they don't understand.

### `GraphBuildingHandler.java`

This class handles all the parsing. Students will need to implement the two
methods `startElement` and `endElement` and may add additional fields. The most
important part here is implementing the graph itself. They will need to
implement the underlying structure. Recommended that they use some sort of
adjacency list.

#### `startElement`

The students need to figure out what to do in the different cases specified in
the skeleton code. They need to use a graph, so encourage them to implement the
components of a graph first. Tell them to look at the berkeley.osm file and see
what the different attributes of a node are. They may need to declare fields
for some of those attributes within the graph. What may also help is asking
them what things are necessary to know about a node, given that we eventually
need to be doing pathfinding and directions.

*Note*: Each node has many
attributes which will be parsed individually. Thus, it is important to keep
track of the current node as you are parsing.

- `qName.equals("node")` case: make a new node object and add it to the graph.
- `activeState.equals("way") && qName.equals("nd")` case: this means that this
  element is just one node in the whole way. Tell the student to create a
structure that can keep track of all the edges in this way. *Note* this means
you need to keep track of the "prev" node somehow.
- `activeState.equals("way") && qName.equals("tag")` case:
  - This means we have gotten to the end of a way. The tag holds the
    identifying information of the way.
  - If it is a highway, check if it is allowed first.
  - If allowed, add the edge to the graph. **Edge information is important for
    the driving instructions part of the project.** So, encourage students to
think of what information they should include in each edge given that they need
to give driving directions.
  - *Note* We have a 2-part process here: first creating the way in a secondary
    structure, then adding all the edges in the secondary structure to the
actual graph. We need to do this separately because while we are parsing, we
cannot know whether or not the way is **allowed**. We have to wait until the
end to see that.
- `activeState.equals("node") && qName.equals("tag") &&
  attributes.getValue("k").equals("name")` case: Get the current node and set
its name. May need to clean the name.

#### `endElement`

This method is called when you just finished parsing through an entire element
of the database. Do any cleaning up: reset variables, active state. Actions to
take may be different depending on whether active state is "way" or "node".

### `GraphDB.java`

- `clean`: Go through all the nodes in the graph and remove nodes without any
  connections. Due to the problem with concurrent modification, they shouldn't
be removing nodes as they are iterating through the collection. Put the
nodes-to-be-removed into a another separate collection, and then remove them
from the original nodes list.
- `vertices`: Return any Iterable type containing all the nodes in the graph.
- `lat`: Get the node from the graph, and return its latitude.
- `lon`: Get node from graph and return its longitude.

## Route Search

### Nearest Neighbor Search

The naive solution for impelementing `closest` in `GraphDB.java` appears below.

```java
long closest(double lon, double lat) {
    double minDistance = Double.MAX_VALUE;
    long minID = 0L;
    for (long id : nodes.keySet()) {
        Node currNode = nodes.get(id);
        double currDistance = distance(currNode.lon(), lon, currNode.lat(), lat);
        if (currDistance < minDistance) {
            mindDistance = currDistance;
            minID = id;
        }
    }
    return minID;
}
```

In order to implement `closest` efficiently, we ask students to build a **k-d
tree** given the pseudocode on Wikipedia. In our case, we want a k-d tree in
2 dimensions (a *2D tree*). This tree works like a regular binary search tree
except we choose to alternate between splitting on the x-axis and y-axis on
each level.

We provide students the [pseudocode on Wikipedia][k-d tree]: their task is to
implement the k-d tree **construction** and **nearest neighbor search**
algorithms from the pseudocode. Students should make their own `KDTree` class
and then, once they've implemented the class, they should invoke it through
their `GraphDB`.

[k-d tree]: https://en.wikipedia.org/wiki/K-d_tree

See Robert Sedgewick and Kevin Wayne's *[2D Tree Demo][]* for more information.

[2D Tree Demo]: https://www.cs.princeton.edu/courses/archive/spring18/cos226/demos/99DemoKdTree.pdf

### A\* Search

#### `shortestPath`

First, students will have to find the closest nodes to the start and
destination coordinates. They can use the `distance` method provided in
GraphDB.

Then, they will need to implement A\* algorithm in its entirety.

- They will need to implement their own comparator for the fringe, or make the
  nodes themselves comparable. **If students are not finding the correct path,
then chances are their comparator is buggy.**
- Remember, to construct the path, we need to go backwards from the destination
  node until we get to the source, then reverse the list so that our path goes
from start node to destination node.

## Optional Extensions

#### `routeDirections`

Edges are important for this part of the project. Make sure that the student
included the name of the way somewhere in their graph. Iterate through all the
edges in the route (This may require some form of conversion from nodes to
edges).

- They should be creating individual navigation direction objects for each
  instruction.
- For edges that continue on the same way, just add to the distance but keep
  the same instruction.
- For an edge on a new way, compare the bearings of the edge and the previous
  one and determine the direction based on that. Create a new navigation
direction object for the new way.

The spec has all the information they need to determine direction from bearing.
