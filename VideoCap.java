package testopenCV;
//Link: https://www.youtube.com/watch?v=Dk5zSWElkno&ab_channel=TahaEmara
import org.opencv.core.*;
import org.opencv.imgcodecs.*; //imread, imwrite, etc
import org.opencv.videoio.*;

//import org.opencv.highgui.Highgui;        
//import org.opencv.highgui.VideoCapture;        
        
public class VideoCap {
    public static void main (String args[]){
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	VideoCapture camera = new VideoCapture(0);
    	
    	if(!camera.isOpened()){
    		System.out.println("Error");
    	}
    	else {
    		Mat frame = new Mat();
    	    while(true){
    	    	if (camera.read(frame)){
    	    		System.out.println("Frame Obtained");
    	    		System.out.println("Captured Frame Width " + 
    	    		frame.width() + " Height " + frame.height());
    	    		Imgcodecs.imwrite("camera.jpg", frame);
    	    		System.out.println("OK");
    	    		break;
    	    	}
    	    }	
    	}
    	camera.release();
    }
}   
