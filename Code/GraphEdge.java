public class GraphEdge 
{
	char type;
	GraphNode endpointOne;
	GraphNode endpointTwo;
	
	public GraphEdge(GraphNode u, GraphNode v, char busLine)
	{
		endpointOne = u;
		endpointTwo = v;
		type = busLine;
	}
	
	public GraphNode firstEndpoint()
	{
		return endpointOne;
	}
	
	public GraphNode secondEndpoint()
	{
		return endpointTwo;
	}

	
	public char getBusLine()
	{
		return type;
	}
}