package servlet.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LogFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Override
    public final void init(final FilterConfig filterConfig) throws ServletException {
        logger.debug("init");
    }

    @Override
    public final void doFilter(final ServletRequest request, final ServletResponse response,
            final FilterChain chain) throws IOException, ServletException {
        logger.debug("-");

        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;

            HashMap<String, Object> m = new HashMap<>();

            Collections.list(req.getHeaderNames()).stream()
                    .forEach(o -> m.put((String) o, req.getHeader((String) o)));

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(m);
            System.out.println(json);

            System.out.println(Collections.list(req.getParameterNames()).size());
        }

        chain.doFilter(request, response);
    }

    @Override
    public final void destroy() {
        logger.debug("destroy");
    }
}
