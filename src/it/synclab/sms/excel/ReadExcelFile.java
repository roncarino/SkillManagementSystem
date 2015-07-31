package it.synclab.sms.excel;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ReadExcelFile {

	public static Object[][] readExcelFile(String path) {
		Object array[][] = new Object[91][11];
		String categoria = "";
		String competenza = "";
		String concatenazione = "";
		
		try {
			/** Creating Input Stream **/
			FileInputStream file = new FileInputStream(path);

			/** Create a POIFSFileSystem object **/
			POIFSFileSystem myFileSystem = new POIFSFileSystem(file);

			/** Create a workbook using the File System **/
			HSSFWorkbook workBook = new HSSFWorkbook(myFileSystem);

			/** Get the first sheet from workbook **/
			HSSFSheet sheet = workBook.getSheetAt(0);

			/** Iteratore con contatore **/
			for(int cont = 0; cont <= 1; cont++) /** CONT = 0 ==> PRIMA COLONNA ....... CONT = 1 ==> SECONDA COLONNA **/ 
				for (int riga_contatore = (7 - (4*cont)) ; riga_contatore < 91; riga_contatore++) {
					HSSFRow riga = sheet.getRow(riga_contatore);
					for (int colonna_contatore = (1 + (7*cont)); colonna_contatore < (4 + (7*cont)); colonna_contatore++) {
						// INDICE DELLA RIGA
						HSSFCell cell_esempio = riga.getCell(colonna_contatore);
						if (cell_esempio != null && !cell_esempio.equals("")) {
							String text;
							String tipologia;
							switch (cell_esempio.getCellType()) {
								case HSSFCell.CELL_TYPE_BLANK:
								case HSSFCell.CELL_TYPE_ERROR:	/** IGNORE ALL BLANK OR ERROR CELLS **/
									break;
								
								/** PRENDE I LIVELLI ESCLUDENDO I LIVELLI = 0 **/
								case HSSFCell.CELL_TYPE_NUMERIC: { 		
										text = Double.toString(cell_esempio.getNumericCellValue());
										text.toString();
									if(!text.equals("0.0")) {
										array[riga_contatore][colonna_contatore] = text;
									}
								}
									break;
	
								case HSSFCell.CELL_TYPE_STRING:
								default:
									try {
										/** PRENDE LE CATEGORIE **/
										tipologia = cell_esempio.getStringCellValue();
										if (colonna_contatore == (1 + (7*cont))) {
											if (!tipologia.trim().equals("")) {
												categoria = tipologia.trim();
												System.out.println("*****" + categoria + "********");
											}
										}
										
										/** PRENDE LE COMPETENZE ESCLUDENDO LE CELLE "Altro" **/
										text = cell_esempio.getStringCellValue();
										if (colonna_contatore == (2 + (7*cont)) && !text.equals("Altro")) {   
											competenza = text;
											array[riga_contatore][colonna_contatore] = competenza;
											System.out.println(array[riga_contatore][colonna_contatore]);
											array[riga_contatore][colonna_contatore - 1] = categoria;
											System.out.println(categoria);
											concatenazione = categoria + "||" + text;
											System.out.println(concatenazione);
										}								
									} catch (Exception err) {
									System.out.print("errore");
									}
									break;
							}
						}
					}
				}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

//	public static void main(String arg[]) {
//		readExcelFile("C:\\Users\\Loris\\Desktop\\skill_matrix_2013.xls");
//	}
}
