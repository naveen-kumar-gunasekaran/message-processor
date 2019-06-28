package com.jpmchase.messageprocessor.reports;

import java.util.Map;
import java.util.Objects;

import com.jpmchase.messageprocessor.beans.ProcessResponseBean;
import com.jpmchase.messageprocessor.beans.ProductMetaBean;
import com.jpmchase.messageprocessor.constants.AppConstants;

/**
 * This file contains the SalesReportImpl.java class
 * 
 *
 * @author NAVEEN
 * @link https://www.jpmorganchase.com/
 * @copyright 2019 Jpmorganchase
 */
public class SalesReportImpl implements SalesReport {

	/**
	 * This method prints the sales report for every 10th message received
	 * printSalesReport
	 * 
	 * @param response
	 */
	public void printSalesReport(ProcessResponseBean response) {
		if (Objects.nonNull(response)) {
			int separatorCounter = 0;
			System.out.println(AppConstants.SalesReportConstants.DECORATOR);
			System.out.println(AppConstants.TOTAL_MESSAGES + response.getTotalMessagesProcessed());
			System.out.println(AppConstants.SalesReportConstants.GENERATE_SALES_REPORT
					+ response.getTotalMessagesProcessed() + AppConstants.SalesReportConstants.MESSAGE_RECEIVED);
			System.out.println(AppConstants.SalesReportConstants.DECORATOR);
			for (Map.Entry<String, ProductMetaBean> metaInfo : response.getProductMetaMap().entrySet()) {
				System.out.printf(AppConstants.SalesReportConstants.PRINT_FORMAT,
						AppConstants.SalesReportConstants.PRODUCT, metaInfo.getKey());
				System.out.printf(AppConstants.SalesReportConstants.PRINT_FORMAT,
						AppConstants.SalesReportConstants.SALES_QUANTITY, metaInfo.getValue().getTotalSalesCount());
				System.out.printf(AppConstants.SalesReportConstants.PRINT_FORMAT,
						AppConstants.SalesReportConstants.SALES_VALUE,
						metaInfo.getValue().getTotalSalesValue() + AppConstants.PENCE);
				if(response.getProductMetaMap().size()!=(separatorCounter+1)) {
				System.out.println(AppConstants.SalesReportConstants.SEPARATOR);
				}
				separatorCounter++;
			}
		}
	}
}
