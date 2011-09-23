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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import net.skyebook.padloader.record.Record;

/**
 * @author Skye Book
 *
 */
public abstract class CSVReader {
	
	protected void stripQuotesFromLine(String[] line){
		for(int i=0; i<line.length; i++){
			line[i] = line[i].substring(1, line[i].length()-1);
		}
	}
	
	protected boolean isEmptyValue(String value){
		if(value.isEmpty()) return true;
		else{
			for(int i=0; i<value.length(); i++){
				if(value.charAt(i)!=' '){
					return false;
				}
			}
			return true;
		}
	}
	
	protected BufferedReader openFileReader(File file) throws FileNotFoundException{
		return new BufferedReader(new FileReader(file));
	}
	
	/**
	 * Reads the records from a CSV file
	 * @param fileToRead
	 * @return
	 */
	public abstract List<Record> readRecords(File fileToRead) throws IOException;

}
