// MIT License 2017
// Jay Randez, https://github.com/jayrandez

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class JobView extends JPanel
{
	protected JButton setImageButton;
	protected JTextField imageDescription;
	protected ImagePanel imagePanel;
	
	protected JButton setFileButton;
	protected JTextField fileDescription;
	protected JComboBox<String> zoneSelect;
	protected JButton addZoneButton;
	protected JTextField fieldNameBox;
	protected JComboBox<String> fieldTypeSelect;
	
	protected ButtonGroup radioGroup;
	protected ArrayList<JRadioButton> radioButtons;
	protected JTextField dayField;
	protected JTextField hourField;
	protected JTextField minuteField;
	
	protected JLabel jobNotc;
	protected JButton startButton;
	
	private Job job;
	
	public JobView(Job j) {
		this.job = j;
		buildView();
	}
	
	private void buildView() {
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());

		JPanel imageSurround = new JPanel(new BorderLayout());
		imageSurround.setBorder(new EmptyBorder(10, 10, 0, 10));
		JPanel imageView = buildImageView();
		imageView.setBorder(BorderFactory.createTitledBorder("Source Image"));
		imageSurround.add(imageView, BorderLayout.CENTER);
		this.add(imageSurround, BorderLayout.WEST);
		
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.PAGE_AXIS));
		
		JPanel zoneSurround = new JPanel(new BorderLayout());
		zoneSurround.setBorder(new EmptyBorder(10, 0, 0, 10));
		JPanel zoneView = buildZoneView();
		zoneView.setBorder(BorderFactory.createTitledBorder("Log Output"));
		zoneSurround.add(zoneView, BorderLayout.CENTER);
		rightSide.add(zoneSurround);
		
		JPanel scheduleSurround = new JPanel(new BorderLayout());
		scheduleSurround.setBorder(new EmptyBorder(10, 0, 0, 10));
		JPanel scheduleView = buildScheduleView();
		scheduleView.setBorder(BorderFactory.createTitledBorder("Scheduling"));
		scheduleSurround.add(scheduleView, BorderLayout.CENTER);
		rightSide.add(scheduleSurround);
		
		this.add(rightSide, BorderLayout.CENTER);
		
		JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		actionPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
		this.startButton = new JButton("Start Job");
		this.jobNotc = new JLabel("Log file last written X at X.");
		jobNotc.setVisible(false);
		actionPanel.add(startButton);
		actionPanel.add(jobNotc);
		this.add(actionPanel, BorderLayout.SOUTH);
	}
	
	private JPanel buildImageView() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel topLine = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.imageDescription = new JTextField("Image URL");
		//imageDescription.setEnabled(false);
		imageDescription.setPreferredSize(new Dimension(200, 27));
		this.setImageButton = new JButton("Choose");
		topLine.add(imageDescription);
		topLine.add(setImageButton);
		panel.add(topLine, BorderLayout.NORTH);
		
		JPanel imageSurround = new JPanel(new BorderLayout());
		imageSurround.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.imagePanel = new ImagePanel(job);
		imageSurround.add(imagePanel);
		panel.add(imageSurround, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel buildZoneView() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JPanel topLine = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.fileDescription = new JTextField("Output File");
		fileDescription.setEnabled(false);
		fileDescription.setPreferredSize(new Dimension(200, 27));
		this.setFileButton = new JButton("Choose");
		topLine.add(fileDescription);
		topLine.add(setFileButton);
		stackLine(topLine);
		panel.add(topLine);
		
		JPanel zoneLine = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.zoneSelect = new JComboBox<String>(new String[] {"1"});
		zoneSelect.setPreferredSize(new Dimension(45, 27));
		this.addZoneButton = new JButton("Add");
		zoneLine.add(new JLabel("OCR Region: "));
		zoneLine.add(zoneSelect);
		zoneLine.add(addZoneButton);
		stackLine(zoneLine);
		panel.add(zoneLine);
		
		JPanel nameLine = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.fieldNameBox = new JTextField("Untitled");
		fieldNameBox.setPreferredSize(new Dimension(150, 27));
		nameLine.add(Box.createHorizontalStrut(20));
		nameLine.add(new JLabel(" - Field Name: "));
		nameLine.add(fieldNameBox);
		stackLine(nameLine);
		panel.add(nameLine);
		
		JPanel typeLine = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.fieldTypeSelect = new JComboBox<String>(new String[] {"Number", "Text", "Image (Unimpl)"});
		typeLine.add(Box.createHorizontalStrut(20));
		typeLine.add(new JLabel(" - Field Type: "));
		typeLine.add(fieldTypeSelect);
		stackLine(typeLine);
		panel.add(typeLine);
		
		return panel;
	}
	
	private JPanel buildScheduleView() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		ButtonGroup radioGroup = new ButtonGroup();
		ArrayList<JRadioButton> radioButtons = new ArrayList<JRadioButton>();
		for(int i = 0; i < 4; i++) {
			JRadioButton button = new JRadioButton(null, null, i == 0);
			radioButtons.add(button);
			radioGroup.add(button);
		}
		
		JPanel line0 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		line0.add(radioButtons.get(0));
		line0.add(new JLabel("Once"));
		stackLine(line0);
		panel.add(line0);
		
		JPanel line1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.dayField = new JTextField("0");
		dayField.setPreferredSize(new Dimension(20, 27));
		this.hourField = new JTextField("0");
		hourField.setPreferredSize(new Dimension(20, 27));
		this.minuteField = new JTextField("30");
		minuteField.setPreferredSize(new Dimension(20, 27));
		line1.add(radioButtons.get(1));
		line1.add(new JLabel("Every"));
		line1.add(dayField);
		line1.add(new JLabel("days,"));
		line1.add(hourField);
		line1.add(new JLabel("hours, and"));
		line1.add(minuteField);
		line1.add(new JLabel("minutes."));
		stackLine(line1);
		panel.add(line1);
		
		JPanel line2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		line2.add(radioButtons.get(2));
		line2.add(new JLabel("Beginning of every hour."));
		stackLine(line2);
		panel.add(line2);
		
		JPanel line3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		line3.add(radioButtons.get(3));
		line3.add(new JLabel("Beginning of every minute."));
		stackLine(line3);
		panel.add(line3);
		
		return panel;
	}
	
	private void stackLine(JPanel line) {
		line.setMaximumSize(line.getPreferredSize());
		line.setAlignmentX(Component.LEFT_ALIGNMENT);
	}
}
