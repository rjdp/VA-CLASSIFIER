package deedeveloper.examples.va_classifier.ECGAnalysis.detection;

/**
 * QRS detector parameter definitions
 */
public class QRSDetectorParameters 
{
    /** Sample rate in Hz. */
    public int    SAMPLE_RATE ; //250 //200(documentation refenrence)
    public double MS_PER_SAMPLE ; //4
    public int MS10 ;
    public int MS25 ;
    public int MS30 ;
    public int MS80 ;
    public int MS95 ;
    public int MS100 ;
    public int MS125 ;
    public int MS150 ;
    public int MS160 ;
    public int MS175 ;
    public int MS195 ;
    public int MS200 ;
    public int MS220 ;
    public int MS250 ;
    public int MS300 ;
    public int MS360 ;
    public int MS450 ;
    public int MS1000 ;
    public int MS1500 ;
    public int DERIV_LENGTH ;
    public int LPBUFFER_LGTH ;
    public int HPBUFFER_LGTH ;
    /** Moving window integration width. */
    public final int WINDOW_WIDTH ; // 

    public QRSDetectorParameters(int sampleRate) 
    {
        SAMPLE_RATE   = sampleRate ; //250 sample/s //200 sample/s   
        MS_PER_SAMPLE = ( (double) 1000/ (double) SAMPLE_RATE) ; //(250sample/s)4ms   (200 sample/s)5 ms/sample
        MS10          = ((int) (10/ MS_PER_SAMPLE + 0.5)); //(250sample/s)=>3.0Hz(samples)
        MS25          = ((int) (25/MS_PER_SAMPLE + 0.5)); //6.25+0.5=6.75
        MS30          = ((int) (30/MS_PER_SAMPLE + 0.5)) ; //7.5+0.5=8.0
        MS80          = ((int) (80/MS_PER_SAMPLE + 0.5)) ; // 20+0.5= 20.5
        MS95          = ((int) (95/MS_PER_SAMPLE + 0.5)) ; //23.75+0.5=24.25
        MS100         = ((int) (100/MS_PER_SAMPLE + 0.5)) ;//25+ 0.5 = 25.5
        MS125         = ((int) (125/MS_PER_SAMPLE + 0.5)) ;//31.25+0.5=31.75
        MS150         = ((int) (150/MS_PER_SAMPLE + 0.5)) ;//37.5+0.5=38
        MS160         = ((int) (160/MS_PER_SAMPLE + 0.5)) ;//40+0.5=40.5
        MS175         = ((int) (175/MS_PER_SAMPLE + 0.5)) ;//43.75+0.5=44.25
        MS195         = ((int) (195/MS_PER_SAMPLE + 0.5)) ;//48.75+0.5=49.25
        MS200         = ((int) (200/MS_PER_SAMPLE + 0.5)) ;//50+0.5=50.5
        MS220         = ((int) (220/MS_PER_SAMPLE + 0.5)) ;
        MS250         = ((int) (250/MS_PER_SAMPLE + 0.5)) ;
        MS300         = ((int) (300/MS_PER_SAMPLE + 0.5)) ;
        MS360         = ((int) (360/MS_PER_SAMPLE + 0.5)) ; 
        MS450         = ((int) (450/MS_PER_SAMPLE + 0.5)) ;
        MS1000        = SAMPLE_RATE ; //250Hz
        MS1500        = ((int) (1500/MS_PER_SAMPLE)) ;
        DERIV_LENGTH  = MS10 ;  //10ms/4ms = 2.5 samples + 0.5 sample = 3 samples
        LPBUFFER_LGTH = ((int) (2*MS25)) ;  //2*6.75 = 13.5
        HPBUFFER_LGTH = MS125 ; //31.25+ 0.5 = 31.75
        WINDOW_WIDTH  = MS80 ;//20 samples+ 0.5 =20.5
    }

    public static class PreBlankParameters 
    {
        public int PRE_BLANK ;
        /** filter delays plus pre blanking delay */
        public int FILTER_DELAY ;
        public int DER_DELAY ;

        public PreBlankParameters(QRSDetectorParameters qrsDetParas, int preBlank) 
        {
            PRE_BLANK = preBlank ;
            FILTER_DELAY = (int) (((double) qrsDetParas.DERIV_LENGTH/2) + ((double) qrsDetParas.LPBUFFER_LGTH/2 - 1) + (((double) qrsDetParas.HPBUFFER_LGTH-1)/2) + PRE_BLANK) ;
            DER_DELAY = qrsDetParas.WINDOW_WIDTH + FILTER_DELAY + qrsDetParas.MS100 ;
        }
    }
}
