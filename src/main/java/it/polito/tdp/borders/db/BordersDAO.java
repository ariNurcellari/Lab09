package it.polito.tdp.borders.db;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public void loadAllCountries(Map<Integer, Country> map) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c = new Country(rs.getString("StateNme"), rs.getInt("ccode"), rs.getString("StateAbb"));
				map.put(c.getCod(), c) ;
			}
			
			rs.close();
			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	public List<Country> getVertex(int anno) {
		
		String sql = "SELECT StateNme , CCode, StateAbb "
				+ "FROM contiguity conf, country st "
				+ "WHERE (conf.state1no = st.CCode OR conf.state2no = st.CCode) AND conf.year < '?' "
				+ "GROUP BY st.CCode" ;
		
		List<Country> result = new ArrayList<>() ;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c = new Country(rs.getString("StateNme"), rs.getInt("CCode"), rs.getString("StateAbb"));
				result.add(c) ;
			}
			
			rs.close();
			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		return result ;
}

	public List<Border> getCountryPairs(Map<Integer, Country> map, int anno) {
		 
		String sql = "SELECT conf.state1no AS st1, conf.state2no as st2 "
				+ "FROM contiguity conf, country st "
				+ "WHERE conf.state1no = st.CCode AND conf.conttype = 1 "
				+ "AND conf.state1no > conf.state2no AND conf.year <= '?'" ;
		
		List<Border> result = new ArrayList<>() ;
		
		try {
		
			Connection conn = ConnectDB.getConnection() ; 
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery() ;
			
			while(rs.next()) {
				
				Border b = new Border(map.get(rs.getInt("st1")), map.get(rs.getInt("st2"))) ;
				result.add(b) ;
				
			}
			conn.close();
			st.close();
			rs.close();
			
		}  catch (SQLException e) {
			return null ;
		}
		return result;
	}
}
