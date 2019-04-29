package app.admin.jobsboard

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PublisherController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    
    @Secured(["permitAll"])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Publisher.list(params), model:[publisherCount: Publisher.count()]
    }

    @Secured(["permitAll"])
    def show(Publisher publisher) {
        respond publisher
    }

    @Transactional
    def save(Publisher publisher) {
        if (publisher == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (publisher.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond publisher.errors, view:'create'
            return
        }

        publisher.save flush:true

        respond publisher, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Publisher publisher) {
        if (publisher == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (publisher.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond publisher.errors, view:'edit'
            return
        }

        publisher.save flush:true

        respond publisher, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Publisher publisher) {

        if (publisher == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        publisher.delete flush:true

        render status: NO_CONTENT
    }
}
