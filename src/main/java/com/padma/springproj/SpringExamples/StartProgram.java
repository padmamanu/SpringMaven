package com.padma.springproj.SpringExamples;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.padma.springproj.SpringExamples.core.DriverInterface;
import com.padma.springproj.SpringExamples.impl.FileDB;

 

public class StartProgram {
	static String country;
	static String deleteOperation = "DELETE";
	static String selectOperation = "SELECT";
	static String updateOperation = "UPDATE";
	static String insertOperation = "INSERT";

	public static void main(String[] args) throws IOException {
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring_Conf.xml");
		
		DriverInterface dbDriver = (DriverInterface)context.getBean("dbDriver");  

		Map<String, List<String>> mapofLines = dbDriver.readFromDB();

		// TODO Auto-generated method stub
		Scanner sc1 = new Scanner(System.in);
		System.out.println(" Enter request of operation");
		String arg = sc1.nextLine();

		if (arg.contains(insertOperation)) {       
			String[] strs=arg.split(",");
			String ssn=strs[0].substring(strs[0].indexOf("'")+1);
			ssn=ssn.substring(0,ssn.indexOf("'"));
			String name=strs[1].substring(strs[1].indexOf("'")+1);
			name=name.substring(0,name.indexOf("'"));
		
			String phone=strs[2].substring(strs[2].indexOf("'")+1);
			phone=phone.substring(0,phone.indexOf("'"));
			String city=strs[3].substring(strs[3].indexOf("'")+1);
			city=city.substring(0,city.indexOf("'"));
			String country=strs[4].substring(strs[4].indexOf("'")+1);
			country=country.substring(0,country.indexOf("'"));
			String[] insertArgs={ssn,name,phone,city,country};
			dbDriver.insert(insertArgs);
		}

		else if (arg.contains(updateOperation)) {
            
		/*	String argname = arg.substring(arg.indexOf("'") + 1);
			String ssn= argname;
			String argname1 = argname.substring(0, argname.indexOf("'"));*/
			
			/*strs[1]=strs[1].substring(strs[1].indexOf("'") + 1);
			strs[1]=strs[1].substring(0, strs[1].indexOf("'"));
			String[] updateArgs = {strs[1],argname1};*/
			String[] strs=arg.split("WHERE");
			String ssn=strs[1].substring(strs[1].indexOf("'")+1);
			ssn=ssn.substring(0, ssn.indexOf("'"));
			String[]strs2=strs[0].split("SET");
			String[]strs3=strs2[1].split("=");
		//String countrys=	strs3[0];
		//String	cname=strs3[1];
			String name;
			String country;
			String field=strs3[0].replaceAll("\\s+","");
			
			switch (field) {
			case 	"name":
				 name = strs3[1].substring(strs3[1].indexOf("'") + 1);

				name = name.substring(0, name.indexOf("'"));
				break;

			case "phone":
				String phone = strs3[1].substring(strs3[1].indexOf("'") + 1);

				phone = phone.substring(0, phone.indexOf("'"));
				break;
			case "city":
				String city = strs3[1].substring(strs3[1].indexOf("'") + 1);

				city = city.substring(0, city.indexOf("'"));
				break;
			case "country":
				 country = strs3[1].substring(strs3[1].indexOf("'") + 1);
				 country = country.substring(0, country.indexOf("'"));
				String[] updateArgs={ssn,field,country};
				dbDriver.update(updateArgs);
				break;
			}
		

		
	}
		else if (arg.contains(deleteOperation)) {
			arg = arg.substring(arg.indexOf("'") + 1);
			arg = arg.substring(0, arg.indexOf("'"));
			dbDriver.delete(arg);
		} else if ((arg.contains(selectOperation))) {
			arg = arg.substring(arg.indexOf("'") + 1);
			arg = arg.substring(0, arg.indexOf("'"));
			dbDriver.select(arg);
		} else if ("saveBook".equalsIgnoreCase(arg)) {
			// save();
		} else if ("quit".equalsIgnoreCase(arg)) {

		}

		dbDriver.save();

	}

}
