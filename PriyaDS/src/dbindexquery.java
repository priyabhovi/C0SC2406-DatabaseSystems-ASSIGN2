
public class dbindexquery {

}
// Main Method of the program
public static void main(String[] args) 
{
	dbindexquery objectTree = new dbindexquery();
  
   try 
   {
    
    long startTime = System.currentTimeMillis();
    objectTree.bplusTreeSearch(args);
    long stopTime = System.currentTimeMillis();
    long totTime = stopTime - startTime;
    System.out.println(totTime + " ms");
    //long stopTime = System.currentTimeMillis();
   } 
   catch(Exception e) 
   {
		System.out.println(e);
   }
}
}