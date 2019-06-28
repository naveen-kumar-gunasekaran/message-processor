package com.jpmchase.messageprocessor.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.jpmchase.messageprocessor.beans.AdjustmentBean;
import com.jpmchase.messageprocessor.beans.ProcessResponseBean;
import com.jpmchase.messageprocessor.beans.ProductMetaBean;
import com.jpmchase.messageprocessor.beans.SaleBean;
import com.jpmchase.messageprocessor.constants.AppConstants;

/**
 * This file contains the MessageProcessorImpl.java class, contains all the
 * message processing logics such as reading the message/processing and storing
 * for reports
 * 
 *
 * @author NAVEEN
 * @link https://www.jpmorganchase.com/
 * @copyright 2019 Jpmorganchase
 */
public class MessageProcessorImpl implements MessageProcessor {

	private static final Pattern MESSAGETYPE1 = Pattern
			.compile(AppConstants.MessageProcessorConstants.MESSAGE_TYPE1_REGEX_PATTERN);
	private static final Pattern MESSAGETYPE2 = Pattern
			.compile(AppConstants.MessageProcessorConstants.MESSAGE_TYPE2_REGEX_PATTERN);
	private static final Pattern MESSAGETYPE3 = Pattern
			.compile(AppConstants.MessageProcessorConstants.MESSAGE_TYPE3_REGEX_PATTERN);

	/**
	 * This method processes all the messages using regex patterns, because the
	 * assumption is same format of the message will be sent.
	 * processNotificationMessage
	 * 
	 * @param message
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ProcessResponseBean processNotificationMessage(String message, ProcessResponseBean response)
			throws Exception {
		SaleBean saleBean = new SaleBean();
		List<SaleBean> sales = new ArrayList<>();

		/* Message type 1 extraction logics */
		Matcher messageType1Matcher = MESSAGETYPE1.matcher(message);
		if (messageType1Matcher.find()
				&& message.split(AppConstants.MessageProcessorConstants.EMPTY_SINGLE_SPACE).length == 3) {
			saleBean.setProductDisplayValue(
					StringUtils.trimToEmpty(message.split(AppConstants.MessageProcessorConstants.SPACE_AT_SPACE)[1]));
			saleBean.setProductType(
					StringUtils.trimToEmpty(message.split(AppConstants.MessageProcessorConstants.SPACE_AT_SPACE)[0]));
			saleBean.setTotalSalesCount(1);
			saleBean.setProductValue(Integer.parseInt(saleBean.getProductDisplayValue().split(AppConstants.PENCE)[0]));
			saleBean.setProductPricingCurrency(AppConstants.PENCE);
			saleBean.setTotalSalesValue(saleBean.getTotalSalesCount() * saleBean.getProductValue());

		}

		/* Message type 2 extraction logics */
		Matcher messageType2Matcher = MESSAGETYPE2.matcher(message);
		if (messageType2Matcher.find()) {
			saleBean.setProductDisplayValue(StringUtils.trimToEmpty(
					StringUtils.trimToEmpty(message.split(AppConstants.MessageProcessorConstants.SPACE_AT_SPACE)[1])
							.split(AppConstants.MessageProcessorConstants.EACH)[0]));
			saleBean.setProductType(StringUtils.trimToEmpty(
					StringUtils.trimToEmpty(message.split(AppConstants.MessageProcessorConstants.SALES_OF)[1])
							.split(AppConstants.MessageProcessorConstants.SPACE_AT_SPACE)[0]));
			saleBean.setTotalSalesCount(Integer.parseInt(
					StringUtils.trimToEmpty(message.split(AppConstants.MessageProcessorConstants.SALES_OF)[0])));
			saleBean.setProductValue(Integer.parseInt(saleBean.getProductDisplayValue().split(AppConstants.PENCE)[0]));
			saleBean.setProductPricingCurrency(AppConstants.PENCE);
			saleBean.setTotalSalesValue(saleBean.getTotalSalesCount() * saleBean.getProductValue());
		}

		/* Message type 3 extraction logics and adjustment operation logics */
		Matcher messageType3Matcher = MESSAGETYPE3.matcher(message);
		if (messageType3Matcher.find()) {
			AdjustmentBean adjustmentBean = new AdjustmentBean();
			adjustmentBean.setAdjustmentType(StringUtils.trimToEmpty(
					message.split(AppConstants.MessageProcessorConstants.EMPTY_SINGLE_SPACE)[0].toUpperCase()));
			adjustmentBean.setAdjustmentDisplayValue(StringUtils
					.trimToEmpty(message.split(AppConstants.MessageProcessorConstants.EMPTY_SINGLE_SPACE)[1]));
			adjustmentBean.setProductType(StringUtils
					.trimToEmpty(message.split(AppConstants.MessageProcessorConstants.EMPTY_SINGLE_SPACE)[2]));
			adjustmentBean.setAdjustmentValue(
					Integer.parseInt(adjustmentBean.getAdjustmentDisplayValue().split(AppConstants.PENCE)[0]));
			adjustmentBean.setAdjustmentValueCurrency(AppConstants.PENCE);
			if (Objects.nonNull(response.getSales())) {
				// This line updates the sales bean with the adjustment operations.
				sales = updateSalesList(response.getSales(), adjustmentBean, response.getProductMetaMap());
			}
			Map<String, ProductMetaBean> tempMap = new HashMap<>();
			if (Objects.nonNull(response) && Objects.nonNull(response.getProductMetaMap())) {
				response.setProductMetaMap(populateMetaMap(sales, tempMap));
				response.setSales(syncReportMapAndSalesBean(message, response.getSales(), response.getProductMetaMap(),
						response));
			} else {
				response.setProductMetaMap(populateMetaMap(sales, tempMap));
			}
			saleBean = null;
		}

		if (Objects.nonNull(saleBean)) {
			sales.add(saleBean);
			if (Objects.nonNull(response) && Objects.nonNull(response.getProductMetaMap())) {
				response.setProductMetaMap(populateMetaMap(sales, response.getProductMetaMap()));
			} else {
				Map<String, ProductMetaBean> tempMap = new HashMap<>();
				response.setProductMetaMap(populateMetaMap(sales, tempMap));
			}
			if (Objects.nonNull(response.getSales())) {
				response.getSales().addAll(sales);
			} else {
				response.setSales(sales);
			}
		}
		return response;
	}

	/**
	 * This method is used to keep the product and its reporting meta datas like
	 * total sale count and total sale values populateMetaMap
	 * 
	 * @param salesBeans
	 * @param reportMap
	 * @return
	 */
	public Map<String, ProductMetaBean> populateMetaMap(List<SaleBean> salesBeans,
			Map<String, ProductMetaBean> reportMap) {
		salesBeans.stream().forEach(eachValue -> {
			// if the product type is already exists then it just updates the total sales
			// value and total sales count in this map
			if (reportMap.containsKey(eachValue.getProductType())) {
				ProductMetaBean productMetaBean = new ProductMetaBean();
				productMetaBean.setTotalSalesCount(eachValue.getTotalSalesCount()
						+ reportMap.get(eachValue.getProductType()).getTotalSalesCount());
				productMetaBean.setTotalSalesValue(eachValue.getTotalSalesValue()
						+ reportMap.get(eachValue.getProductType()).getTotalSalesValue());
				productMetaBean.setProductType(eachValue.getProductType());
				productMetaBean.setProductValue(
						eachValue.getProductValue() + reportMap.get(eachValue.getProductType()).getProductValue());
				reportMap.put(eachValue.getProductType(), productMetaBean);
			} else {
				ProductMetaBean productMetaBean = new ProductMetaBean();
				productMetaBean.setTotalSalesCount(eachValue.getTotalSalesCount());
				productMetaBean.setTotalSalesValue(eachValue.getTotalSalesValue());
				productMetaBean.setProductType(eachValue.getProductType());
				productMetaBean.setProductValue(eachValue.getProductValue());
				reportMap.put(eachValue.getProductType(), productMetaBean);
			}
		});
		return reportMap;
	}

	/**
	 * This method performs sync operation with the dataset whenever adjustment
	 * operations completed to make sure the datas are in consistent states
	 * syncReportMapAndSalesBean
	 * 
	 * @param salesBeans
	 * @param reportMap
	 * @return
	 */
	private List<SaleBean> syncReportMapAndSalesBean(String adjustmentMsg, List<SaleBean> salesBeans,
			Map<String, ProductMetaBean> reportMap, ProcessResponseBean response) {
		List<SaleBean> finalSaleBean = new ArrayList<>();
		if (Objects.nonNull(salesBeans)) {
			salesBeans.stream().forEach(eachVal -> {
				if (!finalSaleBean.contains(eachVal) && Objects.nonNull(reportMap)
						&& Objects.nonNull(reportMap.get(eachVal.getProductType()))) {
					SaleBean tempBean = new SaleBean();
					tempBean.setProductType(eachVal.getProductType());
					tempBean.setTotalSalesCount(reportMap.get(eachVal.getProductType()).getTotalSalesCount());
					tempBean.setTotalSalesValue(reportMap.get(eachVal.getProductType()).getTotalSalesValue());
					tempBean.setProductValue(reportMap.get(eachVal.getProductType()).getProductValue());
					finalSaleBean.add(tempBean);
					if (Objects.nonNull(response) && Objects.nonNull(response.getAdjustmentLogs())) {
						response.getAdjustmentLogs().add(adjustmentMsg);
					} else {
						List<String> adjustmentLogList = new ArrayList<>();
						adjustmentLogList.add(adjustmentMsg);
						response.setAdjustmentLogs(adjustmentLogList);
					}
				}
			});
		}
		return finalSaleBean;
	}

	/**
	 * This method updates the sales list based on the adjustment message received
	 * updateSalesList
	 * 
	 * @param sales
	 * @param adjustmentBean
	 * @return
	 * @throws Exception
	 */
	private static List<SaleBean> updateSalesList(List<SaleBean> sales, AdjustmentBean adjustmentBean,
			Map<String, ProductMetaBean> productMapResp) throws Exception {
		List<SaleBean> tempSales = new ArrayList<>();
		sales.stream().forEach(eachSale -> {
			if (eachSale.getProductType().equalsIgnoreCase(adjustmentBean.getProductType())) {
				// updating the salesbeans based on the adjustment operations through
				// parameterized constructors
				SaleBean tempBean = new SaleBean(adjustmentBean.getAdjustmentType(), eachSale, adjustmentBean,
						productMapResp);
				tempSales.add(tempBean);
			} else {
				tempSales.add(eachSale);
			}
		});
		return tempSales;
	}
}
