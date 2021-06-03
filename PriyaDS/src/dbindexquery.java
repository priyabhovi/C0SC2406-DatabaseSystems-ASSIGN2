import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public void initialiseTree() 
    {
         root = new Tree();
     }
    //This program inputs heap file from index file and performs required operation
 	private static void bplusTreeSearch(String[] args) 
 	{
      String[] SDTNames = Arrays.copyOfRange(args, 0, args.length - 1);
    	 String spageSize = args[args.length - 1];
 	 String indexFileName = "treeIndex." + spageSize;
 	 String SDTName = "";
      
 	  try
 	  {
       if(SDTNames.length == 1)
       {
 		SDTName = SDTNames[0];
       } 
       else 
       {
 	   	SDTName = String.join(" ", SDTNames);
       }
  
     //Connect to input file
     		FileInputStream fileInputStream = new FileInputStream(indexFileName);
     		FileChannel fileChan = fileInputStream.getChannel();
     		fileChan.position(1025l);
     		ObjectInputStream objectInStream = new ObjectInputStream(fileInputStream);
             Tree newroot = (Tree) objectInStream.readObject();
     		objectInStream.close();
     	    //search for input key
     	 	findData(newroot, indexFileName, SDTName, spageSize);
     	
     	  } 
     	  catch (FileNotFoundException e) 
     	  {
     		  e.printStackTrace();
     	  } 
     	  catch (IOException e) 
     	  {
     		  e.printStackTrace();
     	  } 
     	  catch (ClassNotFoundException e) 
     	  {
     		  e.printStackTrace();
     	  }

     	}

     	private static void findData(Tree node, String indexFile, String key, String pageSize) 
     	{
     	
     	  try
     	  {
     			
     		int keyLen = Integer.parseInt(fetchMetadata(indexFile,"key"));
     		if(key.length() > keyLen) 
     		{
     			key = key.substring(0, keyLen);
     		}
     		
     		//for (int x = 0; x < node.key.size()+1; x++) 
     	
     		for (int x = 0; x < node.key.size(); x++) 
     		{
       	  	  if (node.isLeaf) 
       	  	  {
     				boolean temp = node.key.get(x).contains(key);
     				int indexKey = -1;
     				
     				if (temp == true) 
     				{
     					indexKey = x;
     				}
     				
     			if (indexKey == -1) 
     			{
     					if (x < node.key.size() - 1) 
     					{
     						continue;
     					}
     					
     	
     				} 
     			else if (indexKey != -1) 
     			{ 
     					int offVal = node.offsetvalue.get(indexKey).intValue();
     					int dataLen = node.dataLength.get(indexKey);
     					showRecord(indexFile, offVal, dataLen, pageSize);
     				
     				}
     			}
     			else if (key.compareTo(node.key.get(x)) < 0) 
     			{
     				if (node.ptr.get(x) != null) 
     				{
     					findData(node.ptr.get(x), indexFile, key, pageSize);
     					return;
     				}
     			}
     			else if (key.compareTo(node.key.get(x)) >= 0) 
     			{
     		     if (x < node.key.size() - 1) 
     		     {
     					continue;
     		     }
     		    /* else if (key.compareTo(node.key.get(x)) >= 0) 
     				{
     			     if (x < node.key.size() - 1) 
     			     {
     						continue;
     			     }*/
     			else if (x == node.key.size() - 1) 
     			{
     			
     				if (!node.isLeaf && node.ptr.get(x + 1) != null) 
     				{
     						findData((Tree) node.ptr.get(x + 1),indexFile, key, pageSize);
     						return;
     					}
     				}

     			
     			}
     		}

     	} 
     	  catch (FileNotFoundException e) 
     	  {
     		e.printStackTrace();
     	  } 
     	  catch (IOException e) 
     	  {
     		e.printStackTrace();
     	  } 
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