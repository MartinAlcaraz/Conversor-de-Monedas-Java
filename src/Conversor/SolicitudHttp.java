package Conversor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class SolicitudHttp {
	
	private String solicitarDatosViejos() {

		String  response = "{ \"success\": true,\"timestamp\": 1654711323,\"base\": \"USD\",\"date\": \"2022-06-08\",\"rates\": {\"ARS\": 121.458403,\"CLP\": 821.598176,\"CNY\": 6.683702,\"COP\": 3797.53,\"EUR\": 0.93303,\"UYU\": 39.693916}}";
		return response;
	}

	public String solicitarDatosHttp() {

		String apiKey = "";
		//apiKey = "Hr0Qt823razxxfVETWoWxSFK3WzllpH4";
		
		String stringResponse = null; 
		
		try {
			HttpClient cliente = HttpClient.newHttpClient();
			String url = "https://api.apilayer.com/exchangerates_data/latest?symbols=ARS,CLP,CNY,COP,EUR,UYU&base=USD";
			HttpRequest solicitud = HttpRequest.newBuilder(URI.create(url)).header("apiKey", apiKey).build();
			HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

			if (respuesta.statusCode() < 300){
				stringResponse = respuesta.body().toString();				
			}else {
				//System.out.println("Hubo un error en la solicitud");
			}
						 		
		}catch(Exception e){
			System.out.println(e);
		}finally{
			if (stringResponse == null) {
				stringResponse = solicitarDatosViejos();
			}
		}
		
		return stringResponse;					
	}
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