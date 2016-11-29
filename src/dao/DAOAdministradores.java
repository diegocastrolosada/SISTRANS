package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Administrador;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */


public class DAOAdministradores 
{
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Método constructor que crea DAOAerolineas
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOAdministradores() 
	{
		recursos = new ArrayList<Object>();
	}

	/**
	 * Método que cierra todos los recursos que estan en el arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
	public void cerrarRecursos() 
	{
		for(Object ob : recursos)
		{
			if(ob instanceof PreparedStatement)
			try 
			{
					((PreparedStatement) ob).close();
			} 
			catch (Exception ex) 
			{
					ex.printStackTrace();
			}
		}
	}

	/**
	 * Método que inicializa la connection del DAO a la base de datos con la conexión que entra como parámetro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con)
	{
		this.conn = con;
	}


	/**
	 * Método que, usando la conexión a la base de datos, saca todos los videos de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM ADMINISTRADORES;
	 * @return Arraylist con los administradores de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Administrador> darAdministradores() throws SQLException, Exception 
	{
		ArrayList<Administrador> administradores = new ArrayList<Administrador>();

		String sql = "SELECT * FROM ISIS2304A091620.ADMINISTRADORES";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int idAdmin = Integer.parseInt(rs.getString("ID_ADMIN"));
			String nombre = rs.getString("NOMBRE");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			administradores.add(new Administrador(idAdmin, nombre, correo, rol));

		}
		return administradores;
	}


	/**
	 * Método que busca el/los administradores con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los administradores a buscar
	 * @return ArrayList con las administradores encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Administrador> buscarAdministradorPorNombre(String nombre) throws SQLException, Exception
	{
		ArrayList<Administrador> administradores = new ArrayList<Administrador>();

		String sql = "SELECT * FROM ISIS2304A091620.ADMINISTRADORES WHERE NOMBRE ='" + nombre + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int idAdmin = Integer.parseInt(rs.getString("ID_ADMIN"));
			String nombre2 = rs.getString("NOMBRE");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			administradores.add(new Administrador(idAdmin, nombre, correo, rol));
		}
		return administradores;
	}
	
	/**
	 * Método que busca el/los administradores con el id que entra como parámetro.
	 * @param id - id de el/los administradores a buscar
	 * @return ArrayList con las administradores encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Administrador> buscarAdministradorPorId(int idAdmin) throws SQLException, Exception
	{
		ArrayList<Administrador> administradores = new ArrayList<Administrador>();

		String sql = "SELECT * FROM ISIS2304A091620.ADMINISTRADORES WHERE ID_ADMIN ='" + idAdmin + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int idAdmin2 = Integer.parseInt(rs.getString("ID_ADMIN"));
			String nombre = rs.getString("NOMBRE");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			administradores.add(new Administrador(idAdmin, nombre, correo, rol));
		}

		return administradores;
	}

	
	/**
	 * Método que agrega el administrador que entra como parámetro a la base de datos.
	 * @param administrador - el administrador a agregar. administrador !=  null
	 * <b> post: </b> se ha agregado el administrador a la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que el administrador baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el administrador a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addAdministrador(Administrador administrador) throws SQLException, Exception 
	{
		String sql = "INSERT ISIS2304A091620.ADMINISTRADORES VALUES ('";
		sql += administrador.getIdAdmin() + "','";
		sql += administrador.getNombre() + "','";
		sql += administrador.getCorreo() + "'',";
		sql += administrador.getRol() + "')";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		String sql2 = "COMMIT";
		System.out.println("SQL stmt:" + sql2);

		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();
	}

	/**
	 * Método que actualiza el administrador que entra como parámetro en la base de datos.
	 * @param administrador - el administrador a actualizar. aerolinea !=  null
	 * <b> post: </b> se ha actualizado el administrador en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el administrador.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateAdministrador(Administrador administrador) throws SQLException, Exception 
	{
		String sql = "UPDATE ISIS2304A091620.ADMINISTRADORES SET ";
		sql +=  "idAdmin='" + administrador.getIdAdmin() + ",'";
		sql +=  "nombre='" +  administrador.getNombre() + "',";
		sql +=  "correo='" +   administrador.getCorreo() + "',";
		sql += "rol='" + administrador.getRol();
		sql += " WHERE ID_ADMIN = " + administrador.getIdAdmin();
		

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		String sql2 = "COMMIT";
		System.out.println("SQL stmt:" + sql2);

		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();
	}

	/**
	 * Método que elimina el administrador que entra como parámetro en la base de datos.
	 * @param administrador - el administrador a eliminar. administrador !=  null
	 * <b> post: </b> se ha borrado la aerolinea en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el administrador.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteAdministrador(Administrador administrador) throws SQLException, Exception 
	{
		String sql = "DELETE FROM ISIS2304A091620.ADMINISTRADORES";
		sql += " WHERE ID_ADMIN = " + administrador.getIdAdmin();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		String sql2 = "COMMIT";
		System.out.println("SQL stmt:" + sql2);

		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();
	}
	
}
