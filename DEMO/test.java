package DEMO;

import gleem.ExaminerViewer;

import imageProcessor.ReadWritePng;
import imageProcessor.Strech;

import java.awt.*;  
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.swing.*;  
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.media.opengl.*;  

import JOGLOBJ_YaoguangLuo.JOGLDrawOBJ;
import JOGLOBJ_YaoguangLuo.JOGLOBJShape;

import com.jogamp.opengl.util.FPSAnimator;  
import com.jogamp.opengl.util.gl2.GLUT;
import javax.media.opengl.awt.GLCanvas;  
import javax.media.opengl.glu.GLU;
public class test extends JPanel implements GLEventListener 
{  
	
	//String str=new String("c://test//assignment02.png");
	private static final long serialVersionUID = 1L;
    static FPSAnimator animator=null;  
    ChangeListener listener; 
    Box sliderBox = new Box(BoxLayout.Y_AXIS);  
    JSlider  sliderx;
    JSlider  slidery;
    JSlider  sliderz;
   // Image3d l2= new Image3d();
    GL2 gl;  
    GLU glu;  
    GLUT glut;  
    float whiteLight[] = {0.2f, 0.2f, 0.2f, 1.0f};  
    float sourceLight[] = {0.8f, 0.8f, 0.8f, 1.0f};  
    float lightPos[] = {0.0f, 0.0f, 0.0f, 1.0f};  
    float moveshape = 0.0f;  
    float fEarthRot = 0.0f; 
    float xrot = 0.0f;  
    float yrot = 0.0f;  
    float zrot = 0.0f;  
    ByteBuffer buffer;
    int[][] g;
    
    JOGLOBJShape shape=null;
    private ExaminerViewer viewer;
    public double t = 1.0d;
    
    
    public test() throws HeadlessException 
    {  
        //super("name"); 
    	this.setLayout(null);
        setSize(600,480);  
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities glcaps=new GLCapabilities(glp);          //脙茠脗篓脙鈥毭偮棵兟⒚⑩偓啪脗垄脙茠脗漏脙垄芒鈥毬偮∶冣�芒鈧劉脙茠脗楼脙垄芒鈥毬⑩�垄脙鈥γ⑩偓鈩⒚兤捗偮っ冣�脗鹿脙垄芒鈥毬偮姑兤捗偮ッ兟⒚⑩�卢脗掳脙炉脗驴脗陆脙茠脗搂脙鈥毭偮冣�脗聽脙茠脗篓脙鈥γ偮犆兟⒚⑩�卢脜隆脙茠脗搂脙鈥γ偮∶兟⒚⑩�卢脜戮脙茠脗陇脙鈥毭偮幻冣�脗拢脙茠脗搂脙鈥毭偮犆兟偮棵偮矫兤捗偮γ冣�芒鈧撁兟⒚⑩�卢脗掳脙茠脗楼脙鈥γ⑩偓鈩⒚冣�脗潞脙茠脗楼脙鈥姑⑩偓聽脙鈥毭偮�  
        GLCanvas canvas=new GLCanvas(glcaps);  
        canvas.addGLEventListener(this);  
        canvas.setBounds(0, 0, 500, 500);
        //canvas.addMouseListener(listener);  
        //getContentPane().
        this.add(canvas);  
        
        animator=new FPSAnimator(canvas,60,true);                 
        centerWindow(this); 
        animator.start();
        sliderx = new JSlider(0 , 360); 
        sliderx.setSnapToTicks(true);  
        sliderx.setPaintTicks(true);  
        sliderx.setMajorTickSpacing(20);  
        sliderx.setMinorTickSpacing(5);  
        sliderx.addChangeListener( 
        		    new ChangeListener()  
        	        {    
        	            public void stateChanged(ChangeEvent event)  
        	            {    
        	                //鍙栧嚭婊戝姩鏉＄殑鍊硷紝骞跺湪鏂囨湰涓樉绀哄嚭鏉� 
        	                JSlider source = (JSlider) event.getSource();  
        	                xrot= source.getValue();  
        	            }
 
        	        });  
        slidery = new JSlider(0 , 360); 
        slidery.setSnapToTicks(true);  
        slidery.setPaintTicks(true);  
        slidery.setMajorTickSpacing(20);  
        slidery.setMinorTickSpacing(5);  
        slidery.addChangeListener( 
        		    new ChangeListener()  
        	        {    
        	            public void stateChanged(ChangeEvent event)  
        	            {    
        	                //鍙栧嚭婊戝姩鏉＄殑鍊硷紝骞跺湪鏂囨湰涓樉绀哄嚭鏉� 
        	                JSlider source = (JSlider) event.getSource();  
        	                yrot= source.getValue();  
        	            }
 
        	        });  
        sliderz = new JSlider(0 , 360); 
        sliderz.setSnapToTicks(true);  
        sliderz.setPaintTicks(true);  
        sliderz.setMajorTickSpacing(20);  
        sliderz.setMinorTickSpacing(5);  
        sliderz.addChangeListener( 
        		    new ChangeListener()  
        	        {    
        	            public void stateChanged(ChangeEvent event)  
        	            {    
        	                //鍙栧嚭婊戝姩鏉＄殑鍊硷紝骞跺湪鏂囨湰涓樉绀哄嚭鏉� 
        	                JSlider source = (JSlider) event.getSource();  
        	                zrot= source.getValue();  
        	            }
 
        	        });  
        sliderBox.add(sliderx);
        sliderBox.add(slidery);
        sliderBox.add(sliderz);
        //getContentPane().
        sliderBox.setBounds(0, 500, 500, 700);
        add(sliderBox);
    }     
    private void centerWindow(Component frame)
    { // 脙茠脗楼脙鈥毭偮泵兟⒚⑩�卢脗娄脙茠脗陇脙鈥毭偮该冣�脗颅脙茠脗搂脙鈥毭偮兟⒚⑩�卢芒鈧拷脙茠脗陇脙鈥毭偮矫兟⒚⑩�卢脜鈥� 
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
        Dimension frameSize = frame.getSize();  
        if (frameSize.width > screenSize.width)  
            frameSize.width = screenSize.width;  
        if (frameSize.height > screenSize.height)  
            frameSize.height = screenSize.height;  
        frame.setLocation((screenSize.width - frameSize.width) >> 1,  
                (screenSize.height - frameSize.height) >> 1);  
    }  
    public void init(GLAutoDrawable drawable) 
    {  
        gl =  drawable.getGL().getGL2();    
        glu = new GLU();  
        glut= new GLUT();       
        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glTexParameteri(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_MIN_FILTER,gl.GL_LINEAR);
        gl.glTexParameteri(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_MAG_FILTER,gl.GL_NEAREST);      
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);                        // The Type Of Depth Testing To Do
        gl.glHint(gl.GL_PERSPECTIVE_CORRECTION_HINT, gl.GL_NICEST);    
	    /*
        ReadWritePng rr = new ReadWritePng();
		try {
			g  = rr.GRNpngRead(str);
			g  = new Strech().Processor(g, 0.2, 0.8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
 
		System.out.println("error");
    }  
    public void display(GLAutoDrawable drawable) 
    {  
     	GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(-1.5f, 0.0f, -6.0f); // translate left and into the screen
        gl.glRotatef(xrot,1.0f,0.0f,0.0f);                     // Rotate On The X Axis
        gl.glRotatef(yrot,0.0f,1.0f,0.0f);                     // Rotate On The Y Axis
        gl.glRotatef(zrot,0.0f,0.0f,1.0f);     
        gl.glPointSize(1);
        gl.glBegin(GL.GL_POINTS);

        /*
        
        for(int i=0;i<g.length;i++)
        	{
      	  for(int j=0;j<g[0].length;j++)
      	  	{
      		  	gl.glColor3f((float)g[i][j]/255,(float)g[i][j]/255,(float)g[i][j]/255);
      		  	gl.glVertex3d(i*0.005, j*0.005,g[i][j]*0.005);
      	  	}
        	}
        	
        */
        
        //new JOGLDrawOBJ(gl,shape,"/demo1/suzanne2.obj",Math.PI/t); 
        new JOGLDrawOBJ(gl,shape,"/demo1/77.obj",Math.PI/t); 
        // new JOGLDrawOBJ(gl,shape,"/demo1/SKeleton.obj",1); 
        //new JOGLDrawOBJ(gl,shape,"/demo1/10.obj",Math.PI/t); 
        t += 1.0d;
   		if(t > 360.d){
   			t = 1.0d;
   			
   		 }
   		
   		
   	  xrot+=0.5f;   
      if(xrot > 360.0f)  
      	xrot = 0.0f;  
      

      yrot+=0.5f;   
      if(yrot > 360.0f)  
      	yrot = 0.0f;  

      zrot+=0.5f;   
      if(zrot > 360.0f)  
      	zrot = 0.0f;  
   		
        
  	gl.glEnd();
    gl.glFlush();
   }  
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) 
    {  
        float fAspect; 
  
        if (height == 0) 
        {  
            height = 1;  
        }      
        gl.glViewport(0, 0, width, height);  
        fAspect = (float) width / height;      
        gl.glMatrixMode(GL2.GL_PROJECTION);  
        gl.glLoadIdentity();       
        glu.gluPerspective(53.0f, fAspect, 1.0, 400.0);  
        gl.glMatrixMode(GL2.GL_MODELVIEW);  
        gl.glLoadIdentity();  
    }  
    public void dispose(GLAutoDrawable arg0) 
    {       
    }  
    public static void main(String[] args) 
    {  
        final test app = new test();      
        app.setSize(800, 600);
		app.setVisible(true);
	
    }  
}  
