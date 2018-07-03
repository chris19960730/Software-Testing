import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('http://127.0.0.1:8080/index.html')

WebUI.click(findTestObject('Page_Ostrich/loginButton'))

WebUI.setText(findTestObject('Page_Ostrich/input_email'), 'NMSL@tongji.edu.cn')

WebUI.setText(findTestObject('Page_Ostrich/input_password'), '1234')

WebUI.click(findTestObject('Page_Ostrich/login'))

WebUI.click(findTestObject('Page_Ostrich/newProject'))

WebUI.setText(findTestObject('Page_Ostrich/input_projectName'), 'System Test')

WebUI.setText(findTestObject('Page_Ostrich/input_projectDescription'), 'System Testing``')

WebUI.click(findTestObject('Page_Ostrich/createProject'))

WebUI.click(findTestObject('Page_Ostrich/Page_Ostrich/enterenter'))

WebUI.click(findTestObject('Page_Ostrich/shizitaskgroup'))

WebUI.waitForElementVisible(findTestObject('Page_Ostrich/Page_Ostrich (2)/button_'), 5)

WebUI.click(findTestObject('Page_Ostrich/Page_Ostrich (2)/button_'))

WebUI.setText(findTestObject('Page_Ostrich/input_taskgroupName'), 'unit Test`')

WebUI.click(findTestObject('Page_Ostrich/creategroup'))

//WebUI.click(findTestObject('Page_Ostrich/shizitask'))
//
//WebUI.waitForElementClickable(findTestObject('Page_Ostrich/newtask'), 100)
//
//WebUI.click(findTestObject('Page_Ostrich/newtask'))
//
//WebUI.setText(findTestObject('Page_Ostrich/input_taskName'), 'unit Test')
//
////WebUI.waitForElementClickable(findTestObject('Page_Ostrich/createtask'),1000)
//WebUI.click(findTestObject('Page_Ostrich/createtask'))
//
//WebUI.click(findTestObject(null))

