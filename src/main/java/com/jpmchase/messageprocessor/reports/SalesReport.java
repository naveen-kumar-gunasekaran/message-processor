package com.jpmchase.messageprocessor.reports;

import com.jpmchase.messageprocessor.beans.ProcessResponseBean;

/**
 * This file contains the SalesReport.java Interface, which is responsible for
 * sales report abstraction.
 * 
 *
 * @author NAVEEN
 * @link https://www.jpmorganchase.com/
 * @copyright 2019 Jpmorganchase
 */
public interface SalesReport {

	void printSalesReport(ProcessResponseBean response);
}
