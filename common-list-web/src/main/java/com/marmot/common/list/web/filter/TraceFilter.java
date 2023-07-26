package com.marmot.common.list.web.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author htc
 * @date 2019/10/24 16:20
 */
public class TraceFilter extends OncePerRequestFilter {

	private static final String TRACE_ID = "trace-id";
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 获得开始时间
		String traceId = request.getHeader(TRACE_ID);
		if(StringUtils.isBlank(traceId)){
			traceId = UUID.randomUUID().toString();
		}
		MDC.put(TRACE_ID, traceId);
		try {
			response.setHeader(TRACE_ID, traceId);
			// 继续过滤器
			filterChain.doFilter(request, response);
		}finally {
			MDC.remove(TRACE_ID);
		}
	}
}
