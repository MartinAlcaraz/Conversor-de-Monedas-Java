package Conversor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 2011049950654770279L;
	private JPanel contentPane;
	private JTextField textField_monto;
	public JLabel labelFecha;

	private JComboBox<String> comboBox_out;
	private JComboBox<String> comboBox_in;

	private JLabel Label_resultado;

	private Map<String, String> mapMonedaCodigo = new HashMap<String, String>();

	/**
	 * Create the frame.
	 */
	public Ventana() {
		setResizable(false);
		setAlwaysOnTop(true);
		setTitle("Conversor De Moneda");
		setBounds(new Rectangle(0, 0, 475, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 370);
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, 0, 475, 300));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 51, 153));
		panel.setForeground(new Color(102, 102, 153));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBounds(new Rectangle(0, 0, 500, 300));
		panel.setBounds(0, 0, 470, 332);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_resultado = new JPanel();
		panel_resultado.setForeground(new Color(51, 51, 51));
		panel_resultado.setBackground(new Color(204, 204, 204));
		panel_resultado.setBounds(76, 247, 304, 32);
		panel_resultado.setVisible(false);

		labelFecha = new JLabel("01/01/2000");
		labelFecha.setFont(new Font("Candara", Font.PLAIN, 17));
		labelFecha.setHorizontalAlignment(SwingConstants.CENTER);
		labelFecha.setBounds(262, 19, 118, 32);
		panel.add(labelFecha);

		JLabel labelTitulo = new JLabel("Fecha del tipo de moneda: ");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Candara", Font.PLAIN, 15));
		labelTitulo.setBounds(76, 19, 176, 32);
		panel.add(labelTitulo);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(76, 19, 304, 32);
		panel.add(panel_1);

		Label_resultado = new JLabel("Pesos Argentinos:");
		Label_resultado.setHorizontalAlignment(SwingConstants.CENTER);
		Label_resultado.setBounds(76, 247, 304, 32);
		panel.add(Label_resultado);
		Label_resultado.setVisible(false);
		Label_resultado.setFont(new Font("Candara", Font.PLAIN, 15));
		panel.add(panel_resultado);
		panel_resultado.setLayout(null);

		comboBox_in = new JComboBox<String>();
		comboBox_in.setName("selector-modena-input");
		comboBox_in.setForeground(new Color(0, 0, 0));
		comboBox_in.setBackground(new Color(153, 153, 153));
		comboBox_in.setBounds(220, 76, 160, 29);
		comboBox_in.setFont(new Font("Candara", Font.PLAIN, 14));
		comboBox_in.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Dolar", "Euro", "Peso Argentino", "Peso Uruguayo" }));
		comboBox_in.setSelectedItem(0);
		panel.add(comboBox_in);

		JLabel lblNewLabel = new JLabel("Elegir Moneda");
		lblNewLabel.setForeground(new Color(204, 204, 204));
		lblNewLabel.setFont(new Font("Candara", Font.PLAIN, 16));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBounds(76, 76, 107, 29);
		panel.add(lblNewLabel);

		JLabel lblConvertirA = new JLabel("Convertir a");
		lblConvertirA.setForeground(new Color(204, 204, 204));
		lblConvertirA.setToolTipText("");
		lblConvertirA.setFont(new Font("Candara", Font.PLAIN, 16));
		lblConvertirA.setBounds(76, 156, 107, 29);
		panel.add(lblConvertirA);

		comboBox_out = new JComboBox<String>();
		comboBox_out.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Peso Argentino", "Euro", "Peso Uruguayo", "Dolar" }));
		comboBox_out.setSelectedItem(0);
		comboBox_out.setName("selector-modena-output");
		comboBox_out.setForeground(new Color(0, 0, 0));
		comboBox_out.setFont(new Font("Candara", Font.PLAIN, 14));
		comboBox_out.setBackground(new Color(153, 153, 153));
		comboBox_out.setBounds(220, 156, 160, 29);
		panel.add(comboBox_out);

		JLabel lblMonto = new JLabel("Monto:");
		lblMonto.setToolTipText("");
		lblMonto.setForeground(new Color(204, 204, 204));
		lblMonto.setFont(new Font("Candara", Font.PLAIN, 16));
		lblMonto.setBounds(76, 116, 107, 29);
		panel.add(lblMonto);

		JButton btnConvertir = new JButton("Convertir");
		btnConvertir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean todobien = true;
				if (todobien && btnConvertir.isEnabled()) {
					panel_resultado.setVisible(true);
					Label_resultado.setVisible(true);
					calcularMoneda();
				}

			}
		});
		btnConvertir.setEnabled(false);
		btnConvertir.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

			}
		});
		btnConvertir.setFont(new Font("Candara", Font.PLAIN, 18));
		btnConvertir.setBounds(119, 196, 210, 40);
		panel.add(btnConvertir);

		textField_monto = new JTextField();
		textField_monto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				String texto = textField_monto.getText();
				boolean tieneComa = texto.contains(",");

				// Verificar si la tecla pulsada no es un digito

				if ((((caracter < '0') || (caracter > '9')) && (caracter != '\b' && caracter != ','))
						|| (caracter == ',' && tieneComa)) {
					e.consume(); // ignorar el evento de teclado
				}

				// '\b' /* corresponde a BACK_SPACE */
			}

		});

		// evento que escucha los cambios en el textField
		textField_monto.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				habilitarBtnConvertir();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				habilitarBtnConvertir();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				habilitarBtnConvertir();
			}

			protected void habilitarBtnConvertir() {
				String text = textField_monto.getText();
				if (text.length() == 0) {
					btnConvertir.setEnabled(false);
				} else {
					btnConvertir.setEnabled(true);
				}
			}
		});

		textField_monto.setBounds(220, 116, 160, 29);
		panel.add(textField_monto);
		textField_monto.setColumns(10);

		// "Dolar", "Euro", "Peso Argentino", "Peso Uruguayo"
		mapMonedaCodigo.put("Dolar", "USD");
		mapMonedaCodigo.put("Euro", "EUR");
		mapMonedaCodigo.put("Peso Argentino", "ARS");
		mapMonedaCodigo.put("Peso Uruguayo", "UYU");

	}

	protected void calcularMoneda() {

		Double montoInicial = Double.parseDouble(textField_monto.getText());
		Double montoDolares;
		Double montoFinal;
		
		// obtiene la moneda seleccionada
		String monedaIn = (String) comboBox_in.getSelectedItem();
		String monedaOut = (String) comboBox_out.getSelectedItem();

		// convierte la moneda al codigo symbol
		String symbolIn = mapMonedaCodigo.get(monedaIn); // Ej. EUR
		String symbolOut = mapMonedaCodigo.get(monedaOut);
		
		if (symbolIn == "USD") {
			montoDolares = montoInicial;			
		} else {
			Double tasaOtro = Main.dolar.getTarifas().get(symbolIn);
			montoDolares = montoInicial / tasaOtro;
		}
		
		Double tasa = 1.0;
		if (symbolOut != "USD") {
			tasa = Main.dolar.getTarifas().get(symbolOut);
		}
		montoFinal = montoDolares * tasa;

		
		NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance();
		
		Label_resultado.setText(monedaOut + ": " + formatoMoneda.format(montoFinal));

	}
}
