package com.padma.springproj.SpringExamples.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.padma.springproj.SpringExamples.core.DriverInterface;

 

public class FileDB implements DriverInterface {
	static String name;
	static String line;
	public Map<String, List<String>> mapofLines = new HashMap<String, List<String>>();
	

		
	 
	public void update(String... updateAttr) {
		// TODO Auto-generated method stub
		
		String ssn=updateAttr[0];
		List<String> contactlist =  mapofLines.get(ssn);
		switch(updateAttr[1]) {
		 
		case "ssn": 
			contactlist.set(0, updateAttr[2]);
			break;
		case "name": 
			contactlist.set(1, updateAttr[2]);
			break;
		case "phone": 
			contactlist.set(2, updateAttr[2]);
			break;
		case "city": 
			contactlist.set(3, updateAttr[2]);
			break;
		case "country": 
			contactlist.set(4, updateAttr[2]);
			break;
		}
		mapofLines.put(ssn, contactlist);
	}

	 
	public void delete(String key) {
		// TODO Auto-generated method stub
		if (mapofLines.containsKey(key)) {
			System.err.println("deleting..." + key);
			mapofLines.remove(key);
		}
	}

	 
	public void insert(String... insertAttr) {
		// TODO Auto-generated method stub
		List<String> contactlist = new ArrayList<String>();
		String ssn=insertAttr[0];
		for(String columnValue:insertAttr) {
			contactlist.add(columnValue);
		}
		mapofLines.put(ssn, contactlist);
	}

	 
	public void select(String key) {
		// TODO Auto-generated method stub
		if (mapofLines.containsKey(key)) {
			System.out.println(" The record for the key : " +  key +  " ==== "  +  mapofLines.get(key));
		}
		

	}

 
	public void save() {
		// TODO Auto-generated method stub
		writeToDB();
	}
	
	public   void quit() {
		System.exit(0);

	}

	 
	public Map<String, List<String>> readFromDB() {
		// TODO Auto-generated method stub
		String[] splitLine = null;
		BufferedReader br=null;
		try {
			File f = new File(
					"C:\\Users\\padmaja\\workspace\\files\\addressbook.txt");

			FileReader fr = new FileReader(f);
			br = new BufferedReader(fr);
			System.out.println("get the file");
			String line = br.readLine();
			while (line != null) {
				splitLine = line.split(":");
				System.out.println("insid while");
				List<String> splitList = Arrays.asList(splitLine);
				System.out.println(splitLine[0]);
				mapofLines.put(splitLine[0], splitList);
				line = br.readLine();
			}
			System.out.println("outside while");
			return mapofLines;

		}// end try
		catch (Exception e) {
			e.printStackTrace(System.out);
		}

		finally {
			System.out.println("");
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mapofLines;
		 
	}

	 
	public void writeToDB() {
		// TODO Auto-generated method stub
		BufferedWriter bw = null;
		StringBuffer sb = new StringBuffer();
		try {
			File f = new File(
					"C:\\Users\\padmaja\\workspace\\files\\addressbook.txt");
			bw = new BufferedWriter(new FileWriter(f));

			for (List<String> wordsOfLine : mapofLines.values()) {
				sb = new StringBuffer();
				for (int len = 0; len < wordsOfLine.size(); len++) {
					sb.append(wordsOfLine.get(len));
					if (len != wordsOfLine.size() - 1)
						sb.append(":");
				}

				sb.append("\n");
				bw.write(sb.toString());

			}
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				bw.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("");
		}
		
	}

}