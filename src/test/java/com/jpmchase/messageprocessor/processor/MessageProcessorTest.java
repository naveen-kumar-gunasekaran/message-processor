package com.jpmchase.messageprocessor.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import com.jpmchase.messageprocessor.beans.ProcessResponseBean;
import com.jpmchase.messageprocessor.beans.ProductMetaBean;
import com.jpmchase.messageprocessor.beans.SaleBean;
import com.jpmchase.messageprocessor.objectcreator.TestObjectCreator;

import junit.framework.TestCase;

/**
 * This file contains the MessageProcessorTest.java class, junit test cases for
 * Message Processor class
 * 
 *
 * @author NAVEEN
 * @link https://www.jpmorganchase.com/
 * @copyright 2019 Jpmorganchase
 */
@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageProcessorTest extends TestCase {

	static MessageProcessorImpl messageProccessor;

	static ProcessResponseBean response = null;
	static List<String> messages = new ArrayList<>();
	static List<String> onlyOneTypeMessages = new ArrayList<>();

	@BeforeClass
	public static void preConstruct() {
		response = new ProcessResponseBean();
		messageProccessor = new MessageProcessorImpl();
		messages = TestObjectCreator.getSaleNotificationMessages();
		onlyOneTypeMessages = TestObjectCreator.getSingleProductTypeMessages();
	}

	@Test
	public void processNotificationMessageType1Test() throws Exception {
		String message = "orange at 25p";
		response = messageProccessor.processNotificationMessage(message, response);
		assertEquals(true, Objects.nonNull(response) && Objects.nonNull(response.getSales()));
	}

	@Test
	public void processNotificationMessageType2Test() throws Exception {
		String message = "20 sales of orange at 1p each.";
		response = messageProccessor.processNotificationMessage(message, response);
		assertEquals(true, Objects.nonNull(response) && Objects.nonNull(response.getSales()));
	}

	@Test
	public void processNotificationMessageType3TestAddNegative() throws Exception {
		String message = "add 1p apple";
		response = messageProccessor.processNotificationMessage(message, response);
		assertEquals(true, Objects.nonNull(response) && Objects.nonNull(response.getSales()));
	}

	@Test
	public void processNotificationMessages() throws Exception {
		messages.stream().forEach(eachMsg -> {
			try {
				response = messageProccessor.processNotificationMessage(eachMsg, response);
			} catch (Exception e) {
				System.out.println("Exception in Message processing" + e);
			}
		});
		assertEquals(true, Objects.nonNull(response) && Objects.nonNull(response.getSales()));
	}

	@Test
	public void onlyOneTypeMessagesTest() throws Exception {
		onlyOneTypeMessages.stream().forEach(eachMsg -> {
			try {
				response = messageProccessor.processNotificationMessage(eachMsg, response);
			} catch (Exception e) {
				System.out.println("Exception in Message processing {}" + e);
			}
		});

		ProductMetaBean metaBean = response.getProductMetaMap().get("apple");
		assertEquals(true, Objects.nonNull(response) && Objects.nonNull(response.getProductMetaMap().get("apple"))
				&& metaBean.getTotalSalesValue() == 45);
	}

	@Test
	public void onlyOneTypeMessagesTestNegative() throws Exception {
		onlyOneTypeMessages.stream().forEach(eachMsg -> {
			try {
				response = messageProccessor.processNotificationMessage(eachMsg, response);
			} catch (Exception e) {
				System.out.println("Exception occured" + e);
			}
		});

		ProductMetaBean metaBean = response.getProductMetaMap().get("apple");
		assertEquals(false, Objects.nonNull(response) && Objects.nonNull(response.getProductMetaMap().get("apple"))
				&& metaBean.getTotalSalesValue() == 46);
	}

	@Test
	public void processNotificationMessageType3TestMultiply() throws Exception {
		SaleBean saleBean = new SaleBean();
		saleBean.setAdjustmentDone(true);
		saleBean.setProductDisplayValue("27p");
		saleBean.setProductPricingCurrency("p");
		saleBean.setProductType("orange");
		saleBean.setProductValue(12);
		saleBean.setTotalSalesCount(1);
		saleBean.setTotalSalesValue(24);
		List<SaleBean> saleBeanList = new ArrayList<>();
		saleBeanList.add(saleBean);
		response.setSales(saleBeanList);
		String message = "multiply 1p apple";
		response = messageProccessor.processNotificationMessage(message, response);
		assertEquals(true, Objects.nonNull(response) && Objects.nonNull(response.getSales()));
	}

	@Test
	public void processNotificationMessageType3TestSubtract() throws Exception {
		String message = "subtract 1p apple";
		response = messageProccessor.processNotificationMessage(message, response);
		assertEquals(true, Objects.nonNull(response) && Objects.nonNull(response.getSales()));
	}

}
