package com.team.optimalsearch.backend.controller

import org.springframework.web.bind.annotation.*
import com.team.optimalsearch.backend.model.Greeting
import java.util.concurrent.atomic.AtomicLong
import com.team.optimalsearch.backend.jpa.Person
import com.team.optimalsearch.backend.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired

@RestController
@RequestMapping("/api")
class BackendController() {

    @Autowired
    lateinit var personRepository: PersonRepository

    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(), "Hello, $name")

    @GetMapping("/persons")
    fun getPersons() = personRepository.findAll()
}