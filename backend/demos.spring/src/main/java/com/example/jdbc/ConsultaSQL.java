package com.example.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConsultaSQL {

	public void run() {
		List<Actor> listado = new ArrayList<>();
		try (Connection con = JDBCBridged.getConnection()) {
			String sql = "SELECT * FROM actor WHERE first_name LIKE ?";
			try (PreparedStatement cmd = con.prepareStatement(sql)) {
				cmd.setString(1, "P%");
				try (ResultSet rs = cmd.executeQuery()) {
					while (rs.next()) {
						listado.add(new Actor(
								rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getDate(4)
								));
					}
				} catch (SQLException e) {
					System.out.println("ERROR: " + e.getMessage());
				}
			} catch (SQLException e) {
				System.out.println("ERROR: " + e.getMessage());
			}
		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		listado.forEach(System.out::println);
	}
	
	/*
cmdSQL = "delete from ingredientes where idIngrediente = ?";
stm.execute(cmdSQL);

cad
--------------------
1 ->
1 or 1=1
1; DROP TABLE ...
1; EXEC CMDSHELL('FDISK ....
	 */
	public void delete(String cad) {
		try (Connection con = JDBCBridged.getConnection()) {
			String sql = "Delete FROM actor WHERE actor_id = " + cad; // <-- NO HACER NUNCA: IYECCION SQL
			try (Statement cmd = con.createStatement()) {
				cmd.executeUpdate(sql);
				} catch (SQLException e) {
					System.out.println("ERROR: " + e.getMessage());
				}
			} catch (SQLException e) {
				System.out.println("ERROR: " + e.getMessage());
			}
	}
	
//	@Autowired
//	JdbcTemplate jdbcTemplate;
//	
//	public void conPlantilla() {
//		List<Actor> listado = jdbcTemplate.query(
//				"SELECT * FROM actor WHERE first_name LIKE ?",
//				(resultSet, rowNum) -> {
//		            Actor newActor = new Actor();
//		            newActor.setActorId(resultSet.getInt("actor_id"));
//		            newActor.setFirstName(resultSet.getString("first_name"));
//		            newActor.setLastName(resultSet.getString("last_name"));
//		            newActor.setLastUpdate(resultSet.getDate("last_update"));
//		            return newActor;
//		        }, "P%");
//		listado.forEach(System.out::println);
//
//	}
}
