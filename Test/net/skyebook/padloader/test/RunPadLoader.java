/**
 *  PADLoader - Loads NYC Property Address Directory into a Database
 *  Copyright (C) 2011  Skye Book
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.skyebook.padloader.test;
import java.io.File;
import java.io.IOException;
import java.util.List;

import net.skyebook.padloader.database.DatabaseInterface;
import net.skyebook.padloader.database.DerbyImplementation;
import net.skyebook.padloader.read.ADRReader;
import net.skyebook.padloader.read.BBLReader;
import net.skyebook.padloader.record.Record;

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
		
		DatabaseInterface db = new DerbyImplementation();
		
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
		
		System.out.println("BBL database insert took " + (System.currentTimeMillis()-dbStart)+"ms");
		
		// release the resources
		bbl = null;
	}

}
