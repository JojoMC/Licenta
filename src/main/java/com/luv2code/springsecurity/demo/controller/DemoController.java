package com.luv2code.springsecurity.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeSet;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.luv2code.springsecurity.demo.entity.Norm;

import java.sql.*;
import java.util.ArrayList;

@Controller
public class DemoController {
	
	private static int maxNumberHours = 400;
	private static int weeksPerSemester = 14;
	private static int counter = 0;
	
	
	public int getMaxNumberHours() {
		return maxNumberHours;
	}

	public void setMaxNumberHours(int max_number_hours) {
		this.maxNumberHours = max_number_hours;
	}

	@GetMapping("/")
	public String showHome(Model theModel) {
		
		
		
		
 
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/spring_security_demo_bcrypt?useSSL=false",
						"root","parasute72bz");
			Statement stmt=con.createStatement(); 
			ResultSet rs=stmt.executeQuery("select `name` from subjects");
			
			TreeSet<String> subjectSet = new TreeSet<>();
			while(rs.next()) {
				subjectSet.add(rs.getString(1));
			}
			theModel.addAttribute("materii",subjectSet);
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "home";
	}
	
	// add request mapping for /leaders
	@GetMapping("/leaders")
	public String showLeaders(){
		
		return "leaders";
	}
	
	// add request mapping for /systems
	@GetMapping("/systems")
	public String showSystems(){
		
		return "systems";
	}
	
	@GetMapping("uploadSubjectsDinamically")
	public String uploadSubjectDinamically(Model model,@RequestParam("NormsDocument") File file) throws IOException {

		String fileName = file.getPath();
		try {
			
			TreeSet<String> subjects = new TreeSet<>();
			ArrayList<Norm> norms = new ArrayList<Norm>();
			FileInputStream excelFile = new FileInputStream(new File(fileName));
			HSSFWorkbook workbook = new HSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			
			
			while(iterator.hasNext()) {
				Row currentRow = iterator.next();
				Norm currentNorm = new Norm();
				if(currentRow.getCell(6).toString().contains("Conferentiar") ) {
					break;
				}
				
				
				Iterator<Cell> cellIterator = currentRow.iterator();
				
					while(cellIterator.hasNext()) {
					
					Cell currentCell = cellIterator.next();
					
					int type = currentCell.getCellType();
					if(type == 1 && currentCell.getColumnIndex() == 6
							&& !(currentCell.getStringCellValue().contains("Total"))
							&& !(currentCell.getStringCellValue().contains("TOTAL"))
							&& !(currentCell.getStringCellValue().contains("Alte activitati"))
							&& !(currentCell.getStringCellValue().contains("Norma de cercetare"))
							&& !(currentCell.getStringCellValue().contains("Denumire disciplina"))
							&& !(currentCell.getStringCellValue().equals(null)))
							
					{
						subjects.add(currentCell.getStringCellValue().trim());
						currentNorm.setSubjectName(currentCell.getStringCellValue().trim());
					}
					
					
					
					/*
					 * daca norma e vacanta dar numele "vacant" 
					 * nu e exact in randul respectiv tr sa se populeze spatiul cu vacant
					 */
					
					
					if(currentRow.getCell(2).toString().contains("vacant")) {
					
					
					switch(currentCell.getColumnIndex()) {
					
					case 2: //currentNorm.setAvailability(currentCell.getStringCellValue());
							if(currentCell.getCellType() == 1) {
								//System.out.println(currentCell.getStringCellValue());
								currentNorm.setAvailability(currentCell.getStringCellValue());
							}
							break;
							
					case 7: //currentNorm.setFaculty(currentCell.getStringCellValue());
							if(currentCell.getCellType() == 1) {
								//System.out.println(currentCell.getStringCellValue());
								currentNorm.setAvailability(currentCell.getStringCellValue());	
							}
							break;
					
					case 9: //currentNorm.setLanguage(currentCell.getStringCellValue());
							if(currentCell.getCellType() == 1) {
								currentNorm.setAvailability(currentCell.getStringCellValue());	
							}
							break;
							
					case 10: //currentNorm.setYear(currentCell.getStringCellValue());
							if(currentCell.getCellType() == 1) {
								currentNorm.setAvailability(currentCell.getStringCellValue());	
							}
							break;
							
					case 11: //currentNorm.setSeries(currentCell.getStringCellValue());
							if(currentCell.getCellType() == 1) {
								currentNorm.setAvailability(currentCell.getStringCellValue());	
							}
							break;
							
					case 14 : int priorHoursCourse1 = currentNorm.getAvailableHoursCourse();
							currentNorm.setAvailableHoursCourse(priorHoursCourse1 + (int) currentCell.getNumericCellValue()*weeksPerSemester);
							break;
							
					case 15 : int priorHoursApplication1 = currentNorm.getAvailableHoursApplication();
							currentNorm.setAvailableHoursApplication(priorHoursApplication1 + (int) currentCell.getNumericCellValue()*weeksPerSemester);
							break;
							
					case 16 :
						int priorHoursCourse2 = currentNorm.getAvailableHoursCourse();
						currentNorm.setAvailableHoursCourse(priorHoursCourse2 + (int) currentCell.getNumericCellValue()*weeksPerSemester);
						//if(counter > 50)
						//System.out.println( counter + " : &&&&&&&&&&&&& : "   + currentNorm.getSubjectName()  + " && : " + currentNorm.getAvailableHoursCourse());
						break;
						
					case 17 :
						int priorHoursApplication2 = currentNorm.getAvailableHoursApplication();
						currentNorm.setAvailableHoursApplication(priorHoursApplication2 + (int) currentCell.getNumericCellValue()*weeksPerSemester);
						//if(counter > 50) {
							//System.out.println(counter + ": &&&&&&&&&&& : " + currentNorm.getSubjectName() + " && : " + currentNorm.getAvailableHoursApplication());
						//}
						//counter++;
						break;
						
							
					default: break;
					
					}
					}
					
				}
				
				norms.add(currentNorm);
				
			}

				
				try {
					Class.forName("com.mysql.jdbc.Driver"); 
					Connection con=DriverManager.getConnection(  
							"jdbc:mysql://localhost:3306/spring_security_demo_bcrypt?useSSL=false",
								"root","parasute72bz");
					Statement stmt=con.createStatement(); 
					
					for(String subject : subjects) {
						String query = " insert into subjects(`name`)"
						        + " values (?)";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString (1, subject);
						preparedStmt.execute();
					}
					con.close();
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				
				
				
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		/*incarcarea in baza de date a tuturor normelor*/
		try {
			
			String fileName1 = file.getPath();
			FileInputStream excelFile = new FileInputStream(new File(fileName1));
			HSSFWorkbook workbook = new HSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			ArrayList<Norm> normList = new ArrayList<Norm>();
				
			while(iterator.hasNext()) {
				Norm currentNorm = new Norm();
				Row currentRow = iterator.next();
				if(currentRow.getRowNum() == 676) {
					break;
				}
				if(currentRow.getCell(6).toString().contains("Conferentiar") ) {
					break;
				}
						
				Iterator<Cell> cellIterator = currentRow.iterator();
				while(cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					int type = currentCell.getCellType();
					switch(currentCell.getColumnIndex()) {
					
						case 0 : 
							if(currentCell == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK
								|| currentCell.getCellType() == Cell.CELL_TYPE_STRING	){
								currentNorm.setNormNumber(0);
							} else {
								currentNorm.setNormNumber((int)currentCell.getNumericCellValue());
							}							
							break;
							
						case 1 :
								if(currentCell == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK) {
									currentNorm.setPositionName("");
								} else {
									currentNorm.setPositionName(currentCell.getStringCellValue());
								}
							break;
					
						case 2 : 
							if (currentCell == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK) {
							    currentNorm.setAvailability("");
							 }else {
								 currentNorm.setAvailability(currentCell.getStringCellValue());
							 }
							//System.out.println(currentNorm.getAvailability());
							break;
						case 6 : 
							currentNorm.setSubjectName(currentCell.getStringCellValue());
							break;
						case 7:
							currentNorm.setFaculty(currentCell.getStringCellValue());
							break;
							
						case 8 : if(currentCell == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK) {
										currentNorm.setPositionName("");
								 } else {
									 currentNorm.setStudiesType(currentCell.getStringCellValue());
								 }
							break;
							
						case 9:
							currentNorm.setLanguage(currentCell.getStringCellValue());
							break;
						case 10:
							currentNorm.setYear(currentCell.getStringCellValue());
							break;
						case 11: 
							currentNorm.setSeries(currentCell.getStringCellValue());
							break;
							
						case 14 :
							
							if(currentCell == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK	|| currentCell.getCellType() == Cell.CELL_TYPE_STRING	){
								currentNorm.setNormNumber(0);
							} else {
								currentNorm.setHoursPerWeekCourse1((int)currentCell.getNumericCellValue());
							}
							break;
							
						case 15 : 
							
							if(currentCell == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK	|| currentCell.getCellType() == Cell.CELL_TYPE_STRING	){
								currentNorm.setNormNumber(0);
							} else {
								currentNorm.setHoursPerWeekApplications1((int)currentCell.getNumericCellValue());
							}
							
							break;
							
						case 16 :
							
							if(currentCell == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK	|| currentCell.getCellType() == Cell.CELL_TYPE_STRING	){
								currentNorm.setNormNumber(0);
							} else {
								currentNorm.setHoursPerWeekCourse2((int)currentCell.getNumericCellValue());
							}
							
							break;
							
						case 17 : 
							if(currentCell == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK	|| currentCell.getCellType() == Cell.CELL_TYPE_STRING	){
								currentNorm.setNormNumber(0);
				
							} else {
								currentNorm.setHoursPerWeekApplications2((int)currentCell.getNumericCellValue());
								System.out.println(counter + "######## : " + currentNorm.getAvailableHoursApplication());
								System.out.println(currentNorm.getSubjectName());
								counter++;
							}
							break;
					}		
				}
				normList.add(currentNorm);	
			}
				
			
			try { 
				String availability = "";
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection con1=DriverManager.getConnection(  
						"jdbc:mysql://localhost:3306/spring_security_demo_bcrypt?useSSL=false",
							"root","parasute72bz");
				Statement stmt1=con1.createStatement(); 
				String query1 = " insert into norms(`norm_number`,`position_name`,`availability`, `subject_name`, `faculty`, `studies_type`, `language`, `year`, `series`, `hours_per_week_course_1`, `hours_per_week_application_1`, `hours_per_week_course_2`, `hours_per_week_application_2`)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				//System.out.println("###########");				
				
				int i = 0;
				int j = 0;
				for(Norm currentNorm : normList) {
					if(!currentNorm.getAvailability().equals("")) {
						
						availability = currentNorm.getAvailability();
						//System.out.println("@@@@ " + availability);
					}
					
					
					PreparedStatement preparedStmt1 = con1.prepareStatement(query1);
					preparedStmt1.setInt(1, currentNorm.getNormNumber());
					preparedStmt1.setString(2, currentNorm.getPositionName());
					preparedStmt1.setString (3, availability);
					preparedStmt1.setString (4,currentNorm.getSubjectName() );
					preparedStmt1.setString (5, currentNorm.getFaculty() );
					preparedStmt1.setString(6, currentNorm.getStudiesType());
					preparedStmt1.setString (7, currentNorm.getLanguage() );
					preparedStmt1.setString (8, currentNorm.getYear());
					preparedStmt1.setString (9, currentNorm.getSeries());
					preparedStmt1.setInt(10, currentNorm.getHoursPerWeekCourse1());
					preparedStmt1.setInt(11, currentNorm.getHoursPerWeekApplications1());
					preparedStmt1.setInt(12, currentNorm.getHoursPerWeekCourse2());
					preparedStmt1.setInt(13, currentNorm.getHoursPerWeekApplications2());
					//System.out.println(currentNorm.getYear());
					preparedStmt1.executeUpdate();
				}
				con1.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
					
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		
		
		
		
		
		
		
		
		
		model.addAttribute("message", fileName );
		
	 	return "uploadSubjectsDinamicallyCompleted";
	}
	
	
	
	@GetMapping("selectSubject")
	public String selectSubject(Model theModel, @RequestParam("tempSubject") String tempSubject) {
		
		
		theModel.addAttribute("tempSubject",tempSubject);
		
		return "list-of-series";
	}
	
	@GetMapping("confirmNorms")
	public String confirmNorms(Model theModel, @RequestParam("ceva") String tempNormId) {
		
		theModel.addAttribute("tempNormId", tempNormId);
		ArrayList<String> normIdList = new ArrayList<String>();
		int weeksPerSemester = 14;
		ArrayList<Integer> normList = new ArrayList<Integer>();


		
		System.out.println("@@@ : " + tempNormId);
			while(!tempNormId.equals("")) {
				if(tempNormId.contains(",")) {
					int pos = tempNormId.indexOf(",");
					normIdList.add(tempNormId.substring(0, pos));
					tempNormId = tempNormId.substring(pos+1, tempNormId.length());
					int integerTempNormId = Integer.parseInt(tempNormId);
					normList.add(integerTempNormId);
				} else {
					int integerTempNormId = Integer.parseInt(tempNormId);
					normList.add(integerTempNormId);
					normIdList.add(tempNormId);
					tempNormId = "";
					System.out.println(tempNormId);
				}
			}
		
		theModel.addAttribute("normIdList", normIdList);
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = "sthWrong";
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		
		
		
		if(checkEligibilityForNorms(username, normList)) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con1=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/spring_security_demo_bcrypt?useSSL=false",
						"root","parasute72bz");
			
			
			
			for(String normId : normIdList) {
				
				String query0 = "select * from `users` where `username` = ?";
				PreparedStatement preparedStmt0 = con1.prepareStatement(query0);
				preparedStmt0.setString(1, username);
				ResultSet rs0=preparedStmt0.executeQuery();
				rs0.next();
				String firstName = rs0.getString(2);
				String lastName = rs0.getString(3);
				String fullName = lastName + " " + firstName;
				
				Statement stmt1=con1.createStatement();
				String query1 = "select * from `norms` where `id`=" + normId;
				stmt1 = con1.prepareStatement(query1);
				PreparedStatement preparedStmt1 = con1.prepareStatement(query1);
				//preparedStmt1.setString(1, normId);
				ResultSet rs=stmt1.executeQuery(query1);
				rs.next();
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
				
				int additionalHours = rs.getInt(11) + rs.getInt(12) + rs.getInt(13) + rs.getInt(14);
				System.out.println("additional hours : " + additionalHours);
				System.out.println(username);
				
				
				String query3 = "select * from `users` where `username` = ?";
				PreparedStatement preparedStmt3 = con1.prepareStatement(query3);
				preparedStmt3.setString(1, username);
				ResultSet rs3=preparedStmt3.executeQuery();
				rs3.next();
				int existingHours = rs3.getInt(6);
				
				if(existingHours + additionalHours*weeksPerSemester <= maxNumberHours) {
					String query2 = "update `users` set `hours`= `hours` + " + weeksPerSemester * additionalHours + " where `username`= ? ";
					PreparedStatement preparedStmt2 = con1.prepareStatement(query2);
					preparedStmt2.setString(1, username);
					preparedStmt2.executeUpdate();
					
					String query = "update `norms` set `availability` = ? where `id`= ? ";
					PreparedStatement preparedStmt = con1.prepareStatement(query);
					preparedStmt.setString(1, fullName);
					preparedStmt.setString(2, normId);
					preparedStmt.executeUpdate();
				} else {
					return "hours-overflow";
				}
			}
			
			
			
			con1.close();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "norms-confirmed";
	} else {
		return "hours-overflow";
	}
		
		
		
		
		
	}
	
	
	@GetMapping("selectSeries")
	public String selectSeries(Model theModel, @RequestParam("tempSeries") String tempSeries,
			@RequestParam("tempSubject") String tempSubject) {
		
		theModel.addAttribute("tempSeries",tempSeries);
		theModel.addAttribute("tempSubject", tempSubject);
		
		/*Realizeaza o metoda prin care extragi normele de la seria si materia respective*/
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/spring_security_demo_bcrypt?useSSL=false",
						"root","parasute72bz");
			Statement stmt=con.createStatement(); 
			
			
			ArrayList<Norm> requiredNormList = new ArrayList<Norm>();

			for(int i=1; i<600; i++) {
				Norm currentNorm = new Norm();
				PreparedStatement pst=null;
				 ResultSet rs=null;
				 pst=con.prepareStatement("select * from norms where id=" + i);
				 rs=pst.executeQuery();
				 rs.first(); 
				 
				 if(!(rs == null)) {
				 
			     currentNorm.setId(rs.getInt(1));
				 currentNorm.setNormNumber(rs.getInt(2));
				 currentNorm.setPositionName(rs.getString(3));
				 currentNorm.setAvailability(rs.getString(4));
				 currentNorm.setSubjectName(rs.getString(5));
				 currentNorm.setFaculty(rs.getString(6));
				 currentNorm.setStudiesType(rs.getString(7));
				 currentNorm.setLanguage(rs.getString(8));
				 currentNorm.setYear(rs.getString(9));
				 currentNorm.setSeries(rs.getString(10));
				 currentNorm.setHoursPerWeekCourse1(rs.getInt(11));
				 currentNorm.setHoursPerWeekApplications1(rs.getInt(12));
				 currentNorm.setHoursPerWeekCourse2(rs.getInt(13));
				 currentNorm.setHoursPerWeekApplications2(rs.getInt(14));
				 }
				 
				 if(currentNorm.getSubjectName().equals(tempSubject) && currentNorm.getSeries().equals(tempSeries)) {
					 
					 requiredNormList.add(currentNorm); 
				 }
			}
			theModel.addAttribute("requiredNormList", requiredNormList);
			
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		return "list-of-norms";
	}
	
	@GetMapping("checkAdditionalHours")
	public String checkAdditionalHours(Model theModel) {
		
		int  maxHours = maxNumberHours;
		int availableHours;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = "WrongName";
		if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con1=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/spring_security_demo_bcrypt?useSSL=false",
						"root","parasute72bz");
			String query = "select * from `users` where `username` = ?";
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			preparedStmt.setString(1, username);
			ResultSet rs=preparedStmt.executeQuery();
			rs.next();
			
			int hours = rs.getInt(6);
			String fullName = rs.getString(3) + " " + rs.getString(2);
			theModel.addAttribute("hours", hours);
			availableHours = maxHours - hours;
			theModel.addAttribute("availableHours", availableHours);
			System.out.println("###### Additional hours: " + hours);
			
			
			String query2 = "select * from `norms` where `availability` = ?";
			PreparedStatement preparedStmt2 = con1.prepareStatement(query2);
			preparedStmt2.setString(1, fullName);
			ResultSet rs2 = preparedStmt.executeQuery();

			
			con1.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		theModel.addAttribute("username", username);
		
		return "hours-checked";
	}
	
	public static boolean checkEligibilityForNorms(String username, ArrayList<Integer> normIdList) {
		
		int maxHours = maxNumberHours;
		int weeksPerSemester = 14;
		int desiredHours = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con1=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/spring_security_demo_bcrypt?useSSL=false",
						"springstudent","springstudent");
			String query = "select * from `users` where `username` = ?";
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			preparedStmt.setString(1, username);
			ResultSet rs=preparedStmt.executeQuery();
			rs.next();
			
			int hours = rs.getInt(6);
			
			System.out.println("##### normIdList size : " + normIdList.size());
			
			for(int i=0; i<normIdList.size(); i++) {
				//System.out.println("@@@@ : " + normIdList.get(i));
			}
			
			
			for(int i=0; i<normIdList.size(); i++) {
				int normId = normIdList.get(i);
				String query1 = "select * from `norms` where `id` = ?";
				PreparedStatement preparedStmt1 = con1.prepareStatement(query1);
				preparedStmt.setInt(1, normId);
				System.out.println("#### NORM ID: " + normId);
				ResultSet rs1 = preparedStmt.executeQuery();
				if(rs1.next()) {
					desiredHours += rs1.getInt(12);
					System.out.println("####### DESIRED HOURS : " + desiredHours);
				}
			}
			
			con1.close();
			
			if(desiredHours + hours > maxHours) {
				return false;
			}
			
		} catch(Exception e) {
			System.out.println("###### BIG ERROR HERE !!!!");
			e.printStackTrace();
		}
		
		return true;
	}	
	
	@GetMapping("modifyMaxNoHours")
	public String modifyMaxNoHours(Model model, @RequestParam("MAX") int MAX) {

		maxNumberHours = MAX;
		System.out.println("^^^^^^^ : " + maxNumberHours);
		
		model.addAttribute("MAX", MAX);
		return "modifyMaxNoHoursCompleted";
	}
	

}
