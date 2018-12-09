public class GraphNode 
{
	int name;
	boolean mark;
	
	public GraphNode(int newName)
	{
		name = newName;
	}
	
	public void setMark(boolean newMark)
	{
		mark = newMark;
	}
	
	public boolean getMark()
	{
		return mark;
	}
	
	public int getName()
	{
		return name;
	}
}