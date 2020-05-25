package org.sakki.sample.controller;

import org.sakki.sample.dao.PrestoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/employee")
public class PrestoRestController {

    @Autowired
    private PrestoDAO genomeDao;
    @GetMapping("/test")
    public String getAll() {
        genomeDao.findTotalRecords();
        return "Welcome";
    }


   /* @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EmployeeEvent> getEvents(@PathVariable("id") final Integer empId) {
        final Optional<Employee> employee = employeeRepository.findById(empId);
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
        Flux<EmployeeEvent> employeeEventFlux = Flux.fromStream(
                Stream.generate(() -> new EmployeeEvent(employee.get(),
                        new Date()))
        );


        return Flux.zip(interval, employeeEventFlux)
                .map(Tuple2::getT2);
    }*/
}