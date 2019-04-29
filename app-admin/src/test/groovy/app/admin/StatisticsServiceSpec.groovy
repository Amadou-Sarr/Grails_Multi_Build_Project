package app.admin

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(StatisticsService)
class StatisticsServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }

    void "get top publishers when we don't have nothing in our system"() {
         given: "when we don't have any job published"

         when: "we get top publishers"
         def publishers = service.getTopPublishers()
         then:"we will see 0 publishers"
         publishers.size() == 0
         }

}
