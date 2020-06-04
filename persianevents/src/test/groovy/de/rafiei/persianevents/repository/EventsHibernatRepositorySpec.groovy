package de.rafiei.persianevents.repository

import de.rafiei.persianevents.model.Events
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.*

import java.sql.Date

class EventsHibernatRepositorySpec  extends Specification{

    @Shared
    @AutoCleanup
    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, "testjpa")

    IEventsRepository eventsRepository


    void setup(){

        eventsRepository = embeddedServer.applicationContext.createBean(EventsHibernatRepository)
    }

    void "verify new created event can be queried by id and equals original event"() {

        given: "is a single event"
            def event = new Events(1,
                    1,
                    "Event-1",
                    new Date(Calendar.getInstance().getTimeInMillis()),
                    "Description-1",
                    "image-1" ,
                    1,
                    "address-1" ,
                    1)

        when: "new document is saved"
            eventsRepository.saveOne(event)

        def eventRead = eventsRepository.getById(event.getId())

        then: "document should be accessible via id and be equal"
            eventRead != null
            eventRead.title == event.title
            eventRead.description == event.description
            eventRead.address == event.address
    }
}
