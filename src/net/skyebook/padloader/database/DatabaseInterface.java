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
package net.skyebook.padloader.database;

import java.util.List;

import net.skyebook.padloader.record.ADRRecord;
import net.skyebook.padloader.record.BBLRecord;
import net.skyebook.padloader.record.Record;

/**
 * @author Skye Book
 *
 */
public interface DatabaseInterface {
	
	/**
	 * Adds a {@link record} to the database
	 * @param record
	 */
	public void addRecord(Record record);
	
	/**
	 * Adds an {@link ADRRecord} to the database
	 * @param record
	 */
	public void addRecord(ADRRecord record);
	
	/**
	 * Adds a {@link BBLRecord} to the database
	 * @param record
	 */
	public void addRecord(BBLRecord record);
	
	/**
	 * Finds an {@link ADRRecord}
	 * @param borough
	 * @param block
	 * @param lot
	 * @return
	 */
	public List<ADRRecord> findADRRecord(int borough, int block, int lot);
	
	/**
	 * Finds a {@link BBLRecord}
	 * @param borogh
	 * @param block
	 * @param lot
	 * @return
	 */
	public List<BBLRecord> findBBLRecord(int borogh, int block, int lot);
	
	/**
	 * Finds an {@link ADRRecord}
	 * @param borough
	 * @param block
	 * @param lot
	 * @return
	 */
	public List<ADRRecord> findADRRecord(int block, int lot);
	
	/**
	 * Finds a {@link BBLRecord}
	 * @param borogh
	 * @param block
	 * @param lot
	 * @return
	 */
	public List<BBLRecord> findBBLRecord(int block, int lot);
	
	/**
	 * Finds an {@link ADRRecord}
	 * @param query
	 * @return
	 */
	public List<ADRRecord> findRecord(int bin);
	
}
