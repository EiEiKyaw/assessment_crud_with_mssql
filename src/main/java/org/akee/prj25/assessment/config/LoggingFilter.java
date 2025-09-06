package org.akee.prj25.assessment.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

		try {
			filterChain.doFilter(requestWrapper, responseWrapper);
		} catch (Exception ex) {
			LOGGER.error("EXCEPTION during request processing: method={}, uri={}, message={}", request.getMethod(),
					request.getRequestURI(), ex.getMessage(), ex);
			throw ex;
		} finally {
			logRequest(requestWrapper);
			logResponse(responseWrapper);
			responseWrapper.copyBodyToResponse();
		}
	}

	private void logRequest(ContentCachingRequestWrapper request) {
		String body = new String(request.getContentAsByteArray(), StandardCharsets.UTF_8);
		LOGGER.info("REQUEST: method={}, uri={}, body={}", request.getMethod(), request.getRequestURI(), body);
	}

	private void logResponse(ContentCachingResponseWrapper response) throws IOException {
		String body = new String(response.getContentAsByteArray(), StandardCharsets.UTF_8);
		LOGGER.info("RESPONSE: status={}, body={}", response.getStatus(), body);
	}
}