package com.phyloa.dlib.lua;

import java.util.LinkedList;
import java.util.Queue;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Use to run lua scripts during runtime. Uses luaj.
 * lua scripts can call static java methods and fields using Luajava syntax.
 * 
 * Note: ALL SCRIPTS RUN IN THE SAME RUNTIME.
 * 
 * @author Daniel Winkler
 *
 */
public class DLua 
{
	public static ScriptEngineManager mgr = new ScriptEngineManager();
	public static ScriptEngine e = mgr.getEngineByExtension(".lua");
	
	private String script = "";
	
	/**
	 * Default constructor.
	 */
	public DLua()
	{
		
	}
	
	/**
	 * Constructor.
	 * @param s the string containing the lua script to run.
	 */
	public DLua( String s )
	{
		this.setScript(s);
	}
	
	/**
	 * Runs the current script.
	 * @throws ScriptException thrown when there is an error in the lua code. 
	 */
	public void run() throws ScriptException
	{
		setScript(getScript().trim());
		e.eval( getScript() );
	}
	
	/**
	 * Gets the value of a variable in the lua runtime.
	 * @param key the key of the variable
	 * @return the value of the variable.
	 */
	public Object get( String key )
	{
		return e.get( key );
	}
	
	/**
	 * Sets a key value pair (a variable) in the lua runtime.
	 * @param key the key to set
	 * @param value the value to set
	 */
	public void put( String key, Object value )
	{
		e.put( key, value );
	}
	
	/**
	 * Adds a newline, then the a line of code to the current lua script. 
	 * Useful for generating lua scripts.
	 * @param line the line of code to add
	 */
	public void nl( String line ) 
	{
		setScript(getScript() + (" \n " + line));
	}

	/**
	 * Sets the current script.
	 * @param script the string to set
	 */
	public void setScript( String script ) 
	{
		this.script = script;
	}
	
	/**
	 * Gets the current lua script
	 * @return
	 */
	public String getScript() 
	{
		return script;
	}
}