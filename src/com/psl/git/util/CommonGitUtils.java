package com.psl.git.util;

public class CommonGitUtils {

	public static boolean isEmpty(String value){
		if(value == null || value.equals("") || value.equals("null")){
			return true;
		}
		return false;
	}
}
