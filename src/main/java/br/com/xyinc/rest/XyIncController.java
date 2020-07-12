package br.com.xyinc.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class XyIncController {

	@GetMapping
	public void start(HttpServletResponse response) throws IOException {
		response.sendRedirect("/swagger-ui.html");
	}
}
