package io.javaclasses.fileHub.webservices;

import spark.Request;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * Overrides necessary methods of {@link Request HTTP request object} for unit testing {@link spark.Route routes}.
 * Returns constants value that needed for tests.
 */
public class RequestStub extends Request {

    private final String requestBody;

    private String uploadedFileName;
    private final byte[] uploadedFileContent = new byte[]{2, 6, 5, 4, 3, 2};

    public RequestStub() {

        requestBody = "";
    }

    public RequestStub(String body) {

        requestBody = body;
    }

    public void setUploadedFileName(String uploadedFileName) {

        this.uploadedFileName = uploadedFileName;
    }

    @Override
    public String body() {

        return requestBody;
    }

    @Override
    public String headers(String value) {

        return "Bearer userToken";
    }

    @Override
    public String params(String value) {

        return "id";
    }

    @Override
    public void attribute(String attribute, Object value) {
    }

    public String uploadedFileName() {
        return uploadedFileName;
    }

    public byte[] uploadedFileContent() {
        return uploadedFileContent;
    }

    @Override
    public HttpServletRequest raw() {

        return new HttpServletRequest() {
            @Override
            public Object getAttribute(String name) {
                return null;
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                return null;
            }

            @Override
            public String getCharacterEncoding() {
                return null;
            }

            @Override
            public void setCharacterEncoding(String env) {

            }

            @Override
            public int getContentLength() {
                return 0;
            }

            @Override
            public long getContentLengthLong() {
                return 0;
            }

            @Override
            public String getContentType() {

                return "application/json";
            }

            @Override
            public ServletInputStream getInputStream() {
                return null;
            }

            @Override
            public String getParameter(String name) {
                return null;
            }

            @Override
            public Enumeration<String> getParameterNames() {
                return null;
            }

            @Override
            public String[] getParameterValues(String name) {
                return new String[0];
            }

            @Override
            public Map<String, String[]> getParameterMap() {
                return null;
            }

            @Override
            public String getProtocol() {
                return null;
            }

            @Override
            public String getScheme() {
                return null;
            }

            @Override
            public String getServerName() {
                return null;
            }

            @Override
            public int getServerPort() {
                return 0;
            }

            @Override
            public BufferedReader getReader() {
                return null;
            }

            @Override
            public String getRemoteAddr() {
                return null;
            }

            @Override
            public String getRemoteHost() {
                return null;
            }

            @Override
            public void setAttribute(String name, Object o) {

            }

            @Override
            public void removeAttribute(String name) {

            }

            @Override
            public Locale getLocale() {
                return null;
            }

            @Override
            public Enumeration<Locale> getLocales() {
                return null;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public RequestDispatcher getRequestDispatcher(String path) {
                return null;
            }

            @Override
            public String getRealPath(String path) {
                return null;
            }

            @Override
            public int getRemotePort() {
                return 0;
            }

            @Override
            public String getLocalName() {
                return null;
            }

            @Override
            public String getLocalAddr() {
                return null;
            }

            @Override
            public int getLocalPort() {
                return 0;
            }

            @Override
            public ServletContext getServletContext() {
                return null;
            }

            @Override
            public AsyncContext startAsync() throws IllegalStateException {
                return null;
            }

            @Override
            public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) {
                return null;
            }

            @Override
            public boolean isAsyncStarted() {
                return false;
            }

            @Override
            public boolean isAsyncSupported() {
                return false;
            }

            @Override
            public AsyncContext getAsyncContext() {
                return null;
            }

            @Override
            public DispatcherType getDispatcherType() {
                return null;
            }

            @Override
            public String getAuthType() {
                return null;
            }

            @Override
            public Cookie[] getCookies() {
                return new Cookie[0];
            }

            @Override
            public long getDateHeader(String name) {
                return 0;
            }

            @Override
            public String getHeader(String name) {
                return null;
            }

            @Override
            public Enumeration<String> getHeaders(String name) {
                return null;
            }

            @Override
            public Enumeration<String> getHeaderNames() {
                return null;
            }

            @Override
            public int getIntHeader(String name) {
                return 0;
            }

            @Override
            public String getMethod() {
                return null;
            }

            @Override
            public String getPathInfo() {
                return null;
            }

            @Override
            public String getPathTranslated() {
                return null;
            }

            @Override
            public String getContextPath() {
                return null;
            }

            @Override
            public String getQueryString() {
                return null;
            }

            @Override
            public String getRemoteUser() {
                return null;
            }

            @Override
            public boolean isUserInRole(String role) {
                return false;
            }

            @Override
            public Principal getUserPrincipal() {
                return null;
            }

            @Override
            public String getRequestedSessionId() {
                return null;
            }

            @Override
            public String getRequestURI() {
                return null;
            }

            @Override
            public StringBuffer getRequestURL() {
                return null;
            }

            @Override
            public String getServletPath() {
                return null;
            }

            @Override
            public HttpSession getSession(boolean create) {
                return null;
            }

            @Override
            public HttpSession getSession() {
                return null;
            }

            @Override
            public String changeSessionId() {
                return null;
            }

            @Override
            public boolean isRequestedSessionIdValid() {
                return false;
            }

            @Override
            public boolean isRequestedSessionIdFromCookie() {
                return false;
            }

            @Override
            public boolean isRequestedSessionIdFromURL() {
                return false;
            }

            @Override
            public boolean isRequestedSessionIdFromUrl() {
                return false;
            }

            @Override
            public boolean authenticate(HttpServletResponse response) {
                return false;
            }

            @Override
            public void login(String username, String password) {

            }

            @Override
            public void logout() {

            }

            @Override
            public Collection<Part> getParts() {
                return null;
            }

            @Override
            public Part getPart(String name) {

                return new Part() {
                    @Override
                    public InputStream getInputStream() {

                        return new ByteArrayInputStream(uploadedFileContent);
                    }

                    @Override
                    public String getContentType() {
                        return "application/json";
                    }

                    @Override
                    public String getName() {
                        return null;
                    }

                    @Override
                    public String getSubmittedFileName() {
                        return uploadedFileName;
                    }

                    @Override
                    public long getSize() {
                        return 0;
                    }

                    @Override
                    public void write(String fileName) {

                    }

                    @Override
                    public void delete() {

                    }

                    @Override
                    public String getHeader(String name) {
                        return null;
                    }

                    @Override
                    public Collection<String> getHeaders(String name) {
                        return null;
                    }

                    @Override
                    public Collection<String> getHeaderNames() {
                        return null;
                    }
                };
            }

            @Override
            public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) {
                return null;
            }
        };
    }
}
