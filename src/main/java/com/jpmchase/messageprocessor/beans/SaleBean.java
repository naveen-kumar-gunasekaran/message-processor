package com.jpmchase.messageprocessor.beans;

import java.io.Serializable;
import java.util.Map;

import com.jpmchase.messageprocessor.constants.AppConstants;
import com.jpmchase.messageprocessor.constants.AppConstants.AdjustmentOperations;

/**
 * This file contains the SaleBean.java model class.
 * 
 *
 * @author NAVEEN
 * @link https://www.jpmorganchase.com/
 * @copyright 2019 Jpmorganchase
 */
public class SaleBean implements Serializable {

	private static final long serialVersionUID = 82088836433076467L;

	private String productType;

	private Integer productValue;

	private String productPricingCurrency;

	private String productDisplayValue;

	private Integer totalSalesValue;

	private boolean isAdjustmentDone;

	public boolean isAdjustmentDone() {
		return isAdjustmentDone;
	}

	public void setAdjustmentDone(boolean isAdjustmentDone) {
		this.isAdjustmentDone = isAdjustmentDone;
	}

	public Integer getTotalSalesValue() {
		return totalSalesValue;
	}

	public void setTotalSalesValue(Integer totalSalesValue) {
		this.totalSalesValue = totalSalesValue;
	}

	public String getProductPricingCurrency() {
		return productPricingCurrency;
	}

	public void setProductPricingCurrency(String productPricingCurrency) {
		this.productPricingCurrency = productPricingCurrency;
	}

	public void setProductValue(Integer productValue) {
		this.productValue = productValue;
	}

	private Integer totalSalesCount;

	public Integer getTotalSalesCount() {
		return totalSalesCount;
	}

	public void setTotalSalesCount(Integer totalSalesCount) {
		this.totalSalesCount = totalSalesCount;
	}

	public SaleBean() {

	}

	/**
	 * SaleBean Constructor used for adjustment operations bean manipulations
	 * 
	 * @param type
	 * @param eachSale
	 * @param adjustmentBean
	 * @param productMapResp
	 */
	public SaleBean(String type, SaleBean eachSale, AdjustmentBean adjustmentBean,
			Map<String, ProductMetaBean> productMapResp) {
		Integer productUpdatedValue = 0;
		if (adjustmentBean.getProductType().equalsIgnoreCase(eachSale.getProductType())) {
			switch (adjustmentBean.getAdjustmentType()) {
			case AdjustmentOperations.ADD:
				productUpdatedValue = eachSale.getProductValue() + adjustmentBean.getAdjustmentValue();
				this.setProductType(eachSale.getProductType());
				this.setProductValue(productUpdatedValue);
				this.setProductPricingCurrency(AppConstants.PENCE);
				this.setTotalSalesCount(eachSale.getTotalSalesCount());
				this.setProductDisplayValue(String.valueOf(productUpdatedValue + AppConstants.PENCE));
				this.setTotalSalesValue((eachSale.getTotalSalesCount() * productUpdatedValue));
				this.setAdjustmentDone(true);
				break;
			case AdjustmentOperations.SUBTRACT:
				productUpdatedValue = eachSale.getProductValue() - adjustmentBean.getAdjustmentValue();
				this.setProductType(eachSale.getProductType());
				this.setProductValue(productUpdatedValue);
				this.setProductPricingCurrency(AppConstants.PENCE);
				this.setTotalSalesCount(eachSale.getTotalSalesCount());
				this.setProductDisplayValue(String.valueOf(productUpdatedValue + AppConstants.PENCE));
				this.setTotalSalesValue(
						eachSale.getTotalSalesValue() + (eachSale.getTotalSalesCount() * productUpdatedValue));
				this.setAdjustmentDone(true);
				break;
			case AdjustmentOperations.MULTIPLY:
				productUpdatedValue = eachSale.getProductValue() * adjustmentBean.getAdjustmentValue();
				this.setProductType(eachSale.getProductType());
				this.setProductValue(productUpdatedValue);
				this.setProductPricingCurrency(AppConstants.PENCE);
				this.setTotalSalesCount(eachSale.getTotalSalesCount());
				this.setProductDisplayValue(String.valueOf(productUpdatedValue + AppConstants.PENCE));
				this.setAdjustmentDone(true);
				this.setTotalSalesValue(
						eachSale.getTotalSalesValue() + (eachSale.getTotalSalesCount() * productUpdatedValue));
				break;
			}
		} else {
			this.setProductType(eachSale.getProductType());
			this.setProductValue(eachSale.getProductValue());
			this.setTotalSalesCount(eachSale.getTotalSalesCount());
			this.setProductDisplayValue(eachSale.getProductDisplayValue());
			this.setProductPricingCurrency(eachSale.getProductPricingCurrency());
			this.setTotalSalesValue(eachSale.getTotalSalesValue());
		}
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductDisplayValue() {
		return productDisplayValue;
	}

	public void setProductDisplayValue(String productDisplayValue) {
		this.productDisplayValue = productDisplayValue;
	}

	public Integer getProductValue() {
		return productValue;
	}

	/*
	 * Equals and hashcodes are overrided to identify the unique sale beans by
	 * product types
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaleBean other = (SaleBean) obj;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		return true;
	}

}
