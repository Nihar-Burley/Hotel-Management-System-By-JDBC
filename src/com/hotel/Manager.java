package com.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class Manager {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Connection con=DbConnection.connect();
		PreparedStatement st;
		String query;
		while(true)
		{
			System.out.println("***********************");
			System.out.println("Hotel Management System");
			System.out.println("1. Reserve a room");
			System.out.println("2. View Reservation ");
			System.out.println("3. Get Room Number");
			System.out.println("4. Update Reservation");
			System.out.println("5. Delete Reservation ");
			System.out.println("0. Exit");
			System.out.println("***********************");
			
			int choice=sc.nextInt();
			
			switch(choice)
			{
				case 1:
					sc.nextLine();
					System.out.println("Enter Guest Name");
					String name=sc.nextLine();
					System.out.println("Enter Room  Number");
					int rno=sc.nextInt();
					System.out.println("Enter Contact Number");
					long cno=sc.nextLong();
					
					
					query="Insert into HM_reservation (guest_name, room_number, contact_number) Values (?,?,?)";
						try {
							st=con.prepareStatement(query);
							st.setString(1, name);
							st.setInt(2, rno);
							st.setLong(3, cno);
							
							if(st.executeUpdate()>0)
							{
								System.out.println("Reservation Successful...!");
							}
							else
							{
								System.out.println("Reservation was Not Successful...!");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					break;
					
					
				case 2:
					query="Select * from HM_reservation ";
				
					try {
						Statement stt = con.createStatement();
						ResultSet rs=stt.executeQuery(query);
						
						while(rs.next())
						{
							System.out.println("------------------------");
							System.out.println("ID - "+rs.getInt(1));
							System.out.println("Name - "+rs.getString(2));
							System.out.println("Room No - "+rs.getInt(3));
							System.out.println("Contact No - "+rs.getLong(4));
							System.out.println("Time - "+rs.getTimestamp(5));
							System.out.println("------------------------");
							
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
						
					break;
				case 3:
					System.out.println("Enter Guest ID");
					int guestId=sc.nextInt();
					sc.nextLine();
					System.out.println("Enter Guest Name");
					String gname=sc.nextLine();
					
					query="Select room_number from HM_reservation where reservation_id = ? and guest_name =?";
					try {
						st=con.prepareStatement(query);
						st.setInt(1, guestId);
						st.setString(2, gname);
						ResultSet resultSet=st.executeQuery();
						
						while(resultSet.next()) {
							System.out.println("Room No is -- "+resultSet.getInt(1));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				case 4:
					System.out.println("Enter Reservation ID to update");
					int reservationId=sc.nextInt();
					sc.nextLine();
					System.out.println("Enter new guest name");
					String updateName=sc.nextLine();
					System.out.println("Enter new room number");
					int updateRNo=sc.nextInt();
					
					query="Update HM_reservation set guest_name=? and room_number=? where reservation_id=?";
					try {
						st=con.prepareStatement(query);
						st.setString(1, updateName);
						st.setInt(2, updateRNo);
						st.setInt(3, reservationId);
						if(st.executeUpdate()>0)
						{
							System.out.println("Reservation Updated Successful...!");
						}
						else
						{
							System.out.println("Reservation was Not Updated Successful...!");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					break;
				case 5:
					System.out.println("Enter Reservation ID");
					int deleteId=sc.nextInt();
					query="Delete from HM_reservation where reservation_id=?";
					try {
						st=con.prepareStatement(query);
						st.setInt(1, deleteId);
	
						if(st.executeUpdate()>0)
						{
							System.out.println("Reservation Deleted Successful...!");
						}
						else
						{
							System.out.println("Reservation was Not Deleted Successful...!");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				case 6:
					System.out.println("Thank you ");
					System.out.println("Exiting System...");
					break;
			}
			
			
			
			
		}
	}

}
