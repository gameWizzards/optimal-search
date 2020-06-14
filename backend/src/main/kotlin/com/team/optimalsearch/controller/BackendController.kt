package com.team.optimalsearch.controller

import org.springframework.web.bind.annotation.*
import com.team.optimalsearch.model.Greeting

@RestController
@RequestMapping("/api")
class BackendController() {

    @GetMapping("/ping")
    fun greeting(@RequestParam(value = "name", defaultValue = "Miha") name: String) =
        Greeting("Pong, $name")
}