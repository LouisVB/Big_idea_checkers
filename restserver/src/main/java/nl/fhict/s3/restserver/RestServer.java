package nl.fhict.s3.restserver;

import nl.fhict.s3.restserver.endpoint.SimpleRestEndpoint;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestServer {

    private static final Logger log = LoggerFactory.getLogger(RestServer.class);

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080); // TODO Config file

        server.setHandler(getJerseyHandler());

        server.start();
        log.info("Server started");

        server.join();
        log.info("Server joined");
    }

    private static Handler getJerseyHandler() {
        log.info("Create Jersey handler");
        ServletContextHandler handler = new ServletContextHandler(
            ServletContextHandler.SESSIONS);

        handler.setContextPath("/");

        ServletHolder servletHolder = handler.addServlet(ServletContainer.class, "/*");
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter("jersey.config.server.provider.classnames",
            SimpleRestEndpoint.class.getCanonicalName());

        return handler;
    }
}
