/**
 * 
 */
package net.skyebook.padloader.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import net.skyebook.padloader.record.ADRRecord;
import net.skyebook.padloader.record.BBLRecord;
import net.skyebook.padloader.record.Record;

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

	/**
	 * 
	 */
	public DerbyImplementation() {
		connectionString = "jdbc:derby:"+databaseName+";create=true";
		
		// Start Derby
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(connectionString);
			
			insertADR = connection.prepareStatement("INSERT INTO adr VALUES(?, ?, ?, ?, ?," +
					"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see net.skyebook.padloader.database.DatabaseInterface#addRecord(net.skyebook.padloader.record.Record)
	 */
	@Override
	public void addRecord(Record record) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see net.skyebook.padloader.database.DatabaseInterface#findRecord(net.skyebook.padloader.database.ADRQuery)
	 */
	@Override
	public List<ADRRecord> findRecord(ADRQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.skyebook.padloader.database.DatabaseInterface#findRecord(net.skyebook.padloader.database.BBLQuery)
	 */
	@Override
	public List<BBLRecord> findRecord(BBLQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

}
