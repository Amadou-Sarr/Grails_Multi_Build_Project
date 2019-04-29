package app.admin.jobsboard

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_OPERATOR'])
class TypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Type.list(params), model:[typeCount: Type.count()]
    }

    def show(Type type) {
        respond type
    }

    def create() {
        respond new Type(params)
    }

    @Transactional
    def save(Type type) {
        if (type == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (type.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond type.errors, view:'create'
            return
        }

        type.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'type.label', default: 'Type'), type.id])
                redirect type
            }
            '*' { respond type, [status: CREATED] }
        }
    }

    def edit(Type type) {
        respond type
    }

    @Transactional
    def update(Type type) {
        if (type == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (type.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond type.errors, view:'edit'
            return
        }

        type.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'type.label', default: 'Type'), type.id])
                redirect type
            }
            '*'{ respond type, [status: OK] }
        }
    }

    @Transactional
    def delete(Type type) {

        if (type == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        type.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'type.label', default: 'Type'), type.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'type.label', default: 'Type'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
