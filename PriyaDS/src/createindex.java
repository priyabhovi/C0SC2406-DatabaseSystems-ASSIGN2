
public class createindex {
	private static final int STD_NAME_SIZE = 24;
	private static final int ID_SIZE = 4;
	private static final int DATE_SIZE = 8;
	

public static void main(String[] args) 
{
  createindex objTree = new createindex();
  
   try 
   {
    String input = args[0];
    int pageSize = Integer.parseInt(input);
    
    long startTime = System.currentTimeMillis();
    
	   objTree.initialiseTree();
    objTree.CreateTree(pageSize);
  
    long stopTime = System.currentTimeMillis();
    
    System.out.println(stopTime - startTime + " ms");
    System.out.println((stopTime - startTime) + " ms");
    System.out.println("Index created successfully");
	   
	} 
   catch (Exception e) 
   {   
      System.out.println("Please enter a valid pagesize");
 }
}
private void CreateTree(int pageSize) {
	// TODO Auto-generated method stub
	
}
private void initialiseTree() {
	// TODO Auto-generated method stub
	
}
}