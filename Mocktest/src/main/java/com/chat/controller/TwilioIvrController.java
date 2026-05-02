package com.chat.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/twilio")
public class TwilioIvrController {

    @PostMapping(value = "/ivr", produces = MediaType.APPLICATION_XML_VALUE)
    public String handleIvrSelection(
            @RequestParam(value = "Digits", required = false) String digits,
            @RequestParam(value = "studentName", defaultValue = "Student") String studentName) {

        String message = "";
        String language = "hi-IN";

        if ("1".equals(digits)) {
            // Hindi
            language = "hi-IN";
            message = "Namaste " + studentName + "! Yeh Exam prep ki taraf se ek reminder hai. "
                    + "Aapne pichle do din se koi mock test nahi diya hai. "
                    + "Lagatar practice se hi achchhe results aate hain. "
                    + "Kripya login karein aur aaj hi ek test dein. "
                    + "Aapki taiyari ke liye shubhkaamnayein!";
        } else if ("2".equals(digits)) {
            // Kannada
            language = "kn-IN";
            message = "Namaskara " + studentName + "! Idu Exam prep ninda ondu nenapu. "
                    + "Neevu kaleda eradu dinagalinda yavude mock test tegadukondilla. "
                    + "Nirantara abhyasadinda olleya phalitamsa siguttade. "
                    + "Dayavittu login madi mattu ivatte ondu test tegedukolli. "
                    + "Nimma tayarige shubha vagali!";
        } else if ("3".equals(digits)) {
            // Bhojpuri (Using hi-IN TTS engine as Twilio doesn't natively support Bhojpuri voice)
            language = "hi-IN";
            message = "Pranam " + studentName + "! I Exam prep ke taraf se ek yaad dilawe wala sandesh ba. "
                    + "Raua pichla do din se kaunhau mock test naahi dele baani. "
                    + "Lagatar abhyas kaile se hi nik natija aawela. "
                    + "Kirpa karke aaj hi login kari aur ekgo test di. "
                    + "Raur taiyari khatir dher saara shubhkaamna!";
        } else {
            // Invalid input, fallback to Hindi
            language = "hi-IN";
            message = "Namaste " + studentName + "! Yeh Exam prep ki taraf se ek reminder hai. "
                    + "Aapne pichle do din se koi mock test nahi diya hai. "
                    + "Kripya login karein aur aaj hi ek test dein.";
        }

        return "<Response><Say language='" + language + "'>" + message + "</Say></Response>";
    }
}
