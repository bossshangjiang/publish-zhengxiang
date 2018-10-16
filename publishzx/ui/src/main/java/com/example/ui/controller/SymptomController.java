package com.example.ui.controller;

import com.example.ui.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class SymptomController {
    @Autowired
    SymptomService symptomService;

}