package com.luv2code.springsecurity.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.util.SocketUtils;

import com.luv2code.springsecurity.demo.entity.Norm;

public class WriteXls {

	public static void main(String[] args){
		
		String fileName = "D:\\Licenta\\stat_functii_2.xls";
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int bec = 0;
		
		
		try {
			FileInputStream excelFile = new FileInputStream(new File(fileName));
			HSSFWorkbook workbook = new HSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			
			while(iterator.hasNext()) {
				Row currentRow = iterator.next();
				
				if(currentRow.getCell(6).getStringCellValue().contains("Cadre didactice")) {
					break;
				}
				
				Iterator<Cell> cellIterator = currentRow.iterator();
				while(cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if(currentCell.getColumnIndex() == 2) {
						if(currentCell.getStringCellValue().contains("vacant")) {
						count1++;
						//System.out.println(currentCell.getStringCellValue() + " " +
						//		currentRow.getCell(6).getStringCellValue());
						bec = 1;
						
						}
						
					}
					if(currentCell.getColumnIndex() == 2 && 
							currentCell.getStringCellValue().length() > 3 &&
							!currentCell.getStringCellValue().contains("vacant")) {
						bec = 0;
					}
					
				}
				if(bec == 1) {
					
					if(currentRow.getCell(6).getStringCellValue().contains("Total")
							|| currentRow.getCell(6).getStringCellValue().contains("TOTAL")
							|| currentRow.getCell(6).getStringCellValue().contains("Alte activitati in norma")
							|| currentRow.getCell(6).getStringCellValue().contains("Norma de cerce")) {
						continue;
					}
					
					System.out.println(currentRow.getCell(6).getStringCellValue());
				}
				
				
			}
			System.out.println(count1);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
