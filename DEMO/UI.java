package DEMO;
import gleem.ExaminerViewer;

import glredbook12x.histogram;

import java.awt.*;  

import javax.swing.*;  
import javax.media.opengl.*;  

import biProcessor.Histogram;

import com.jogamp.opengl.util.FPSAnimator;  

import demo1.OBJTest;

import javax.media.opengl.awt.GLCanvas;  
public class UI
{  
    public static void main(String[] args) 
    {  
        final Histogram app = new Histogram();  
    	//final OBJTest app = new OBJTest();  
        app.setSize(800, 600);
	    app.setVisible(true);	
		JFrame f=new JFrame();
		f.add(app);
		f.setTitle("ButtonDemo");
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500,600);
		f.setVisible(true);
	
    }  
}  