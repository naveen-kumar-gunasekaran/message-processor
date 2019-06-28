package com.jpmchase.messageprocessor.app;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.jpmchase.messageprocessor.beans.ProcessResponseBean;
import com.jpmchase.messageprocessor.objectcreator.TestObjectCreator;

/**
 * This file contains the AppTest.java class, which is the junit test file for
 * App.java Main class
 * 
 *
 * @author NAVEEN
 * @link https://www.jpmorganchase.com/
 * @copyright 2019 Jpmorganchase
 */
@RunWith(JUnit4.class)
public class AppTest {

	static ProcessResponseBean response = null;
	static List<String> messages = new ArrayList<>();

	@BeforeClass
	public static void preConstruct() {
		response = new ProcessResponseBean();
		messages = TestObjectCreator.getSaleNotificationMessages();
	}

	@Test
	public void appMainTest() throws Exception {
		String[] input = new String[] { "false" };
		App.main(input);
		assertEquals(true, true);
	}
}
