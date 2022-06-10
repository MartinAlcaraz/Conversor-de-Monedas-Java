package Conversor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class TipoDeCambio {

	private String base; 
	private String date; 
	private Map<String, Double> tarifas =  new HashMap<>();
	
	public TipoDeCambio(String base, String date, Map<String, Double> tarifas) {
		this.base = base;
		this.date = date;
		this.tarifas = tarifas;
	}

	public String getBase() {
		return this.base;
	}

	public String getDate() {
		return this.date;
	}

	public Map<String, Double> getTarifas() {
		
		return Collections.unmodifiableMap(this.tarifas);
	}
		
	
}
