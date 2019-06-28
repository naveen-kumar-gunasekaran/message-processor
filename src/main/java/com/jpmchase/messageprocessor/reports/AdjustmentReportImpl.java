package com.jpmchase.messageprocessor.reports;

import java.util.Objects;

import com.jpmchase.messageprocessor.beans.ProcessResponseBean;
import com.jpmchase.messageprocessor.constants.AppConstants;

/**
 * This file contains the AdjustmentReportImpl.java class
 * 
 *
 * @author NAVEEN
 * @link https://www.jpmorganchase.com/
 * @copyright 2019 Jpmorganchase
 */
public class AdjustmentReportImpl implements AdjustmentReport {

	/**
	 * This method prints the adjustment logging report after 50th message received
	 * and also terminates the program
	 * 
	 * @param response
	 */
	@Override
	public void printAdjustmentLogReport(ProcessResponseBean response) {
		if (Objects.nonNull(response)) {
			System.out.println(AppConstants.AdjustmentReportConstants.DECORATOR);
			System.out.println(AppConstants.TOTAL_MESSAGES + response.getTotalMessagesProcessed());
			System.out.println(AppConstants.AdjustmentReportConstants.APPLICATION_PAUSE_MSG);
			System.out.println(AppConstants.AdjustmentReportConstants.DECORATOR);
			System.out.println(AppConstants.AdjustmentReportConstants.GENERATE_ADJUSTMENT_REPORT);
			System.out.println(AppConstants.AdjustmentReportConstants.DECORATOR_SMALL);
			response.getAdjustmentLogs().stream().forEach(log -> {
				System.out.println(log);
			});

			if (response.getIsSystemExit())
				System.exit(0);
		}
	}
}
