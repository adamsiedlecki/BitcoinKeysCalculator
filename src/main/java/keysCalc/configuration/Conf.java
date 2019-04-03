package keysCalc.configuration;

import keysCalc.beans.Calc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Configuration
@ComponentScan("keysCalc.beans")
public class Conf {

    @Bean
    public Dimension getFrameSize() {
        return new Dimension(700, 700);
    }

    @Bean
    public Calc getCalc() {
        return new Calc();
    }


    @Bean
    public BufferedImage getBitcoinLogo() {
        try {
            return ImageIO.read(new File("src\\main\\resources\\btclogo.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
