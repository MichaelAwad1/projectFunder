package de.unidue.inf.is.stores;
import java.sql.*;
import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import de.unidue.inf.is.domain.Account;
import de.unidue.inf.is.domain.Comment;
import de.unidue.inf.is.domain.Project;
import de.unidue.inf.is.domain.Support;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.interfaces.IProjects;
import de.unidue.inf.is.utils.DBUtil;

public final class UserStore implements Closeable, IProjects {

    private Connection connection;
    private boolean complete;
    private static UserStore instance;

    public UserStore() throws StoreException {
        try {
        	
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }
    public void complete() {
        complete = true;
    }

    public static UserStore getInstance() {
		if (instance == null) {
			instance = new UserStore();
		}

		return instance;
	}
    
    
    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                if (complete) {
                    connection.commit();
                }
                else {
                    connection.rollback();
                }
            }
            catch (SQLException e) {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }
//done
    @Override
    public ArrayList<User> getAllUsers() {
    	ArrayList<User> result = new ArrayList<User>();
    	String sqlSelect = "SELECT u.email , u.name , u.profiledescription , a.balance , a.secretNumber from dbp011.User u JOIN dbp011.Account a ON u.email = a.owner ORDER BY u.email";
    	try(PreparedStatement st = connection.prepareStatement(sqlSelect)){
    		try(ResultSet rs = st.executeQuery()){
    			while(rs.next()) {
    				Account account = new Account();
    				account.setBalance(rs.getDouble(4));
    				account.setSecretNumber(rs.getString(5));
    		    	User temp = new User(rs.getString(1),rs.getString(2), rs.getString(3), account);
    		    	result.add(temp);
    			}
    			return result;
    		}
    	} catch (SQLException e) {
    		 throw new StoreException(e);
    	}
    }
    //done
	public ArrayList<Project> getAllProjects(String status) {
		ArrayList<Project> result = new ArrayList<Project>();
		double totalDonations = 0 ;
		String sqlSelect = "SELECT p.projectID, p.title, p.description, p.status, p.fundinglimit, "
				+ "p.creator, p.pre, p.category, u.name , c.icon FROM dbp011.Project p JOIN dbp011.User u "
				+ "ON u.email = p.creator JOIN dbp011.Category c On p.category = c.categoryID WHERE p.status = ? "
						+ "ORDER BY p.title";
		 try(PreparedStatement st = connection.prepareStatement(sqlSelect)){
			 st.setString(1, status);
			 try(ResultSet rs1 = st.executeQuery())  {
				 System.out.println("11");
				 //rs1.next();
				 while (rs1.next()) {
					 System.out.println("11");
					 String sqlSum = "Select sum(s.amount) from dbp011.Support s Join dbp011.Project p"
								+ " ON s.project = p.projectID WHERE p.projectID = ?";
					 try(PreparedStatement st2 = connection.prepareStatement(sqlSum)){
						 st2.setInt(1, +rs1.getInt(1));
						 try(ResultSet rs2 = st2.executeQuery()){
							 if(rs2.next()) {
								 if(rs2.getBigDecimal(1) != null)
								 {
									 BigDecimal bg = rs2.getBigDecimal(1);
									 totalDonations = bg.doubleValue();
								 }
								 else {
									 totalDonations = 0;
								 }							  
							 }
						 }
					 }	 		
						Project temp = new Project(rs1.getInt(1), 
								rs1.getString(2), rs1.getString(3), rs1.getString(4),
								rs1.getDouble(5), rs1.getString(6) , rs1.getInt(7) , 
								rs1.getInt(8) , totalDonations , rs1.getString(9), rs1.getString(10));
									result.add(temp);				
					}
			 }
			 catch (SQLException e) {
		           throw new StoreException(e);
		        }	 
	        }
	        catch (SQLException e) {
	           throw new StoreException(e);
	        }		
		return result;
	}
	//done
	@Override
	public ArrayList<Project> getProjectsByTitle(String title) {
		ArrayList<Project> result = new ArrayList<Project>();
		double totalDonations = 0;
				String sqlSelect = "SELECT p.projectID, p.title, p.description, p.status, p.fundinglimit," + 
				"p.creator, p.pre, p.category, u.name , c.icon FROM dbp011.Project p JOIN dbp011.User u " + 
				"ON u.email = p.creator JOIN dbp011.Category c On p.category = c.categoryID "
				+ "WHERE UPPER(p.title) LIKE UPPER(?) ORDER BY p.title";
		 try( PreparedStatement st = connection.prepareStatement(sqlSelect)){
			 st.setString(1, title+"%");
			 try (ResultSet rs1 = st.executeQuery()){
				 while (rs1.next()) {
					 String sqlSum = "Select sum(s.amount) from dbp011.Support s Join dbp011.Project p"
								+ " ON s.project = p.projectID WHERE p.projectID = ?";
					 try(PreparedStatement st2 = connection.prepareStatement(sqlSum)){
						 st2.setInt(1, +rs1.getInt(1));
						 try(ResultSet rs2 = st2.executeQuery()){
							 if(rs2.next()) {
								 if(rs2.getBigDecimal(1) != null)
								 {
									 BigDecimal bg = rs2.getBigDecimal(1);
									 totalDonations = bg.doubleValue();
								 }
								 else {
									 totalDonations = 0;
								 }							  
							 }
						 }
					 }
					 		
						Project temp = new Project(rs1.getInt(1), 
								rs1.getString(2), rs1.getString(3), rs1.getString(4),
								rs1.getDouble(5), rs1.getString(6) , rs1.getInt(7) , 
								rs1.getInt(8) , totalDonations , rs1.getString(9), rs1.getString(10));
									result.add(temp);	
									
					}
			 }			 
	        }
	        catch (SQLException e) {
	           throw new StoreException(e);
	        }		
		return result;
		
	}
	
	//done
	@Override
	public Project getParentProject(int pre) {
		
		double totalDonations = 0;
		String sqlSelect = "SELECT p.projectID, p.title, p.description, p.status, p.fundinglimit," + 
						" p.creator, p.pre, p.category, u.name, c.icon FROM dbp011.Project p JOIN dbp011.User u" + 
						" ON u.email = p.creator JOIN dbp011.Category c On p.category = c.categoryID"
						+ " WHERE projectID = ?";
		Project parent = null;
		 try( PreparedStatement st = connection.prepareStatement(sqlSelect)){
			 st.setInt(1, pre);
			 try (ResultSet rs1 = st.executeQuery()){
				 while (rs1.next()) {
					 String sqlSum = "Select sum(s.amount) from dbp011.Support s Join dbp011.Project p"
								+ " ON s.project = p.projectID WHERE p.projectID = ?";
					 try(PreparedStatement st2 = connection.prepareStatement(sqlSum)){
						 st2.setInt(1, rs1.getInt(1));
						 try(ResultSet rs2 = st2.executeQuery()){
							 if(rs2.next()) {
								 if(rs2.getBigDecimal(1) != null)
								 {
									 BigDecimal bg = rs2.getBigDecimal(1);
									 totalDonations = bg.doubleValue();
								 }
								 else {
									 totalDonations = 0;
								 }							  
							 }
						 }
					 }					 		
						 parent = new Project(rs1.getInt(1), 
								rs1.getString(2), rs1.getString(3), rs1.getString(4),
								rs1.getDouble(5), rs1.getString(6) , rs1.getInt(7) , 
								rs1.getInt(8) , totalDonations , rs1.getString(9), rs1.getString(10));										
					}
			 }			 
	        }
	        catch (SQLException e) {
	           throw new StoreException(e);
	        }
		return parent;		
	}
	
	//done
	@Override
	public ArrayList<Project> getAllUserProjects(String email) {
		ArrayList<Project> result = new ArrayList<Project>();
		double totalDonations = 0;
		String sqlSelect = "SELECT p.projectID, p.title, p.description, p.status, p.fundinglimit," + 
						"p.creator, p.pre, p.category, u.name, c.icon FROM dbp011.Project p JOIN dbp011.User u" + 
						" ON u.email = p.creator JOIN dbp011.Category c On p.category = c.categoryID "
						+ "WHERE p.creator = ? ORDER BY p.title";
		 try( PreparedStatement st = connection.prepareStatement(sqlSelect)){
			 st.setString(1,email);
			 try (ResultSet rs1 = st.executeQuery()){
				 while (rs1.next()) {
					 String sqlSum = "Select sum(s.amount) from dbp011.Support s Join dbp011.Project p"
								+ " ON s.project = p.projectID WHERE p.projectID = ?";
					 try( PreparedStatement st2 = connection.prepareStatement(sqlSum)){
						 st2.setInt(1, rs1.getInt(1));
						 try(ResultSet rs2 = st2.executeQuery()){
							 if(rs2.next()) {
								 if(rs2.getBigDecimal(1) != null)
								 {
									 BigDecimal bg = rs2.getBigDecimal(1);
									 totalDonations = bg.doubleValue();
								 }
								 else {
									 totalDonations = 0;
								 }							  
							 }
						 }
					 }
					 		
						Project temp = new Project(rs1.getInt(1), 
								rs1.getString(2), rs1.getString(3), rs1.getString(4),
								rs1.getDouble(5), rs1.getString(6) , rs1.getInt(7) , 
								rs1.getInt(8) , totalDonations , rs1.getString(9), rs1.getString(10));
									result.add(temp);	
									
					}
			 }			 
	        }
	        catch (SQLException e) {
	           throw new StoreException(e);
	        }		
		return result;
	}
	//done
	@Override
	public ArrayList<Project> getAllSupportedProjects(String email) {
		ArrayList<Project> result = new ArrayList<Project>();
		double totalDonations = 0;
		User user = getUserInformation(email);
		String sqlSelect = "SELECT p.projectID, p.title, p.description, p.status, p.fundinglimit," + 
						"p.creator, p.pre, p.category, c.icon FROM dbp011.Project p JOIN dbp011.Category c ON p.category = c.categoryID"
						+ " JOIN dbp011.Support s on s.project = p.projectid WHERE s.donor = ?"
								+ " ORDER BY s.amount DESC, s.project";
		 try(PreparedStatement st = connection.prepareStatement(sqlSelect)){
			 st.setString(1, email);
			 try (ResultSet rs1 = st.executeQuery()){
				 while (rs1.next()) {
					 String sqlSum = "Select sum(s.amount) from dbp011.Support s Join dbp011.Project p"
								+ " ON s.project = p.projectID WHERE p.projectID = ?";
					 try(PreparedStatement st2 = connection.prepareStatement(sqlSum)){
						 st2.setInt(1, rs1.getInt(1));
						 try(ResultSet rs2 = st2.executeQuery()){
							 if(rs2.next()) {
								 if(rs2.getBigDecimal(1) != null)
								 {
									 BigDecimal bg = rs2.getBigDecimal(1);
									 totalDonations = bg.doubleValue();
								 }
								 else {
									 totalDonations = 0;
								 }							  
							 }
						 }
					 }
						Project temp = new Project(rs1.getInt(1), 
								rs1.getString(2), rs1.getString(3), rs1.getString(4),
								rs1.getDouble(5), rs1.getString(6) , rs1.getInt(7) , 
								rs1.getInt(8) , totalDonations ,user.getName(), rs1.getString(9));
						result.add(temp);										
					}
			 }			 
	        }
	        catch (SQLException e) {
	           throw new StoreException(e);
	        }		
		return result;
	}
	
	//done //not needed
	public ArrayList<Comment> getAllUserComments(User user) {
		ArrayList<Comment> result = new ArrayList<Comment>();
		String sqlSelect = "SELECT c.commentID , c.text , c.date , c.commentStatus , w.project"
				+ " FROM dbp011.Comment c JOIN dbp011.Write w ON c.commentID = w.comment "
				+ "JOIN dbp011.User u ON u.email = w. user where u.email = ?" ;

		 try( PreparedStatement ps1 = connection.prepareStatement(sqlSelect)){
			 ps1.setString(1, user.getEmail());
			 try (ResultSet rs1 = ps1.executeQuery()){
				 while (rs1.next()) {					
					Project project = new Project();
					project.setProjectID(rs1.getInt(5));
						Comment temp = new Comment(rs1.getInt(1), rs1.getString(2), rs1.getTimestamp(3),
								rs1.getString(4), rs1.getInt(5) , user.getName());
						
						result.add(temp);
					}
			 }			 
	        }
	        catch (SQLException e) {
	           throw new StoreException(e);
	        }		
		return result;
		
	}
	//done
		public ArrayList<Support> getAllUserSupports(String email) {
			ArrayList<Support> result = new ArrayList<Support>();
			User user = getUserInformation(email);
			String sqlSelect = "SELECT * FROM dbp011.Support s WHERE s.donor = ?"
					+ " ORDER BY s.amount DESC, s.project";

			 try(PreparedStatement st = connection.prepareStatement(sqlSelect)){
				 st.setString(1, email);
				 try (ResultSet rs1 = st.executeQuery()){
					 while (rs1.next()) {					
			Support temp = new Support(rs1.getString(1), rs1.getInt(2), rs1.getDouble(3),
					rs1.getString(4), user.getName());	
							result.add(temp);
						}
				 }			 
		        }
		        catch (SQLException e) {
		           throw new StoreException(e);
		        }		
			return result;
		}


//done
	public ArrayList<Comment> getAllProjectComments( int projectID) {
		
		ArrayList<Comment> result = new ArrayList<Comment>();
		String sqlSelect = "SELECT c.commentID , c.text , c.date , c.commentStatus , u.name "
				+ "FROM dbp011.Comment c JOIN dbp011.Write w ON c.commentID = w.comment "
				+ "JOIN dbp011.User u ON u.email = w. user WHERE w.project = ? ORDER BY c.date DESC";
		
		 try(PreparedStatement st = connection.prepareStatement(sqlSelect)){ 
			 st.setInt(1, projectID);
			 try(ResultSet rs1 = st.executeQuery())  {
				 
				 while (rs1.next()) {
				
					 Comment temp = new Comment(rs1.getInt(1), rs1.getString(2),
						rs1.getTimestamp(3), rs1.getString(4), projectID ,rs1.getString(5));	
						result.add(temp);
					}
			 } 		 
	        }
	        catch (SQLException e) {
	           throw new StoreException(e);
	        }		
		return result;
	}
	
	//done
	@Override
	public ArrayList<Support> getAllProjectDonations(int projectID) {
		
		ArrayList<Support> result = new ArrayList<Support>();
		String sqlSelect = "SELECT s.donor , s.project , s.amount , s.donationstatus, u.name "
						+ "FROM dbp011.Support s JOIN dbp011.User u ON s.donor = u.email "
						+ "Where s.project = ? ORDER BY s.amount DESC";
		 try(PreparedStatement st = connection.prepareStatement(sqlSelect)){
			 st.setInt(1, projectID);
			 try (ResultSet rs1 = st.executeQuery()){
				 
				 while (rs1.next()) {					
				
					  Support temp = new Support(rs1.getString(1), (int)rs1.getShort(2),
								rs1.getBigDecimal(3).doubleValue() , rs1.getString(4), rs1.getString(5));	
						result.add(temp);
					}
			 }			 
	        }
	        catch (SQLException e) {
	           throw new StoreException(e);
	        }		
		return result;
	}
	//done
	@Override
	public boolean saveProject(String title, String description,
			double fundingLimit,String creator, int pre,
			int category) throws StoreException{
		int id = getLastProjectID()+1;
		String sqlInsert;
		
	
			sqlInsert = "INSERT INTO dbp011.Project(projectID, title, description "
					+ ", status , fundinglimit , creator , pre , category )"
					+ " Values (?,?,?,?,?,?,?,?)";
		try (PreparedStatement st = connection.prepareStatement(sqlInsert)) {
			st.setInt(1, id);
			st.setString(2, title);
			st.setString(3, description);
			st.setString(4, "opened");
			st.setDouble(5, fundingLimit);
			st.setString(6, creator);
			if(pre!=0) {
			st.setInt(7, pre);
			}
			else {
				st.setString(7, null);
			}
			st.setInt(8, category);
			
			st.executeUpdate();
			connection.commit();
			return true;
       }
       catch (SQLException e) {
          
           try{
           	connection.rollback();
           }catch(SQLException e2){
           	System.out.println("ErRoR");
           	return false;
           } throw new StoreException(e);
       }
	}
	
	
	//done
	public boolean editProject(int projectID, String title, String description, String status,
			double fundingLimit,String creator , int pre,int category) throws StoreException{
		String sqlUpdate;
		if(status.equalsIgnoreCase("opened")){
			
				sqlUpdate = "UPDATE dbp011.Project SET title = ? , description = ? ,"
						+ " fundinglimit = ? , pre = ? ,"
						+ " category = ? WHERE projectID = ?";
		try (PreparedStatement st = connection.prepareStatement(sqlUpdate)) {
			
			st.setString(1, title);
			st.setString(2, description);
			st.setDouble(3, fundingLimit);
			if(pre!=0) {
			st.setInt(4, pre);
			}
			else {
				st.setString(4, null);
			}
			st.setInt(5, category);
			st.setInt(6, projectID);
			st.executeUpdate();
			connection.commit();
			return true;
       }
       catch (SQLException e) {
          
           try{
           	connection.rollback();
           }catch(SQLException e2){
           	System.out.println("ErRoR");
           	
           } throw new StoreException(e);
       }
		}
		else
			return false;
	}
	//done
	public boolean saveComment(String text, String commentStatus,
			int projectID , String email) throws StoreException{
		int id = getLastCommentID()+1; 
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		String sqlInsert1 = "INSERT INTO dbp011.Comment(commentID , text ,date, CommentStatus)"
				+ " Values (?,?,?,?)";
		String sqlInsert2 = "INSERT INTO dbp011.Write(user , project , comment)"
				+ " Values (?,?,?)";


		try (PreparedStatement ps1 = connection.prepareStatement(sqlInsert1);
				PreparedStatement ps2 = connection.prepareStatement(sqlInsert2)) {
			ps1.setInt(1, id);
			ps1.setString(2, text);
			ps1.setTimestamp(3, timestamp);
			ps1.setString(4, commentStatus);
			ps1.executeUpdate();
			ps2.setString(1, email);
			ps2.setInt(2, projectID);
			ps2.setInt(3, id);
			ps2.executeUpdate();
			connection.commit();
			return true;
       }
       catch (SQLException e) {
          
           try{
           	connection.rollback();
           }catch(SQLException e2){
           	System.out.println("ErRoR");
           	return false;
           } throw new StoreException(e);
       }
	}

	//done
	public boolean saveSupport(String donor, int project, double amount,
			String donationStatus) throws StoreException{
		Project p = getParentProject(project);
		int flag = 0;
		User user = getUserInformation(donor);	
		ArrayList<Support> supports = getAllUserSupports(donor);
		for(int i = 0; i< supports.size(); i++) {
			if(supports.get(i).getProject() == project) {
				flag = 1;
			}
		}
		double originalBalance = user.getAccount().getBalance();	
		if(originalBalance< amount || p.getStatus().equalsIgnoreCase("closed") || flag ==1) {
			return false;
		}
		else {
		String sqlInsert = "INSERT INTO dbp011.Support(donor , project , amount , donationStatus) "
				+ "Values (?,?,?,?)";
		double balance = originalBalance - amount;
	
		String sqlUpdate = "UPDATE dbp011.Account SET balance = ? WHERE owner = ?"; 
				try (PreparedStatement ps1 = connection.prepareStatement(sqlInsert);
						PreparedStatement ps2 = connection.prepareStatement(sqlUpdate)) {
					
				ps1.setString(1, donor);
				ps1.setInt(2, project);
				ps1.setDouble(3, amount);
				ps1.setString(4, donationStatus);
				ps1.executeUpdate();
			ps2.setDouble(1, balance);
			ps2.setString(2, donor);
			ps2.executeUpdate();
			connection.commit();
			return true;
       }
       catch (SQLException e) {
          
           try{
           	connection.rollback();
           }catch(SQLException e2){
           	System.out.println("ErRoR");
           } throw new StoreException(e);
       }
		}
	}
	//done
	@Override
	public User getUserInformation(String email) {
		String sqlSelect = "SELECT u.email , u.name , u.profiledescription , a.balance"
				+ " FROM  dbp011.User u JOIN dbp011.Account a " + 
				"ON u.email = a.owner WHERE u.email = ?";
		 Account account = new Account();
		User user = null;
		try(PreparedStatement ps1 = connection.prepareStatement(sqlSelect)){
			ps1.setString(1, email);
			try (ResultSet rs1 = ps1.executeQuery()){
		 if (rs1.next()) {
			 account.setBalance(rs1.getDouble(4));
			 			 user = new User(rs1.getString(1), rs1.getString(2), rs1.getString(3), 
			 					 account);
						return user;			
			}
	 }			 
    }
    catch (SQLException e) {
       throw new StoreException(e);
    }
return user;		

	}
	
	
   // done
@Override
public boolean deleteProject(int id) {
	ArrayList<Comment> projectComments = getAllProjectComments(id);
	ArrayList<Support> projectSupports = getAllProjectDonations(id);
	int commentid;
	double amount;
	String donor;
	String updateBalance = "UPDATE dbp011.Account SET balance = ? WHERE owner = ?"; //1
	String sqlDeleteWrite = "DELETE FROM dbp011.Write WHERE project = ?"; //2
	String sqlDeleteSupports = "DELETE FROM dbp011.Support WHERE project = ?"; //3
	String sqlDeleteComment = "DELETE FROM dbp011.Comment WHERE commentID = ?"; //4
	String updatePre = "UPDATE dbp011.Project SET pre = "+null +" WHERE pre = ?";  //5
	String sqlDeleteProject = "DELETE FROM dbp011.Project WHERE projectID = ?"; //6
	
	try(PreparedStatement ps1 = connection.prepareStatement(updateBalance);
		PreparedStatement ps2 = connection.prepareStatement(sqlDeleteWrite);
		PreparedStatement ps3 = connection.prepareStatement(sqlDeleteSupports);
		PreparedStatement ps4 = connection.prepareStatement(sqlDeleteComment);
		PreparedStatement ps5 = connection.prepareStatement(updatePre);
		PreparedStatement ps6 = connection.prepareStatement(sqlDeleteProject);){
			
		for(int i = 0 ; i<projectSupports.size(); i++) {
			amount = projectSupports.get(i).getAmount();	
			donor = projectSupports.get(i).getDonor();	
			double originalBalance = getUserInformation(donor).getAccount().getBalance();
			double newBalance = amount + originalBalance;
			ps1.setDouble(1, newBalance);
			ps1.setString(2, donor);
			ps1.executeUpdate();
		}
		ps2.setInt(1, id);
		ps2.executeUpdate();
		ps3.setInt(1, id);
		ps3.executeUpdate();
	for(int i = 0; i< projectComments.size(); i++) {
		commentid = projectComments.get(i).getCommentID();
		ps4.setInt(1, commentid);
		ps4.executeUpdate();
	}
	
		ps5.setInt(1, id);
		ps5.executeUpdate();
		
		ps6.setInt(1, id);
		ps6.executeUpdate();
		connection.commit();
		return true;
	
	} 
   catch (SQLException e) {
      
       try{
       	connection.rollback();
       }catch(SQLException e2){
       	System.out.println("ErRoR");
       	return false;
       } throw new StoreException(e);
   }
}

public int getLastProjectID() {
	String sqlQuery = "select max(projectid) as max from dbp011.Project";
	try (Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery)) {
					
	     if (rs.next()) {
				int lastID = rs.getInt("max");
				return lastID;	
			}
	     return 0;
		
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	return 0;
}	

public int getLastCommentID() {
   String sqlQuery = "select max(commentid) as max from dbp011.Comment";
	try (Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery)) {
					
	     if (rs.next()) {
				int lastID=rs.getInt("max");
				return lastID;	
			}
	     return 0;
		
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	return 0;	   
}



}
