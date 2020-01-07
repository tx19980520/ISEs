package wd.pledge.guarantee.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;
import wd.pledge.guarantee.service.AlertService;

@RestController
public class AlertController {

  @Autowired
  private AlertService alertService;

  @RequestMapping(value = "/stream-sse/{name}", method = RequestMethod.GET)
  public SseEmitter streamSseMvc(@PathVariable("name") String name) throws IOException {
    return alertService.send(name);
  }
}
