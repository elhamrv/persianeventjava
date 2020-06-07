package de.rafiei.persianevents.repository

import de.rafiei.persianevents.model.Events
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.*

import java.sql.Date

class EventsHibernatRepositorySpec extends Specification{

    @Shared
    @AutoCleanup
    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, "testjpa")

    IGenericRepository<Events> eventsRepository

    List<Events> savedEvents

    void setup(){

        eventsRepository = embeddedServer.applicationContext.createBean(EventsHibernatRepository)

        savedEvents = new ArrayList<>()
    }

    void cleanup(){
        for(Events item:savedEvents){
            eventsRepository.deleteById(item.id)
        }
        savedEvents.clear()
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
            eventsRepository.save(event)

            savedEvents.add(event)

            def eventRead = eventsRepository.getById(event.getId())

        then: "document should be accessible via id and be equal"
            eventRead != null
            eventRead.title == event.title
            eventRead.description == event.description
            eventRead.address == event.address
    }

    void "verify event can by id deleted"(){
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
            eventsRepository.save(event)

            def eventRead = eventsRepository.getById(event.getId())

        then: "document should be accessible via id and be equal"
            eventRead != null
            eventRead.title == event.title
            eventRead.description == event.description
            eventRead.address == event.address

        when: " document is deleted"
            eventsRepository.deleteById(event.getId())
            eventRead = eventsRepository.getById(event.getId())

        then: "document is not accessible"
            eventRead == null
    }

    void "verify event can by id updated"(){
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
            eventsRepository.save(event)
            savedEvents.add(event)

            def eventRead = eventsRepository.getById(event.getId())

        then: "document should be accessible via id and be equal"
            eventRead != null
            eventRead.title == event.title
            eventRead.description == event.description
            eventRead.address == event.address


        when: " document is updated"

            eventRead.title == "updated title"
            eventRead.description == "updated desc"
            eventRead.address == "updated addr"

            eventsRepository.update(eventRead)
            def eventUpdated = eventsRepository.getById(event.getId())

        then: "document is updated"
            eventUpdated != null
            eventUpdated.title == eventRead.title
            eventUpdated.description == eventRead.description
            eventUpdated.address == eventRead.address
    }

    void "verify get all events "(){
        given: "are three events"

        for(int i=1;i<4;i++){
            def event = new Events(i,
                    1,
                    "Event-"+i,
                    new Date(Calendar.getInstance().getTimeInMillis()),
                    "Description"+i,
                    "image-1" ,
                    1,
                    "address-1" ,
                    1)
            eventsRepository.save(event)
            savedEvents.add(event)
        }

        when: "new documents are saved"

             List<Events> eventsList = eventsRepository.getAll()

        then: "list should be three documents"
            eventsList != null
            eventsList.size() == 3

            eventsList.get(0).id==1
            eventsList.get(0).title=="Event-1"

            eventsList.get(1).id==2
            eventsList.get(1).title=="Event-2"

            eventsList.get(2).id==3
            eventsList.get(2).title=="Event-3"
        }
}
