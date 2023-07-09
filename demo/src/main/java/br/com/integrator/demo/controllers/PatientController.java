package br.com.integrator.demo.controllers;

import br.com.integrator.demo.services.PatientSender;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientSender patientSender;

    @GetMapping("/send")
    public void send(@PathParam("name") String name) {
        this.patientSender.send(name);
    }
}
