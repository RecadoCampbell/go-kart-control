/**
 *  Project     Go-Kart Control
 *  @author Gerd Bartelt - www.sebulli.com
 *  
 *  @copyright	GPL3
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.sebulli.gokart;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import static com.sebulli.gokart.Translate._;

/**
 * Handles configuration settings
 * 
 */
public class Config {

	private static Config _instance = null;

	private Properties props = null;

	/**
	 * Constructor
	 * Loads the file settings.txt
	 */
	private Config() {
		props = new Properties();
		try {
			FileInputStream fis = new FileInputStream(new File("settings.txt"));
			props.load(fis);
		} catch (Exception e) {
			// T: Error message
			Logger.getLogger().log(_("Error opening file settings.txt"));
		}
	}

	/**
	 * Generate a singleton
	 * 
	 * @return a Reference to the class
	 */
	public synchronized static Config getInstance() {
		if (_instance == null)
			_instance = new Config();
		return _instance;
	}

	/**
	 * Get a property as string by key name
	 * Returns an empty string, if it doesn't exist.
	 * Also trims the string
	 * 
	 * @param key
	 * 			The property's name (key)
	 * @return
	 * 			The value as string
	 */
	public String getProperty(String key) {
		String value = "";
		
		// Get the property's value
		if (props.containsKey(key))
			value = (String) props.get(key);
		else {
			//T: Error message
			Logger.getLogger().log(_("Key not found:") + " " + key);
			return "";
		}
		
		// Trim the string
		return value.trim();
	}

	/**
	 * Get a property as string by key name
	 * Also trims the string.
	 * Does not generate a error log, if the property doesn't exist.
	 * 
	 * @param key
	 * 			The property's name (key)
	 * @return
	 * 			The value as string
	 */
	public String getPropertyIfExists(String key) {
		String value = "";
		
		// Get the property's value
		if (props.containsKey(key))
			value = (String) props.get(key);
		else {
			return "";
		}
		
		// Trim the string
		return value.trim();
	}

	/**
	 * Checks whether a configuration key exists and
	 * is not empty
	 * 
	 * @param key
	 * 			The key to check
	 * @return
	 * 			True, if there is an entry with this key
	 */
	public boolean isSet(String key) {
		if (!props.containsKey(key))
			return false;

		// Check also empty strings
		String value = (String) props.get(key);
		return !value.trim().isEmpty();

	}

	/**
	 * Get a property as integer by key name
	 * Returns 0, if it doesn't exist.
	 * 
	 * @param 
	 * 		key
	 *      defaultValue
	 *      ifExists
	 * @return
	 * 		The value of the key 
	 */
	public int getPropertyAsInt(String key, int defaultValue, boolean ifExists) {
		String property;
		
		if (ifExists)
			property = getPropertyIfExists(key);
		else
			property = getProperty(key);
		
		if (!property.isEmpty()) {
			try {
				return Integer.parseInt(property);
			} catch (Exception e) {
				//T: Error message
				Logger.getLogger().log("Error parsing key as integer:" + " " + key);
				return 0;
			}
		} else {
			return 0;
		}

	}
	
	/**
	 * Get a property as integer by key name
	 * Returns 0, if it doesn't exist.
	 * 
	 * @param 
	 * 		key
	 * @return
	 * 		The value of the key 
	 */
	public int getPropertyAsInt(String key) {
		return getPropertyAsInt(key, 0, false);
	}
	
	/**
	 * Get a property as integer by key name
	 * Returns 0, if it doesn't exist.
	 * 
	 * @param 
	 * 		key
	 * @return
	 * 		The value of the key 
	 */
	public int getPropertyAsIntIfExists(String key, int defaultValue) {
		return getPropertyAsInt(key, defaultValue, true);
	}
	
	/**
	 * Get a property as double by key name
	 * Returns 0, if it doesn't exist.
	 * 
	 * @param 
	 * 		key
	 *      defaultValue if no key exists
	 * @return
	 * 		The value of the key 
	 */
	public double getPropertyAsDouble(String key, double defaultValue, boolean ifExists) {
		String property;
		
		if (ifExists)
			property = getPropertyIfExists(key);
		else
			property = getProperty(key);
		
		if (!property.isEmpty()) {
			try {
				return Double.parseDouble(property);
			} catch (Exception e) {
				//T: Error message
				Logger.getLogger().log("Error parsing key as double:" + " " + key);
				return 0;
			}
		} else {
			return defaultValue;
		}

	}
	
	
	
	/**
	 * Get a property as double by key name
	 * Returns 0, if it doesn't exist.
	 * 
	 * @param 
	 * 		key
	 * 		defaultValue
	 * @return
	 * 		The value of the key 
	 */
	public double getPropertyAsDoubleIfExists(String key, double defaultValue) {
		return getPropertyAsDouble(key, defaultValue, true);
	}
	
	/**
	 * Get a property as double by key name
	 * Returns 0, if it doesn't exist.
	 * 
	 * @param 
	 * 		key
	 * @return
	 * 		The value of the key 
	 */
	public double getPropertyAsDouble(String key) {
		return getPropertyAsDouble(key, 0.0, false);
	}

}
