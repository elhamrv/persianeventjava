package de.rafiei.persianevents.repository

import de.rafiei.persianevents.model.EventType
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.*

class EventTypeRepositorySpec extends Specification{

    @Shared
    @AutoCleanup
    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, "testjpa")

    IEventTypeRepository eventTypeRepository

    List<EventType> savedEventType

    void setup(){

        eventTypeRepository = embeddedServer.applicationContext.createBean(EventTypeHibernatRepository)

        savedEventType = new ArrayList<>()
    }

    void cleanup(){
        for(EventType item:savedEventType){
            eventTypeRepository.deleteById(item.id)
        }
        savedEventType.clear()
    }

    void "verify new created eventType can be queried by id and equals original eventType"() {

        given: "is a single eventType"
        def eventType = new EventType(1,"name-1",1)

        when: "new document is saved"
        eventTypeRepository.save(eventType)

        savedEventType.add(eventType)

        def eventTypeRead = eventTypeRepository.getById(eventType.getId())

        then: "document should be accessible via id and be equal"
        eventTypeRead != null
        eventTypeRead.name == eventType.name
        eventTypeRead.status == eventType.status

    }

    void "verify eventType can by id deleted"(){
        given: "is a single eventType"
        def eventType = new EventType(1,"name-1",1)

        when: "new document is saved"
        eventTypeRepository.save(eventType)

        def eventTypeRead = eventTypeRepository.getById(eventType.getId())

        then: "document should be accessible via id and be equal"
        eventTypeRead != null
        eventTypeRead.name == eventType.name
        eventTypeRead.status == eventType.status

        when: " document is deleted"
        eventTypeRepository.deleteById(eventType.getId())
        eventTypeRead = eventTypeRepository.getById(eventType.getId())

        then: "document is not accessible"
        eventTypeRead == null
    }

    void "verify eventType can by id updated"(){
        given: "is a single eventType"
        def eventType = new EventType(1,"name-1",1)

        when: "new document is saved"
        eventTypeRepository.save(eventType)
        savedEventType.add(eventType)

        def eventTypeRead = eventTypeRepository.getById(eventType.getId())

        then: "document should be accessible via id and be equal"
        eventTypeRead != null
        eventTypeRead.name == eventType.name
        eventTypeRead.status == eventType.status



        when: " document is updated"

        eventTypeRead.name = "updated name"
        eventTypeRead.status = 2


        eventTypeRepository.update(eventTypeRead)
        def eventTypeUpdated = eventTypeRepository.getById(eventType.getId())

        then: "document is updated"
        eventTypeUpdated != null
        eventTypeUpdated.name == eventTypeRead.name
        eventTypeUpdated.status == eventTypeRead.status

    }

    void "verify get all eventTypes "(){
        given: "are three eventTypes"

        for(int i=1;i<4;i++){
            def eventType = new EventType(i,"name-"+i,i)
            eventTypeRepository.save(eventType)
            savedEventType.add(eventType)
        }

        when: "new documents are saved"

        List<EventType> eventTypeList = eventTypeRepository.getAll()

        then: "list should be three documents"
        eventTypeList != null
        eventTypeList.size() == 3

        eventTypeList.get(0).id==1
        eventTypeList.get(0).name=="name-1"

        eventTypeList.get(1).id==2
        eventTypeList.get(1).name=="name-2"

        eventTypeList.get(2).id==3
        eventTypeList.get(2).name=="name-3"
    }
}
