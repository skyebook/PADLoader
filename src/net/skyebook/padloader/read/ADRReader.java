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

import net.skyebook.padloader.record.ADRRecord;
import net.skyebook.padloader.record.Record;

/**
 * @author Skye Book
 *
 */
public class ADRReader extends CSVReader {
	
	String[] keys = null;

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
			}
			// If this isn't the first line, we simply process the record and add it to the list
			else{
				ADRRecord record = createADRecord(line.split(","));
				records.add(record);
			}
		}
		
		return records;
	}
	
	private ADRRecord createADRecord(String values[]){
		ADRRecord adr = new ADRRecord();
		
		for(int i=0; i<values.length; i++){
			ADRRecord.Fields key = ADRRecord.Fields.valueOf(keys[i]);
			// what does it link to?
			switch (key) {
			case boro:
				adr.setBoro(Short.parseShort(values[i]));
				break;
			case block:
				adr.setBlock(Integer.parseInt(values[i]));
				break;
			case lot:
				adr.setLot(Integer.parseInt(values[i]));
				break;
			case bin:
				adr.setBin(Integer.parseInt(values[i]));
				break;
			case lhnd:
				adr.setLhnd(values[i]);
				break;
			case lhns:
				adr.setLhns(values[i]);
				break;
			case lcontpar:
				adr.setLcontpar(Short.parseShort(values[i]));
				break;
			case lsos:
				adr.setLsos(Short.parseShort(values[i]));
				break;
			case hhnd:
				adr.setHhnd(values[i]);
				break;
			case hhns:
				adr.setHhns(values[i]);
				break;
			case hcontpar:
				adr.setHcontpar(Short.parseShort(values[i]));
				break;
			case hsos:
				adr.setHsos(Short.parseShort(values[i]));
				break;
			case scboro:
				adr.setScboro(Short.parseShort(values[i]));
				break;
			case sc5:
				adr.setSc5(Integer.parseInt(values[i]));
				break;
			case sclgc:
				adr.setSclgc(Short.parseShort(values[i]));
				break;
			case stname:
				adr.setStname(values[i]);
				break;
			case addrtype:
				adr.setAddrtype(Short.parseShort(values[i]));
				break;
			case realb7sc:
				adr.setRealb7sc(Integer.parseInt(values[i]));
				break;
			case validlgcs:
				// break up valid LGC's
				break;
			case parity:
				adr.setParity(Short.parseShort(values[i]));
				break;
			case b10sc:
				adr.setB10sc(Long.parseLong(values[i]));
				break;
			case segid:
				adr.setSegid(Integer.parseInt(values[i]));
				break;
			}
		}
		
		return adr;
	}

}
