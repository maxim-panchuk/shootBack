package lab.panchuk.application.util;

import lab.panchuk.application.entity.Dot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DotHandler {
    private final Dot dot;
    private final double x;
    private final double y;
    private final double r;

    public DotHandler(Dot dot) {
        this.dot = dot;
        this.x = dot.getX();
        this.y = dot.getY();
        this.r = dot.getR();
    }

    public void handle () {
        dot.setResult(String.valueOf(isInArea()));
        String currentTime = new SimpleDateFormat("hh:mm:ss").format(new Date());
        dot.setTime(currentTime);
    }

    private boolean isInArea() {
        return x <= 0 && y >= 0 && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) <= r / 2 ||
                x <= 0 && y <= 0 && x >= -r && y >= -r ||
                x >= 0 && y <= 0 && y >= (x - r);
    }
}
