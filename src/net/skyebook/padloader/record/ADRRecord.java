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
package net.skyebook.padloader.record;

/**
 * Features of a BBL
 * @author Skye Book
 *
 */
public class ADRRecord extends Record{
	
	public enum Fields{
		boro,
		block,
		lot,
		bin,
		lhnd,
		lhns,
		lcontpar,
		lsos,
		hhnd,
		hhns,
		hcontpar,
		hsos,
		scboro,
		sc5,
		sclgc,
		stname,
		addrtype,
		realb7sc,
		validlgcs,
		parity,
		b10sc,
		segid
	}
	
	private short boro;
	private int block;
	private int lot;
	private int bin;
	private String lhnd;
	private String lhns;
	private char lcontpar;
	private char lsos;
	private String hhnd;
	private String hhns;
	private char hcontpar;
	private char hsos;
	private short scboro;
	private int sc5;
	private short sclgc;
	private String stname;
	private short addrtype;
	private int realb7sc;
	private short[] validlgcs;
	private short parity;
	private long b10sc;
	private int segid;

	/**
	 * 
	 */
	public ADRRecord() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the boro
	 */
	public short getBoro() {
		return boro;
	}

	/**
	 * @param boro the boro to set
	 */
	public void setBoro(short boro) {
		this.boro = boro;
	}

	/**
	 * @return the block
	 */
	public int getBlock() {
		return block;
	}

	/**
	 * @param block the block to set
	 */
	public void setBlock(int block) {
		this.block = block;
	}

	/**
	 * @return the lot
	 */
	public int getLot() {
		return lot;
	}

	/**
	 * @param lot the lot to set
	 */
	public void setLot(int lot) {
		this.lot = lot;
	}

	/**
	 * @return the bin
	 */
	public int getBin() {
		return bin;
	}

	/**
	 * @param bin the bin to set
	 */
	public void setBin(int bin) {
		this.bin = bin;
	}

	/**
	 * @return the lhnd
	 */
	public String getLhnd() {
		return lhnd;
	}

	/**
	 * @param lhnd the lhnd to set
	 */
	public void setLhnd(String lhnd) {
		this.lhnd = lhnd;
	}

	/**
	 * @return the lhns
	 */
	public String getLhns() {
		return lhns;
	}

	/**
	 * @param lhns the lhns to set
	 */
	public void setLhns(String lhns) {
		this.lhns = lhns;
	}

	/**
	 * @return the lcontpar
	 */
	public char getLcontpar() {
		return lcontpar;
	}

	/**
	 * @param lcontpar the lcontpar to set
	 */
	public void setLcontpar(char lcontpar) {
		this.lcontpar = lcontpar;
	}

	/**
	 * @return the lsos
	 */
	public char getLsos() {
		return lsos;
	}

	/**
	 * @param lsos the lsos to set
	 */
	public void setLsos(char lsos) {
		this.lsos = lsos;
	}

	/**
	 * @return the hhnd
	 */
	public String getHhnd() {
		return hhnd;
	}

	/**
	 * @param hhnd the hhnd to set
	 */
	public void setHhnd(String hhnd) {
		this.hhnd = hhnd;
	}

	/**
	 * @return the hhns
	 */
	public String getHhns() {
		return hhns;
	}

	/**
	 * @param hhns the hhns to set
	 */
	public void setHhns(String hhns) {
		this.hhns = hhns;
	}

	/**
	 * @return the hcontpar
	 */
	public char getHcontpar() {
		return hcontpar;
	}

	/**
	 * @param hcontpar the hcontpar to set
	 */
	public void setHcontpar(char hcontpar) {
		this.hcontpar = hcontpar;
	}

	/**
	 * @return the hsos
	 */
	public char getHsos() {
		return hsos;
	}

	/**
	 * @param hsos the hsos to set
	 */
	public void setHsos(char hsos) {
		this.hsos = hsos;
	}

	/**
	 * @return the scboro
	 */
	public short getScboro() {
		return scboro;
	}

	/**
	 * @param scboro the scboro to set
	 */
	public void setScboro(short scboro) {
		this.scboro = scboro;
	}

	/**
	 * @return the sc5
	 */
	public int getSc5() {
		return sc5;
	}

	/**
	 * @param sc5 the sc5 to set
	 */
	public void setSc5(int sc5) {
		this.sc5 = sc5;
	}

	/**
	 * @return the sclgc
	 */
	public short getSclgc() {
		return sclgc;
	}

	/**
	 * @param sclgc the sclgc to set
	 */
	public void setSclgc(short sclgc) {
		this.sclgc = sclgc;
	}

	/**
	 * @return the stname
	 */
	public String getStname() {
		return stname;
	}

	/**
	 * @param stname the stname to set
	 */
	public void setStname(String stname) {
		this.stname = stname;
	}

	/**
	 * @return the addrtype
	 */
	public short getAddrtype() {
		return addrtype;
	}

	/**
	 * @param addrtype the addrtype to set
	 */
	public void setAddrtype(short addrtype) {
		this.addrtype = addrtype;
	}

	/**
	 * @return the realb7sc
	 */
	public int getRealb7sc() {
		return realb7sc;
	}

	/**
	 * @param realb7sc the realb7sc to set
	 */
	public void setRealb7sc(int realb7sc) {
		this.realb7sc = realb7sc;
	}

	/**
	 * @return the validlgcs
	 */
	public short[] getValidlgcs() {
		return validlgcs;
	}

	/**
	 * @param validlgcs the validlgcs to set
	 */
	public void setValidlgcs(short[] validlgcs) {
		this.validlgcs = validlgcs;
	}

	/**
	 * @return the parity
	 */
	public short getParity() {
		return parity;
	}

	/**
	 * @param parity the parity to set
	 */
	public void setParity(short parity) {
		this.parity = parity;
	}

	/**
	 * @return the b10sc
	 */
	public long getB10sc() {
		return b10sc;
	}

	/**
	 * @param b10sc the b10sc to set
	 */
	public void setB10sc(long b10sc) {
		this.b10sc = b10sc;
	}

	/**
	 * @return the segid
	 */
	public int getSegid() {
		return segid;
	}

	/**
	 * @param segid the segid to set
	 */
	public void setSegid(int segid) {
		this.segid = segid;
	}
	
}
