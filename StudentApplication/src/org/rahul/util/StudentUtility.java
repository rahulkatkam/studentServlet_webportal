package org.rahul.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentUtility {
	
	public String errorMessage = "";
	public int k=0;

	public boolean dataValidate(String sno, String sname, String dob, String pno) {
		//if(validateSno(sno) && validateStudentName(sname) && validatePhoneNumber(pno)) {
		validateSno(sno); validateStudentName(sname); validatePhoneNumber(pno);
		if(k==1) {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean validateSno(String sno) {
		if(sno.trim().length()==0 || sno.isEmpty() ) {
			k=1;
			errorMessage = errorMessage + "Please enter valid Student Number. </br>";
			return false;
		} else {
			Pattern p = Pattern.compile("[0-9]");
	        Matcher m = p.matcher(sno);
	        if(m.find() && m.group().equals(sno)) {
	        	return true;
	        } else {
	        	errorMessage = errorMessage + "Please enter valid Student Number . </br>";
	        	return false;
	        }
		}
	}
	
	private boolean validateStudentName(String sname) {
		if(sname.trim().length()==0 || sname.isEmpty()) {
			k=1;
			errorMessage = errorMessage + "Please enter valid Student Name. </br>";
			return false;
		} else {
			return true;
		}
	}
	
	private boolean validatePhoneNumber(String pno) {
		if(pno.trim().length()==0 || pno.isEmpty()) {
			k=1;
			errorMessage = errorMessage + "Please enter valid Phone Number. </br>";
			return false;
		} else {
			Pattern p = Pattern.compile("[7-9][0-9]{9}");
	        Matcher m = p.matcher(pno);
	        if(m.find() && m.group().equals(pno)) {
	        	return true;
	        } else {
	        	errorMessage = errorMessage + "Please enter valid Phone Number. </br>";
	        	return false;
	        }
		}
	}
}
