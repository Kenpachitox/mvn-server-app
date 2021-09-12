package org.kenpachitox.http.app;

import lombok.extern.java.Log;
import org.example.http.framework.Server;
import org.example.http.framework.resolver.argument.RequestHandlerMethodArgumentResolver;
import org.example.http.framework.resolver.argument.RequestHeaderHandlerMethodArgumentResolver;
import org.example.http.framework.resolver.argument.ResponseHandlerMethodArgumentResolver;

import java.util.logging.Level;

@Log
public class Main {
  public static final int port = 9999;
  public static void main(String[] args){
    final var server = new Server();
    server.autoRegisterHandlers("org.example.http.app");
    server.addArgumentResolver(
        new RequestHandlerMethodArgumentResolver(),
        new ResponseHandlerMethodArgumentResolver(),
        new RequestHeaderHandlerMethodArgumentResolver()
    );
    new Thread(() -> {
      try {
        Thread.sleep(2000);
        log.log(Level.INFO,"Server's sleep finished");
        server.stop();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    server.listen(port);
  }
}
