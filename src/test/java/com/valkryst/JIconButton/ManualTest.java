package com.valkryst.JIconButton;

import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class ManualTest {
    private final JIconButton iconButton;
    private final JSlider widthSlider;
    private final JSlider heightSlider;
    private final JCheckBox enabledCheckbox;
    private final JRadioButton fontIconRadio;
    private final JRadioButton squareImageRadio;
    private final JRadioButton wideImageRadio;
    private final JRadioButton tallImageRadio;

    public ManualTest() {
        // Create and set up the main frame
        final JFrame frame = new JFrame("com.valkryst.com.valkryst.JIconButton.JIconButton.com.valkryst.JIconButton.JIconButton Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create the layouts
        frame.setLayout(new BorderLayout());

        // Create the button panel
        final JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Button Display"));
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Create the control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
        frame.add(controlPanel, BorderLayout.EAST);

        // Width slider
        JPanel widthPanel = new JPanel(new BorderLayout());
        widthPanel.setBorder(BorderFactory.createTitledBorder("Width"));
        widthSlider = new JSlider(50, 300, 100);
        widthSlider.setMajorTickSpacing(50);
        widthSlider.setMinorTickSpacing(10);
        widthSlider.setPaintTicks(true);
        widthSlider.setPaintLabels(true);
        widthPanel.add(widthSlider);
        controlPanel.add(widthPanel);

        // Height slider
        JPanel heightPanel = new JPanel(new BorderLayout());
        heightPanel.setBorder(BorderFactory.createTitledBorder("Height"));
        heightSlider = new JSlider(50, 300, 100);
        heightSlider.setMajorTickSpacing(50);
        heightSlider.setMinorTickSpacing(10);
        heightSlider.setPaintTicks(true);
        heightSlider.setPaintLabels(true);
        heightPanel.add(heightSlider);
        controlPanel.add(heightPanel);

        // Enabled checkbox
        JPanel enabledPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        enabledPanel.setBorder(BorderFactory.createTitledBorder("State"));
        enabledCheckbox = new JCheckBox("Enabled", true);
        enabledPanel.add(enabledCheckbox);
        controlPanel.add(enabledPanel);

        // Icon type selection
        JPanel iconTypePanel = new JPanel();
        iconTypePanel.setLayout(new BoxLayout(iconTypePanel, BoxLayout.Y_AXIS));
        iconTypePanel.setBorder(BorderFactory.createTitledBorder("Icon Type"));

        fontIconRadio = new JRadioButton("Font Icon", true);
        squareImageRadio = new JRadioButton("Square Image", false);
        wideImageRadio = new JRadioButton("Wide Image (3:1)", false);
        tallImageRadio = new JRadioButton("Tall Image (1:3)", false);

        ButtonGroup iconGroup = new ButtonGroup();
        iconGroup.add(fontIconRadio);
        iconGroup.add(squareImageRadio);
        iconGroup.add(wideImageRadio);
        iconGroup.add(tallImageRadio);

        iconTypePanel.add(fontIconRadio);
        iconTypePanel.add(squareImageRadio);
        iconTypePanel.add(wideImageRadio);
        iconTypePanel.add(tallImageRadio);
        controlPanel.add(iconTypePanel);

        // Initialize button with a default FontIcon
        FontIcon defaultIcon = FontIcon.of(MaterialDesignP.PLUS, 32, Color.ORANGE);
        iconButton = new JIconButton(defaultIcon);
        iconButton.setPreferredSize(new Dimension(100, 100));
        iconButton.addComponentListener(iconButton); // Add component listener to itself
        buttonPanel.add(iconButton);

        // Add listeners
        widthSlider.addChangeListener(this::updateButtonSize);
        heightSlider.addChangeListener(this::updateButtonSize);
        enabledCheckbox.addActionListener(e -> iconButton.setEnabled(enabledCheckbox.isSelected()));
        fontIconRadio.addActionListener(e -> setFontIcon());
        squareImageRadio.addActionListener(e -> setSquareImageIcon());
        wideImageRadio.addActionListener(e -> setWideImageIcon());
        tallImageRadio.addActionListener(e -> setTallImageIcon());

        // Display the window
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void updateButtonSize(ChangeEvent e) {
        int width = widthSlider.getValue();
        int height = heightSlider.getValue();
        iconButton.setPreferredSize(new Dimension(width, height));
        iconButton.revalidate();
    }

    private void setFontIcon() {
        FontIcon icon = FontIcon.of(MaterialDesignP.PLUS, 32, Color.ORANGE);
        iconButton.setIcon(icon);
    }

    private void setSquareImageIcon() {
        BufferedImage image = createSampleImage(64, 64);
        iconButton.setIcon(new ImageIcon(image));
    }

    private void setWideImageIcon() {
        BufferedImage image = createSampleImage(192, 64);  // 3:1 aspect ratio
        iconButton.setIcon(new ImageIcon(image));
    }

    private void setTallImageIcon() {
        BufferedImage image = createSampleImage(64, 192);  // 1:3 aspect ratio
        iconButton.setIcon(new ImageIcon(image));
    }

    private BufferedImage createSampleImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // Draw a gradient background
        g2d.setPaint(new GradientPaint(0, 0, Color.BLUE, width, height, Color.GREEN));
        g2d.fill(new Rectangle2D.Double(0, 0, width, height));

        // Draw a red circle in the middle
        g2d.setPaint(Color.RED);
        g2d.fillOval(width/4, height/4, width/2, height/2);

        // Draw a grid to help visualize the aspect ratio
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(1f));
        for (int x = 0; x < width; x += 16) {
            g2d.drawLine(x, 0, x, height);
        }
        for (int y = 0; y < height; y += 16) {
            g2d.drawLine(0, y, width, y);
        }

        g2d.dispose();
        return image;
    }

    public static void main(String[] args) {
        // Run the application on the EDT
        SwingUtilities.invokeLater(ManualTest::new);
    }
}