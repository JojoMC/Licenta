package com.luv2code.springsecurity.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Test {

	public static void main(String[] args) {
		
		
		try {
			
			String fileName = "C:\\Users\\AlexM\\Desktop\\statFunctii.xls";
			FileInputStream excelFile = new FileInputStream(new File(fileName));
			HSSFWorkbook workbook = new HSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			
			Cell currentCell = datatypeSheet.getRow(1).getCell(1);
			currentCell.setCellValue("test");
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
