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
package net.skyebook.padloader.read;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.skyebook.padloader.record.BBLRecord;
import net.skyebook.padloader.record.Record;

/**
 * @author Skye Book
 *
 */
public class BBLReader extends CSVReader {
	
	private String[] keys = null;

	/* (non-Javadoc)
	 * @see net.skyebook.padloader.read.CSVReader#readRecords(java.io.File)
	 */
	@Override
	public List<Record> readRecords(File fileToRead) throws IOException {
		List<Record> records = new ArrayList<Record>();
		
		BufferedReader reader = openFileReader(fileToRead);
		
		while(reader.ready()){
			// retrieve the line of text
			String line = reader.readLine();
			
			// If this is the first line, they keys will still be null
			if(keys==null){
				keys = line.split(",");
				stripQuotesFromLine(keys);
			}
			// If this isn't the first line, we simply process the record and add it to the list
			else{
				String[] values = line.split(",");
				stripQuotesFromLine(values);
				BBLRecord record = createBBLRecord(values);
				records.add(record);
			}
		}
		
		return records;
	}
	
	private BBLRecord createBBLRecord(String values[]){
		BBLRecord bbl = new BBLRecord();
		
		for(int i=0; i<values.length; i++){
			BBLRecord.Fields key = BBLRecord.Fields.valueOf(keys[i]);
			
			// if the value is empty, skip it
			if(isEmptyValue(values[i])){
				continue;
			}
			
			// what does it link to?
			switch (key) {
			case loboro:
				bbl.setLoboro(Short.parseShort(values[i]));
				break;
			case loblock:
				bbl.setLoblock(Integer.parseInt(values[i]));
				break;
			case lolot:
				bbl.setLolot(Integer.parseInt(values[i]));
				break;
			case lobblscc:
				bbl.setLobblscc(Short.parseShort(values[i]));
				break;
			case hiboro:
				bbl.setHiboro(Short.parseShort(values[i]));
				break;
			case hiblock:
				bbl.setHiblock(Integer.parseInt(values[i]));
				break;
			case hilot:
				bbl.setHilot(Integer.parseInt(values[i]));
				break;
			case hibblscc:
				bbl.setHibblscc(Short.parseShort(values[i]));
				break;
			case boro:
				bbl.setBoro(Short.parseShort(values[i]));
				break;
			case block:
				bbl.setBlock(Integer.parseInt(values[i]));
				break;
			case lot:
				bbl.setLot(Integer.parseInt(values[i]));
				break;
			case bblscc:
				bbl.setBblscc(Short.parseShort(values[i]));
				break;
			case billboro:
				bbl.setBillboro(Short.parseShort(values[i]));
				break;
			case billblock:
				bbl.setBillblock(Integer.parseInt(values[i]));
				break;
			case billlot:
				bbl.setBilllot(Integer.parseInt(values[i]));
				break;
			case billbblscc:
				bbl.setBillbblscc(Short.parseShort(values[i]));
				break;
			case condoflag:
				bbl.setCondoflag(values[i].charAt(0));
				break;
			case condonum:
				bbl.setCondonum(values[i]);
				break;
			case coopnum:
				bbl.setCoopnum(Integer.parseInt(values[i]));
				break;
			case numbf:
				bbl.setNumbf(Short.parseShort(values[i]));
				break;
			case numaddr:
				bbl.setNumaddr(Integer.parseInt(values[i]));
				break;
			case vacant:
				bbl.setVacant(values[i].charAt(0));
				break;
			case interior:
				bbl.setInterior(values[i].charAt(0));
				break;
			}
		}
		
		return bbl;
	}

}
