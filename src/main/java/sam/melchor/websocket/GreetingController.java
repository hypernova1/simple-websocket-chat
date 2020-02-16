package sam.melchor.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage helloMessage) throws InterruptedException {
        Thread.sleep(1000);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(helloMessage.getName()) + "!");
    }


}
