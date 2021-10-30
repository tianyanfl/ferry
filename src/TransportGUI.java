
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.text.DecimalFormat;


public class TransportGUI extends JFrame implements ChangeListener {

	private Box sliderBox;
	private JSlider autoJSlider;
	private JSlider camperJSlider;
	private JSlider busseJSlider;

	private Box statBox;
	private JFormattedTextField wichtField;
	private JFormattedTextField preisField;
	private Vehicle car = new Car();
	private Vehicle camper = new Camper();
	private Vehicle bus = new Bus();

	private Font font = new Font("Yahei", Font.BOLD, 14);


	public static void main(String[] args) {
		new TransportGUI();
	}

	/**
	 * Construct
	 */
	public TransportGUI() {
		setTitle("");
		setSize(500, 350);

		initCenterJPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * init the center JPanel
	 */
	private void initCenterJPanel() {
		JPanel jPanel = new JPanel();
		JLabel jLabel = new JLabel("Fahern Kalkulation                              ");
		jLabel.setFont(new Font("Yahei", Font.BOLD, 20));
		jLabel.setForeground(Color.RED);
		jPanel.add(jLabel);
		add(jPanel, BorderLayout.NORTH);

		sliderBox = new Box(BoxLayout.Y_AXIS);
		autoJSlider = initSlider(0, 40, "Anzahl Autos");
		camperJSlider = initSlider(0, 20, "Anzahl Camper");
		busseJSlider = initSlider(0, 10, "Anzahl Busse");
		jPanel = new JPanel();
		jPanel.add(sliderBox);
		this.add(jPanel, BorderLayout.CENTER);

		statBox = new Box(BoxLayout.Y_AXIS);
		statBox.setBorder(new EmptyBorder(0,5,10,5));
		try {
			wichtField = new JFormattedTextField(new DecimalFormat("###,##0"));
			wichtField.setForeground(Color.BLACK);
			wichtField.setValue(0);
			addStatBox(wichtField, "Gesamtgewicht");

			DecimalFormat decimalFormat = new DecimalFormat("€ ,##0.00");
			preisField = new JFormattedTextField(decimalFormat);
			preisField.setValue(0);
			preisField.setForeground(Color.BLACK);
			addStatBox(preisField, "Gesamtpreis");
		} catch (Exception e) {
			e.printStackTrace();
		}
		jPanel = new JPanel();
		jPanel.add(statBox);
		this.add(jPanel, BorderLayout.SOUTH);

	}

	/**
	 * add statistic box
	 * @param jTextField
	 * @param description
	 */
	private void addStatBox(JTextField jTextField, String description) {
		JLabel jLabel = new JLabel(description+":");
		jLabel.setPreferredSize(new Dimension(140, 30));
		jLabel.setFont(font);

		jTextField.setPreferredSize(new Dimension(150, 10));
		jTextField.setEditable(false);
		Box box = new Box(BoxLayout.X_AXIS);
		box.setBorder(new EmptyBorder(0,5,10,5));
		box.add(jLabel);
		box.add(jTextField);
		statBox.add(box);
	}

	/**
	 * init slider
	 * @param start slider start
	 * @param end slider end
	 * @param description description
	 * @return the JSlider
	 */
	private JSlider initSlider(int start, int end, String description) {
		JSlider slider = new JSlider();
		// 设置绘制刻度
		slider.setMinimum(start);
		slider.setMaximum(end);
		slider.setPaintTicks(true);
		// 设置主、次刻度的间距
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		// 设置绘制刻度标签，默认绘制数值刻度标签
		slider.setPaintLabels(true);
		slider.setValue(0);
		addSlider(slider, description);
		return slider;
	}

	/**
	 * add slider
	 * @param slider
	 * @param description
	 */
	public void addSlider(JSlider slider, String description) {
		slider.addChangeListener(this);

		JLabel jLabel = new JLabel(description+":");
		jLabel.setPreferredSize(new Dimension(120, 30));
		jLabel.setFont(font);
		Box box = new Box(BoxLayout.X_AXIS);
		box.setBorder(new EmptyBorder(5,5,5,5));
		box.add(jLabel);
		box.add(slider);
		sliderBox.add(box);
	}

	/**
	 * state change
	 * @param e change event
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		int count = autoJSlider.getValue() * car.getWeight()
				+ camperJSlider.getValue() * camper.getWeight()
				+ busseJSlider.getValue() * bus.getWeight();
		int money = autoJSlider.getValue() * car.getPrice()
				+ camperJSlider.getValue() * camper.getPrice()
				+ busseJSlider.getValue() * bus.getPrice();

		if (count >= 300_000) {
			wichtField.setForeground(Color.RED);
		} else {
			wichtField.setForeground(Color.BLACK);
		}
		wichtField.setValue(count);
		String text = wichtField.getText();
		wichtField.setText(text.replace(",", " "));

		preisField.setValue(money);
		text = preisField.getText();
		preisField.setText(text.replace(",", " "));
	}
}
