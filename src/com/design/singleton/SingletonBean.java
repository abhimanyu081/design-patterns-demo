package com.design.singleton;

import java.io.Serializable;

public class SingletonBean implements Serializable, Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SingletonBean() {// To prevent Reflection Exploitation
		if(SingletonBeanHolder.instance!=null) {
			throw new RuntimeException("Not allowed to create instance from here ");
		}
		
	}
	
	
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException { // To prevent new instance creation on clone
		return getInstance();
	}


	protected Object readResolve() {//To prevent new instance creation on deserialization
		return getInstance();
	}

	public static SingletonBean getInstance() {
		return SingletonBeanHolder.instance;
	}
	
	private static class SingletonBeanHolder{ // static class for lazy initialization
		private static final SingletonBean instance = new SingletonBean();
	}
	
}
