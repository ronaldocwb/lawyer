package br.com.lawyer.web.api;

import br.com.lawyer.web.annotation.ApiController;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@ApiController
public class TimerController {

    @RequestMapping(value = "/timers/{uuid}", method = RequestMethod.POST)
    public @ResponseBody int inicializarTemporarizador(@PathVariable("uuid") String uuid) {
        return 400;
    }
}
