package com.padma.springproj.SpringExamples.core;

import java.util.List;
import java.util.Map;

public interface DriverInterface {
	
public   void update(String... updateAttr) ;
	
	public   void delete(String key);
	
	public   void insert(String... insertAttr);
	
	public   void select(String key) ;
	
	public   void save();
	
	public  Map<String, List<String>> readFromDB();
	
	public void writeToDB();
	
}
