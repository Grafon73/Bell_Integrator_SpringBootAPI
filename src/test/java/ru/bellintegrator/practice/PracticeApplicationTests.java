package ru.bellintegrator.practice;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;


@RunWith(JUnitPlatform.class)
@SelectClasses({InfoControllerTest.class,
		OrganizationControllerTest.class,
		OfficeControllerTest.class,
		UserControllerTest.class})
class PracticeApplicationTests {}
