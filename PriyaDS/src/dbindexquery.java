
public class dbindexquery {

     //All constants used for this program
    static final int SDTName_SIZE = 25;
    static final int RECORD_SIZE = 112;
  	static Tree root;
	static int Nodesize = 0; 
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
    private static final int TOTAL_SIZE =    STD_NAME_SIZE + 
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
											public static final int ID_OFFSET =   STD_NAME_SIZE;

    private static final int DATE_OFFSET =   STD_NAME_SIZE +
                                            ID_SIZE;

    private static final int YEAR_OFFSET =  STD_NAME_SIZE +
                                            ID_SIZE +
                                            DATE_SIZE;

    private static final int MONTH_OFFSET =  STD_NAME_SIZE +
                                            ID_SIZE +
                                            DATE_SIZE +
                                            YEAR_SIZE;

    private static final int MDATE_OFFSET =  STD_NAME_SIZE +
                                            ID_SIZE +
                                            DATE_SIZE +
                                            YEAR_SIZE +
                                            MONTH_SIZE;

    private static final int DAY_OFFSET =   STD_NAME_SIZE +
                                            ID_SIZE +
                                            DATE_SIZE +
                                            YEAR_SIZE +
                                            MONTH_SIZE +
                                            MDATE_SIZE;

    private static final int TIME_OFFSET =   STD_NAME_SIZE + 
                                            ID_SIZE + 
                                            DATE_SIZE +
                                            YEAR_SIZE +
                                            MONTH_SIZE +
                                            MDATE_SIZE +
                                            DAY_SIZE;

    private static final int SENSORID_OFFSET =   STD_NAME_SIZE + 
                                                ID_SIZE + 
                                                DATE_SIZE +
                                                YEAR_SIZE +
                                                MONTH_SIZE +
                                                MDATE_SIZE +
                                                DAY_SIZE +
                                                TIME_SIZE;

    private static final int SENSORNAME_OFFSET = STD_NAME_SIZE + 
                                                ID_SIZE + 
                                                DATE_SIZE + 
                                                YEAR_SIZE + 
                                                MONTH_SIZE + 
                                                MDATE_SIZE + 
                                                DAY_SIZE + 
                                                TIME_SIZE + 
                                                SENSORID_SIZE; 

    private static final int COUNTS_OFFSET = STD_NAME_SIZE + 
                                            ID_SIZE + 
                                            DATE_SIZE + 
                                            YEAR_SIZE + 
                                            MONTH_SIZE + 
                                            MDATE_SIZE + 
                                            DAY_SIZE + 
                                            TIME_SIZE + 
                                            SENSORID_SIZE + 
                                            SENSORNAME_SIZE;  
  
    // Initialize b+ tree
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