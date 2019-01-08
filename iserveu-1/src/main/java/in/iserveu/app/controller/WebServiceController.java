package in.iserveu.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import in.iserveu.app.DTO.CustomerDTO;
import in.iserveu.app.DTO.LoginDTO;

@RestController
public class WebServiceController {

	@Value("${base.URL}")
	private String baseURL;

	/**
	 * Login method to obtain auth token
	 * 
	 * @param loginDTO
	 * @return
	 */
	@PostMapping(path = "/login")
	public ResponseEntity<String> doLogin(@RequestBody LoginDTO loginDTO) {
		RestTemplate restTemplate = new RestTemplate();
		String loginUrl = baseURL + "getlogintoken.json ";
		ResponseEntity<String> result = restTemplate.postForEntity(loginUrl, loginDTO, String.class);
		return result;
	}

	/**
	 * Get one customer detail via mobile number and auth token
	 * 
	 * @param mobileNumber
	 * @param authToken
	 * @return
	 */
	@PostMapping(path = "/customer/{mobilenumber}")
	public ResponseEntity<String> getCustomer(@PathVariable("mobilenumber") String mobileNumber,
			@RequestParam("authorization") String authToken) {
		RestTemplate restTemplate = new RestTemplate();
		String getCustomerURL = baseURL + "dmt/getcustomer/" + mobileNumber;

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authToken);

		HttpEntity<?> request = new HttpEntity<>(headers);

		ResponseEntity<String> result = restTemplate.postForEntity(getCustomerURL, request, String.class);
		return result;
	}

	
	@PostMapping(path = "/customer")
	public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO,
			@RequestParam("authorization") String authToken) {
		RestTemplate restTemplate = new RestTemplate();
		String addCustomerURL = baseURL + "dmt/addcustomer";

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authToken);

		HttpEntity<CustomerDTO> request = new HttpEntity<>(customerDTO, headers);

		ResponseEntity<String> result = restTemplate.postForEntity(addCustomerURL, request, String.class);
		return result;
	}

}
