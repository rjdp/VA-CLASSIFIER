package deedeveloper.examples.va_classifier.ECGAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

import deedeveloper.examples.va_classifier.ECGAnalysis.classification.BeatDetectionAndClassification;
import deedeveloper.examples.va_classifier.ECGAnalysis.classification.BeatDetectionAndClassification.BeatDetectAndClassifyResult;
import deedeveloper.examples.va_classifier.ECGAnalysis.classification.ECGCODES;
import deedeveloper.examples.va_classifier.ECGAnalysis.detection.QRSDetector2;

//import com.jmatio.io.*;
//import com.jmatio.types.*;
/**
 *
 * @author Dee Dee
 */
public class ECG_detect_classification implements Serializable {

    /**
     * @param args the command line arguments
     */
    private static BeatDetectionAndClassification bdac;
    private static QRSDetector2 qrsDetector;
    private static int sampleRate = 128 ;
    //private int[] ecgSamples = ... ;    
    private static String[] ecgSample ;
    private static int[] ecgSampleValue = new int[120000];
    private File file;
    private ECGAnalysisFactory ecgAnalysisFactory;
    
    public ECG_detect_classification() {
        /**.csv comma separated values**/

        ecgAnalysisFactory = new ECGAnalysisFactory();
        qrsDetector = ecgAnalysisFactory.createQRSDetector2(sampleRate);
        System.out.println("ecgAnalysisFactory obj successfully created");
    }

    public void ecgDC_setting(String filepath){
        // String fileName = "doc/16265m-1.csv";
        /**.csv comma separated values**/
        file = new File(filepath);
        System.out.println("Hello ECG");

        QRS_detection(file);
        System.out.println("Finish data detection....");

        QRS_classifier();
        System.out.println("Finish data classification....");
    }

    public void QRS_detection(File file){
        /**** QRS-detection***/

        try{
            Scanner inputStream = new Scanner(file);
            while(inputStream.hasNext()){
                String data = inputStream.next(); //gets a whole line
                ecgSample = data.split(",");
                for (int i = 0; i < ecgSample.length; i++) {
                     ecgSampleValue[i] = Integer.parseInt(ecgSample[i]);
                    System.out.println("sample "+i + ": "+ecgSample[i]);
                    ecgSampleValue[i] = Integer.parseInt(ecgSample[i]);

                    int resultDetection = qrsDetector.QRSDet(ecgSampleValue[i]);
                    if (resultDetection != 0){
                        System.out.println("A QRS-Complex was detected at sample: " + (i-resultDetection));
                    }
                }
              //    System.out.println(i + ". "+ecgSample + "***");
            }
            inputStream.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void QRS_classifier(){
        /****QRS-classification****/
        BeatDetectionAndClassification bdac = ecgAnalysisFactory.createBDAC(sampleRate, sampleRate/2);
        for (int i = 0; i< ecgSampleValue.length; i++) {
            //  System.out.print("...." +ecgSampleValue[i]);
            BeatDetectAndClassifyResult resultClassify = bdac.BeatDetectAndClassify(ecgSampleValue[i]);
            if (resultClassify.samplesSinceRWaveIfSuccess != 0) {
                System.out.println(resultClassify.samplesSinceRWaveIfSuccess);
                int qrsPosition =  i - resultClassify.samplesSinceRWaveIfSuccess;
                if (resultClassify.beatType == ECGCODES.UNKNOWN) {
                    System.out.println("A unknown beat type was detected at sample: " + qrsPosition);
                } else if (resultClassify.beatType == ECGCODES.NORMAL) {
                    System.out.println("A normal beat type was detected at sample: " + qrsPosition);
                } else if (resultClassify.beatType == ECGCODES.PVC) {
                    System.out.println("A premature ventricular contraction was detected at sample: " + qrsPosition);
                }else{
                    System.out.println("Nothing detect");
                }
            }
        }
    }


}
