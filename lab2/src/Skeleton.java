import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;


public class Skeleton extends JPanel implements ActionListener {
    private static int width = 2000;
    private static int height = 800;

    private Graphics2D g2d;
    private Timer timer;
    private double [][] points = {
            { 0, 0 }, { 0, 200 }, { 200, 200 }, { 200, 0 },
    };
    // shape to draw in animation
    private GeneralPath square;
    // for 7th animation
    private double angle = 0;
    // for 9th animation
    private double opacity = 1;
    private double delta = 0.01;

    public Skeleton() {
        square = pathFromPoints(points);
        timer = new Timer(10, this);
        timer.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Computer Graphics lab2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new Skeleton());
        frame.setVisible(true);

        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        width = size.width - insets.left - insets.right - 10;
        height = size.height - insets.top - insets.bottom - 1;
    }

    public void paint(Graphics g) {
        g2d = (Graphics2D) g;
        setRenderRights();
        setBackgroundColor(new Color(193, 193, 193));
        drawBody();
        drawEyes();
        drawEars();
        drawFrame();
        drawAnimation();
    }

    private void drawFrame() {
        g2d.translate(width / 2, 0);
        int frameWeight = 32;
        BasicStroke bs = new BasicStroke(frameWeight, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND);
        g2d.setStroke(bs);
        g2d.setColor(Color.PINK);
        g2d.drawRect(frameWeight / 2, frameWeight / 2,
                width / 2 - frameWeight, height - frameWeight);
    }

    private void drawAnimation() {
        g2d.translate(width / 4, height / 2);
        // for rotation animation
        g2d.rotate(angle, points[0][0], points[0][1]);
        g2d.setColor(Color.RED);
        // for opacity change animation
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                (float)opacity));
        g2d.fill(square);
    }

    public void actionPerformed(ActionEvent e) {
        angle += 0.03;

        if (opacity < 0.01) {
            delta = -delta;
        } else if (opacity > 0.99) {
            delta = -delta;
        }
        opacity += delta;

        repaint();
    }

    private void setRenderRights() {
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
    }

    private void setBackgroundColor(Color color) {
        g2d.setBackground(color);
        g2d.clearRect(0, 0, width, height);
    }

    private GeneralPath pathFromPoints(double[][] points) {
        GeneralPath path = new GeneralPath();
        path.moveTo(points[0][0], points[0][1]);
        for (int k = 1; k < points.length; k++)
            path.lineTo(points[k][0], points[k][1]);
        path.closePath();
        return path;
    }

    private void drawBody() {
        double[][] bodyPoints = {
                //head starts
                {390, 95},
                {390, 230},
                //body starts
                {230, 230},
                {230, 415},
                //pelvis starts
                {325, 415},
                {325, 485},
                //left leg
                {325, 680},
                {500, 680},
                {500, 615},
                {465, 615},
                {465, 485},
                //right leg
                {570, 485},
                {570, 680},
                {735, 680},
                {735, 615},
                {690, 615},
                {690, 485},
                //pelvis ends
                {690, 485},
                {690, 415},
                //body ends
                {760, 415},
                {760, 230},
                //head ends
                {590, 230},
                {590, 95}
        };
        GeneralPath body = pathFromPoints(bodyPoints);
        g2d.setColor(new Color(129, 0, 255));
        g2d.fill(body);
    }

    private void drawEyes() {
        g2d.setPaint(Color.yellow);
        g2d.fillRect(440, 130, 30, 20);
        g2d.fillRect(520, 130, 30, 20);
    }

    private void drawEars() {
        double[][] leftEarPoints = {
                {280, 40},
                {390, 10},
                {390, 95}
        };
        GeneralPath leftEar = pathFromPoints(leftEarPoints);

        double[][] rightEarPoints = {
                {590, 20},
                {590, 95},
                {715, 95}
        };
        GeneralPath rightEar = pathFromPoints(rightEarPoints);

        GradientPaint gp = new GradientPaint(5, 25,
                Color.YELLOW, 20, 2, Color.BLUE, true);
        g2d.setPaint(gp);

        g2d.fill(leftEar);
        g2d.fill(rightEar);
    }
}
