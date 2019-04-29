package app.admin.jobsboard

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_OPERATOR'])
class JobController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Job.list(params), model:[jobCount: Job.count()]
    }

    def show(Job job) {
        respond job
    }

    def create() {
        respond new Job(params)
    }

    @Transactional
    def save(Job job) {
        if (job == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (job.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond job.errors, view:'create'
            return
        }

        job.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'job.label', default: 'Job'), job.id])
                redirect job
            }
            '*' { respond job, [status: CREATED] }
        }
    }

    def edit(Job job) {
        respond job
    }

    @Transactional
    def update(Job job) {
        if (job == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (job.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond job.errors, view:'edit'
            return
        }

        job.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'job.label', default: 'Job'), job.id])
                redirect job
            }
            '*'{ respond job, [status: OK] }
        }
    }

    @Transactional
    def delete(Job job) {

        if (job == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        job.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'job.label', default: 'Job'), job.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'job.label', default: 'Job'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
