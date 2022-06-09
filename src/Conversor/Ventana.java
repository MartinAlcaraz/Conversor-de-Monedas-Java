package Conversor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.ComponentOrientation;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Proxy;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.*;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTextField textField_monto;

	/**
	 * Launch the application.
	 * Request request = new Request.Builder()
      .url("https://api.apilayer.com/exchangerates_data/symbols")
      .addHeader
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});


		String apiKey = "Hr0Qt823razxxfVETWoWxSFK3WzllpH4";
		
		try {
			HttpClient cliente = HttpClient.newHttpClient();
			
			String url = "https://api.apilayer.com/exchangerates_data/latest?symbols=ARS,CLP,CNY,COP,EUR,UYU&base=USD";
			HttpRequest solicitud = HttpRequest.newBuilder(URI.create(url)).header("apiKey", apiKey).build();
		
			HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

			if (respuesta.statusCode() > 299){
				System.out.println("Hubo un error en la solicitud");
			}
			Object objFicticio = {
		    "success": true,
		    "timestamp": 1654711624,
		    "base": "USD",
		    "date": "2022-06-08",
		    "rates": {
		        "ARS": 121.458403,
		        "CLP": 821.598176,
		        "CNY": 6.683702,
		        "COP": 3797.53,
		        "EUR": 0.93303,
		        "UYU": 39.693916
		    }
		    }
		
			Object obj = respuesta.body().toString();
			System.out.println(obj);
		
		}catch(Exception e){
			System.out.println(e);
		}
		/** ARS,CLP,CNY,COP,EUR,UYU
		 * 
		 * "ARS": "Argentine Peso",
    		"CLP": "Chilean Peso",
    		"CNY": "Chinese Yuan",
    		"COP": "Colombian Peso",
    		"EUR": "Euro",
    		"USD": "United States Dollar",
    		"UYU": "Uruguayan Peso"    
    		
		 */
		
		
		
//		try {
//			URL url = new URL("http://api.fixer.io/latest?base=USD");
//
//			HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
//
//			conexion.setRequestMethod("GET");
//
//			conexion.setRequestProperty("Accept", "application/json");
//
//			conexion.addRequestProperty("apiKey", apiKey);
//
//			if (conexion.getResponseCode() != 200) {
//				throw new RuntimeException("Intento Fallido : codigo de error HTTP: " + conexion.getResponseCode());
//			}
//
//			BufferedReader br = new BufferedReader(new InputStreamReader((conexion.getInputStream())));
//
//			String output;
//
//			while ((output = br.readLine()) != null) {
//				System.out.println(output);
//			}
//
//			conexion.disconnect();
//
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		setResizable(false);
		setAlwaysOnTop(true);
		setTitle("Conversor De Moneda");
		setBounds(new Rectangle(0, 0, 475, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 300);
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
		panel.setBounds(0, 0, 470, 262);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel Label_resultado = new JLabel("Pesos Argentinos:");
		Label_resultado.setVisible(false);
		Label_resultado.setBounds(76, 204, 292, 32);
		panel.add(Label_resultado);
		Label_resultado.setFont(new Font("Candara", Font.PLAIN, 15));

		JPanel panel_resultado = new JPanel();
		panel_resultado.setForeground(new Color(51, 51, 51));
		panel_resultado.setBackground(new Color(204, 204, 204));
		panel_resultado.setBounds(76, 204, 292, 32);
		panel_resultado.setVisible(false);
		panel.add(panel_resultado);
		panel_resultado.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				String item1 = (String) comboBox.getSelectedItem();
				System.out.println(item1);
				System.out.println("-----");

			}
		});
		comboBox.setName("selector-modena-input");
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setBackground(new Color(153, 153, 153));
		comboBox.setBounds(220, 30, 148, 29);
		comboBox.setFont(new Font("Candara", Font.PLAIN, 14));
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "Dolar", "Euro", "Peso Argentino", "Peso Uruguayo" }));
		panel.add(comboBox);

		JLabel lblNewLabel = new JLabel("Elegir Moneda");
		lblNewLabel.setForeground(new Color(204, 204, 204));
		lblNewLabel.setFont(new Font("Candara", Font.PLAIN, 16));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBounds(76, 30, 107, 29);
		panel.add(lblNewLabel);

		JLabel lblConvertirA = new JLabel("Convertir a");
		lblConvertirA.setForeground(new Color(204, 204, 204));
		lblConvertirA.setToolTipText("");
		lblConvertirA.setFont(new Font("Candara", Font.PLAIN, 16));
		lblConvertirA.setBounds(76, 113, 107, 29);
		panel.add(lblConvertirA);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(
				new DefaultComboBoxModel(new String[] { "Peso Argentino", "Peso Uruguayo", "Dolar", "Euro" }));
		comboBox_1.setName("selector-modena-output");
		comboBox_1.setForeground(new Color(0, 0, 0));
		comboBox_1.setFont(new Font("Candara", Font.PLAIN, 14));
		comboBox_1.setBackground(new Color(153, 153, 153));
		comboBox_1.setBounds(220, 113, 148, 29);
		panel.add(comboBox_1);

		JLabel lblMonto = new JLabel("Monto:");
		lblMonto.setToolTipText("");
		lblMonto.setForeground(new Color(204, 204, 204));
		lblMonto.setFont(new Font("Candara", Font.PLAIN, 16));
		lblMonto.setBounds(76, 73, 107, 29);
		panel.add(lblMonto);

		JButton btnConvertir = new JButton("Convertir");
		btnConvertir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean todobien = true;
				if (todobien && btnConvertir.isEnabled()) {
					panel_resultado.setVisible(true);
					Label_resultado.setVisible(true);

				}
			}
		});
		btnConvertir.setEnabled(false);
		btnConvertir.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

			}
		});
		btnConvertir.setFont(new Font("Candara", Font.PLAIN, 18));
		btnConvertir.setBounds(120, 153, 193, 40);
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

		textField_monto.setBounds(220, 70, 148, 29);
		panel.add(textField_monto);
		textField_monto.setColumns(10);
	}
}
