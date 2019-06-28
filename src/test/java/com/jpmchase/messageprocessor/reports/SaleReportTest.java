package com.jpmchase.messageprocessor.reports;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.jpmchase.messageprocessor.beans.ProcessResponseBean;
import com.jpmchase.messageprocessor.objectcreator.TestObjectCreator;
import com.jpmchase.messageprocessor.processor.MessageProcessorImpl;

/**
 * This file contains the SaleReportTest.java class
 * 
 *
 * @author NAVEEN
 * @link https://www.jpmorganchase.com/
 * @copyright 2019 Jpmorganchase
 */
@RunWith(JUnit4.class)
public class SaleReportTest {

	static SalesReportImpl salesReport;
	static MessageProcessorImpl messageProccessor;
	static List<String> messages = new ArrayList<>();
	static ProcessResponseBean response = null;

	@BeforeClass
	public static void preConstruct() {
		response = new ProcessResponseBean();
		messageProccessor = new MessageProcessorImpl();
		salesReport = new SalesReportImpl();
		messages = TestObjectCreator.getSaleNotificationMessages();
	}

	@Test
	public void processNotificationMessagesSalesReport() throws Exception {
		messages.stream().forEach(eachMsg -> {
			try {
				response = messageProccessor.processNotificationMessage(eachMsg, response);
				salesReport.printSalesReport(response);
			} catch (Exception e) {
				System.out.println("Exception in Sales Report Test {}" + e);
			}
		});
		assertEquals(true, Objects.nonNull(response) && Objects.nonNull(response.getProductMetaMap()));
	}

}
