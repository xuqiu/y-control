package jetty;


import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.log.StdErrLog;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author hongxia.huhx GuahaoJettyServer for Jetty
 */
public class JettyServer {

    public static final int DEFAULT_PORT = 11056;
    /**
     * port
     */
    private int port = DEFAULT_PORT;

    public JettyServer(String port) {
        if(port != null && port.matches("\\d+")){
            this.port = Integer.parseInt(port);
        }else{
            System.out.println("端口设置有误, 将使用默认端口:"+this.port);
        }

    }

    /**
     * 服务器启动。
     */
    public void start() {
        try {
            // 设置Jetty日志
            System.setProperty("org.eclipse.jetty.util.log.class", StdErrLog.class.getName());

            Server server = new Server();

            // 设置连接器
            HttpConfiguration config = new HttpConfiguration();
            config.setRequestHeaderSize(16246);
            ServerConnector connector = new ServerConnector(server, new HttpConnectionFactory(config));
            connector.setReuseAddress(true);
            connector.setIdleTimeout(30000);
            connector.setPort(port);
            server.setConnectors(new Connector[] { connector });

            //            Connector connector = new SelectChannelConnector();
            //            connector.setPort(port);
            //            connector.setRequestHeaderSize(16246);
            //            server.setConnectors(new Connector[] { connector });

            // 设置context
            WebAppContext context = new WebAppContext();
            //        context.setResourceBase("./WebRoot");
            URL webRootLocation = JettyServer.class.getResource("/webapp/");
            System.out.println(webRootLocation.toString());
            //        context.setResourceBase(new ClassPathResource("classpath:templates/publicTemplete.xml"););
            context.setBaseResource(Resource.newResource(webRootLocation));
            context.setContextPath("/");
            // PS:嵌入式的Jetty，应用当前工程的ClassPath，如果不设置将使用WebAppClassLoder，WEB-INF/lib目录加载jar。
            context.setClassLoader(Thread.currentThread().getContextClassLoader());
            context.setParentLoaderPriority(true);
            server.setHandler(context);

            server.start();
            server.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        String port = getArgValue("-port", args);
        JettyServer userServer = new JettyServer(port);

        userServer.start();
    }

    public static String getArgValue(String argName, String[] args) {
        final List<String> argsList = Arrays.asList(args);
        final int portIndex = argsList.indexOf(argName);
        if (argsList.size() > portIndex + 1) {
            return argsList.get(portIndex + 1);
        }
        return null;
    }
}