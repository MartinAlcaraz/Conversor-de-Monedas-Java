package Conversor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {

	public static Ventana frame;

	public static TipoDeCambio dolar;

	public static void main(String[] args) {

		frame = new Ventana();
		frame.setVisible(true);

		setDatos();
			
		getDolar( getDolarActual() );
		String fecha = dolar.getDate();
		actualizarFecha(fecha);
	}
	
	
	// actualiza fecha en labelFecha
	public static void actualizarFecha(String fecha) {

		// formato de fecha esperado
		SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-mm-dd");
		// formato deseado
		SimpleDateFormat formatoSalida = new SimpleDateFormat("dd/mm/yyyy");

		java.util.Date fecha2 = null;
		try {
			fecha2 = formatoEntrada.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frame.labelFecha.setText(formatoSalida.format(fecha2));

	}

	/**
	 * Asigna el valor de la variable dolar
	 */
	public static void getDolar(String respuestaTarifas) {

		JSONObject objJSON = null;
		TipoDeCambio dolares = null;

		Map<String, Double> tarifas = new HashMap<>();
		try {
			objJSON = new JSONObject(respuestaTarifas);

			String base = objJSON.getString("base");
			String date = objJSON.getString("date");
			JSONObject rates = objJSON.getJSONObject("rates");

			Double ARS = rates.getDouble("ARS");
			Double CLP = rates.getDouble("CLP");
			Double CNY = rates.getDouble("CNY");
			Double COP = rates.getDouble("COP");
			Double EUR = rates.getDouble("EUR");
			Double UYU = rates.getDouble("UYU");

			tarifas.put("ARS", ARS);
			tarifas.put("CLP", CLP);
			tarifas.put("CNY", CNY);
			tarifas.put("COP", COP);
			tarifas.put("EUR", EUR);
			tarifas.put("UYU", UYU);

			dolares = new TipoDeCambio(base, date, tarifas);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		dolar = dolares;
	}

	public static void setDatos() {

		SolicitudHttp solicitud = new SolicitudHttp();
		String respuesta = solicitud.solicitarDatosViejos();
		getDolar(respuesta);
		actualizarFecha(dolar.getDate());
	}

	/**
	 * solicita el tipo de cambio actual
	 */
	public static String getDolarActual() {

		SolicitudHttp solicitud = new SolicitudHttp();
		String respuesta = solicitud.solicitarDatosHttp();
		System.out.println("se actualizo el dolar");
		return respuesta;
	}
	
}























