package com.example.WebSocket.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua_parser.Client;
import ua_parser.Parser;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DeviceController {

    private final Parser uaParser = new Parser();

    @GetMapping("/device-details")
    public Map<String, String> getDeviceDetails(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        Client client = uaParser.parse(userAgent);

        String browser = client.userAgent.family + " " + (client.userAgent.major != null ? client.userAgent.major : "");
        String os = client.os.family + " " + (client.os.major != null ? client.os.major : "");
        String device = client.device.family.isEmpty() ? "Desktop" : client.device.family;

        if (device == null || device.equalsIgnoreCase("Other") || device.isEmpty()) {
            device = "Desktop";
        }


        Map<String, String> result = new HashMap<>();
        result.put("browser", browser);
        result.put("os", os);
        result.put("device", device);

        return result;
    }
}
