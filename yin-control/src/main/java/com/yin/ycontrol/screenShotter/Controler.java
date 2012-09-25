package com.yin.ycontrol.screenShotter;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
/**
* @author bean
* 
*/
public class Controler {

private static Robot robot = null;

static {
   try {
    robot = new Robot();
   } catch (AWTException e) {
    e.printStackTrace();
   }
}
/** 可以弹出QQ */
public void keyBoardDemo() {
   robot.keyPress(KeyEvent.VK_ALT);
   robot.keyPress(KeyEvent.VK_CONTROL);
   robot.keyPress(KeyEvent.VK_Z);
   robot.keyRelease(KeyEvent.VK_Z);
   robot.keyRelease(KeyEvent.VK_CONTROL);
   robot.keyRelease(KeyEvent.VK_ALT);
}
/** 前提是有个最大化的窗口，功能是移动到标题栏，然后拖拽到600,600的位置*/
	public void mouseDemo() {
		try {
			Thread.sleep(1000);
			robot.mousePress(KeyEvent.BUTTON3_MASK);
			robot.mouseRelease(KeyEvent.BUTTON3_MASK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void mousedown(int x,int y) {
		try {
			robot.mouseMove(x, y);
			robot.mousePress(KeyEvent.BUTTON1_MASK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void mouseup(int x,int y) {
		try {
			robot.mouseMove(x, y);
			robot.mouseRelease(KeyEvent.BUTTON1_MASK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void rightClickPosition(int x,int y) {
		try {
			robot.mouseMove(x, y);
			
			robot.mousePress(KeyEvent.BUTTON3_MASK);
			robot.mouseRelease(KeyEvent.BUTTON3_MASK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void keydown(int k) {
		if(k==13){
		   k=KeyEvent.VK_ENTER;
		}
	    robot.keyPress(k);
	}
	public static void keyup(int k) {
		if(k==13){
		   k=KeyEvent.VK_ENTER;
		}
	    robot.keyRelease(k);
	}
/**
   * @param args
   */
public static void main(String[] args) {
	//Controler demo=new Controler();
	rightClickPosition(10,10);
   //demo.mouseDemo();
}

}