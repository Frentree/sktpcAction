package com.frentree.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.util.config.AppConfig;
import com.frentree.scheduler.SchedulerThread;
import com.frentree.scheduler.TomorowActionThread;

public class pServermain {

	private static String CONF_PATH = null;
	private static String LOGJ_PATH = null;
	private static String PID = null;
	public static String currentDir = null;
	private static BlockingQueue<pServermain> queue = null;

	private static String customer_id = "";
	public static void main(String[] args) {

		currentDir = System.getProperty("user.dir");
		File f = new File(currentDir);
		currentDir = f.getParent().toString();

		LOGJ_PATH = currentDir + "/conf/logbackAction.xml";
		System.setProperty("logback.configurationFile", LOGJ_PATH);
		Logger logger = LoggerFactory.getLogger(pServermain.class);
		//Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		AppConfig.setPID(getPID() + "");
		wrtiePID(AppConfig.getPID());
	

		customer_id = AppConfig.getProperty("config.customer");

		logger.info(">> Process ID :" + AppConfig.getPID());
		logger.info(">> Home Dir :" + AppConfig.currentDir);
		logger.info("SKT Network Schedule Action START");
		logger.info("Crete(date) 2022-03");
		logger.info(" Mod(date) 2024-07-03");
		logger.info(" 1. SKT Network PC Scan ");
		logger.info(" 2. Update : log4j > logback ");
		logger.info(" 3. Update : column change data ");

		new Thread(new SchedulerThread()).start();
		new Thread(new TomorowActionThread()).start();

	}

	public static long getPID() {
		String processName = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
		return Long.parseLong(processName.split("@")[0]);
	}

	public static void wrtiePID(String pid) {
		BufferedWriter out = null;
		try {
			////////////////////////////////////////////////////////////////
			out = new BufferedWriter(new FileWriter(AppConfig.currentDir + "/pcAction"));

			out.write(pid);
			////////////////////////////////////////////////////////////////
		} catch (IOException e) {
			System.err.println(e); 
			System.exit(1);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
