package com.common.swing.richclient.test;

import org.springframework.richclient.application.ApplicationLauncher;

public class Main {
	public static void main(String[] args) {
		try {
			String rootContextDirectoryClassPath = "/org/springframework/richclient/samples/petclinic/ctx";
			String startupContextPath = rootContextDirectoryClassPath + "/common/richclient-startup-context.xml";
			String richclientApplicationContextPath = rootContextDirectoryClassPath + "/common/richclient-application-context.xml";
			String businessLayerContextPath = rootContextDirectoryClassPath + "/common/business-layer-context.xml";
			String securityContextPath = rootContextDirectoryClassPath + "/standalone/security-context.xml";
			new ApplicationLauncher(startupContextPath, new String[]
				{ richclientApplicationContextPath, businessLayerContextPath, securityContextPath });
		} catch (Exception e) {
			System.exit(1);
		}
	}
}
