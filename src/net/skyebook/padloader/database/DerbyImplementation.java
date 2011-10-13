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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.skyebook.padloader.record.ADRRecord;
import net.skyebook.padloader.record.BBLRecord;
import net.skyebook.padloader.record.Record;
import net.skyebook.padloader.util.ResourceLoader;

/**
 * Connects to an embedded Derby database
 * @author Skye Book
 *
 */
public class DerbyImplementation implements DatabaseInterface {

	private final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private String databaseName = "property-address-directory";
	private String connectionString;
	private Connection connection;

	private PreparedStatement insertADR;
	private PreparedStatement insertBBL;

	private PreparedStatement findADRByBoroughBlockLot;

	private boolean adrExisted = true;
	private boolean bblExisted = true;

	/**
	 * 
	 */
	public DerbyImplementation() {
		this(null);
	}

	/**
	 * 
	 * @param databaseLocation The location to create the database
	 * in.  {@code null} will have Derby start in the default location
	 */
	public DerbyImplementation(String databaseLocation) {

		// Setup Derby system properties
		System.setProperty("derby.system.durability", "test");
		if(databaseLocation!=null) System.setProperty("derby.system.home", databaseLocation);

		connectionString = "jdbc:derby:"+databaseName+";create=true";

		// Start Derby
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(connectionString);

			// check if the table exists
			ResultSet adrSearch = connection.getMetaData().getTables(null, null, "adr", null);
			if(!adrSearch.next()){
				createADRTable();
				adrExisted = false;
			}

			// check if the table exists
			ResultSet bblSearch = connection.getMetaData().getTables(null, null, "bbl", null);
			if(!bblSearch.next()){
				createBBLTable();
				bblExisted = false;
			}


			insertADR = connection.prepareStatement("INSERT INTO ADR VALUES(?, ?, ?, ?, ?," +
			"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			insertBBL = connection.prepareStatement("INSERT INTO BBL VALUES(?, ?, ?, ?, ?," +
			"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			findADRByBoroughBlockLot = connection.prepareStatement("SELECT * FROM adr WHERE boro = ? AND block = ? AND lot = ?");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean tablesWereAlreadyCreated(){
		return adrExisted && bblExisted;
	}

	private void createADRTable() throws IOException, SQLException{
		Statement createTables = connection.createStatement();

		StringBuilder createTablesString = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLoader.loadResource("/sql/create_adr_table.sql").openStream()));

		while(reader.ready()){
			String thisLine = reader.readLine();
			if(!thisLine.contains("--")){
				createTablesString.append(thisLine);
			}
		}
		reader.close();

		createTables.execute(createTablesString.toString());
	}

	private void createBBLTable() throws IOException, SQLException{
		Statement createTables = connection.createStatement();
		StringBuilder createTablesString = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLoader.loadResource("/sql/create_bbl_table.sql").openStream()));

		while(reader.ready()){
			String thisLine = reader.readLine();
			if(!thisLine.contains("--")){
				createTablesString.append(thisLine);
			}
		}
		reader.close();

		createTables.execute(createTablesString.toString());
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

	/* (non-Javadoc)
	 * @see net.skyebook.padloader.database.DatabaseInterface#addRecord(net.skyebook.padloader.record.ADRRecord)
	 */
	@Override
	public void addRecord(ADRRecord record) {
		try {
			insertADR.setShort(1, record.getBoro());
			insertADR.setInt(2, record.getBlock());
			insertADR.setInt(3, record.getLot());
			insertADR.setInt(4, record.getBin());
			insertADR.setString(5, record.getLhnd());
			insertADR.setString(6, record.getLhns());
			insertADR.setString(7, ""+record.getLcontpar());
			insertADR.setString(8, ""+record.getLsos());
			insertADR.setString(9, record.getHhnd());
			insertADR.setString(10, record.getHhns());
			insertADR.setString(11, ""+record.getHcontpar());
			insertADR.setString(12, ""+record.getHsos());
			insertADR.setShort(13, record.getScboro());
			insertADR.setInt(14, record.getSc5());
			insertADR.setShort(15, record.getSclgc());
			insertADR.setString(16, record.getStname());
			insertADR.setString(17, ""+record.getAddrtype());
			insertADR.setInt(18, record.getRealb7sc());
			insertADR.setString(19, createShortArray(record.getValidlgcs()));
			insertADR.setShort(20, record.getParity());
			insertADR.setLong(21, record.getB10sc());
			insertADR.setInt(22, record.getSegid());
			insertADR.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see net.skyebook.padloader.database.DatabaseInterface#addRecord(net.skyebook.padloader.record.BBLRecord)
	 */
	@Override
	public void addRecord(BBLRecord record) {
		try {
			insertBBL.setShort(1, record.getLoboro());
			insertBBL.setInt(2, record.getLoblock());
			insertBBL.setInt(3, record.getLolot());
			insertBBL.setShort(4, record.getLobblscc());
			insertBBL.setShort(5, record.getHiboro());
			insertBBL.setInt(6, record.getHiblock());
			insertBBL.setInt(7, record.getHilot());
			insertBBL.setShort(8, record.getHibblscc());
			insertBBL.setShort(9, record.getBoro());
			insertBBL.setInt(10, record.getBlock());
			insertBBL.setInt(11, record.getLot());
			insertBBL.setShort(12, record.getBblscc());
			insertBBL.setShort(13, record.getBillboro());
			insertBBL.setInt(14, record.getBillblock());
			insertBBL.setInt(15, record.getBilllot());
			insertBBL.setShort(16, record.getBillbblscc());
			insertBBL.setString(17, ""+record.getCondoflag());
			insertBBL.setString(18, record.getCondonum());
			insertBBL.setInt(19, record.getCoopnum());
			insertBBL.setShort(20, record.getNumbf());
			insertBBL.setInt(21, record.getNumaddr());
			insertBBL.setString(22, ""+record.getVacant());
			insertBBL.setString(23, ""+record.getInterior());
			insertBBL.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String createShortArray(short[] shortArray){
		if(shortArray==null) return "0:";

		StringBuilder stringArray = new StringBuilder();
		stringArray.append(shortArray.length+":");
		for(int i=0; i<shortArray.length; i++){
			stringArray.append(shortArray[i]+";");
		}
		return stringArray.toString();
	}

	private static short[] extractShortArray(String stringArray){
		// get the size of the array
		short[] shortArray = new short[Integer.parseInt(stringArray.substring(0, stringArray.indexOf(":")))];
		stringArray = stringArray.substring(stringArray.indexOf(":")+1);
		for(int i=0; i<shortArray.length; i++){
			short value = Short.parseShort(stringArray.substring(0, stringArray.indexOf(";")));
			shortArray[i] = value;
			stringArray = stringArray.substring(stringArray.indexOf(";")+1);
		}

		return shortArray;
	}

	/*
	 * (non-Javadoc)
	 * @see net.skyebook.padloader.database.DatabaseInterface#findADRRecord(int, int, int)
	 */
	@Override
	public List<ADRRecord> findADRRecord(int borough, int block, int lot) {
		ArrayList<ADRRecord> records = new ArrayList<ADRRecord>();
		try {
			findADRByBoroughBlockLot.setInt(1, borough);
			findADRByBoroughBlockLot.setInt(2, block);
			findADRByBoroughBlockLot.setInt(3, lot);
			ResultSet rs = findADRByBoroughBlockLot.executeQuery();
			while(rs.next()){
				

				// add the record to the list of records
				records.add(createADRFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}
	
	private ADRRecord createADRFromResultSet(ResultSet rs) throws SQLException{
		// create the record
		ADRRecord record = new ADRRecord();

		// load the record with data
		record.setBoro(rs.getShort("boro"));
		record.setBlock(rs.getInt("block"));
		record.setLot(rs.getInt("lot"));
		record.setBin(rs.getInt("bin"));
		record.setLhnd(rs.getString("lhnd"));
		record.setLhns(rs.getString("lhns"));
		record.setLcontpar(rs.getString("lcontpar").charAt(0));
		record.setLsos(rs.getString("lsos").charAt(0));
		record.setHhnd(rs.getString("hhnd"));
		record.setHhns(rs.getString("hhns"));
		record.setHcontpar(rs.getString("hcontpar").charAt(0));
		record.setHsos(rs.getString("hsos").charAt(0));
		record.setScboro(rs.getShort("scboro"));
		record.setSc5(rs.getInt("sc5"));
		record.setSclgc(rs.getShort("sclgc"));
		record.setStname(rs.getString("stname"));
		record.setAddrtype(rs.getString("addrtype").charAt(0));
		record.setRealb7sc(rs.getInt("realb7sc"));
		record.setValidlgcs(extractShortArray(rs.getString("validlgcs")));
		record.setParity(rs.getShort("parity"));
		record.setB10sc(rs.getLong("b10sc"));
		record.setSegid(rs.getInt("segid"));
		
		return record;
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
