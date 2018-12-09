import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Stack;
import java.io.*;
public class BusLines 																													
{
	private Graph graphMap;
	private Stack<GraphNode> path = new Stack<GraphNode>();
	
	private int  width;
	private int length;
	private int route;
	private int start;
	private int end;

	BusLines(String inputFile) throws MapException																
	{
		BufferedReader input;
		
		try 																															
		{
			input = new BufferedReader(new FileReader(inputFile));
			String line;
			
			line = input.readLine();
			String [] getFirst = line.split(" ");
			
			int count = 0;

			//read through the file, use the getfirst array to seperate by letters
			String readFirst = input.readLine();
			String readSecond = input.readLine();
			width = Integer.parseInt(getFirst[1]);
			length= Integer.parseInt(getFirst[2]);
			route= Integer.parseInt(getFirst[3]);
			
			graphMap = new Graph(length * width);
			
			int lengthOne = readFirst.length();
			int lengthTwo = readSecond.length();

			while(readFirst != null && readSecond != null) 																						
			{
				int matrixWidth = 0;
				
				while(matrixWidth < lengthTwo) 																				
				{
					char firstLetter = readFirst.charAt(matrixWidth);
					char secondLetter = readSecond.charAt(matrixWidth);
					
					GraphNode firstNode = null;
					GraphNode secondNode = null;
					 
					if(firstLetter == 'S')
						start = count;
					else if (secondLetter == 'D') 
						end = count;

					if((matrixWidth % 2) != 0) 
					{
						if(firstLetter != ' ') 		
						{
							switch(secondLetter)
							{
								case 'S':
									break;
								case 'D':
									break;
								case '.':
									break;
								//if all above cases fail then run default
								default:									graphMap.insertEdge(new GraphNode(count), new GraphNode(count+1), readFirst.charAt(matrixWidth));
									break;	
							}																	
						}
						count++;																							
					}
					
					else 																												
					{
						if(secondLetter != ' ' ) 																					
						{
							graphMap.insertEdge(new GraphNode(count), new GraphNode(count + width), readSecond.charAt(matrixWidth));
						}																									
					}	
					matrixWidth++;	
				}
				
				readFirst = input.readLine();
				readSecond = input.readLine();
				count++;																								
				}
			
			int matrixWidth = 0;
			while(matrixWidth < lengthOne) 																									
			{
				
				switch(readFirst.charAt(matrixWidth))
				{
					case 'S':
						start = count;
						break;
					case 'D':
						end = count;
						break;
					case ' ':
						count++;
						break;
					case '.':
						break;
					//if none of the above cases are met run default
					default:
						graphMap.insertEdge(new GraphNode(count), new GraphNode(count+1), readFirst.charAt(matrixWidth));
						count++;
						break;
				}
				matrixWidth++;																												
			}																											
		}
		catch (GraphException e)
		{
			throw new MapException("Graph Exception");
		}
		 catch (IOException e) 
		{
			throw new MapException("Map Exception");
		}
																																		
	}
	
	Graph getGraph() throws MapException 																								
	{
		return graphMap;
	}
	
	public Iterator<GraphNode> trip() throws GraphException
	{
		try
		{
			Iterator<GraphEdge> edge = graphMap.incidentEdges(graphMap.getNode(start));
			
			//infinite loop until the next edge is null
			for(;;)
			{
				GraphEdge nextEdge = edge.next();
				if (getPath(graphMap.getNode(start), graphMap.getNode(end), nextEdge.getBusLine()))
					return path.iterator();
				
				//break if the next incident edge is null
				if(!edge.hasNext())
					break;
			}
			return null;
		}
		catch (GraphException e)
		{
			throw new GraphException("Graph Exception");
		}
	
		
	}
	
	private boolean getPath(GraphNode first, GraphNode last, char busses) throws GraphException
	{		
		first.setMark(true);
		path.push(first);
		
		if(first.getName() != last.getName())
		{
			
			Iterator<GraphEdge> incidents = graphMap.incidentEdges(first);
			
			while(incidents.hasNext()) 
			{
				GraphEdge road = incidents.next();
				boolean routeCheck = false;
				
				if(route>0)
					routeCheck = true;
				
				if(routeCheck || road.getBusLine() == busses) 
				{
					boolean endpoint = graphMap.getNode(road.secondEndpoint().getName()).getMark();

					if(!endpoint) 
					{
						if(road.getBusLine() != busses)
							route--;
						
						//decrement the route assuming the next statement is true			
						if(getPath(graphMap.getNode(road.secondEndpoint().getName()), last, road.getBusLine()))
							return true;
								
						//if the above is false, then increment the route
						else if(road.getBusLine() != busses)
							route++;
					}					
				}
			}

			first.setMark(false);
			path.pop();
			return false;
		}
		//if first is last
		else
			return true;
	}
	
	}
	
	
	
	
	
	
	
	
	
