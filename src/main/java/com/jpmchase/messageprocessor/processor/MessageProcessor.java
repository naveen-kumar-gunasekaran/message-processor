package com.jpmchase.messageprocessor.processor;

import com.jpmchase.messageprocessor.beans.ProcessResponseBean;

/**
 * This file contains the MessageProcessor.java interface, which is the
 * abstraction layer for our actual message processing logics
 * 
 *
 * @author NAVEEN
 * @link https://www.jpmorganchase.com/
 * @copyright 2019 Jpmorganchase
 */
public interface MessageProcessor {

	ProcessResponseBean processNotificationMessage(String msg, ProcessResponseBean response) throws Exception;

}
