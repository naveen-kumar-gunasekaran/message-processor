package com.jpmchase.messageprocessor.reports;

import com.jpmchase.messageprocessor.beans.ProcessResponseBean;

/**
 * This file contains the AdjustmentReport.java Interface, which is responsible
 * for adjustment report abstraction.
 * 
 *
 * @author NAVEEN
 * @link https://www.jpmorganchase.com/
 * @copyright 2019 Jpmorganchase
 */
public interface AdjustmentReport {
	void printAdjustmentLogReport(ProcessResponseBean response);
}
