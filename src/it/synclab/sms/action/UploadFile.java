package it.synclab.sms.action;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import com.opensymphony.xwork2.ActionSupport;

public class UploadFile extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static File userFile = null;
	String userExcelContentType;
	String userExcelFileName;

	public UploadFile() {}

	public File getUserFile() {
		return userFile;
	}

	@SuppressWarnings("static-access")
	public void setUserFile(File userFile) {
		this.userFile = userFile;
	}

	public String getUserExcelContentType() {
		return userExcelContentType;
	}

	public void setUserExcelContentType(String userExcelContentType) {
		this.userExcelContentType = userExcelContentType;
	}

	public String getUserExcelFileName() {
		return userExcelFileName;
	}

	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}

	public static void deleteFile() throws IOException{
		File filetodelete = File.createTempFile("TMP_File",".tmp");
		System.out.println("*****************************FILE CANCELLATO*************************************");
		filetodelete.deleteOnExit();
	}
	
	public static File createFile() throws IOException {
		String path = System.getProperty("java.io.tmpdir") + "/skill_matrix.xls";
		File filetocreate = new File(path);
		System.out.println(path);
		FileUtils.copyFile(userFile, filetocreate);
		System.out.println("**************************************FILE CREATO*************************************");
		return userFile;
	}
	
	public String execute() throws IOException{
		createFile();
		return "SUCCESS";
	}
	
	public void validate() {
		if(getFieldErrors().get("userFile") == null) 
		if(userFile == null) {	
			addFieldError("userFile", "SELEZIONA IL FILE");
		}
	}
}
