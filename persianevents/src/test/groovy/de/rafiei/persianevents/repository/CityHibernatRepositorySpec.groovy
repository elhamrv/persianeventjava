package de.rafiei.persianevents.repository


import de.rafiei.persianevents.model.City
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.*

class CityHibernatRepositorySpec extends Specification {

    @Shared
    @AutoCleanup
    private EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, "testjpa")

    ICityRepository cityRepository

    List<City> savedCity

    void setup(){

        cityRepository = embeddedServer.applicationContext.createBean(CityHibernatRepository)

        savedCity = new ArrayList<>()
    }

    void cleanup(){
        for(City item:savedCity){
            cityRepository.deleteById(item.id)
        }
        savedCity.clear()
    }

    void "verify new created city can be queried by id and equals original city"() {

        given: "is a single city"
        def city = new City(1,"name-1",1,1)

        when: "new document is saved"
        cityRepository.save(city)

        savedCity.add(city)

        def cityRead = cityRepository.getById(city.getId())

        then: "document should be accessible via id and be equal"
        cityRead != null
        cityRead.name == city.name
        cityRead.code == city.code
        cityRead.countryId == city.countryId
    }

    void "verify city can by id deleted"(){
        given: "is a single city"
        def city = new City(1,"name-1",1,1)

        when: "new document is saved"
        cityRepository.save(city)

        def cityRead = cityRepository.getById(city.getId())

        then: "document should be accessible via id and be equal"
        cityRead != null
        cityRead.name == city.name
        cityRead.code == city.code
        cityRead.countryId == city.countryId

        when: " document is deleted"
        cityRepository.deleteById(city.getId())
        cityRead = cityRepository.getById(city.getId())

        then: "document is not accessible"
        cityRead == null
    }

    void "verify city can by id updated"(){
        given: "is a single city"
        def city = new City(1,"name-1",1,1)

        when: "new document is saved"
        cityRepository.save(city)
        savedCity.add(city)

        def cityRead = cityRepository.getById(city.getId())

        then: "document should be accessible via id and be equal"
        cityRead != null
        cityRead.name == city.name
        cityRead.code == city.code
        cityRead.countryId == city.countryId


        when: " document is updated"

            cityRead.name = "updated name"
            cityRead.code = 2


            cityRepository.update(cityRead)
        def cityUpdated = cityRepository.getById(city.getId())

        then: "document is updated"
        cityUpdated != null
        cityUpdated.name == cityRead.name
        cityUpdated.code == cityRead.code

    }

    void "verify get all cities "(){
        given: "are three cities"

        for(int i=1;i<4;i++){
            def city = new City(i,"name-"+i,i,1)
            cityRepository.save(city)
            savedCity.add(city)
        }

        when: "new documents are saved"

        List<City> cityList = cityRepository.getAll()

        then: "list should be three documents"
        cityList != null
        cityList.size() == 3

        cityList.get(0).id==1
        cityList.get(0).name=="name-1"

        cityList.get(1).id==2
        cityList.get(1).name=="name-2"

        cityList.get(2).id==3
        cityList.get(2).name=="name-3"
    }
}
