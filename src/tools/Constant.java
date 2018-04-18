package tools;

import java.awt.*;

/**
 * Created by hyx on 2015/12/25.
 */
public class Constant {
    public static final Dimension screenSize;
    static {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    }
}
