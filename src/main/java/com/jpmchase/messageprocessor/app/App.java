package com.jpmchase.messageprocessor.app;

import java.util.List;
import java.util.Objects;

import com.jpmchase.messageprocessor.beans.ProcessResponseBean;
import com.jpmchase.messageprocessor.constants.AppConstants;
import com.jpmchase.messageprocessor.objectcreator.TestObjectCreator;
import com.jpmchase.messageprocessor.processor.MessageProcessor;
import com.jpmchase.messageprocessor.processor.MessageProcessorImpl;
import com.jpmchase.messageprocessor.reports.AdjustmentReport;
import com.jpmchase.messageprocessor.reports.AdjustmentReportImpl;
import com.jpmchase.messageprocessor.reports.SalesReport;
import com.jpmchase.messageprocessor.reports.SalesReportImpl;

/**
 * This file contains the App.java model class.
 * 
 *
 * @author NAVEEN
 * @link https://www.jpmorganchase.com/
 * @copyright 2019 Jpmorganchase
 */
public class App {

	static int totalTransactions = 1;
	static int everyTenthTransactions = 1;

	/**
	 * Main method where execution starts main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SalesReport salesReport = new SalesReportImpl();
			AdjustmentReport adjustmentReport = new AdjustmentReportImpl();
			ProcessResponseBean response = new ProcessResponseBean();
			MessageProcessor messageProcessor = new MessageProcessorImpl();
			List<String> messages = TestObjectCreator.getSaleNotificationMessages();
			messages.stream().forEach(eachMsg -> {
				if (everyTenthTransactions == 10) {
					response.setTotalMessagesProcessed(totalTransactions);
					if (Objects.nonNull(response) && Objects.nonNull(response.getSales())) {
						salesReport.printSalesReport(response);
					}
					everyTenthTransactions = 0;
				}

				if (totalTransactions == 50) {
					if (Objects.nonNull(response) && Objects.nonNull(response.getAdjustmentLogs())) {
						response.setTotalMessagesProcessed(totalTransactions);
						if (Objects.nonNull(args) && args.length > 0 && Objects.nonNull(args[0])
								&& Objects.nonNull(Boolean.valueOf((args[0])))) {
							response.setIsSystemExit(Boolean.valueOf((args[0])));
						} else {
							response.setIsSystemExit(true);
						}
						adjustmentReport.printAdjustmentLogReport(response);
					}
				}

				if (!eachMsg.isEmpty()) {
					if (Objects.nonNull(eachMsg) && !eachMsg.isEmpty()) {
						try {
							messageProcessor.processNotificationMessage(eachMsg, response);
						} catch (Exception e) {
							System.out.println("Exception in App {}" + e);
						}
					}
				}
				++totalTransactions;
				++everyTenthTransactions;
			});

			if (--totalTransactions == messages.size()
					&& (Objects.isNull(response) || Objects.isNull(response.getSales()))) {
				System.out.println(AppConstants.NO_VALID_DATA);
			}

			if ((totalTransactions < 10 || totalTransactions > 10)
					&& (Objects.isNull(response) || Objects.isNull(response.getSales()))) {
				System.out.println(AppConstants.TOTAL_MESSAGES + (totalTransactions));
			}
		} catch (Exception e) {
			System.out.println("Exception in App {}" + e);
		}
	}
}
