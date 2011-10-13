/**
 * 
 */
package net.skyebook.padloader.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Enables the loading of resources from a Jar file
 * when using Web Start or from a folder in the
 * project when being run from an IDE
 * @author Skye Book
 */
public class ResourceLoader {
	
	public static URL loadResource(String resource){
		URL res = ResourceLoader.class.getResource(resource);
		if(res==null){
			try {
				res = new File(resource.substring(resource.indexOf("/")+1)).toURI().toURL();
			} catch (MalformedURLException e) {
				// This shouldn't be happening since its coming straight from Java
			}
		}
		return res;
	}
}
