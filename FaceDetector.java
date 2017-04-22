/*
 * @author Sheethal Mathew
 * @author Paul Diaz
 * 4/21/17
 * sources :http://stackoverflow.com/questions/28231066/how-to-crop-the-detected-face-image-in-opencv-java
 * source2:https://blog.openshift.com/day-12-opencv-face-detection-for-java-developers/
 */
package faceDetection;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;//instead of HighGui
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
 
public class FaceDetector {
 
    public static void main(String[] args) {
 
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("\nRunning FaceDetector");
 
//        CascadeClassifier faceDetector = new CascadeClassifier(FaceDetector.class.getResource("haarcascade_frontalface_alt.xml").getPath());//.substring(1));
//        Mat image = Imgcodecs
//                .imread(FaceDetector.class.getResource("unnamed.JPG").getPath());
        //"C:/opencv-3.2.0-vc14/opencv/sources/data/haarcascades/haarcascade_frontalface_alt"
//        MatOfRect faceDetections = new MatOfRect();
        CascadeClassifier faceDetector = new CascadeClassifier();
       faceDetector.load("C:/opencv-3.2.0-vc14/opencv/sources/data/haarcascades/haarcascade_frontalface_alt.xml");
        Mat image = Imgcodecs.imread("C:/Users/SHEETHAL/Documents/Sheethal/sjsu/SPRING2017/SE195A(srproject)/faceDetection/IMG_2990.jpg");
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
 
        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
       // System.out.println("image height" + image.height());
       // System.out.println("image width " + image.width());
       // System.out.println("image area =" + image.height() * image.width());
        int image_height=image.height();
        int image_width = image.width();
        double image_area = (double)(image_width*image_height);
        System.out.println("image area = "+ image_area);
        
        double total_face_area = 0.0;
        
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
            System.out.println("RECT x=" + rect.x + "  rect y=" + rect.y + "  rect width="+rect.width + "  rect height = " + rect.height);
            System.out.println("face rect area = " + rect.area());
            total_face_area = total_face_area + rect.area();
        }
        
        System.out.println("totalFaceArea= " + total_face_area);
        
        double percent_face_area = (total_face_area/image_area)*100;
        System.out.println("percent_face_area = " + percent_face_area);
        		
        String filename = "ouput.png";
        System.out.println(String.format("Writing %s", filename));
        Imgcodecs.imwrite(filename, image);
    }
}