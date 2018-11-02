package com.dslz.beans;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "Orders")
public class Order {
	
	@Id
	@Column(name = "ORDER_ID")
	@SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
	private Integer orderId;
	@Column(name = "DATE_ORDERED")
	private LocalDateTime dateOrdered;
	@Column(name = "DATE_FULFILLED")
	private LocalDateTime dateFulfilled;
	@Column(name = "FEDERAL_TAX")
	private BigDecimal taxFederal;
	@Column(name = "STATE_TAX")
	private BigDecimal taxState;
	@Column(name = "CURRENCY_TAX")
	private BigDecimal taxCurrency;
	@Column(name = "SHIPPED_TO")
	private Integer shipToContactId;
	@Column(name = "BILLED_TO")
	private Integer billToContactId;
	
	public Order() {
		super();
	}
	
	public Order(Integer orderId, LocalDateTime dateOrdered, LocalDateTime dateFulfilled, BigDecimal taxFederal, BigDecimal taxState,
			BigDecimal taxCurrency, Integer shipToContactId, Integer billToContactId) {
		this.orderId = orderId;
		this.dateOrdered = dateOrdered;
		this.dateFulfilled = dateFulfilled;
		this.taxFederal = taxFederal;
		this.taxState = taxState;
		this.taxCurrency = taxCurrency;
		this.shipToContactId = shipToContactId;
		this.billToContactId = billToContactId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(LocalDateTime dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	public LocalDateTime getDateFulfilled() {
		return dateFulfilled;
	}

	public void setDateFulfilled(LocalDateTime dateFulfilled) {
		this.dateFulfilled = dateFulfilled;
	}

	public BigDecimal getTaxFederal() {
		return taxFederal;
	}

	public void setTaxFederal(BigDecimal taxFederal) {
		this.taxFederal = taxFederal;
	}

	public BigDecimal getTaxState() {
		return taxState;
	}

	public void setTaxState(BigDecimal taxState) {
		this.taxState = taxState;
	}

	public BigDecimal getTaxCurrency() {
		return taxCurrency;
	}

	public void setTaxCurrency(BigDecimal taxCurrency) {
		this.taxCurrency = taxCurrency;
	}

	public Integer getShipToContactId() {
		return shipToContactId;
	}

	public void setShipToContactId(Integer shipToContactId) {
		this.shipToContactId = shipToContactId;
	}

	public Integer getBillToContactId() {
		return billToContactId;
	}

	public void setBillToContactId(Integer billToContactId) {
		this.billToContactId = billToContactId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billToContactId == null) ? 0 : billToContactId.hashCode());
		result = prime * result + ((dateFulfilled == null) ? 0 : dateFulfilled.hashCode());
		result = prime * result + ((dateOrdered == null) ? 0 : dateOrdered.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((shipToContactId == null) ? 0 : shipToContactId.hashCode());
		result = prime * result + ((taxCurrency == null) ? 0 : taxCurrency.hashCode());
		result = prime * result + ((taxFederal == null) ? 0 : taxFederal.hashCode());
		result = prime * result + ((taxState == null) ? 0 : taxState.hashCode());
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
		Order other = (Order) obj;
		if (billToContactId == null) {
			if (other.billToContactId != null)
				return false;
		} else if (!billToContactId.equals(other.billToContactId))
			return false;
		if (dateFulfilled == null) {
			if (other.dateFulfilled != null)
				return false;
		} else if (!dateFulfilled.equals(other.dateFulfilled))
			return false;
		if (dateOrdered == null) {
			if (other.dateOrdered != null)
				return false;
		} else if (!dateOrdered.equals(other.dateOrdered))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (shipToContactId == null) {
			if (other.shipToContactId != null)
				return false;
		} else if (!shipToContactId.equals(other.shipToContactId))
			return false;
		if (taxCurrency == null) {
			if (other.taxCurrency != null)
				return false;
		} else if (!taxCurrency.equals(other.taxCurrency))
			return false;
		if (taxFederal == null) {
			if (other.taxFederal != null)
				return false;
		} else if (!taxFederal.equals(other.taxFederal))
			return false;
		if (taxState == null) {
			if (other.taxState != null)
				return false;
		} else if (!taxState.equals(other.taxState))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", dateOrdered=" + dateOrdered + ", dateFulfilled=" + dateFulfilled
				+ ", taxFederal=" + taxFederal + ", taxState=" + taxState + ", taxCurrency=" + taxCurrency
				+ ", shipToContactId=" + shipToContactId + ", billToContactId=" + billToContactId + "]";
	}

}
