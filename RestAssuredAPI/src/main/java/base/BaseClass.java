package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
	public Properties prop;
	public BaseClass(){
	try{
	prop = new Properties();
	String dirPath = System.getProperty("user.dir");
	String configFileLoc = "\\src\\main\\resources\\config.properties" ;
	FileInputStream ip = new FileInputStream(dirPath+configFileLoc);
	prop.load(ip);
	} catch (FileNotFoundException e){
	e.printStackTrace();
	} catch (IOException e){
	e.printStackTrace();
	}
	}

}
