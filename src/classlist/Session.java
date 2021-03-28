package classlist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.DaoSession;

public class Session implements DaoSession {
	private int id, duree;
	private String date,formation;

	public Session(int duree, String formation, String date) {
		super();
		this.duree = duree;
		this.formation = formation;
		this.date = date;
	}

	
	
	
	public Session() {
		super();
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getId_formation() {
		return formation;
	}

	public void setId_formation(String formation) {
		this.formation = formation;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	@Override
	public void createSession() {
		// TODO Auto-generated method stub
		try {

			Connexion c = new Connexion();
			java.sql.PreparedStatement statement = c.conn.prepareStatement(
					"INSERT INTO `session`( `date`, `id_formation`, `duree`) VALUES ('" + this.getDate() + "','"
							+ this.getId_formation() + "','" + this.getDuree() + "')");
			statement.executeUpdate();
			System.out.println("ajouté avec succés");
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}

	@Override
	public void updateSession(int ids) {
		// TODO Auto-generated method stub
		try
		{
			Connexion c=new Connexion();
			String sql="UPDATE `session` SET `date`='"+this.getDate()+"',`duree`='"+this.getDuree()+"' WHERE id='"+ids+"';";
					java.sql.PreparedStatement statement =
					c.conn.prepareStatement(sql);
					statement.executeUpdate();
					System.out.println("modifi� avec succ�s");
			
			
		}
		catch(Exception ex)
		{
			
			System.out.println("erreur lors de la modification "+ ex.toString());
			
		}
	}

	@Override
	public void deleteSession(int ids) {
		// TODO Auto-generated method stub
		try {
			Connexion c=new Connexion();
			String sql = "DELETE FROM `session` WHERE `id`=?";
			java.sql.PreparedStatement statement =
			c.conn.prepareStatement(sql); statement.setInt(1,ids);
			
			statement.execute();
			System.out.println("supprim� avec succ�s");
			} catch (SQLException ex) {
				System.out.println("erreur lors de la suppression "+ex.toString());
				
			}

	}

	@Override
	public ResultSet afficheSession() throws SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String query="SELECT session.id, date,prix,session.duree,formation.titre,formation.description FROM `session`,formation WHERE session.formation=formation.titre";
		Connexion c = new Connexion();
		PreparedStatement pst;
		pst = (PreparedStatement) c.conn.prepareStatement(query);
		pst.executeQuery();
		ResultSet rs = (ResultSet) pst.executeQuery();
		return rs;
	}

}
