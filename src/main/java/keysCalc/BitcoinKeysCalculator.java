package keysCalc;

import keysCalc.beans.AppFrame;
import keysCalc.configuration.Conf;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication
public class BitcoinKeysCalculator {
    public static void main(String[] args) {
        //AppFrame af = new AppFrame();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Conf.class);

        AppFrame appFrame = context.getBean(AppFrame.class);

    }
}
