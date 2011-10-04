import java.io.File;
import java.io.IOException;
import java.util.List;

import net.skyebook.padloader.database.DatabaseInterface;
import net.skyebook.padloader.database.MongoImplementation;
import net.skyebook.padloader.read.ADRReader;
import net.skyebook.padloader.read.BBLReader;
import net.skyebook.padloader.record.Record;

/**
 * 
 */

/**
 * @author Skye Book
 *
 */
public class RunPadLoader {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		DatabaseInterface db = new MongoImplementation();
		
		File addrFile = new File("/Users/skyebook/Downloads/pad10d/bobaadr.txt");
		List<Record> adr = new ADRReader().readRecords(addrFile);
		System.out.println(adr.size() + " records read");
		
		long dbStart = System.currentTimeMillis();
		
		for(Record record : adr){
			db.addRecord(record);
		}
		
		System.out.println("ADR database insert took " + (System.currentTimeMillis()-dbStart)+"ms");
		
		// release the resources
		adr = null;
		
		
		
		File bblFile = new File("/Users/skyebook/Downloads/pad10d/bobabbl.txt");
		List<Record> bbl = new BBLReader().readRecords(bblFile);
		System.out.println(bbl.size() + " records read");
		
		dbStart = System.currentTimeMillis();
		
		for(Record record : bbl){
			db.addRecord(record);
		}
		
		System.out.println("ADR database insert took " + (System.currentTimeMillis()-dbStart)+"ms");
		
		// release the resources
		bbl = null;
	}

}
