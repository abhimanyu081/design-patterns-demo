package com.design.singleton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonBeanClient {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException,
			NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, CloneNotSupportedException {

		SingletonBean instance = SingletonBean.getInstance();
		System.out.println("instance Original = " + instance.hashCode());
		
		SingletonBean instanceSerCopy = deserializedInstance(instance);
		System.out.println("instanceSerCopy = " + instanceSerCopy.hashCode());
		
		
		
		SingletonBean instanceClone = (SingletonBean) instance.clone();
		System.out.println("instanceClone = " + instanceClone.hashCode());
		
		SingletonBean instanceRef = createInstanceUsingReflection(instance);
		System.out.println("instanceRef = " + instanceRef.hashCode());

	}

	public static SingletonBean createInstanceUsingReflection(SingletonBean instance)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		Constructor<?> cons = instance.getClass().getDeclaredConstructor(new Class[0]);
		cons.setAccessible(true);
		SingletonBean copyInstance = (SingletonBean) cons.newInstance();
		return copyInstance;
	}

	public static SingletonBean deserializedInstance(SingletonBean instance)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		ObjectOutput out = new ObjectOutputStream(new FileOutputStream("file.ser"));
		out.writeObject(instance);
		ObjectInput in = new ObjectInputStream(new FileInputStream("file.ser"));
		SingletonBean copyInstance = (SingletonBean) in.readObject();
		in.close();
		out.close();
		return copyInstance;

	}

}
