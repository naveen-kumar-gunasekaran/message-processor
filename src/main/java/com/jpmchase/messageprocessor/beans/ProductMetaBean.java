package com.jpmchase.messageprocessor.beans;

import java.io.Serializable;

/**
 * This file contains the ProductMetaBean.java model class.
 * 
 *
 * @author NAVEEN
 * @link https://www.jpmorganchase.com/
 * @copyright 2019 Jpmorganchase
 */
public class ProductMetaBean implements Serializable {

	private static final long serialVersionUID = 8900412471289837548L;

	private Integer totalSalesCount;

	private Integer totalSalesValue;

	private String productType;

	private Integer productValue;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Integer getProductValue() {
		return productValue;
	}

	public void setProductValue(Integer productValue) {
		this.productValue = productValue;
	}

	public Integer getTotalSalesCount() {
		return totalSalesCount;
	}

	public void setTotalSalesCount(Integer totalSalesCount) {
		this.totalSalesCount = totalSalesCount;
	}

	public Integer getTotalSalesValue() {
		return totalSalesValue;
	}

	public void setTotalSalesValue(Integer totalSalesValue) {
		this.totalSalesValue = totalSalesValue;
	}
}
