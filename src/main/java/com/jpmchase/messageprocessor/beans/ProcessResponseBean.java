package com.jpmchase.messageprocessor.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * This file contains the ProcessResponseBean.java class
 * 
 *
 * @author NAVEEN
 * @link https://www.jpmorganchase.com/
 * @copyright 2019 Jpmorganchase
 */
public class ProcessResponseBean implements Serializable {

	private static final long serialVersionUID = 2427749125851653357L;

	private List<SaleBean> sales;

	private Map<String, ProductMetaBean> productMetaMap;
	
	private List<String> adjustmentLogs;
	
	private Integer totalMessagesProcessed;
	
	private Boolean isSystemExit = false;
	
	public Boolean getIsSystemExit() {
		return isSystemExit;
	}

	public void setIsSystemExit(Boolean isSystemExit) {
		this.isSystemExit = isSystemExit;
	}

	public Integer getTotalMessagesProcessed() {
		return totalMessagesProcessed;
	}

	public void setTotalMessagesProcessed(Integer totalMessagesProcessed) {
		this.totalMessagesProcessed = totalMessagesProcessed;
	}

	public List<String> getAdjustmentLogs() {
		return adjustmentLogs;
	}

	public void setAdjustmentLogs(List<String> adjustmentLogs) {
		this.adjustmentLogs = adjustmentLogs;
	}

	public List<SaleBean> getSales() {
		return sales;
	}

	public void setSales(List<SaleBean> sales) {
		this.sales = sales;
	}

	public Map<String, ProductMetaBean> getProductMetaMap() {
		return productMetaMap;
	}

	public void setProductMetaMap(Map<String, ProductMetaBean> productMetaMap) {
		this.productMetaMap = productMetaMap;
	}

}
