package com.jpmchase.messageprocessor.constants;

/**
 * This file contains the AppConstants.java constants class. Created nested
 * static classes to keep all the class level constants in app constants itself
 * 
 *
 * @author NAVEEN
 * @link https://www.jpmorganchase.com/
 * @copyright 2019 Jpmorganchase
 */
public class AppConstants {

	private AppConstants() {

	}

	public static class MessageProcessorConstants {
		private MessageProcessorConstants() {
		}

		public static final String SPACE_AT_SPACE = " at ";
		public static final String SALES_OF = "sales of";
		public static final String EACH = "each";
		public static final String EMPTY_SINGLE_SPACE = " ";
		public static final String MESSAGE_TYPE1_REGEX_PATTERN = "\\s+at+\\s+";
		public static final String MESSAGE_TYPE2_REGEX_PATTERN = "\\s+sales of+\\s+";
		public static final String MESSAGE_TYPE3_REGEX_PATTERN = "^(Add|Subtract|Multiply|add|subtract|multiply)+\\s";
	}

	public static class SalesReportConstants {
		private SalesReportConstants() {

		}

		public static final String PRODUCT = "Product";
		public static final String SALES_QUANTITY = "Sales Quantity";
		public static final String SALES_VALUE = "Sales Value";
		public static final String GENERATE_SALES_REPORT = "Generating the sales report after ";
		public static final String DECORATOR = "=======================================================";
		public static final String SEPARATOR = "---------------------------------";
		public static final String MESSAGE_RECEIVED = " Messages received";
		public static final String PRINT_FORMAT = " %-15s %15s %n";
	}

	public static class AdjustmentReportConstants {

		private AdjustmentReportConstants() {

		}

		public static final String DECORATOR = "=====================================================================";
		public static final String APPLICATION_PAUSE_MSG = "Application is paused now & will not accept anymore messages.";
		public static final String GENERATE_ADJUSTMENT_REPORT = "Generating the adjustment log report";
		public static final String DECORATOR_SMALL = "======================================";

	}

	public static class AdjustmentOperations {

		private AdjustmentOperations() {

		}

		public static final String ADD = "ADD";
		public static final String SUBTRACT = "SUBTRACT";
		public static final String MULTIPLY = "MULTIPLY";

	}

	public static final String PENCE = "p";
	public static final String NO_VALID_DATA = "No Valid Data/Messages Received to print any reports.";
	public static final String TOTAL_MESSAGES = "Total No of Messages Received & Processed => ";
}
