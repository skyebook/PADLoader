/**
 * 
 */
package net.skyebook.padloader.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.skyebook.padloader.read.BBLReader;
import net.skyebook.padloader.record.Record;

/**
 * @author Skye Book
 *
 */
public class TestBBLReader {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File file = new File("/Users/skyebook/Downloads/pad10d/bobabbl.txt");
		List<Record> records = new BBLReader().readRecords(file);
		System.out.println(records.size() + " records read");
	}

}
