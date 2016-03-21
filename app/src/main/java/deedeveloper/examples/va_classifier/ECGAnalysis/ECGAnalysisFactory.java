package deedeveloper.examples.va_classifier.ECGAnalysis;

import deedeveloper.examples.va_classifier.ECGAnalysis.classification.BDACParameters;
import deedeveloper.examples.va_classifier.ECGAnalysis.classification.BeatAnalyzer;
import deedeveloper.examples.va_classifier.ECGAnalysis.classification.BeatDetectionAndClassification;
import deedeveloper.examples.va_classifier.ECGAnalysis.classification.Classifier;
import deedeveloper.examples.va_classifier.ECGAnalysis.classification.Matcher;
import deedeveloper.examples.va_classifier.ECGAnalysis.classification.NoiseChecker;
import deedeveloper.examples.va_classifier.ECGAnalysis.classification.PostClassifier;
import deedeveloper.examples.va_classifier.ECGAnalysis.classification.RythmChecker;
import deedeveloper.examples.va_classifier.ECGAnalysis.detection.QRSDetector;
import deedeveloper.examples.va_classifier.ECGAnalysis.detection.QRSDetector2;
import deedeveloper.examples.va_classifier.ECGAnalysis.detection.QRSDetectorParameters;
import deedeveloper.examples.va_classifier.ECGAnalysis.detection.QRSFilterer;

/**
 * A factory to create detection of classification objects.
 */
public class ECGAnalysisFactory
{

    /**
     * Creates a BeatDetectorAndClassificator for the given parameters.
     * 
     * @param sampleRate The sampleRate of the ECG samples.
     * @param beatSampleRate The sampleRate for the classification
     * @return An object capable of detection and classification
     */
    public static BeatDetectionAndClassification createBDAC(int sampleRate, int beatSampleRate)
    {
        BDACParameters bdacParameters = new BDACParameters(beatSampleRate) ;
        QRSDetectorParameters qrsDetectorParameters = new QRSDetectorParameters(sampleRate) ;

        BeatAnalyzer beatAnalyzer = new BeatAnalyzer(bdacParameters) ;
        Classifier classifier = new Classifier(bdacParameters, qrsDetectorParameters) ;
        Matcher matcher = new Matcher(bdacParameters, qrsDetectorParameters) ;
        NoiseChecker noiseChecker = new NoiseChecker(qrsDetectorParameters) ;
        PostClassifier postClassifier = new PostClassifier(bdacParameters) ;
        QRSDetector2 qrsDetector = createQRSDetector2(sampleRate) ;
        RythmChecker rythmChecker = new RythmChecker(qrsDetectorParameters) ;
        BeatDetectionAndClassification bdac 
                = new BeatDetectionAndClassification(bdacParameters, qrsDetectorParameters) ;

        classifier.setObjects(matcher, rythmChecker, postClassifier, beatAnalyzer) ;
        matcher.setObjects(postClassifier, beatAnalyzer, classifier) ;
        postClassifier.setObjects(matcher) ;
        bdac.setObjects(qrsDetector, noiseChecker, matcher, classifier) ;

        return bdac;
    }

    /**
     * Create a QRSDetector for the given sampleRate
     * 
     * @param sampleRate The sampleRate of the ECG samples
     * @return A QRSDetector
     */
    public static QRSDetector createQRSDetector(int sampleRate) 
    {
        QRSDetectorParameters qrsDetectorParameters = new QRSDetectorParameters(sampleRate) ;

        QRSDetector qrsDetector = new QRSDetector(qrsDetectorParameters) ;
        QRSFilterer qrsFilterer = new QRSFilterer(qrsDetectorParameters) ;

        qrsDetector.setObjects(qrsFilterer) ;
        return qrsDetector ;
    }

    /**
     * Create a QRSDetector2 for the given sampleRate
     * 
     * @param sampleRate The sampleRate of the ECG samples
     * @return A QRSDetector2
     */
    public static QRSDetector2 createQRSDetector2(int sampleRate) 
    {
            QRSDetectorParameters qrsDetectorParameters = new QRSDetectorParameters(sampleRate) ;

            QRSDetector2 qrsDetector2 = new QRSDetector2(qrsDetectorParameters) ;
            QRSFilterer qrsFilterer = new QRSFilterer(qrsDetectorParameters) ;

            qrsDetector2.setObjects(qrsFilterer) ;
            return qrsDetector2 ;
    }
}
