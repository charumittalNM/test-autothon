package common;

import org.openqa.selenium.remote.DesiredCapabilities;

public class UILibrary {
	
	public static DesiredCapabilities selectBrowser(String Platform, String Browser) {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserstack.local", "false"); 
		
		switch (Browser) {
        case "Chrome":  
        	 caps.setCapability("browser", "Chrome");
        	 caps.setCapability("browser_version", "62.0");
        	     break;
                 
        case "Edge":  
        	 caps.setCapability("browser", "Edge");
        	 caps.setCapability("browser_version", "18.0"); 
       	 
                break;
                
        case "IE":  
        	 caps.setCapability("browser", "IE");
        	 caps.setCapability("browser_version", "11.0"); 
      	 
               break;
                
        default: 
        	caps.setCapability("browser", "Chrome");
        	caps.setCapability("browser_version", "62.0");
       	 	     break;
    }
		
		switch (Platform) {
		
        case "Windows10":  
        	caps.setCapability("os_version", "10"); 
                 break;
       
                 
        default: 
        	caps.setCapability("os_version", "10");
                 break;
    }
		
		return caps;
	}


}
