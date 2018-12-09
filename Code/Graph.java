import java.util.*;

public class Graph implements GraphADT
{
	GraphEdge[][] matrix;
	GraphNode[] nodes;
	
	public Graph(int n)
	{
		nodes = new GraphNode[n];
		
		for (int i = 0; i < n; i ++)
			nodes[i] = new GraphNode(i);
		
		matrix = new GraphEdge[n][n];
	}

	public void insertEdge(GraphNode u, GraphNode v, char busLine) throws GraphException
	{
		int length = nodes.length;
		int uName = u.getName(), vName = v.getName();
		if (uName >= length || vName >= length || nodes[vName] == null ||matrix[uName][vName] != null || nodes[uName] == null)
			throw new GraphException("Error");
		else
		{
			matrix[uName][vName] = new GraphEdge(u,v,busLine); 
			matrix[vName][uName] = new GraphEdge(v,u,busLine);
		}
	}
	
	public GraphNode getNode(int name) throws GraphException
	{
		int length = nodes.length;
		
		if (name >= length || nodes[name] == null)
			throw new GraphException("Not found.");
		else
		{
			return nodes[name];
		}
		
	}
		
	public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException
	{
		int length = nodes.length;
		int uName = u.getName();
		if (u.getName() >= length)
			throw new GraphException("Node not found.");
		
				List<GraphEdge> edge= new ArrayList<>();	
				int i = 0;
				while(i < length)
				{
					if (matrix[uName][i]!=null) edge.add(matrix[uName][i]);
					i++;
				}
				if (edge.isEmpty() == false)
					return edge.iterator();
				else
					return null;
			}
	
	public GraphEdge getEdge(GraphNode u,GraphNode v) throws GraphException
	{
	
		if (u.getName() >= nodes.length || v.getName() >= nodes.length || nodes[v.getName()] == null || nodes[u.getName()] == null || matrix[u.getName()][v.getName()] == null)
			throw new GraphException("Edge not found.");
		
		return matrix[u.getName()][v.getName()];
	}
	
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException
	{
		int length = nodes.length;
		int uName = u.getName(), vName = v.getName();
		if (uName >= length || vName >= length || nodes[vName] == null || nodes[uName] == null)
			throw new GraphException("cant insert");
		
		if (matrix[u.getName()][v.getName()] != null)
			return true;
		else
			return false;
	}
}