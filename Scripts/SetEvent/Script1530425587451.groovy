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

WebUI.click(findTestObject('Calendar/calendar'))

WebUI.click(findTestObject('Calendar/day11'))

WebUI.setText(findTestObject('Calendar/title'), '去野餐')

WebUI.setText(findTestObject('Calendar/description'), '真的开心')

WebUI.setText(findTestObject('Calendar/start'), '7/14/2018')

WebUI.setText(findTestObject('Calendar/end'), '7/18/2018')

WebUI.setText(findTestObject('Calendar/remind (1)'), '7/14/2018')

WebUI.click(findTestObject('Calendar/submit'))

