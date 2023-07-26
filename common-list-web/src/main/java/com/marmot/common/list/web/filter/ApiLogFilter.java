package com.marmot.common.list.web.filter;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.marmot.common.list.web.utils.JsonUtils;
import com.marmot.common.list.web.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * API 访问日志 Filter
 *
 * @author tsr
 */
@Slf4j
public class ApiLogFilter extends OncePerRequestFilter {

    private final String applicationName = "";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 获得开始时间
        LocalDateTime beginTime = LocalDateTime.now();
        String requestBody = ServletUtils.isJsonRequest(request) ? ServletUtil.getBody(request) : null;
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper( response);
        try {
            // 继续过滤器
            filterChain.doFilter(requestWrapper, responseWrapper);
            // 正常执行，记录日志
            String responseBody = null;
            if(ServletUtils.isJsonResponse(response)){
                JsonNode jsonNode = JsonUtils.parseTree(responseWrapper.getContentAsByteArray());
                if(jsonNode != null){
                    responseBody = JsonUtils.toJsonString(jsonNode);
                }
            }
            createApiAccessLog(request, beginTime, requestBody,responseBody);

        } catch (Exception ex) {
            // 异常执行，记录日志
            createApiErrorLog(request, beginTime, requestBody, ex);
        }
        responseWrapper.copyBodyToResponse();
    }

    private void createApiAccessLog(HttpServletRequest request, LocalDateTime beginTime, String requestBody, String responseBody) {
        String requestUrl = request.getRequestURL().toString();
        try {
            String ua = ServletUtils.getUserAgent();
            String ip = ServletUtils.getClientIP();
            String queryString = request.getQueryString();
            long time = LocalDateTimeUtil.between(beginTime,LocalDateTime.now()).toMillis();
            log.info("client_ip:{}, ua:{}, req_url:{}, args:{},requestBody:{}, result:{}, time:{}",ip, ua,requestUrl,queryString, requestBody,responseBody,time+" ms");
        } catch (Throwable th) {
            log.error("[createApiAccessLog][url({}) log({}) exception]", request.getRequestURI(), th);
        }
    }
    private void createApiErrorLog(HttpServletRequest request, LocalDateTime beginTime, String requestBody,Exception ex) {
        String requestUrl = request.getRequestURL().toString();
        try {
            String ua = ServletUtils.getUserAgent();
            String ip = ServletUtils.getClientIP();
            String queryString = request.getQueryString();
            long time = LocalDateTimeUtil.between(beginTime,LocalDateTime.now()).toMillis();
            log.error("client_ip:{}, ua:{}, req_url:{}, args:{},requestBody:{},time:{}", ip, ua,requestUrl,queryString, requestBody,time+" ms",ex);
        } catch (Throwable th) {
            log.error("[createApiAccessLog][url({}) log({}) exception]", request.getRequestURI(), th);
        }
    }

}
