package com.jpmchase.messageprocessor.beans;

import java.io.Serializable;

/**
 * This file contains the AdjustmentBean.java model class.
 * 
 *
 * @author NAVEEN
 * @link https://www.jpmorganchase.com/
 * @copyright 2019 Jpmorganchase
 */
public class AdjustmentBean implements Serializable {

	private static final long serialVersionUID = -8244882829632668062L;

	private String adjustmentType;
	private String productType;
	private Integer adjustmentValue;
	private String adjustmentDisplayValue;
	private String adjustmentValueCurrency;

	public String getAdjustmentDisplayValue() {
		return adjustmentDisplayValue;
	}

	public void setAdjustmentDisplayValue(String adjustmentDisplayValue) {
		this.adjustmentDisplayValue = adjustmentDisplayValue;
	}

	public String getAdjustmentValueCurrency() {
		return adjustmentValueCurrency;
	}

	public void setAdjustmentValueCurrency(String adjustmentValueCurrency) {
		this.adjustmentValueCurrency = adjustmentValueCurrency;
	}

	public void setAdjustmentValue(Integer adjustmentValue) {
		this.adjustmentValue = adjustmentValue;
	}

	public String getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Integer getAdjustmentValue() {
		return adjustmentValue;
	}
}
