package com.common.swing.richclient.model;

import com.common.util.annotations.Service;

@Service
public class ServiceA {

	public ServiceA() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
