package client.ai.pathfinding;

import java.util.LinkedList;
import java.util.List;


public class Map<T extends AbstractNode> {
	protected static boolean CANMOVEDIAGONALY = true;

	private T[][] nodes;
	protected int width;
	protected int higth;

	@SuppressWarnings("unchecked")
	public Map(int width, int higth) {
		nodes = (T[][]) new AbstractNode[width][higth];
		this.width = width-1;
		this.higth = higth-1;
		initEmptyNodes();
	}

	@SuppressWarnings("unchecked")
	private void initEmptyNodes() {
		for (int i = 0; i <= width; i++) {
			for (int j = 0; j <= higth; j++) {
				nodes[i][j] = (T) new MapNode(i, j);
			}
		}
	}

	public void setWalkable(int x, int y, boolean value) {
		nodes[x][y].setWalkable(value);
	}

	public final T getNode(int x, int y) {
		return nodes[x][y];
	}

	private List<T> openList;
	private List<T> closedList;
	private boolean done = false;


	public final List<T> findPath(int oldX, int oldY, int newX, int newY) {
		openList = new LinkedList<>();
		closedList = new LinkedList<>();
		openList.add(nodes[oldX][oldY]);

		done = false;
		T current;
		while (!done) {
			current = lowestFInOpen();//get node with lowest fCosts from openList
			closedList.add(current);//add current node to closed list
			openList.remove(current); //delete current node from open list

			if ((current.getXPosition() == newX)
					&& (current.getYPosition() == newY)) {//found goal
				return calcPath(nodes[oldX][oldY], current);
			}

			List<T> adjacentNodes = getAdjacent(current);
			for (int i = 0; i < adjacentNodes.size(); i++) {
				T currentAdj = adjacentNodes.get(i);
				if (!openList.contains(currentAdj)) {//node is not in openList
					currentAdj.setPrevious(current);//set current node as previous for this node
					currentAdj.sethCosts(nodes[newX][newY]);//set h costs of this node (estimated costs to goal)
					currentAdj.setgCosts(current);//set g costs of this node (costs from start to this node)
					openList.add(currentAdj);//add node to openList
				} else {//node is in openList
					if (currentAdj.getgCosts() > currentAdj.calculategCosts(current)) {//costs from current node are cheaper than previous costs
						currentAdj.setPrevious(current);//set current node as previous for this node
						currentAdj.setgCosts(current);//set g costs of this node (costs from start to this node)
					}
				}
			}

			if (openList.isEmpty()) {//no path exists
				return new LinkedList<>();//return empty list
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private List<T> calcPath(T start, T goal) {
		LinkedList<T> path = new LinkedList<>();

		T curr = goal;
		boolean done = false;
		while (!done) {
			path.addFirst(curr);
			curr = (T)curr.getPrevious();

			if (curr.equals(start)) {
				done = true;
			}
		}
		return path;
	}

	private T lowestFInOpen() {
		T cheapest = openList.get(0);
		for (int i = 0; i < openList.size(); i++) {
			if (openList.get(i).getfCosts() < cheapest.getfCosts()) {
				cheapest = openList.get(i);
			}
		}
		return cheapest;
	}

	private List<T> getAdjacent(T node) {
		int x = node.getXPosition();
		int y = node.getYPosition();
		List<T> adj = new LinkedList<>();

		T temp;
		if (x > 0) {
			temp = this.getNode((x - 1), y);
			if (temp.isWalkable() && !closedList.contains(temp)) {
				temp.setIsDiagonaly(false);
				adj.add(temp);
			}
		}

		if (x < width) {
			temp = this.getNode((x + 1), y);
			if (temp.isWalkable() && !closedList.contains(temp)) {
				temp.setIsDiagonaly(false);
				adj.add(temp);
			}
		}

		if (y > 0) {
			temp = this.getNode(x, (y - 1));
			if (temp.isWalkable() && !closedList.contains(temp)) {
				temp.setIsDiagonaly(false);
				adj.add(temp);
			}
		}

		if (y < higth) {
			temp = this.getNode(x, (y + 1));
			if (temp.isWalkable() && !closedList.contains(temp)) {
				temp.setIsDiagonaly(false);
				adj.add(temp);
			}
		}
		// add nodes that are diagonaly adjacent too:
		if (CANMOVEDIAGONALY) {
			if (x < width && y < higth) {
				temp = this.getNode((x + 1), (y + 1));
				if (temp.isWalkable() && !closedList.contains(temp)) {
					temp.setIsDiagonaly(true);
					adj.add(temp);
				}
			}

			if (x > 0 && y > 0) {
				temp = this.getNode((x - 1), (y - 1));
				if (temp.isWalkable() && !closedList.contains(temp)) {
					temp.setIsDiagonaly(true);
					adj.add(temp);
				}
			}

			if (x > 0 && y < higth) {
				temp = this.getNode((x - 1), (y + 1));
				if (temp.isWalkable() && !closedList.contains(temp)) {
					temp.setIsDiagonaly(true);
					adj.add(temp);
				}
			}

			if (x < width && y > 0) {
				temp = this.getNode((x + 1), (y - 1));
				if (temp.isWalkable() && !closedList.contains(temp)) {
					temp.setIsDiagonaly(true);
					adj.add(temp);
				}
			}
		}
		return adj;
	}

}
