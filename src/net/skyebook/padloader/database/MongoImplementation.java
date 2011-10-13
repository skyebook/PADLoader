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

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

import net.skyebook.padloader.record.ADRRecord;
import net.skyebook.padloader.record.BBLRecord;
import net.skyebook.padloader.record.Record;

/**
 * @author Skye Book
 *
 */
public class MongoImplementation implements DatabaseInterface {
	
	private Mongo mongo;
	private DB database;

	/**
	 * 
	 */
	public MongoImplementation() {
		try {
			mongo = new Mongo("localhost");
			database = mongo.getDB("property-address-directory");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		

		System.out.println("--Collections--");
		for(String s : database.getCollectionNames()){
			System.out.println(s);
		}
		System.out.println("--End Collections--");
	}

	/* (non-Javadoc)
	 * @see net.skyebook.padloader.database.DatabaseInterface#addRecord(net.skyebook.padloader.record.ADRRecord)
	 */
	@Override
	public void addRecord(ADRRecord record) {
		BasicDBObject document = new BasicDBObject();
		document.put("boro", record.getBoro());
		document.put("block", record.getBlock());
		document.put("lot", record.getLot());
		document.put("bin", record.getBin());
		document.put("lhnd", record.getLhnd());
		document.put("lhns", record.getLhns());
		document.put("lcontpar", ""+record.getLcontpar());
		document.put("lsos", ""+record.getLsos());
		document.put("hhnd", record.getHhnd());
		document.put("hhns", record.getHhns());
		document.put("hcontpar", ""+record.getHcontpar());
		document.put("hsos", ""+record.getHsos());
		document.put("scboro", record.getScboro());
		document.put("sc5", record.getSc5());
		document.put("sclgc", record.getSclgc());
		document.put("stname", record.getStname());
		document.put("addrtype", ""+record.getAddrtype());
		document.put("realb7sc", record.getRealb7sc());
		document.put("validlgcs", record.getValidlgcs());
		document.put("parity", record.getParity());
		document.put("b10sc", record.getB10sc());
		document.put("segid", record.getSegid());
		DBCollection adr = database.getCollection("adr");
		adr.insert(document);
	}

	/* (non-Javadoc)
	 * @see net.skyebook.padloader.database.DatabaseInterface#addRecord(net.skyebook.padloader.record.BBLRecord)
	 */
	@Override
	public void addRecord(BBLRecord record) {
		BasicDBObject document = new BasicDBObject();
		document.put("loboro", record.getLoboro());
		document.put("loblock", record.getLoblock());
		document.put("lolot", record.getLolot());
		document.put("lobblscc", record.getLobblscc());
		document.put("hiboro", record.getHiboro());
		document.put("hiblock", record.getHiblock());
		document.put("hilot", record.getHilot());
		document.put("hibblscc", record.getHibblscc());
		document.put("boro", record.getBoro());
		document.put("block", record.getBlock());
		document.put("lot", record.getLot());
		document.put("bblscc", record.getBblscc());
		document.put("billboro", record.getBillboro());
		document.put("billblock", record.getBillblock());
		document.put("billlot", record.getBilllot());
		document.put("billbblscc", record.getBillbblscc());
		document.put("condoflag", ""+record.getCondoflag());
		document.put("condonum", record.getCondonum());
		document.put("coopnum", record.getCoopnum());
		document.put("numbf", record.getNumbf());
		document.put("numaddr", record.getNumaddr());
		document.put("vacant", ""+record.getVacant());
		document.put("interior", ""+record.getInterior());
		DBCollection bbl = database.getCollection("bbl");
		bbl.insert(document);
	}

	/* (non-Javadoc)
	 * @see net.skyebook.padloader.database.DatabaseInterface#addRecord(net.skyebook.padloader.record.Record)
	 */
	@Override
	public void addRecord(Record record) {
		if(record instanceof ADRRecord){
			addRecord((ADRRecord)record);
		}
		else{
			addRecord((BBLRecord)record);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see net.skyebook.padloader.database.DatabaseInterface#findADRRecord(int, int, int)
	 */
	@Override
	public List<ADRRecord> findADRRecord(int borough, int block, int lot) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see net.skyebook.padloader.database.DatabaseInterface#findBBLRecord(int, int, int)
	 */
	@Override
	public List<BBLRecord> findBBLRecord(int borough, int block, int lot) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.skyebook.padloader.database.DatabaseInterface#findRecord(int)
	 */
	@Override
	public List<ADRRecord> findRecord(int bin) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.skyebook.padloader.database.DatabaseInterface#findADRRecord(int, int)
	 */
	@Override
	public List<ADRRecord> findADRRecord(int block, int lot) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.skyebook.padloader.database.DatabaseInterface#findBBLRecord(int, int)
	 */
	@Override
	public List<BBLRecord> findBBLRecord(int block, int lot) {
		// TODO Auto-generated method stub
		return null;
	}

}
