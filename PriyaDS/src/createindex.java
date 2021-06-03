

public class createindex 
{

// size of field SDTName
static final int SDTName_SIZE = 24;
// Total size of a single record.
static final int RECORD_SIZE = 111;
//static Tree root;
static int Nodesize = 0; 
//initialize the size of each field in a record
private static final int STD_NAME_SIZE = 24;
private static final int ID_SIZE = 4;
private static final int DATE_SIZE = 8;
private static final int YEAR_SIZE = 4;
private static final int MONTH_SIZE = 9;
private static final int MDATE_SIZE = 4;
private static final int DAY_SIZE = 9;
private static final int TIME_SIZE = 4;
private static final int SENSORID_SIZE = 4;
private static final int SENSORNAME_SIZE = 38;
private static final int COUNTS_SIZE = 4;
private static final int TOTAL_SIZE =  STD_NAME_SIZE + 
ID_SIZE + 
DATE_SIZE + 
YEAR_SIZE + 
MONTH_SIZE + 
MDATE_SIZE + 
DAY_SIZE + 
TIME_SIZE + 
SENSORID_SIZE + 
SENSORNAME_SIZE + 
COUNTS_SIZE;

// Initialize tree for the first time
public void initialiseTree() 
{
	//root = new Tree();
}
public static void main(String[] args) 
{
  createindex objTree = new createindex();
  
   try 
   {
    String input = args[0];
    int pageSize = Integer.parseInt(input);
    
    long startTime = System.currentTimeMillis();
    
	   objTree.initialiseTree();
   // objTree.CreateTree(pageSize);
  
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
}